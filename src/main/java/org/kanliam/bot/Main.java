package org.kanliam.bot;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import org.kanliam.bot.audio.TrackSchedulerManager;

import javax.security.auth.login.LoginException;

public class Main {
    private static JDA jda;
    private static AudioPlayerManager playerManager;
    private static TrackSchedulerManager trackSchedulerManager = new TrackSchedulerManager();
    public static void main(String[] args) {
        try{
            jda = new JDABuilder().setToken(System.getenv("TOKEN")).build();
            jda.getPresence().setPresence(Activity.playing("^^help for help"), false);
            jda.addEventListener(new EventManager());
            playerManager = new DefaultAudioPlayerManager();
            AudioSourceManagers.registerRemoteSources(playerManager);
        } catch(LoginException e){
              e.printStackTrace();
        }
    }
    public static JDA getJDA(){
        return jda;
    }

    public static AudioPlayerManager getPlayerManager() {
        return playerManager;
    }

    public static TrackSchedulerManager getTrackSchedulerManager() {
        return trackSchedulerManager;
    }
}