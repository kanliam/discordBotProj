package org.kanliam.bot.commands.generic;

import org.kanliam.bot.commands.Command;
import org.kanliam.bot.commands.CommandContext;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeCommand extends Command {

    public TimeCommand() {
        super("time");
    }

    @Override
    public void exec(CommandContext ctx) {
        SimpleDateFormat aFormatter= new SimpleDateFormat("HH:mm:ss z");
        Date aDate = new Date(System.currentTimeMillis());
        ctx.getMsg().getChannel().sendMessage("The current time is: " + aFormatter.format(aDate)).queue();
    }
}