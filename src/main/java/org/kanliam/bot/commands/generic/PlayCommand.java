package org.kanliam.bot.commands.generic;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.VoiceChannel;
import org.apache.commons.validator.routines.UrlValidator;
import org.kanliam.bot.Main;
import org.kanliam.bot.audio.TrackScheduler;
import org.kanliam.bot.audio.TrackSchedulerManager;
import org.kanliam.bot.commands.Command;
import org.kanliam.bot.commands.CommandContext;

import java.util.Arrays;
import java.util.Objects;

public class PlayCommand extends Command {

    public PlayCommand(){
        super("play");
    }

    @Override
    public void exec(CommandContext ctx) {

        Message message = ctx.getMsg();
        TrackSchedulerManager trackSchedulerManager = Main.getTrackSchedulerManager();
        TrackScheduler trackScheduler = trackSchedulerManager.getScheduler(ctx.getMsg().getGuild());

        boolean connected = false;


        if (ctx.getArgs().length == 1){
            message.getChannel().sendMessage("Play what? you didn't enter arguments for the command").queue();
        } else {
            if(message.getMember().getVoiceState().getChannel() == null){
                message.getChannel().sendMessage("You're not connected to a channel").queue();
            } else {
                VoiceChannel channel = message.getMember().getVoiceState().getChannel();
                if(!message.getGuild().getAudioManager().isConnected()){
                    message.getGuild().getAudioManager().openAudioConnection(channel);
                    message.getChannel().sendMessage("Connecting...").queue();
                } else {
                    if(!Objects.equals(message.getGuild().getAudioManager().getConnectedChannel(), message.getMember().getVoiceState().getChannel())){
                        message.getGuild().getAudioManager().openAudioConnection(message.getMember().getVoiceState().getChannel());
                        message.getChannel().sendMessage("Moving to your channel").queue();
                    }
                }
                connected = true;
            }
            if(connected){
                int length = ctx.getArgs().length;
                if(length > 1){
                    String arg = String.join(" ", Arrays.copyOfRange(ctx.getArgs(),1, ctx.getArgs().length));
                    System.out.println(arg);
                    if(!isURLValid(arg)){
                        System.out.println("Not a link");
                        Main.getPlayerManager().loadItem("ytsearch: " + arg, new org.kanliam.bot.audio.AudioLoadResultHandler(ctx, trackScheduler));
                    } else {
                        System.out.println("Link");
                        Main.getPlayerManager().loadItem(arg, new org.kanliam.bot.audio.AudioLoadResultHandler(ctx, trackScheduler));
                    }
                }
            }
        }
    }


    public boolean isURLValid (String url){
        UrlValidator urlValidator = new UrlValidator();
        return urlValidator.isValid(url);
    }
}