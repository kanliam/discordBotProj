package org.kanliam.bot.audio;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;
import com.sedmelluq.discord.lavaplayer.track.playback.AudioFrame;
import net.dv8tion.jda.api.audio.AudioSendHandler;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import org.kanliam.bot.Main;

import javax.annotation.Nullable;
import java.nio.ByteBuffer;
import java.util.ArrayDeque;
import java.util.Deque;

public class TrackScheduler extends AudioEventAdapter implements AutoCloseable, AudioSendHandler {

    private final AudioPlayer player;
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
            enqueue(song);
        } else {
            currentSong = song;
            getPlayer().playTrack(song.getTrack());
        }
    }

    public boolean isPlaying(){
        return this.currentSong != null;
    }

    public void enqueue(Song newSong){
        newSong.getChannel().sendMessage(String.format(("The song %s has been added to the queue"), newSong.getName())).queue();
        queue.add(newSong);
    }

    public void emptyQueue(){
        queue = new ArrayDeque<>();
    }

    @Override
    public void onTrackStart(AudioPlayer player, AudioTrack track) {
        currentSong.getChannel().sendMessage("Playing " + track.getInfo().title).queue();
    }

    @Override
    public void onTrackEnd(AudioPlayer player, AudioTrack track, AudioTrackEndReason endReason) {

        if(queue.isEmpty()){
            currentSong.getChannel().sendMessage("Queue is empty, leaving channel").queue();
            currentSong.getChannel().getGuild().getAudioManager().closeAudioConnection();
            try {
                Main.getTrackSchedulerManager().destroyScheduler(currentSong.getChannel().getGuild());
            } catch (Exception e) {
                System.out.println("Exception in onTruckEnd, TrackScheduler");
                e.printStackTrace();
            }
        } else {
            this.player.playTrack(queue.pop().getTrack());
        }
        super.onTrackEnd(player, track, endReason);
    }

    @Override
    public void onTrackStuck(AudioPlayer player, AudioTrack track, long thresholdMs) {
        System.out.println("stuck in onTruckStuck, TrackScheduler");
        super.onTrackStuck(player, track, thresholdMs);
    }

    @Override
    public void close() throws Exception {
        if(this.player != null){
            this.player.destroy();
        }
    }

    public void sendQueue(Message message){
        if(this.player != null && this.queue != null && this.currentSong != null) {
            if(!queue.isEmpty()){
                StringBuilder songList = new StringBuilder();
                int i = 1;
                for (Song song : queue) {
                    long duration = song.getTrack().getDuration();
                    String b = String.format("%d:%02d",(duration/1000)/60, (duration/1000)%60);
                    songList.append(String.format("%d) %s ==> Duration of [%s]\n", i++, song.getName(), b));
                }
                message.getChannel().sendMessage(songList.toString()).queue();
            } else {
                message.getChannel().sendMessage("Queue is empty").queue();
            }
        } else if (!message.getGuild().getAudioManager().isConnected()){
            message.getTextChannel().sendMessage("The queue is empty, the bot isn't even connected").queue();
        }
    }

    public AudioPlayer getPlayer() {
        return player;
    }


    public Deque<Song> getQueue() {
        return queue;
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