package btw.community.sockthing.socksmobs.mixins;

import btw.community.sockthing.socksmobs.SocksMobsAddon;
import btw.community.sockthing.socksmobs.entities.EntityRooster;
import btw.community.sockthing.socksmobs.interfaces.EntityAnimalInterface;
import btw.community.sockthing.socksmobs.items.SMItems;
import btw.community.sockthing.socksmobs.utils.MobUtils;
import btw.world.util.WorldUtils;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityChicken.class)
public abstract class EntityChickenMixin extends EntityAnimal implements EntityAnimalInterface {

    @Shadow protected long timeToLayEgg;

    public EntityChickenMixin(World world) {
        super(world);
    }

    @Inject(method = "getLivingSound", at = @At(value = "HEAD"), cancellable = true)
    public void getLivingSound(CallbackInfoReturnable<String> cir) {
        String returnSound;

        if(this.isChild()){
            if (this.isStarving())  returnSound = SocksMobsAddon.BABY_CHICK_HURT.sound();
            else  returnSound = SocksMobsAddon.BABY_CHICK_CHIRP.sound();

            cir.setReturnValue( returnSound );
        }
    }

    @Inject(method = "getHurtSound", at = @At(value = "HEAD"), cancellable = true)
    public void getHurtSound(CallbackInfoReturnable<String> cir) {
        if(this.isChild()) cir.setReturnValue( SocksMobsAddon.BABY_CHICK_HURT.sound() );
    }

    @Inject(method = "getDeathSound", at = @At(value = "HEAD"), cancellable = true)
    public void getDeathSound(CallbackInfoReturnable<String> cir) {
        if(this.isChild()) cir.setReturnValue( SocksMobsAddon.BABY_CHICK_HURT.sound() );
    }

    // --- Init --- //

    @Override
    public void preInitCreature() {
        //biomes
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(MobUtils.DATA_TYPE_ID, (byte)0);
        this.dataWatcher.addObject(MobUtils.DATA_GENDER_ID, (byte)0);
        this.dataWatcher.addObject(MobUtils.DATA_PREGNANT_ID, (byte)0);
        this.dataWatcher.addObject(MobUtils.DATA_AMOUNT_ID, (byte)0);
        this.dataWatcher.addObject(MobUtils.DATA_DELAY_ID, 0);
    }

    @Override
    public EntityLivingData onSpawnWithEgg(EntityLivingData data) {
        data = super.onSpawnWithEgg(data);

        int type = this.getRNG().nextInt(MobUtils.SPAWN_TYPE_CHANCE);
        boolean shouldBeMale = this.getRNG().nextInt(MobUtils.SPAWN_GENDER_CHANCE) == 0;

        setType(type);

        EntityChicken thisObject = (EntityChicken)(Object)this;
        if (shouldBeMale || thisObject instanceof EntityRooster){
            this.setGender(MobUtils.MALE);
        }
        else this.setGender(MobUtils.FEMALE);

        return data;
    }

    // --- Breeding --- //

    @Override
    public boolean canMateWith(EntityAnimal par1EntityAnimal) {
        // Can't mate with itself
        if (par1EntityAnimal == this) {
            return false;
        }

        // Ensure the other entity is a type of chicken
        if (!(par1EntityAnimal instanceof EntityChicken)) {
            return false;
        }

        // Prevent two roosters from mating
        EntityChicken thisObject = (EntityChicken)(Object)this;
        if (thisObject instanceof EntityRooster && par1EntityAnimal instanceof EntityRooster) {
            return false;
        }

        // Check opposite gender
        if (this.getGender() == ((EntityAnimalInterface) par1EntityAnimal).getGender()) {
            return false;
        }

        // Both must be in love
        return this.isInLove() && par1EntityAnimal.isInLove();
    }

    @Inject(method = "onLivingUpdate", at = @At(value = "TAIL"))
    public void onLivingUpdate(CallbackInfo ci) {
        EntityChicken thisObject = (EntityChicken)(Object)this;
        if (!this.isChild()){
            if (isRooster(thisObject)){

                handleDelayTimer();
                handleRoosterCrowing();
            }
        }
    }

    private void handleDelayTimer() {
        if (this.getDelayTimer() > 0){
            this.setDelayTimer(getDelayTimer() - 1);
        }

        if (this.getDelayTimer() == 0){
            if (this.getAmount() < 4){
                this.setGrowingAge(0);
                this.setInLove(6000);
            }
            else this.setDelayTimer(-1);
        }
    }

    private void handleRoosterCrowing() {
        long currentTime = WorldUtils.getOverworldTimeServerOnly();

        if (isDawn(currentTime)){
            if (currentTime % 50 == 0 && this.worldObj.rand.nextInt(MobUtils.ROOSTER_CROW_CHANCE) == 0) this.worldObj.playSoundAtEntity(this, SocksMobsAddon.ROOSTER_CROWING.sound(), this.getSoundVolume(), 1.0f); //this.rand.nextFloat() * 0.2f + 1.5f);
        }
    }

    @Override
    protected void procreate(EntityAnimal targetMate) {
        double dChildX = this.posX;
        double dChildY = this.posY;
        double dChildZ = this.posZ;
        if (this.getWearingBreedingHarness()) {
            dChildX = (this.posX + targetMate.posX) / 2.0;
            dChildY = (this.posY + targetMate.posY) / 2.0;
            dChildZ = (this.posZ + targetMate.posZ) / 2.0;
        }

        this.resetMatingStateOfBothParents(targetMate);

        int min = MobUtils.MIN_DELAY_TIME; // inclusive 30s
        int max = MobUtils.MAX_DELAY_TIME; // exclusive 60s
        int delayTime = this.getRNG().nextInt(max - min) + min;

        EntityChicken thisObject = (EntityChicken)(Object)this;
        handleBreedingInteraction(thisObject, delayTime);
        handleBreedingInteraction((EntityChicken) targetMate, delayTime);

        this.worldObj.playSoundAtEntity(this, this.getDeathSound(), this.getSoundVolume(), (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2f + 1.0f);

    }

    public void handleBreedingInteraction(EntityChicken targetChicken, int delayTime) {
        EntityAnimalInterface target = (EntityAnimalInterface) targetChicken;
        if (isRooster((EntityChicken)target)) {
            if (target.getAmount() <= MobUtils.MAX_BREED_AMOUNT) {
                target.setAmount(target.getAmount() + 1);
                target.setDelayTimer(delayTime);
            }
        } else {
            target.setPregnant(true);
        }
    }

    @Inject(method = "onEatBreedingItem", at = @At(value = "HEAD"), cancellable = true)
    public void onEatBreedingItem(CallbackInfo ci) {
        if (this.getGender() == MobUtils.FEMALE){
            this.setInLove(6000);
        }
        else this.setDelayTimer(600); //MALE

        long currentTime = WorldUtils.getOverworldTimeServerOnly();
        this.timeToLayEgg = ((currentTime + 12000L) / 24000L + 1L) * 24000L;
        this.timeToLayEgg += (long)(-1450 + this.rand.nextInt(600));
        this.worldObj.playSoundAtEntity(this, this.getDeathSound(), this.getSoundVolume(), this.rand.nextFloat() * 0.2f + 1.5f);
        ci.cancel();
    }

    @Override
    public void updateHungerState() {
        if (!this.isChild() && this.isFullyFed() && this.timeToLayEgg > 0L && this.validateTimeToLayEgg() && WorldUtils.getOverworldTimeServerOnly() > this.timeToLayEgg) {
            if (this.getGender() == 0){
                this.playSound("mob.slime.attack", 1.0f, this.getSoundPitch());
                this.playSound(this.getDeathSound(), this.getSoundVolume(), (this.getSoundPitch() + 0.25f) * (this.getSoundPitch() + 0.25f));
                layEgg();
                resetChickenState();
            }
            else resetChickenState();
        }
        super.updateHungerState();
    }

    private void layEgg() {
        if (this.getIsPregnant()){
            this.dropItem(Item.egg.itemID, 1);
        }
        else this.dropItem(SMItems.unfertilizedEgg.itemID, 1);
    }

    private void resetChickenState() {
        this.timeToLayEgg = 0L;
        this.setPregnant(false);
        this.setAmount(0);
        this.setGrowingAge(0);
        this.setInLove(0);
        this.setDelayTimer(-1);
    }

    private boolean validateTimeToLayEgg() {
        long currentTime = WorldUtils.getOverworldTimeServerOnly();
        long deltaTime = this.timeToLayEgg - currentTime;
        if (deltaTime > 48000L) {
            this.timeToLayEgg = 0L;
            return false;
        }
        return true;
    }

    public boolean isDawn(long time) {
        long timeOfDay = time % 24000;

        if (time < 250) return false; //skip the very first dawn when you initially start the world

        return timeOfDay >= 23500 || timeOfDay < 250;
    }

    private boolean isRooster(EntityChicken chicken) {
        return this.getGender() == MobUtils.MALE || chicken instanceof EntityRooster;
    }

    // --- NBT --- //

    @Inject(method = "writeEntityToNBT", at = @At(value = "TAIL"))
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound, CallbackInfo ci) {
        par1NBTTagCompound.setInteger("type", this.getType());
        par1NBTTagCompound.setInteger("gender", this.getGender());
        par1NBTTagCompound.setInteger("pregnant", this.getIsPregnant() ? 1 : 0);
        par1NBTTagCompound.setInteger("amount", this.getAmount());
        par1NBTTagCompound.setInteger("delay", this.getDelayTimer());

    }

    @Inject(method = "readEntityFromNBT", at = @At(value = "TAIL"))
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound, CallbackInfo ci) {
        if (par1NBTTagCompound.hasKey("type")) {
            this.setType(par1NBTTagCompound.getInteger("type"));
        }
        if (par1NBTTagCompound.hasKey("gender")) {
            this.setGender(par1NBTTagCompound.getInteger("gender"));
        }
        if (par1NBTTagCompound.hasKey("pregnant")) {
            this.setPregnant(par1NBTTagCompound.getInteger("pregnant") == 1);
        }
        if (par1NBTTagCompound.hasKey("amount")) {
            this.setAmount(par1NBTTagCompound.getInteger("amount"));
        }
        if (par1NBTTagCompound.hasKey("delay")) {
            this.setDelayTimer(par1NBTTagCompound.getInteger("delay"));
        }
    }

    // --- EntityAnimalInterface --- //

    public int getType() {
        return this.dataWatcher.getWatchableObjectByte(MobUtils.DATA_TYPE_ID);
    }
    public void setType(int type) {
        this.dataWatcher.updateObject(MobUtils.DATA_TYPE_ID, (byte)type);
    }
    public int getGender() {
        return this.dataWatcher.getWatchableObjectByte(MobUtils.DATA_GENDER_ID);
    }
    public void setGender(int gender) {
        this.dataWatcher.updateObject(MobUtils.DATA_GENDER_ID, (byte)gender);
    }
    public boolean getIsPregnant() { return this.dataWatcher.getWatchableObjectByte(MobUtils.DATA_PREGNANT_ID) == 1; }
    public void setPregnant(boolean pregnant) { this.dataWatcher.updateObject(MobUtils.DATA_PREGNANT_ID, pregnant ? (byte)1 : (byte)0); }
    public int getAmount() {
        return this.dataWatcher.getWatchableObjectByte(MobUtils.DATA_AMOUNT_ID);
    }
    public void setAmount(int amount) {
        this.dataWatcher.updateObject(MobUtils.DATA_AMOUNT_ID, (byte)amount);
    }
    public int getDelayTimer() { return this.dataWatcher.getWatchableObjectInt(MobUtils.DATA_DELAY_ID); }
    public void setDelayTimer(int delaytimer) { this.dataWatcher.updateObject(MobUtils.DATA_DELAY_ID, delaytimer);}


}
