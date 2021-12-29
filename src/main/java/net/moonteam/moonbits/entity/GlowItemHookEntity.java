package net.moonteam.moonbits.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.moonteam.moonbits.MBEntities;
import net.moonteam.moonbits.MBItems;

public class GlowItemHookEntity extends ItemHookEntity {
    public GlowItemHookEntity(EntityType<? extends ItemHookEntity> entityType, World world) {
        super(entityType, world);
    }

    public GlowItemHookEntity(World world, BlockPos pos, Direction facing) {
        super(MBEntities.GLOW_ITEM_HOOK_ENTITY, world, pos, facing);
    }

    public SoundEvent getRemoveItemSound() {
        return SoundEvents.ENTITY_GLOW_ITEM_FRAME_REMOVE_ITEM;
    }

    public SoundEvent getBreakSound() {
        return SoundEvents.ENTITY_GLOW_ITEM_FRAME_BREAK;
    }

    public SoundEvent getPlaceSound() {
        return SoundEvents.ENTITY_GLOW_ITEM_FRAME_PLACE;
    }

    public SoundEvent getAddItemSound() {
        return SoundEvents.ENTITY_GLOW_ITEM_FRAME_ADD_ITEM;
    }

    public SoundEvent getRotateItemSound() {
        return SoundEvents.ENTITY_GLOW_ITEM_FRAME_ROTATE_ITEM;
    }

    protected ItemStack getAsItemStack() {
        return new ItemStack(MBItems.GLOW_ITEM_HOOK);
    }

    @Override
    public Packet<?> createSpawnPacket() {
        return new MBS2CSpawnPacket(this, this.getType(), this.facing.getId(), this.getDecorationBlockPos());
    }
}
