// Made with Blockbench 3.8.4
// Exported for Minecraft version 1.5.2
// Paste this class into your mod and generate all required imports

package net.minecraft.src;

import java.util.List;

import org.lwjgl.opengl.GL11;

public class SocksMobsModelFox extends ModelBase {
	
	private final ModelRenderer body;
	private final ModelRenderer head;
	private  ModelRenderer leg0;
	private  ModelRenderer leg1;
	private  ModelRenderer leg2;
	private  ModelRenderer leg3;
	private final ModelRenderer tail;
	ModelRenderer mainModel;
	
	int foxState = 1; //0 = sneaking, 1 = Normal, 2 = Sprinting, 3 = Sitting

	public SocksMobsModelFox() {
		textureWidth = 48;
		textureHeight = 32;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 16.0F, 0.0F);
		setRotation(body, 1.5708F, 0.0F, 0.0F);
		this.body.setTextureOffset(24, 15).addBox(-3.0F, -3.0F, -3.0F, 6, 11, 6, 0.0F); //UV coords: )

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 16.0F, -3.0F);
		this.head.setTextureOffset(1, 5).addBox(-4.0F, -2.0F, -6.0F, 8, 6, 6, 0.0F); //UV coords: )
		this.head.setTextureOffset(8, 1).addBox(-4.0F, -4.0F, -5.0F, 2, 2, 1, 0.0F); //UV coords: )
		this.head.setTextureOffset(15, 1).addBox(2.0F, -4.0F, -5.0F, 2, 2, 1, 0.0F); //UV coords: )
		this.head.setTextureOffset(6, 18).addBox(-2.0F, 2.0F, -9.0F, 4, 2, 3, 0.0F); //UV coords: )

		leg0 = new ModelRenderer(this);
		leg0.setRotationPoint(-3.0F, 18.0F, 6.0F);
		this.leg0.setTextureOffset(13, 24).addBox(-0.005F, 0.0F, -1.0F, 2, 6, 2, 0.0F); //UV coords: )

		leg1 = new ModelRenderer(this);
		leg1.setRotationPoint(1.0F, 18.0F, 6.0F);
		this.leg1.setTextureOffset(4, 24).addBox(0.005F, 0.0F, -1.0F, 2, 6, 2, 0.0F); //UV coords: )

		leg2 = new ModelRenderer(this);
		leg2.setRotationPoint(-3.0F, 18.0F, -1.0F);
		this.leg2.setTextureOffset(13, 24).addBox(-0.005F, 0.0F, -1.0F, 2, 6, 2, 0.0F); //UV coords: )

		leg3 = new ModelRenderer(this);
		leg3.setRotationPoint(1.0F, 18.0F, -1.0F);
		this.leg3.setTextureOffset(4, 24).addBox(0.005F, 0.0F, -1.0F, 2, 6, 2, 0.0F); //UV coords: )

		tail = new ModelRenderer(this);
		tail.setRotationPoint(0.0F, 16.0F, 7.0F);
		setRotation(tail, 1.5708F, 0.0F, 0.0F);
		this.tail.setTextureOffset(30, 0).addBox(-2.0F, 1.0F, -2.25F, 4, 9, 5, 0.0F); //UV coords: )
	}
	/**
     		* Sets the models various rotation angles then renders the model.
     		*/
	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);

        if (this.isChild)
        {
            float scaleValue = 2.0F;
            GL11.glPushMatrix();
            GL11.glScalef(1.5F / scaleValue, 1.5F / scaleValue, 1.5F / scaleValue);
            GL11.glTranslatef(0.0F, 10.0F * f5, 4.0F * f5);
            this.head.render(f5);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef(1.0F / scaleValue, 1.0F / scaleValue, 1.0F / scaleValue);
            GL11.glTranslatef(0.0F, 24.0F * f5, 0.0F);
            this.body.render(f5);
            this.leg1.render(f5);
            this.leg0.render(f5);
            this.leg3.render(f5);
            this.leg2.render(f5);
            this.tail.render(f5);
            GL11.glPopMatrix();
        }
        else
        {
        	this.head.render(f5);
        	this.body.render(f5);
            this.leg1.render(f5);
            this.leg0.render(f5);
            this.leg3.render(f5);
            this.leg2.render(f5);
            this.tail.render(f5);
        }

	}

	public void setRotation(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	/**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
   	 */
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entity)	
	{
		float legRotationAngle = degToRad(38);
        float legRotaionSpeed;
        
        float tailWaggleAngle = degToRad(30);
        float tailWaggleSpeed = 0.7F;
        
        
        this.tail.rotateAngleY = MathHelper.sin(limbSwing * tailWaggleAngle) * tailWaggleSpeed * limbSwingAmount; //Tail swing when walking
        
		this.head.rotateAngleX = headPitch / (180F / (float)Math.PI);  //Make the head follow
        this.head.rotateAngleY = netHeadYaw / (180F / (float)Math.PI); //where the fox is actually looking, used by AI looking around
        
        
        if (this.foxState == 4) {
        	this.head.rotateAngleX = 0;
        	this.head.rotateAngleY = -0.2618F;
        	
        	this.leg0.rotationPointY =  18;
        	this.leg1.rotationPointY =  18;
        	this.leg2.rotationPointY =  18;
        	this.leg3.rotationPointY =  18;
        	
        	this.tail.rotateAngleX =  degToRad(-90);
        	this.tail.rotateAngleZ = degToRad(180);
        	this.tail.rotateAngleY = degToRad(45);

        }
        
        if (this.foxState != 3) //not sitting //0 = sneaking, 1 = normal, 2 = Sprinting, 3 = Sitting
        	
        {
        	
            this.body.rotateAngleX = degToRad(90);
            
            
            
            if (this.foxState == 2) //is Sprinting
            {
            	
            	legRotaionSpeed = 0.9F;
            	walk(leg0, limbSwing, legRotationAngle, limbSwingAmount, legRotaionSpeed, false);
                walk(leg1, limbSwing, legRotationAngle, limbSwingAmount, legRotaionSpeed, true);
                walk(leg2, limbSwing, legRotationAngle, limbSwingAmount, legRotaionSpeed, true);
                walk(leg3, limbSwing, legRotationAngle, limbSwingAmount, legRotaionSpeed, false);
            }
            else //is not sprinting
            {
            	
            	legRotaionSpeed = 0.6F;
            	walk(leg0, limbSwing, legRotationAngle, limbSwingAmount, legRotaionSpeed, false);
                walk(leg1, limbSwing, legRotationAngle, limbSwingAmount, legRotaionSpeed, true);
                walk(leg2, limbSwing, legRotationAngle, limbSwingAmount, legRotaionSpeed, true);
                walk(leg3, limbSwing, legRotationAngle, limbSwingAmount, legRotaionSpeed, false);
            	
            	
                if (this.foxState == 1) //is normal
                {
                    
                }
                else //is sneaking
                {
                    
                	swing(tail, limbSwing, degToRad(50), limbSwingAmount * 2, 1.4F, false);
                	
                }
            }
        }
        
    }
	
	/**
     * Used for easily adding entity-dependent animations. The second and third float params here are the same second
     * and third as in the setRotationAngles method.
     */
    public void setLivingAnimations(EntityLiving entityLiving, float par2, float par3, float par4)
    {
        SocksMobsEntityFox var5 = (SocksMobsEntityFox)entityLiving;
        //normal values
        this.body.setRotationPoint(0.0F, 16.0F, 0.0F);
		this.body.rotateAngleX = degToRad(90);
		
		this.head.setRotationPoint(0.0F, 16.0F, -3.0F);
		
		this.leg0.setRotationPoint(-3.0F, 18.0F, 6.0F);
		this.leg1.setRotationPoint(1.0F, 18.0F, 6.0F);
		this.leg2.setRotationPoint(-3.0F, 18.0F, -1.0F);
		this.leg3.setRotationPoint(1.0F, 18.0F, -1.0F);

		this.tail.setRotationPoint(0.0F, 16.0F, 7.0F);
		this.tail.rotateAngleX = degToRad(90);
		this.tail.rotateAngleY = 0;

        if (var5.isSneaking())
        {
        	this.head.setRotationPoint(0.0F, 18.0F, -3.0F); //2.0F lower than normal
        	
        	this.body.setRotationPoint(0.0F, 18.0F + 1.0F, 0.0F); 
        	
        	this.leg0.setRotationPoint(-3.0F, 18.0F, 6.0F);
        	this.leg1.setRotationPoint(1.0F, 18.0F, 6.0F);
        	this.leg2.setRotationPoint(-3.0F, 18.0F, -1.0F);
        	this.leg3.setRotationPoint(1.0F, 18.0F, -1.0F);
        	
        	this.tail.setRotationPoint(0.0F, 16.0F + 3.0F, 7.0F);
        	this.tail.rotateAngleX = degToRad(90);
        	this.tail.rotateAngleY = 0;
        	
            this.foxState = 0;
        }
        else if (var5.isSprinting())
        {
            //TODO add animation when sprinting
            this.foxState = 2;
        }
        else if (var5.isSitting())
        {
            this.body.setRotationPoint(0.0F, 16.0F, 0.0F);
    		this.body.rotateAngleX = 0.4363F;
    		this.body.rotateAngleY = 0;
    		
    		this.head.setRotationPoint(0.0F, 10.0F, 0.0F);
    		
    		this.leg0.setRotationPoint(-3.0F, 23.0F, 4.0F);
    		this.leg0.rotateAngleX = -1.5708F;
    		this.leg1.setRotationPoint(1.0F, 23.0F, 4.0F);
    		this.leg1.rotateAngleX = leg0.rotateAngleX;
    		this.leg2.setRotationPoint(-3.0F, 20.0F, -1.0F);
    		this.leg2.rotateAngleX = -0.6981F;
    		this.leg3.setRotationPoint(1.0F, 20.0F, -1.0F);
    		this.leg3.rotateAngleX = leg2.rotateAngleX;
    		this.tail.setRotationPoint(0.0F, 22.0F, 4.0F);
    		this.tail.rotateAngleX = 1.5708F;
    		this.tail.rotateAngleY = 0;
    		
            this.foxState = 3;
            
            if (!var5.canFoxSeeSky())
            {
            	this.body.setRotationPoint(-2.5F, 21.5F, 3.0F);
            	this.body.rotateAngleX = 1.5708F;
            	this.body.rotateAngleY = 1.5708F;
            	
        		this.head.setRotationPoint(-6.0F, 19.75F, 2.0F);
        		this.head.rotateAngleY = -0.2618F;
        		
        		this.tail.setRotationPoint(7.5F, 21.75F, 1.75F);
        		this.tail.rotateAngleX =  degToRad(-90);
        		this.tail.rotateAngleY = degToRad(-45);
        		            	
            	
        		
                this.foxState = 4;
            }
        }
        else 
        {
            this.foxState = 1;
        }
    }
	
	protected void walk(ModelRenderer par1, float limbSwing, float angle, float limbSwingAmount, float speed, boolean invert){
		float invertValue;
		if (invert == true) {
			invertValue = (float)Math.PI;
		}
		else invertValue = 0;
		
		par1.rotateAngleX = MathHelper.cos(limbSwing * angle + invertValue ) * speed * limbSwingAmount;
	}
	
	protected void flap(ModelRenderer par1, float limbSwing, float angle, float limbSwingAmount, float speed, boolean invert){
		float invertValue;
		if (invert == true) {
			invertValue = (float)Math.PI;
		}
		else invertValue = 0;
		
		par1.rotateAngleZ = MathHelper.cos(limbSwing * angle + invertValue ) * speed * limbSwingAmount;
	}
	
	protected void swing(ModelRenderer par1, float limbSwing, float angle, float limbSwingAmount, float speed, boolean invert){
		float invertValue;
		if (invert == true) {
			invertValue = (float)Math.PI;
		}
		else invertValue = 0;
		
		par1.rotateAngleY = MathHelper.cos(limbSwing * angle + invertValue ) * speed * limbSwingAmount;
	}
	
	
	protected float degToRad(float degrees)
    {
        return degrees * (float)Math.PI / 180 ;
    }
	

}