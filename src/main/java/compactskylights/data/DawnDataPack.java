package compactskylights.data;

import compactskylights.CompactSkylights;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.neoforged.neoforge.event.AddPackFindersEvent;

public final class DawnDataPack {

    private static final ResourceLocation PACK_LOCATION =
            ResourceLocation.fromNamespaceAndPath(
                    CompactSkylights.MODID,
                    "data/compactskylights/datapacks/compact_machines_dawn"
            );

    private DawnDataPack() {
    }

    public static void register(AddPackFindersEvent event) {

        // サーバー用データパック以外には登録しない
        if (event.getPackType() != PackType.SERVER_DATA) {
            return;
        }

        // クライアント既知パックとしては登録しない
        if (event.isTrusted()) {
            return;
        }

        event.addPackFinders(
                PACK_LOCATION,
                PackType.SERVER_DATA,
                Component.literal("CompactSkylights: Compact Machines Dawn"),
                PackSource.BUILT_IN,
                true,
                Pack.Position.TOP
        );
    }
}