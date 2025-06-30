package btw.community.sockthing.socksmobs.items.item;

import btw.entity.attribute.AttributeOperation;
import btw.entity.attribute.BTWAttributes;
import btw.entity.attribute.CustomDisplayAttributeModifier;
import btw.item.items.ArmorItemMod;
import com.google.common.collect.Multimap;
import net.minecraft.src.*;

import java.util.UUID;

public class WolfArmorItem extends Item {

    protected static final UUID[] armorAttributeUUIDs = new UUID[]{UUID.fromString("11450d09-f668-4f71-b44d-b6c69a2e568d"), UUID.fromString("c3ef4ad1-f68c-441d-bcca-0aa9b9c95bd8"), UUID.fromString("4f27b212-7fed-4cfd-b2c0-e47311d68bd2"), UUID.fromString("440a8c41-b3d9-4e86-9442-a1a60e9c3347")};
    protected static final UUID[] knockbackResistanceAttributeUUIDs = new UUID[]{UUID.fromString("422dbfc0-dcae-48d6-ab12-5e0b2d5df137"), UUID.fromString("4b4d22d3-5a13-455c-ab5d-beb3a0092655"), UUID.fromString("31d0509e-7851-4052-b818-01cec0780c67"), UUID.fromString("5cd9e587-4b8d-44d1-80de-c9b451330838")};
    protected static final UUID[] armorWeightAttributeUUIDs = new UUID[]{UUID.fromString("0bc794a4-54c8-4349-a06f-d7d99d70d5ca"), UUID.fromString("10d83c15-6806-440e-8504-5a374b296080"), UUID.fromString("e0a91b35-2681-4aaf-8b46-f72c218a4463"), UUID.fromString("fa366152-3668-4693-8ce5-5e2e8d2b4e68")};
    protected static final UUID[] hungerCostAttributeUUIDs = new UUID[]{UUID.fromString("cfde7e5c-9ccf-4aef-abd4-152fde3d0c1a"), UUID.fromString("fff6ca20-9b0e-4eec-a093-f1fb7cbf241e"), UUID.fromString("807efd32-d327-4896-9235-ce70c596f0ea"), UUID.fromString("0ea5e15d-0a01-4d38-8c07-dc2d6abe2561")};

    //copied and modified from horse {0, 5, 7, 11} //none, iron, gold, diamond
    public static final int[] armorValues = new int[]{0, 3, 4, 5, 11, 20}; //none, wool, leather, iron, gold, diamond, steel

    public static final int WOOL = 1;
    public static final int LEATHER = 2;
    public static final int IRON = 3;
    public static final int GOLD = 4;
    public static final int DIAMOND = 5;
    public static final int STEEL = 6;

    public final int armorType;
    protected final int armorWeight;
    protected final double knockbackResistance;
    public int damageReduceAmount;
    public static int armorMaterial;

    public WolfArmorItem(int iItemID, EnumArmorMaterial enumArmorMaterial, int armorMaterial, int armorType, int weight, double knockbackResistance) {
        super(iItemID);
        this.setMaxStackSize(1);
        this.setMaxDamage(enumArmorMaterial.getDurability(armorType));
        this.setMaxDamage(this.getMaxDamage() >> 2); //wool
//        this.setMaxDamage(this.getMaxDamage() << 1); //tanned leather
        this.setInfernalMaxEnchantmentCost(10);
        this.setInfernalMaxNumEnchants(2);
        this.setBuoyant();
        this.setIncineratedInCrucible();
        this.setCreativeTab(CreativeTabs.tabCombat);

        this.armorType = armorType;
        this.armorWeight = weight;
        this.knockbackResistance = knockbackResistance;
        this.damageReduceAmount = enumArmorMaterial.getDamageReductionAmount(armorType);
        this.armorMaterial = armorMaterial;
    }

    @Override
    public Multimap getItemAttributeModifiers() {
        Multimap attributeModifiers = super.getItemAttributeModifiers();
        if (this.armorWeight > 0) {
            double hungerCostMultiplierBase = (double)this.armorWeight / 44.0;
            double hungerCostMultiplier = Math.round(hungerCostMultiplierBase * 100.0);
            attributeModifiers.put(BTWAttributes.hungerCost.getAttributeUnlocalizedName(), new CustomDisplayAttributeModifier(hungerCostAttributeUUIDs[this.armorType], "Hunger Cost", hungerCostMultiplier /= 100.0, AttributeOperation.ADDITIVE, modifier -> {
                double magnitude = Math.abs(modifier.getAmount());
                String baseName = "attribute.name." + BTWAttributes.hungerCost.getAttributeUnlocalizedName();
                if (magnitude < 0.08) {
                    return baseName + ".tiny";
                }
                if (magnitude < 0.16) {
                    return baseName + ".small";
                }
                if (magnitude < 0.24) {
                    return baseName + ".medium";
                }
                return baseName + ".large";
            }));
            attributeModifiers.put(BTWAttributes.armorWeight.getAttributeUnlocalizedName(), new AttributeModifier(armorWeightAttributeUUIDs[this.armorType], "Armor Weight", this.armorWeight, AttributeOperation.ADDITIVE.value));
        }
        if (this.knockbackResistance > 0.0) {
            attributeModifiers.put(SharedMonsterAttributes.knockbackResistance.getAttributeUnlocalizedName(), new CustomDisplayAttributeModifier(knockbackResistanceAttributeUUIDs[this.armorType], "Knockback Resistance", this.knockbackResistance, AttributeOperation.ADDITIVE, modifier -> (int)(modifier.getAmount() * 100.0) + "%"));
        }
        attributeModifiers.put(BTWAttributes.armor.getAttributeUnlocalizedName(), new AttributeModifier(armorAttributeUUIDs[this.armorType], "Armor", this.damageReduceAmount, AttributeOperation.ADDITIVE.value));
        return attributeModifiers;
    }

    @Override
    public boolean shouldApplyAttributesWhenHeld() {
        return false;
    }
}

