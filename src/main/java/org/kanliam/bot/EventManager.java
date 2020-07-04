package org.kanliam.bot;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.kanliam.bot.commands.CommandManager;
import org.kanliam.bot.util.CommandLoader;

import javax.annotation.Nonnull;
public class EventManager extends ListenerAdapter {
    private CommandManager cmm = new CommandManager();
    private Message message;

    public EventManager() {
        CommandLoader.load(cmm);
    }

    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event) {
        super.onMessageReceived(event);
        Message message = event.getMessage();
        cmm.handle(message);
        this.message = message;
    }

    public Message getMessage() {
        return message;
    }
}