package btw.community.sockthing.socksmobs.mixins;

import btw.community.sockthing.socksmobs.interfaces.EntityAnimalInterface;
import btw.community.sockthing.socksmobs.utils.AnimalTextureManager;
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
        int gender = ((EntityAnimalInterface) chicken).getGender();
        int hungerLevel = chicken.getHungerLevel();

        if (chicken.isChild()) {
            AnimalTextureManager.getChickenTexture("baby_chick", subtype, hungerLevel);
        } else {
            if (gender == MobUtils.MALE){
                AnimalTextureManager.getChickenTexture("rooster", subtype, hungerLevel);
            }
            else AnimalTextureManager.getChickenTexture("chicken", subtype, hungerLevel);
        }
    }
}
