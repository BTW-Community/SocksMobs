package net.minecraft.src;

public class SocksMobsModelGolem extends ModelBase{

	private final ModelRenderer backpack;
	private final ModelRenderer cube_r1;
	private final ModelRenderer head;
	private final ModelRenderer body;
	private ModelRenderer left_arm;
	ModelRenderer right_arm;
	private ModelRenderer left_leg;
	private ModelRenderer right_leg;
	ModelRenderer mainModel;

	public SocksMobsModelGolem() {
		textureWidth = 128;
		textureHeight = 64;

		backpack = new ModelRenderer(this);
		backpack.setRotationPoint(0.0F, 24.0F, 0.0F);
		this.backpack.setTextureOffset(64, 11).addBox(-3.5F, -15.0F, 2.0F, 7, 7, 4, 0.0F);
		this.backpack.setTextureOffset(64, 22).addBox(-3.5F, -12.0F, 6.0F, 7, 4, 1, 0.0F);

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(0.0F, -17.0F, 4.0F);
		backpack.addChild(cube_r1);
		setRotation(cube_r1, -0.7854F, 0.0F, 0.0F);
		this.cube_r1.setTextureOffset(82, 18).addBox(-3.5F, -2.0F, -2.0F, 7, 4, 4, -0.1F);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 9.0F, -1.5F);
		this.head.setTextureOffset(0, 11).addBox(-2.5F, -6.0F, -2.5F, 5, 6, 5, 0.0F);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 12.0F, -0.5F);
		this.body.setTextureOffset(64, 0).addBox(-4.5F, -3.0F, -2.5F, 9, 6, 5, 0.25F);
		this.body.setTextureOffset(0, 0).addBox(-4.5F, -3.0F, -2.5F, 9, 6, 5, 0.0F);
		this.body.setTextureOffset(20, 11).addBox(-2.5F, 3.0F, -2.0F, 5, 3, 4, 0.0F);

		left_arm = new ModelRenderer(this);
		left_arm.setRotationPoint(5.5F, 10.5F, -0.5F);
		this.left_arm.setTextureOffset(0, 22).addBox(-1.0F, -1.5F, -1.5F, 2, 14, 3, 0.0F);

		right_arm = new ModelRenderer(this);
		right_arm.setRotationPoint(-5.5F, 10.5F, -0.5F);
		this.right_arm.setTextureOffset(17, 19).addBox(-1.0F, -1.5F, -1.5F, 2, 14, 3, 0.0F);

		left_leg = new ModelRenderer(this);
		left_leg.setRotationPoint(2.5F, 17.0F, -0.5F);
		this.left_leg.setTextureOffset(27, 18).addBox(-1.5F, -1.0F, -1.5F, 3, 8, 3, 0.0F);

		right_leg = new ModelRenderer(this);
		right_leg.setRotationPoint(-2.5F, 17.0F, -0.5F);
		this.right_leg.setTextureOffset(28, 0).addBox(-1.5F, -1.0F, -1.5F, 3, 8, 3, 0.0F);
	}
	/**
    * Sets the models various rotation angles then renders the model.
    */
	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		backpack.render(f5);
		head.render(f5);
		body.render(f5);
		left_arm.render(f5);
		right_arm.render(f5);
		left_leg.render(f5);
		right_leg.render(f5);
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
		float legRotaionSpeed = 0.9F;
 
		
		walk(left_leg, limbSwing, legRotationAngle, limbSwingAmount, legRotaionSpeed, false);
        walk(right_leg, limbSwing, legRotationAngle, limbSwingAmount, legRotaionSpeed, true);
        
        walk(left_arm, limbSwing, legRotationAngle, limbSwingAmount, legRotaionSpeed, true);
        walk(right_arm, limbSwing, legRotationAngle, limbSwingAmount, legRotaionSpeed, false);
	
        this.head.rotateAngleX = headPitch / (180F / (float)Math.PI);  //Make the head follow
        this.head.rotateAngleY = netHeadYaw / (180F / (float)Math.PI); //where the fox is actually looking, used by AI looking around
	}
	
	/**
     * Used for easily adding entity-dependent animations. The second and third float params here are the same second
     * and third as in the setRotationAngles method.
     */
    public void setLivingAnimations(EntityLiving entityLiving, float par2, float par3, float par4)
    {
    	
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
