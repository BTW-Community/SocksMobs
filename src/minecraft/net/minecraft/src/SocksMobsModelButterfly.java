// Made with Blockbench 3.9.2
// Exported for Minecraft version 1.5.2
// Paste this class into your mod and generate all required imports

package net.minecraft.src;

public class SocksMobsModelButterfly extends ModelBase {
	private final ModelRenderer body;
	private final ModelRenderer leftwing;
	private final ModelRenderer rightwing;
	private final ModelRenderer head;

	public SocksMobsModelButterfly() {
		textureWidth = 64;
		textureHeight = 64;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 22.5F, 0.0F);
		this.body.setTextureOffset(0, 12).addBox(-1.5F, -1.5F, -4.0F, 3, 3, 10, 0.0F);

		leftwing = new ModelRenderer(this);
		leftwing.setRotationPoint(0.5F, 21.0F, 0.0F);
		setRotation(leftwing, 0.0F, 0.0F, -0.1745F);
		this.leftwing.setTextureOffset(0, 0).addBox(0.0F, 0.0F, -5.0F, 10, 0, 12, 0.0F);

		rightwing = new ModelRenderer(this);
		rightwing.setRotationPoint(-0.5F, 21.0F, 0.0F);
		setRotation(rightwing, 0.0F, 0.0F, 0.1745F);
		this.rightwing.setTextureOffset(0, 0).addBox(-10.0F, 0.0F, -5.0F, 10, 0, 12, 0.0F);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 24.0F, 0.0F);
		this.head.setTextureOffset(0, 0).addBox(-1.5F, -3.0F, -6.0F, 3, 3, 2, 0.0F);
		this.head.setTextureOffset(16, 12).addBox(-4.5F, -3.0F, -10.0F, 9, 1, 4, 0.0F);
	}
	/**
     		* Sets the models various rotation angles then renders the model.
     		*/
	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		body.render(f5);
		leftwing.render(f5);
		rightwing.render(f5);
		head.render(f5);
	}
	
	/**
     * not actually sure this is size, is not used as of now, but the model would be recreated if the value changed and
     * it seems a good match for a bats size
     */
    public int getBatSize()
    {
        return 36;
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
	public void setRotationAngles(float time, float swingAmount, float f3, float netHeadYaw, float headPitch, float scaleFactor, Entity entity)	
	{
		flap(leftwing, time, degToRad(90), swingAmount, 0.9F, false);
		flap(rightwing, time, degToRad(90), swingAmount, 0.9F, true);
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