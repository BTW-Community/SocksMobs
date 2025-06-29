package btw.community.sockthing.socksmobs.mixins;

import btw.community.sockthing.socksmobs.entities.models.CustomPigModel;
import btw.community.sockthing.socksmobs.enums.WolfState;
import btw.community.sockthing.socksmobs.enums.WolfType;
import btw.community.sockthing.socksmobs.interfaces.EntityAnimalInterface;
import btw.community.sockthing.socksmobs.utils.AnimalTextureManager;
import btw.community.sockthing.socksmobs.utils.WolfTextures;
import btw.community.sockthing.socksmobs.utils.WolfUtils;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RenderWolf.class)
public abstract class RenderWolfMixin extends RenderLiving {

    private final ModelBase RENDER_PASS_MODEL = new ModelWolf();

    public RenderWolfMixin(ModelBase par1ModelBase, float par2) {
        super(par1ModelBase, par2);
    }

    @Inject(method = "<init>", at = @At(value = "TAIL"))
    public void init(ModelBase par1ModelBase, ModelBase par2ModelBase, float par3, CallbackInfo ci){
        this.setRenderPassModel(RENDER_PASS_MODEL);
    }

    @Inject(method = "func_110914_a", at = @At(value = "HEAD"), cancellable = true)
    public void getWolfTextures(EntityWolf wolf, CallbackInfoReturnable<ResourceLocation> cir) {
        int subtype = ((EntityAnimalInterface) wolf).getType();

        cir.setReturnValue(WolfTextures.getWolfTexture(subtype, wolf.isTamed(), wolf.isStarving(), wolf.isAngry(), wolf.hasAttackTarget()));
        cir.cancel();
    }

    @Inject(method = "shouldRenderPass", at = @At(value = "HEAD"), cancellable = true)
    public void shouldRenderPass(EntityLivingBase entity, int iRenderPass, float par3, CallbackInfoReturnable<Integer> cir) {
        EntityWolf wolf = (EntityWolf) entity;
        if (wolf.getCurrentItemOrArmor(3) != null) {
            this.renderArmor(wolf, iRenderPass);
            cir.setReturnValue(1);
        }
    }

    private boolean renderArmor(EntityWolf wolf, int iRenderPass) {
        if (iRenderPass == 1) {
            this.bindTexture(WolfTextures.getWolfArmorTexture(wolf));
            return true;
        }
        return false;
    }
}
