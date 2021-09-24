// Made with Blockbench 3.9.2
// Exported for Minecraft version 1.5.2
// Paste this class into your mod and generate all required imports

package net.minecraft.src;

public class SocksMobsModelFireFly extends ModelBase {
	private final ModelRenderer legs_left;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer cube_r3;
	private final ModelRenderer legs_right;
	private final ModelRenderer cube_r4;
	private final ModelRenderer cube_r5;
	private final ModelRenderer cube_r6;
	private final ModelRenderer head;
	private final ModelRenderer cube_r7;
	private final ModelRenderer body;
	private final ModelRenderer wings;
	private final ModelRenderer cube_r8;
	private final ModelRenderer cube_r9;
	private final ModelRenderer outer_wings;
	private final ModelRenderer cube_r10;
	private final ModelRenderer cube_r11;

	public SocksMobsModelFireFly() {
		textureWidth = 64;
		textureHeight = 64;

		legs_left = new ModelRenderer(this);
		legs_left.setRotationPoint(1.9998F, 19.9994F, -2.0F);
		setRotation(legs_left, 0.0F, 0.0F, 1.309F);
		

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		legs_left.addChild(cube_r1);
		setRotation(cube_r1, -0.2618F, 0.0F, -0.6109F);
		this.cube_r1.setTextureOffset(4, 3).addBox(0.0F, 0.0F, -1.0F, 0, 3, 1, 0.0F);

		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(-0.0008F, 0.0006F, 0.5F);
		legs_left.addChild(cube_r2);
		setRotation(cube_r2, 0.0F, 0.0F, -0.6109F);
		this.cube_r2.setTextureOffset(4, 0).addBox(0.001F, 0.0F, 0.0F, 0, 3, 1, 0.0F);

		cube_r3 = new ModelRenderer(this);
		cube_r3.setRotationPoint(-0.0008F, 0.0006F, 2.0F);
		legs_left.addChild(cube_r3);
		setRotation(cube_r3, 0.2618F, 0.0F, -0.6109F);
		this.cube_r3.setTextureOffset(2, 3).addBox(0.001F, 0.0F, 0.0F, 0, 3, 1, 0.0F);

		legs_right = new ModelRenderer(this);
		legs_right.setRotationPoint(-1.999F, 20.0F, 0.0F);
		setRotation(legs_right, 0.0F, 0.0F, -1.309F);
		

		cube_r4 = new ModelRenderer(this);
		cube_r4.setRotationPoint(0.0F, 0.0F, 0.0F);
		legs_right.addChild(cube_r4);
		setRotation(cube_r4, 0.2618F, 0.0F, 0.6109F);
		this.cube_r4.setTextureOffset(2, 3).addBox(-0.001F, 0.0F, 0.0F, 0, 3, 1, 0.0F);

		cube_r5 = new ModelRenderer(this);
		cube_r5.setRotationPoint(0.0F, 0.0F, -1.5F);
		legs_right.addChild(cube_r5);
		setRotation(cube_r5, 0.0F, 0.0F, 0.6109F);
		this.cube_r5.setTextureOffset(4, 0).addBox(-0.001F, 0.0F, 0.0F, 0, 3, 1, 0.0F);

		cube_r6 = new ModelRenderer(this);
		cube_r6.setRotationPoint(-0.0008F, -0.0006F, -2.0F);
		legs_right.addChild(cube_r6);
		setRotation(cube_r6, -0.2618F, 0.0F, 0.6109F);
		this.cube_r6.setTextureOffset(4, 3).addBox(0.0F, 0.0F, -1.0F, 0, 3, 1, 0.0F);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 19.0F, -5.0F);
		this.head.setTextureOffset(0, 24).addBox(-3.0F, -1.0F, -2.0F, 6, 3, 3, 0.0F);

		cube_r7 = new ModelRenderer(this);
		cube_r7.setRotationPoint(-5.0F, -1.0F, -1.0F);
		head.addChild(cube_r7);
		setRotation(cube_r7, -0.5236F, 0.0F, 0.0F);
		this.cube_r7.setTextureOffset(21, 14).addBox(0.0F, 0.0F, -3.0F, 10, 0, 3, 0.0F);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 24.0F, 0.0F);
		this.body.setTextureOffset(0, 0).addBox(-2.0F, -6.0F, -4.0F, 4, 3, 11, 0.0F);
		this.body.setTextureOffset(19, 19).addBox(-2.5F, -4.5F, 2.5F, 5, 2, 5, 0.0F);

		wings = new ModelRenderer(this);
		wings.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		cube_r8 = new ModelRenderer(this);
		cube_r8.setRotationPoint(1.0F, -6.001F, -4.0F);
		wings.addChild(cube_r8);
		setRotation(cube_r8, 0.0F, 0.8727F, 0.0F);
		this.cube_r8.setTextureOffset(0, 14).addBox(-2.0F, 0.001F, 0.0F, 3, 0, 10, 0.0F);

		cube_r9 = new ModelRenderer(this);
		cube_r9.setRotationPoint(-1.0F, -6.001F, -4.0F);
		wings.addChild(cube_r9);
		setRotation(cube_r9, 0.0F, -0.829F, 0.0F);
		this.cube_r9.setTextureOffset(9, 0).addBox(-1.0F, 0.001F, 0.0F, 3, 0, 10, 0.0F);

		outer_wings = new ModelRenderer(this);
		outer_wings.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		cube_r10 = new ModelRenderer(this);
		cube_r10.setRotationPoint(-1.0F, -6.101F, -4.0F);
		outer_wings.addChild(cube_r10);
		setRotation(cube_r10, 0.0F, -1.3963F, 0.0F);
		this.cube_r10.setTextureOffset(6, 14).addBox(-1.0F, 0.001F, 0.0F, 2, 0, 10, 0.0F);

		cube_r11 = new ModelRenderer(this);
		cube_r11.setRotationPoint(1.0F, -6.101F, -4.0F);
		outer_wings.addChild(cube_r11);
		setRotation(cube_r11, 0.0F, 1.309F, 0.0F);
		this.cube_r11.setTextureOffset(10, 14).addBox(-1.0F, 0.001F, 0.0F, 2, 0, 10, 0.0F);
	}
	
	/**
     * not actually sure this is size, is not used as of now, but the model would be recreated if the value changed and
     * it seems a good match for a bats size
     */
    public int getSize()
    {
        return 36;
    }
	/**
     		* Sets the models various rotation angles then renders the model.
     		*/
	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		legs_left.render(f5);
		legs_right.render(f5);
		head.render(f5);
		body.render(f5);
		wings.render(f5);
		outer_wings.render(f5);
	}

	public void setRotation(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

}