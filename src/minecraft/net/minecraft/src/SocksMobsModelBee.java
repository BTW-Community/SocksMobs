// Made with Blockbench 3.9.2
// Exported for Minecraft version 1.5.2
// Paste this class into your mod and generate all required imports

package net.minecraft.src;

public class SocksMobsModelBee extends ModelBase {
	private final ModelRenderer body;
	private final ModelRenderer stinger;
	private final ModelRenderer rightwing_bone;
	private final ModelRenderer leftwing_bone;
	private final ModelRenderer leg_front;
	private final ModelRenderer leg_mid;
	private final ModelRenderer leg_back;

	public SocksMobsModelBee() {
		textureWidth = 64;
		textureHeight = 64;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.5F, 19.0F, 0.0F);
		this.body.setTextureOffset(0, 0).addBox(-3.5F, -4.0F, -5.0F, 7, 7, 10, 0.0F);
		this.body.setTextureOffset(2, 3).addBox(-2.5F, -4.0F, -8.0F, 1, 2, 3, 0.0F);
		this.body.setTextureOffset(2, 0).addBox(1.5F, -4.0F, -8.0F, 1, 2, 3, 0.0F);

		stinger = new ModelRenderer(this);
		stinger.setRotationPoint(0.0F, -1.0F, 1.0F);
		body.addChild(stinger);
		this.stinger.setTextureOffset(26, 7).addBox(0.0F, 0.0F, 4.0F, 0, 1, 2, 0.0F);

		rightwing_bone = new ModelRenderer(this);
		rightwing_bone.setRotationPoint(-1.5F, -4.0F, -3.0F);
		body.addChild(rightwing_bone);
		setRotation(rightwing_bone, 0.2618F, -0.2618F, 0.0F);
		this.rightwing_bone.setTextureOffset(0, 18).addBox(-9.0F, 0.0F, 0.0F, 9, 0, 6, 0.0F);

		leftwing_bone = new ModelRenderer(this);
		leftwing_bone.setRotationPoint(1.5F, -4.0F, -3.0F);
		body.addChild(leftwing_bone);
		setRotation(leftwing_bone, 0.2618F, 0.2618F, 0.0F);
		this.leftwing_bone.setTextureOffset(9, 24).addBox(0.0F, 0.0F, 0.0F, 9, 0, 6, 0.0F);

		leg_front = new ModelRenderer(this);
		leg_front.setRotationPoint(1.5F, 3.0F, -2.0F);
		body.addChild(leg_front);
		this.leg_front.setTextureOffset(26, 1).addBox(-5.0F, 0.0F, 0.0F, 7, 2, 0, 0.0F);

		leg_mid = new ModelRenderer(this);
		leg_mid.setRotationPoint(1.5F, 3.0F, 0.0F);
		body.addChild(leg_mid);
		this.leg_mid.setTextureOffset(26, 3).addBox(-5.0F, 0.0F, 0.0F, 7, 2, 0, 0.0F);

		leg_back = new ModelRenderer(this);
		leg_back.setRotationPoint(1.5F, 3.0F, 2.0F);
		body.addChild(leg_back);
		this.leg_back.setTextureOffset(26, 5).addBox(-5.0F, 0.0F, 0.0F, 7, 2, 0, 0.0F);
	}
	/**
     		* Sets the models various rotation angles then renders the model.
     		*/
	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		body.render(f5);
	}

	public void setRotation(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

}