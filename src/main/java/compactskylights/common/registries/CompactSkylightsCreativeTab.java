package compactskylights.common.registries;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class CompactSkylightsCreativeTab {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(
                    Registries.CREATIVE_MODE_TAB,
                    "compactskylights"
            );

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> COMPACT_SKYLIGHTS =
            CREATIVE_MODE_TAB.register("main", () ->
                    CreativeModeTab.builder()

                            .title(Component.translatable(
                                    "itemGroup.compactskylights.main"
                            ))

                            .icon(() -> CompactSkylightsItem.PERSONAL_SKYLIGHT_CONVERTER
                                    .get().getDefaultInstance())

                            .displayItems((parameters, output) -> {
                                output.accept(
                                        CompactSkylightsItem.PERSONAL_SKYLIGHT_CONVERTER.get()
                                );
                            })

                            .build()
            );
}