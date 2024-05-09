package com.nosiphus.furniture.blockentity;

import com.mrcrayfish.furniture.util.BlockEntityUtil;
import com.mrcrayfish.furniture.util.ItemStackHelper;
import com.nosiphus.furniture.core.ModBlockEntities;
import com.nosiphus.furniture.core.ModRecipeTypes;
import com.nosiphus.furniture.recipe.ChoppingRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class ChoppingBoardBlockEntity extends BlockEntity {

    public ChoppingBoardBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.CHOPPING_BOARD.get(), pos, state);
    }

    private ItemStack food = null;
    private final NonNullList<ItemStack> foodStack = NonNullList.withSize(1, ItemStack.EMPTY);
    private final byte[] rotations = new byte[4];

    public void setFood(ItemStack food) {
        this.food = food;
        if (food == null) {
            this.foodStack.set(0, ItemStack.EMPTY);
        } else {
            this.foodStack.set(0, food);
        }
    }

    public ItemStack getFood() {
        return food;
    }

    public byte[] getRotations() {
        return rotations;
    }

    public boolean chopFood() {
        if(food != null) {
            Optional<ChoppingRecipe> optional = findMatchingRecipe(food);
            if(optional.isPresent()) {
                if(!level.isClientSide()) {
                    ItemEntity itemEntity = new ItemEntity(level, worldPosition.getX() + 0.5, worldPosition.getY() + 0.2, worldPosition.getZ() + 0.5, optional.get().getResultItem().copy());
                    level.addFreshEntity(itemEntity);
                    //play sound code
                }
                setFood(null);
                CompoundTag compoundTag = new CompoundTag();
                this.writeFood(compoundTag);
                BlockEntityUtil.sendUpdatePacket(this, compoundTag);
                return true;
            }
        }
        return false;
    }

    public Optional<ChoppingRecipe> findMatchingRecipe(ItemStack input) {
        return this.level.getRecipeManager().getRecipeFor(ModRecipeTypes.CHOPPING.get(), new SimpleContainer(input), this.level);
    }

    @Override
    public void load(CompoundTag compoundTag) {
        super.load(compoundTag);
        if(compoundTag.contains("ChoppingBoard", Tag.TAG_LIST)) {
            this.foodStack.clear();
            ItemStackHelper.loadAllItems("ChoppingBoard", compoundTag, this.foodStack);
            this.food = foodStack.get(0);
        }
        if(compoundTag.contains("Rotations", Tag.TAG_BYTE_ARRAY)) {
            byte[] rotations = compoundTag.getByteArray("Rotations");
            System.arraycopy(rotations, 0, this.rotations, 0, Math.min(this.rotations.length, rotations.length));
        }
    }

    @Override
    protected void saveAdditional(CompoundTag compoundTag) {
        super.saveAdditional(compoundTag);
        this.writeFood(compoundTag);
        this.writeRotations(compoundTag);
    }

    public CompoundTag writeFood(CompoundTag compoundTag) {
        ItemStackHelper.saveAllItems("ChoppingBoard", compoundTag, this.foodStack, true);
        return compoundTag;
    }

    private CompoundTag writeRotations(CompoundTag compoundTag) {
        compoundTag.putByteArray("Rotations", this.rotations);
        return compoundTag;
    }

    @Override
    public CompoundTag getUpdateTag() {
        return this.saveWithFullMetadata();
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this, BlockEntity::getUpdateTag);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        CompoundTag compoundTag = pkt.getTag();
        this.load(compoundTag);
    }

}