package com.nosiphus.furniture.blockentity;

import com.mrcrayfish.furniture.util.BlockEntityUtil;
import com.mrcrayfish.furniture.util.ItemStackHelper;
import com.nosiphus.furniture.core.ModBlockEntities;
import com.nosiphus.furniture.core.ModRecipeTypes;
import com.nosiphus.furniture.core.ModSounds;
import com.nosiphus.furniture.recipe.ChoppingRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

import static com.mrcrayfish.furniture.block.FurnitureHorizontalBlock.DIRECTION;

public class ChoppingBoardBlockEntity extends BlockEntity {

    public ChoppingBoardBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.CHOPPING_BOARD.get(), pos, state);
    }

    public int getDirection(BlockState state) {
        if(state.getValue(DIRECTION) == Direction.EAST) {
            return 0;
        } else if (state.getValue(DIRECTION) == Direction.SOUTH) {
            return 1;
        } else if (state.getValue(DIRECTION) == Direction.WEST) {
            return 2;
        } else if (state.getValue(DIRECTION) == Direction.NORTH) {
            return 3;
        }
        return 0;
    }

    private ItemStack food = null;
    private final NonNullList<ItemStack> foodStack = NonNullList.withSize(1, ItemStack.EMPTY);

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

    public NonNullList<ItemStack> getFoodStack() {
        return foodStack;
    }

    public boolean chopFood() {
        if(food != null) {
            Optional<ChoppingRecipe> optional = findMatchingRecipe(food);
            if(optional.isPresent()) {
                if(!level.isClientSide()) {
                    ItemEntity itemEntity = new ItemEntity(level, worldPosition.getX() + 0.5, worldPosition.getY() + 0.2, worldPosition.getZ() + 0.5, optional.get().getResultItem().copy());
                    level.addFreshEntity(itemEntity);
                    level.playSound(null, worldPosition.getX() + 0.5, worldPosition.getY(), worldPosition.getZ() + 0.5, ModSounds.BLOCK_CHOPPING_BOARD_KNIFE_CHOP.get(), SoundSource.BLOCKS, 1.0F, 0.5F);
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
            setFood(foodStack.get(0));
        }
    }

    @Override
    protected void saveAdditional(CompoundTag compoundTag) {
        super.saveAdditional(compoundTag);
        this.writeFood(compoundTag);
    }

    public CompoundTag writeFood(CompoundTag compoundTag) {
        ItemStackHelper.saveAllItems("ChoppingBoard", compoundTag, this.foodStack, true);
        return compoundTag;
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this, this::getUpdateTag);
    }

    private CompoundTag getUpdateTag(BlockEntity blockEntity) {
        return this.saveWithFullMetadata();
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        CompoundTag compoundTag = pkt.getTag();
        this.load(compoundTag);
    }

}