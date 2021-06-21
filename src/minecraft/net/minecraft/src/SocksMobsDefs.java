package net.minecraft.src;

public class SocksMobsDefs {
	//assign ID number
	//private static int
		//id_goatSkull = 3999;
		//end 4096
	private static int
		id_glowInk = 31000;
		//end 32000
	

	public static Item glowInk;

	//definitions
	public static void addDefinitions() {
		//goatSkull = new SocksMobsBlockGoatSkull(id_goatSkull, true);
		//Item.itemsList[goatSkull.blockID] = new ItemBlock(goatSkull.blockID - 256);			
				
		glowInk = new SocksMobsItemGlowInk(id_glowInk - 256);

	}

} 