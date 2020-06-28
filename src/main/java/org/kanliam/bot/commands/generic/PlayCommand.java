package org.kanliam.bot.commands.generic;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.VoiceChannel;
import org.kanliam.bot.Main;
import org.kanliam.bot.commands.Command;
import org.kanliam.bot.commands.CommandContext;

public class PlayCommand extends Command {
    public PlayCommand() {
        super("play");
    }

    @Override
    public void exec(CommandContext ctx){
        AudioPlayerManager playerManager = Main.getPlayerManager();
        AudioPlayer player = playerManager.createPlayer();
        Message message = ctx.getMsg();
        if(message.getMember().getVoiceState().getChannel() == null){
            message.getChannel().sendMessage("You're not connected to a channel").queue();
        } else {
            VoiceChannel channel = message.getMember().getVoiceState().getChannel();
            //AudioPlayer player = playerManager.createPlayer();
            while(channel.getMembers() != null){
                //message.getGuild().getAudioManager().openAudioConnection(channel);
                //message.getGuild().getAudioManager().setSpeakingMode();
            }
        }
    }
}


/*
    @Override
    public void exec(CommandContext ctx) {
        Message message = ctx.getMsg();
        message.getChannel().sendMessage("Connecting...").queue();
        if(message.getMember().getVoiceState().getChannel() == null){
            message.getChannel().sendMessage("You're not connected to a channel").queue();
        } else {
            VoiceChannel theChannel = message.getMember().getVoiceState().getChannel();
            message.getGuild().getAudioManager().openAudioConnection(theChannel);
        }
    }
*/