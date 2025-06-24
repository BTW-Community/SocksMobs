package btw.community.sockthing.socksmobs.mixins;

import btw.community.sockthing.socksmobs.entities.models.CustomModelCow;
import btw.community.sockthing.socksmobs.entities.models.CustomModelCowUdder;
import btw.community.sockthing.socksmobs.entities.models.ModelColdCow;
import btw.community.sockthing.socksmobs.entities.models.ModelWarmCow;
import btw.community.sockthing.socksmobs.enums.AnimalState;
import btw.community.sockthing.socksmobs.enums.CowType;
import btw.community.sockthing.socksmobs.interfaces.EntityAnimalInterface;
import btw.community.sockthing.socksmobs.utils.AnimalTextureManager;
import btw.entity.model.CowUdderModel;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RenderCow.class)
public abstract class RenderCowMixin extends RenderLiving{
    private static final ResourceLocation COW_UDDER_TEXTURES = new ResourceLocation("socksmobs:textures/entity/cow/cow_udder.png");
    private static final ResourceLocation COW_BREEDING_HARNESS = new ResourceLocation("socksmobs:textures/entity/cow/breeding_harness.png");
    private static ResourceLocation NORMAL = null;
    private static ResourceLocation FAMISHED = null;
    private static ResourceLocation STARVING = null;
    private static ResourceLocation BREEDING_HARNESS_NORMAL = null;
    private static ResourceLocation BREEDING_HARNESS_FAMISHED = null;
    private static ResourceLocation BREEDING_HARNESS_STARVING = null;

    private CustomModelCowUdder modelUdder = new CustomModelCowUdder();

    public RenderCowMixin(ModelBase par1ModelBase, float par2) {
        super(par1ModelBase, par2);
    }

    @Inject(method = "<init>", at = @At(value = "TAIL"))
    public void init(ModelBase par1ModelBase, float par2, CallbackInfo ci){
        this.setRenderPassModel(this.modelUdder);

        NORMAL = AnimalTextureManager.getAnimalTexture(CowType.class, CowType.DEFAULT.ordinal(), AnimalState.BREEDING_HARNESS);
        FAMISHED = AnimalTextureManager.getAnimalTexture(CowType.class, CowType.DEFAULT.ordinal(), AnimalState.BREEDING_HARNESS_FAMISHED);
        STARVING = AnimalTextureManager.getAnimalTexture(CowType.class, CowType.DEFAULT.ordinal(), AnimalState.BREEDING_HARNESS_STARVING);

        BREEDING_HARNESS_NORMAL = AnimalTextureManager.getAnimalTexture(CowType.class, CowType.DEFAULT.ordinal(), AnimalState.BREEDING_HARNESS);
        BREEDING_HARNESS_FAMISHED = AnimalTextureManager.getAnimalTexture(CowType.class, CowType.DEFAULT.ordinal(), AnimalState.BREEDING_HARNESS_FAMISHED);
        BREEDING_HARNESS_STARVING = AnimalTextureManager.getAnimalTexture(CowType.class, CowType.DEFAULT.ordinal(), AnimalState.BREEDING_HARNESS_STARVING);
    }

    @Override
    public int shouldRenderPass(EntityLivingBase par1EntityLiving, int par2, float par3) {
        int returnValue = -1;

        if (par2 == 0 ) {
            if (((EntityCow) par1EntityLiving).gotMilk()) {
                this.bindTexture(COW_UDDER_TEXTURES);
                returnValue = 1;
            }

//            if (((EntityCow)par1EntityLiving).getWearingBreedingHarness()) {
                this.bindTexture(COW_BREEDING_HARNESS);
                returnValue = 1;
//            }
        }

        return returnValue;
    }

    @Override
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        EntityCow cow = (EntityCow)par1Entity;
        if (((EntityAnimalInterface) cow).getType() == CowType.COLD.ordinal()){
            this.mainModel = new ModelColdCow();
        }
        else if (((EntityAnimalInterface) cow).getType() == CowType.WARM.ordinal()){
            this.mainModel = new ModelWarmCow();
        }
        else this.mainModel = new CustomModelCow();

        super.doRender(par1Entity, par2, par4, par6, par8, par9);
    }

    @Inject(method = "getCowTextures", at = @At(value = "HEAD"), cancellable = true)
    public void getCowTextures(EntityCow cow, CallbackInfoReturnable<ResourceLocation> cir) {
        int subtype = ((EntityAnimalInterface) cow).getType();

        if (cow.getWearingBreedingHarness()) {
            int iHungerLevel = cow.getHungerLevel();
            if (iHungerLevel == 1) {
                cir.setReturnValue( BREEDING_HARNESS_FAMISHED );
                cir.cancel();
            }
            else if (iHungerLevel == 2) {
                cir.setReturnValue( BREEDING_HARNESS_STARVING );
                cir.cancel();
            }
            else {
                cir.setReturnValue( BREEDING_HARNESS_NORMAL );
                cir.cancel();
            }
        }
        else {
            int iHungerLevel = cow.getHungerLevel();
            if (iHungerLevel == 1) {
                cir.setReturnValue( FAMISHED );
                cir.cancel();
            }
            else if (iHungerLevel == 2) {
                cir.setReturnValue( STARVING );
                cir.cancel();
            }
            else {
                cir.setReturnValue( NORMAL );
                cir.cancel();
            }
        }

    }
}
