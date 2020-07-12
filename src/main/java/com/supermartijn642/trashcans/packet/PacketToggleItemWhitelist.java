package com.supermartijn642.trashcans.packet;

import com.supermartijn642.trashcans.TrashCanTile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Created 7/8/2020 by SuperMartijn642
 */
public class PacketToggleItemWhitelist extends TrashCanPacket {

    public PacketToggleItemWhitelist(BlockPos pos){
        super(pos);
    }

    public PacketToggleItemWhitelist(PacketBuffer buffer){
        super(buffer);
    }

    public static PacketToggleItemWhitelist decode(PacketBuffer buffer){
        return new PacketToggleItemWhitelist(buffer);
    }

    @Override
    protected void handle(PlayerEntity player, World world, TrashCanTile tile){
        if(tile.items){
            tile.itemFilterWhitelist = !tile.itemFilterWhitelist;
            tile.dataChanged();
        }
    }
}
