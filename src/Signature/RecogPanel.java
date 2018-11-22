/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Signature;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EtchedBorder;

class RecogPanel extends JPanel
{
    private int width,height;
    public JLabel home;
    public JButton sample;
    private JPanel img,simg;
    public JDialog fc;
    Font f;
    private int x,y;
    private String filepath;
    public JProgressBar jpb;
    private JPanel recognise;
    public JPanel rechome,rechomeb;
    private JLabel resultidl,resultnamel;
    public JLabel resulidt,resultnamet;
    private RecogPanel recp;
    boolean imgset;
    GUI gui;
    RecogPanel(GUI gui,final int width,final int height)
    {
        this.gui=gui;
        recp=this;
        filepath="";
        imgset=false;
        this.width=width;
        this.height=height;
        this.setName("RecogPanel");
        this.setLayout(null);
        setBackground(Color.white);
        x=y=0;
        x=20;y=20;
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
        
        JLabel panel=new JLabel("Signature Recognition");
        panel.setFont(f);
        panel.setForeground(Color.BLUE);
        panel.setBounds(x,y,350,50);
        add(panel);
        
       
        
        x=(int) (width/3);y=70;
        simg=new JPanelimgp("SigSample.jpg",220,height/4);
        System.out.print("frrfr"+(height/4));
        simg.setName("sample");
        simg.setCursor(new Cursor(Cursor.HAND_CURSOR));
        simg.setToolTipText("Browse Signature image for Recognition");
        simg.setBounds(width/2-110, y,220, height/4);
        simg.setBorder(BorderFactory.createLineBorder(Color.BLUE,2));
        add(simg);
          
        sample=new JButton("Browse Image");
        
        sample.setCursor(new Cursor(Cursor.HAND_CURSOR));
        sample.setToolTipText("Click to Select Signature image for Recognition");
        f=new Font("SANS_SERIF",Font.ITALIC,18);
        sample.setFont(f);
        sample.setForeground(Color.BLUE);
        x=120+width/2;
        y=70+height/4-30;
        sample.setBounds(x,y,150,30);
        add(sample);
                    
        
        
        
        recognise=new JPanel();
        recognise.setName("recognise");
        recognise.setCursor(new Cursor(Cursor.HAND_CURSOR));
        recognise.setBackground(new Color(150,150,255));
        recognise.setToolTipText("Click to Recognise Signature.");
                        
        recognise.setBounds(5*width/12, (int) (height/2.1), width/6, height/13);
        JLabel recl=new JLabel("Recognise");
        recl.setFont(new Font("SERIF",Font.ITALIC,20));
        recl.setForeground(Color.WHITE);
        recognise.add(recl);
        add(recognise);
        
        
        rechome=new JPanel();
        rechome.setName("rechome");
        rechome.setCursor(new Cursor(Cursor.HAND_CURSOR));
        rechome.setBackground(new Color(55,55,55));
        rechome.setToolTipText("Go to home.");                      
        rechome.setBounds(5*width/12, (int) (height/2.1), width/6, height/13);
        JLabel reclh=new JLabel("Go To Home");
        reclh.setFont(new Font("SERIF",Font.ITALIC,20));
        reclh.setForeground(Color.WHITE);
        rechome.add(reclh);
        rechome.setVisible(false);
        add(rechome);
        
        rechomeb=new JPanel();
        rechomeb.setName("rechomeb");
        rechomeb.setCursor(new Cursor(Cursor.HAND_CURSOR));
        rechomeb.setBackground(new Color(55,55,55));
        rechomeb.setToolTipText("Recognisation is Running.");                      
        rechomeb.setBounds(5*width/12, (int) (height/2.1), width/6, height/13);
        JLabel reclhb=new JLabel("Recognise");
        reclhb.setFont(new Font("SERIF",Font.ITALIC,20));
        reclhb.setForeground(Color.WHITE);
        rechomeb.add(reclhb);
        rechomeb.setVisible(false);
        add(rechomeb);
        
        
        jpb = new JProgressBar();
        jpb.setStringPainted(true);
        jpb.setValue(0);
        
        jpb.setBounds(30, (int) (height/1.6), width-60, height/20);
        add(jpb);
        
        resultidl=new JLabel("Recognied Person's ID:");
        resultidl.setBounds((int) (width/15),9*height/10, (int) (width/4.5), height/15);
        f=new Font("SANS_SERIF",Font.ITALIC,20);
        
        resultidl.setFont(f);
        resultidl.setBackground(Color.BLUE);
        //add(resultidl);
        
        resulidt=new JLabel();
        resulidt.setBounds(39*width/135,9*height/10, 57*width/270, height/15);
        f=new Font("SANS_SERIF",Font.BOLD,30);
        
        resulidt.setFont(f);
        resulidt.setBackground(Color.BLUE);
        //add(resulidt);
        
        resultnamel=new JLabel("Recognied Person's Name:");
        resultnamel.setBounds((int) (width/2),9*height/10, width/4, height/15);
        f=new Font("SANS_SERIF",Font.ITALIC,20);
        
        resultnamel.setFont(f);
        resultnamel.setBackground(Color.BLUE);
        //add(resultnamel);
        
        resultnamet=new JLabel();
        resultnamet.setBounds(6*width/8,9*height/10, 2*width/8, height/15);
        f=new Font("SANS_SERIF",Font.BOLD,30);
        
        resultnamet.setFont(f);
        resultnamet.setBackground(Color.BLUE);
        //add(resultnamet);
        
       
        home.addMouseListener(new MouseAdapter(){
        public void mouseClicked(MouseEvent me)
        {
                recp.ResetRecogPanel();
                recp.gui.MainReset();
        }
        });
        
        rechome.addMouseListener(new MouseAdapter(){
        public void mouseClicked(MouseEvent me)
        {
                recp.ResetRecogPanel();
                recp.gui.MainReset();
        }
        });
        
        
        sample.addMouseListener(new MouseAdapter(){
        public void mouseClicked(MouseEvent me)
        {
            JFileChooser chooser = new JFileChooser("F:\\");
                int res = chooser.showOpenDialog(null);
                File fileobj = chooser.getSelectedFile();
                chooser.addChoosableFileFilter(new filter1());
                if(res == JFileChooser.APPROVE_OPTION) {
                    filepath=fileobj.getPath();
                    simg.setVisible(false);
                    sample.setVisible(false);
                    filepath=filepath.replace("\\", "\\\\");
                    System.out.print(""+filepath);
                    img=new JPanelimg(filepath, 220,height/4);
                    img.setBounds(width/2-110,70,220, height/4);
                    img.setBorder(BorderFactory.createLineBorder(Color.BLUE,2));
                    imgset=true;
                    add(img);
                    updateUI();
                }
        }});
        recognise.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e) 
            {
                            if(imgset)
                            {
                                RecogSignature sig=new RecogSignature(filepath,recp);
                                RecogProgress prg=new RecogProgress(recp,sig);
                                System.out.print(sig.getPriority()+"G:"+prg.getPriority());
                                prg.setPriority(1);
                                recognise.setVisible(false);
                                rechomeb.setVisible(true);
                            }
                            else
                            {
                               JOptionPane.showMessageDialog((Component) null, 
                              "Please Select Sample Signatures.","",JOptionPane.WARNING_MESSAGE);
                            }
                
            }
            public void mouseEntered(MouseEvent e) {
                    recognise.setBackground(new Color(190,190,255));
                    recognise.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED,new Color(200,200,255), Color.lightGray));
                    updateUI();
            }
            public void mouseExited(MouseEvent e) {
                    recognise.setBackground(new Color(150,150,255));
                    recognise.setBorder(null);
                    updateUI();
            }
            
        });
        
    }
    public void setResult(double res)
    {
        int uid;
        String u=res+"";
        u=u.substring(0, u.length()-2);
        uid = Integer.parseInt(""+u);
        DatabasePanel db=new DatabasePanel();
        resulidt.setText(db.getID(uid));
        resultnamet.setText(db.getName(uid));
        this.add(resultidl);
        this.add(resulidt);
        this.add(resultnamel);
        this.add(resultnamet);
        recp.updateUI();
    }
    void ResetRecogPanel()
    {
        if(imgset)
        {
            remove(img);
            imgset=false;
        }
        simg.setVisible(true);
        sample.setVisible(true);
                    
        this.jpb.setValue(0);
        this.resulidt.setText("");
        this.resultnamet.setText("");
        this.remove(resultidl);
        this.remove(resulidt);
        this.remove(resultnamel);
        this.remove(resultnamet);
        recognise.setVisible(true);
        rechome.setVisible(false);
        rechomeb.setVisible(false);
        updateUI();
    }

}
class RecogProgress extends Thread
{
    RecogPanel recp;
    RecogSignature sig;
    int curr,state;
    RecogProgress(RecogPanel recp,RecogSignature sig)
    {
        curr=0;
        state=0;
        this.sig=sig;
        this.recp=recp;
        start();
    }
    public void run()
    {
        while(true)
        {
            if(curr!=sig.getstate())
            {
                    state=sig.getstate();
                    for(int i=10*curr;i<=10*state;i++)
                    {
                       // System.out.print("\n:"+curr+"::"+state);
                         try {
                         recp.jpb.setValue(i);
                         recp.updateUI();
                         Thread.sleep(50);
                         } catch (InterruptedException ex) { }
                    }
                    curr=state;
            }
            //System.out.print(" "+train.getstate());
            if(recp.resulidt.getText().length()!=0)
            {
                recp.jpb.setValue(100);
                break;
            }
            
            
          }
        recp.rechomeb.setVisible(false);
        recp.rechome.setVisible(true);
             
        }
}
