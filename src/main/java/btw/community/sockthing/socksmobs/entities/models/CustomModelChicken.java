/*
 * Decompiled with CFR 0.2.1 (FabricMC 53fa44c9).
 */
package btw.community.sockthing.socksmobs.entities.models;

import net.minecraft.src.*;
import org.lwjgl.opengl.GL11;

public class CustomModelChicken extends ModelChicken {
    public ModelRenderer head;
    public ModelRenderer hawk;
    public ModelRenderer body;
    public ModelRenderer tail;
    public ModelRenderer tail_cube;
    public ModelRenderer rightLeg;
    public ModelRenderer leftLeg;
    public ModelRenderer rightWing;
    public ModelRenderer leftWing;
    public ModelRenderer bill;
    public ModelRenderer chin;
    private float headRotation;

    public CustomModelChicken() {
        int var1 = 16;
        this.head = new ModelRenderer(this, 0, 0);
        this.head.addBox(-2.0f, -6.0f, -2.0f, 4, 6, 3, 0.0f);
        this.head.setRotationPoint(0.0f, -1 + var1, -4.0f);

        hawk = new ModelRenderer(this);
        hawk.setRotationPoint(0.0F, 0.0F, 0.0F);
        head.addChild(hawk);
        this.hawk.setTextureOffset(18, 19).addBox(0.0F, -8.0F, -2.5F, 0, 3, 4, 0.0F);

        this.bill = new ModelRenderer(this, 14, 0);
        this.bill.addBox(-2.0f, -4.0f, -4.0f, 4, 2, 2, 0.0f);
        this.bill.setRotationPoint(0.0f, -1 + var1, -4.0f);
        this.chin = new ModelRenderer(this, 14, 4);
        this.chin.addBox(-1.0f, -2.0f, -3.0f, 2, 2, 2, 0.0f);
        this.chin.setRotationPoint(0.0f, -1 + var1, -4.0f);
        this.body = new ModelRenderer(this, 0, 9);
        this.body.addBox(-3.0f, -4.0f, -3.0f, 6, 8, 6, 0.0f);
        this.body.setRotationPoint(0.0f, var1, 0.0f);

        tail = new ModelRenderer(this);
        tail.setRotationPoint(0.0F, 0.0F, 0.0F);
        body.addChild(tail);

        tail_cube = new ModelRenderer(this);
        tail_cube.setRotationPoint(0.0F, 4.0F, 3.0F);
        tail.addChild(tail_cube);
        setRotation(tail_cube, -1.5708F, 0.0F, 0.0F);
        this.tail_cube.setTextureOffset(0, 15).addBox(0.0F, -5.0F, -3.0F, 0, 9, 8, 0.0F);

        this.rightLeg = new ModelRenderer(this, 26, 0);
        this.rightLeg.addBox(-1.0f, 0.0f, -3.0f, 3, 5, 3);
        this.rightLeg.setRotationPoint(-2.0f, 3 + var1, 1.0f);
        this.leftLeg = new ModelRenderer(this, 26, 0);
        this.leftLeg.addBox(-1.0f, 0.0f, -3.0f, 3, 5, 3);
        this.leftLeg.setRotationPoint(1.0f, 3 + var1, 1.0f);
        this.rightWing = new ModelRenderer(this, 24, 13);
        this.rightWing.addBox(0.0f, 0.0f, -3.0f, 1, 4, 6);
        this.rightWing.setRotationPoint(-4.0f, -3 + var1, 0.0f);
        this.leftWing = new ModelRenderer(this, 24, 13);
        this.leftWing.addBox(-1.0f, 0.0f, -3.0f, 1, 4, 6);
        this.leftWing.setRotationPoint(4.0f, -3 + var1, 0.0f);
    }

    @Override
    public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
        this.setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
        if (this.isChild) {
            float var8 = 2.0f;
            GL11.glPushMatrix();
            GL11.glTranslatef(0.0f, 5.0f * par7, 2.0f * par7);
            this.head.render(par7);
            this.bill.render(par7);
            this.chin.render(par7);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef(1.0f / var8, 1.0f / var8, 1.0f / var8);
            GL11.glTranslatef(0.0f, 24.0f * par7, 0.0f);
            this.body.render(par7);
            this.rightLeg.render(par7);
            this.leftLeg.render(par7);
            this.rightWing.render(par7);
            this.leftWing.render(par7);
            GL11.glPopMatrix();
        } else {
            this.head.render(par7);
            this.bill.render(par7);
            this.chin.render(par7);
            this.body.render(par7);
            this.rightLeg.render(par7);
            this.leftLeg.render(par7);
            this.rightWing.render(par7);
            this.leftWing.render(par7);
        }
    }

    @Override
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        this.head.rotateAngleX = par5 / 57.295776f;
        this.head.rotateAngleY = par4 / 57.295776f;
        this.bill.rotateAngleX = this.head.rotateAngleX;
        this.bill.rotateAngleY = this.head.rotateAngleY;
        this.chin.rotateAngleX = this.head.rotateAngleX;
        this.chin.rotateAngleY = this.head.rotateAngleY;
        this.body.rotateAngleX = 1.5707964f;
        this.rightLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
        this.leftLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662f + (float)Math.PI) * 1.4f * par2;
        this.rightWing.rotateAngleZ = par3;
        this.leftWing.rotateAngleZ = -par3;
        this.bill.rotateAngleX = this.chin.rotateAngleX = this.headRotation;
        this.head.rotateAngleX = this.chin.rotateAngleX;
    }

    @Override
    public void setLivingAnimations(EntityLivingBase entity, float par2, float par3, float fPartialTick) {
        super.setLivingAnimations(entity, par2, par3, fPartialTick);
        EntityChicken chicken = (EntityChicken)entity;
        this.head.rotationPointY = !chicken.isChild() ? 15.0f + chicken.getGrazeHeadVerticalOffset(fPartialTick) * 3.0f : 15.0f + chicken.getGrazeHeadVerticalOffset(fPartialTick) * 1.5f;
        this.bill.rotationPointY = this.chin.rotationPointY = this.head.rotationPointY;
        this.headRotation = chicken.getGrazeHeadRotation(fPartialTick);
    }

    public void setRotation(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}

