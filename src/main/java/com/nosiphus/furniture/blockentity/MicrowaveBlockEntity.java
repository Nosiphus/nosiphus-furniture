package com.nosiphus.furniture.blockentity;

import com.nosiphus.furniture.core.ModSounds;
import com.nosiphus.furniture.inventory.container.MicrowaveMenu;
import com.nosiphus.furniture.core.ModBlockEntities;
import com.nosiphus.furniture.item.crafting.CookingRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.Random;

public class MicrowaveBlockEntity extends BlockEntity implements MenuProvider {

    private final ItemStackHandler itemHandler = new ItemStackHandler(2) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 100;

    private static Random random = new Random();
    private static int timer = 20;

    public MicrowaveBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.MICROWAVE.get(), pos, state);
        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> MicrowaveBlockEntity.this.progress;
                    case 1 -> MicrowaveBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> MicrowaveBlockEntity.this.progress = value;
                    case 1 -> MicrowaveBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    public int getContainerSize() {
        return 2;
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("container.nfm.microwave");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return new MicrowaveMenu(id, inventory, this, this.data);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        tag.put("inventory", itemHandler.serializeNBT());
        tag.putInt("microwave.progress", this.progress);
        super.saveAdditional(tag);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        itemHandler.deserializeNBT(tag.getCompound("inventory"));
        progress = tag.getInt("microwave.progress");
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, MicrowaveBlockEntity blockEntity) {
        if(level != null) {
            if (hasRecipe(blockEntity)) {
                if (level.isClientSide()) {
                    double posX = (double) pos.getX() + 0.35D + (random.nextDouble() / 3);
                    double posZ = (double) pos.getZ() + 0.35D + (random.nextDouble() / 3);
                    //particle code here
                }
                blockEntity.progress++;
                setChanged(level, pos, state);
                if (blockEntity.progress >= blockEntity.maxProgress) {
                    craftItem(blockEntity);
                    if (!level.isClientSide) {
                        level.playSound(null, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, ModSounds.BLOCK_MICROWAVE_FINISH.get(), SoundSource.BLOCKS, 0.75F, 1.0F);
                    }
                    timer = 0;
                } else {
                    if (timer == 20) {
                        timer = 0;
                    }
                    if (timer == 0) {
                        level.playSound(null, blockEntity.worldPosition.getX() + 0.5, blockEntity.worldPosition.getY(), blockEntity.worldPosition.getZ() + 0.5, ModSounds.BLOCK_MICROWAVE_RUNNING.get(), SoundSource.BLOCKS, 0.75F, 1.0F);
                    }
                    timer++;
                }

            } else {
                blockEntity.resetProgress();
                timer = 0;
                setChanged(level, pos, state);
            }
        }
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private static void craftItem(MicrowaveBlockEntity blockEntity) {
        Level level = blockEntity.level;
        SimpleContainer inventory = new SimpleContainer(blockEntity.itemHandler.getSlots());
        for (int i = 0; i < blockEntity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, blockEntity.itemHandler.getStackInSlot(i));
        }
        Optional<CookingRecipe> recipe = level.getRecipeManager().getRecipeFor(CookingRecipe.Type.INSTANCE, inventory, level);
        if(hasRecipe(blockEntity)) {
            blockEntity.itemHandler.extractItem(1, 1, false);
            blockEntity.itemHandler.setStackInSlot(1, new ItemStack(recipe.get().getResultItem(null).getItem(),
                    blockEntity.itemHandler.getStackInSlot(1).getCount() + 1));
            blockEntity.resetProgress();
        }
    }

    private static boolean hasRecipe(MicrowaveBlockEntity blockEntity) {
        Level level = blockEntity.level;
        SimpleContainer inventory = new SimpleContainer(blockEntity.itemHandler.getSlots());

        for (int i = 0; i < blockEntity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, blockEntity.itemHandler.getStackInSlot(i));
        }

        Optional<CookingRecipe> recipe = level.getRecipeManager().getRecipeFor(CookingRecipe.Type.INSTANCE, inventory, level);

        return recipe.isPresent();
    }

    public boolean stillValid(Player player) {
        if (this.level.getBlockEntity(this.worldPosition) != this) {
            return false;
        } else {
            return !(player.distanceToSqr((double)this.worldPosition.getX() + 0.5D, (double)this.worldPosition.getY() + 0.5D, (double)this.worldPosition.getZ() + 0.5D) > 64.0D);
        }
    }
}
