package pl.discord.listeners;


import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import pl.discord.functionality.BotFunctionality;
import pl.discord.functionality.MessageType;

import javax.annotation.Nonnull;


public class MessageEventListener extends ListenerAdapter {


    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event) {
        if(!event.getAuthor().getName().equals("kamil_kub"))
            event.getAuthor().openPrivateChannel().flatMap(privateChannel -> privateChannel.sendMessage("Pojebało cię?")).queue();

        if(event.getMessage().getContentDisplay().equals(MessageType.JOKE.getValue())){
            TextChannel textChannel = event.getGuild().getTextChannelsByName("ogólny", false).get(0);
            textChannel.sendTyping().queue();
            textChannel.sendMessage(BotFunctionality.randomJoke()).queue();
        }
    }


}
