package net.no.ff;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;

import static com.mojang.brigadier.arguments.StringArgumentType.*;
import static net.no.ff.noFFmain.*;

public class Command {

    public static void commandManager(){
        addFriend();
        removeFriend();
        friendList();
    }

    public static void addFriend() {
        ClientCommandRegistrationCallback.EVENT.register(
                (dispatcher, registryAccess) -> dispatcher.register(ClientCommandManager.literal("friendadd")
                        .then(ClientCommandManager.argument("player", greedyString()).executes(context -> {
                                String nickname = getString(context, "player");
                                Friends.addFriend(nickname);
                                return 1;
                                })))
                );
    }
    public static void removeFriend() {
        ClientCommandRegistrationCallback.EVENT.register(
                (dispatcher, registryAccess) -> dispatcher.register(ClientCommandManager.literal("friendremove")
                        .then(ClientCommandManager.argument("player", string()).executes(context -> {
                            String nickname = getString(context, "player");
                            Friends.removeFriend(nickname);
                            return 1;
                            })))
                );
    }
    public static void friendList() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> dispatcher.register(ClientCommandManager.literal("friendlist").executes(context -> {
            sendfriendlist();
            return 1;
        })));
    }


    public static void sendfriendlist(){
        int i =0;
        sendMsg("Friends: \n");
        while(friends.size() > i){
            sendMsg(friends.get(i));
            i++;
        }
    }
}
