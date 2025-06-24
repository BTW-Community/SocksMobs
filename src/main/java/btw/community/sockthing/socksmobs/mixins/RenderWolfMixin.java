package btw.community.sockthing.socksmobs.mixins;

import btw.community.sockthing.socksmobs.enums.WolfState;
import btw.community.sockthing.socksmobs.interfaces.EntityAnimalInterface;
import btw.community.sockthing.socksmobs.utils.AnimalTextureManager;
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
                cir.setReturnValue( AnimalTextureManager.getWolfTexture(type, WolfState.TAME_STARVING) );
                cir.cancel();
            }
            cir.setReturnValue( AnimalTextureManager.getWolfTexture(type, WolfState.TAME) );
            cir.cancel();
        }
        if (wolf.isAngry()) {
            cir.setReturnValue( AnimalTextureManager.getWolfTexture(type, WolfState.ANGRY) );
            cir.cancel();
        }
        if (wolf.isStarving() || wolf.hasAttackTarget()) {
            cir.setReturnValue( AnimalTextureManager.getWolfTexture(type, WolfState.WILD_STARVING) );
            cir.cancel();
        }
        cir.setReturnValue(wolf.isTamed() ? AnimalTextureManager.getWolfTexture(type, WolfState.TAME) : (wolf.isAngry() ? AnimalTextureManager.getWolfTexture(type, WolfState.ANGRY) : AnimalTextureManager.getWolfTexture(type, WolfState.WILD)));
        cir.cancel();
    }
}
