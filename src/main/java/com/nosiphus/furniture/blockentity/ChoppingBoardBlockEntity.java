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
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.Optional;

import static com.mrcrayfish.furniture.block.FurnitureHorizontalBlock.DIRECTION;

public class ChoppingBoardBlockEntity extends BlockEntity implements WorldlyContainer {

    public static final int[] ALL_SLOTS = new int[]{0};
    public static final int[] CHOPPING_SLOT = new int[]{0};

    private final NonNullList<ItemStack> choppingBoard = NonNullList.withSize(1, ItemStack.EMPTY);

    protected ChoppingBoardBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

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

    public NonNullList<ItemStack> getChoppingBoard() {
        return this.choppingBoard;
    }

    public boolean addItem(ItemStack stack) {
        if(this.choppingBoard.get(0).isEmpty()) {
            ItemStack copy = stack.copy();
            copy.setCount(1);
            this.choppingBoard.set(0, copy);
            CompoundTag compoundTag = new CompoundTag();
            this.writeItems(compoundTag);
            BlockEntityUtil.sendUpdatePacket(this, compoundTag);
            return true;
        }
        return false;
    }

    public boolean chopItem() {
        if(!this.choppingBoard.get(0).isEmpty()) {
            Optional<ChoppingRecipe> optional = this.level.getRecipeManager().getRecipeFor(ModRecipeTypes.CHOPPING.get(), new SimpleContainer(this.choppingBoard.get(0)), this.level);
            if(optional.isPresent()) {
                if(!level.isClientSide()) {
                    double posX = worldPosition.getX() + 0.5;
                    double posY = worldPosition.getY() + 0.2;
                    double posZ = worldPosition.getZ() + 0.5;
                    ItemEntity itemEntity = new ItemEntity(level, posX, posY, posZ, optional.get().getResultItem(this.level.registryAccess()).copy());
                    level.addFreshEntity(itemEntity);
                    level.playSound(null, posX, posY, posZ, ModSounds.BLOCK_CHOPPING_BOARD_KNIFE_CHOP.get(), SoundSource.BLOCKS, 1.0F, 0.5F);
                }
                this.choppingBoard.set(0, ItemStack.EMPTY);
                CompoundTag compoundTag = new CompoundTag();
                this.writeItems(compoundTag);
                BlockEntityUtil.sendUpdatePacket(this, compoundTag);
                return true;
            }
        }
        return false;
    }

    public void removeItem() {
        if(!this.choppingBoard.get(0).isEmpty()) {
            double posX = worldPosition.getX() + 0.5;
            double posY = worldPosition.getY() + 0.2;
            double posZ = worldPosition.getZ() + 0.5;

            ItemEntity entity = new ItemEntity(this.level, posX, posY, posZ, this.choppingBoard.get(0).copy());
            this.level.addFreshEntity(entity);

            this.choppingBoard.set(0, ItemStack.EMPTY);

            CompoundTag compoundTag = new CompoundTag();
            this.writeItems(compoundTag);
            BlockEntityUtil.sendUpdatePacket(this, compoundTag);
        }
    }

    public Optional<ChoppingRecipe> findMatchingRecipe(ItemStack input)
    {
        return this.choppingBoard.stream().noneMatch(ItemStack::isEmpty) ? Optional.empty() : this.level.getRecipeManager().getRecipeFor(ModRecipeTypes.CHOPPING.get(), new SimpleContainer(input), this.level);
    }

    @Override
    public int getContainerSize() {
        return this.choppingBoard.size();
    }

    @Override
    public boolean isEmpty() {
        for(ItemStack stack : this.choppingBoard) {
            if(!stack.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack getItem(int index) {
        return this.choppingBoard.get(index);
    }

    @Override
    public ItemStack removeItem(int index, int count) {
        ItemStack result = ContainerHelper.removeItem(this.choppingBoard, index, count);

        if(this.choppingBoard.get(index).isEmpty()) {
            double posX = worldPosition.getX() + 0.5;
            double posY = worldPosition.getY() + 0.2;
            double posZ = worldPosition.getZ() + 0.5;
        }

        CompoundTag compoundTag = new CompoundTag();
        this.writeItems(compoundTag);
        BlockEntityUtil.sendUpdatePacket(this, compoundTag);

        return result;
    }

    @Override
    public ItemStack removeItemNoUpdate(int index)
    {
        return ContainerHelper.takeItem(this.choppingBoard, index);
    }

    @Override
    public void setItem(int index, ItemStack stack) {
        NonNullList<ItemStack> inventory = this.choppingBoard;
        Optional<ChoppingRecipe> optional = this.level.getRecipeManager().getRecipeFor(ModRecipeTypes.CHOPPING.get(), new SimpleContainer(stack), this.level);
        if(optional.isPresent()) {
            ChoppingRecipe recipe = optional.get();
            CompoundTag compoundTag = new CompoundTag();
            this.writeItems(compoundTag);
            BlockEntityUtil.sendUpdatePacket(this, compoundTag);
        }
        inventory.set(index, stack);
        if(stack.getCount() > this.getMaxStackSize()) {
            stack.setCount(this.getMaxStackSize());
        }

        CompoundTag compoundTag = new CompoundTag();
        this.writeItems(compoundTag);
        BlockEntityUtil.sendUpdatePacket(this, compoundTag);
    }

    @Override
    public int getMaxStackSize()
    {
        return 1;
    }

    @Override
    public void clearContent()
    {
        this.choppingBoard.clear();
    }

    @Override
    public void load(CompoundTag compound)
    {
        super.load(compound);
        if(compound.contains("ChoppingBoard", Tag.TAG_LIST))
        {
            this.choppingBoard.clear();
            ItemStackHelper.loadAllItems("ChoppingBoard", compound, this.choppingBoard);
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag)
    {
        super.saveAdditional(tag);
        this.writeItems(tag);
    }

    private CompoundTag writeItems(CompoundTag compound)
    {
        ItemStackHelper.saveAllItems("ChoppingBoard", compound, this.choppingBoard, true);
        return compound;
    }

    @Override
    public CompoundTag getUpdateTag()
    {
        return this.saveWithFullMetadata();
    }

    @Nullable
    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket()
    {
        return ClientboundBlockEntityDataPacket.create(this, BlockEntity::getUpdateTag);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt)
    {
        CompoundTag compound = pkt.getTag();
        this.load(compound);
    }

    @Override
    public boolean stillValid(Player player)
    {
        return this.level.getBlockEntity(this.worldPosition) == this && player.distanceToSqr(this.worldPosition.getX() + 0.5, this.worldPosition.getY() + 0.5, this.worldPosition.getZ() + 0.5) <= 64;
    }

    @Override
    public int[] getSlotsForFace(Direction side)
    {
        return side == Direction.DOWN ? CHOPPING_SLOT : ALL_SLOTS;
    }

    @Override
    public boolean canPlaceItemThroughFace(int index, ItemStack stack, @Nullable Direction direction)
    {
        return false;
    }

    @Override
    public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction)
    {
        return false;
    }

}