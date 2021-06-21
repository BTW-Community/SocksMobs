package net.minecraft.src;

/**
 * @author Sockthing (@socklessthing)
 *
 */
public class SocksMobsAddon extends AddonExt {
	private static SocksMobsAddon instance;

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
		EntityList.addMapping(SocksMobsEntitySquid.class, "GlowSquid", 202, 0x164f4e, 0x4ddaba);
		FCAddOnHandler.LogMessage(this.getName() + " Entities Loaded");
    }
	
	private void ReplaceEntityMappings()
    {
		//EntityList.replaceExistingMappingSafe(SocksMobsEntitySquid.class, "Squid");
		FCAddOnHandler.LogMessage(this.getName() + " Entities Replaced");
    }
	
	private void AddEntityRenderer()
    {
		RenderManager.AddEntityRenderer(SocksMobsEntitySquid.class, new SocksMobsRenderSquid(new FCClientModelSquid()));
		
		FCAddOnHandler.LogMessage(this.getName() + " Models Loaded");
    }
	
	private void AddBiomeSpawn()
    {	
		BiomeGenBase.ocean.getSpawnableList(EnumCreatureType.waterCreature).add(new SpawnListEntry(SocksMobsEntitySquid.class, 40, 4, 4));
		BiomeGenBase.river.getSpawnableList(EnumCreatureType.waterCreature).add(new SpawnListEntry(SocksMobsEntitySquid.class, 40, 4, 4));
		//<biome>.getSpawnableList(EnumCreatureType.<type>).add(new SpawnListEntry(<yourEntity>.class, <weight>, <maxNumber>, <minNumber>));
		//BiomeGenBase.hell.getSpawnableList(EnumCreatureType.lavaCreature).add(new SpawnListEntry(SocksMobsEntitySquidLava.class, 100, 4, 4));
    }
}