package pl.discord.functionality;

import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


public class BotFunctionality {

    private static final Logger logger = LoggerFactory.getLogger(BotFunctionality.class);
    private static final String jokesUrl = "https://www.dowcipy.pl/p/";
    private static final String youtubeUrl = "https://www.youtube.com/results?search_query=";

    public static String randomJoke() {
        int randomPageNumber = (int) (Math.random() * 100) + 1;

        try {
            Document document = Jsoup.connect(jokesUrl + randomPageNumber + "/").get();
            Elements jokes = document.getElementsByClass("dowcip");
            String joke = jokes.get((int) (Math.random() * jokes.size())).text();

            return joke.substring(0, joke.length() - 21);
        } catch (IOException e) {
            e.printStackTrace();

        }
        return "No jokes available";
    }

    // TODO: Add YouTube Link function



    public static class Decider {
        public static void decideOnMessage(String messageContent, MessageReceivedEvent event) {

            if(messageContent.equals(MessageType.JOKE.getValue())){
                TextChannel textChannel = event.getGuild().getTextChannelsByName("ogólny", false).get(0);
                textChannel.sendTyping().queue();
                textChannel.sendMessage(BotFunctionality.randomJoke()).queue();
            } else {

            }
        }
    }

}
