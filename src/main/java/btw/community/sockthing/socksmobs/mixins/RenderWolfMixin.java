package btw.community.sockthing.socksmobs.mixins;

import btw.community.sockthing.socksmobs.enums.WolfState;
import btw.community.sockthing.socksmobs.enums.WolfType;
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
        int subtype = ((EntityAnimalInterface) wolf).getType();

        if (wolf.isTamed()) {
            if (wolf.isStarving()) {
                cir.setReturnValue( AnimalTextureManager.getWolfTexture(subtype, WolfState.TAME_STARVING) );
                cir.cancel();
            }
            cir.setReturnValue( AnimalTextureManager.getWolfTexture(subtype, WolfState.TAME) );
            cir.cancel();
        }
        if (wolf.isAngry()) {
            cir.setReturnValue( AnimalTextureManager.getWolfTexture(subtype, WolfState.ANGRY) );
            cir.cancel();
        }
        if (wolf.isStarving() || wolf.hasAttackTarget()) {
            cir.setReturnValue( AnimalTextureManager.getWolfTexture(subtype, WolfState.WILD_STARVING) );
            cir.cancel();
        }
        cir.setReturnValue(wolf.isTamed() ? AnimalTextureManager.getWolfTexture(subtype, WolfState.TAME) :
                (wolf.isAngry() ? AnimalTextureManager.getWolfTexture(subtype, WolfState.ANGRY) :
                        AnimalTextureManager.getWolfTexture(subtype, WolfState.NORMAL)));
        cir.cancel();
    }
}
