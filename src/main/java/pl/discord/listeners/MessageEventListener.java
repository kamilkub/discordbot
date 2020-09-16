package pl.discord.listeners;


import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.discord.functionality.BotFunctionality;
import pl.discord.functionality.MainChatManager;

import javax.annotation.Nonnull;


public class MessageEventListener extends ListenerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(MessageEventListener.class);

    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event) {
        String messageContent = event.getMessage().getContentDisplay();

        try {
            MainChatManager.sendWelcomeMessage(event);
            BotFunctionality.Decider.decideOnMessage(messageContent, event);

            }catch(Exception e){
                if(!(e instanceof UnsupportedOperationException)){
                    logger.error(e.getMessage(), e.getCause());
            }

        }
    }
}
