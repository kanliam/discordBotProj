package org.kanliam.bot.commands.generic;

import org.kanliam.bot.commands.Command;
import org.kanliam.bot.commands.CommandContext;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateCommand extends Command {

    public DateCommand() {
        super("date");
    }

    @Override
    public void exec(CommandContext ctx) {
        SimpleDateFormat formatter= new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date(System.currentTimeMillis());
        ctx.getMsg().getChannel().sendMessage("The current date is: " + formatter.format(date)).queue();
    }
}
