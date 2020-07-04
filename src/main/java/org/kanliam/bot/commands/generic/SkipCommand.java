package org.kanliam.bot.commands.generic;

import org.kanliam.bot.Main;
import org.kanliam.bot.commands.Command;
import org.kanliam.bot.commands.CommandContext;

public class SkipCommand extends Command {


    public SkipCommand() {
        super("skip");
    }

    @Override
    public void exec(CommandContext ctx) {

        // Checks if it's playing anything
        if(Main.getTrackSchedulerManager().getScheduler(ctx.getMsg().getGuild()).getPlayer() != null && Main.getTrackSchedulerManager().getScheduler(ctx.getMsg().getGuild()).getPlayer().getPlayingTrack() != null) {
                ctx.getMsg().getTextChannel().sendMessage("Skipping: " + Main.getTrackSchedulerManager().getScheduler(ctx.getMsg().getGuild()).getPlayer().getPlayingTrack().getInfo().title).queue();
                if(Main.getTrackSchedulerManager().getScheduler(ctx.getMsg().getGuild()).getQueue().peek() != null){
                    Main.getTrackSchedulerManager().getScheduler(ctx.getMsg().getGuild()).getPlayer().stopTrack();
                } else {
                    if(Main.getTrackSchedulerManager().getScheduler(ctx.getMsg().getGuild()).isPlaying()){
                        try {
                            Main.getTrackSchedulerManager().destroyScheduler(ctx.getMsg().getGuild());
                        } catch (Exception e) {
                            System.out.println("Exception in SkipCommand");
                            e.printStackTrace();
                        }
                    }
                }
        // Prints a message to the user that states that there are no songs currently playing, so there's nothing to skip
        } else {
            ctx.getMsg().getTextChannel().sendMessage("Not playing anything").queue();
        }
    }
}