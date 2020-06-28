package org.kanliam.bot.commands.generic;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.VoiceChannel;
import org.apache.commons.validator.routines.UrlValidator;
import org.kanliam.bot.Main;
import org.kanliam.bot.audio.Song;
import org.kanliam.bot.audio.TrackSchedulerManager;
import org.kanliam.bot.commands.Command;
import org.kanliam.bot.commands.CommandContext;
import org.kanliam.bot.audio.TrackScheduler;

public class TryCommand extends Command {

    public TryCommand(){
        super("try");
    }

    @Override
    public void exec(CommandContext ctx) {
        //Arrays.copyOfRange(ctx.getArgs(),0, ctx.getArgs().length);
        Message message = ctx.getMsg();
        if(!message.getGuild().getSelfMember().getVoiceState().inVoiceChannel()){
            if (message.getMember().getVoiceState().getChannel() == null) {
                message.getChannel().sendMessage("You're not connected to a channel").queue();
            } else {
                message.getChannel().sendMessage("Connecting...").queue();
                VoiceChannel channel = message.getMember().getVoiceState().getChannel();
                message.getGuild().getAudioManager().openAudioConnection(channel);
            }
        }

        TrackSchedulerManager trackSchedulerManager = Main.getTrackSchedulerManager();
        TrackScheduler trackScheduler = trackSchedulerManager.getScheduler(ctx.getMsg().getGuild());
        String[] anArray = ctx.getArgs();


        if(anArray.length > 1){
            if(anArray.length > 2){
                // Search with a couple of arguments
                String arg = String.join(" ", anArray);
                //Main.getPlayerManager().loadItem("ytsearch: " + arg, new org.kanliam.bot.audio.AudioLoadResultHandler(ctx, trackScheduler));

            } else {
                // if there's one argument, check if its a URL or not
                String arg = anArray[1];
                if (isURLValid(arg)) {
                    Main.getPlayerManager().loadItem(arg, new org.kanliam.bot.audio.AudioLoadResultHandler(ctx, trackScheduler));
                } else {
                    // Search for a video with one argument
                    // Search with arg
                    Main.getPlayerManager().loadItem("ytsearch: " + arg, new org.kanliam.bot.audio.AudioLoadResultHandler(ctx, trackScheduler));
                }
            }
        }
    }
    public boolean isURLValid (String url){
        UrlValidator urlValidator = new UrlValidator();
        return urlValidator.isValid(url);
    }
}