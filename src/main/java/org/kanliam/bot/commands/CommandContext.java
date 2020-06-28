package org.kanliam.bot.commands;

import net.dv8tion.jda.api.entities.Message;

public class CommandContext {
    private Message msg;
    private String[] args;

    public CommandContext(Message msg, String[] args) {
        this.msg = msg;
        this.args = args;
    }

    public Message getMsg() {
        return msg;
    }

    public String[] getArgs() {
        return args;
    }
}