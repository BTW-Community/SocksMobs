package btw.community.sockthing.socksmobs.entities.models;

import net.minecraft.src.*;


public class CustomPigModel extends ModelQuadruped {
    private float headRotation;

    public CustomPigModel() {
        this(0.0f);
    }

    public CustomPigModel(float f) {
        super(6, f);
        textureWidth = 64;
        textureHeight = 64;

        int i = 6;

        this.head = new ModelRenderer(this, 0, 0);
        this.head.addBox(-4.0f, -4.0f, -8.0f, 8, 8, 8, f);
        this.head.setRotationPoint(0.0f, 18 - i, -6.0f);
        this.body = new ModelRenderer(this, 28, 8);
        this.body.addBox(-5.0f, -10.0f, -7.0f, 10, 16, 8, f);
        this.body.setRotationPoint(0.0f, 17 - i, 2.0f);
        this.leg1 = new ModelRenderer(this, 0, 16);
        this.leg1.addBox(-2.0f, 0.0f, -2.0f, 4, i, 4, f);
        this.leg1.setRotationPoint(-3.0f, 24 - i, 7.0f);
        this.leg2 = new ModelRenderer(this, 0, 16);
        this.leg2.addBox(-2.0f, 0.0f, -2.0f, 4, i, 4, f);
        this.leg2.setRotationPoint(3.0f, 24 - i, 7.0f);
        this.leg3 = new ModelRenderer(this, 0, 16);
        this.leg3.addBox(-2.0f, 0.0f, -2.0f, 4, i, 4, f);
        this.leg3.setRotationPoint(-3.0f, 24 - i, -5.0f);
        this.leg4 = new ModelRenderer(this, 0, 16);
        this.leg4.addBox(-2.0f, 0.0f, -2.0f, 4, i, 4, f);
        this.leg4.setRotationPoint(3.0f, 24 - i, -5.0f);

        this.head.setTextureOffset(16, 16).addBox(-2.0f, 0.0f, -9.0f, 4, 3, 1, 0F); //snout
        this.field_78145_g = 4.0f;

        this.head.setTextureOffset(0,32).addBox(-4.0f, -4.0f, -8.0f, 8, 8, 8, 0.25F); // headoverlay
        this.body.setTextureOffset(28,40).addBox(-5.0f, -10.0f, -7.0f, 10, 16, 8, 0.25F); //bodyoverlay

        this.leg1.setTextureOffset(0,48).addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4, 0.25F); //legoverlay
        this.leg2.setTextureOffset(0,48).addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4, 0.25F);
        this.leg3.setTextureOffset(0,48).addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4, 0.25F);
        this.leg4.setTextureOffset(0,48).addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4, 0.25F);
    }

    @Override
    public void setLivingAnimations(EntityLivingBase entity, float par2, float par3, float fPartialTick) {
        super.setLivingAnimations(entity, par2, par3, fPartialTick);
        EntityPig pig = (EntityPig)entity;
        this.head.rotationPointY = !pig.isChild() ? 12.0f + pig.getGrazeHeadVerticalOffset(fPartialTick) * 4.0f : 12.0f + pig.getGrazeHeadVerticalOffset(fPartialTick) * 2.0f;
        this.headRotation = pig.getGrazeHeadRotation(fPartialTick);
    }

    @Override
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity) {
        super.setRotationAngles(par1, par2, par3, par4, par5, par6, entity);
        this.head.rotateAngleX = this.headRotation;
    }
}

