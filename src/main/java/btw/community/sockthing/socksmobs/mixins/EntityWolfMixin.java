package btw.community.sockthing.socksmobs.mixins;

import btw.community.sockthing.socksmobs.enums.EnumWolfType;
import btw.community.sockthing.socksmobs.interfaces.EntityAnimalInterface;
import btw.community.sockthing.socksmobs.utils.MobUtils;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityWolf.class)
public abstract class EntityWolfMixin extends EntityTameable implements EntityAnimalInterface {

    public EntityWolfMixin(World par1World) {
        super(par1World);
    }

    @Override
    public void preInitCreature() {
        //biomes
    }

    @Inject(method = "entityInit", at = @At(value = "TAIL"))
    public void entityInit(CallbackInfo ci) {
        this.dataWatcher.addObject(MobUtils.DATA_TYPE_ID, (byte)0);
    }

    @Override
    public EntityLivingData onSpawnWithEgg(EntityLivingData data) {
        data = super.onSpawnWithEgg(data);

        int totalTypes = EnumWolfType.values().length;
        int type = this.getRNG().nextInt(totalTypes);

        this.setType(type);

        return data;
    }

    // --- NBT --- //

    @Inject(method = "writeEntityToNBT", at = @At(value = "TAIL"))
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound, CallbackInfo ci) {
        par1NBTTagCompound.setInteger("type", this.getType());
    }

    @Inject(method = "readEntityFromNBT", at = @At(value = "TAIL"))
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound, CallbackInfo ci) {
        if (par1NBTTagCompound.hasKey("type")) {
            this.setType(par1NBTTagCompound.getInteger("type"));
        }
    }

    // --- EntityAnimalInterface --- //

    public int getType() {
        return this.dataWatcher.getWatchableObjectByte(MobUtils.DATA_TYPE_ID);
    }
    public void setType(int type) {
        this.dataWatcher.updateObject(MobUtils.DATA_TYPE_ID, (byte)type);
    }
}

