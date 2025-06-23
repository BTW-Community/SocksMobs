package btw.community.sockthing.socksmobs.entities.models;

import btw.entity.mob.KickingAnimal;
import net.minecraft.src.*;

public class CustomModelCow extends ModelQuadruped {
    private float headRotation;
    private final ModelRenderer snout;

    public CustomModelCow() {
        super(12, 0.0f);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.addBox(-4.0f, -4.0f, -6.0f, 8, 8, 6, 0.0f);
        this.head.setRotationPoint(0.0f, 4.0f, -8.0f);
        this.head.setTextureOffset(22, 0).addBox(-5.0f, -5.0f, -4.0f, 1, 3, 1, 0.0f);
        this.head.setTextureOffset(22, 0).addBox(4.0f, -5.0f, -4.0f, 1, 3, 1, 0.0f);

        snout = new ModelRenderer(this);
        snout.setRotationPoint(0.0F, 0.0F, 0.0F);
        head.addChild(snout);
        this.snout.setTextureOffset(28, 0).addBox(-3.0F, 1.0F, -7.0F, 6, 3, 1, 0.0F);

        this.body = new ModelRenderer(this, 18, 4);
        this.body.addBox(-6.0f, -10.0f, -7.0f, 12, 18, 10, 0.0f);
        this.body.setRotationPoint(0.0f, 5.0f, 2.0f);
        this.body.setTextureOffset(52, 0).addBox(-2.0f, 2.0f, -8.0f, 4, 6, 1);
        this.leg1.rotationPointX -= 1.0f;
        this.leg2.rotationPointX += 1.0f;
        this.leg1.rotationPointZ += 0.0f;
        this.leg2.rotationPointZ += 0.0f;
        this.leg3.rotationPointX -= 1.0f;
        this.leg4.rotationPointX += 1.0f;
        this.leg3.rotationPointZ -= 1.0f;
        this.leg4.rotationPointZ -= 1.0f;
        this.field_78151_h += 2.0f;
    }

    @Override
    public void setLivingAnimations(EntityLivingBase entity, float par2, float par3, float fPartialTick) {
        super.setLivingAnimations(entity, par2, par3, fPartialTick);
        EntityCow cow = (EntityCow)entity;
        this.head.rotationPointY = 4.0f + cow.getGrazeHeadVerticalOffset(fPartialTick) * 4.0f;
        this.headRotation = cow.getGrazeHeadRotation(fPartialTick);
    }

    @Override
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity) {
        super.setRotationAngles(par1, par2, par3, par4, par5, par6, entity);
        this.adjustRotationAnglesForKickAttack(entity);
        this.head.rotateAngleX = this.headRotation;
    }

    private void adjustRotationAnglesForKickAttack(Entity entity) {
        int iAttackProgressCounter;
        KickingAnimal cow = (KickingAnimal)entity;
        if (cow != null && (iAttackProgressCounter = cow.kickAttackInProgressCounter) > 0) {
            float fRotationFactor = 2.0f;
            if (cow.kickAttackLegUsed == 0) {
                this.leg1.rotateAngleX = MathHelper.cos((float)Math.PI * fRotationFactor) * 1.4f;
            } else {
                this.leg2.rotateAngleX = MathHelper.cos((float)Math.PI * fRotationFactor) * 1.4f;
            }
        }
    }
}
