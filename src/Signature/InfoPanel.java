/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Signature;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
/**
 *
 * @author Mysterious_K
 */
class InfoPanel extends JPanel implements MouseListener{
public int width,height;
    public JLabel dbinfo,sysreq,manual,instm;
    public JLabel panel;
    public JLabel sysinfo,help;
    public JLabel home;
    JPanel dbpane,syspane;
    JSeparator js;
    Font f;
   
    private int x,y;
    InfoPanel ip;
    public GUI gui;
    DatabasePanel dp;
    InfoPanel(GUI gui,final int width,final int height)
    {
        this.gui=gui;
        ip=this;
        this.width=width;
        this.height=height;
        this.setName("InfoPanel");
        this.setLayout(null);
        setBackground(Color.white);
       
        f=new Font("Matura MT Script Capitals",Font.TRUETYPE_FONT,30);
        
        x=15;y=5;
        home=new JLabel(new ImageIcon(this.getClass().getResource("house.gif")));
        home.setSize(50,50);
        home.setName("home");
        home.setForeground(Color.white);
        home.setBounds(x,y,50,50);
        home.setCursor(new Cursor(Cursor.HAND_CURSOR));
        home.setToolTipText("Click to go to Home");
        add(home);
        
        x=70;y=10;
        
        panel=new JLabel("<html>Information &<br> Help</html>");
        panel.setFont(f);
        panel.setForeground(Color.BLUE);
        panel.setBounds(x,y,350,90);
        add(panel);
        
        sysinfo=new JLabel("System Information");
        sysinfo.setForeground(new Color(50,50,155));
        sysinfo.setFont(new Font("Monotype Corsiva",Font.BOLD,30));
        sysinfo.setBounds(width/7,height/4, width/3, height/20);
        add(sysinfo);
        /*
        Train.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Train.setToolTipText("Click to Train the Samples.");
        add(Train);
        
        */
        y=height/4;
        y+=height/6;
        dbinfo=new JLabel("-> Database Information");
        dbinfo.setName("dbinfo");
        dbinfo.setForeground(Color.LIGHT_GRAY);
        dbinfo.setFont(new Font("Monotype Corsiva",Font.ITALIC,28));
        dbinfo.setBounds(width/15,y, width/3, height/20);
        add(dbinfo);
        
        y+=height/10;
        sysreq=new JLabel("-> System requirement Information");
        sysreq.setName("sysreq");
        sysreq.setForeground(Color.LIGHT_GRAY);
        sysreq.setFont(new Font("Monotype Corsiva",Font.ITALIC,28));
        sysreq.setBounds(width/15,y, (int) (width/2.5), height/20);
        add(sysreq);
        
        js=new JSeparator(1); 
        js.setBounds((int) (width/2),height/5,2,4*height/6);
        add(js);
        
        help=new JLabel("Software Help");
        help.setForeground(new Color(50,50,155));
        help.setFont(new Font("Monotype Corsiva",Font.BOLD,30));
        help.setBounds(2*width/3,height/4, width/3, height/20);
        add(help);
        
        y=height/4;
        y+=height/6;
        
        manual=new JLabel("-> User Manual");
        manual.setName("manual");
        manual.setForeground(Color.LIGHT_GRAY);
        manual.setFont(new Font("Monotype Corsiva",Font.ITALIC,28));
        manual.setBounds(17*width/30,y, (int) (width/2.5), height/20);
        add(manual);
        
        y+=height/10;
        
        instm=new JLabel("-> Installation Information");
        instm.setName("instm");
        instm.setForeground(Color.LIGHT_GRAY);
        instm.setFont(new Font("Monotype Corsiva",Font.ITALIC,28));
        instm.setBounds(17*width/30,y, width/3, height/20);
        add(instm);
        
        dbinfo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        sysreq.setCursor(new Cursor(Cursor.HAND_CURSOR));
        manual.setCursor(new Cursor(Cursor.HAND_CURSOR));
        instm.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        home.addMouseListener(this);
        dbinfo.addMouseListener(this);
        sysreq.addMouseListener(this);
        manual.addMouseListener(this);
        instm.addMouseListener(this);
        
        dbpane=new JPanel();
        dbpane.setBounds(0,0, width, height);
        dbpane.setBackground(Color.white);

        JLabel[] jlb1=new JLabel[2];
        JLabel temp;
        dp=new DatabasePanel();
                dbpane.setLayout(null);
                dbpane.setBackground(Color.white);
                f=new Font("Matura MT Script Capitals",Font.TRUETYPE_FONT,30);

                x=15;y=5;           
                jlb1[0]=new JLabel(new ImageIcon(this.getClass().getResource("house.gif")));
                jlb1[0].setSize(50,50);
                jlb1[0].setName("home2");
                jlb1[0].setForeground(Color.white);
                jlb1[0].setBounds(x,y,50,50);
                jlb1[0].setCursor(new Cursor(Cursor.HAND_CURSOR));
                jlb1[0].setToolTipText("Click to go to Home");
                jlb1[0].addMouseListener(this);
                dbpane.add(jlb1[0]);
                

                x=70;y=10;
                jlb1[1]=new JLabel("Database Information");
                jlb1[1].setFont(f);
                jlb1[1].setForeground(Color.BLUE);
                jlb1[1].setBounds(x,y,350,90);
                dbpane.add(jlb1[1]);
              
                int x1=width/7,y1=height/4;
                x1=width/7;
                temp=new JLabel("Current DataBase");
                temp.setForeground(new Color(50,50,155));
                temp.setFont(new Font("Monotype Corsiva",Font.BOLD,25));
                temp.setBounds(x1,y1, width/3, height/20);
                dbpane.add(temp);
                x1+=width/3;
                temp=new JLabel("jdbc:mysql://localhost/Signature");
                temp.setForeground(new Color(150,150,255));
                temp.setFont(new Font("Monotype Corsiva",Font.BOLD,20));
                temp.setBounds(x1,y1, width/3, height/20);
                dbpane.add(temp);
                
                y1+=height/15;
                x1=width/7;
                temp=new JLabel("Total Person's in Database");
                temp.setForeground(new Color(50,50,155));
                temp.setFont(new Font("Monotype Corsiva",Font.BOLD,25));
                temp.setBounds(x1,y1, width/3, height/20);
                dbpane.add(temp);
                x1+=width/3;
                temp=new JLabel(dp.getDBRows()+"");
                temp.setForeground(new Color(150,150,255));
                temp.setFont(new Font("Monotype Corsiva",Font.BOLD,20));
                temp.setBounds(x1,y1, width/3, height/20);
                dbpane.add(temp);
               
                 y1+=height/15;
                x1=width/7;
                temp=new JLabel("Current Software Directory");
                temp.setForeground(new Color(50,50,155));
                temp.setFont(new Font("Monotype Corsiva",Font.BOLD,25));
                temp.setBounds(x1,y1, width/3, height/20);
                dbpane.add(temp);
                x1+=width/3;
                temp=new JLabel(System.getProperty("user.home")+"\\Documents\\Signature");
                temp.setForeground(new Color(150,150,255));
                temp.setFont(new Font("Monotype Corsiva",Font.BOLD,20));
                temp.setBounds(x1,y1, width/2, height/20);
                dbpane.add(temp);
               
                y1+=height/15;
                x1=width/7;
                temp=new JLabel("Feature File");
                temp.setForeground(new Color(50,50,155));
                temp.setFont(new Font("Monotype Corsiva",Font.BOLD,25));
                temp.setBounds(x1,y1, width/3, height/20);
                dbpane.add(temp);
                x1+=width/3;
                temp=new JLabel(System.getProperty("user.home")+"\\Documents\\Signature\\Features");
                temp.setForeground(new Color(150,150,255));
                temp.setFont(new Font("Monotype Corsiva",Font.BOLD,20));
                temp.setBounds(x1,y1, width/2, height/20);
                dbpane.add(temp);
                
                y1+=height/15;
                x1=width/7;
                temp=new JLabel("Model File");
                temp.setForeground(new Color(50,50,155));
                temp.setFont(new Font("Monotype Corsiva",Font.BOLD,25));
                temp.setBounds(x1,y1, width/3, height/20);
                dbpane.add(temp);
                x1+=width/3;
                temp=new JLabel(System.getProperty("user.home")+"\\Documents\\Signature\\Model");
                temp.setForeground(new Color(150,150,255));
                temp.setFont(new Font("Monotype Corsiva",Font.BOLD,20));
                temp.setBounds(x1,y1, width/2, height/20);
                dbpane.add(temp);
                
        
        
        syspane=new JPanel();
        syspane.setBounds(0,0, width, height);
        syspane.setBackground(Color.white);
        
        JLabel[] jlb=new JLabel[2];
                syspane.setLayout(null);
                syspane.setBackground(Color.white);
                f=new Font("Matura MT Script Capitals",Font.TRUETYPE_FONT,30);

                x=15;y=5;           
                jlb[0]=new JLabel(new ImageIcon(this.getClass().getResource("house.gif")));
                jlb[0].setSize(50,50);
                jlb[0].setName("home1");
                jlb[0].setForeground(Color.white);
                jlb[0].setBounds(x,y,50,50);
                jlb[0].setCursor(new Cursor(Cursor.HAND_CURSOR));
                jlb[0].setToolTipText("Click to go to Home");
                jlb[0].addMouseListener(this);
                syspane.add(jlb[0]);
                

                x=70;y=10;
                jlb[1]=new JLabel("<html>System Information</html>");
                jlb[1].setFont(f);
                jlb[1].setForeground(Color.BLUE);
                jlb[1].setBounds(x,y,350,90);
                syspane.add(jlb[1]);
              
                y1=height/4;
                x1=width/7;
                temp=new JLabel("System Operating System");
                temp.setForeground(new Color(50,50,155));
                temp.setFont(new Font("Monotype Corsiva",Font.BOLD,25));
                temp.setBounds(x1,y1, width/3, height/20);
                syspane.add(temp);
                x1+=width/2.5;
                temp=new JLabel(System.getProperty("os.name"));
                temp.setForeground(new Color(150,150,255));
                temp.setFont(new Font("Monotype Corsiva",Font.BOLD,20));
                temp.setBounds(x1,y1, width/3, height/20);
                syspane.add(temp);
                
                y1+=height/15;
                x1=width/7;
                temp=new JLabel("System Architecture");
                temp.setForeground(new Color(50,50,155));
                temp.setFont(new Font("Monotype Corsiva",Font.BOLD,25));
                temp.setBounds(x1,y1, width/3, height/20);
                syspane.add(temp);
                x1+=width/2.5;
                temp=new JLabel(System.getProperty("os.arch"));
                temp.setForeground(new Color(150,150,255));
                temp.setFont(new Font("Monotype Corsiva",Font.BOLD,20));
                temp.setBounds(x1,y1, width/3, height/20);
                syspane.add(temp);
                
                y1+=height/15;
                x1=width/7;
                temp=new JLabel("Operating System Verion");
                temp.setForeground(new Color(50,50,155));
                temp.setFont(new Font("Monotype Corsiva",Font.BOLD,25));
                temp.setBounds(x1,y1, width/3, height/20);
                syspane.add(temp);
                x1+=width/2.5;
                temp=new JLabel(System.getProperty("os.version"));
                temp.setForeground(new Color(150,150,255));
                temp.setFont(new Font("Monotype Corsiva",Font.BOLD,20));
                temp.setBounds(x1,y1, width/3, height/20);
                syspane.add(temp);
                
                y1+=height/15;
                x1=width/7;
                temp=new JLabel("Java Runtime Version");
                temp.setForeground(new Color(50,50,155));
                temp.setFont(new Font("Monotype Corsiva",Font.BOLD,25));
                temp.setBounds(x1,y1, width/3, height/20);
                syspane.add(temp);
                x1+=width/2.5;
                temp=new JLabel(System.getProperty("java.version"));
                temp.setForeground(new Color(150,150,255));
                temp.setFont(new Font("Monotype Corsiva",Font.BOLD,20));
                temp.setBounds(x1,y1, width/3, height/20);
                syspane.add(temp);
                
                
                y1+=height/15;
                x1=width/7;
                temp=new JLabel("User's home directory");
                temp.setForeground(new Color(50,50,155));
                temp.setFont(new Font("Monotype Corsiva",Font.BOLD,25));
                temp.setBounds(x1,y1, width/3, height/20);
                syspane.add(temp);
                x1+=width/2.5;
                temp=new JLabel(System.getProperty("user.home"));
                temp.setForeground(new Color(150,150,255));
                temp.setFont(new Font("Monotype Corsiva",Font.BOLD,20));
                temp.setBounds(x1,y1, width/3, height/20);
                syspane.add(temp);
                
                y1+=height/15;
                x1=width/7;
                temp=new JLabel("Software current directory");
                temp.setForeground(new Color(50,50,155));
                temp.setFont(new Font("Monotype Corsiva",Font.BOLD,25));
                temp.setBounds(x1,y1, width/3, height/20);
                syspane.add(temp);
                x1+=width/2.5;
                temp=new JLabel(System.getProperty("user.home")+"\\Documents\\Signature");
                temp.setForeground(new Color(150,150,255));
                temp.setFont(new Font("Monotype Corsiva",Font.BOLD,20));
                temp.setBounds(x1,y1, width/2, height/20);
                syspane.add(temp);
                
                
        
        
       
    }
    public void ResetInfoPanel()
    {
        ip.removeAll();
        add(home);
        add(panel);
        add(sysinfo);
        add(dbinfo);
        add(sysreq);
        add(js);
        add(help);
        add(manual);
        add(instm);
        dbinfo.setForeground(Color.lightGray);
        sysreq.setForeground(Color.lightGray);
        manual.setForeground(Color.lightGray);
        instm.setForeground(Color.lightGray);
        
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //String acro="C:\\Program Files (x86)\\Adobe\\Reader 9.0\\Reader\\acrord32 ";
    switch (e.getComponent().getName()) {
        case "home": 
            ip.ResetInfoPanel();
            gui.MainReset();
            break;
        case "home1":
            ip.ResetInfoPanel();
            gui.MainReset();
            break;
        case "home2":
            ip.ResetInfoPanel();
            gui.MainReset();
            break;
                
        case "dbinfo":
            ip.removeAll(); 
            ip.add(dbpane);
            ip.updateUI();
            break;
        case "sysreq":
            ip.removeAll(); 
            ip.add(syspane);
            ip.updateUI();
            break;
            
        case "manual":
            String path=""+this.getClass().getResource("Manual.txt");
            path=path.substring(6,path.length());
            try {
                  Runtime.getRuntime().exec("notepad "+path);
                } catch (IOException ex) {
                
            }
            break;
            
        case "instm":
            String path1=""+this.getClass().getResource("SysReq.txt");
            path1=path1.substring(6,path1.length());
            try {
                  Runtime.getRuntime().exec("notepad "+path1);
                } catch (IOException ex) {
                
            }
            break;
    }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
       
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    switch (e.getComponent().getName()) {
        case "dbinfo":
            dbinfo.setForeground(Color.gray);
            break;
        case "sysreq":
            sysreq.setForeground(Color.gray);
            break;
        case "manual":
            manual.setForeground(Color.gray);
            break;
        case "instm":
            instm.setForeground(Color.gray);
            break;
       }
                updateUI();
    }

    @Override
    public void mouseExited(MouseEvent e) {
    switch (e.getComponent().getName()) {
        case "dbinfo":
            dbinfo.setForeground(Color.lightGray);
            break;
        case "sysreq":
            sysreq.setForeground(Color.lightGray);
            break;
        case "manual":
            manual.setForeground(Color.lightGray);
            break;
        case "instm":
            instm.setForeground(Color.lightGray);
            break;
       }
                updateUI();
    }


}
