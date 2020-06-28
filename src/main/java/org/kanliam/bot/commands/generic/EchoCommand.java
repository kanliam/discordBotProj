package org.kanliam.bot.commands.generic;

import org.kanliam.bot.commands.Command;
import org.kanliam.bot.commands.CommandContext;

import java.util.Arrays;

public class EchoCommand extends Command {

    public EchoCommand(){
        super("echo");
    }

    @Override
    public void exec(CommandContext ctx) {

        //Checks if the message has 2 or more words, because if it had one (^^echo), we'll get an error
        if(ctx.getArgs().length > 1){
            ctx.getMsg().getChannel().sendMessage(String.join(" ", Arrays.copyOfRange(ctx.getArgs(),1, ctx.getArgs().length))).queue();
        }
    }
}