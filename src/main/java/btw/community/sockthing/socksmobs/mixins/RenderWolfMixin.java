package btw.community.sockthing.socksmobs.mixins;

import btw.community.sockthing.socksmobs.enums.WolfState;
import btw.community.sockthing.socksmobs.enums.WolfType;
import btw.community.sockthing.socksmobs.interfaces.EntityAnimalInterface;
import btw.community.sockthing.socksmobs.utils.AnimalTextureManager;
import btw.community.sockthing.socksmobs.utils.WolfTextures;
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

        cir.setReturnValue(WolfTextures.getWolfTexture(subtype, wolf.isTamed(), wolf.isStarving(), wolf.isAngry(), wolf.hasAttackTarget()));
        cir.cancel();
    }
}
