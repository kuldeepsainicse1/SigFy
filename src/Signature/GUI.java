/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Signature;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;



/**
 *
 * @author Mysterious_K
 */
public class GUI extends JPanel{
     private TaskPanel TaskBar;
     private LeftPanel LeftPane;
     private RightPanel RightPane;
     private int width,height;
     private JFrame mainFrame;
     public GUI(JFrame mainFrame,int width,int height) {
         this.mainFrame=mainFrame;
        this.width=width;
        this.height=height;
        setBounds(0,0, width+2, height+2);
    
    TaskBar = new TaskPanel(width,height/15,mainFrame);
    LeftPane=new LeftPanel(width/4,14*height/15);
    RightPane=new RightPanel(this,LeftPane,3*width/4-20,14*height/15);
    
    TaskBar.setBackground(Color.white);
    LeftPane.setBackground(Color.white);
    RightPane.setBackground(Color.white);
    
    setLayout(null);
    
    TaskBar.setBounds(1, 1, width,height/15);
    LeftPane.setBounds(1,1+height/15, width/4+21, 14*height/15);
    RightPane.setBounds(width/4+21,1+height/15, 3*width/4-20, 14*height/15);
   
    setBorder(BorderFactory.createLineBorder(Color.BLACK));
    add(TaskBar);
    add(LeftPane);
    add(RightPane);
    //action();
  }
  public void MainReset()
  {
      this.removeAll();
      add(TaskBar);
      LeftPane.reset();
      add(LeftPane);
      add(RightPane);
      this.updateUI();
  }
/*
  final void action()
  {
    RightPane.train.addMouseListener(new MouseAdapter(){@Override
       public void mouseClicked(MouseEvent e) {
            
     }
    });
    
    RightPane.recog.addMouseListener(new MouseAdapter(){@Override
        public void mouseClicked(MouseEvent e) {   
            main.remove(RightPane);
            main.updateUI();
            main.add(re);
            main.updateUI();
     }});
    
    
    
      tr.home.addMouseListener(new MouseAdapter(){@Override
        public void mouseClicked(MouseEvent e) {
           tr.ResetTrainPanel();
            main.remove(tr);
            main.updateUI();
            main.add(LeftPane);
            main.add(RightPane);
            main.updateUI();
        }});
      re.home.addMouseListener(new MouseAdapter(){@Override
        public void mouseClicked(MouseEvent e) {
            re.ResetRecogPanel(); 
            main.remove(re);
          
            main.updateUI();
            main.add(RightPane);
            main.updateUI();
        }});
     
  }
*/
}
class filter1 extends javax.swing.filechooser.FileFilter 
{
    public boolean accept(File fileobj) 
    {
        String extension = "";

        if(fileobj.getPath().lastIndexOf('.') > 0)
            extension = fileobj.getPath().substring(
                fileobj.getPath().lastIndexOf('.') 
                + 1).toLowerCase();

        if(extension != "")
            return extension.equals("gif");
        else
            return fileobj.isDirectory();
    }

    public String getDescription() 
    {
        return "Gif Files (*.gif)";
    }
}

class MyFileChooser extends JFileChooser {
         protected JDialog createDialog(Component parent) throws HeadlessException {
             JDialog dialog = super.createDialog(parent);
             dialog.setLocation(300, 200);
             dialog.setResizable(false);
             return dialog;
         }
     }

class JPanelimg extends JPanel
{
     private BufferedImage img;
     private int width,height;
    public JPanelimg(String Splashimg,int width,int height)
    {
        this.width=width;
        this.height=height;
        
     try {
         img = ImageIO.read(new File(Splashimg)); 
         } catch (IOException ex) {
             System.out.printf("not found"+Splashimg);
         }
    }
     @Override
    protected void paintComponent(Graphics g)
    {
       super.paintComponent(g);
        g.drawImage(img,0,0,width,height,this);
    }
}

class JPanelimgp extends JPanel
{
     private BufferedImage img;
     private int width,height;
    public JPanelimgp(String Splashimg,int width,int height)
    {
        this.width=width;
        this.height=height;
        
     try {
         img = ImageIO.read(Main.class.getResourceAsStream(Splashimg)); 
         } catch (IOException ex) {
             System.out.printf("not found"+Splashimg);
         }
    }
     @Override
    protected void paintComponent(Graphics g)
    {
       super.paintComponent(g);
        g.drawImage(img,0,0,width,height,this);
    }
}
