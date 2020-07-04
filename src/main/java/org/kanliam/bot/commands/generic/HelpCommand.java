package org.kanliam.bot.commands.generic;
import org.kanliam.bot.commands.Command;
import org.kanliam.bot.commands.CommandContext;

public class HelpCommand extends Command {
    public HelpCommand() {
        super("help");
    }

    @Override
    public void exec(CommandContext ctx) {
        ctx.getMsg().getChannel().sendMessage("Commands available:\n\n" +
                "connect: the bot will connect to the channel if he's not connected\n" +
                "current: displays the current song\n"+
                "date: displays the current date\n" +
                "echo: echos the inputted message\n" +
                "help: displays commands and their description(the command you just entered)\n" +
                "invite: displays the bot's invite link\n" +
                "leave: leaves the channel and clears the queue of the songs\n" +
                "pause: pauses the current song\n" +
                "ping: displays the ping in ms\n" +
                "play: plays songs with a given youtube link or search arguments\n" +
                "purge: deletes a give number of recent messages\n" +
                "queue: displays the current queue of songs\n" +
                "skip: skips the current song\n" +
                "time: displays the current time in israel daylight time (IDT)\n" +
                "unpause: unpauses the current song\n" +
                "whoami: displays the name of the message sender\n" +
                "whoareyou: displays information about the bot's programmer").queue();
    }
}