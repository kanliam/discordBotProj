package org.kanliam.bot.commands.generic;

import net.dv8tion.jda.api.JDA;
import org.kanliam.bot.commands.Command;
import org.kanliam.bot.commands.CommandContext;

import java.time.temporal.ChronoUnit;

public class PingCommand extends Command {


    public PingCommand() {
        super("ping");
    }

    @Override
    public void exec(CommandContext ctx) {
        JDA jda = ctx.getMsg().getJDA();
        ctx.getMsg().getChannel().sendMessage("Calculating...").queue((msg -> {
            long ping = ctx.getMsg().getTimeCreated().until(msg.getTimeCreated(), ChronoUnit.MILLIS);
            msg.editMessage(String.format("Ping: %d ms\nWebsocket: %d ms", ping, jda.getGatewayPing())).queue();
        }));
    }
}
