/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Signature;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author Mysterious_K
 */
class LeftPanel extends JPanel
{
    private JPanel logo;
    private JSeparator sep;
    public JPanel ref,notifi,info;
    public int width,height;
    private int x,y;
    
    JLabel currdb,currdbt,lastUpdate,lastUpdatet,totperson,totpersont;
    LeftPanel(int width,int height)
    {
        
        this.width=width;
        this.height=height;
        logo=new JPanelimgp("logo.gif",width-40,height/4);
        logo.setBackground(Color.white);
        this.setLayout(null);    
        y=10;
        logo.setBounds(40,y, width-40, height/4);
        add(logo);
        sep=new JSeparator();
        y+=15+height/4;
        sep.setBounds(35,y,width-30,5);
        sep.setForeground(Color.lightGray);
        add(sep);
        ref=new JPanelimgp("ref1.gif",width/4,width/4);
        y+=15;
        ref.setBackground(Color.white);
        
        
        notifi=new JPanel();
        notifi.setBackground(Color.white);
        notifi.setBounds(40+width/4,y, 3*width/4-30, width/4);
        notifi.setLayout(null);
        add(notifi);
        sep=new JSeparator();
        y+=15+width/4;
        sep.setBounds(35,y,width-30,5);
        sep.setForeground(Color.lightGray);
        add(sep);
        info=new JPanel();
        info.setBackground(Color.white);
        y+=20;
        info.setBounds(30,y,width-20,height-y-50);
        info.setLayout(null);
        
        
        currdb=new JLabel("Current Database");
        totperson=new JLabel("Total Persons Added");
        lastUpdate=new JLabel("Last Signature Update");
        
        currdbt=new JLabel("SIGNATURE");
        
        
        totpersont=new JLabel();
        lastUpdatet=new JLabel();
        
        Font f=new Font("Monotype Corsiva",Font.TRUETYPE_FONT,22);
        currdb.setFont(f);
        currdb.setForeground(Color.blue);
        
        totperson.setFont(f);
        totperson.setForeground(Color.blue);
        
        lastUpdate.setFont(f);
        lastUpdate.setForeground(Color.blue);
        
        f=new Font("Monotype Corsiva",Font.ITALIC,20);
        currdbt.setFont(f);
        currdbt.setForeground(Color.gray);
        
        totpersont.setFont(f);
        totpersont.setForeground(Color.gray);
        
        lastUpdatet.setFont(f);
        lastUpdatet.setForeground(Color.gray);
        
        x=0;y=10;
        currdb.setBounds(x, y, (int) (width/1.1), height/10);
        y+=height/8;
        totperson.setBounds(x, y, (int) (width/1.1), height/10);
        y+=height/8;
        lastUpdate.setBounds(x, y, (int) (width/1.1), height/10);
        
        x=(int) (width/2.7);y=10+height/20;
        currdbt.setBounds(x, y, width/2, height/10);
        y+=height/8;
        totpersont.setBounds(x, y, width/2, height/10);
        y+=height/8;
        lastUpdatet.setBounds(x, y, width/2, height/10);
        
        info.add(currdb);
        info.add(currdbt);
        info.add(totperson);
        info.add(totpersont);
        info.add(lastUpdate);
        info.add(lastUpdatet);
        add(info);
        new LeftProg(this);
    }
    public void reset()
    {
        
        DatabasePanel db=new DatabasePanel();
        totpersont.setText(""+db.getDBRows());
        lastUpdatet.setText(db.getLastUpdate());
        this.updateUI();
    }
}
class LeftProg extends Thread
{
    LeftPanel lp;
    boolean error1,error2,error3,error4;
    LeftProg(LeftPanel lp)
    {
       this.lp=lp;
       error1=error2=error3=error4=false;
       start();
    }
    @Override
    public void run()
    {
        JLabel noti=new JLabel();
        noti.setForeground(new Color(10,10,255));
        noti.setFont(new Font("Monotype Corsiva",Font.ITALIC,17));
        lp.notifi.add(noti);
        try{
                for(int i=0;i<62;i++)
                {   
                    lp.ref=new JPanelimgp("ref"+((i%3)+1)+".gif",lp.width/4,lp.width/4);
                    lp.ref.setBounds(30,40+lp.height/4, lp.width/4, lp.width/4);
                    lp.add(lp.ref);
                    if(i==10)
                    {
                        noti.setText("Loading Modules..");
                            try {
                                Main.loadJarDll();
                                } 
                            catch (Exception ex) {
                                System.out.print("\n\n1111"+ex);
                                  error1=true;
                                }              
                    }
                    else if(i==20)
                    {
                            try {
                                Class.forName("com.mysql.jdbc.Driver");
                                } 
                            catch (Exception ex) {
                                 System.out.print("\n\n2222"+ex);
                                  error2=true;
                                }              
                    }
                    else if(i==30)
                        noti.setText("Processing Model File..");
                    else if(i==40)
                    {
                        noti.setText("Processing Features File..");
                    }
                    else if(i==55)
                        noti.setText("Processing DataBase..");
                   noti.setBounds(0, 20,3*lp.width/4-30,lp.width/8);
                    lp.notifi.add(noti);
                    
                    lp.ref.updateUI();
                    //System.out.print("C:\\c\\ref"+((i%2)+1)+".gif");
                    
                    lp.updateUI();
                    Thread.sleep(150);
                    lp.remove(lp.ref);
                    lp.notifi.remove(noti);
                }
                if(!error1&&!error2&&!error3&&!error4)
                {
                    lp.ref=new JPanelimgp("refcomp.gif",lp.width/4,lp.width/4);
                    lp.ref.setBounds(30,40+lp.height/4, lp.width/4, lp.width/4);         
                    lp.add(lp.ref);
                    noti.setText("Processing Complete..");
                    noti.setBounds(0, 20,3*lp.width/4-30,lp.width/8);
                    lp.notifi.add(noti);
                }
                else
                {
                    lp.ref=new JPanelimgp("refcomperror.gif",lp.width/4,lp.width/4);
                    lp.ref.setBounds(30,40+lp.height/4, lp.width/4, lp.width/4);         
                    lp.add(lp.ref);
                   noti.setForeground(new Color(255,10,10));
                   String err="",er="Check for Errors";
                   if(error1)
                       err+= "Check OpenCV Module.\n";
                   if(error2)
                       err+= "Check MySQL installation.\n";
                   if(error3)
                       err+= "Check Database File.\n";
                   if(error4)
                       err+= "Check Features File.\n";
           
                   noti.setText(er);
                   final String errormsg=err;
                    noti.setBounds(0, 20,3*lp.width/4-30,lp.width/8);
                    noti.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    noti.addMouseListener(new MouseAdapter(){
                            public void mouseClicked(MouseEvent me)
                            {
                                JOptionPane.showMessageDialog((Component) null, 
                              errormsg,"Warning",JOptionPane.WARNING_MESSAGE);
                                if(error1)
                                {
                                 JOptionPane.showMessageDialog((Component) null, 
                              "Reinstall Software to fix the OpenCV Module","Fatal Error",JOptionPane.WARNING_MESSAGE);
                                   
                                }
                            }
                    });
                    lp.notifi.add(noti);
                    
                }
                lp.reset();
                lp.updateUI();
        }
        catch(Exception ex)
        {
            System.out.printf("\nLeftProgress"+ex);
            
        }
        
    }
}