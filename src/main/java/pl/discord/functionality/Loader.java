package pl.discord.functionality;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Loader {

    public static String loadToken() {
        Properties props = new Properties();

        try {
            props.load(new FileInputStream("keys.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return String.valueOf(props.get("BOT_TOKEN"));
    }
}
