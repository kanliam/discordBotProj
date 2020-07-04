package org.kanliam.bot.audio;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import net.dv8tion.jda.api.entities.Guild;
import org.kanliam.bot.Main;

import java.util.HashMap;

public class TrackSchedulerManager {
    private HashMap<String, TrackScheduler> map = new HashMap<>();

    public TrackScheduler getScheduler(Guild guild){
        if(!map.containsKey(guild.getId())){
            AudioPlayerManager playerManager = Main.getPlayerManager();
            AudioPlayer player = playerManager.createPlayer();
            TrackScheduler trackScheduler = new TrackScheduler(player, guild);
            map.put(guild.getId(), trackScheduler);
        }
        return map.get(guild.getId());
    }


    public void destroyScheduler (Guild guild) throws Exception{
        if(map.containsKey(guild.getId())){
            map.get(guild.getId()).close();
            map.remove(guild.getId());
        }
    }
}