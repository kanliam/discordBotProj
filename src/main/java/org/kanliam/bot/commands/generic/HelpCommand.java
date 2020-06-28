package org.kanliam.bot.commands.generic;

import org.kanliam.bot.commands.Command;
import org.kanliam.bot.commands.CommandContext;

public class HelpCommand extends Command {
    public HelpCommand() {
        super("help");
    }

    @Override
    public void exec(CommandContext ctx) {
        ctx.getMsg().getChannel().sendMessage("Commands available:\n\necho: echos the inputted message\n" +
                "ping: displays the ping in ms\nwhoami: displays the name of the message sender\ntime:" +
                " displays the current time in israel daylight time (IDT)\n date: displays the current date\nwhoareyou: displays" +
                " information about the bot's programmer\nconnect: the bot will connect to the channel if he's not connected\n" +
                "leave: the bot will leave the channel if he's connected").queue();
    }
}
