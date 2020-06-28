package org.kanliam.bot.commands.generic;

import net.dv8tion.jda.api.entities.User;
import org.kanliam.bot.commands.Command;
import org.kanliam.bot.commands.CommandContext;

public class WhoamiCommand extends Command {

    public WhoamiCommand() {
        super("whoami");
    }

    @Override
    public void exec(CommandContext ctx) {
        User user = ctx.getMsg().getAuthor();
        ctx.getMsg().getChannel().sendMessage(user.getName()).queue();
    }
}
