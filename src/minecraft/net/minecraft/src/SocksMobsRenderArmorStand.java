package net.minecraft.src;

public class SocksMobsRenderArmorStand extends RenderBiped {

	public SocksMobsRenderArmorStand() {
		super(new SocksMobsModelArmorStand(), 0.5F, 1.0F);
	}
	
	protected void renderEquippedItems(EntityLiving par1EntityLiving, float par2)
    {
        this.func_82428_a((SocksMobsEntityArmorStand)par1EntityLiving, par2);
    }
	
	protected void func_82428_a(SocksMobsEntityArmorStand par1EntityZombie, float par2)
    {
        super.renderEquippedItems(par1EntityZombie, par2);
    }
}
