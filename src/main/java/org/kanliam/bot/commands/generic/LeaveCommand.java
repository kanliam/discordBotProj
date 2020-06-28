package org.kanliam.bot.commands.generic;

import net.dv8tion.jda.api.entities.Message;
import org.kanliam.bot.commands.Command;
import org.kanliam.bot.commands.CommandContext;

public class LeaveCommand extends Command {

    public LeaveCommand() {
        super("leave");
    }

    @Override
    public void exec(CommandContext ctx) {
        Message message = ctx.getMsg();
        if(message.getGuild().getAudioManager().isConnected() == false){
            message.getChannel().sendMessage("The bot is not connected to a channel").queue();
        } else {
            message.getChannel().sendMessage("Leaving").queue();
            message.getGuild().getAudioManager().closeAudioConnection();
        }
    }
}