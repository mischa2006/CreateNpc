package me.mischa.myfirstplugin.trash;



import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class InventoryGUI implements Listener {

    public static void openCustomInventory(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 9, "Create npc?");

        ItemStack greenWool = new ItemStack(Material.GREEN_WOOL);
        ItemStack redWool = new ItemStack(Material.RED_WOOL);
        ItemStack greyglass = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);

        ItemMeta greenWoolMeta = greenWool.getItemMeta();
        greenWoolMeta.setItemName(ChatColor.GREEN + "yeh");
        greenWool.setItemMeta(greenWoolMeta);

        ItemMeta redWoolMeta = redWool.getItemMeta();
        redWoolMeta.setItemName(ChatColor.RED + "nah");
        redWool.setItemMeta(redWoolMeta);

        ItemMeta greyglassMeta = greyglass.getItemMeta();
        greyglassMeta.setItemName(" ");
        greyglass.setItemMeta(greyglassMeta);



        inventory.setItem(0, greyglass);
        inventory.setItem(1, greyglass);
        inventory.setItem(2, greyglass);
        inventory.setItem(4, greyglass);
        inventory.setItem(6, greyglass);
        inventory.setItem(7, greyglass);
        inventory.setItem(8, greyglass);
        inventory.setItem(3, greenWool);
        inventory.setItem(5, redWool);

        player.openInventory(inventory);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Inventory inventory = event.getInventory();
        ItemStack clickedItem = event.getCurrentItem();
        Player player = (Player) event.getWhoClicked();
        // Check if the inventory is the custom one
        if (event.getView().getTitle().equals("Create npc?")) {
            event.setCancelled(true); // Prevent the default behavior

            if (clickedItem == null) return;

            switch (event.getCurrentItem().getType()) {
                case GREEN_WOOL:
                    event.getWhoClicked().sendMessage("You clicked the green wool!");
                    player.sendMessage(clickedItem.getType().name());
                    player.closeInventory();
                    player.updateInventory();
                    break;
                case RED_WOOL:
                    event.getWhoClicked().sendMessage("You clicked the red wool!");
                    player.sendMessage(clickedItem.getType().name());
                    player.closeInventory();
                    player.updateInventory();
                    break;
            }
        }
    }
}

