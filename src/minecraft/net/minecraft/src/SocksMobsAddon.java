package net.minecraft.src;

import java.util.List;

/**
 * @author Sockthing (@socklessthing)
 *
 */
public class SocksMobsAddon extends AddonExt {
	private static SocksMobsAddon instance;

	public static Item minecartDispenser = (new FCItemMinecart(3000, 6)).setUnlocalizedName("minecartDispenser");
	
	public SocksMobsAddon() {
		super("Sock's Mobs Addon", " 0.0.0", "SocksMobs");
	}

	public static SocksMobsAddon getInstance() {
		if (instance == null) {
			instance = new SocksMobsAddon();
		}

		return instance;
	}

	@Override
	public void Initialize() {
		
		AddEntityMappings();
		ReplaceEntityMappings();
		AddEntityRenderer();	
		AddBiomeSpawn();
		SocksMobsDefs.addDefinitions(); //add blocks and items
	}
	
	public String GetLanguageFilePrefix()
	{
		return "SocksMobs";
	}
	
	private void AddEntityMappings()
    {
		//Dispenser Cart
		EntityList.addMapping(SocksMobsEntityMinecartDispenser.class, "MinecartDispenser", 208);
		
		//Glow Squid
		EntityList.addMapping(SocksMobsEntitySquid.class, "GlowSquid", 201, 0x164f4e, 0x4ddaba);
		
		//Fox
		EntityList.addMapping(SocksMobsEntityFox.class, "Fox", 202, 0xeeeae0, 0xeb8931);
		
		//Brown Mooshroom
		EntityList.addMapping(SocksMobsEntityMooshroom.class, "BrownMooshroom", 203, 0x164f4e, 0x4ddaba); //TODO Add egg color.
		
		//Goat
		EntityList.addMapping(SocksMobsEntityGoat.class, "Goat", 204, 0xf0efec, 0x857261); //eggID, eggcolor1, eggcolor2
		EntityList.addMapping(SocksMobsEntityGoatPossessed.class, "GoatPossessed", 205, 0xfff, 0xaaa);
		
		EntityList.addMapping(SocksMobsEntityBee.class, "Bee", 206, 0x121212, 0xaaaa00); //TODO Add egg color.
		
		//EntityList.addMapping(SocksMobsEntityFireFly.class, "FireFly", 204, 0x373737, 0xcaff1b);
		//EntityList.addMapping(SocksMobsEntityButterfly.class, "Butterfly", 205, 0x1214aa, 0xff031a); //TODO Add egg color.
		
		EntityList.addMapping(SocksMobsEntityLlamaContainer.class, "Llama", 207, 0xaabbcc, 0x112233); //TODO Add egg color.
		
		EntityList.addMapping(SocksMobsEntityGolem.class, "MiniGolem", 208, 0xffffff, 0x000000);
		
		FCAddOnHandler.LogMessage(this.getName() + " Entities Loaded");
    }
	
	private void ReplaceEntityMappings()
    {
		//EntityList.replaceExistingMappingSafe(SocksMobsEntitySquid.class, "Squid"); replacing doesn't properly work, or I don't know how to use it
		FCAddOnHandler.LogMessage(this.getName() + " Entities Replaced");
    }
	
	private void AddEntityRenderer()
    {
		//Dispenser Minecart
		RenderManager.AddEntityRenderer(SocksMobsEntityMinecartDispenser.class, new RenderMinecart());
		
		//Glow Squid
		RenderManager.AddEntityRenderer(SocksMobsEntitySquid.class, new SocksMobsRenderSquid(new FCClientModelSquid()));
		
		//Fox
		RenderManager.AddEntityRenderer(SocksMobsEntityFox.class, new SocksMobsRenderFox(new SocksMobsModelFox(), new SocksMobsModelFox(), 0.7F));
		
		//Brown Mooshroom
		RenderManager.AddEntityRenderer(SocksMobsEntityMooshroom.class, new SocksMobsRenderMooshroom(new ModelCow(), 0.7F));
		
		RenderManager.AddEntityRenderer(SocksMobsEntityGolem.class, new SocksMobsRenderGolem(new SocksMobsModelGolem(), 0.4F));
		
		//Goat
		RenderManager.AddEntityRenderer(SocksMobsEntityGoat.class, new SocksMobsRenderGoat(new SocksMobsModelGoat(), 0.7F));
		RenderManager.AddEntityRenderer(SocksMobsEntityGoatPossessed.class, new SocksMobsRenderGoatPossessed(new SocksMobsModelGoatPossessed(), 1.0F));
		
		//RenderManager.AddEntityRenderer(SocksMobsEntityFireFly.class, new SocksMobsRenderFireFly(new SocksMobsModelFireFly()));
		//RenderManager.AddEntityRenderer(SocksMobsEntityButterfly.class, new SocksMobsRenderButterfly());
		
		RenderManager.AddEntityRenderer(SocksMobsEntityBee.class, new SocksMobsRenderBee(new SocksMobsModelBee(), new SocksMobsModelBee(), 0.7F));
		
		RenderManager.AddEntityRenderer(SocksMobsEntityLlamaContainer.class, new SocksMobsRenderLlama(new SocksMobsModelLlama(), new SocksMobsModelLlama(), 0.7F));

		FCAddOnHandler.LogMessage(this.getName() + " Models Loaded");
    }
	
	private void AddBiomeSpawn()
    {	
		//Glow Squid
		BiomeGenBase.ocean.getSpawnableList(EnumCreatureType.waterCreature).add(new SpawnListEntry(SocksMobsEntitySquid.class, 10, 4, 4)); //squids have 10, 4, 4
		BiomeGenBase.river.getSpawnableList(EnumCreatureType.waterCreature).add(new SpawnListEntry(SocksMobsEntitySquid.class, 10, 4, 4));
		
		//Fox
		BiomeGenBase.taiga.getSpawnableList(EnumCreatureType.creature).add(new SpawnListEntry(SocksMobsEntityFox.class, 4, 1, 3)); //other animals are 8-10. 4, 4
		BiomeGenBase.taigaHills.getSpawnableList(EnumCreatureType.creature).add(new SpawnListEntry(SocksMobsEntityFox.class, 4, 1, 3));
		
		//Brown Mooshroom
		BiomeGenMushroomIsland.mushroomIsland.getSpawnableList(EnumCreatureType.creature).add(new SpawnListEntry(SocksMobsEntityMooshroom.class,  4, 4, 8)); //red has 8, 4, 8
		
		//Goat
		BiomeGenBase[] biomeList = BiomeGenBase.biomeList;
		
		for (int i = 0; i < biomeList.length; i++) {
			
			if (biomeList[i] != null)
            {
				biomeList[i].getSpawnableList(EnumCreatureType.creature).add( new SpawnListEntry( SocksMobsEntityGoat.class, 10, 4, 4 ) );
            }
		}
		
		
		//BiomeGenBase.swampland.getSpawnableList(EnumCreatureType.ambient).add((new SpawnListEntry(SocksMobsEntityFireFly.class, 50, 2, 8)));
		//<biome>.getSpawnableList(EnumCreatureType.<type>).add(new SpawnListEntry(<yourEntity>.class, <weight>, <maxNumber>, <minNumber>));
		//BiomeGenBase.hell.getSpawnableList(EnumCreatureType.lavaCreature).add(new SpawnListEntry(SocksMobsEntitySquidLava.class, 100, 4, 4));
    }

}