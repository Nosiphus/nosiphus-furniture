package com.nosiphus.furniture.client.menu;

import com.nosiphus.furniture.blockentity.DishwasherBlockEntity;
import com.nosiphus.furniture.core.ModMenuTypes;
import com.nosiphus.furniture.inventory.SoapyWaterSlot;
import com.nosiphus.furniture.inventory.ToolSlot;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;

public class DishwasherMenu extends AbstractContainerMenu {

    protected final DishwasherBlockEntity blockEntity;
    private final Level level;
    private final ContainerData data;

    public DishwasherMenu(int id, Inventory inventory, FriendlyByteBuf extraData) {
        this(id, inventory, inventory.player.level.getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(7));
    }

    public DishwasherMenu(int id, Inventory inventory, BlockEntity entity, ContainerData data) {
        super(ModMenuTypes.DISHWASHER.get(), id);
        checkContainerSize(inventory, 7);
        blockEntity = (DishwasherBlockEntity) entity;
        this.level = inventory.player.level;
        this.data = data;

        this.blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(iItemHandler -> {
            this.addSlot(new SoapyWaterSlot(iItemHandler, 0, 125, 7));
            this.addSlot(new ToolSlot(iItemHandler, 1, 56, 43, 0));
            this.addSlot(new ToolSlot(iItemHandler, 2, 80, 43, 1));
            this.addSlot(new ToolSlot(iItemHandler, 3, 104, 43, 2));
            this.addSlot(new ToolSlot(iItemHandler, 4, 56, 74, 3));
            this.addSlot(new ToolSlot(iItemHandler, 5, 80, 74, 4));
            this.addSlot(new ToolSlot(iItemHandler, 6, 104, 74, 5));
        });

        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 9; ++j)
            {
                this.addSlot(new Slot(inventory, j + i * 9 + 9, j * 18 + 8, i * 18 + 146));
            }
        }

        for(int i = 0; i < 9; i++)
        {
            this.addSlot(new Slot(inventory, i, i * 18 + 8, 204));
        }

        addDataSlots(data);

    }

    @Override
    public ItemStack quickMoveStack(Player playerEntity, int index)
    {
        ItemStack clickedStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if(slot != null && slot.hasItem())
        {
            ItemStack slotStack = slot.getItem();
            clickedStack = slotStack.copy();
            if(index < this.blockEntity.getContainerSize())
            {
                if(!this.moveItemStackTo(slotStack, this.blockEntity.getContainerSize(), this.slots.size(), true))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if(!this.moveItemStackTo(slotStack, 0, this.blockEntity.getContainerSize(), false))
            {
                return ItemStack.EMPTY;
            }

            if(slotStack.isEmpty())
            {
                slot.set(ItemStack.EMPTY);
            }
            else
            {
                slot.setChanged();
            }
        }
        return clickedStack;
    }

    @Override
    public boolean stillValid(Player player) {
        return this.blockEntity.stillValid(player);
    }

}
