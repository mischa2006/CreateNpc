package me.mischa.myfirstplugin.trash;


import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InvCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("inv")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                InventoryGUI.openCustomInventory(player);
                sender.sendMessage("test");
                return true;
            } else {
                sender.sendMessage("This command can only be used by players.");
            }
        }
        return false;
    }
}
