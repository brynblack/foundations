package me.brynblack.foundations.item;

import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;

public class FlintToolBase {
  public static final ToolMaterial FLINT = new ToolMaterial(
    BlockTags.INCORRECT_FOR_WOODEN_TOOL,
    20,
    2.0f,
    0.0f,
    0,
    ItemTags.STONE_TOOL_MATERIALS
  );
}
