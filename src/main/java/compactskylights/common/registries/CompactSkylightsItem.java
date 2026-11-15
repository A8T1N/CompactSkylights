package compactskylights.common.registries;

import compactskylights.CompactSkylights;
import compactskylights.common.item.PersonalSkylightConverter;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class CompactSkylightsItem {
    private CompactSkylightsItem() {
    }

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(CompactSkylights.MODID);

    public static final DeferredItem<Item> PERSONAL_SKYLIGHT_CONVERTER =
            ITEMS.register("personal_skylight_converter", () -> new PersonalSkylightConverter(new Item.Properties()));

    public static final DeferredItem<BlockItem> SOLID_SKYLIGHT_GLASS_ITEM =
            ITEMS.registerSimpleBlockItem("solid_skylight_glass", CompactSkylightsBlock.SOLID_SKYLIGHT_GLASS);

}
