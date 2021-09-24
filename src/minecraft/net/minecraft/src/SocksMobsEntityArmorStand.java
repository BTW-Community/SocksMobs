package net.minecraft.src;

public class SocksMobsEntityArmorStand extends EntityMob {

	public SocksMobsEntityArmorStand(World world) {
		super(world);
		this.texture = "/socksmobs/armor_stand.png";
	}

	@Override
	protected void entityInit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getMaxHealth() {
		// TODO Auto-generated method stub
		return 99;
	}


}
