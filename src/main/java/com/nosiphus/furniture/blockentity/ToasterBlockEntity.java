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
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.Optional;

import static com.mrcrayfish.furniture.block.FurnitureHorizontalBlock.DIRECTION;

public class ToasterBlockEntity extends BlockEntity implements WorldlyContainer {

    public static final int[] ALL_SLOTS = new int[]{0, 1};
    public static final int[] TOAST_SLOTS = new int[]{0, 1};

    private final NonNullList<ItemStack> toaster = NonNullList.withSize(2, ItemStack.EMPTY);
    private final int[] cookingTimes = new int[2];
    private final int[] cookingTotalTimes = new int[2];
    private final float[] experience = new float[2];
    private final byte[] rotations = new byte[2];

    protected ToasterBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public ToasterBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.TOASTER.get(), pos, state);
    }

    public int getDirection(BlockState state) {
        if(state.getValue(DIRECTION) == Direction.NORTH) {
            return 0;
        } else if (state.getValue(DIRECTION) == Direction.EAST) {
            return 1;
        } else if (state.getValue(DIRECTION) == Direction.SOUTH) {
            return 2;
        } else if (state.getValue(DIRECTION) == Direction.WEST) {
            return 3;
        }
        return 0;
    }

    public NonNullList<ItemStack> getToaster() {
        return this.toaster;
    }

    public byte[] getRotations()
    {
        return this.rotations;
    }

    public boolean addItem(ItemStack stack, int position, int cookTime, float experience, byte rotation) {
        if(this.toaster.get(position).isEmpty()) {
            ItemStack copy = stack.copy();
            copy.setCount(1);
            this.toaster.set(position, copy);
            this.resetPosition(position, cookTime, experience, rotation);

            return true;
        }
        return false;
    }

    private void resetPosition(int position, int cookTime, float experience, byte rotation) {
        this.cookingTimes[position] = 0;
        this.cookingTotalTimes[position] = cookTime / 2;
        this.experience[position] = 0;
        this.rotations[position] = rotation;

        CompoundTag compound = new CompoundTag();
        this.writeItems(compound);
        this.writeCookingTimes(compound);
        this.writeCookingTotalTimes(compound);
        this.writeRotations(compound);
        BlockEntityUtil.sendUpdatePacket(this, compound);
    }

    public void removeItem(int position) {
        if(!this.toaster.get(position).isEmpty()) {
            double posX = worldPosition.getX() + 0.5;
            double posY = worldPosition.getY();
            double posZ = worldPosition.getZ() + 0.5;

            ItemEntity entity = new ItemEntity(this.level, posX, posY, posZ, this.toaster.get(position).copy());
            this.level.addFreshEntity(entity);

            this.toaster.set(position, ItemStack.EMPTY);

            if(this.cookingTimes[position] == this.cookingTotalTimes[position]) {
                int amount = (int)experience[position];
                while(amount > 0) {
                    int splitAmount = ExperienceOrb.getExperienceValue(amount);
                    amount -= splitAmount;
                    this.level.addFreshEntity(new ExperienceOrb(this.level, posX, posY, posZ, splitAmount));
                }
            }

            CompoundTag compound = new CompoundTag();
            this.writeItems(compound);
            BlockEntityUtil.sendUpdatePacket(this, compound);
        }
    }

    public static void tick(Level level, BlockPos pos, BlockState state, ToasterBlockEntity blockEntity) {
        boolean canToast = blockEntity.canToast();
        if(canToast) {
            blockEntity.toastItems();
        }
    }

    private boolean canToast() {
        for(int i = 0; i < this.toaster.size(); i++) {
            if(!this.toaster.get(i).isEmpty() && this.cookingTimes[i] != this.cookingTotalTimes[i]) {
                return true;
            }
        }
        return false;
    }

    private void toastItems() {
        boolean itemsChanged = false;
        for(int i = 0; i < this.toaster.size(); i++) {
            if(!this.toaster.get(i).isEmpty()) {
                if(this.cookingTimes[i] < this.cookingTotalTimes[i]) {
                    this.cookingTimes[i]++;
                    if(this.cookingTimes[i] == this.cookingTotalTimes[i]) {
                        Optional<ToastingRecipe> optional = this.level.getRecipeManager().getRecipeFor(ModRecipeTypes.TOASTING.get(), new SimpleContainer(this.toaster.get(i)), this.level);
                        if(optional.isPresent()) {
                            this.toaster.set(i, optional.get().getResultItem(this.level.registryAccess()).copy());
                        }
                        itemsChanged = true;
                    }
                }
            }
        }
        if(itemsChanged) {
            CompoundTag compound = new CompoundTag();
            this.writeItems(compound);
            this.writeCookingTimes(compound);
            BlockEntityUtil.sendUpdatePacket(this, compound);
        }
    }

    private boolean isToasting() {
        for(int i = 0; i < this.toaster.size(); i++) {
            if(!this.toaster.get(i).isEmpty() && (this.cookingTimes[i] != this.cookingTotalTimes[i])) {
                return true;
            }
        }
        return false;
    }

    public Optional<ToastingRecipe> findMatchingRecipe(ItemStack input) {
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
            if(this.cookingTimes[index] == this.cookingTotalTimes[index]) {
                double posX = worldPosition.getX() + 0.5;
                double posY = worldPosition.getY();
                double posZ = worldPosition.getZ() + 0.5;
                int amount = (int) experience[index];
                while(amount > 0)
                {
                    int splitAmount = ExperienceOrb.getExperienceValue(amount);
                    amount -= splitAmount;
                    this.level.addFreshEntity(new ExperienceOrb(this.level, posX, posY, posZ, splitAmount));
                }
            }
        }
        CompoundTag compound = new CompoundTag();
        this.writeItems(compound);
        BlockEntityUtil.sendUpdatePacket(this, compound);
        return result;
    }

    @Override
    public ItemStack removeItemNoUpdate(int index) {
        return ContainerHelper.takeItem(this.toaster, index);
    }

    @Override
    public void setItem(int index, ItemStack stack) {
        NonNullList<ItemStack> inventory = this.toaster;
        Optional<ToastingRecipe> optional = this.level.getRecipeManager().getRecipeFor(ModRecipeTypes.TOASTING.get(), new SimpleContainer(stack), this.level);
        if(optional.isPresent()) {
            ToastingRecipe recipe = optional.get();
            this.resetPosition(index, recipe.getCookingTime(), recipe.getExperience(), (byte) 0);
        }
        inventory.set(index, stack);
        if(stack.getCount() > this.getMaxStackSize()) {
            stack.setCount(this.getMaxStackSize());
        }
        CompoundTag compound = new CompoundTag();
        this.writeItems(compound);
        BlockEntityUtil.sendUpdatePacket(this, compound);
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
    public void load(CompoundTag compound) {
        super.load(compound);
        if(compound.contains("Toaster", Tag.TAG_LIST)) {
            this.toaster.clear();
            ItemStackHelper.loadAllItems("Toaster", compound, this.toaster);
        }
        if(compound.contains("CookingTimes", Tag.TAG_INT_ARRAY))
        {
            int[] cookingTimes = compound.getIntArray("CookingTimes");
            System.arraycopy(cookingTimes, 0, this.cookingTimes, 0, Math.min(this.cookingTotalTimes.length, cookingTimes.length));
        }
        if(compound.contains("CookingTotalTimes", Tag.TAG_INT_ARRAY))
        {
            int[] cookingTimes = compound.getIntArray("CookingTotalTimes");
            System.arraycopy(cookingTimes, 0, this.cookingTotalTimes, 0, Math.min(this.cookingTotalTimes.length, cookingTimes.length));
        }
        if(compound.contains("Experience", Tag.TAG_INT_ARRAY))
        {
            int[] experience = compound.getIntArray("Experience");
            for(int i = 0; i < Math.min(this.experience.length, experience.length); i++)
            {
                this.experience[i] = Float.intBitsToFloat(experience[i]);
            }
        }
        if(compound.contains("Rotations", Tag.TAG_BYTE_ARRAY))
        {
            byte[] rotations = compound.getByteArray("Rotations");
            System.arraycopy(rotations, 0, this.rotations, 0, Math.min(this.rotations.length, rotations.length));
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag)
    {
        super.saveAdditional(tag);
        this.writeItems(tag);
        this.writeCookingTimes(tag);
        this.writeCookingTotalTimes(tag);
        this.writeExperience(tag);
        this.writeRotations(tag);
    }

    private CompoundTag writeItems(CompoundTag compound)
    {
        ItemStackHelper.saveAllItems("Toaster", compound, this.toaster, true);
        return compound;
    }

    private CompoundTag writeCookingTimes(CompoundTag compound)
    {
        compound.putIntArray("CookingTimes", this.cookingTimes);
        return compound;
    }

    private CompoundTag writeCookingTotalTimes(CompoundTag compound)
    {
        compound.putIntArray("CookingTotalTimes", this.cookingTotalTimes);
        return compound;
    }

    private CompoundTag writeExperience(CompoundTag compound)
    {
        int[] experience = new int[this.experience.length];
        for(int i = 0; i < this.experience.length; i++)
        {
            experience[i] = Float.floatToIntBits(experience[i]);
        }
        compound.putIntArray("Experience", experience);
        return compound;
    }

    private CompoundTag writeRotations(CompoundTag compound)
    {
        compound.putByteArray("Rotations", this.rotations);
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
        return side == Direction.DOWN ? TOAST_SLOTS : ALL_SLOTS;
    }

    @Override
    public boolean canPlaceItemThroughFace(int index, ItemStack stack, @Nullable Direction direction)
    {
        if(!this.getItem(index).isEmpty())
        {
            return false;
        }
        if(index >= 0)
        {
            return this.level.getRecipeManager().getRecipeFor(ModRecipeTypes.TOASTING.get(), new SimpleContainer(stack), this.level).isPresent();
        }
        return false;
    }

    @Override
    public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction)
    {
        if(direction == Direction.DOWN)
        {
            if(index >= 0)
            {
                if(this.cookingTimes[index] == this.cookingTotalTimes[index])
                {
                    Optional<ToastingRecipe> optional = this.level.getRecipeManager().getRecipeFor(ModRecipeTypes.TOASTING.get(), new SimpleContainer(stack), this.level);
                    return !optional.isPresent();
                }
            }
        }
        return false;
    }

}