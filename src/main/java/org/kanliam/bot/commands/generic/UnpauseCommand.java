package org.kanliam.bot.commands.generic;

import org.kanliam.bot.Main;
import org.kanliam.bot.commands.Command;
import org.kanliam.bot.commands.CommandContext;

public class UnpauseCommand extends Command {


    public UnpauseCommand() {
        super("unpause");
    }

    @Override
    public void exec(CommandContext ctx) {

        if(Main.getTrackSchedulerManager().getScheduler(ctx.getMsg().getGuild()).getPlayer() != null && Main.getTrackSchedulerManager().getScheduler(ctx.getMsg().getGuild()).getPlayer().getPlayingTrack() != null){
            if(Main.getTrackSchedulerManager().getScheduler(ctx.getMsg().getGuild()).getPlayer().isPaused()){
                Main.getTrackSchedulerManager().getScheduler(ctx.getMsg().getGuild()).getPlayer().setPaused(false);
                ctx.getMsg().getChannel().sendMessage("Resuming").queue();
            } else {
                ctx.getMsg().getChannel().sendMessage("The bot is not paused").queue();

            }
        } else {
            ctx.getMsg().getChannel().sendMessage("Not playing anything").queue();
        }
    }
}