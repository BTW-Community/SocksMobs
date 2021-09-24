package net.minecraft.src;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.Iterator;
import java.util.List;

public class SocksMobsEntityGoat extends EntityAnimal
{
		
	protected static final int m_iGotMilkDataWatcherID = 26;
    private static final int m_iFullMilkAccumulationCount = 24000;
    private static final int m_iKickAttackTicksToCooldown = 40;
    private static final double m_dKickAttackRange = 1.75D;
    public static final int m_iKickAttackDuration = 20;
    public static final double m_dKickAttackTipCollisionWidth = 2.75D;
    public static final double m_dKickAttackTipCollisionHalfWidth = 1.375D;
    public static final double m_dKickAttackTipCollisionHeight = 2.0D;
    public static final double m_dKickAttackTipCollisionHalfHeight = 1.0D;
    private int m_iMilkAccumulationCount = 0;
    private int m_iKickAttackCooldownTimer = 40;
    public int m_iKickAttackInProgressCounter = -1;
    public int m_iKickAttackLegUsed = 0;
    
	
    public SocksMobsEntityGoat(World par1World)
    {
        super(par1World);
        this.texture = "/socksmobs/goat_horns.png";
        this.setSize(0.9F, 1.3F);
        this.getNavigator().setAvoidsWater(true);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, new FCEntityAIAnimalFlee(this, 0.38F));
        this.tasks.addTask(3, new EntityAIMate(this, 0.2F));
        this.tasks.addTask(4, new FCEntityAIMultiTempt(this, 0.25F));
        this.tasks.addTask(5, new EntityAIFollowParent(this, 0.25F));
        this.tasks.addTask(6, new FCEntityAIGraze(this));
        this.tasks.addTask(7, new FCEntityAIMoveToLooseFood(this, 0.2F));
        this.tasks.addTask(8, new FCEntityAIMoveToGraze(this, 0.2F));
        this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(11, new EntityAILookIdle(this));
    }
    
    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(26, new Byte((byte)0)); //milk datawatcher
    }
    
    protected void fall(float par1)
    {
        EntityLivingFall(par1-3.0F);
    }
    
    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource var1, int var2)
    {
        if (this.isEntityInvulnerable())
        {
            return false;
        }
        else
        {
            Entity var3 = var1.getEntity();

            if (var3 != null && var3 instanceof EntityPlayer)
            {
                EntityPlayer var4 = (EntityPlayer)var3;
                List var5 = this.worldObj.getEntitiesWithinAABB(SocksMobsEntityGoatPossessed.class, this.boundingBox.expand(16.0D, 8.0D, 16.0D));
                Iterator var6 = var5.iterator();

                while (var6.hasNext())
                {
                	SocksMobsEntityGoatPossessed var7 = (SocksMobsEntityGoatPossessed)var6.next();

                    if (!var7.isLivingDead)
                    {
                       // var7.BecomeAngryWhenPigAttacked(var4);
                    }
                }
            }

            return super.attackEntityFrom(var1, var2);
        }
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
        return "mob.sheep.hurt";
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected String getDeathSound()
    {
        return "mob.sheep.hurt";
    }

    /**
     * Plays step sound at given x, y, z for the entity
     */
    protected void playStepSound(int par1, int par2, int par3, int par4)
    {
        this.playSound("mob.sheep.step", 0.15F, 1.0F);
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
        return Item.leather.itemID;
    }

    /**
     * Drop 0-2 items of this living's type. @param par1 - Whether this entity has recently been hit by a player. @param
     * par2 - Level of Looting used to kill this mob.
     */
    protected void dropFewItems(boolean var1, int var2)
    {
        if (!this.IsStarving())
        {
            int var3 = this.rand.nextInt(3) + this.rand.nextInt(1 + var2);

            if (this.IsFamished())
            {
                var3 /= 2;
            }

            int var4;

            for (var4 = 0; var4 < var3; ++var4)
            {
                this.dropItem(Item.leather.itemID, 1);
            }

            if (!this.HasHeadCrabbedSquid())
            {
                var3 = this.rand.nextInt(3) + 1 + this.rand.nextInt(1 + var2);

                if (this.IsFamished())
                {
                    var3 /= 2;
                }

                for (var4 = 0; var4 < var3; ++var4)
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
            }
        }
    }

    /**
     * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
     */
    public boolean interact(EntityPlayer var1)
    {
        ItemStack var2 = var1.inventory.getCurrentItem();

        if (var2 != null && var2.itemID == Item.bucketEmpty.itemID)
        {
            if (this.GotMilk())
            {
                --var2.stackSize;

                if (var2.stackSize <= 0)
                {
                    var1.inventory.setInventorySlotContents(var1.inventory.currentItem, new ItemStack(Item.bucketMilk));
                }
                else if (!var1.inventory.addItemStackToInventory(new ItemStack(Item.bucketMilk)))
                {
                    var1.dropPlayerItem(new ItemStack(Item.bucketMilk.itemID, 1, 0));
                }

                this.attackEntityFrom(DamageSource.generic, 0);

                if (!this.worldObj.isRemote)
                {
                    this.SetGotMilk(false);
                    this.worldObj.playAuxSFX(2254, MathHelper.floor_double(this.posX), (int)this.posY, MathHelper.floor_double(this.posZ), 0);
                }
            }
            else
            {
                this.attackEntityFrom(DamageSource.causePlayerDamage(var1), 0);
            }

            return true;
        }
        else
        {
            return this.EntityAnimalInteract(var1);
        }
    }

    /**
     * This function is used when two same-species animals in 'love mode' breed to generate the new baby animal.
     */
    public SocksMobsEntityGoat spawnBabyAnimal(EntityAgeable par1EntityAgeable)
    {
    	return new SocksMobsEntityGoat(this.worldObj);
    }

    public EntityAgeable createChild(EntityAgeable par1EntityAgeable)
    {
        return this.spawnBabyAnimal(par1EntityAgeable);
    }
    
    
    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound var1)
    {
        super.writeEntityToNBT(var1);
        var1.setBoolean("fcGotMilk", this.GotMilk());
        var1.setInteger("fcMilkCount", this.m_iMilkAccumulationCount);
    }


    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound var1)
    {
        super.readEntityFromNBT(var1);

        if (var1.hasKey("fcGotMilk"))
        {
        	this.SetGotMilk(var1.getBoolean("fcGotMilk"));
        }
        if (var1.hasKey("fcMilkCount"))
        {
            this.m_iMilkAccumulationCount = var1.getInteger("fcMilkCount");
        }
    }
    
    /**
     * Initialize this creature.
     */
    public void initCreature()
    {
        this.InitHungerWithVariance();

        if (!this.isChild())
        {
            this.m_iMilkAccumulationCount = this.worldObj.rand.nextInt(30001);

            if (this.m_iMilkAccumulationCount >= 24000)
            {
                this.m_iMilkAccumulationCount = 0;
                this.SetGotMilk(true);
            }
        }
        
        
    }
    

    
    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate()
    {
        this.UpdateKickAttack();
        super.onLivingUpdate();
    }
    
    /**
     * Checks if the parameter is an item which this animal can be fed to breed it (wheat, carrots or seeds depending on
     * the animal type)
     */
    public boolean isBreedingItem(ItemStack var1)
    {
        return var1.itemID == Item.carrot.itemID;
    }
    
    public void OnGrazeBlock(int var1, int var2, int var3)
    {
        super.OnGrazeBlock(var1, var2, var3);

        if (!this.getWearingBreedingHarness())
        {
            this.CheckForGrazeSideEffects(var1, var2, var3);
        }
    }

    public boolean IsSubjectToHunger()
    {
        return true;
    }

    public void OnBecomeFamished()
    {
        super.OnBecomeFamished();

        if (this.GotMilk())
        {
            this.SetGotMilk(false);
        }

        this.m_iMilkAccumulationCount = 0;
    }

    public boolean CanGrazeMycelium()
    {
        return true;
    }

    /**
     * Returns the Y offset from the entity's position for any entity riding this one.
     */
    public double getMountedYOffset()
    {
        return (double)this.height * 1.2D;
    }

    protected boolean GetCanCreatureTypeBePossessed()
    {
        return true;
    }
    
    protected void OnFullPossession()
    {
        this.worldObj.playAuxSFX(2257, MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ), 0);
        this.setDead();
        SocksMobsEntityGoatPossessed var1 = new SocksMobsEntityGoatPossessed(this.worldObj);
        var1.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
        var1.renderYawOffset = this.renderYawOffset;
        var1.SetPersistent(true);
        this.worldObj.spawnEntityInWorld(var1);
    }

    protected void UpdateHungerState()
    {
        if (!this.GotMilk() && this.IsFullyFed() && !this.isChild() && !this.getWearingBreedingHarness())
        {
            --this.m_iHungerCountdown;
            ++this.m_iMilkAccumulationCount;

            if (this.m_iMilkAccumulationCount >= 24000)
            {
                this.SetGotMilk(true);
                this.m_iMilkAccumulationCount = 0;
                this.worldObj.playAuxSFX(2253, MathHelper.floor_double(this.posX), (int)this.posY + 1, MathHelper.floor_double(this.posZ), 0);
            }
        }

        super.UpdateHungerState();
    }
    
    public float KnockbackMagnitude()
    {
        return 0.3F;
    }

    public void CheckForGrazeSideEffects(int var1, int var2, int var3)
    {
        int var4 = this.worldObj.getBlockId(var1, var2, var3);

        if (var4 == Block.mycelium.blockID)
        {
            this.ConvertToMooshroom();
        }
    }

    public void ConvertToMooshroom()
    {
    	
//        int var1 = MathHelper.floor_double(this.posX);
//        int var2 = MathHelper.floor_double(this.posY) + 1;
//        int var3 = MathHelper.floor_double(this.posZ);
//        byte var4 = 0;
//
//        if (this.isChild())
//        {
//            var4 = 1;
//        }
//
//        this.worldObj.playAuxSFX(2255, var1, var2, var3, var4);
//        this.setDead();
//        EntityMooshroom var5 = new EntityMooshroom(this.worldObj);
//        var5.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
//        var5.setEntityHealth(this.getHealth());
//        var5.renderYawOffset = this.renderYawOffset;
//        var5.setGrowingAge(this.getGrowingAge());
//        this.worldObj.spawnEntityInWorld(var5);
    }

    public boolean GotMilk()
    {
        byte var1 = this.dataWatcher.getWatchableObjectByte(26);
        return var1 != 0;
    }

    protected void SetGotMilk(boolean var1)
    {
        byte var2 = 0;

        if (var1)
        {
            var2 = 1;
        }

        this.dataWatcher.updateObject(26, Byte.valueOf(var2));
    }

    private void UpdateKickAttack()
    {
        if (this.m_iKickAttackInProgressCounter >= 0)
        {
            ++this.m_iKickAttackInProgressCounter;

            if (this.m_iKickAttackInProgressCounter >= 20)
            {
                this.m_iKickAttackInProgressCounter = -1;
            }
        }
        else if (!this.worldObj.isRemote)
        {
            --this.m_iKickAttackCooldownTimer;

            if (this.isEntityAlive() && !this.isChild() && !this.getWearingBreedingHarness() && this.m_iKickAttackCooldownTimer <= 0 && (this.isBurning() || this.getAITarget() != null))
            {
                Vec3 var1 = this.ComputeKickAttackCenter();
                AxisAlignedBB var2 = AxisAlignedBB.getAABBPool().getAABB(var1.xCoord - 1.375D, var1.yCoord - 1.0D, var1.zCoord - 1.375D, var1.xCoord + 1.375D, var1.yCoord + 1.0D, var1.zCoord + 1.375D);
                List var3 = this.worldObj.getEntitiesWithinAABB(EntityLiving.class, var2);

                if (!var3.isEmpty())
                {
                    boolean var4 = false;
                    Vec3 var5 = Vec3.createVectorHelper(this.posX, this.posY + (double)(this.height / 2.0F), this.posZ);
                    Iterator var6 = var3.iterator();

                    while (var6.hasNext())
                    {
                        EntityLiving var7 = (EntityLiving)var6.next();

                        if (!(var7 instanceof SocksMobsEntityGoat) && var7.isEntityAlive() && var7.ridingEntity != this && this.CanEntityBeSeenForAttackToCenterOfMass(var7, var5))
                        {
                            var4 = true;
                            this.KickAttackHitTarget(var7);
                        }
                    }

                    if (var4)
                    {
                        this.LaunchKickAttack();
                    }
                }
            }
        }
    }

    public boolean CanEntityBeSeenForAttackToCenterOfMass(Entity var1, Vec3 var2)
    {
        return this.worldObj.rayTraceBlocks_do_do(var2, this.worldObj.getWorldVec3Pool().getVecFromPool(var1.posX, var1.posY + (double)(var1.height / 2.0F), var1.posZ), false, true) == null;
    }

    public Vec3 ComputeKickAttackCenter()
    {
        float var1 = MathHelper.wrapAngleTo180_float(this.rotationYaw + 180.0F);
        double var2 = (double)(-MathHelper.sin(var1 / 180.0F * (float)Math.PI)) * 1.75D;
        double var4 = (double)(this.height / 2.0F);
        double var6 = (double)MathHelper.cos(var1 / 180.0F * (float)Math.PI) * 1.75D;
        var2 += this.posX;
        var4 += this.posY;
        var6 += this.posZ;
        return Vec3.createVectorHelper(var2, var4, var6);
    }

    private void LaunchKickAttack()
    {
        this.m_iKickAttackInProgressCounter = 0;
        this.m_iKickAttackCooldownTimer = 40;
        this.TransmitKickAttackToClients();
    }

    private void TransmitKickAttackToClients()
    {
        ByteArrayOutputStream var1 = new ByteArrayOutputStream();
        DataOutputStream var2 = new DataOutputStream(var1);

        try
        {
            var2.writeInt(this.entityId);
            var2.writeByte(2);
        }
        catch (Exception var4)
        {
            var4.printStackTrace();
        }

        Packet250CustomPayload var3 = new Packet250CustomPayload("FC|EV", var1.toByteArray());
        FCUtilsWorld.SendPacketToAllPlayersTrackingEntity((WorldServer)this.worldObj, this, var3);
    }

    public void OnClientNotifiedOfKickAttack()
    {
        this.m_iKickAttackInProgressCounter = 0;
        this.m_iKickAttackLegUsed = this.rand.nextInt(2);
        this.worldObj.playSound(this.posX, this.posY, this.posZ, "random.bow", 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 0.5F);
    }

    private void KickAttackHitTarget(Entity var1)
    {
        DamageSource var2 = DamageSource.causeMobDamage(this);

        if (var1.attackEntityFrom(var2, 7))
        {
            if (this.isBurning() && this.rand.nextFloat() < 0.6F)
            {
                var1.setFire(4);
            }

            this.FlingAwayFromEntity(var1, 1.0D);
        }
    }
    
    /**
     * Returns the texture's file path as a String.
     */
    public String getTexture()
    {
        if (this.getWearingBreedingHarness())
        {
            return "/btwmodtex/fc_mr_cow.png";
        }
        else
        {
            int var1 = this.GetHungerLevel();
            return var1 == 1 ? "/btwmodtex/fcCowFamished.png" : (var1 == 2 ? "/btwmodtex/fcCowStarving.png" : super.getTexture());
        }
    }
}
