package compactskylights.common.block;

import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

public class ProtectedGlassEventHandler {

    public static void leftClickBlock(final PlayerInteractEvent.LeftClickBlock evt) {
        final var player = evt.getEntity();
        final var pos = evt.getPos();
        final var lev = evt.getLevel();

        final var state = lev.getBlockState(pos);
        if (state.getBlock() instanceof SolidSkylightGlass pwb) {
            if (!pwb.canPlayerBreak(player))
                evt.setCanceled(true);
        }
    }
}
