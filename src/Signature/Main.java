/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Signature;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

import org.opencv.core.Core;

/**
 *
 * @author Mysterious_K
 */
public class Main{

    /**
     * Creates new form NewJFrame
     */
    static private JFrame mainFrame;
    static private SplashScreen splash;
    public Main() {        
    }
    
    public static void main(String args[]) {
//    	System.loadLibrary(Core.NATIVE_LIBRARY_NAME); 
                splash = new SplashScreen(1000);
                mainFrame=new JFrame("VeriSign");
                int width;
                int height;
                
                Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();
                width=(int) ((int)screen.width/1.4);
                height=(int) ((int)screen.height/1.4);
                int x = (screen.width - width) / 2;
                int y = (screen.height - height-30) / 2;
                new DatabasePanel().getDBRows();
                mainFrame.setLayout(null);
                mainFrame.setUndecorated(true);
                mainFrame.setBounds(x, y, width+2,height+2);
                mainFrame.add(new GUI(mainFrame,width,height));
                mainFrame.setVisible(true);
       
    }
     public static void loadJarDll() throws IOException {
        InputStream in = Main.class.getResourceAsStream("opencv_java300.dll");
        byte[] buffer = new byte[1024];
        int read = -1;
        File temp = File.createTempFile("opencv_java300.dll", "");
        FileOutputStream fos = new FileOutputStream(temp);
        while((read = in.read(buffer)) != -1) {
        fos.write(buffer, 0, read);
        }
        fos.close();
        in.close();
        System.load(temp.getAbsolutePath());
        }
    
    
}
