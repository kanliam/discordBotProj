package org.kanliam.bot.commands.generic;

import org.kanliam.bot.commands.Command;
import org.kanliam.bot.commands.CommandContext;

public class WhoareyouCommand extends Command {

    public WhoareyouCommand() {
        super("whoareyou");
    }

    @Override
    public void exec(CommandContext ctx) {
        ctx.getMsg().getChannel().sendMessage("Hey there, my name is Liam and this is a simple discord bot " +
                "that i programmed with Java, you can see the source code of the bot in my GitHub page: " +
                "https://github.com/kanliam").queue();
    }
}