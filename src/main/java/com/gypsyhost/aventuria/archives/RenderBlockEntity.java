/*
package com.gypsyhost.aventuria.custom.block.entity;

import com.gypsyhost.aventuria.custom.item.tool.ToolMiningProperties;
import com.gypsyhost.aventuria.custom.item.upgradecards.Upgrade;
import com.gypsyhost.aventuria.custom.item.upgradecards.UpgradeTools;
import com.gypsyhost.aventuria.custom.util.ServerTickHandler;
import com.gypsyhost.aventuria.custom.util.SpecialBlockActions;
import com.gypsyhost.aventuria.registry.ModBlockEntities;
import com.gypsyhost.aventuria.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.Tag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.world.BlockEvent;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

public class RenderBlockEntity extends BlockEntity {
    private final Random rand = new Random();
    private BlockState renderBlock;
    private int priorDurability = 9999;
    private int clientPrevDurability;
    private int clientDurability;
    private int durability;
    private UUID playerUUID;
    private int originalDurability;
    private int ticksSinceMine = 0;
    private List<Upgrade> toolUpgrades;
    private boolean packetReceived = false;
    private int totalAge;
    private ToolMiningProperties.BreakTypes breakType;
    private boolean blockAllowed;
    private ItemStack tempTool;

    public RenderBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.RENDER_BLOCK_TILE.get(), pos, state);
    }

    public static boolean blockAllowed(List<ItemStack> drops) {

        return true;
    }

    public static <T extends BlockEntity> void ticker(Level level, BlockPos blockPos, BlockState state, T tile) {
        if (!(tile instanceof RenderBlockEntity)) {
            return;
        }

        RenderBlockEntity entity = ((RenderBlockEntity) tile);
        entity.totalAge++;

        //Client only

        if (entity.level.isClientSide) {
            //Update ticks since last mine on client side for particle renders
            if (entity.playerUUID != null) {
                if (entity.getPlayer() != null && !entity.getPlayer().isUsingItem()) {
                    entity.ticksSinceMine++;
                } else {
                    entity.ticksSinceMine = 0;
                }
            }
            //The packet with new durability arrives between ticks. Update it on tick.
            if (entity.packetReceived) {
                //System.out.println("PreChange: " + entity.durability + ":" + entity.priorDurability);
                entity.priorDurability = entity.durability;
                entity.durability = entity.clientDurability;
                //System.out.println("PostChange: " + entity.durability + ":" + entity.priorDurability);
                entity.packetReceived = false;
            } else {
                if (entity.durability != 0) {
                    entity.priorDurability = entity.durability;
                }

            }


        }
        //Server Only
        if (!entity.level.isClientSide) {
            if (entity.ticksSinceMine == 1) {
                //Immediately after player stops mining, stability the shrinking effects and notify players
                entity.priorDurability = entity.durability;
                ServerTickHandler.addToList(blockPos, entity.durability, level);
            }
            if (entity.ticksSinceMine >= 10) {
                //After half a second, start 'regrowing' blocks that haven't been mined.
                entity.priorDurability = entity.durability;
                entity.durability++;
                ServerTickHandler.addToList(blockPos, entity.durability, level);
            }
            if (entity.durability >= entity.originalDurability) {
                //Once we reach the original durability value set the block back to its original blockstate.
                entity.resetBlock();
            }
            entity.ticksSinceMine++;
        }
    }

    public BlockState getRenderBlock() {
        return this.renderBlock;
    }

    public void setRenderBlock(BlockState state) {
        this.renderBlock = state;
    }

    public ToolMiningProperties.BreakTypes getBreakType() {
        return this.breakType;
    }

    public void setBreakType(ToolMiningProperties.BreakTypes breakType) {
        this.breakType = breakType;
    }

    public void justSetDurability(int durability) {
        this.priorDurability = this.durability;
        this.durability = durability;
    }

    public void setDurability(int dur, ItemStack stack) {
        this.ticksSinceMine = 0;
        if (this.durability != 0) {
            this.priorDurability = this.durability;
        }
        this.durability = dur;
        if (dur <= 0) {
            this.removeBlock();
        }
        if (!(this.level.isClientSide)) {
            this.setChanged();
            ServerTickHandler.addToList(this.worldPosition, this.durability, this.level);
            //PacketHandler.sendToAll(new PacketDurabilitySync(pos, dur), world);
            //System.out.println("Sent: "+ " Prior: " + priorDurability + " Dur: " + dur);
        }
    }

    public int getDurability() {
        return this.durability;
    }

    public int getOriginalDurability() {
        return this.originalDurability;
    }

    public void setOriginalDurability(int originalDurability) {
        this.originalDurability = originalDurability;
    }

    public Player getPlayer() {
        if (this.getLevel() == null) {
            return null;
        }

        return this.getLevel().getPlayerByUUID(this.playerUUID);
    }

    public void setPlayer(Player player) {
        this.playerUUID = player.getUUID();
    }

    public UUID getPlayerUUID() {
        return this.playerUUID;
    }

    public int getTicksSinceMine() {
        return this.ticksSinceMine;
    }

    public void setTicksSinceMine(int ticksSinceMine) {
        this.ticksSinceMine = ticksSinceMine;
    }

    public int getPriorDurability() {
        return this.priorDurability;
    }

    public void setPriorDurability(int priorDurability) {
        this.priorDurability = priorDurability;
    }

    public int getClientDurability() {
        return this.clientDurability;
    }

    public void setClientDurability(int clientDurability) {
        if (this.durability == 0) {
            this.clientPrevDurability = clientDurability;
        } else {
            this.clientPrevDurability = this.durability;
        }
        this.clientDurability = clientDurability;
        this.packetReceived = true;
    }

    public List<Upgrade> getToolUpgrades() {
        return this.toolUpgrades;
    }

    public void setToolUpgrades(List<Upgrade> gadgetUpgrades) {
        this.toolUpgrades = gadgetUpgrades;
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        // Vanilla uses the type parameter to indicate which type of tile entity (command block, skull, or beacon?) is receiving the packet, but it seems like Forge has overridden this behavior
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        this.load(tag);
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag = new CompoundTag();
        saveAdditional(tag);
        return tag;
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        this.load(pkt.getTag());
    }

    public void markDirtyClient() {
        this.setChanged();
        if (this.getLevel() != null) {
            BlockState state = this.getLevel().getBlockState(this.getBlockPos());
            this.getLevel().sendBlockUpdated(this.getBlockPos(), state, state, 3);
        }
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        this.renderBlock = NbtUtils.readBlockState(tag.getCompound("renderBlock"));
        this.originalDurability = tag.getInt("originalDurability");
        this.priorDurability = tag.getInt("priorDurability");
        this.durability = tag.getInt("durability");
        this.ticksSinceMine = tag.getInt("ticksSinceMine");
        if (tag.contains("playerUUID")) {
            this.playerUUID = tag.getUUID("playerUUID");
        }
        this.toolUpgrades = UpgradeTools.getUpgradesFromTag(tag);
        this.breakType = ToolMiningProperties.BreakTypes.values()[tag.getByte("breakType")];
        this.blockAllowed = tag.getBoolean("blockAllowed");
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        if (this.renderBlock != null) {
            tag.put("renderBlock", NbtUtils.writeBlockState(this.renderBlock));
        }
        tag.putInt("originalDurability", this.originalDurability);
        tag.putInt("priorDurability", this.priorDurability);
        tag.putInt("durability", this.durability);
        tag.putInt("ticksSinceMine", this.ticksSinceMine);
        if (this.playerUUID != null) {
            tag.putUUID("playerUUID", this.playerUUID);
        }
        tag.put("upgrades", UpgradeTools.setUpgradesNBT(this.toolUpgrades).getList("upgrades", Tag.TAG_COMPOUND));
        tag.putByte("breakType", (byte) this.breakType.ordinal());
        tag.putBoolean("blockAllowed", this.blockAllowed);
    }

    private void removeBlock() {
        if (this.level == null || this.level.isClientSide || this.playerUUID == null) {
            return;
        }

        Player player = this.level.getPlayerByUUID(this.playerUUID);
        if (player == null) {
            return;
        }

        int silk = 0;
        int fortune = 0;

        // If silk is in the upgrades, apply it without a tier.
        if (UpgradeTools.containsActiveUpgradeFromList(this.toolUpgrades, Upgrade.SILK)) {
            tempTool.enchant(Enchantments.SILK_TOUCH, 1);
            silk = 1;
        }

        // FORTUNE_1 is eval'd against the basename so this'll support all fortune upgrades
        if (UpgradeTools.containsActiveUpgradeFromList(this.toolUpgrades, Upgrade.FORTUNE_1)) {
            Optional<Upgrade> upgrade = UpgradeTools.getUpgradeFromList(this.toolUpgrades, Upgrade.FORTUNE_1);
            if (upgrade.isPresent()) {
                fortune = upgrade.get().getTier();
                tempTool.enchant(Enchantments.BLOCK_FORTUNE, fortune);
            }
        }

        // Fire an event for other mods that we've just broken the block
        BlockEvent.BreakEvent breakEvent = fixForgeEventBreakBlock(this.renderBlock, player, level, worldPosition, tempTool);
        MinecraftForge.EVENT_BUS.post(breakEvent);
        // Someone cancelled out break event
        if (breakEvent.isCanceled()) {
            return;
        }


        List<ItemStack> drops = Block.getDrops(this.renderBlock, (ServerLevel) this.level, this.worldPosition, null, player, tempTool);

        if (this.blockAllowed) {
            int exp = this.renderBlock.getExpDrop(this.level, this.worldPosition, fortune, silk);
            boolean magnetMode = (UpgradeTools.containsActiveUpgradeFromList(this.toolUpgrades, Upgrade.MAGNET));
            for (ItemStack drop : drops) {
                if (drop != null) {
                    if (magnetMode) {
                        int wasPickedUp = ForgeEventFactory.onItemPickup(new ItemEntity(this.level, this.worldPosition.getX(), this.worldPosition.getY(), this.worldPosition.getZ(), drop), player);
                        // 1  = someone allowed the event meaning it's handled,
                        // -1 = someone blocked the event and thus we shouldn't drop it nor insert it
                        // 0  = no body captured the event and we should handle it by hand.
                        if (wasPickedUp == 0) {
                            if (!player.addItem(drop)) {
                                Block.popResource(this.level, this.worldPosition, drop);
                            }
                        }
                    } else {
                        Block.popResource(this.level, this.worldPosition, drop);
                    }
                }
            }
            if (magnetMode) {
                if (exp > 0) {
                    player.giveExperiencePoints(exp);
                }
            } else {
                if (exp > 0) {
                    this.renderBlock.getBlock().popExperience((ServerLevel) this.level, this.worldPosition, exp);
                }
            }

            this.renderBlock.spawnAfterBreak((ServerLevel) this.level, this.worldPosition, tempTool); // Fixes silver fish basically...
        }

        //        BlockState underState = world.getBlockState(this.pos.down());
        this.level.removeBlockEntity(this.worldPosition);
        this.level.setBlockAndUpdate(this.worldPosition, Blocks.AIR.defaultBlockState());

        // Add to the break stats
        player.awardStat(Stats.BLOCK_MINED.get(this.renderBlock.getBlock()));

        // Handle special cases
        if (SpecialBlockActions.getRegister().containsKey(this.renderBlock.getBlock())) {
            SpecialBlockActions.getRegister().get(this.renderBlock.getBlock()).accept(this.level, this.worldPosition, this.renderBlock);
        }
    }

    private static BlockEvent.BreakEvent fixForgeEventBreakBlock(BlockState state, Player player, Level world, BlockPos pos, ItemStack tool) {
        BlockEvent.BreakEvent event = new BlockEvent.BreakEvent(world, pos, state, player);
        // Handle empty block or player unable to break block scenario
        if (state != null && ForgeHooks.isCorrectToolForDrops(state, player)) {
            int bonusLevel = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.BLOCK_FORTUNE, tool);
            int silklevel = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SILK_TOUCH, tool);
            event.setExpToDrop(state.getExpDrop(world, pos, bonusLevel, silklevel));
        }

        return event;
    }

    private void resetBlock() {
        if (this.level == null) {
            return;
        }

        if (!this.level.isClientSide) {
            if (this.renderBlock != null) {
                this.level.setBlockAndUpdate(this.worldPosition, this.renderBlock);
            } else {
                this.level.setBlockAndUpdate(this.worldPosition, Blocks.AIR.defaultBlockState());
            }
        }
    }

    public void setBlockAllowed() {

        Player player = this.level.getPlayerByUUID(this.playerUUID);
        if (player == null) {
            return;
        }
        int silk = 0;
        int fortune = 0;

        // If silk is in the upgrades, apply it without a tier.
        if (UpgradeTools.containsActiveUpgradeFromList(this.toolUpgrades, Upgrade.SILK)) {
            tempTool.enchant(Enchantments.SILK_TOUCH, 1);
            silk = 1;
        }

        // FORTUNE_1 is eval'd against the basename so this'll support all fortune upgrades
        if (UpgradeTools.containsActiveUpgradeFromList(this.toolUpgrades, Upgrade.FORTUNE_1)) {
            Optional<Upgrade> upgrade = UpgradeTools.getUpgradeFromList(this.toolUpgrades, Upgrade.FORTUNE_1);
            if (upgrade.isPresent()) {
                fortune = upgrade.get().getTier();
                tempTool.enchant(Enchantments.BLOCK_FORTUNE, fortune);
            }
        }

        List<ItemStack> drops = Block.getDrops(this.renderBlock, (ServerLevel) this.level, this.worldPosition, null, player, tempTool);

        this.blockAllowed = blockAllowed(drops);
    }

    public boolean getBlockAllowed() {
        return this.blockAllowed;
    }

}*/
