package org.kanliam.bot.commands.generic;

import org.kanliam.bot.Main;
import org.kanliam.bot.commands.Command;
import org.kanliam.bot.commands.CommandContext;

public class PauseCommand extends Command {


    public PauseCommand() {
        super("pause");
    }

    @Override
    public void exec(CommandContext ctx) {

        if(Main.getTrackSchedulerManager().getScheduler(ctx.getMsg().getGuild()).getPlayer() != null && Main.getTrackSchedulerManager().getScheduler(ctx.getMsg().getGuild()).getPlayer().getPlayingTrack() != null){
            if(!Main.getTrackSchedulerManager().getScheduler(ctx.getMsg().getGuild()).getPlayer().isPaused()){
                Main.getTrackSchedulerManager().getScheduler(ctx.getMsg().getGuild()).getPlayer().setPaused(true);
                ctx.getMsg().getChannel().sendMessage("Pausing").queue();
            } else {
                ctx.getMsg().getChannel().sendMessage("Paused already").queue();
            }
        } else {
            ctx.getMsg().getChannel().sendMessage("Not playing anything").queue();
        }
    }
}








/*

if(Main.getTrackSchedulerManager().getScheduler(ctx.getMsg().getGuild()).getPlayer() != null && Main.getTrackSchedulerManager().getScheduler(ctx.getMsg().getGuild()).getPlayer().getPlayingTrack() != null){
            if(!Main.getTrackSchedulerManager().getScheduler(ctx.getMsg().getGuild()).getPlayer().isPaused()){
                Main.getTrackSchedulerManager().getScheduler(ctx.getMsg().getGuild()).getPlayer().setPaused(true);
                ctx.getMsg().getChannel().sendMessage("Pausing").queue();
            } else {
                Main.getTrackSchedulerManager().getScheduler(ctx.getMsg().getGuild()).getPlayer().setPaused(false);
                ctx.getMsg().getChannel().sendMessage("Resuming").queue();
            }
        } else {
            ctx.getMsg().getChannel().sendMessage("Not playing anything").queue();
        }


 */











