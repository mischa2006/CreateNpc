package me.mischa.myfirstplugin;

import net.minecraft.network.protocol.game.ClientboundMoveEntityPacket;
import net.minecraft.network.protocol.game.ClientboundRotateHeadPacket;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class MovementListener implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();

        MyFirstPlugin.getPlugin().getNpcs().stream()
                .forEach(npc -> {

                    Location location = npc.getBukkitEntity().getLocation();
                    location.setDirection(p.getLocation().subtract(location).toVector());
                    float yaw = location.getYaw();
                    float pitch = location.getPitch();

                    //horizontal
                    ReflectionUtil.sendPacket(new ClientboundRotateHeadPacket(npc, (byte) ((yaw%360)*256/360)),p);
                    //vertical
                    ReflectionUtil.sendPacket(new ClientboundMoveEntityPacket.Rot(npc.getBukkitEntity().getEntityId(),(byte) ((yaw%360)*256/360),(byte) ((pitch%360)*256/360),false),p);

                });
    }
}
