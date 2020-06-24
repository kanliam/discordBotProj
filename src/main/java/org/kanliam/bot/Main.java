package org.kanliam.bot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

public class Main {
    public static void main(String[] args) {
        try{
            JDA jda = new JDABuilder().setToken(System.getenv("TOKEN")).build();
            jda.addEventListener(new EventManager());
            jda.getPresence().setPresence(Activity.playing("^^help for help"), false);
        }catch(LoginException e){
              e.printStackTrace();
        }
    }
}