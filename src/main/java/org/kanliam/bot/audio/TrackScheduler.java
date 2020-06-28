package org.kanliam.bot.audio;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;
import com.sedmelluq.discord.lavaplayer.track.playback.AudioFrame;
import net.dv8tion.jda.api.audio.AudioSendHandler;
import net.dv8tion.jda.api.entities.Guild;

import javax.annotation.Nullable;
import java.nio.ByteBuffer;
import java.util.ArrayDeque;
import java.util.Deque;

public class TrackScheduler extends AudioEventAdapter implements AutoCloseable, AudioSendHandler {
    private AudioPlayer player;
    private Deque<Song> queue = new ArrayDeque<>();
    private Song currentSong;
    private AudioFrame lastFrame;

    public TrackScheduler(AudioPlayer ap, Guild guild) {
       this.player = ap;
       player.addListener(this);
       guild.getAudioManager().setSendingHandler(this);
    }

    public void playSong(Song song){
        if(isPlaying()){
            String name = song.getTrack().getInfo().title;
            enqueue(song);
        } else {
            currentSong = song;
            getPlayer().playTrack(song.getTrack());

            //this.player.playTrack(song.getTrack());
            // delete this if everything works
        }
    }

    public boolean isPlaying(){
        return this.currentSong != null;
    }

    public void enqueue(Song newSong){
        newSong.getChannel().sendMessage(String.format(("The song %s has been added to the queue"), newSong.getName())).queue();
        queue.add(newSong);
    }


    @Override
    public void onTrackStart(AudioPlayer player, AudioTrack track) {
        currentSong.getChannel().sendMessage("Playing " + track.getInfo().title).queue();
    }

    @Override
    public void onTrackEnd(AudioPlayer player, AudioTrack track, AudioTrackEndReason endReason) {
        super.onTrackEnd(player, track, endReason);
        queue.pop();
        if(queue.isEmpty()){
            currentSong.getChannel().sendMessage("Queue is empty, leaving channel").queue();
            currentSong.getChannel().getGuild().getAudioManager().closeAudioConnection();
        } else {
            this.player.playTrack(queue.getFirst().getTrack());
        }
    }

    @Override
    public void onTrackStuck(AudioPlayer player, AudioTrack track, long thresholdMs) {
        super.onTrackStuck(player, track, thresholdMs);
    }

    @Override
    public void close() throws Exception {
        this.player.destroy();
    }

    public void sendQueue(){

        StringBuilder songList = new StringBuilder();
        int i = 1;
        for (Song song : queue) {
            long duration = song.getTrack().getDuration();
            String b = String.format("%d:%02d",(duration/1000)/60, (duration/1000)%60);
            songList.append(String.format("%d) %s\n with the duration of [%s]\n", i++, song.getName(), b));
        }
        currentSong.getChannel().sendMessage(songList.toString()).queue();
    }

    public AudioPlayer getPlayer() {
        return player;
    }

    @Override
    public boolean canProvide() {
        lastFrame = player.provide();
        return lastFrame != null;
    }

    @Nullable
    @Override
    public ByteBuffer provide20MsAudio() {
        return ByteBuffer.wrap(lastFrame.getData());
    }

    @Override
    public boolean isOpus() {
        return true;
    }
}