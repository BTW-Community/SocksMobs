// Made with Blockbench 3.9.2
// Exported for Minecraft version 1.5.2
// Paste this class into your mod and generate all required imports

package net.minecraft.src;

import org.lwjgl.opengl.GL11;

public class SocksMobsModelLlama extends ModelBase {
	private final ModelRenderer body;
	private final ModelRenderer body_r1;
	private final ModelRenderer head;
	private final ModelRenderer chest;
	private final ModelRenderer left_chest_r1;
	private final ModelRenderer right_chest_r1;
	private final ModelRenderer leg1;
	private final ModelRenderer leg2;
	private final ModelRenderer leg3;
	private final ModelRenderer leg4;
	
	private float m_fHeadRotation;
	

	public SocksMobsModelLlama() {
		textureWidth = 128;
		textureHeight = 64;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		body_r1 = new ModelRenderer(this);
		body_r1.setRotationPoint(0.0F, -16.0F, 0.0F);
		body.addChild(body_r1);
		setRotation(body_r1, 1.5708F, 0.0F, 0.0F);
		this.body_r1.setTextureOffset(29, 0).addBox(-6.0F, -8.0F, -6.0F, 12,  17,  10,  0.0F);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 8.0F, -8.0F);
		this.head.setTextureOffset(0, 14).addBox(-4.0F, -16.0F, -4.0F, 8, 18, 6, 0.0F);
		this.head.setTextureOffset(17, 0).addBox(1.0F, -19.0F, -2.0F, 3, 3, 2, 0.0F);
		this.head.setTextureOffset(17, 0).addBox(-4.0F, -19.0F, -2.0F, 3, 3, 2, 0.0F);
		this.head.setTextureOffset(5, 5).addBox(-2.0F, -14.0F, -8.0F, 4, 4, 4, 0.0F);

		chest = new ModelRenderer(this);
		chest.setRotationPoint(0.0F, 8.0F, 2.0F);
		
		left_chest_r1 = new ModelRenderer(this);
		left_chest_r1.setRotationPoint(6.0F, -3.0F, -2.0F);
		chest.addChild(left_chest_r1);
		setRotation(left_chest_r1, 0.0F, -1.5708F, 0.0F);
		this.left_chest_r1.setTextureOffset(45, 28).addBox(-2.0F, 0.0F, -2.5F, 8, 7, 3, 0.0F);

		right_chest_r1 = new ModelRenderer(this);
		right_chest_r1.setRotationPoint(-6.0F, -3.0F, -2.0F);
		chest.addChild(right_chest_r1);
		setRotation(right_chest_r1, 0.0F, 1.5708F, 0.0F);
		this.right_chest_r1.setTextureOffset(45, 28).addBox(-6.0F, 0.0F, -2.5F, 8, 7, 3, 0.0F);

		leg1 = new ModelRenderer(this);
		leg1.setRotationPoint(0.0F, 12.0F, 6.0F);
		this.leg1.setTextureOffset(29, 29).addBox(-5.0F, -2.0F, -2.0F, 4, 14, 4, 0.0F);

		leg2 = new ModelRenderer(this);
		leg2.setRotationPoint(0.0F, 12.0F, 6.0F);
		this.leg2.setTextureOffset(29, 29).addBox(1.0F, -2.0F, -2.0F, 4, 14, 4, 0.0F);

		leg3 = new ModelRenderer(this);
		leg3.setRotationPoint(0.0F, 12.0F, -5.0F);
		this.leg3.setTextureOffset(29, 29).addBox(-5.0F, -2.0F, -2.0F, 4, 14, 4, 0.0F);

		leg4 = new ModelRenderer(this);
		leg4.setRotationPoint(0.0F, 12.0F, -5.0F);
		this.leg4.setTextureOffset(29, 29).addBox(1.0F, -2.0F, -2.0F, 4, 14, 4, 0.0F);
	}
	/**
    * Sets the models various rotation angles then renders the model.
    */
	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {

		this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		SocksMobsEntityLlama Llama = (SocksMobsEntityLlama)entity;
		
		
		if (this.isChild)
		{
		    float var8 = 2.0F;
		    GL11.glPushMatrix();
		    GL11.glTranslatef(0.0F, 8.0F * f5, 4.0F * f5);
		    this.head.render(f5);
		    GL11.glPopMatrix();
		    GL11.glPushMatrix();
		    GL11.glScalef(1.0F / var8, 1.0F / var8, 1.0F / var8);
		    GL11.glTranslatef(0.0F, 24.0F * f5, 0.0F);
		    this.body.render(f5);
		    this.leg1.render(f5);
		    this.leg2.render(f5);
		    this.leg3.render(f5);
		    this.leg4.render(f5);
		    GL11.glPopMatrix();
		}
		if(Llama.getChest()) {
			this.chest.render(f5);
			
			this.body.render(f5);
		    this.head.render(f5);
		    this.leg1.render(f5);
		    this.leg2.render(f5);
		    this.leg3.render(f5);
		    this.leg4.render(f5);
		}
		else
		{
		    this.body.render(f5);
		    this.head.render(f5);
		    this.leg1.render(f5);
		    this.leg2.render(f5);
		    this.leg3.render(f5);
		    this.leg4.render(f5);
		}

	}

	public void setRotation(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
	
	/**
     * Used for easily adding entity-dependent animations. The second and third float params here are the same second
     * and third as in the setRotationAngles method.
     */
    public void setLivingAnimations(EntityLiving var1, float var2, float var3, float var4)
    {
        super.setLivingAnimations(var1, var2, var3, var4);
        SocksMobsEntityLlama var5 = (SocksMobsEntityLlama)var1;

        if (!var5.isChild())
        {
            this.head.rotationPointY = 5.0F + var5.GetGrazeHeadVerticalOffset(var4) * 4.0F;
        }
        else
        {
            this.head.rotationPointY = 5.0F + var5.GetGrazeHeadVerticalOffset(var4) * 2.0F;
        }

        this.m_fHeadRotation = var5.GetGrazeHeadRotation(var4);
    }

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entity)
    {
    	//super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entity);
               
        this.head.rotateAngleY = netHeadYaw / (180F / (float)Math.PI);
        this.head.rotateAngleX = headPitch / (180F / (float)Math.PI);
        
        walk(leg1, limbSwing, degToRad(38), limbSwingAmount, 1.4F, true);
        walk(leg2, limbSwing, degToRad(38), limbSwingAmount, 1.4F, true);
        walk(leg3, limbSwing, degToRad(38), limbSwingAmount, 1.4F, false);
        walk(leg4, limbSwing, degToRad(38), limbSwingAmount, 1.4F, false);
        
        this.head.rotateAngleX = this.m_fHeadRotation;
    }
    
    protected float degToRad(float degrees)
    {
        return degrees * (float)Math.PI / 180 ;
    }
    
    protected void walk(ModelRenderer par1, float limbSwing, float angle, float limbSwingAmount, float speed,  boolean invert){
		float invertValue;
		if (invert == true) {
			invertValue = (float)Math.PI;
		}
		else invertValue = 0;
		
		par1.rotateAngleX = MathHelper.cos(limbSwing * angle + invertValue ) * speed * limbSwingAmount;
	}

}