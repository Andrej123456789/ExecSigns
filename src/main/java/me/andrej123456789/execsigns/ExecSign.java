package me.andrej123456789.execsigns;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public final class ExecSign extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(this, this);

        getLogger().info("Initialization of ExecSigns is done!");
        getLogger().info("If you like this plugin, give it a star on Github: https://github.com/Andrej123456789/ExecSigns");
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

            /* Check if block is sign */
            if (blockType == Material.ACACIA_SIGN ||
                blockType == Material.BIRCH_SIGN ||
                blockType == Material.DARK_OAK_SIGN ||
                blockType == Material.JUNGLE_SIGN ||
                blockType == Material.OAK_SIGN ||
                blockType == Material.SPRUCE_SIGN ||
                blockType == Material.MANGROVE_SIGN ||
                blockType == Material.WARPED_SIGN ||
                blockType == Material.CRIMSON_SIGN ||
                blockType == Material.ACACIA_WALL_SIGN ||
                blockType == Material.BIRCH_WALL_SIGN ||
                blockType == Material.DARK_OAK_WALL_SIGN ||
                blockType == Material.JUNGLE_WALL_SIGN ||
                blockType == Material.OAK_WALL_SIGN ||
                blockType == Material.SPRUCE_WALL_SIGN ||
                blockType == Material.MANGROVE_WALL_SIGN ||
                blockType == Material.WARPED_WALL_SIGN ||
                blockType == Material.CRIMSON_WALL_SIGN) {

                Sign sign = (Sign) clickedBlock.getState();

                String[] lines = sign.getLines();
                String space = " ";

                /* If first line starts with '<' and ends with '>', execute command */
                if (lines[0].startsWith("<") && lines[0].endsWith(">") && event.getPlayer().hasPermission("exec-signs.execute")) {
                    String command = getCommand(event, lines, space);
                    event.getPlayer().performCommand(command);

                    getLogger().info("Command '" + command + "' has been executed.");
                }

                else if (lines[0].startsWith("<") && lines[0].endsWith(">") && !event.getPlayer().hasPermission("exec-signs.execute")) {
                    String errorMessage = ChatColor.RED + "[ExecSigns] You don't have permission to execute commands on sign!" + ChatColor.RESET;
                    event.getPlayer().sendMessage(errorMessage);
                }
            }
        }
    }

    @NotNull
    private static String getCommand(PlayerInteractEvent event, String[] lines, String space) {
        String remove = "<>";
        StringBuilder resultStringBuilder = new StringBuilder();

        for (char c : lines[0].toCharArray()) {
            if (remove.indexOf(c) == -1) {
                resultStringBuilder.append(c);
            }
        }

        String player_placeholder = "{player}";
        String player_name = event.getPlayer().getName();

        return (resultStringBuilder.toString() + space + lines[1].replace(player_placeholder, player_name));
    }
}
