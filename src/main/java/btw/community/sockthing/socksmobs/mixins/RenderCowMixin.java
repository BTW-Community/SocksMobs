package btw.community.sockthing.socksmobs.mixins;

import btw.community.sockthing.socksmobs.entities.models.CustomCowUdderModel;
import btw.community.sockthing.socksmobs.enums.CowExtraState;
import btw.community.sockthing.socksmobs.enums.CowType;
import btw.community.sockthing.socksmobs.interfaces.EntityAnimalInterface;
import btw.community.sockthing.socksmobs.utils.AnimalTextureManager;
import btw.community.sockthing.socksmobs.utils.CowTextures;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RenderCow.class)
public abstract class RenderCowMixin extends RenderLiving {
    private static final ResourceLocation BREEDING_HARNESS_TEXTURE = new ResourceLocation("socksmobs:textures/entity/cow/cow_breeding_harness.png");
    private static final ResourceLocation COW_UDDER_TEXTURE = new ResourceLocation("socksmobs:textures/entity/cow/cow_udder.png");
    private static final ResourceLocation BREEDING_HARNESS_UDDER_TEXTURE = new ResourceLocation("socksmobs:textures/entity/cow/cow_udder_breeding_harness.png");
    private CustomCowUdderModel COW_RENDER_PASS_MODEL = new CustomCowUdderModel();

    public RenderCowMixin(ModelBase par1ModelBase, float par2) {
        super(par1ModelBase, par2);
    }

    @Inject(method = "<init>", at = @At(value = "TAIL"))
    public void init(ModelBase par1ModelBase, float par2, CallbackInfo ci) {
        this.setRenderPassModel(COW_RENDER_PASS_MODEL);
    }

    @Inject(method = "getCowTextures", at = @At(value = "HEAD"), cancellable = true)
    public void getTextures(EntityCow cow, CallbackInfoReturnable<ResourceLocation> cir) {
        int subtype = ((EntityAnimalInterface) cow).getType();
        int hungerLevel = cow.getHungerLevel();

        cir.setReturnValue(CowTextures.getCowTexture(subtype, hungerLevel));
        cir.cancel();
    }

    @Inject(method = "shouldRenderPass", at = @At(value = "HEAD"), cancellable = true)
    public void shouldRenderPass(EntityLivingBase par1EntityLiving, int par2, float par3, CallbackInfoReturnable<Integer> cir) {
        int returnValue = -1;
        EntityCow cow = (EntityCow)par1EntityLiving;
        if (par2 == 0 && cow.gotMilk() || cow.getWearingBreedingHarness()){
            this.bindTexture( CowTextures.getOverlayTexture(cow.getHungerLevel(), cow.gotMilk(), cow.getWearingBreedingHarness()) );
            returnValue = 1;
        }
        cir.setReturnValue( returnValue );
        cir.cancel();
    }

    private ResourceLocation getOverlayTexture(EntityCow cow, boolean gotMilk, boolean wearingBreedingHarness) {
//        int hungerLevel = cow.getHungerLevel();
//        CowExtraState extraState = null;
//
//        if (gotMilk && wearingBreedingHarness) extraState = CowExtraState.UDDER_BREEDING_HARNESS;
//        else if (gotMilk) extraState = CowExtraState.UDDER;
//        else if (wearingBreedingHarness) extraState = CowExtraState.BREEDING_HARNESS;
//
//        return AnimalTextureManager.getCowTexture(CowType.DEFAULT.ordinal(), hungerLevel, extraState);

        if (gotMilk && wearingBreedingHarness) return BREEDING_HARNESS_UDDER_TEXTURE;
        if (gotMilk) return COW_UDDER_TEXTURE;
        if (wearingBreedingHarness) return BREEDING_HARNESS_TEXTURE;

        return null;
    }
}
