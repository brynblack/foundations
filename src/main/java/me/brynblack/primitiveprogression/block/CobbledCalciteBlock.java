package me.brynblack.primitiveprogression.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

public class CobbledCalciteBlock extends Block {

  public CobbledCalciteBlock() {
    super(AbstractBlock.Settings.copy(Blocks.CALCITE).strength(1.25F));
  }
}
