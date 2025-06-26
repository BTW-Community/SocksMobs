package btw.community.sockthing.socksmobs.mixins;

import btw.community.sockthing.socksmobs.entities.models.CustomPigModel;
import btw.community.sockthing.socksmobs.enums.PigExtraState;
import btw.community.sockthing.socksmobs.enums.PigType;
import btw.community.sockthing.socksmobs.interfaces.EntityAnimalInterface;
import btw.community.sockthing.socksmobs.utils.AnimalTextureManager;
import btw.community.sockthing.socksmobs.utils.PigTextures;
import com.prupe.mcpatcher.mob.MobOverlay;
import net.minecraft.src.*;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RenderPig.class)
public abstract class RenderPigMixin extends RenderLiving {

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
            if (par1EntityPig.getSaddled() || par1EntityPig.getWearingBreedingHarness()){
                this.bindTexture( PigTextures.getOverlayTexture(par1EntityPig.getHungerLevel(), par1EntityPig.getWearingBreedingHarness(), par1EntityPig.getSaddled()));
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
        int extraState = 0;

        if (pigType == PigType.MUDDY.ordinal()){
            extraState = ((EntityAnimalInterface) pig).getExtraState();
        }

        cir.setReturnValue(PigTextures.getPigTexture(pigType, hungerLevel, extraState));
    }

    @Override
    protected void renderEquippedItems(EntityLivingBase par1EntityLivingBase, float par2) {
        if ( ((EntityAnimalInterface) par1EntityLivingBase).getType() == PigType.MUDDY.ordinal()
            && ((EntityAnimalInterface) par1EntityLivingBase).getExtraState() == PigExtraState.WET.ordinal())
        {
            this.renderPigFlower((EntityPig)par1EntityLivingBase, par2);
        }
    }

    protected void renderPigFlower(EntityPig pig, float par2) {
        super.renderEquippedItems(pig, par2);
        if (pig.isChild()) {
            MobOverlay.finishMooshroom();
        } else if (!pig.getWearingBreedingHarness() && pig.isFullyFed()) {
            this.bindTexture(MobOverlay.setupMooshroom(pig, TextureMap.locationBlocksTexture));
            GL11.glEnable(2884);
            GL11.glPushMatrix();
            ((ModelQuadruped)this.mainModel).head.postRender(0.0625f);
            float scale = 0.6f;
            GL11.glScalef(scale, -scale, scale);
            GL11.glTranslatef(0.15f, 0.925f, -0.45f);
            GL11.glRotatef(12.0f, 0.0f, 1.0f, 0.0f);
            if (pig.isFullyFed() && !MobOverlay.renderMooshroomOverlay(0.0)) {
                this.renderBlocks.renderBlockAsItem(Block.plantRed, 0, 2f);
            }
            GL11.glPopMatrix();
            GL11.glDisable(2884);
            MobOverlay.finishMooshroom();
        }
    }
}
