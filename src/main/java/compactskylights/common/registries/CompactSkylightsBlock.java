package compactskylights.common.registries;

import compactskylights.common.block.SolidSkylightGlass;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class CompactSkylightsBlock {

    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks("compactskylights");

    public static final DeferredBlock<SolidSkylightGlass> SOLID_SKYLIGHT_GLASS =
            BLOCKS.registerBlock(
                    "solid_skylight_glass",
                    SolidSkylightGlass::new,
                    BlockBehaviour.Properties.of()
                            .strength(-1.0F, 3600000.8F)
                            .sound(SoundType.GLASS)
                            .noOcclusion()
                            .lightLevel((state) -> 15)
            );
}
