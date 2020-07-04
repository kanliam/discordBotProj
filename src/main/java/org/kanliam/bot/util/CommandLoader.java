package org.kanliam.bot.util;

import org.kanliam.bot.commands.CommandManager;
import org.kanliam.bot.commands.generic.*;

public class CommandLoader {


    // Make new instances of the commands and loads them into the command manager (cmm)
    public static void load(CommandManager cmm){

        cmm.addCommand(new ConnectCommand());
        cmm.addCommand(new CurrentCommand());
        cmm.addCommand(new DateCommand());
        cmm.addCommand(new EchoCommand());
        cmm.addCommand(new HelpCommand());
        cmm.addCommand(new InviteCommand());
        cmm.addCommand(new LeaveCommand());
        cmm.addCommand(new PauseCommand());
        cmm.addCommand(new PingCommand());
        cmm.addCommand(new PlayCommand());
        cmm.addCommand(new PurgeCommand());
        cmm.addCommand(new QueueCommand());
        cmm.addCommand(new SkipCommand());
        cmm.addCommand(new TimeCommand());
        cmm.addCommand(new UnpauseCommand());
        cmm.addCommand(new WhoamiCommand());
        cmm.addCommand(new WhoareyouCommand());

    }
}