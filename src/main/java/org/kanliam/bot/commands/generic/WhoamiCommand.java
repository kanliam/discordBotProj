package org.kanliam.bot.commands.generic;

import org.kanliam.bot.commands.Command;
import org.kanliam.bot.commands.CommandContext;

public class WhoamiCommand extends Command {

    public WhoamiCommand() {
        super("whoami");
    }

    @Override
    public void exec(CommandContext ctx) {
        ctx.getMsg().getChannel().sendMessage(ctx.getMsg().getAuthor().getName()).queue();
    }
}
