package com.nosiphus.furniture.blockentity;

import com.mrcrayfish.furniture.util.BlockEntityUtil;
import com.nosiphus.furniture.core.ModBlockEntities;
import com.nosiphus.furniture.core.ModRecipeTypes;
import com.nosiphus.furniture.recipe.ChoppingRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Optional;

public class ChoppingBoardBlockEntity extends BlockEntity {

    public ChoppingBoardBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.CHOPPING_BOARD.get(), pos, state);
    }

    private ItemStack food = null;

    public void setFood(ItemStack food) {
        this.food = food;
    }

    public ItemStack getFood() {
        return food;
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
                BlockEntityUtil.sendUpdatePacket(this);
                return true;
            }
        }
        return false;
    }

    public Optional<ChoppingRecipe> findMatchingRecipe(ItemStack input) {
        return this.level.getRecipeManager().getRecipeFor(ModRecipeTypes.CHOPPING.get(), new SimpleContainer(input), this.level);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        if(tag.contains("Food", 10))
        {
            CompoundTag nbt = tag.getCompound("Food");
            food = new ItemStack((ItemLike) nbt);
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        CompoundTag nbt = new CompoundTag();
        if(food != null) {
            food.save(nbt);
            tag.put("Food", nbt);
        }
        super.saveAdditional(tag);
    }

}