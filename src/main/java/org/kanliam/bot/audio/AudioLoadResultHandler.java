package org.kanliam.bot.audio;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.entities.Message;
import org.kanliam.bot.commands.CommandContext;

public class AudioLoadResultHandler implements com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler {
    private CommandContext ctx;
    private TrackScheduler trackScheduler;
    private Message message;

    public AudioLoadResultHandler(CommandContext ctx, TrackScheduler trackScheduler) {
        this.ctx = ctx;
        this.trackScheduler = trackScheduler;
        this.message = ctx.getMsg();
    }

    //@Override
    public void trackLoaded(AudioTrack track) {
        Song song = new Song(track, ctx.getMsg().getTextChannel(), ctx.getMsg().getAuthor(), trackScheduler);
        trackScheduler.playSong(song);
    }

    //@Override
    public void playlistLoaded(AudioPlaylist playlist) {
        for (AudioTrack track : playlist.getTracks()) {
            Song song = new Song(track, ctx.getMsg().getTextChannel(), ctx.getMsg().getAuthor(), trackScheduler);
            trackScheduler.playSong(song);
            message.getTextChannel().sendMessage(String.format("Adding %s to the queue", song.getName())).queue();
        }
    }

    //@Override
    public void noMatches() {
        ctx.getMsg().getTextChannel().sendMessage("There are no results").queue();
    }

    //@Override
    public void loadFailed(FriendlyException exception) {
        exception.printStackTrace();
    }
}