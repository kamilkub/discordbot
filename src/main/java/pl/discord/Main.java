package pl.discord;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.discord.functionality.Loader;
import pl.discord.listeners.MessageEventListener;

import javax.security.auth.login.LoginException;

public class Main {

    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String [] args) {

        try {
            JDA jda = JDABuilder.createDefault(Loader.loadToken()).build();
            jda.getPresence().setActivity(Activity.playing("Myje łysemu okna"));
            jda.getPresence().setStatus(OnlineStatus.ONLINE);
            jda.setAutoReconnect(true);
            jda.addEventListener(new MessageEventListener());
        } catch (LoginException e) {
            e.printStackTrace();
        }

    }

}
