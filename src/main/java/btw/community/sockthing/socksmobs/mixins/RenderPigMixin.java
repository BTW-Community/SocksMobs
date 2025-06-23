package btw.community.sockthing.socksmobs.mixins;

import btw.community.sockthing.socksmobs.interfaces.EntityAnimalInterface;
import net.minecraft.src.EntityPig;
import net.minecraft.src.ModelBase;
import net.minecraft.src.RenderPig;
import net.minecraft.src.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RenderPig.class)
public abstract class RenderPigMixin {

    private static final ResourceLocation PIG_STARVING_TEXTURE = new ResourceLocation("btw:textures/entity/mob/pig/pig_starving.png");
    private static final ResourceLocation PIG_FAMISHED_TEXTURE = new ResourceLocation("btw:textures/entity/mob/pig/pig_famished.png");
    private static final ResourceLocation MR_PIG_TEXTURE = new ResourceLocation("btw:textures/entity/mob/pig/pig_breeding_harness.png");
    private static final ResourceLocation PIG_SADDLE_TEXTURE = new ResourceLocation("textures/entity/pig/pig_saddle.png");
    private static final ResourceLocation PIG_DEFAULT_TEXTURE = new ResourceLocation("textures/entity/pig/pig.png");

    private static final ResourceLocation PIG_WARM_TEXTURE = new ResourceLocation("socksmobs:textures/entity/pig/warm_pig.png");
    private static final ResourceLocation PIG_WARM_FAMISHED_TEXTURE = new ResourceLocation("socksmobs:textures/entity/pig/warm_pig_famished.png");
    private static final ResourceLocation PIG_WARM_STARVING_TEXTURE = new ResourceLocation("socksmobs:textures/entity/pig/warm_pig_starving.png");
    private static final ResourceLocation PIG_WARM_HARNESS_TEXTURE = new ResourceLocation("socksmobs:textures/entity/pig/warm_pig_harness.png");

    private static final ResourceLocation PIG_COLD_TEXTURE = new ResourceLocation("socksmobs:textures/entity/pig/cold_pig.png");
    private static final ResourceLocation PIG_COLD_FAMISHED_TEXTURE = new ResourceLocation("socksmobs:textures/entity/pig/cold_pig_famished.png");
    private static final ResourceLocation PIG_COLD_STARVING_TEXTURE = new ResourceLocation("socksmobs:textures/entity/pig/cold_pig_starving.png");
    private static final ResourceLocation PIG_COLD_HARNESS_TEXTURE = new ResourceLocation("socksmobs:textures/entity/mob/pig/cold_pig_harness.png");

    public RenderPigMixin(ModelBase model, float par2) {
        super();
    }

    @Inject(method = "getPigTextures", at = @At(value = "HEAD"), cancellable = true)
    protected void getPigTextures(EntityPig pig, CallbackInfoReturnable<ResourceLocation> cir) {
        int pigType = ((EntityAnimalInterface) pig).getType();

        if (pig.getWearingBreedingHarness()) {
            cir.setReturnValue(getPigHarnessTexture(pigType));
        } else {
            int iHungerLevel = pig.getHungerLevel();
            if (iHungerLevel == 1) {
                cir.setReturnValue(getPigFamishedTexture(pigType));
            } else {
                cir.setReturnValue(iHungerLevel == 2 ? getPigStarvingTexture(pigType) : getPigDefaultTexture(pigType));
            }
        }
    }

    private ResourceLocation getPigDefaultTexture(int pigType) {
        if (pigType == 1) return PIG_COLD_TEXTURE;
        if (pigType == 2) return PIG_WARM_TEXTURE;
        return PIG_DEFAULT_TEXTURE;
    }

    private ResourceLocation getPigStarvingTexture(int pigType) {
        if (pigType == 1) return PIG_COLD_STARVING_TEXTURE;
        if (pigType == 2) return PIG_WARM_STARVING_TEXTURE;
        return PIG_STARVING_TEXTURE;
    }

    private ResourceLocation getPigFamishedTexture(int pigType) {
        if (pigType == 1) return PIG_COLD_FAMISHED_TEXTURE;
        if (pigType == 2) return PIG_WARM_FAMISHED_TEXTURE;
        return PIG_FAMISHED_TEXTURE;
    }

    private ResourceLocation getPigHarnessTexture(int pigType){
        if (pigType == 1) return PIG_COLD_HARNESS_TEXTURE;
        if (pigType == 2) return PIG_WARM_HARNESS_TEXTURE;
        return MR_PIG_TEXTURE;
    }
}
