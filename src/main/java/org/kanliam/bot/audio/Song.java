package org.kanliam.bot.audio;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

public class Song {
    private AudioTrack track;
    private TextChannel channel;
    private User user;
    private TrackScheduler scheduler;

    public Song(AudioTrack track, TextChannel channel, User user, TrackScheduler scheduler) {
        this.track = track;
        this.channel = channel;
        this.user = user;
        this.scheduler = scheduler;
    }

    public AudioTrack getTrack() {
        return track;
    }

    public TextChannel getChannel() {
        return channel;
    }

    public User getUser() {
        return user;
    }

    public TrackScheduler getScheduler() {
        return scheduler;
    }


    //public void sendEnqueued(){
    //    channel.sendMessage(String.format(("The song %s has been added to the queue"), getName())).queue();
    //}

    /**
     * returns the name of the video
     * @return the name of the video
     */
    public String getName(){
        return getTrack().getInfo().title;
    }
}