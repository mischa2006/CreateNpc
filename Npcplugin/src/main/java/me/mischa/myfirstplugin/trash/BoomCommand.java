package me.mischa.myfirstplugin.trash;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BoomCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

//        if (sender instanceof Player){
//
//            Player p = (Player) sender;
//            p.sendMessage("yipyap");
//            ProtocolManager manager = ProtocolLibrary.getProtocolManager();
//
//            //Construct a packet that we can send to someone
//            //The PacketType will be Server if its origin is the Server(clientbound), Client if its origin is the Client(serverbound)
//            PacketContainer packet = manager.createPacket(PacketType.Play.Server.UPDATE_TIME);
//
//            packet.getLongs().write(0, 18000L);
//            packet.getLongs().write(1, 18000L);
//
//            try {
//                //send to a single player, so only they see the explosion happen
//                while(true) manager.sendServerPacket(p, packet);
//                //send to all players
//                //manager.broadcastServerPacket(packet);
//            } catch (InvocationTargetException e) {
//                System.out.println("oopsies");
//            }
//            return true;
//        }





//        CraftPlayer craftPlayer = (CraftPlayer) player;
//
//        ServerPlayer sp = (ServerPlayer) craftPlayer.getHandle();
//        ServerGamePacketListenerImpl ps = sp.connection;
//        MinecraftServer server = sp.getServer();
//        if(ps ==null){
//            System.out.println("Player "+sp.getName()+" is null!");
//        }
//        ServerLevel level = sp.serverLevel();
//        GameProfile gameProfile = new GameProfile(UUID.randomUUID(), "gert");
//        ClientInformation clientInformation = sp.clientInformation();
//
//        // Additional logging for debugging
//        System.out.println("Server: " + server);
//        System.out.println("Level: " + level);
//        System.out.println("Client Information: " + clientInformation);
//
//        if (server == null) {
//            System.out.println("Server is null");
//            return false;
//        }
//        if (level == null) {
//            System.out.println("Level is null");
//            return false;
//        }
//        if (clientInformation == null) {
//            System.out.println("Client information is null");
//            return false;
//        }
//
//        ServerPlayer npc = new ServerPlayer(server, level, gameProfile, clientInformation);
//
//        System.out.println("NPC created successfully: " + npc);
//
//        try {
//            // Ensure correct packet creation
//            System.out.println("Creating ClientboundPlayerInfoUpdatePacket...");
//            ClientboundPlayerInfoUpdatePacket packet = new ClientboundPlayerInfoUpdatePacket(ClientboundPlayerInfoUpdatePacket.Action.ADD_PLAYER, npc);
//            System.out.println("Packet created: " + packet);
//
//            ps.send(packet); // Send the packet to add the NPC to the player's client
//
//            ClientboundAddEntityPacket packet1 = new ClientboundAddEntityPacket((Entity) npc);
//            ps.send(packet1);
//
//            System.out.println("Packets sent successfully.");
//        } catch (Exception e) {
//            System.err.println("Error occurred while sending packets:");
//            e.printStackTrace();
//        }
        return false;
  }
}
