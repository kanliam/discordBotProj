package org.kanliam.bot.commands.generic;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.VoiceChannel;
import org.kanliam.bot.commands.Command;
import org.kanliam.bot.commands.CommandContext;

public class ConnectCommand extends Command {
    public ConnectCommand() {
        super("connect");
    }

    @Override
    public void exec(CommandContext ctx) {
        Message message = ctx.getMsg();
        message.getChannel().sendMessage("Connecting...").queue();
        if(message.getMember().getVoiceState().getChannel() == null){
            message.getChannel().sendMessage("You're not connected to a channel").queue();
        } else {
            VoiceChannel channel = message.getMember().getVoiceState().getChannel();
            message.getGuild().getAudioManager().openAudioConnection(channel);
        }
    }
}