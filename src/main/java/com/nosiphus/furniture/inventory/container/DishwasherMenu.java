package com.nosiphus.furniture.inventory.container;

import com.mojang.datafixers.util.Pair;
import com.nosiphus.furniture.Reference;
import com.nosiphus.furniture.blockentity.DishwasherBlockEntity;
import com.nosiphus.furniture.core.ModFluids;
import com.nosiphus.furniture.core.ModMenuTypes;
import com.nosiphus.furniture.network.message.S2CMessageFluidSync;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.items.SlotItemHandler;

public class DishwasherMenu extends AbstractContainerMenu {

    public static final ResourceLocation BLOCK_ATLAS = new ResourceLocation("textures/atlas/blocks.png");
    public static final ResourceLocation EMPTY_TOOL_SLOT_PICKAXE = new ResourceLocation(Reference.MOD_ID, "item/empty_tool_slot_pickaxe");
    public static final ResourceLocation EMPTY_TOOL_SLOT_SHOVEL = new ResourceLocation(Reference.MOD_ID, "item/empty_tool_slot_shovel");
    public static final ResourceLocation EMPTY_TOOL_SLOT_SWORD = new ResourceLocation(Reference.MOD_ID, "item/empty_tool_slot_sword");
    public static final ResourceLocation EMPTY_TOOL_SLOT_AXE = new ResourceLocation(Reference.MOD_ID, "item/empty_tool_slot_axe");
    public static final ResourceLocation EMPTY_TOOL_SLOT_HOE = new ResourceLocation(Reference.MOD_ID, "item/empty_tool_slot_hoe");
    public static final ResourceLocation EMPTY_ARMOR_SLOT_SHIELD = new ResourceLocation("item/empty_armor_slot_shield");
    public static final ResourceLocation EMPTY_TOOL_SLOT_BUCKET = new ResourceLocation(Reference.MOD_ID, "item/empty_tool_slot_bucket");

    protected final DishwasherBlockEntity blockEntity;
    private final Level level;
    private FluidStack fluidStack;

    public DishwasherMenu(int id, Inventory inventory, FriendlyByteBuf extraData) {
        this(id, inventory, inventory.player.level().getBlockEntity(extraData.readBlockPos()));
    }

    public DishwasherMenu(int id, Inventory inventory, BlockEntity entity) {
        super(ModMenuTypes.DISHWASHER.get(), id);
        checkContainerSize(inventory, 8);
        blockEntity = (DishwasherBlockEntity) entity;
        this.level = inventory.player.level();
        this.fluidStack = blockEntity.getFluidStack();

        this.blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(iItemHandler -> {
            this.addSlot(new SlotItemHandler(iItemHandler, 0, 56, 43) {
                public Pair<ResourceLocation, ResourceLocation> getNoItemIcon() {
                    return Pair.of(DishwasherMenu.BLOCK_ATLAS, DishwasherMenu.EMPTY_TOOL_SLOT_PICKAXE);
                }
            });
            this.addSlot(new SlotItemHandler(iItemHandler, 1, 80, 43) {
                public Pair<ResourceLocation, ResourceLocation> getNoItemIcon() {
                    return Pair.of(DishwasherMenu.BLOCK_ATLAS, DishwasherMenu.EMPTY_TOOL_SLOT_SHOVEL);
                }
            });
            this.addSlot(new SlotItemHandler(iItemHandler, 2, 104, 43) {
                public Pair<ResourceLocation, ResourceLocation> getNoItemIcon() {
                    return Pair.of(DishwasherMenu.BLOCK_ATLAS, DishwasherMenu.EMPTY_TOOL_SLOT_SWORD);
                }
            });
            this.addSlot(new SlotItemHandler(iItemHandler, 3, 56, 74) {
                public Pair<ResourceLocation, ResourceLocation> getNoItemIcon() {
                    return Pair.of(DishwasherMenu.BLOCK_ATLAS, DishwasherMenu.EMPTY_TOOL_SLOT_AXE);
                }
            });
            this.addSlot(new SlotItemHandler(iItemHandler, 4, 80, 74) {
                public Pair<ResourceLocation, ResourceLocation> getNoItemIcon() {
                    return Pair.of(DishwasherMenu.BLOCK_ATLAS, DishwasherMenu.EMPTY_TOOL_SLOT_HOE);
                }
            });
            this.addSlot(new SlotItemHandler(iItemHandler, 5, 104, 74) {
                public Pair<ResourceLocation, ResourceLocation> getNoItemIcon() {
                    return Pair.of(DishwasherMenu.BLOCK_ATLAS, DishwasherMenu.EMPTY_ARMOR_SLOT_SHIELD);
                }
            });
            this.addSlot(new SlotItemHandler(iItemHandler, 6, 125, 7));

            this.addSlot(new SlotItemHandler(iItemHandler, 6, 125, 7) {
                public Pair<ResourceLocation, ResourceLocation> getNoItemIcon() {
                    return Pair.of(DishwasherMenu.BLOCK_ATLAS, DishwasherMenu.EMPTY_TOOL_SLOT_BUCKET);
                }
            });
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

    }

    public void setFluid(FluidStack fluidStack) {
        this.fluidStack = fluidStack;
    }

    public FluidStack getFluidStack() {
        return fluidStack;
    }

    public DishwasherBlockEntity getBlockEntity() {
        return this.blockEntity;
    }

    public int getFluidType() {
        int fluidType = 0;
        if (getFluidStack().getFluid() == ModFluids.SOAPY_WATER.get()) {
            fluidType = 185;
        } else if (getFluidStack().getFluid() == ModFluids.SUPER_SOAPY_WATER.get()) {
            fluidType = 199;
        }
        return 185;
    }

    public int getFluidRenderAmount() {
        int actualAmount = getFluidStack().getAmount();
        int actualMaxAmount = 64000;
        int tankSize = 55;
        return actualAmount * tankSize / actualMaxAmount;
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
