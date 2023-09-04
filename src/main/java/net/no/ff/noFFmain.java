package net.no.ff;

import io.netty.buffer.Unpooled;
import net.fabricmc.api.ModInitializer;

import net.no.ff.orbitEvent.EventBus;
import net.no.ff.orbitEvent.IEventBus;
import net.minecraft.entity.Entity;
import net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.nio.file.Files;
import java.util.List;

public class noFFmain implements ModInitializer {

	public static final IEventBus EVENT_BUS = new EventBus();
	public static MinecraftClient mc = MinecraftClient.getInstance();
	public static List<String> friends;

	@Override
	public void onInitialize() {
		EVENT_BUS.registerLambdaFactory("dev", (lookupInMethod, klass) -> (MethodHandles.Lookup) lookupInMethod.invoke(null, klass, MethodHandles.lookup()));
		System.out.println("Started TeamProtect");
		try {
			if (!Files.exists(Friends.FriendList.toPath())) {
				Files.createFile(Friends.FriendList.toPath());
				System.out.println("Created noFFlist.txt");
			}else {
				System.out.println("noFFlist.txt already exists");
				friends = Files.readAllLines(Friends.FriendList.toPath());
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		Command.commandManager();
	}

	public static Entity getEntity(@NotNull PlayerInteractEntityC2SPacket packet) {
		PacketByteBuf packetBuf = new PacketByteBuf(Unpooled.buffer());
		packet.write(packetBuf);
		return mc.world.getEntityById(packetBuf.readVarInt());
	}

	public static void sendMsg(String message) {
		if (mc.player == null) return;
		mc.player.sendMessage(Text.of(message.replace("&", "§")));
	}
}