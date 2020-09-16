package pl.discord.config;


import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.discord.functionality.Loader;

import java.util.Set;

public class DiscordBot {

    private static final Logger logger = LoggerFactory.getLogger(DiscordBot.class);
    private static JDA botInstance = null;

    public static void run(Class classRunner, String [] args) {
        initializeBot();
    }


    private static void initEventListeners() {

        Reflections reflection = new Reflections("pl.discord.listeners");
        Set<Class<? extends ListenerAdapter>> subTypesOf = reflection.getSubTypesOf(ListenerAdapter.class);

        subTypesOf.forEach(subType -> {
            try {
                ListenerAdapter listenerAdapter =
                        subType.getConstructor()
                        .newInstance();
                botInstance.addEventListener(listenerAdapter);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private static void baseConfig(){
        botInstance.getPresence().setActivity(Activity.watching("Game of Thrones"));
        botInstance.getPresence().setStatus(OnlineStatus.ONLINE);
        botInstance.setAutoReconnect(true);
    }

    private static void initializeBot() {

        try {
            botInstance = JDABuilder.createDefault(Loader.loadToken()).build();
            baseConfig();
            initEventListeners();
        } catch (Exception e) {
            logger.error(e.getMessage(), e.fillInStackTrace());
        }

    }

}
