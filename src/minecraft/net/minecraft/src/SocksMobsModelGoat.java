// Made with Blockbench 3.8.4
// Exported for Minecraft version 1.5.2
// Paste this class into your mod and generate all required imports

package net.minecraft.src;

import org.lwjgl.opengl.GL11;

public class SocksMobsModelGoat extends ModelBase {
	private final ModelRenderer left_back_leg;
	private final ModelRenderer right_back_leg;
	private final ModelRenderer right_front_leg;
	private final ModelRenderer left_front_leg;
	public final ModelRenderer body;
	private final ModelRenderer horns;
	public final ModelRenderer head;
	private final ModelRenderer head_r1;
	
	
	private float m_fHeadRotation;
	
	public SocksMobsModelGoat() {
		textureWidth = 64;
		textureHeight = 64;

		left_back_leg = new ModelRenderer(this);
		left_back_leg.setRotationPoint(1.0F, 14.0F, 4.0F);
		this.left_back_leg.setTextureOffset(36, 29).addBox(0.0F, 4.0F, 0.0F, 3, 6, 3, 0.0F);

		right_back_leg = new ModelRenderer(this);
		right_back_leg.setRotationPoint(-3.0F, 14.0F, 4.0F);
		this.right_back_leg.setTextureOffset(49, 29).addBox(0.0F, 4.0F, 0.0F, 3, 6, 3, 0.0F);

		right_front_leg = new ModelRenderer(this);
		right_front_leg.setRotationPoint(-3.0F, 14.0F, -6.0F);
		this.right_front_leg.setTextureOffset(49, 2).addBox(0.0F, 0.0F, 0.0F, 3, 10, 3, 0.0F);

		left_front_leg = new ModelRenderer(this);
		left_front_leg.setRotationPoint(1.0F, 14.0F, -6.0F);
		this.left_front_leg.setTextureOffset(35, 2).addBox(0.0F, 0.0F, 0.0F, 3, 10, 3, 0.0F);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 24.0F, 0.0F);
		this.body.setTextureOffset(1, 1).addBox(-4.0F, -17.0F, -7.0F, 9, 11, 16, 0.0F);
		this.body.setTextureOffset(0, 28).addBox(-5.0F, -18.0F, -8.0F, 11, 14, 11, 0.0F);
		this.body.setTextureOffset(47, 39).addBox(-1.5F, -6.0F, 1.5F, 4, 1, 4, 0.0F);

		horns = new ModelRenderer(this);
		horns.setRotationPoint(0.5F, 7.0F, -7.0F);
		this.horns.setTextureOffset(12, 55).addBox(-2.4F, -9.0F, -3.0F, 2, 7, 2, 0.0F);
		this.horns.setTextureOffset(12, 55).addBox(0.4F, -9.0F, -3.0F, 2, 7, 2, 0.0F);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 7.0F, -7.0F);
		this.head.setTextureOffset(23, 52).addBox(0.5F, 4.0F, -7.0F, 0, 7, 5, 0.0F);
		this.head.setTextureOffset(2, 61).addBox(3.0F, -3.0F, -3.0F, 3, 2, 1, 0.0F);
		this.head.setTextureOffset(2, 61).addBox(-5.0F, -3.0F, -3.0F, 3, 2, 1, 0.0F);

		head_r1 = new ModelRenderer(this);
		head_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.addChild(head_r1);
		setRotation(head_r1, 0.7854F, 0.0F, 0.0F);
		this.head_r1.setTextureOffset(34, 46).addBox(-2.0F, -5.0F, -9.0F, 5, 7, 10, 0.0F);
	}
	/**
     		* Sets the models various rotation angles then renders the model.
     		*/
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		
		this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		

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
		    this.left_back_leg.render(f5);
		    this.right_back_leg.render(f5);
		    this.right_front_leg.render(f5);
		    this.left_front_leg.render(f5);
		    GL11.glPopMatrix();
		}
		else
		{
		    this.head.render(f5);
		    this.horns.render(f5);
		    this.body.render(f5);
		    this.left_back_leg.render(f5);
		    this.right_back_leg.render(f5);
		    this.right_front_leg.render(f5);
		    this.left_front_leg.render(f5);
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
        //super.setLivingAnimations(var1, var2, var3, var4);
        SocksMobsEntityGoat var5 = (SocksMobsEntityGoat)var1;

        if (var5.isChild()) {
        	this.head.rotationPointY = 6.0F + var5.GetGrazeHeadVerticalOffset(var4) * 3.0F; //TODO -7 is too high
        } else {
        	this.head.rotationPointY = 7.0F + var5.GetGrazeHeadVerticalOffset(var4) * 6.0F; //TODO - 7 is too high
        }
        this.horns.rotationPointY = this.head.rotationPointY;
        this.m_fHeadRotation = var5.GetGrazeHeadRotation(var4);
    }

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    //movements here
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entity)
    {
    	//super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entity);
        this.AdjustRotationAnglesForKickAttack(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entity);
       
        this.head.rotateAngleY = netHeadYaw / (180F / (float)Math.PI);
        this.head.rotateAngleX = headPitch / (180F / (float)Math.PI);
        
        this.horns.rotateAngleY = this.head.rotateAngleY;
        this.horns.rotateAngleX = this.head.rotateAngleX;
        
        walk(right_front_leg, limbSwing, degToRad(38), limbSwingAmount, 1.4F, true);
        walk(left_front_leg, limbSwing, degToRad(38), limbSwingAmount, 1.4F, false);
        walk(right_back_leg, limbSwing, degToRad(38), limbSwingAmount, 1.4F, true);
        walk(left_back_leg, limbSwing, degToRad(38), limbSwingAmount, 1.4F, false);
        
        
        
        this.head.rotateAngleX = this.m_fHeadRotation;
        this.horns.rotateAngleX = this.head.rotateAngleX;
    }
    
    private void AdjustRotationAnglesForKickAttack(float var1, float var2, float var3, float var4, float var5, float var6, Entity var7)
    {
    	SocksMobsEntityGoat var8 = (SocksMobsEntityGoat)var7;

        if (var8 != null)
        {
            int var9 = var8.m_iKickAttackInProgressCounter;

            if (var9 > 0)
            {
                float var10 = 2.0F;

                if (var8.m_iKickAttackLegUsed == 0)
                {
                    this.head.rotateAngleX = MathHelper.cos((float)Math.PI * var10) * 1.4F;
                    
                }
                else
                {
                    this.head.rotateAngleX = MathHelper.cos((float)Math.PI * var10) * 1.4F;
                }
            }
        }
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