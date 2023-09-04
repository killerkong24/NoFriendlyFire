package net.no.ff.orbitEvent.impl;

import net.no.ff.orbitEvent.Event;
import net.minecraft.network.packet.Packet;


public class PacketEvent extends Event {
    private final Packet<?> packet;

    public PacketEvent(Packet<?> packet) {
        this.packet = packet;
    }


    public static class Send extends PacketEvent {
        public Send(Packet<?> packet) {
            super(packet);

        }
    }

    public static class SendPost extends PacketEvent {
        public SendPost(Packet<?> packet) {
            super(packet);
        }
    }
}
