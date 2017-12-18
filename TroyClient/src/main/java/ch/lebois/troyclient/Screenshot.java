package ch.lebois.troyclient;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import javax.imageio.ImageIO;

public class Screenshot {

    public static void takeScreenshot() {
        try {
            Robot robot = new Robot();
            Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage screenFullImage = robot.createScreenCapture(screenRect);

            File file = new File(String.valueOf(new Date().getTime()) + ".jpg");

            ImageIO.write(screenFullImage, "jpg", file);
        } catch (AWTException | IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}