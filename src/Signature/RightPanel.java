/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Signature;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 *
 * @author Mysterious_K
 */
class RightPanel extends JPanel  implements MouseListener
{
    private int width,height;
    public JPanel recog,verify,train,info,db;
    private JLabel lt1,lt2,lr1,lr2,lv1,lv2,ld1,ld2,li1,li2;
    private int x,y;
    private JLabel copyr,copy;
    private JPanel rightpanel,leftpanel;
    private GUI gui;
     
     private TrainPanel tr;
     private RecogPanel re;
     private VerifyPanel vp;
     private InfoPanel ip;
     private DatabasePanel dp;
     
    RightPanel(GUI gui,JPanel leftpanel,int width,int height)
    {
        this.rightpanel=this;
        this.gui=gui;
        this.leftpanel=leftpanel;
        this.width=width;
        this.height=height;
        this.setLayout(null);
        x=y=0;
        train=new JPanel();
        train.setName("train");
        train.setBackground(new Color(50,195,50));
        x=50;y=40;
        train.setBounds(x,y, width/3, (int)(height/3.8));
        train.setCursor(new Cursor(Cursor.HAND_CURSOR));
        train.setLayout(null);
        lt1=new JLabel("Training");
        lt1.setForeground(Color.white);
        lt1.setBounds(20, 0, 100, 50);
        lt1.setFont(new Font("SERIF",Font.TRUETYPE_FONT,24));
        train.add(lt1);
        lt2=new JLabel("Click to Train the Sample Images");
        lt2.setForeground(Color.white);
        lt2.setBounds(10,height/7,width/3, 50);
        lt2.setFont(new Font("SERIF",Font.ITALIC,16));
        train.add(lt2);
        add(train);
        
        
        y+=10+ (int)(height/3.8);
        recog=new JPanel();
        recog.setName("recog");
        recog.setBackground(Color.BLUE);
        recog.setBounds(x,y, (int) (width/1.8)+10, (int)(height/3.8));
        recog.setCursor(new Cursor(Cursor.HAND_CURSOR));
        recog.setLayout(null);
        lr1=new JLabel("Recognition");
        lr1.setForeground(Color.white);
        lr1.setBounds(20, 0,(int)(width/1.8), 50);
        lr1.setFont(new Font("SERIF",Font.TRUETYPE_FONT,24));
        recog.add(lr1);
        lr2=new JLabel("Click here to Recognise the Sample Signature.");
        lr2.setForeground(Color.white);
        lr2.setBounds(10,height/7,(int)(width/1.8), 50);
        lr2.setFont(new Font("SERIF",Font.ITALIC,16));
        recog.add(lr2);
        add(recog);
        
        
        x+=20+(int) (width/1.8);
        verify=new JPanel();
        verify.setName("verify");
        verify.setBackground(new Color(255,128,0));
        verify.setBounds(x,y, (int) (width/4), (int)(height/3.8));
        verify.setCursor(new Cursor(Cursor.HAND_CURSOR));
        verify.setLayout(null);
        lv1=new JLabel("Verify");
        lv1.setForeground(Color.white);
        lv1.setBounds(20, 0,(int)(width/4), 50);
        lv1.setFont(new Font("SERIF",Font.TRUETYPE_FONT,24));
        verify.add(lv1);
        lv2=new JLabel("Click to Verify the Signature.");
        lv2.setForeground(Color.white);
        lv2.setBounds(10,height/7,(int)(width/4), 50);
        lv2.setFont(new Font("SERIF",Font.ITALIC,12));
        verify.add(lv2);
        add(verify);
        
        x=50;
        y+=10+ (int)(height/3.8);
        db=new JPanel();
        db.setName("db");
        db.setBackground(Color.darkGray);
        db.setBounds(x,y, (int) (width/3.6), (int)(height/3.8));
        db.setCursor(new Cursor(Cursor.HAND_CURSOR));
        db.setLayout(null);
        ld1=new JLabel("<html>Sgnatures<br>Database</html>");
        ld1.setForeground(Color.white);
        ld1.setBounds(20, 0,(int)(width/3.6), 80);
        ld1.setFont(new Font("SERIF",Font.TRUETYPE_FONT,20));
        db.add(ld1);
        ld2=new JLabel("Information of Signature Database.");
        ld2.setForeground(Color.white);
        ld2.setBounds(10,height/7,(int)(width/3.6), 50);
        ld2.setFont(new Font("SERIF",Font.ITALIC,12));
        db.add(ld2);
        add(db);
        
        x+=20+(int) (width/3.8);
        info=new JPanel();
        info.setName("info");
        info.setBackground(Color.gray);
        info.setBounds(x,y, (int) (width/3.6), (int)(height/3.8));
        info.setCursor(new Cursor(Cursor.HAND_CURSOR));
        info.setLayout(null);
        li1=new JLabel("<html>System Info &<br>Help</html>");
        li1.setForeground(Color.white);
        li1.setBounds(20, 0,(int)(width/3.6), 80);
        li1.setFont(new Font("SERIF",Font.TRUETYPE_FONT,20));
        info.add(li1);
        li2=new JLabel("Informaation and Help");
        li2.setForeground(Color.white);
        li2.setBounds(10,height/7,(int)(width/3.6), 50);
        li2.setFont(new Font("SERIF",Font.ITALIC,12));
        info.add(li2);
        add(info);
        
        copy=new JLabel("Made By : Kuldeep Saini");
        
        copy.setFont(new Font("SANS_SERIF",Font.ITALIC,20));
        copy.setForeground(Color.lightGray);
        copy.setBounds(4*width/6,10*height/11,2*width/6, height/10);
        add(copy);
        
        
    Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();
    width=(int) ((int)screen.width/1.4);
    height=(int) ((int)screen.height/1.4);
    
    
    dp=new DatabasePanel(gui,width,14*height/15);
    dp.setBounds(1,1+height/15, width, 14*height/15);
    
    tr=new TrainPanel(gui,dp,width,14*height/15);
    tr.setBounds(1,1+height/15, width, 14*height/15);
   
    re=new RecogPanel(gui,width,14*height/15);
   
    re.setBounds(1,1+height/15, width, 14*height/15);
    
    vp=new VerifyPanel(gui,width,14*height/15);
   
    vp.setBounds(1,1+height/15, width, 14*height/15);    
    
    ip=new InfoPanel(gui,width,14*height/15);
   
    ip.setBounds(1,1+height/15, width, 14*height/15);
    
     train.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.gray, Color.lightGray));
     recog.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.gray, Color.lightGray));
     verify.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.gray, Color.lightGray));
     info.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.gray, Color.lightGray));
     db.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.gray, Color.lightGray));
              
    
        train.addMouseListener(this);
        recog.addMouseListener(this);
        verify.addMouseListener(this);
        info.addMouseListener(this);
        db.addMouseListener(this);
    
    }
     @Override
  public void mouseEntered(MouseEvent e) {
        switch (e.getComponent().getName()) {
            case "train":
                train.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLACK, Color.gray));
                break;
            case "recog":
                recog.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLACK, Color.gray));
                break;
            case "verify":
                verify.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLACK, Color.gray));
                break;
            case "info":
                info.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLACK, Color.gray));
                break;
            case "db":
                db.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLACK, Color.gray));
                break;
        }
            updateUI();
     }
 @Override
  public void mouseExited(MouseEvent e) {
        switch (e.getComponent().getName()) {
            case "train":
                train.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.BLACK, Color.gray));
                break;
            case "recog":
                recog.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.BLACK, Color.gray));
                break;
            case "verify":
                verify.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.BLACK, Color.gray));
                break;
            case "info":
                info.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.BLACK, Color.gray));
                break;
            case "db":
                db.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.BLACK, Color.gray));
                break;
        
        }
            updateUI();
     }
    
 @Override
  public void mouseClicked(MouseEvent e) {
        switch (e.getComponent().getName()) {
            case "train":
                switch(dp.DefaultDBcheck())
                {
                    case 1:
                    gui.remove(rightpanel);
                    gui.remove(leftpanel);
                    gui.updateUI();
                    gui.add(tr);
                    gui.updateUI();
                        break;
                    case 0:
                        JOptionPane.showMessageDialog((Component) null,
                      "First Select Database.","",JOptionPane.WARNING_MESSAGE);
                        break;
                }
                break;
            case "recog":
                switch(dp.DefaultDBcheck())
                {
                    case 1:
                    gui.remove(rightpanel);
                    gui.remove(leftpanel);
                    gui.updateUI();
                    gui.add(re);
                    gui.updateUI();
                        break;
                    case 0:
                        JOptionPane.showMessageDialog((Component) null,
                      "First Select Database.","",JOptionPane.WARNING_MESSAGE);
               }
                break;
            case "verify":
                switch(dp.DefaultDBcheck())
                {
                    case 1:
                    gui.remove(rightpanel);
                    gui.remove(leftpanel);
                    gui.updateUI();
                    gui.add(vp);
                    gui.updateUI();
                        break;
                    case 0:
                        JOptionPane.showMessageDialog((Component) null,
                      "First Select Database.","",JOptionPane.WARNING_MESSAGE);
                        break;
                }
                break;
            case "info":
                gui.remove(rightpanel);
                gui.remove(leftpanel);
                gui.updateUI();
                gui.add(ip);
                gui.updateUI();
                break;
            case "db":
                gui.remove(rightpanel);
                gui.remove(leftpanel);
                gui.updateUI();
                gui.add(dp);
                gui.updateUI();
                break;
        }
            updateUI();
 
 }

 @Override
  public void mousePressed(MouseEvent e) {}

 @Override
  public void mouseReleased(MouseEvent e){}

}