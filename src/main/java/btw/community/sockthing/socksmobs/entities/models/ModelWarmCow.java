package btw.community.sockthing.socksmobs.entities.models;

import btw.entity.mob.KickingAnimal;
import net.minecraft.src.*;

public class ModelWarmCow extends ModelQuadruped {
    private float headRotation;
    private final ModelRenderer snout;
    private final ModelRenderer horns;

    public ModelWarmCow() {
        super(12, 0.0f);
        this.textureWidth = 64;
        this.textureHeight = 64;

        this.head = new ModelRenderer(this, 0, 0);
        this.head.addBox(-4.0f, -4.0f, -6.0f, 8, 8, 6, 0.0f);
        this.head.setRotationPoint(0.0f, 4.0f, -8.0f);

        snout = new ModelRenderer(this);
        snout.setRotationPoint(0.0F, 20.0F, 2.0F);
        head.addChild(snout);
        this.snout.setTextureOffset(28, 0).addBox(-3.0F, -19.0F, -9.0F, 6, 3, 1, 0.0F);

        horns = new ModelRenderer(this);
        horns.setRotationPoint(5.0F, -2.0F, -4.0F);
        head.addChild(horns);

        this.body = new ModelRenderer(this, 18, 4);
        this.body.addBox(-6.0f, -10.0f, -7.0f, 12, 18, 10, 0.0f);
        this.body.setRotationPoint(0.0f, 5.0f, 2.0f);
        this.body.setTextureOffset(52, 0).addBox(-2.0f, 2.0f, -8.0f, 4, 6, 1); //udder

        leg1 = new ModelRenderer(this);
        leg1.setRotationPoint(-3.0F, 12.0F, 7.0F);
        this.leg1.setTextureOffset(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);

        leg2 = new ModelRenderer(this);
        leg2.setRotationPoint(3.0F, 12.0F, 7.0F);
        this.leg2.setTextureOffset(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);

        leg3 = new ModelRenderer(this);
        leg3.setRotationPoint(-3.0F, 12.0F, -6.0F);
        this.leg3.setTextureOffset(0, 16).addBox(-2.0F, 0.0F, -1.0F, 4, 12, 4, 0.0F);

        leg4 = new ModelRenderer(this);
        leg4.setRotationPoint(3.0F, 12.0F, -6.0F);
        this.leg4.setTextureOffset(0, 16).addBox(-2.0F, 0.0F, -1.0F, 4, 12, 4, 0.0F);

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
    public void render(Entity entity, float f, float g, float h, float i, float j, float k) {
        if (!this.isChild) {
            this.horns.setTextureOffset(52, 10).addBox(-1.0F, -1.0F, -1.0F, 4, 2, 2, 0.0F);
            this.horns.setTextureOffset(52, 10).addBox(-13.0F, -1.0F, -1.0F, 4, 2, 2, 0.0F);
            this.horns.setTextureOffset(56, 6).addBox(1.0F, -3.0F, -1.0F, 2, 2, 2, 0.0F);
            this.horns.setTextureOffset(56, 6).addBox(-13.0F, -3.0F, -1.0F, 2, 2, 2, 0.0F);
        }
        super.render(entity, f, g, h, i, j, k);
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

    /**
     *	Sets the rotation of a ModelRenderer. Only called if the ModelRenderer has a rotation
     */
    public void setRotation(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
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
