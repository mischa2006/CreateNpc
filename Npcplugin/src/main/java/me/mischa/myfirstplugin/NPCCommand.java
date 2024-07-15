package me.mischa.myfirstplugin;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.mojang.datafixers.util.Pair;
import net.minecraft.network.protocol.game.*;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ClientInformation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_20_R4.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_20_R4.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class NPCCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player player) {
            CraftPlayer craftPlayer = (CraftPlayer) player;
            ServerPlayer sp = craftPlayer.getHandle();

            MinecraftServer server = sp.server;
            ServerLevel level = sp.serverLevel();
            GameProfile gameProfile = new GameProfile(UUID.randomUUID(), "gert");

            String signature = "TF+G4RhDwn0up2VjHBhKKI8IRs9x3FNK1j1CSGC+jSCtXJ4i4PqcjTYumki+mANAFAz5kLnGucjL1gV+zKK+5EErMFYktriDr5ELCOJABdttTArdd9v/IP8mb87asi+oFKQ9vadquTdxRgn1FEQQrr/iGtXa6o8KgpQIyBRERw9/jb4bVmZfFR324qBdHE1VfQD3DH1UdQ6HTQ3wXVqropuQRAsY9QAmRo2nnLiJhGJ8l+1R7ZhoeJPyGityKGSDwS18YQzh0Z/Mh2x8UaVfpiWf4LCxOvvUG4jSoV3+mj/c1ylqvq47rudZbJ+9UzOOn0Mw31vnbrvyJYTth6V5mZ4zoK1/UJCAGk2wjIa0BR0z501qzFtFuzOPBa6qRIBzPD8DeCAGHmPdVUGMz6NzBwbl6Ske0Kruj1R8TY7dIOjAjZnZ4YXA1Xr+rwJqIYpoZlP1cRQqNj05Cu04Y/mkZCzl/GMrrmgwA33fmoq+5cmIKTYuey0eIJDagghlVR1c5+An46geo6PnUy3zuwZxs1Rfzz9CgAWD3B9UTe8+M4zcHfX58OvApM44G+GyKI1RwuO5sQe6iNsRdbW9IzkdAM8cTw3SAhb2MKSeQGgVA00eS5eAIvWiCf7ERdJYKSFZUen1flmM/+G0rSStVtdWCxTeUJyEwoCLPUNnVrEqXm0=";
            String texture= "ewogICJ0aW1lc3RhbXAiIDogMTcyMDk4Mzg2OTkxMCwKICAicHJvZmlsZUlkIiA6ICIwNjlhNzlmNDQ0ZTk0NzI2YTViZWZjYTkwZTM4YWFmNSIsCiAgInByb2ZpbGVOYW1lIiA6ICJOb3RjaCIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS8yOTIwMDlhNDkyNWI1OGYwMmM3N2RhZGMzZWNlZjA3ZWE0Yzc0NzJmNjRlMGZkYzMyY2U1NTIyNDg5MzYyNjgwIgogICAgfQogIH0KfQ==";

            gameProfile.getProperties().put("textures", new Property("textures",texture,signature));

            ServerPlayer npc = new ServerPlayer(server, level, gameProfile, ClientInformation.createDefault());
            npc.setPos(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ());

            ItemStack axe = new ItemStack(Material.DIAMOND_AXE);

            SynchedEntityData synchedEntityData = npc.getEntityData();
            synchedEntityData.set(new EntityDataAccessor<>(17, EntityDataSerializers.BYTE), (byte) 127);

            ReflectionUtil.setValue(npc, "c", ((CraftPlayer) player).getHandle().connection);
            ReflectionUtil.sendPacket(new ClientboundPlayerInfoUpdatePacket(ClientboundPlayerInfoUpdatePacket.Action.ADD_PLAYER, npc), player);
            ReflectionUtil.sendPacket(new ClientboundAddEntityPacket(npc), player);
            ReflectionUtil.sendPacket(new ClientboundSetEntityDataPacket(npc.getId(), synchedEntityData.getNonDefaultValues()), player);
            ReflectionUtil.sendPacket(new ClientboundSetEquipmentPacket(npc.getBukkitEntity().getEntityId(), List.of(Pair.of(EquipmentSlot.MAINHAND, CraftItemStack.asNMSCopy(axe)))),player);
//            Bukkit.getScheduler().runTaskLaterAsynchronously((Plugin) this, new Runnable() {
//                @Override
//                public void run() {
//                    ReflectionUtil.sendPacket(new ClientboundPlayerInfoRemovePacket(Collections.singletonList(npc.getUUID())), player);
//                }
//            }, 40);
            MyFirstPlugin.getPlugin().getNpcs().add(npc);
            player.sendMessage("NPC Summoned");

        }
        return true;
    }
}
