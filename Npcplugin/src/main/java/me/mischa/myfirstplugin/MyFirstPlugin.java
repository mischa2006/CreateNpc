package me.mischa.myfirstplugin;


import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import me.mischa.myfirstplugin.trash.BoomCommand;
import me.mischa.myfirstplugin.trash.HorseTamingListener;
import me.mischa.myfirstplugin.trash.InventoryGUI;
import net.minecraft.server.level.ServerPlayer;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class MyFirstPlugin extends JavaPlugin {
    private static List<ServerPlayer> npcs = new ArrayList<>();
    private static MyFirstPlugin plugin;

    @Override
    public void onEnable() {
        ProtocolManager manager = ProtocolLibrary.getProtocolManager();

//        manager.addPacketListener(new PacketAdapter(this, ListenerPriority.NORMAL, PacketType.Play.Client.POSITION) {
//            @Override
//            public void onPacketReceiving(PacketEvent event) {
//               PacketContainer packet = event.getPacket();
//               Player p = event.getPlayer();
//               double x = packet.getDoubles().read(0);
//               double y = packet.getDoubles().read(1);
//               double z = packet.getDoubles().read(2);
//               boolean isOnGround = packet.getBooleans().read(0);
//
//
//            }
//        });
//        manager.addPacketListener(new PacketAdapter(this, PacketType.Play.Server.REL_ENTITY_MOVE) {
//            @Override
//            public void onPacketSending(PacketEvent event) {
//
//                PacketContainer packet = event.getPacket();
//                Player p = event.getPlayer();
//                short x = packet.getShorts().read(0);
//                short y = packet.getShorts().read(1);
//                short z = packet.getShorts().read(2);
//                int id = packet.getIntegers().read(0);
//
//                p.sendMessage("OUTGOING PACKET: X: "+ x + " Y: "+ y + " Z: "+ z );
//                Entity entity = manager.getEntityFromID(p.getWorld(), id);
//                if (entity != null) {
//                    entity.teleport(p.getLocation());
//                }
//
//            }
//        });
//        manager.addPacketListener(new PacketAdapter(this, PacketType.Play.Client.CHAT) {
//            @Override
//            public void onPacketReceiving(PacketEvent event) {
//               event.setCancelled(true);
//
//            }
//        });


        plugin = this;

        getServer().getPluginManager().registerEvents(new HorseTamingListener(), this);
        getServer().getPluginManager().registerEvents(new InventoryGUI(), this);
        getServer().getPluginManager().registerEvents(new MovementListener(), this);
        this.getCommand("boom").setExecutor(new BoomCommand());
        this.getCommand("npc").setExecutor(new NPCCommand());
        getLogger().info("HorseTamingPlugin has been enabled");


    }

    public List<ServerPlayer> getNpcs() {
        return npcs;
    }

    public static MyFirstPlugin getPlugin() {
        return plugin;
    }


}
