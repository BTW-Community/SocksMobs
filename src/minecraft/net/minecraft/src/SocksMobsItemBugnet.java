package net.minecraft.src;

public class SocksMobsItemBugnet extends FCItemTool {


	protected SocksMobsItemBugnet(int var1) {
		super(var1, var1, EnumToolMaterial.WOOD);
		this.setUnlocalizedName("SocksMobsItemBugnet");
        this.setCreativeTab(CreativeTabs.tabTools);
	}

	protected boolean GetCanBePlacedAsBlock()
	{
		return true;
	}

	@Override
	protected boolean IsToolTypeEfficientVsBlockType(Block var1) {
		return false;
	}

}
