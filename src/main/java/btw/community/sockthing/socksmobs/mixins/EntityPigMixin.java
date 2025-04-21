package btw.community.sockthing.socksmobs.mixins;

import btw.community.sockthing.socksmobs.interfaces.EntityPigInterface;
import btw.world.util.data.BTWWorldData;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityPig.class)
public abstract class EntityPigMixin extends EntityAnimal implements EntityPigInterface {

    private final int NORMAL = 0;
    private final int COLD = 1;
    private final int WARM = 2;

    public EntityPigMixin(World world) {
        super(world);
    }

    public int getPigType() {
        return this.dataWatcher.getWatchableObjectByte(13);
    }

    public void setPigType(int pigType) {
        this.dataWatcher.updateObject(13, (byte)pigType);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void onConstructed(World world, CallbackInfo ci) {
        this.dataWatcher.addObject(13, (byte)0);
    }

    @Override
    public void preInitCreature() {
        if (isColdBiome(this.worldObj.getBiomeGenForCoords((int) this.posX, (int) this.posZ))) setPigType(COLD);
        else if (isWarmBiome(this.worldObj.getBiomeGenForCoords((int) this.posX, (int) this.posZ))) setPigType(WARM);
        else setPigType(NORMAL);
    }

    @Override
    public EntityLivingData onSpawnWithEgg(EntityLivingData data) {
        data = super.onSpawnWithEgg(data);

        if (isColdBiome(this.worldObj.getBiomeGenForCoords((int) this.posX, (int) this.posZ))) setPigType(COLD);
        else if (isWarmBiome(this.worldObj.getBiomeGenForCoords((int) this.posX, (int) this.posZ))) setPigType(WARM);
        else setPigType(NORMAL);

        return data;
    }

    private boolean isWarmBiome(BiomeGenBase biome) {
        if (biome == BiomeGenBase.jungle) return true;
        if (biome == BiomeGenBase.jungleHills) return true;
        if (biome == BiomeGenBase.desertHills) return true;
        return biome == BiomeGenBase.desert;
    }

    private boolean isColdBiome(BiomeGenBase biome) {
        if (biome == BiomeGenBase.iceMountains) return true;
        if (biome == BiomeGenBase.icePlains) return true;
        if (biome == BiomeGenBase.frozenOcean) return true;
        if (biome == BiomeGenBase.frozenRiver) return true;
        if (biome == BiomeGenBase.taigaHills) return true;
        return biome == BiomeGenBase.taiga;
    }

    @Inject(method = "writeEntityToNBT", at = @At(value = "TAIL"))
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound, CallbackInfo ci) {
        par1NBTTagCompound.setInteger("pigType", this.getPigType());

    }

    @Inject(method = "readEntityFromNBT", at = @At(value = "TAIL"))
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound, CallbackInfo ci) {
        if (par1NBTTagCompound.hasKey("pigType")) {
            int var2 = par1NBTTagCompound.getInteger("pigType");
            this.setPigType(var2);
        }
    }


}
