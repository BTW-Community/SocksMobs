package net.minecraft.src;

import java.util.Iterator;
import java.util.List;

public class SocksMobsEntityFox extends EntityTameable
{
	/**
     * The tempt AI task for this mob, used to prevent taming while it is fleeing. //From EntityOcelot
     */
    private EntityAITempt aiTempt;
    
    public SocksMobsEntityFox(World world)
    {
    	//From EntityOcelot
    	super(world);
        this.texture = "/socksmobs/fox/fox.png";
        this.setSize(0.6F, 0.8F);
        this.getNavigator().setAvoidsWater(true);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, this.aiSit); //sitting  tamed animals
        this.tasks.addTask(3, this.aiTempt = new EntityAITempt(this, 0.18F, Item.fishRaw.itemID, true)); //true if is scared by player movement
        this.tasks.addTask(4, new EntityAIAvoidEntity(this, EntityPlayer.class, 16.0F, 0.23F, 0.4F));
        this.tasks.addTask(5, new EntityAIFollowOwner(this, 0.3F, 10.0F, 5.0F));
        this.tasks.addTask(6, new SocksMobsEntityAIFoxSit(this, 0.4F)); // currently just copied from ocelot. Used for sitting on chests, brick ovens and beds if tamed
        this.tasks.addTask(7, new EntityAILeapAtTarget(this, 0.4F));
        this.tasks.addTask(8, new EntityAIAttackOnCollide(this, this.moveSpeed, true));
        this.tasks.addTask(9, new EntityAIOcelotAttack(this));
        this.tasks.addTask(10, new EntityAIMate(this, 0.23F));
        this.tasks.addTask(11, new FCEntityAIWanderSimple(this, 0.23F)); // changed to FC's wander AI
        this.tasks.addTask(12, new EntityAIWatchClosest(this, EntityPlayer.class, 10.0F));
        this.targetTasks.addTask(1, new EntityAITargetNonTamed(this, FCEntityChicken.class, 14.0F, 750, false)); //changed to attack FC's chicken
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 16.0F, 0, true)); //copied from zombie to attack player
   
    }
    
    public int getMaxHealth()
    {
        return this.isTamed() ? 20 : 8;
    }

    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(24, new Byte((byte)0));
    }
    
    /**
     * Return this skeleton's type.
     */
	public int getFoxType()
	{
		return this.dataWatcher.getWatchableObjectByte(24);
	}
	
	/**
     * Set this skeleton's type.
     */
    public void setFoxType(int par1)
    {
        this.dataWatcher.updateObject(24, Byte.valueOf((byte)par1));

    }
    
	/**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readEntityFromNBT(par1NBTTagCompound);

        if (par1NBTTagCompound.hasKey("FoxType"))
        {
            byte var2 = par1NBTTagCompound.getByte("FoxType");
            this.setFoxType(var2);
        }
    }
    
    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeEntityToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setByte("FoxType", (byte)this.getFoxType());
    }
    
    
    
    /**
     * Returns the texture's file path as a String.
     */
    public String getTexture()
    {

    	if (!this.worldObj.canBlockSeeTheSky(MathHelper.floor_double(this.posX), (int)this.boundingBox.minY, MathHelper.floor_double(this.posZ))) //if can't see sky, change texture
    	{
    		if (this.isSitting()) {
    			if (this.getFoxType() == 2) {
        			return "/socksmobs/racoon_sleeping.png";
        		}
        		else {
        			return "/socksmobs/fox_sleeping.png";
        		}
    		} 
    	} else {
    		if (this.getFoxType() == 2) {
    			return "/socksmobs/racoon.png";
    		}
    		else {
    			return  "/socksmobs/fox.png";
    		}
    	}
		return super.getTexture();
    }
    
    /**
     * main AI tick function, replaces updateEntityActionState
     */
    public void updateAITick()
    {
        if (this.getMoveHelper().isUpdating())
        {
            float speed = this.getMoveHelper().getSpeed();

            if (speed == 0.18F)
            {
                this.setSneaking(true);
                this.setSprinting(false);
            }
            else if (speed == 0.4F)
            {
                this.setSneaking(false);
                this.setSprinting(true);
            }
            else
            {
                this.setSneaking(false);
                this.setSprinting(false);
            }
        }
        else
        {
            this.setSneaking(false);
            this.setSprinting(false);
        }
    }
    
    /**
     * Determines if an entity can be despawned, used on idle far away entities
     */
    protected boolean canDespawn()
    {
        return false;
    }
    
    /**
     * Returns true if the newer Entity AI code should be run
     */
    public boolean isAIEnabled()
    {
        return true;
    }
    
    /**
     * Returns the sound this mob makes while it's alive.
     */
    protected String getLivingSound()
    {
        return this.isTamed() ? (this.isInLove() ? "mob.cat.purr" : (this.rand.nextInt(4) == 0 ? "mob.cat.purreow" : "mob.cat.meow")) : "";
    }
    
    /**
     * Returns the sound this mob makes when it is hurt.
     */
    protected String getHurtSound()
    {
        return "mob.cat.hitt";
    }
    
    /**
     * Returns the sound this mob makes on death.
     */
    protected String getDeathSound()
    {
        return "mob.cat.hitt";
    }

    /**
     * Returns the volume for the sounds this mob makes.
     */
    protected float getSoundVolume()
    {
        return 0.4F;
    }
    
    /**
     * Returns the item ID for the item the mob drops on death.
     */
    protected int getDropItemId()
    {
        //TODO set a drop Item
    	return Item.leather.itemID;
    }
    
    /**
     * Drop 0-2 items of this living's type. @param par1 - Whether this entity has recently been hit by a player. @param
     * par2 - Level of Looting used to kill this mob.
     */
    protected void dropFewItems(boolean par1, int par2) {} //TODO doesn't drop anything
    
    public boolean attackEntityAsMob(Entity par1Entity)
    {
    	int var2 = this.isTamed() ? 4 : 2;
        return par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), var2);
    }

    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource par1DamageSource, int par2)
    {
        if (this.isEntityInvulnerable())
        {
            return false;
        }
        else
        {
            this.aiSit.setSitting(false);
            return super.attackEntityFrom(par1DamageSource, par2);
        }
    }
    
    /**
     * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
     */
    public boolean interact(EntityPlayer player)
    {
        ItemStack item = player.inventory.getCurrentItem();

        if (this.isTamed())
        {
            if (player.username.equalsIgnoreCase(this.getOwnerName()) && !this.worldObj.isRemote && !this.isBreedingItem(item))  //set Fox to sit if you are not holding the breeding item
            {
                this.aiSit.setSitting(!this.isSitting());
            }
        }
        else if (this.aiTempt.func_75277_f() && item != null && item.itemID == Item.fishRaw.itemID && player.getDistanceSqToEntity(this) < 9.0D) //set's the taming item // func_75277_f = start executing
        {
            if (!player.capabilities.isCreativeMode)
            {
                --item.stackSize;
            }

            if (item.stackSize <= 0)
            {
            	player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack)null);
            }

            if (!this.worldObj.isRemote)
            {
                if (this.rand.nextInt(3) == 0) // 1 in 4 chance to tame Fox
                {
                    this.setTamed(true);
                    //this.setTameSkin(1 + this.worldObj.rand.nextInt(3));
                    this.setOwner(player.username);
                    this.playTameEffect(true);
                    this.aiSit.setSitting(true);
                    this.worldObj.setEntityState(this, (byte)7);
                }
                else
                {
                    this.playTameEffect(false);
                    // TODO make foxes run away on unsuccessful tame attempt
                    this.worldObj.setEntityState(this, (byte)6);
                }
            }

            return true;
        }

        return super.interact(player);
    }

    /**
     * This function is used when two same-species animals in 'love mode' breed to generate the new baby animal.
     */
    public SocksMobsEntityFox spawnBabyAnimal(EntityAgeable par1EntityAgeable)
    {
    	SocksMobsEntityFox newFox = new SocksMobsEntityFox(this.worldObj);

        if (this.isTamed()) //if the breeding foxes are tame breed a tamed fox and set the owner
        {
        	newFox.setOwner(this.getOwnerName());
        	newFox.setTamed(true);
            //var2.setTameSkin(this.getTameSkin());
        }
        else if (!this.isTamed())
        {
        	newFox.setOwner(this.getOwnerName());
        	newFox.setTamed(true);
        }
        return newFox; //else the fox is wild and thus will spawn a not tamed Fox
        //TODO change this to fit Fox behaviour
    }

    /**
     * Checks if the parameter is an item which this animal can be fed to breed it (wheat, carrots or seeds depending on
     * the animal type)
     */
    public boolean isBreedingItem(ItemStack par1ItemStack)
    {
        return par1ItemStack != null && par1ItemStack.itemID == Item.carrot.itemID; //set's the breeding item
    }

    /**
     * Returns true if the mob is currently able to mate with the specified mob.
     */
    public boolean canMateWith(EntityAnimal EntityAnimal)
    {
        if (EntityAnimal == this) //if an animal is a fox
        {
            return false;  //they can't mate
        }
        else if (!this.isTamed())  //else if fox isn't tamed
        {
            return true; //they can't mate // changed to can mate
        }
        else if (!(EntityAnimal instanceof SocksMobsEntityFox)) //else if an animal is not a type of fox
        {
            return false; //they can't mate
        }
        else //in all other cases, this means in this case only tamed Foxes can breed
        {
        	SocksMobsEntityFox fox = (SocksMobsEntityFox)EntityAnimal;  
            return !fox.isTamed() ? false : this.isInLove() && fox.isInLove();  //if tamed, return true if two foxes are in love. if not tamed don't mate 
        }
    }
    
    /**
     * Checks if the entity's current position is a valid location to spawn this entity.
     */
    public boolean getCanSpawnHere()
    {
        if (this.worldObj.rand.nextInt(20) >= 5)  //75% chance to not spawn
        {
            return false;
        }
        else  // rest chance (25%) allow to spawn
        {
            if (this.worldObj.checkNoEntityCollision(this.boundingBox) && this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(this.boundingBox)) 
            {
                int var1 = MathHelper.floor_double(this.posX);
                int var2 = MathHelper.floor_double(this.boundingBox.minY);
                int var3 = MathHelper.floor_double(this.posZ);

                if (var2 < 100) //don't spawn below y 63
                {
                    return false;
                }

                int var4 = this.worldObj.getBlockId(var1, var2 - 1, var3);

                if (var4 == Block.grass.blockID) //Spawn only on grass blocks
                {
                    return true;
                }
            }

            return false; // in all other cases don't spawn
        }
    }
    
    /**
     * Gets the username of the entity.
     */
    public String getEntityName()
    {
        return this.func_94056_bM() ? this.func_94057_bL() : (this.isTamed() ? "entity.Cat.name" : super.getEntityName());
    }

    /**
     * Initialize this creature.
     */
    public void initCreature()
    {
    	if (this.getRNG().nextInt(2) == 0) //this.worldObj.provider instanceof WorldProviderHell && 
        {
            this.setFoxType(2); 
        }
        else {
        	this.setFoxType(0);
        }
    	
        if (this.worldObj.rand.nextInt(7) == 0) //1/8 chance to spawn 2 babies in world
        {
            for (int var1 = 0; var1 < 2; ++var1) //will spawn 2
            {
                SocksMobsEntityFox var2 = new SocksMobsEntityFox(this.worldObj);
                var2.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
                var2.setGrowingAge(-var2.GetTicksForChildToGrow());
                this.worldObj.spawnEntityInWorld(var2);
            }
        }
    }

    public EntityAgeable createChild(EntityAgeable par1EntityAgeable)
    {
        return this.spawnBabyAnimal(par1EntityAgeable);
    }


	public boolean canFoxSeeSky() {
    	if (!this.worldObj.canBlockSeeTheSky(MathHelper.floor_double(this.posX), (int)this.boundingBox.minY, MathHelper.floor_double(this.posZ))) //if can't see sky, change texture
    	{
    		return false;
    	}
    	
    	return true;
    }
	
	//BTW Code
	
	protected void CheckForLooseFood() //eats raw chicken and fish from ground
    {
        if (!this.worldObj.isRemote && this.isEntityAlive())
        {
            boolean var1 = false;
            List var2 = this.worldObj.getEntitiesWithinAABB(EntityItem.class, this.boundingBox.expand(2.5D, 1.0D, 2.5D));
            Iterator var3 = var2.iterator();

            while (var3.hasNext())
            {
                EntityItem var4 = (EntityItem)var3.next();

                if (var4.delayBeforeCanPickup == 0 && !var4.isDead)
                {
                    ItemStack var5 = var4.getEntityItem();
                    Item var6 = var5.getItem();

                    if (var6.itemID == Item.chickenRaw.itemID || var6.itemID == Item.fishRaw.itemID)
                    {
                        var4.setDead();
                        var1 = true;
                    }
                }
            }

            if (var1)
            {
                this.worldObj.playAuxSFX(2226, MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ), 0);
            }
        }
    }
	
	//public void OnNearbyAnimalAttacked(EntityAnimal var1, EntityLiving var2) {} TODO OnNearbyAnimalAttacked

    //public void OnNearbyPlayerStartles(EntityPlayer var1) {} TODO OnNearbyPlayerStartles

    public boolean IsAffectedByMovementModifiers()
    {
        return false;
    }
    
    protected int GetItemFoodValue(ItemStack var1)
    {
        return 0;
    }

    protected boolean IsTooHungryToHeal()
    {
        return true;
    }

    public int GetMeleeAttackStrength(Entity var1)
    {
        return 3;
    }

}