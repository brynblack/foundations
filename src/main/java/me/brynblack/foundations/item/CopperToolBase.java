package me.brynblack.foundations.item;

import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class CopperToolBase {
  public static final TagKey<Item> COPPER_TOOL_MATERIALS = TagKey.of(RegistryKeys.ITEM, Identifier.of("foundations", "copper_tool_materials"));

  public static final ToolMaterial COPPER = new ToolMaterial(
    BlockTags.INCORRECT_FOR_IRON_TOOL,
    131,
    4.0f,
    1.0f,
    5,
    COPPER_TOOL_MATERIALS
  );
}
