/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Signature;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.EtchedBorder;

class TrainPanel extends JPanel
{
    public int width,height;
    public JLabel temp;
    public JLabel sample,panel;
    public JLabel uid,Id,Name;
    public JLabel home;
    JPanel Train;
    public JPanel Trainhome,Trainhomeb;
    public JTextField uidt,Idt,Namet;
    public JPanel[] imgl;
    public JPanel[] img;
    public JLabel[] rec_l,rec_r;
    boolean[] imgset;
    public JDialog fc;
    Font f;
    String[] fp;
    private int x,y;
    TrainPanel tp;
    Float PersonID;
    public GUI gui;
    DatabasePanel dp;
    TrainPanel(GUI gui,DatabasePanel dp,final int width,final int height)
    {
        this.gui=gui;
        tp=this;
        this.dp=dp;
        this.width=width;
        this.height=height;
        this.setName("TrainPanel");
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
        
        panel=new JLabel("Train Signature");
        panel.setFont(f);
        panel.setForeground(Color.BLUE);
        panel.setBounds(x,y,250,50);
        add(panel);
        
        uid  =new JLabel("Unique Database Id");
        Id  =new JLabel("Person's Id");
        Name=new JLabel("Person's Name");
        uidt=new JTextField(""+(tp.dp.getDBRows()+1));
        Idt=new JTextField();
        Namet=new JTextField();
        f=new Font("SANS_SERIF",Font.ITALIC,20);
        x=width/15;y=height/4;
        
        
        uid.setForeground(Color.BLUE);
        uid.setFont(f);
        uid.setBounds(x, y, width/5, height/20);
        add(uid);
        x+=width/5;
        uidt.setEditable(false);
        uidt.setForeground(Color.BLUE);
        uidt.setFont(f);
        uidt.setBounds(x, y, width/5, height/20);
        add(uidt);
        x=width/15;y+=20+height/20;
        Id.setForeground(Color.BLUE);
        Id.setFont(f);
        Id.setBounds(x, y, width/5, height/20);
        add(Id);
        x+=width/5;
        Idt.setForeground(Color.BLUE);
        Idt.setFont(f);
        Idt.setBounds(x, y, width/5, height/20);
        add(Idt);
        
        x=width/15;y+=20+height/20;
        Name.setForeground(Color.BLUE);
        Name.setFont(f);
        Name.setBounds(x, y, width/5, height/20);
        add(Name);
        x+=width/5;
        Namet.setForeground(Color.BLUE);
        Namet.setFont(f);
        Namet.setBounds(x, y, width/5, height/20);
        add(Namet);
        x=width/8;
        temp=new JLabel("Train Signature");
        temp.setForeground(Color.white);
        temp.setFont(new Font("Monotype Corsiva",Font.ITALIC,24));
        y+=50+height/20;
        Train=new JPanel();
        Train.setName("train");
        Train.add(temp);
        Train.setBackground(new Color(50,50,255));
        Train.setBounds(width/8, y, width/4, height/14);
        Train.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Train.setToolTipText("Click to Train the Samples.");
        add(Train);
        
        
         Trainhome=new JPanel();
        Trainhome.setName("trainhome");
        temp=new JLabel("Go To Home");
        temp.setForeground(Color.white);
        temp.setFont(new Font("Monotype Corsiva",Font.ITALIC,24));
        Trainhome.add(temp);
        Trainhome.setBackground(new Color(50,50,55));
        Trainhome.setBounds(width/8, y, width/4, height/14);
        Trainhome.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Trainhome.setToolTipText("Go To Home.");
        Trainhome.setVisible(false);
        add(Trainhome);
        
        Trainhomeb=new JPanel();
        Trainhomeb.setName("trainhomeb");
        temp=new JLabel("Train Signature");
        temp.setForeground(Color.white);
        temp.setFont(new Font("Monotype Corsiva",Font.ITALIC,24));
        Trainhomeb.add(temp);
        Trainhomeb.setBackground(new Color(50,50,55));
        Trainhomeb.setBounds(width/8, y, width/4, height/14);
        Trainhomeb.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Trainhomeb.setToolTipText("Traing is Running.");
        Trainhomeb.setVisible(false);
        add(Trainhomeb);
        
        
        JSeparator js=new JSeparator(1); 
        js.setBounds(width/2,height/15,2, 14*height/16);
        add(js);
        fp=new String[6];
        imgset=new boolean[6];
        imgset[0]=imgset[1]=imgset[2]=imgset[3]=imgset[4]=imgset[5]=false;
        img=new JPanel[6];
        imgl=new JPanel[6];
        x=(int) (width/1.9);
        y=height/15;
        rec_l=new JLabel[6];
        rec_r=new JLabel[6];
        for(int i=0;i<6;i++)
        {
            rec_l[i]=new JLabel();
            rec_l[i].setFont(f);
            rec_l[i].setForeground(Color.gray);
            rec_l[i].setBounds(width/20,i*20+2*height/3, width/4, height/20);
            add(rec_l[i]);
            rec_r[i]=new JLabel();
            rec_r[i].setFont(f);
            rec_r[i].setForeground(Color.blue);
            rec_r[i].setBounds(width/20+width/4,i*20+2*height/3, width/4, height/20);
            add(rec_r[i]);
            
            imgl[i]=new JPanel();
            temp=new JLabel("Click to Select Signature Sample "+(i+1));
            temp.setFont(f);
            temp.setForeground(Color.blue);
            if(i%2==0)
            imgl[i].setBackground(new Color(240,240,240));
            else
            imgl[i].setBackground(new Color(230,230,230));
            imgl[i].add(temp);
            imgl[i].setBounds(x, y, (int) (width/2.3), (int) (height/6.8));
            System.out.print(width/2.3+"::::"+height/6.8);
            
            y+=height/6.8;
            add(imgl[i]);
            imgl[i].setToolTipText("Click to Select Signature Sample "+(i+1));
            imgl[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
            final int ii=i;
            imgl[i].addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e) 
                {
                   JFileChooser chooser = new JFileChooser("C:\\Users\\Mysterious\\Desktop\\Signature\\Dataset_Signature_Final\\Dataset");
                   int res = chooser.showSaveDialog(null);
                   File fileobj = chooser.getSelectedFile();
                   chooser.addChoosableFileFilter(new filter1());
                   if(res == JFileChooser.APPROVE_OPTION) 
                   {
                       fp[ii]=fileobj.getPath();
                       tp.remove(imgl[ii]);
                       fp[ii]=fp[ii].replace("\\", "\\\\");
                       img[ii]=new JPanelimg(fp[ii],160,height/7);
                       if(ii%2==0)
                           img[ii].setBounds(50+width/2,20+ii*height/7,160, height/7);
                       else
                           img[ii].setBounds(width-200,20+ii*height/7,160, height/7);
                       img[ii].setBorder(BorderFactory.createLineBorder(Color.BLUE,2));
                       imgset[ii]=true;
                       add(img[ii]);
                       updateUI();  
                   }
                }
                public void mouseEntered(MouseEvent e) 
                {
                    imgl[ii].setBackground(new Color(210,210,210));
                    imgl[ii].setBorder(BorderFactory.createBevelBorder(2, Color.lightGray, Color.yellow));
                    updateUI();
                }
                public void mouseExited(MouseEvent e) 
                {
                    imgl[ii].setBorder(null);
                    if(ii%2==0)
                    imgl[ii].setBackground(new Color(240,240,240));
                    else
                    imgl[ii].setBackground(new Color(230,230,230));
                    updateUI();
                    
                }
            });
        }
        home.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent me)
            {
                 tp.ResetTrainPanel();
                tp.gui.MainReset();
            }
        });
        Trainhome.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent me)
            {
                 tp.ResetTrainPanel();
                tp.gui.MainReset();
            }
        });
        Train.addMouseListener(new MouseAdapter()
        {
             public void mouseClicked(MouseEvent e) 
             {
                        if(imgset.length>0&&Idt.getText().length()!=0)
                        {
                            if(tp.dp.CheckID(Idt.getText()))
                            {
                                    tp.Idt.setEditable(false);
                                    tp.Namet.setEditable(false);
                                    //tp.Train.setBackground(Color.BLACK);
                                    tp.Train.setVisible(false);
                                    tp.Trainhomeb.setVisible(true);
                                    //tp.Trainhome.setVisible(false);
                                    new  TrainProgress(tp,img,fp,Idt.getText(),Namet.getText());
                            }
                            else
                            {
                                  JOptionPane.showMessageDialog((Component) null, 
                              "Person's Id Exist.\nPlease Change the Id.","",JOptionPane.WARNING_MESSAGE);

                            }
                        }
                        else
                        {
                        JOptionPane.showMessageDialog((Component) null, 
                              "Please Select all Sample Signatures & Person's ID.","",JOptionPane.WARNING_MESSAGE);
                        }
            }
            public void mouseEntered(MouseEvent e) {
                    Train.setBackground(new Color(50,50,255));
                    Train.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED,new Color(200,200,255), Color.lightGray));
                    updateUI();
            }
            public void mouseExited(MouseEvent e) {
                    Train.setBackground(new Color(100,100,255));
                    Train.setBorder(null);
                    updateUI();
            }
        });
       
    }
    public void ResetTrainPanel()
    {
        uidt.setText(""+(tp.dp.getDBRows()+1));
        for(int i=0;i<6;i++)
        {
            imgset[i]=false;
            try{ tp.remove(img[i]);
                 tp.add(imgl[i]);
                 rec_l[i].setText("");
                 rec_r[i].setText("");
                }catch(NullPointerException e){}
        }
        tp.Idt.setEditable(true);
        tp.Namet.setEditable(true);
        tp.Idt.setText("");
        tp.Namet.setText("");
        tp.Train.setVisible(true);
        tp.Trainhomeb.setVisible(false);
        tp.Trainhome.setVisible(false);
        tp.updateUI();
    }

    


}
class TrainProgress extends Thread
{
    TrainPanel tp;
    int curr,state;
    String[] sign_file;
    String PersonID;
    JPanel[] img;
    String PersonName;
    Float id,num;
    TrainProgress(TrainPanel tp,JPanel[] img,String[] sign_file,String PersonID,String PersonName)
    {
        this.img=img;

        this.sign_file=sign_file;
        this.PersonID=PersonID;
        this.PersonName=PersonName;
        curr=0;
        state=0;
        this.tp=tp;
        num=1.0f;
        id=num+tp.dp.getDBRows();
        start();
    }
    public void run()
    {
        SignatureRecognition sr=new SignatureRecognition();
        try {   
             for(int i=0;i<3;i++)
             {
                  tp.rec_l[i].setText("Signature "+(i+1)+" :");
                  tp.rec_r[i].setText("Processing..");
                     tp.updateUI();
                  if(sr.train(System.getProperty("user.home")+"\\Documents\\Signature\\Features",sign_file[i],id)==1)
                  {
                        sr.ImgWrite(System.getProperty("user.home")+"\\Documents\\Signature\\sample.jpg");
                      
                         tp.rec_r[i].setText("Done");
                        tp.remove(tp.img[i]);
                        tp.img[i]=new JPanelimg(System.getProperty("user.home")+"\\Documents\\Signature\\sample.jpg",160,tp.height/7);
                         if(i%2==0)
                              tp.img[i].setBounds(50+tp.width/2,20+i*tp.height/7,160, tp.height/7);
                         else
                              tp.img[i].setBounds(tp.width-200,20+i*tp.height/7,160, tp.height/7);
                         tp.img[i].setBorder(BorderFactory.createLineBorder(Color.BLUE,2));           
                        tp.add(tp.img[i]);
                        tp.updateUI();
                  }
             }
             
             sr.createModelFile(System.getProperty("user.home")+"\\Documents\\Signature\\Features",System.getProperty("user.home")+"\\Documents\\Signature\\Model");
             
             System.out.println("::"+id);
             tp.dp.registerPerson(id,PersonID,PersonName);
             
             tp.Trainhomeb.setVisible(false);
             tp.Trainhome.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(TrainProgress.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}                 