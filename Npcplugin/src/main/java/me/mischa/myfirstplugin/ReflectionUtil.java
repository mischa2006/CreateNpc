package me.mischa.myfirstplugin;

import net.minecraft.network.protocol.Packet;
import org.bukkit.craftbukkit.v1_20_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;

public class ReflectionUtil {

    public static void sendPacket(Packet<?> packet, Player player) {
        ((CraftPlayer) player).getHandle().connection.send(packet);
    }

    public static void setValue(Object packet, String fieldName, Object value) {
        try {
            Field field = packet.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(packet, value);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
