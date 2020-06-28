package org.kanliam.bot.commands;

import net.dv8tion.jda.api.entities.Message;

import java.util.HashMap;

public class CommandManager {
    private HashMap<String, Command> commands = new HashMap<>();

    public void addCommand(Command cmd) {
        commands.put(cmd.getName(), cmd);
    }

    public void handle(Message msg){
        String prefix = "^^";
        if(msg.getContentDisplay().startsWith(prefix)){
            String content = msg.getContentDisplay().substring(prefix.length());
            String[] anArray  = content.split(" ");
            String commandName = anArray[0];
            if(commands.containsKey(commandName)){
                commands.get(commandName).exec(new CommandContext(msg, anArray));
            }
        }
    }
}