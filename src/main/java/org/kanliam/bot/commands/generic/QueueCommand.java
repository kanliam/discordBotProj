package org.kanliam.bot.commands.generic;

import org.kanliam.bot.Main;
import org.kanliam.bot.commands.Command;
import org.kanliam.bot.commands.CommandContext;

public class QueueCommand extends Command {


    public QueueCommand() {
        super("queue");
    }

    @Override
    public void exec(CommandContext ctx) {
        Main.getTrackSchedulerManager().getScheduler(ctx.getMsg().getGuild()).sendQueue();
    }
}