package net.minecraft.src;

public class SocksMobsItemGlowInk extends FCItemFood {
	public SocksMobsItemGlowInk(int par1) {
		super(par1, 1, 0.0F, false, "SocksMobsItemGlowInk");
		this.setCreativeTab(CreativeTabs.tabMaterials);
		this.setPotionEffect(Potion.nightVision.id, 5, 0,1.0F);
		
	}
	
	 public ItemStack onEaten(ItemStack var1, World var2, EntityPlayer var3)
	    {
		 	super.onEaten(var1, var2, var3);
		 	var3.addPotionEffect(new PotionEffect(Potion.hunger.id, 5*20, 0));
	        
			return var1;
	    }
}
