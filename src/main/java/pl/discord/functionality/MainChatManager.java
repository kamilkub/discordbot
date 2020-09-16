package pl.discord.functionality;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.ArrayList;

public class MainChatManager {

    private static ArrayList<String> chatUsers = new ArrayList<>();

    public static void addGuest(String userName) {
        chatUsers.add(userName);
    }

    public static boolean isUserActive(String userName){
        return chatUsers.contains(userName);
    }

    public static void sendWelcomeMessage(MessageReceivedEvent event) {
        String author = event.getAuthor().getName();
        if(!isUserActive(author)){
            event.getAuthor().openPrivateChannel()
                    .flatMap(privateChannel -> privateChannel.sendMessage("Witam " + author)).queue();
            addGuest(author);
        }
    }

}
