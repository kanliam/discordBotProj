package org.kanliam.bot.commands.generic;

import net.dv8tion.jda.api.entities.TextChannel;
import org.kanliam.bot.commands.Command;
import org.kanliam.bot.commands.CommandContext;

import java.util.concurrent.CompletableFuture;

public class PurgeCommand extends Command {

    public PurgeCommand(){
        super("purge");
    }

    @Override
    public void exec(CommandContext ctx) {
        //add an if to check if the bot has enough permissions!

        TextChannel channel = ctx.getMsg().getTextChannel();

        if (ctx.getArgs().length > 1) {
            if(ctx.getArgs().length == 2){
                String[] argsArray = ctx.getArgs();
                int purgeInt = Integer.parseInt(argsArray[1]);
                channel.getHistoryBefore(ctx.getMsg(), purgeInt).queue((history) -> {CompletableFuture.allOf(channel.purgeMessages(history.getRetrievedHistory())
                        .toArray(new CompletableFuture[0]))
                        .thenAccept((v) ->{channel.sendMessage(String.format("Deleted %d messages", history.size())).queue();});});
            } else{
                ctx.getMsg().getChannel().sendMessage("Invalid arguments, only one argument needed, which is the number of messages to delete").queue();
            }
        }
    }
}