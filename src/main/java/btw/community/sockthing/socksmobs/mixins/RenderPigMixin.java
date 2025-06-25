package btw.community.sockthing.socksmobs.mixins;

import btw.community.sockthing.socksmobs.entities.models.CustomPigModel;
import btw.community.sockthing.socksmobs.interfaces.EntityAnimalInterface;
import btw.community.sockthing.socksmobs.utils.AnimalTextureManager;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RenderPig.class)
public abstract class RenderPigMixin extends RenderLiving {

    private static final ResourceLocation SADDLE_TEXTURE = new ResourceLocation("socksmobs:textures/entity/pig/pig_saddle.png");
    private static final ResourceLocation MR_PIG_TEXTURE = new ResourceLocation("socksmobs:textures/entity/pig/pig_breeding_harness.png");

    private final ModelBase PIG_RENDER_PASS_MODEL = new CustomPigModel();

    public RenderPigMixin(ModelBase model, float par2) {
        super(model,par2);
    }

    @Inject(method = "<init>", at = @At(value = "TAIL"))
    public void init(ModelBase par1ModelBase, ModelBase par2ModelBase, float par3, CallbackInfo ci){
        this.setRenderPassModel(PIG_RENDER_PASS_MODEL);
    }

    @Inject(method = "renderSaddledPig", at = @At(value = "HEAD"), cancellable = true)
    public void renderSaddledPig(EntityPig par1EntityPig, int par2, float par3, CallbackInfoReturnable<Integer> cir) {
        int returnValue = -1;

        if (par2 == 0){
            if (par1EntityPig.getSaddled()){
                this.bindTexture(SADDLE_TEXTURE);
                returnValue = 1;
            }
            if (par1EntityPig.getWearingBreedingHarness()){
                this.bindTexture(MR_PIG_TEXTURE);
                returnValue = 1;
            }
        }
        cir.setReturnValue( returnValue );
        cir.cancel();
    }

    @Inject(method = "getPigTextures", at = @At(value = "HEAD"), cancellable = true)
    protected void getPigTextures(EntityPig pig, CallbackInfoReturnable<ResourceLocation> cir) {
        int pigType = ((EntityAnimalInterface) pig).getType();
        int hungerLevel = pig.getHungerLevel();

        cir.setReturnValue(AnimalTextureManager.getPigTexture(pigType, hungerLevel));
    }
}
