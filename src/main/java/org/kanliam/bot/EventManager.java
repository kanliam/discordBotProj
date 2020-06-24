package org.kanliam.bot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

public class EventManager extends ListenerAdapter {

    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event) {
        super.onMessageReceived(event);
        Message message = event.getMessage();
        String prefix = "^^";
        if(message.getContentDisplay().startsWith(prefix)){
            String content = message.getContentDisplay().substring(prefix.length());
            String[] anArray  = content.split(" ");
            String commandName = anArray[0];
            switch(commandName){
                case "echo":
                    message.getChannel().sendMessage(String.join(" ",Arrays.copyOfRange(anArray,1, anArray.length))).queue();
                    break;
                case "whoami":
                    User user = message.getAuthor();
                    message.getChannel().sendMessage(user.getName()).queue();
                    break;
                case "ping":
                    JDA jda = message.getJDA();
                    message.getChannel().sendMessage("Calculating...").queue((msg -> {
                        long ping = message.getTimeCreated().until(msg.getTimeCreated(), ChronoUnit.MILLIS);
                        msg.editMessage(String.format("Ping: %dms, Websocket: %dms", ping, jda.getGatewayPing())).queue();
                    }));
                    break;
                case "help":
                    message.getChannel().sendMessage("Commands available:\necho: echos the inputted message\n" +
                            "ping: outputs the ping in ms\nwhoami: outputs the name of the message sender").queue();
                    break;

            }
        }
    }
}