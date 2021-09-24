package net.minecraft.src;

public class SocksMobsModelGoatHorns extends SocksMobsModelGoat
{
    private ModelRenderer horns;

	public SocksMobsModelGoatHorns()
    {   
		horns = new ModelRenderer(this,12, 55); //replace 0,0 with UV coords from below
		horns.setRotationPoint(0.5F, -4.0F, -2.0F);
		this.head.addChild(horns);
		this.horns.addBox(-2.4F, -5.0F, -1.0F, 2, 7, 2, 0.0F); //UV coords: 12, 55
		this.horns.addBox(0.4F, -5.0F, -1.0F, 2, 7, 2, 0.0F); //UV coords: 12, 55


    }
}
