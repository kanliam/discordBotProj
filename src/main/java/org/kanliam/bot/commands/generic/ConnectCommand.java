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
        if(message.getMember().getVoiceState().getChannel() == null){
            message.getChannel().sendMessage("You're not connected to a channel").queue();
        } else {
            VoiceChannel channel = message.getMember().getVoiceState().getChannel();
            if(!message.getGuild().getAudioManager().isConnected()){
                message.getGuild().getAudioManager().openAudioConnection(channel);
                message.getChannel().sendMessage("Connecting...").queue();
            } else {
                if(!message.getGuild().getAudioManager().getConnectedChannel().equals(message.getMember().getVoiceState().getChannel())){
                    message.getGuild().getAudioManager().openAudioConnection(message.getMember().getVoiceState().getChannel());
                    message.getChannel().sendMessage("Moving to your channel").queue();
                } else {
                    message.getChannel().sendMessage("Connected already").queue();
                }
            }
        }
    }
}