package org.kanliam.bot.commands.generic;

import org.kanliam.bot.Main;
import org.kanliam.bot.commands.Command;
import org.kanliam.bot.commands.CommandContext;

public class CurrentCommand extends Command {

    public CurrentCommand() {
        super("current");
    }

    @Override
    public void exec(CommandContext ctx) {
        if(Main.getTrackSchedulerManager().getScheduler(ctx.getMsg().getGuild()).getPlayer().getPlayingTrack() != null){
            ctx.getMsg().getTextChannel().sendMessage("Currently playing: " + Main.getTrackSchedulerManager().getScheduler(ctx.getMsg().getGuild()).getPlayer().getPlayingTrack().getInfo().title).queue();
        } else {
            ctx.getMsg().getTextChannel().sendMessage("Currently not playing a song").queue();
        }
    }
}