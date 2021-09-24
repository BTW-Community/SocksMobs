// Made with Blockbench 3.9.2
// Exported for Minecraft version 1.5.2
// Paste this class into your mod and generate all required imports

package net.minecraft.src;

public class SocksMobsModelArmorStand extends ModelBiped {
	private final ModelRenderer baseplate;
	private final ModelRenderer waist;
	private final ModelRenderer body;
	private final ModelRenderer head;
	private final ModelRenderer leftarm;
	private final ModelRenderer leftleg;
	private final ModelRenderer rightarm;
	private final ModelRenderer rightleg;

	public SocksMobsModelArmorStand() {
		textureWidth = 64;
		textureHeight = 64;

		baseplate = new ModelRenderer(this);
		baseplate.setRotationPoint(0.0F, 24.0F, 0.0F);
		this.baseplate.setTextureOffset(0, 32).addBox(-6.0F, -1.0F, -6.0F, 12, 1, 12, 0.0F);

		waist = new ModelRenderer(this);
		waist.setRotationPoint(0.0F, -12.0F, 0.0F);
		baseplate.addChild(waist);
		

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, -12.0F, 0.0F);
		waist.addChild(body);
		this.body.setTextureOffset(0, 26).addBox(-6.0F, 0.0F, -1.5F, 12, 3, 3, 0.0F);
		this.body.setTextureOffset(16, 0).addBox(-3.0F, 3.0F, -1.0F, 2, 7, 2, 0.0F);
		this.body.setTextureOffset(48, 16).addBox(1.0F, 3.0F, -1.0F, 2, 7, 2, 0.0F);
		this.body.setTextureOffset(0, 48).addBox(-4.0F, 10.0F, -1.0F, 8, 2, 2, 0.0F);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 0.0F, 0.0F);
		body.addChild(head);
		this.head.setTextureOffset(0, 0).addBox(-1.0F, -7.0F, -1.0F, 2, 7, 2, 0.0F);

		leftarm = new ModelRenderer(this);
		leftarm.setRotationPoint(5.0F, 2.0F, 0.0F);
		body.addChild(leftarm);
		this.leftarm.setTextureOffset(32, 16).addBox(0.0F, -2.0F, -1.0F, 2, 12, 2, 0.0F);

		leftleg = new ModelRenderer(this);
		leftleg.setRotationPoint(1.9F, 12.0F, 0.0F);
		body.addChild(leftleg);
		this.leftleg.setTextureOffset(40, 16).addBox(-1.0F, 0.0F, -1.0F, 2, 11, 2, 0.0F);

		rightarm = new ModelRenderer(this);
		rightarm.setRotationPoint(-5.0F, 2.0F, 0.0F);
		body.addChild(rightarm);
		this.rightarm.setTextureOffset(24, 0).addBox(-2.0F, -2.0F, -1.0F, 2, 12, 2, 0.0F);

		rightleg = new ModelRenderer(this);
		rightleg.setRotationPoint(-1.9F, 12.0F, 0.0F);
		body.addChild(rightleg);
		this.rightleg.setTextureOffset(8, 0).addBox(-1.0F, 0.0F, -1.0F, 2, 11, 2, 0.0F);
	}
	/**
     		* Sets the models various rotation angles then renders the model.
     		*/
	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		baseplate.render(f5);
	}

	public void setRotation(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

}