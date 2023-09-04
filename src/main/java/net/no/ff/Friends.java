package net.no.ff;

import net.fabricmc.loader.api.FabricLoader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

import static net.no.ff.noFFmain.*;

public class Friends {

    public static File FriendList = new File(FabricLoader.getInstance().getGameDir() + "\\config\\noFFlist.txt");

    public static void save(){
        try {
            String newFriends = "";
            int i=0;
            while (friends.size() > i){
                newFriends = newFriends +"\n"+friends.get(i);
                i++;
            }

            String content = newFriends;
            File file = new File(FriendList.toURI());
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean isFriend(String name){
        if (FriendList.exists()) {
            try {
                friends = Files.readAllLines(FriendList.toPath());
            } catch (IOException e) {System.out.println("failed to get friends");}
            int i = 0;
            while (friends.size() > i) {
                if (friends.get(i).equalsIgnoreCase(name)) {
                    return true;
                }
                i++;
            }
            return false;
        }
        return false;
    }
    public static void addFriend(String username){
        friends.add(username);
        sendMsg("new friend named: "+ username);
        save();
    }
    public static void removeFriend(String username){
        friends.remove(username);
        friends.remove("");
        sendMsg("removed friend named: "+ username);
        save();
    }
}
