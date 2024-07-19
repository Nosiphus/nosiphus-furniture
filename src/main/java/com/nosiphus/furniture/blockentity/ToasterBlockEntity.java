package com.nosiphus.furniture.blockentity;

import com.mrcrayfish.furniture.util.BlockEntityUtil;
import com.mrcrayfish.furniture.util.ItemStackHelper;
import com.nosiphus.furniture.core.ModBlockEntities;
import com.nosiphus.furniture.core.ModRecipeTypes;
import com.nosiphus.furniture.item.crafting.ToastingRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
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

public class ToasterBlockEntity extends BlockEntity implements WorldlyContainer {

    public static final int[] ALL_SLOTS = new int[]{0};
    public static final int[] TOASTING_SLOT = new int[]{0};

    private final NonNullList<ItemStack> toaster = NonNullList.withSize(1, ItemStack.EMPTY);

    protected ToasterBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public ToasterBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.TOASTER.get(), pos, state);
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

    public NonNullList<ItemStack> getToaster() {
        return this.toaster;
    }

    public boolean addItem(ItemStack stack) {
        if(this.toaster.get(0).isEmpty()) {
            ItemStack copy = stack.copy();
            copy.setCount(1);
            this.toaster.set(0, copy);
            CompoundTag compoundTag = new CompoundTag();
            this.writeItems(compoundTag);
            BlockEntityUtil.sendUpdatePacket(this, compoundTag);
            return true;
        }
        return false;
    }

    public boolean toastItem() {
        if(!this.toaster.get(0).isEmpty()) {
            Optional<ToastingRecipe> optional = this.level.getRecipeManager().getRecipeFor(ModRecipeTypes.TOASTING.get(), new SimpleContainer(this.toaster.get(0)), this.level);
            if(optional.isPresent()) {
                if(!level.isClientSide()) {
                    double posX = worldPosition.getX() + 0.5;
                    double posY = worldPosition.getY() + 0.2;
                    double posZ = worldPosition.getZ() + 0.5;
                    ItemEntity itemEntity = new ItemEntity(level, posX, posY, posZ, optional.get().getResultItem(this.level.registryAccess()).copy());
                    level.addFreshEntity(itemEntity);
                }
                this.toaster.set(0, ItemStack.EMPTY);
                CompoundTag compoundTag = new CompoundTag();
                this.writeItems(compoundTag);
                BlockEntityUtil.sendUpdatePacket(this, compoundTag);
                return true;
            }
        }
        return false;
    }

    public void removeItem() {
        if(!this.toaster.get(0).isEmpty()) {
            double posX = worldPosition.getX() + 0.5;
            double posY = worldPosition.getY() + 0.2;
            double posZ = worldPosition.getZ() + 0.5;

            ItemEntity entity = new ItemEntity(this.level, posX, posY, posZ, this.toaster.get(0).copy());
            this.level.addFreshEntity(entity);

            this.toaster.set(0, ItemStack.EMPTY);

            CompoundTag compoundTag = new CompoundTag();
            this.writeItems(compoundTag);
            BlockEntityUtil.sendUpdatePacket(this, compoundTag);
        }
    }

    public Optional<ToastingRecipe> findMatchingRecipe(ItemStack input)
    {
        return this.toaster.stream().noneMatch(ItemStack::isEmpty) ? Optional.empty() : this.level.getRecipeManager().getRecipeFor(ModRecipeTypes.TOASTING.get(), new SimpleContainer(input), this.level);
    }

    @Override
    public int getContainerSize() {
        return this.toaster.size();
    }

    @Override
    public boolean isEmpty() {
        for(ItemStack stack : this.toaster) {
            if(!stack.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack getItem(int index) {
        return this.toaster.get(index);
    }

    @Override
    public ItemStack removeItem(int index, int count) {
        ItemStack result = ContainerHelper.removeItem(this.toaster, index, count);

        if(this.toaster.get(index).isEmpty()) {
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
        return ContainerHelper.takeItem(this.toaster, index);
    }

    @Override
    public void setItem(int index, ItemStack stack) {
        NonNullList<ItemStack> inventory = this.toaster;
        Optional<ToastingRecipe> optional = this.level.getRecipeManager().getRecipeFor(ModRecipeTypes.TOASTING.get(), new SimpleContainer(stack), this.level);
        if(optional.isPresent()) {
            ToastingRecipe recipe = optional.get();
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
        this.toaster.clear();
    }

    @Override
    public void load(CompoundTag compound)
    {
        super.load(compound);
        if(compound.contains("Toaster", Tag.TAG_LIST))
        {
            this.toaster.clear();
            ItemStackHelper.loadAllItems("Toaster", compound, this.toaster);
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
        ItemStackHelper.saveAllItems("Toaster", compound, this.toaster, true);
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
        return side == Direction.DOWN ? TOASTING_SLOT : ALL_SLOTS;
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