package btw.community.sockthing.socksmobs.mixins;

import btw.client.render.entity.DireWolfRenderer;
import btw.community.sockthing.socksmobs.utils.WolfTextures;
import btw.entity.mob.DireWolfEntity;
import net.minecraft.src.EntityLivingBase;
import net.minecraft.src.EntityWolf;
import net.minecraft.src.ModelBase;
import net.minecraft.src.RenderLiving;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DireWolfRenderer.class)
public abstract class DireWolfRendererMixin extends RenderLiving {
    public DireWolfRendererMixin(ModelBase par1ModelBase, float par2) {
        super(par1ModelBase, par2);
    }

    @Inject(method = "shouldRenderPass", at = @At(value = "HEAD"), cancellable = true)
    public void shouldRenderPass(EntityLivingBase entity, int iRenderPass, float par3, CallbackInfoReturnable<Integer> cir) {
        DireWolfEntity wolf = (DireWolfEntity) entity;
        if (wolf.getCurrentItemOrArmor(3) != null) {
            this.renderArmor(wolf, iRenderPass);
            cir.setReturnValue(1);
        }
    }

    private boolean renderArmor(DireWolfEntity wolf, int iRenderPass) {
        if (iRenderPass == 1) {
            this.bindTexture(WolfTextures.getWolfArmorTexture(wolf));
            return true;
        }
        return false;
    }
}
