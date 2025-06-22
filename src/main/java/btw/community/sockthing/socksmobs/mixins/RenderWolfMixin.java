package btw.community.sockthing.socksmobs.mixins;

import btw.community.sockthing.socksmobs.enums.EnumWolfState;
import btw.community.sockthing.socksmobs.interfaces.EntityAnimalInterface;
import btw.community.sockthing.socksmobs.utils.WolfTextureManager;
import net.minecraft.src.EntityWolf;
import net.minecraft.src.RenderWolf;
import net.minecraft.src.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RenderWolf.class)
public class RenderWolfMixin {

    @Inject(method = "func_110914_a", at = @At(value = "HEAD"), cancellable = true)
    protected void getWolfTextures(EntityWolf wolf, CallbackInfoReturnable<ResourceLocation> cir) {
        int type = ((EntityAnimalInterface) wolf).getType();

        if (wolf.isTamed()) {
            if (wolf.isStarving()) {
                cir.setReturnValue( WolfTextureManager.getTexture(type, EnumWolfState.TAME_STARVING) );
                cir.cancel();
            }
            cir.setReturnValue( WolfTextureManager.getTexture(type, EnumWolfState.TAME) );
            cir.cancel();
        }
        if (wolf.isAngry()) {
            cir.setReturnValue( WolfTextureManager.getTexture(type, EnumWolfState.ANGRY) );
            cir.cancel();
        }
        if (wolf.isStarving() || wolf.hasAttackTarget()) {
            cir.setReturnValue( WolfTextureManager.getTexture(type, EnumWolfState.WILD_STARVING) );
            cir.cancel();
        }
        cir.setReturnValue(wolf.isTamed() ? WolfTextureManager.getTexture(type, EnumWolfState.TAME) : (wolf.isAngry() ? WolfTextureManager.getTexture(type, EnumWolfState.ANGRY) : WolfTextureManager.getTexture(type, EnumWolfState.WILD)));
        cir.cancel();
    }
}
