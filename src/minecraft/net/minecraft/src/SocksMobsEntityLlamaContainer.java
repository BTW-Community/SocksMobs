package net.minecraft.src;

public class SocksMobsEntityLlamaContainer extends SocksMobsEntityLlama implements IInventory{
	
	private ItemStack[] llamaContainerItems = new ItemStack[36];
	
	/**
     * When set to true, the minecart will drop all items when setDead() is called. When false (such as when travelling
     * dimensions) it preserves its contents.
     */
    private boolean dropContentsWhenDead = true;
	
	public SocksMobsEntityLlamaContainer(World world) {
		super(world);
	}


	@Override
	public int getSizeInventory() {
		// TODO Auto-generated method stub
		return 27;
	}

	@Override
	public ItemStack getStackInSlot(int par1) {
		return this.llamaContainerItems[par1];
	}

	@Override
	public ItemStack decrStackSize(int par1, int par2) {
        if (this.llamaContainerItems[par1] != null)
        {
            ItemStack var3;

            if (this.llamaContainerItems[par1].stackSize <= par2)
            {
                var3 = this.llamaContainerItems[par1];
                this.llamaContainerItems[par1] = null;
                return var3;
            }
            else
            {
                var3 = this.llamaContainerItems[par1].splitStack(par2);

                if (this.llamaContainerItems[par1].stackSize == 0)
                {
                    this.llamaContainerItems[par1] = null;
                }

                return var3;
            }
        }
        else
        {
            return null;
        }
    }

	@Override
	public ItemStack getStackInSlotOnClosing(int par1) {
        if (this.llamaContainerItems[par1] != null)
        {
            ItemStack var2 = this.llamaContainerItems[par1];
            this.llamaContainerItems[par1] = null;
            return var2;
        }
        else
        {
            return null;
        }
    }

	@Override
	public void setInventorySlotContents(int par1, ItemStack par2ItemStack) {
        this.llamaContainerItems[par1] = par2ItemStack;

        if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit())
        {
            par2ItemStack.stackSize = this.getInventoryStackLimit();
        }
    }
	
	/**
     * Do not make give this method the name canInteractWith because it clashes with Container
     */
	@Override
    public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
    {
        return this.isDead ? false : par1EntityPlayer.getDistanceSqToEntity(this) <= 64.0D;
    }
	
	@Override
	public String getInvName() {
		// TODO Auto-generated method stub
		return "Llama";
	}
	
	

	@Override
	public boolean isInvNameLocalized() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public void onInventoryChanged() {}

	@Override
	public void openChest() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeChest() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isStackValidForSlot(int var1, ItemStack var2) {
		return true;
	}
	
	/**
     * Teleports the entity to another dimension. Params: Dimension number to teleport to
     */
    public void travelToDimension(int par1)
    {
        this.dropContentsWhenDead = false;
        super.travelToDimension(par1);
    }
    
    /**
     * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
     */
    public boolean interact(EntityPlayer par1EntityPlayer)
    {
    	if (super.interact(par1EntityPlayer))
        {
            return true;
        }
    	else if (!this.worldObj.isRemote && par1EntityPlayer.isSneaking() && this.getChest())
        {
            par1EntityPlayer.displayGUIChest(this);
        }

        return true;
    }
    
    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeEntityToNBT(par1NBTTagCompound);
        NBTTagList var2 = new NBTTagList();

        for (int var3 = 0; var3 < this.llamaContainerItems.length; ++var3)
        {
            if (this.llamaContainerItems[var3] != null)
            {
                NBTTagCompound var4 = new NBTTagCompound();
                var4.setByte("Slot", (byte)var3);
                this.llamaContainerItems[var3].writeToNBT(var4);
                var2.appendTag(var4);
            }
        }

        par1NBTTagCompound.setTag("Items", var2);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readEntityFromNBT(par1NBTTagCompound);
        NBTTagList var2 = par1NBTTagCompound.getTagList("Items");
        this.llamaContainerItems = new ItemStack[this.getSizeInventory()];

        for (int var3 = 0; var3 < var2.tagCount(); ++var3)
        {
            NBTTagCompound var4 = (NBTTagCompound)var2.tagAt(var3);
            int var5 = var4.getByte("Slot") & 255;

            if (var5 >= 0 && var5 < this.llamaContainerItems.length)
            {
                this.llamaContainerItems[var5] = ItemStack.loadItemStackFromNBT(var4);
            }
        }
    }
}
