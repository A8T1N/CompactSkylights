package compactskylights;

import compactskylights.common.block.ProtectedGlassEventHandler;
import compactskylights.common.registries.CompactSkylightsBlock;
import compactskylights.common.registries.CompactSkylightsCreativeTab;
import compactskylights.common.registries.CompactSkylightsItem;
import compactskylights.data.DawnDataPack;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

@Mod(CompactSkylights.MODID)
public class CompactSkylights {

    public static final String MODID = "compactskylights";

    public CompactSkylights(IEventBus modEventBus) {
        modEventBus.addListener(DawnDataPack::register);
        modEventBus.addListener(this::addCreative);
        CompactSkylightsItem.ITEMS.register(modEventBus);
        CompactSkylightsBlock.BLOCKS.register(modEventBus);
        CompactSkylightsCreativeTab.CREATIVE_MODE_TAB.register(modEventBus);
        registerEvents(modEventBus);
    }

    private static void registerEvents(IEventBus modEventBus) {
        NeoForge.EVENT_BUS.addListener(ProtectedGlassEventHandler::leftClickBlock);
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(CompactSkylightsItem.PERSONAL_SKYLIGHT_CONVERTER.get());
        }
    }

}
