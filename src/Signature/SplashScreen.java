
package Signature;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

import javax.swing.JPanel;
import javax.swing.JWindow;

class SplashScreen extends JWindow {
  private int duration;
  private String img;
  public SplashScreen(int d) {
    duration = d;
    img="Splash.jpg";//Users\\Mysterious_K\\Documents\\polarbear.gif";
    JPanel content = (JPanel)new SplashImg(img);
    content.setBackground(Color.white);
    int width;
    int height;
    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    width=400;
    height=200;
    int x = (screen.width - width) / 2;
    int y = (screen.height - height) / 2;
    setBounds(x, y, width,height);
    content.setSize(width,height);
   // content.add(img);
   // Color oraRed = new Color(156, 20, 20, 255);
    //content.setBorder(BorderFactory.createLineBorder(oraRed, 10));

setOpacity((float) 0.6);
content.setOpaque(false);
this.add(content);

setVisible(true);
    try {
      Thread.sleep(duration);
    } catch (Exception e) {
    }
   this.dispose();
  }

    private SplashScreen() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
  
   
}
class SplashImg extends JPanel
{
      private BufferedImage img;

    public SplashImg(String Splashimg)
    {
        
     try {
         img = ImageIO.read(Main.class.getResourceAsStream(Splashimg)); 
         } catch (IOException ex) {
         }
    }
     @Override
    protected void paintComponent(Graphics g)
    {
       super.paintComponent(g);
        g.drawImage(img,0,0,this);
    }
}