package org.kanliam.bot.commands.generic;

import net.dv8tion.jda.api.entities.Message;
import org.kanliam.bot.Main;
import org.kanliam.bot.commands.Command;
import org.kanliam.bot.commands.CommandContext;

public class LeaveCommand extends Command {

    public LeaveCommand() {
        super("leave");
    }

    @Override
    public void exec(CommandContext ctx) {
        Message message = ctx.getMsg();
        if(!message.getGuild().getAudioManager().isConnected()){
            message.getChannel().sendMessage("The bot is not connected to a channel").queue();
        } else {
            message.getChannel().sendMessage("Leaving...").queue();
            Main.getTrackSchedulerManager().getScheduler(ctx.getMsg().getGuild()).emptyQueue();
            if(Main.getTrackSchedulerManager().getScheduler(ctx.getMsg().getGuild()).isPlaying()){
                try {
                    Main.getTrackSchedulerManager().destroyScheduler(message.getGuild());
                } catch (Exception e) {
                    System.out.println("Exception in LeaveCommand");
                    e.printStackTrace();
                }
            }
            message.getGuild().getAudioManager().closeAudioConnection();
        }
    }
}