package net.minecraft.src;

public class SocksMobsBlockCauldron extends BlockContainer {

	private final FCModelBlock blockModel = new SocksMobsModelBlockCauldron();
	
	protected SocksMobsBlockCauldron(int par1) {
		super(par1, Material.iron);
        this.setHardness(0.05F);
        this.setResistance(1.0F);
		this.setCreativeTab(CreativeTabs.tabBrewing);
		this.setUnlocalizedName("Cauldron");
	}
	
	@Override
	public TileEntity createNewTileEntity(World world) {
		return new SocksMobsTileEntityCauldron();
	}
	
	//Render
	
	/**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }
    
    /**
     * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
     * coordinates.  Args: blockAccess, x, y, z, side
     */
    public boolean shouldSideBeRendered(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        return true;
    }

    public boolean RenderBlock(RenderBlocks var1, int var2, int var3, int var4)
    {
        FCModelBlock var6 = this.blockModel.MakeTemporaryCopy();
        var6.RenderAsBlock(var1, this, var2, var3, var4);
        return true;
    }
    
    public void RenderBlockAsItem(RenderBlocks var1, int var2, float var3)
    {
        this.blockModel.RenderAsItemBlock(var1, this, var2);
    }
    
    //textures
    
    private Icon icon_top;
    private Icon icon_bottom;
    private Icon icon_sides_0;
    private Icon icon_sides_1;
    
    public Icon getIcon(int side, int metadata) {
    	if (side == 0) {
    		return icon_bottom;
    	}
    	else if (side == 1) {
    		return icon_top;
    	}
    	else if (((side == 2 || side == 3) && metadata <=3) || ((side == 4 || side == 5) && metadata >= 4)) {
    		return icon_sides_0;
    	}
    	else {
    		return icon_sides_1;
    	}
    }
    
    public void registerIcons(IconRegister register) {
    	icon_top = register.registerIcon("cauldron_top");
    	icon_bottom = register.registerIcon("cauldron_inner");
    	icon_sides_0 = register.registerIcon("cauldron_side");
    	icon_sides_1 = register.registerIcon("cauldron_side");
    }

}
