package btw.community.sockthing.socksmobs.mixins;

import btw.community.sockthing.socksmobs.interfaces.EntityAnimalInterface;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RenderChicken.class)
public abstract class RenderChickenMixin {

    private static final ResourceLocation BABY_CHICK_TEXTURE = new ResourceLocation("socksmobs:textures/entity/chicken/baby_chick.png");
    private static final ResourceLocation BABY_CHICK_FAMISHED_TEXTURE = new ResourceLocation("socksmobs:textures/entity/chicken/baby_chick_famished.png");
    private static final ResourceLocation BABY_CHICK_STARVING_TEXTURE = new ResourceLocation("socksmobs:textures/entity/chicken/baby_chick_starving.png");
    private static final ResourceLocation BABY_CHICK_GRAY_TEXTURE = new ResourceLocation("socksmobs:textures/entity/chicken/gray_baby_chick.png");
    private static final ResourceLocation BABY_CHICK_GRAY_FAMISHED_TEXTURE = new ResourceLocation("socksmobs:textures/entity/chicken/gray_baby_chick_famished.png");
    private static final ResourceLocation BABY_CHICK_GRAY_STARVING_TEXTURE = new ResourceLocation("socksmobs:textures/entity/chicken/gray_baby_chick_starving.png");
    private static final ResourceLocation BABY_CHICK_DARK_TEXTURE = new ResourceLocation("socksmobs:textures/entity/chicken/dark_baby_chick.png");
    private static final ResourceLocation BABY_CHICK_DARK_FAMISHED_TEXTURE = new ResourceLocation("socksmobs:textures/entity/chicken/dark_baby_chick_famished.png");
    private static final ResourceLocation BABY_CHICK_DARK_STARVING_TEXTURE = new ResourceLocation("socksmobs:textures/entity/chicken/dark_baby_chick_starving.png");
    private static final ResourceLocation CHICKEN_ORANGE_TEXTURE = new ResourceLocation("socksmobs:textures/entity/chicken/orange_chicken.png");
    private static final ResourceLocation CHICKEN_ORANGE_FAMISHED_TEXTURE = new ResourceLocation("socksmobs:textures/entity/chicken/orange_chicken_famished.png");
    private static final ResourceLocation CHICKEN_ORANGE_STARVING_TEXTURE = new ResourceLocation("socksmobs:textures/entity/chicken/orange_chicken_starving.png");
    private static final ResourceLocation CHICKEN_WHITE_TEXTURE = new ResourceLocation("socksmobs:textures/entity/chicken/white_chicken.png");
    private static final ResourceLocation CHICKEN_WHITE_FAMISHED_TEXTURE = new ResourceLocation("socksmobs:textures/entity/chicken/white_chicken_famished.png");
    private static final ResourceLocation CHICKEN_WHITE_STARVING_TEXTURE = new ResourceLocation("socksmobs:textures/entity/chicken/white_chicken_starving.png");
    private static final ResourceLocation CHICKEN_DARK_TEXTURE = new ResourceLocation("socksmobs:textures/entity/chicken/dark_chicken.png");
    private static final ResourceLocation CHICKEN_DARK_FAMISHED_TEXTURE = new ResourceLocation("socksmobs:textures/entity/chicken/dark_chicken_famished.png");
    private static final ResourceLocation CHICKEN_DARK_STARVING_TEXTURE = new ResourceLocation("socksmobs:textures/entity/chicken/dark_chicken_starving.png");

    private static final ResourceLocation ROOSTER_ORANGE_TEXTURE = new ResourceLocation("socksmobs:textures/entity/chicken/orange_rooster.png");
    private static final ResourceLocation ROOSTER_ORANGE_FAMISHED_TEXTURE = new ResourceLocation("socksmobs:textures/entity/chicken/orange_rooster_famished.png");
    private static final ResourceLocation ROOSTER_ORANGE_STARVING_TEXTURE = new ResourceLocation("socksmobs:textures/entity/chicken/orange_rooster_starving.png");
    private static final ResourceLocation ROOSTER_WHITE_TEXTURE = new ResourceLocation("socksmobs:textures/entity/chicken/white_rooster.png");
    private static final ResourceLocation ROOSTER_WHITE_FAMISHED_TEXTURE = new ResourceLocation("socksmobs:textures/entity/chicken/white_rooster_famished.png");
    private static final ResourceLocation ROOSTER_WHITE_STARVING_TEXTURE = new ResourceLocation("socksmobs:textures/entity/chicken/white_rooster_starving.png");
    private static final ResourceLocation ROOSTER_DARK_TEXTURE = new ResourceLocation("socksmobs:textures/entity/chicken/dark_rooster.png");
    private static final ResourceLocation ROOSTER_DARK_FAMISHED_TEXTURE = new ResourceLocation("socksmobs:textures/entity/chicken/dark_rooster_famished.png");
    private static final ResourceLocation ROOSTER_DARK_STARVING_TEXTURE = new ResourceLocation("socksmobs:textures/entity/chicken/dark_rooster_starving.png");


    @Inject(method = "getChickenTextures", at = @At(value = "HEAD"), cancellable = true)
    protected void getTextures(EntityChicken chicken, CallbackInfoReturnable<ResourceLocation> cir) {
        int type = ((EntityAnimalInterface) chicken).getType();
        int gender = ((EntityAnimalInterface) chicken).getGender();
        int hungerLevel = chicken.getHungerLevel();

        if (chicken.isChild()) {
            if (hungerLevel == 1) {
                cir.setReturnValue(getBabyFamishedTexture(type, gender));
            } else {
                cir.setReturnValue(hungerLevel == 2 ? getBabyStarvingTexture(type, gender) : getBabyTexture(type, gender));
            }
        } else {
            if (hungerLevel == 1) {
                cir.setReturnValue(getFamishedTexture(type, gender));
            } else {
                cir.setReturnValue(hungerLevel == 2 ? getStarvingTexture(type, gender) : getDefaultTexture(type, gender));
            }
        }
    }

    private ResourceLocation getBabyTexture(int type, int gender) {
        if (type == 1) return BABY_CHICK_GRAY_TEXTURE;
        if (type == 2) return BABY_CHICK_DARK_TEXTURE;
        return BABY_CHICK_TEXTURE;
    }

    private ResourceLocation getBabyFamishedTexture(int type, int gender) {
        if (type == 1) return BABY_CHICK_GRAY_FAMISHED_TEXTURE;
        if (type == 2) return BABY_CHICK_DARK_FAMISHED_TEXTURE;
        return BABY_CHICK_FAMISHED_TEXTURE;
    }

    private ResourceLocation getBabyStarvingTexture(int type, int gender) {
        if (type == 1) return BABY_CHICK_GRAY_STARVING_TEXTURE;
        if (type == 2) return BABY_CHICK_DARK_STARVING_TEXTURE;
        return BABY_CHICK_STARVING_TEXTURE;
    }

    private ResourceLocation getDefaultTexture(int type, int gender) {
        if (gender == 1) {
            if (type == 1) return ROOSTER_WHITE_TEXTURE;
            else if (type == 2) return ROOSTER_DARK_TEXTURE;
            else return ROOSTER_ORANGE_TEXTURE;
        }
        else {
            if (type == 1) return CHICKEN_WHITE_TEXTURE;
            else if (type == 2) return CHICKEN_DARK_TEXTURE;
            else return CHICKEN_ORANGE_TEXTURE;
        }
    }

    private ResourceLocation getFamishedTexture(int type, int gender) {
        if (gender == 1) {
            if (type == 1) return ROOSTER_WHITE_FAMISHED_TEXTURE;
            else if (type == 2) return ROOSTER_DARK_FAMISHED_TEXTURE;
            else return ROOSTER_ORANGE_FAMISHED_TEXTURE;
        }
        else {
            if (type == 1) return CHICKEN_WHITE_FAMISHED_TEXTURE;
            else if (type == 2) return CHICKEN_DARK_FAMISHED_TEXTURE;
            else return CHICKEN_ORANGE_FAMISHED_TEXTURE;
        }
    }

    private ResourceLocation getStarvingTexture(int type, int gender) {
        if (gender == 1) {
            if (type == 1) return ROOSTER_WHITE_STARVING_TEXTURE;
            else if (type == 2) return ROOSTER_DARK_STARVING_TEXTURE;
            else return ROOSTER_ORANGE_STARVING_TEXTURE;
        }
        else {
            if (type == 1) return CHICKEN_WHITE_STARVING_TEXTURE;
            else if (type == 2) return CHICKEN_DARK_STARVING_TEXTURE;
            else return CHICKEN_ORANGE_STARVING_TEXTURE;
        }
    }
}
