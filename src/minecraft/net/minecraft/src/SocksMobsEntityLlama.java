package net.minecraft.src;

public class SocksMobsEntityLlama extends EntityAnimal
{
    /** AI task for player control. */
    private final EntityAIControlledByPlayer aiControlledByPlayer;
	

    public SocksMobsEntityLlama(World par1World)
    {
        super(par1World);
        this.texture = "/socksmobs/llama/brown.png";
        this.setSize(1.25F, 1.95F);
        this.getNavigator().setAvoidsWater(true);
        float var2 = 0.25F;
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIPanic(this, 0.38F));
        this.tasks.addTask(2, this.aiControlledByPlayer = new EntityAIControlledByPlayer(this, 0.34F));
        this.tasks.addTask(3, new EntityAIMate(this, var2));
        this.tasks.addTask(4, new EntityAITempt(this, 0.3F, Item.carrotOnAStick.itemID, false));
        this.tasks.addTask(4, new EntityAITempt(this, 0.3F, Item.carrot.itemID, false));
        this.tasks.addTask(5, new EntityAIFollowParent(this, 0.28F));
        this.tasks.addTask(6, new EntityAIWander(this, var2));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
    }
    
    /**
     * Returns true if the newer Entity AI code should be run
     */
    public boolean isAIEnabled()
    {
        return true;
    }

    public int getMaxHealth()
    {
        return 10;
    }

    protected void updateAITasks()
    {
        super.updateAITasks();
    }
    
    public boolean IsAffectedByMovementModifiers()
    {
        return false;
    }

    /**
     * returns true if all the conditions for steering the entity are met. For pigs, this is true if it is being ridden
     * by a player and the player is holding a carrot-on-a-stick
     */
    public boolean canBeSteered()
    {
        ItemStack var1 = ((EntityPlayer)this.riddenByEntity).getHeldItem();
        return var1 != null && var1.itemID == Item.carrotOnAStick.itemID;
    }

    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(16, Byte.valueOf((byte)0)); //saddle
        this.dataWatcher.addObject(17, Byte.valueOf((byte)0)); //chest
    }
    
    /**
     * Returns the Y offset from the entity's position for any entity riding this one.
     */
    public double getMountedYOffset()
    {
        return (double)this.height * 0.6D;
    }
    
    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeEntityToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setBoolean("Saddle", this.getSaddled());
        par1NBTTagCompound.setBoolean("Chest", this.getChest());
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readEntityFromNBT(par1NBTTagCompound);
        this.setSaddled(par1NBTTagCompound.getBoolean("Saddle"));
        this.setChest(par1NBTTagCompound.getBoolean("Chest"));
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
    protected String getLivingSound()
    {
        return "mob.sheep.say";
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    protected String getHurtSound()
    {
        return "mob.sheep.say";
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected String getDeathSound()
    {
        return "mob.sheep.death";
    }

    /**
     * Plays step sound at given x, y, z for the entity
     */
    protected void playStepSound(int par1, int par2, int par3, int par4)
    {
        this.playSound("mob.sheep.step", 0.15F, 1.0F);
    }

    /**
     * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
     */
    public boolean interact(EntityPlayer par1EntityPlayer)
    {    	
    	ItemStack currentItem = par1EntityPlayer.inventory.getCurrentItem();
    	
        if (super.interact(par1EntityPlayer))
        {
            return true;
        }
        else if (currentItem == null && this.getSaddled() && !this.worldObj.isRemote && (this.riddenByEntity == null || this.riddenByEntity == par1EntityPlayer))
        {
            par1EntityPlayer.mountEntity(this);
            return true;
        }
        else if (currentItem != null && currentItem.itemID == FCBetterThanWolves.fcBlockChest.blockID && !this.getChest())
        {
            this.attackEntityFrom(DamageSource.generic, 0);

            if (!this.worldObj.isRemote && !this.getChest())
            {
                this.setChest(true);
                this.worldObj.playAuxSFX(2254, MathHelper.floor_double(this.posX), (int)this.posY, MathHelper.floor_double(this.posZ), 0);
                
                par1EntityPlayer.inventory.decrStackSize(par1EntityPlayer.inventory.currentItem, 1);
            }
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Returns the item ID for the item the mob drops on death.
     */
    protected int getDropItemId()
    {
        return this.isBurning() ? Item.porkCooked.itemID : Item.porkRaw.itemID;
    }

    /**
     * Drop 0-2 items of this living's type. @param par1 - Whether this entity has recently been hit by a player. @param
     * par2 - Level of Looting used to kill this mob.
     */
    protected void dropFewItems(boolean par1, int par2)
    {
        int var3 = this.rand.nextInt(3) + 1 + this.rand.nextInt(1 + par2);

        for (int var4 = 0; var4 < var3; ++var4)
        {
            if (this.isBurning())
            {
                this.dropItem(FCBetterThanWolves.fcItemMeatBurned.itemID, 1);
            }
            else
            {
                this.dropItem(FCBetterThanWolves.fcItemMuttonRaw.itemID, 1);
            }
        }

        if (this.getSaddled())
        {
            this.dropItem(Item.saddle.itemID, 1);
        }
        
        if (this.getChest())
        {
            this.dropItem(FCBetterThanWolves.fcBlockChest.blockID, 1);
        }

    }

    /**
     * Returns true if the pig is saddled.
     */
    public boolean getSaddled()
    {
        return (this.dataWatcher.getWatchableObjectByte(16) & 1) != 0;
    }

    /**
     * Set or remove the saddle of the pig.
     */
    public void setSaddled(boolean par1)
    {
        if (par1)
        {
            this.dataWatcher.updateObject(16, Byte.valueOf((byte)1));
        }
        else
        {
            this.dataWatcher.updateObject(16, Byte.valueOf((byte)0));
        }
    }
    
    /**
     * Returns true if the pig is saddled.
     */
    public boolean getChest()
    {
        return (this.dataWatcher.getWatchableObjectByte(17) & 1) != 0;
    }

    /**
     * Set or remove the saddle of the pig.
     */
    public void setChest(boolean par1)
    {
        if (par1)
        {
            this.dataWatcher.updateObject(17, Byte.valueOf((byte)1));
        }
        else
        {
            this.dataWatcher.updateObject(17, Byte.valueOf((byte)0));
        }
    }

    /**
     * This function is used when two same-species animals in 'love mode' breed to generate the new baby animal.
     */
    public SocksMobsEntityLlama spawnBabyAnimal(EntityAgeable par1EntityAgeable)
    {
        return new SocksMobsEntityLlama(this.worldObj);
    }

    /**
     * Checks if the parameter is an item which this animal can be fed to breed it (wheat, carrots or seeds depending on
     * the animal type)
     */
    public boolean isBreedingItem(ItemStack par1ItemStack)
    {
        return par1ItemStack != null && par1ItemStack.itemID == Item.carrot.itemID;
    }

    /**
     * Return the AI task for player control.
     */
    public EntityAIControlledByPlayer getAIControlledByPlayer()
    {
        return this.aiControlledByPlayer;
    }

    public EntityAgeable createChild(EntityAgeable par1EntityAgeable)
    {
        return this.spawnBabyAnimal(par1EntityAgeable);
    }
}
