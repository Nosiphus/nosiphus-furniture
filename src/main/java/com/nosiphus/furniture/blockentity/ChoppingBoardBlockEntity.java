package com.nosiphus.furniture.blockentity;

import com.mrcrayfish.furniture.util.BlockEntityUtil;
import com.mrcrayfish.furniture.util.ItemStackHelper;
import com.nosiphus.furniture.core.ModBlockEntities;
import com.nosiphus.furniture.core.ModRecipeTypes;
import com.nosiphus.furniture.recipe.ChoppingRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.Optional;

public class ChoppingBoardBlockEntity extends BlockEntity implements WorldlyContainer {

    public static final int[] ALL_SLOTS = new int[]{0};
    public static final int[] CHOPPING_SLOT = new int[]{0};

    private ItemStack food = null;

    public void setFood(ItemStack food) {
        this.food = food;
    }

    public ItemStack getFood() {
        return food;
    }

    private final NonNullList<ItemStack> choppingBoard = NonNullList.withSize(1, ItemStack.EMPTY);

    protected ChoppingBoardBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public ChoppingBoardBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.CHOPPING_BOARD.get(), pos, state);
    }

    public NonNullList<ItemStack> getChoppingBoard() {
        return this.choppingBoard;
    }

    public boolean addItem(ItemStack stack) {
        if(this.choppingBoard.get(0).isEmpty()) {
            ItemStack copy = stack.copy();
            copy.setCount(1);
            this.choppingBoard.set(0, copy);

            Level level = this.getLevel();
            if(level != null) {
                //Play Sound Code here
            }

            return true;
        }
        return false;
    }

    public void chopItem(ItemStack stack) {
        double posX = worldPosition.getX() + 0.3 + 0.4 * (1 % 2);
        double posY = worldPosition.getY() + 1.0;
        double posZ = worldPosition.getZ() + 0.3 + 0.4 * (1 / 2);

        ItemEntity entity = new ItemEntity(this.level, posX, posY + 0.1, posZ, stack.copy());
        this.level.addFreshEntity(entity);
        this.choppingBoard.set(0, ItemStack.EMPTY);

        CompoundTag compoundTag = new CompoundTag();
        this.writeItem(compoundTag);
        BlockEntityUtil.sendUpdatePacket(this, compoundTag);
    }

    public boolean chopFood() {
        if(food != null) {
            Optional<ChoppingRecipe> optional = findMatchingRecipe(food);
            if(optional.isPresent()) {
                if(!level.isClientSide()) {
                    ItemEntity entity = new ItemEntity(level, getBlockPos().getX() + 0.5, getBlockPos().getY() + 0.2, getBlockPos().getZ() + 0.5, optional.get().getResultItem().copy());
                    this.level.addFreshEntity(entity);
                    //sound
                }
                setFood(null);
                CompoundTag compoundTag = new CompoundTag();
                this.writeItem(compoundTag);
                BlockEntityUtil.sendUpdatePacket(this, compoundTag);
                return true;
            }
        }
        return false;
    }

    public void removeItem() {
        if(!this.choppingBoard.get(0).isEmpty()) {
            double posX = worldPosition.getX() + 0.3 + 0.4 * (1 % 2);
            double posY = worldPosition.getY() + 1.0;
            double posZ = worldPosition.getZ() + 0.3 + 0.4 * (1 / 2);

            ItemEntity entity = new ItemEntity(this.level, posX, posY + 0.1, posZ, this.choppingBoard.get(0).copy());
            this.level.addFreshEntity(entity);

            this.choppingBoard.set(0, ItemStack.EMPTY);

            CompoundTag compoundTag = new CompoundTag();
            this.writeItem(compoundTag);
            BlockEntityUtil.sendUpdatePacket(this, compoundTag);
        }
    }

    public Optional<ChoppingRecipe> findMatchingRecipe(ItemStack input) {
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
        CompoundTag compoundTag = new CompoundTag();
        this.writeItem(compoundTag);
        BlockEntityUtil.sendUpdatePacket(this, compoundTag);
        return result;
    }

    @Override
    public ItemStack removeItemNoUpdate(int index) {
        return ContainerHelper.takeItem(this.choppingBoard, index);
    }

    @Override
    public void setItem(int index, ItemStack stack) {
        NonNullList<ItemStack> inventory = this.choppingBoard;
        Optional<ChoppingRecipe> optional = this.level.getRecipeManager().getRecipeFor(ModRecipeTypes.CHOPPING.get(), new SimpleContainer(stack), this.level);
        if(optional.isPresent()) {
            CompoundTag compoundTag = new CompoundTag();
            this.writeItem(compoundTag);
            BlockEntityUtil.sendUpdatePacket(this, compoundTag);
        }

        inventory.set(index, stack);
        if(stack.getCount() > this.getMaxStackSize()) {
            stack.setCount(this.getMaxStackSize());
        }

        CompoundTag compoundTag = new CompoundTag();
        this.writeItem(compoundTag);
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
    public void load(CompoundTag compoundTag) {
        super.load(compoundTag);
        if(compoundTag.contains("ChoppingBoard", CompoundTag.TAG_LIST)) {
            this.choppingBoard.clear();
            ItemStackHelper.loadAllItems("ChoppingBoard", compoundTag, this.choppingBoard);
        }
    }

    @Override
    protected void saveAdditional(CompoundTag compoundTag) {
        super.saveAdditional(compoundTag);
        this.writeItem(compoundTag);
    }

    public CompoundTag writeItem(CompoundTag compoundTag) {
        ItemStackHelper.saveAllItems("ChoppingBoard", compoundTag, this.choppingBoard, true);
        return compoundTag;
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
    public boolean canPlaceItemThroughFace(int index, ItemStack stack, @Nullable Direction direction) {
        if(!this.getItem(index).isEmpty()) {
            return false;
        }
        return this.level.getRecipeManager().getRecipeFor(ModRecipeTypes.CHOPPING.get(), new SimpleContainer(stack), this.level).isPresent();
    }

    @Override
    public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
        if(direction == Direction.DOWN) {
            Optional<ChoppingRecipe> optional = this.level.getRecipeManager().getRecipeFor(ModRecipeTypes.CHOPPING.get(), new SimpleContainer(stack), this.level);
            return !optional.isPresent();
        }
        return false;
    }
}