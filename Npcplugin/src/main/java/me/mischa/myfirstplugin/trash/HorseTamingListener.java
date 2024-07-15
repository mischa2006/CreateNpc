package me.mischa.myfirstplugin.trash;

import net.minecraft.network.protocol.game.ClientboundGameEventPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import org.bukkit.craftbukkit.v1_20_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class HorseTamingListener implements Listener {
    int i = 0;
    @EventHandler
    public void moveevent(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        CraftPlayer craftPlayer = (CraftPlayer) player;
        ServerPlayer serverPlayer = craftPlayer.getHandle();
        ServerGamePacketListenerImpl listener =  serverPlayer.connection;

        ClientboundGameEventPacket packet1 = new ClientboundGameEventPacket(ClientboundGameEventPacket.STOP_RAINING, 0.0f);
        //listener.send(packet1);
    }
}