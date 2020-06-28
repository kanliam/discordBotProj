package org.kanliam.bot.commands.generic;

import org.kanliam.bot.commands.Command;
import org.kanliam.bot.commands.CommandContext;

import static org.kanliam.bot.Main.getJDA;

public class InviteCommand extends Command {

    public InviteCommand() {
        super("invite");
    }

    @Override
    public void exec(CommandContext ctx) {
        String invUrl = getJDA().getInviteUrl();
        ctx.getMsg().getChannel().sendMessage("The invite link of the bot: " + invUrl).queue();
    }
}