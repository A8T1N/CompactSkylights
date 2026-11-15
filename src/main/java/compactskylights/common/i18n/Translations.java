package compactskylights.common.i18n;

import compactskylights.CompactSkylights;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Supplier;

public interface Translations {
    // Shiftキーを押すように案内
    Supplier<Component> HINT_HOLD_SHIFT = () ->
            Component.translatableWithFallback(
                            IDs.HINT_HOLD_SHIFT,
                            "Hold shift for details."
                    )
                    .withStyle(ChatFormatting.DARK_GRAY)
                    .withStyle(ChatFormatting.ITALIC);

    // Shiftキーを押したときに表示する説明
    Supplier<Component> SKYLIGHT_GLASS_INFO = () ->
            Component.translatableWithFallback(
                    IDs.SKYLIGHT_GLASS_INFO,
                    "Shift + Right-click: Replace the Compact Machine ceiling with skylight glass."
            ).withStyle(ChatFormatting.GRAY);

    Supplier<Component> SKYLIGHT_GLASS_RESTORE_INFO = () ->
            Component.translatableWithFallback(
                    IDs.SKYLIGHT_GLASS_RESTORE_INFO,
                    "Right-click: Restore the original Compact Machines ceiling blocks."
            ).withStyle(ChatFormatting.GRAY);

    interface IDs {

        String HINT_HOLD_SHIFT = Util.makeDescriptionId(
                "messages",
                ResourceLocation.fromNamespaceAndPath(
                        CompactSkylights.MODID,
                        "hint.hold_shift"
                )
        );

        String SKYLIGHT_GLASS_INFO = Util.makeDescriptionId(
                "messages",
                ResourceLocation.fromNamespaceAndPath(
                        CompactSkylights.MODID,
                        "skylight_glass_info"
                )
        );

        String SKYLIGHT_GLASS_RESTORE_INFO = Util.makeDescriptionId(
                "messages",
                ResourceLocation.fromNamespaceAndPath(
                        "compactskylights",
                        "skylight_glass_restore_info"
                )
        );

    }
}
