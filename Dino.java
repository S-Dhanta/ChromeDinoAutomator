import java.awt.AWTException;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.*;

public class Dino {

    public static void main(String[] args) throws AWTException {
        Robot robot = new Robot();

        int jumpKey = KeyEvent.VK_SPACE;
        int downKey = KeyEvent.VK_DOWN;

        long start=0;
        int c_start=0;

        int x_sub;
        int x_sub_birb;
        int birb_delay=400;

        System.out.println("Program Running...");


        while (true) {
            BufferedImage screenshot = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
            Map<String,Integer> jump = new HashMap<>();
            jump.put("Yes",0);
            Map<String,Integer> down = new HashMap<>();
            down.put("Yes",0);

            //If we are on the chrome dino page, start timer
            Color temp = new Color(screenshot.getRGB(340,210));
            if(temp.getRed() == 32 && temp.getGreen() == 33 && temp.getBlue() == 36 && c_start == 0) {
                start = System.currentTimeMillis();
                c_start++;
            }
            long elapsedTimeMillis = System.currentTimeMillis()-start;
            float elapsedTimeSec = elapsedTimeMillis/1000F;
            if(elapsedTimeSec<=15.0){
                x_sub=300;
            }
            else if(elapsedTimeSec<=25.0){
                x_sub=320;
            }
            else if(elapsedTimeSec<=35.0){
                x_sub=355;
            }
            else if(elapsedTimeSec<=45.0){
                x_sub=410;
            }
            else if(elapsedTimeSec<=55.0){
                x_sub=430;
            }
            else{
                x_sub=450;
            }

            x_sub_birb=x_sub+20;

            for(int x=x_sub_birb;x>=220;x--){
                for(int y=578;y>=568;y--){
                    Color color = new Color(screenshot.getRGB(x, y));
                    if (color.getRed() == 172 | color.getRed() == 0) {
                        int t = down.get("Yes");
                        t += 1;
                        down.put("Yes", t);
                    }
                }
                if (down.get("Yes") != 0) {
                    robot.keyPress(downKey);
                    robot.delay(birb_delay);
                    robot.keyRelease(downKey);
                    break;
                }
            }

            for (int x = x_sub; x >= 240; x--) {
                for (int y = 680; y >= 631; y--) {
                    Color color = new Color(screenshot.getRGB(x, y));
                    if (color.getRed() == 172 | color.getRed() == 0) {
                        int t = jump.get("Yes");
                        t += 1;
                        jump.put("Yes", t);
                    }
                }
                if (jump.get("Yes") != 0) {
                    if(elapsedTimeSec<=60.0) {
                        robot.keyPress(jumpKey);
                        robot.delay(100);
                        robot.keyRelease(jumpKey);
                    }
                    else{
                        robot.keyPress(jumpKey);
                        robot.delay(130);
                        robot.keyRelease(jumpKey);
                        robot.keyPress(downKey);
                        robot.delay(10);
                        robot.keyRelease(downKey);
                    }
                    break;
                }
            }

            robot.delay(0);
        }
    }
}

