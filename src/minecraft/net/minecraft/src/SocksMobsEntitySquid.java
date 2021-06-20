package net.minecraft.src;

public class SocksMobsEntitySquid extends FCEntitySquid {
	
	public SocksMobsEntitySquid(World var1)
    {
        super(var1);
        this.texture = "/mob/squid.png";
    }
	
	/**
     * Returns the texture's file path as a String.
     */
    public String getTexture()
    {
    	if (this.getSquidType() == 1) {
    		return "/socksmobs/glow_squid.png";
    	}
    	else return super.getTexture();
    }
    
    /**
     * Initialize this creature.
     */
    public void initCreature()
    {
    	int worldTime = (int)(this.worldObj.worldInfo.getWorldTime() % 24000L);
    	
    	if (worldTime > 17500  && worldTime < 18500) //this.getRNG().nextInt(20) == 0  !this.worldObj.isDaytime() this.worldObj.getMoonPhase()
        {
            this.setSquidType(1);
        }
        else this.setSquidType(0);
    }
    
    
	/**
     * Drop 0-2 items of this living's type. @param par1 - Whether this entity has recently been hit by a player. @param
     * par2 - Level of Looting used to kill this mob.
     */
	@Override
    protected void dropFewItems(boolean var1, int var2)
    {	
		//Ink Sacks
		int var3 = this.rand.nextInt(3 + var2) + 1;

        for (int var4 = 0; var4 < var3; ++var4)
        {
            this.entityDropItem(new ItemStack(Item.dyePowder, 1, 0), 0.0F);
        }
        
        //Shards 
        /**
        var3 = this.rand.nextInt(3 + var2) + 1;

        for (int var4 = 0; var4 < var3; ++var4)
        {
            this.entityDropItem(new ItemStack(DecoDefs.PrismarineShard, 1, 0), 0.0F); 
        }
        */
        
        //Glands
        if (this.rand.nextInt(8) - var2 <= 0)
        {
            this.dropItem(FCBetterThanWolves.fcItemMysteriousGland.itemID, 1);
        }
        
        if (this.getSquidType() == 1 && this.worldObj.provider.dimensionId != -1) //not nether and normal squid
        {
        	//Glowstone Dust
            if (this.rand.nextInt(2) <= 1)
            {
                this.dropItem(Item.lightStoneDust.itemID, 1);
            }
        }

    }
	
	protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(16, new Byte((byte)0));
    }
	
    /**
     * Return this skeleton's type.
     */
	public int getSquidType()
	{
		return this.dataWatcher.getWatchableObjectByte(16);
	}

    /**
     * Set this skeleton's type.
     */
    public void setSquidType(int par1)
    {
        this.dataWatcher.updateObject(16, Byte.valueOf((byte)par1));

    }
    
    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readEntityFromNBT(par1NBTTagCompound);

        if (par1NBTTagCompound.hasKey("SquidType"))
        {
            byte var2 = par1NBTTagCompound.getByte("SquidType");
            this.setSquidType(var2);
        }
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeEntityToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setByte("SquidType", (byte)this.getSquidType());
    }
    
    public boolean DoesGlow()
    {
    	if (this.getSquidType() == 1 ) {
    		return true;
    	}
        return false;
    }
    
    

}
