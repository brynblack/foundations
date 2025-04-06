package me.brynblack.primitiveprogression.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

public class CobblestoneBlock extends Block {

  public CobblestoneBlock() {
    super(AbstractBlock.Settings.copy(Blocks.COBBLESTONE));
  }
}
