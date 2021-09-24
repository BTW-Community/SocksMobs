package net.minecraft.src;

public class SocksMobsDefs {
	//assign ID number
	private static int
		id_cauldron = 4000;
		
		//end 4096
	private static int
		id_glowInk = 31000,
		id_glider = 31001,
		id_bugnet = 31002,
		id_cauldronItem = 31003;
		//end 32000
	

	public static Item glowInk;
	public static Item glider;
	public static Item bugnet;

	public static Block cauldron;

	//definitions
	public static void addDefinitions() {		
		cauldron = new SocksMobsBlockCauldron(id_cauldron);
		Item.itemsList[cauldron.blockID] = new ItemBlock(cauldron.blockID - 256);	
		
		bugnet = new SocksMobsItemBugnet(id_bugnet - 256);
		
		glowInk = new SocksMobsItemGlowInk(id_glowInk - 256);
		
		glider = new SocksMobsItemGlider(id_glider - 256);

	}

} 