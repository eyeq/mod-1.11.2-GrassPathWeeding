package eyeq.grasspathweeding.event;

import eyeq.util.entity.player.EntityPlayerUtils;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GrassPathWeedingEventHandler {
    @SubscribeEvent
    public void onPlayerRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        EnumFacing facing = event.getFace();
        if(facing == null || facing == EnumFacing.DOWN) {
            return;
        }
        ItemStack itemStack = event.getItemStack();
        if(!(itemStack.getItem() instanceof ItemSpade)) {
            return;
        }
        EntityPlayer player = event.getEntityPlayer();
        World world = player.getEntityWorld();
        BlockPos pos = event.getPos();
        if(world.getBlockState(pos).getBlock() == Blocks.GRASS) {
            pos = pos.up();
        } else if(world.getBlockState(pos.down()).getBlock() != Blocks.GRASS) {
            return;
        }

        IBlockState state = world.getBlockState(pos);
        if(!state.getMaterial().isSolid() && state.getBlock() != Blocks.AIR) {
            world.destroyBlock(pos, true);
            EntityPlayerUtils.onItemUse(player, world, itemStack, pos.down(), facing, event.getHitVec(), event.getHand());
        }
    }
}
