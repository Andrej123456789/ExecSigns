package me.andrej123456789.execsigns;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public final class ExecSign extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onPlayerRightClick(PlayerInteractEvent event) {
        if (event.getAction().toString().contains("RIGHT_CLICK_BLOCK")) {
            Block clickedBlock = event.getClickedBlock();
            Material blockType = clickedBlock.getType();

            if (blockType == Material.ACACIA_SIGN ||
                blockType == Material.BIRCH_SIGN ||
                blockType == Material.DARK_OAK_SIGN ||
                blockType == Material.JUNGLE_SIGN ||
                blockType == Material.OAK_SIGN ||
                blockType == Material.SPRUCE_SIGN ||
                blockType == Material.WARPED_SIGN ||
                blockType == Material.CRIMSON_SIGN) {

                Sign sign = (Sign) clickedBlock.getState();

                String[] lines = sign.getLines();
                String space = " ";

                getLogger().warning(lines[0] + space + lines[1]);
                event.getPlayer().performCommand(lines[0] + space + lines[1]);
            }
        }
    }
}
