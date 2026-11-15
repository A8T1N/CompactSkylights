package compactskylights.common.item;

import compactskylights.common.i18n.Translations;
import compactskylights.common.registries.CompactSkylightsBlock;
import dev.compactmods.machines.api.CompactMachines;
import dev.compactmods.machines.api.dimension.CompactDimension;
import dev.compactmods.machines.api.room.RoomInstance;
import dev.compactmods.machines.api.util.BlockSpaceUtil;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PersonalSkylightConverter extends Item {
    public PersonalSkylightConverter(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(
            ItemStack stack,
            TooltipContext context,
            List<Component> tooltips,
            TooltipFlag flags
    ) {
        super.appendHoverText(stack, context, tooltips, flags);

        if (Screen.hasShiftDown()) {
            tooltips.add(Translations.SKYLIGHT_GLASS_INFO.get());
            tooltips.add(Translations.SKYLIGHT_GLASS_RESTORE_INFO.get());
        } else {
            tooltips.add(Translations.HINT_HOLD_SHIFT.get());
        }
    }

    @NotNull
    @Override
    public InteractionResultHolder<ItemStack> use(
            Level world,
            Player player,
            InteractionHand hand
    ) {
        ItemStack stack = player.getItemInHand(hand);

        // オフハンドでの使用は禁止する
        if (hand == InteractionHand.OFF_HAND) {
            return InteractionResultHolder.fail(stack);
        }

        // プレイヤーがCompact Machinesのサーバーディメンション内にいて、
        // さらにサーバー側のプレイヤーであることを確認する。
        if (CompactDimension.isInServerDimension(player) && player instanceof ServerPlayer serverPlayer) {

            final var roomCode = CompactMachines.chunkManager()
                    .findRoomByChunk(serverPlayer.chunkPosition())
                    .orElseThrow();

            RoomInstance instance = (RoomInstance) CompactMachines.room(world.getServer(), roomCode).orElseThrow();
            ServerLevel roomLevel = instance.level();
            AABB outerBounds = instance.boundaries().outerBounds();

            AABB ceilingArea = new AABB(
                    outerBounds.minX + 1,
                    outerBounds.minY,
                    outerBounds.minZ + 1,
                    outerBounds.maxX - 1,
                    outerBounds.maxY,
                    outerBounds.maxZ - 1
            );

            AABB ceilingBounds = BlockSpaceUtil.getWallBounds(ceilingArea, Direction.UP);

            // Shift + 右クリック
            if (player.isShiftKeyDown()) {

                // 天井をガラスに置き換える。
                BlockSpaceUtil.blocksInside(ceilingBounds).forEach(pos -> {
                    roomLevel.setBlock(
                            pos,
                            CompactSkylightsBlock.SOLID_SKYLIGHT_GLASS.get().defaultBlockState(),
                            Block.UPDATE_ALL
                    );
                });

            }
            // 右クリック
            else {

                // コンパクトマシーン内の構成ブロックを取得。
                BlockPos floorPos = BlockSpaceUtil.centerWallBlockPos(outerBounds, Direction.DOWN);
                BlockState floorState = roomLevel.getBlockState(floorPos);

                // 天井を構成ブロックに置き換える。
                BlockSpaceUtil.blocksInside(ceilingBounds).forEach(pos -> {
                    roomLevel.setBlock(
                            pos,
                            floorState,
                            Block.UPDATE_ALL);
                });
            }
        }
        return InteractionResultHolder.success(stack);
    }
}
