package me.brynblack.foundations.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

public class CobbledTuffBlock extends Block {

  public CobbledTuffBlock() {
    super(AbstractBlock.Settings.copy(Blocks.TUFF).strength(2.0F, 6.0F));
  }
}
