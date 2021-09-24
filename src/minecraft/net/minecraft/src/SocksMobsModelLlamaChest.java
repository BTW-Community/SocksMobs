package net.minecraft.src;

public class SocksMobsModelLlamaChest extends ModelBase
{
	private final ModelRenderer chest;
	private final ModelRenderer left_chest_r1;
	private final ModelRenderer right_chest_r1;
	
    public SocksMobsModelLlamaChest()
    {
        super();
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
    }
    
    public void setRotation(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
