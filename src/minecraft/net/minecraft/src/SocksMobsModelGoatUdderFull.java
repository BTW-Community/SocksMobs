package net.minecraft.src;

public class SocksMobsModelGoatUdderFull extends SocksMobsModelGoat
{
    ModelRenderer udderFull;

	public SocksMobsModelGoatUdderFull()
    {   
		udderFull = new ModelRenderer(this);
		udderFull.setRotationPoint(0.0F, 0.0F, 0.0F);
		body.addChild(udderFull);
		this.udderFull.setTextureOffset(47, 39).addBox(-1.5F, -6.0F, 1.5F, 4, 3, 4, 0.0F);

    }
}