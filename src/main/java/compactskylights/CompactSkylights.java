package compactskylights;

import compactskylights.data.DawnDataPack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

@Mod(CompactSkylights.MODID)
public class CompactSkylights {

    public static final String MODID = "compactskylights";

    public CompactSkylights(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(DawnDataPack::register);
    }
}
