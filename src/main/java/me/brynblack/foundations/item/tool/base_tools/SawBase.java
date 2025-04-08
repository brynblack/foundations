package me.brynblack.foundations.item.tool.base_tools;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class SawBase extends AxeItem {
  public static final TagKey<Block> WOODEN_MATERIAL = TagKey.of(RegistryKeys.BLOCK, Identifier.of("foundations", "wooden_material"));


  public SawBase(ToolMaterial material, Settings settings) {
    super(material, 2.0F, -3.2F, settings);
  }

  @Override
  public float getMiningSpeed(ItemStack stack, BlockState state) {
    Block block = state.getBlock();

    return block.getDefaultState().isIn(WOODEN_MATERIAL)
        ? this.getMiningSpeed(stack, state) - 2.0F
        : super.getMiningSpeed(stack, state) - 2.0F;
  }
}
