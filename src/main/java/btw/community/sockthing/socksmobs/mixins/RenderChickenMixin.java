package btw.community.sockthing.socksmobs.mixins;

import btw.community.sockthing.socksmobs.interfaces.EntityAnimalInterface;
import btw.community.sockthing.socksmobs.utils.AnimalTextureManager;
import btw.community.sockthing.socksmobs.utils.ChickenTextures;
import btw.community.sockthing.socksmobs.utils.MobUtils;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RenderChicken.class)
public abstract class RenderChickenMixin {
    @Inject(method = "getChickenTextures", at = @At(value = "HEAD"), cancellable = true)
    protected void getTextures(EntityChicken chicken, CallbackInfoReturnable<ResourceLocation> cir) {
        int subtype = ((EntityAnimalInterface) chicken).getType();
        int hungerLevel = chicken.getHungerLevel();

        if (!chicken.isChild()) {
            if (subtype == ChickenTextures.DEFAULT){
                if (hungerLevel == ChickenTextures.FAMISHED) cir.setReturnValue( ChickenTextures.CHICKEN_FAMISHED_TEXTURE );
                else if (hungerLevel == ChickenTextures.STARVING) cir.setReturnValue( ChickenTextures.CHICKEN_STARVING_TEXTURE );
                else cir.setReturnValue( ChickenTextures.CHICKEN_TEXTURE );
                cir.cancel();
            }
            else{
                cir.setReturnValue( ChickenTextures.CHICKEN_TEXTURE );
                cir.cancel();
            }
        }
        else {
            if (subtype == ChickenTextures.DEFAULT){
                if (hungerLevel == ChickenTextures.FAMISHED) cir.setReturnValue( ChickenTextures.BABY_CHICK_FAMISHED_TEXTURE );
                else if (hungerLevel == ChickenTextures.STARVING) cir.setReturnValue( ChickenTextures.BABY_CHICK_STARVING_TEXTURE );
                else cir.setReturnValue( ChickenTextures.BABY_CHICK_TEXTURE );
                cir.cancel();
            }
            else {
                cir.setReturnValue( ChickenTextures.BABY_CHICK_TEXTURE );
                cir.cancel();
            }
        }
    }
}
