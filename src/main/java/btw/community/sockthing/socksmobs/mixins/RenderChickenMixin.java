package btw.community.sockthing.socksmobs.mixins;

import btw.community.sockthing.socksmobs.interfaces.EntityAnimalInterface;
import btw.community.sockthing.socksmobs.utils.ChickenTextures;
import btw.community.sockthing.socksmobs.utils.ChickenUtils;
import btw.community.sockthing.socksmobs.utils.MobUtils;
import btw.community.sockthing.socksmobs.utils.PigTextures;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RenderChicken.class)
public abstract class RenderChickenMixin {

    @Inject(method = "getChickenTextures", at = @At(value = "HEAD"), cancellable = true)
    protected void getTextures(EntityChicken chicken, CallbackInfoReturnable<ResourceLocation> cir) {
        if (!chicken.isChild()){
            cir.setReturnValue(ChickenTextures.getChickenTexture(chicken));
            cir.cancel();
        }
        else {
            cir.setReturnValue(ChickenTextures.getBabyChickTexture(chicken));
        }
    }
}
