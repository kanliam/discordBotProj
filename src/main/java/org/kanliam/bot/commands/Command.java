package org.kanliam.bot.commands;

public abstract class Command {
    private String name;

    public Command(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public abstract void exec(CommandContext ctx);
}