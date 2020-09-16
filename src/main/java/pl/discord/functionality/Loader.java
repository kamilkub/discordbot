package pl.discord.functionality;


public class Loader {

    public static String loadToken() {
        return System.getenv("DISCORD_BOT");
    }
}
