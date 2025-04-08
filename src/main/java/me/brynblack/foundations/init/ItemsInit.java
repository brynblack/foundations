package me.brynblack.foundations.init;

import me.brynblack.foundations.armor.BaseArmor;
import me.brynblack.foundations.armor.CopperArmorBase;
import me.brynblack.foundations.item.*;
import me.brynblack.foundations.item.tool.base_tools.*;
import me.brynblack.foundations.item.tool.flint_tier.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ItemsInit {

  public static final Item PLANT_FIBER,
      LASHING,
      FIRE_STARTER,
      SLINGSHOT,
      STONE_ROCK,
      ANDESITE_STONE_ROCK,
      DIORITE_STONE_ROCK,
      GRANITE_STONE_ROCK,
      DEEPSLATE_STONE_ROCK,
      CALCITE_STONE_ROCK,
      TUFF_STONE_ROCK,
      SANDSTONE_ROCK,
      RED_SANDSTONE_ROCK,
      FLINT_SHARD,
      FLINT_KNIFE,
      FLINT_PICKAXE,
      FLINT_AXE,
      FLINT_SAW,
      COPPER_NUGGET,
      COPPER_SWORD,
      COPPER_KNIFE,
      COPPER_SHOVEL,
      COPPER_PICKAXE,
      COPPER_AXE,
      COPPER_SAW,
      COPPER_HOE,
      IRON_KNIFE,
      IRON_SAW,
      GOLDEN_KNIFE,
      GOLDEN_SAW,
      DIAMOND_KNIFE,
      DIAMOND_SAW,
      NETHERITE_KNIFE,
      NETHERITE_SAW;

  public static final ArmorMaterial COPPER_ARMOR = CopperArmorBase.COPPER;

  private static void register(String name, Item item) {
    Registry.register(Registries.ITEM, Identifier.of("foundations", name), item);
  }

  public static void init() {

    // rocks
    register("stone_rock", ItemsInit.STONE_ROCK);
    register("andesite_rock", ItemsInit.ANDESITE_STONE_ROCK);
    register("diorite_rock", ItemsInit.DIORITE_STONE_ROCK);
    register("granite_rock", ItemsInit.GRANITE_STONE_ROCK);
    register("deepslate_rock", ItemsInit.DEEPSLATE_STONE_ROCK);
    register("calcite_rock", ItemsInit.CALCITE_STONE_ROCK);
    register("tuff_rock", ItemsInit.TUFF_STONE_ROCK);
    register("sandstone_rock", ItemsInit.SANDSTONE_ROCK);
    register("red_sandstone_rock", ItemsInit.RED_SANDSTONE_ROCK);

    // stone
    register(
        "cobbled_andesite",
        new BlockItem(
            BlocksInit.COBBLED_ANDESITE,
            new Item.Settings()));
    register(
        "cobbled_diorite",
        new BlockItem(
            BlocksInit.COBBLED_DIORITE,
            new Item.Settings()));
    register(
        "cobbled_granite",
        new BlockItem(
            BlocksInit.COBBLED_GRANITE,
            new Item.Settings()));
    register(
        "cobbled_calcite",
        new BlockItem(
            BlocksInit.COBBLED_CALCITE,
            new Item.Settings()));
    register(
        "cobbled_tuff",
        new BlockItem(
            BlocksInit.COBBLED_TUFF, new Item.Settings()));

    // plant
    register("plant_fiber", PLANT_FIBER);
    register("lashing", LASHING);

    // fire starter
    register("fire_starter", FIRE_STARTER);
    register("slingshot", SLINGSHOT);

    // flint
    register("flint_shard", FLINT_SHARD);
    register("flint_pickaxe", FLINT_PICKAXE);
    register("flint_axe", FLINT_AXE);
    register("flint_knife", FLINT_KNIFE);
    register("flint_saw", FLINT_SAW);

    // copper
    register("copper_nugget", COPPER_NUGGET);
    register("copper_sword", COPPER_SWORD);
    register("copper_knife", COPPER_KNIFE);
    register("copper_shovel", COPPER_SHOVEL);
    register("copper_pickaxe", COPPER_PICKAXE);
    register("copper_axe", COPPER_AXE);
    register("copper_saw", COPPER_SAW);
    register("copper_hoe", COPPER_HOE);
    register(
        "copper_helmet",
        new BaseArmor(
            COPPER_ARMOR,
            EquipmentType.HELMET,
            new Item.Settings()));
    register(
        "copper_chestplate",
        new BaseArmor(
            COPPER_ARMOR,
            EquipmentType.CHESTPLATE,
            new Item.Settings()));
    register(
        "copper_leggings",
        new BaseArmor(
            COPPER_ARMOR,
            EquipmentType.LEGGINGS,
            new Item.Settings()));
    register(
        "copper_boots",
        new BaseArmor(
            COPPER_ARMOR,
            EquipmentType.BOOTS,
            new Item.Settings()));

    // iron
    register("iron_knife", IRON_KNIFE);
    register("iron_saw", IRON_SAW);

    // gold
    register("golden_knife", GOLDEN_KNIFE);
    register("golden_saw", GOLDEN_SAW);

    // diamond
    register("diamond_knife", DIAMOND_KNIFE);
    register("diamond_saw", DIAMOND_SAW);

    // netherite (prefix is dumb please replace it with ancient prefix)
    register("netherite_knife", NETHERITE_KNIFE);
    register("netherite_saw", NETHERITE_SAW);
  }

  static {
    // plant
    PLANT_FIBER = new Item(new Item.Settings());
    LASHING = new Item(new Item.Settings());

    // tools
    FIRE_STARTER =
        new FireStarterItem(
            new Item.Settings().maxDamage(64));
    SLINGSHOT =
        new SlingshotItem(
            new Item.Settings().maxDamage(64));

    // rocks
    STONE_ROCK =
        new BlockItem(
            BlocksInit.STONE_ROCK_BLOCK,
            new Item.Settings());
    ANDESITE_STONE_ROCK =
        new BlockItem(
            BlocksInit.ANDESITE_ROCK_BLOCK,
            new Item.Settings());
    DIORITE_STONE_ROCK =
        new BlockItem(
            BlocksInit.DIORITE_ROCK_BLOCK,
            new Item.Settings());
    GRANITE_STONE_ROCK =
        new BlockItem(
            BlocksInit.GRANITE_ROCK_BLOCK,
            new Item.Settings());
    DEEPSLATE_STONE_ROCK =
        new BlockItem(
            BlocksInit.DEEPSLATE_ROCK_BLOCK,
            new Item.Settings());
    CALCITE_STONE_ROCK =
        new BlockItem(
            BlocksInit.CALCITE_ROCK_BLOCK,
            new Item.Settings());
    TUFF_STONE_ROCK =
        new BlockItem(
            BlocksInit.TUFF_ROCK_BLOCK,
            new Item.Settings());
    SANDSTONE_ROCK =
        new BlockItem(
            BlocksInit.SANDSTONE_ROCK_BLOCK,
            new Item.Settings());
    RED_SANDSTONE_ROCK =
        new BlockItem(
            BlocksInit.RED_SANDSTONE_ROCK_BLOCK,
            new Item.Settings());

    // flint
    FLINT_SHARD = new Item(new Item.Settings());
    FLINT_KNIFE =
        new FlintKnife(
            FlintToolBase.FLINT, new Item.Settings());
    FLINT_PICKAXE =
        new FlintPickaxe(
            FlintToolBase.FLINT, new Item.Settings());
    FLINT_AXE =
        new FlintAxe(
            FlintToolBase.FLINT, new Item.Settings());
    FLINT_SAW =
        new FlintSaw(
            FlintToolBase.FLINT, new Item.Settings());

    // copper
    COPPER_NUGGET = new Item(new Item.Settings());
    COPPER_SWORD =
        new SwordBase(
            CopperToolBase.COPPER, new Item.Settings());
    COPPER_KNIFE =
        new KnifeBase(
            CopperToolBase.COPPER, new Item.Settings());
    COPPER_SHOVEL =
        new ShovelBase(
            CopperToolBase.COPPER, new Item.Settings());
    COPPER_PICKAXE =
        new PickaxeBase(
            CopperToolBase.COPPER, new Item.Settings());
    COPPER_AXE =
        new AxeBase(
            CopperToolBase.COPPER, new Item.Settings());
    COPPER_SAW =
        new SawBase(
            CopperToolBase.COPPER, new Item.Settings());
    COPPER_HOE =
        new HoeBase(
            CopperToolBase.COPPER, new Item.Settings());

    // iron
    IRON_KNIFE =
        new KnifeBase(
            ToolMaterial.IRON, new Item.Settings());
    IRON_SAW =
        new SawBase(
            ToolMaterial.IRON, new Item.Settings());

    // gold
    GOLDEN_KNIFE =
        new KnifeBase(
            ToolMaterial.GOLD, new Item.Settings());
    GOLDEN_SAW =
        new SawBase(
            ToolMaterial.GOLD, new Item.Settings());

    // diamond
    DIAMOND_KNIFE =
        new KnifeBase(
            ToolMaterial.DIAMOND, new Item.Settings());
    DIAMOND_SAW =
        new SawBase(
            ToolMaterial.DIAMOND, new Item.Settings());

    // netherite
    NETHERITE_KNIFE =
        new KnifeBase(
            ToolMaterial.NETHERITE, new Item.Settings());
    NETHERITE_SAW =
        new SawBase(
            ToolMaterial.NETHERITE, new Item.Settings());
  }
}
