package org.kanliam.bot.util;

import org.kanliam.bot.commands.CommandManager;
import org.kanliam.bot.commands.generic.*;

import java.util.Queue;

public class CommandLoader {


    // Make new instances of the commands and loads them into the command manager (cmm)
    public static void load(CommandManager cmm){
        cmm.addCommand(new EchoCommand());
        cmm.addCommand(new DateCommand());
        cmm.addCommand(new HelpCommand());
        cmm.addCommand(new LeaveCommand());
        cmm.addCommand(new InviteCommand());
        cmm.addCommand(new ConnectCommand());
        cmm.addCommand(new PingCommand());
        cmm.addCommand(new WhoareyouCommand());
        cmm.addCommand(new TimeCommand());
        cmm.addCommand(new WhoamiCommand());
        cmm.addCommand(new TryCommand());
        cmm.addCommand(new QueueCommand());
    }
}