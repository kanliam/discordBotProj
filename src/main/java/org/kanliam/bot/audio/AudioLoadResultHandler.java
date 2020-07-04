package org.kanliam.bot.audio;

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

    @Override
    public void trackLoaded(AudioTrack track) {
        Song song = new Song(track, ctx.getMsg().getTextChannel(), ctx.getMsg().getAuthor(), trackScheduler);
        trackScheduler.playSong(song);
    }

    @Override
    public void playlistLoaded(AudioPlaylist playlist) {
        Song song = new Song(playlist.getTracks().get(0), ctx.getMsg().getTextChannel(), ctx.getMsg().getAuthor(), trackScheduler);
        trackScheduler.playSong(song);
    }

    @Override
    public void noMatches() {
        ctx.getMsg().getTextChannel().sendMessage("There are no results").queue();
    }

    @Override
    public void loadFailed(FriendlyException exception) {
        System.out.println("Error in loadFailed, AudioLoadResultHandler");
        exception.printStackTrace();
    }
}