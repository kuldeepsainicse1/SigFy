
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Signature;


import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;
import java.sql.*;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Mysterious_K
 */
class DatabasePanel extends JPanel implements MouseListener{
    public JLabel panel,temp;
    public JLabel createdb,showdb;
    public JLabel home;
    public JLabel newdb,user,pass;
    JTextField newdbt,usert;
    JPasswordField passt;
    JPanel dbbutton;
    Font f;
    public int width,height;
    private int x,y;
    DatabasePanel dp;
    public GUI gui;
    private JPanel dbpane,showpane;
    
    Connection con = null;
    Statement smt = null;
    ResultSet rs = null;
    DatabasePanel()    
    {       
    }
    DatabasePanel(GUI gui,final int width,final int height)
    {
        this.gui=gui;
        dp=this;
        this.width=width;
        this.height=height;
        this.setName("databasePanel");
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
        
        x=70;y=0;
        
        panel=new JLabel("Database Settings");
        panel.setFont(f);
        panel.setForeground(Color.BLUE);
        panel.setBounds(x,y,350,90);
        add(panel);
        
        createdb=new JLabel("-> Set Databse ID's");
        createdb.setName("createdb");
        createdb.setForeground(Color.BLUE);
        createdb.setFont(new Font("Monotype Corsiva",Font.ITALIC,30));
        createdb.setBounds(width/18,height/4, width/4, height/20);
        add(createdb);
        
        y=height/4;
        dbpane=new JPanel();
        dbpane.setBackground(Color.WHITE);
        dbpane.setBounds(10+width/3, 20,-10+2*width/3, height-10);
        dbpane.setLayout(null);
        newdb=new JLabel("Name of Database :");
        newdb.setForeground(Color.BLUE);
        newdb.setFont(new Font("Monotype Corsiva",Font.ITALIC,30));
        newdb.setBounds(width/15,y, width/4, height/20);
        newdbt=new JTextField("SIGNATURE");
        newdbt.setEditable(false);
        newdbt.setForeground(Color.BLUE);
        newdbt.setBounds(width/3,y, width/5, height/20);
        newdbt.setFont(new Font("Monotype Corsiva",Font.ITALIC,25));
        
        y+=height/8;
        user=new JLabel("Database User :");
        user.setForeground(Color.BLUE);
        user.setFont(new Font("Monotype Corsiva",Font.ITALIC,30));
        user.setBounds(width/15,y, width/4, height/20);
        usert=new JTextField("root");
        usert.setForeground(Color.BLUE);
        usert.setBounds(width/3,y, width/5, height/20);
        usert.setFont(new Font("Monotype Corsiva",Font.ITALIC,25));
        
        y+=height/8;
        pass=new JLabel("Database Password :");
        pass.setForeground(Color.BLUE);
        pass.setFont(new Font("Monotype Corsiva",Font.ITALIC,30));
        pass.setBounds(width/15,y, width/4, height/20);
        passt=new JPasswordField();
        passt.setForeground(Color.BLUE);
        passt.setBounds(width/3,y, width/5, height/20);
        passt.setFont(new Font("Monotype Corsiva",Font.ITALIC,25));
        
        y+=height/4;
        dbbutton=new JPanel();
        dbbutton.setName("dbbutton");
        dbbutton.setBackground(new Color(100,100,255));
        temp=new JLabel("Create Database");
        temp.setFont(new Font("Monotype Corsiva",Font.ITALIC,25));
        temp.setForeground(Color.WHITE);
  
  
        dbbutton.setBounds(7*width/30,y, width/5, height/12);
        dbbutton.add(temp);
  
        dbpane.add(newdb);
        dbpane.add(newdbt);
        dbpane.add(user);
        dbpane.add(usert);
        dbpane.add(pass);
        dbpane.add(passt);
        dbpane.add(dbbutton);
        add(dbpane);
        
        showpane=new JPanel();
        showpane.setBackground(Color.white);
        showpane.setBounds(10+width/3,20,-10+2*width/3, height-10);
        showpane.setLayout(null);
        
          Object[] data = new Object[4];

        DefaultTableModel defaulttablemodel = new DefaultTableModel();
        JTable jtable = new JTable(defaulttablemodel);
            
             defaulttablemodel.addColumn("Unique ID");
             defaulttablemodel.addColumn("Person's ID");
             defaulttablemodel.addColumn("Person Name");
             defaulttablemodel.addColumn("Added Date");
            
       int rows=this.getDBRows();
      
       String r=null;
          try {
              BufferedReader fp;
            fp = new BufferedReader(new FileReader(System.getProperty("user.home")+"\\Documents\\Signature\\DBinfo.txt"));
            String user = null,pass = null,line= fp.readLine();
            StringTokenizer st = new StringTokenizer(line," \t\n\r\f:");
	    user=st.nextToken();
            pass=st.nextToken();
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/signature",user,pass);
            smt=con.createStatement();
            rs=smt.executeQuery("SELECT * FROM PERSON_INFO");
            while(rs.next())
            {
                data[0]=rs.getString("uid");
                data[1]=rs.getString("id");
                data[2]=rs.getString("name");
                data[3]=rs.getString("date");
                defaulttablemodel.addRow(data);
            }
        } catch (Exception ex) {
        System.out.printf("\nTableSet()"+ex);
        }
   
        JScrollPane per_detail;     
        per_detail = new JScrollPane(jtable);
        per_detail.setBackground(Color.white);
        per_detail.setForeground(Color.white);
        per_detail.setBounds(5,height/8,-15+2*width/3, height-15);
        showpane.add(per_detail);
        
        y=height/4;
        y+=height/10;
        showdb=new JLabel("-> Show Database");
        showdb.setName("showdb");
        showdb.setForeground(Color.LIGHT_GRAY);
        showdb.setFont(new Font("Monotype Corsiva",Font.ITALIC,28));
        showdb.setBounds(width/18,y, width/4, height/20);
        add(showdb);
        
        JSeparator js=new JSeparator(1); 
        js.setBounds((int) (width/3),height/5,2,4*height/6);
        add(js);
        
        createdb.setCursor(new Cursor(Cursor.HAND_CURSOR));
        showdb.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        
        home.addMouseListener(this);
        createdb.addMouseListener(this);
        showdb.addMouseListener(this);
        
        
        if(!DBCheck())
        {
            dbbutton.addMouseListener(this);
        }
        else
        {
            usert.setEditable(false);
            passt.setEditable(false);
            dbbutton.setBackground(Color.BLACK);
            dbbutton.setToolTipText("Database Alredy Existed.");
        }
        
        
    }
    @Override
    public void mouseClicked(MouseEvent e) {
         switch (e.getComponent().getName()) 
         {
            case "home":
                this.ResetDatabasePanel();
                gui.MainReset();
                break;
            case "createdb":
                createdb.setForeground(Color.BLUE);
                showdb.setForeground(Color.LIGHT_GRAY);
                
                
                dp.remove(showpane);
                
                dp.add(dbpane);
                this.updateUI();
                break;
            case "showdb":
                
                createdb.setForeground(Color.LIGHT_GRAY);
                showdb.setForeground(Color.BLUE);
                
                dp.remove(dbpane);
                
                dp.add(showpane);
                this.updateUI();
                break;
            case "dbbutton":
                if(!this.DBCheck())
                {
                   if(this.DBCreate(usert.getText(),passt.getText()))
                   {
                      JOptionPane.showMessageDialog((Component) null, 
                      "Database 'Signature' Created Successfully.","",JOptionPane.INFORMATION_MESSAGE);
                      dbbutton.removeMouseListener(dp);
                      usert.setEditable(false);
                      passt.setEditable(false);
                      dbbutton.setBackground(Color.BLACK);
                      dbbutton.setToolTipText("Database Alredy Existed.");
                   }
                
                }
                else
                {
                    JOptionPane.showMessageDialog((Component) null, 
                    "Database 'Signature' Already Existed.","",JOptionPane.INFORMATION_MESSAGE);
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
         switch (e.getComponent().getName()) 
         {
            case "dbbutton":
                dbbutton.setBackground(new Color(150,150,255));
                dbbutton.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.gray, Color.lightGray));
                break;
         }
         this.updateUI();
    }

    @Override
    public void mouseExited(MouseEvent e) {    
         switch (e.getComponent().getName()) 
         {
            case "dbbutton":
                dbbutton.setBackground(new Color(100,100,255));
                dbbutton.setBorder(null);
         }
         this.updateUI();
    }
    
    public boolean CheckID(String id)
    {
        int r=0;
          try {
              BufferedReader fp;
            fp = new BufferedReader(new FileReader(System.getProperty("user.home")+"\\Documents\\Signature\\DBinfo.txt"));
            String user = null,pass = null,line= fp.readLine();
            StringTokenizer st = new StringTokenizer(line," \t\n\r\f:");
	    user=st.nextToken();
            pass=st.nextToken();
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/signature",user,pass);
            smt=con.createStatement();
            rs=smt.executeQuery("SELECT * FROM PERSON_INFO where id='"+id+"'");
            while(rs.next())
                r++;
        } catch (Exception ex) {
        System.out.printf("\ngetrows()"+ex);
        }
        if(r==0)
            return true;
        else
            return false;
    }
    public int getDBRows()
    {
        int r=0;
          try {
              BufferedReader fp;
            fp = new BufferedReader(new FileReader(System.getProperty("user.home")+"\\Documents\\Signature\\DBinfo.txt"));
            String user = null,pass = null,line= fp.readLine();
            StringTokenizer st = new StringTokenizer(line," \t\n\r\f:");
            user=st.nextToken();
            pass=st.nextToken();
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/signature",user,pass);
            smt=con.createStatement();
            rs=smt.executeQuery("SELECT * FROM PERSON_INFO");
            while(rs.next())
                r++;
        } catch (Exception ex) {
        System.out.printf("\ngetrows()"+ex);
        }
          return r;
    }
     public String getLastUpdate()
    {
        String r=null;
          try {
              BufferedReader fp;
            fp = new BufferedReader(new FileReader(System.getProperty("user.home")+"\\Documents\\Signature\\DBinfo.txt"));
            String user = null,pass = null,line= fp.readLine();
            StringTokenizer st = new StringTokenizer(line," \t\n\r\f:");
	    user=st.nextToken();
            pass=st.nextToken();
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/signature",user,pass);
            smt=con.createStatement();
            rs=smt.executeQuery("SELECT * FROM PERSON_INFO");
            while(rs.next())
            {
                r=rs.getString("date");
            }
        } catch (Exception ex) {
        System.out.printf("\ngetrows()"+ex);
        }
          return r;
    }
     public String getID(int uid)
    {
        String r=null;
          try {
              BufferedReader fp;
            fp = new BufferedReader(new FileReader(System.getProperty("user.home")+"\\Documents\\Signature\\DBinfo.txt"));
            String user = null,pass = null,line= fp.readLine();
            StringTokenizer st = new StringTokenizer(line," \t\n\r\f:");
	    user=st.nextToken();
            pass=st.nextToken();
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/signature",user,pass);
            smt=con.createStatement();
            rs=smt.executeQuery("SELECT id FROM PERSON_INFO WHERE UID="+uid);
            while(rs.next())
            {
                r=rs.getString("id");
            }
        } catch (Exception ex) {
        System.out.printf("\ngetID()"+ex);
        }
          return r;
    }
     public String getName(int uid)
    {
        String r=null;
          try {
              BufferedReader fp;
            fp = new BufferedReader(new FileReader(System.getProperty("user.home")+"\\Documents\\Signature\\DBinfo.txt"));
            String user = null,pass = null,line= fp.readLine();
            StringTokenizer st = new StringTokenizer(line," \t\n\r\f:");
	    user=st.nextToken();
            pass=st.nextToken();
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/signature",user,pass);
            smt=con.createStatement();
            rs=smt.executeQuery("SELECT name FROM PERSON_INFO WHERE uid="+uid);
            while(rs.next())
            {
                r=rs.getString("name");
            }
        } catch (Exception ex) {
        System.out.printf("\ngetName()"+ex);
        }
          return r;
    }
    public void registerPerson(Float uid,String ID,String Name)
    {
          try {
              BufferedReader fp;
            fp = new BufferedReader(new FileReader(System.getProperty("user.home")+"\\Documents\\Signature\\DBinfo.txt"));
            String user = null,pass = null,line= fp.readLine();
            StringTokenizer st = new StringTokenizer(line," \t\n\r\f:");
	    user=st.nextToken();
            pass=st.nextToken();
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/signature",user,pass);
            smt=con.createStatement();
            String query="INSERT INTO PERSON_INFO VALUES("+uid+",'"+ID+"','"+Name+"',CURDATE())";
            smt.executeUpdate(query);
        } catch (Exception ex) {
        System.out.printf("\nregister"+ex);
        }
    }
    public int DefaultDBcheck()
    {
        try {
            BufferedReader fp;
            fp = new BufferedReader(new FileReader(System.getProperty("user.home")+"\\Documents\\Signature\\DBinfo.txt"));
            if(fp==null)
                return 0;
            String user = null,pass = null,line= fp.readLine();
            if(line.length()!=0)
            {
			StringTokenizer st = new StringTokenizer(line," \t\n\r\f:");
			user=st.nextToken();
                        pass=st.nextToken();
            }
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/signature",user,pass);
            FileReader f;
            try{
            f=new FileReader(System.getProperty("user.home")+"\\Documents\\Signature\\Model");
            f.close();
            }catch(IOException ex){
            JOptionPane.showMessageDialog((Component) null, 
                      "Model File does not Existed.\nContact Software Administrator.","",JOptionPane.WARNING_MESSAGE);
            this.CloseDB();
            return -1;
            }
            try{
            f=new FileReader(System.getProperty("user.home")+"\\Documents\\Signature\\Features");
            f.close();
            }catch(IOException ex){
            JOptionPane.showMessageDialog((Component) null, 
                      "Features File does not Existed.\nContact Software Administrator.","",JOptionPane.WARNING_MESSAGE);
            this.CloseDB();
            return -1;
            }
        }
        catch(Exception ex) {
            this.CloseDB();
            return 0;
        }
        this.CloseDB();
        return 1;
    }
    public boolean DBCheck()
    {
        try {
            BufferedReader fp;
            fp = new BufferedReader(new FileReader(System.getProperty("user.home")+"\\Documents\\Signature\\DBinfo.txt"));
            if(fp==null)
                return false;
            String user = null,pass = null,line= fp.readLine();
            if(line.length()!=0)
            {
			StringTokenizer st = new StringTokenizer(line," \t\n\r\f:");
			user=st.nextToken();
                        pass=st.nextToken();
                        usert.setText(user);
                        passt.setText(pass);             
            }
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/signature",user,pass);
           
        }
        catch(Exception ex) {
            this.CloseDB();
            return false;
        }
        this.CloseDB();
        return true;
    }
    public void CloseDB()
    {
        try {
            smt.close();
            con.close();
            rs.close();
        } catch (Exception ex) {}
         
    }
    private boolean DBCreate(String user,String pass)
    {
            int i = 1;
            try {      
              
                Class.forName("com.mysql.jdbc.Driver");
                con=DriverManager.getConnection("jdbc:mysql://localhost/mysql",user,pass);
            } catch (ClassNotFoundException ex) {
                 JOptionPane.showMessageDialog((Component) null,
                      "MySQL is Not Installed.\nPlease Install MySQL.","",JOptionPane.WARNING_MESSAGE);
                 this.CloseDB();
                 return false;
            } catch (SQLException ex) {
                 JOptionPane.showMessageDialog((Component) null, 
                      "Default Database is not accessed.\nPlease enter Correct User name and password for localhost.","",JOptionPane.WARNING_MESSAGE);
                 this.CloseDB();
                 return false;
            }
            try {           
                smt=con.createStatement();
                i=smt.executeUpdate("CREATE DATABASE SIGNATURE");
                
            } catch (SQLException ex) {
                 JOptionPane.showMessageDialog((Component) null, 
                      "SIGNATURE Database Alredy Existed.","",JOptionPane.WARNING_MESSAGE);
                 this.CloseDB();
                 return false;
            }
             try {  
                con=DriverManager.getConnection("jdbc:mysql://localhost/signature",user,pass);
                smt=con.createStatement();
                smt.execute("CREATE TABLE SIGNATURE_INFO(user char(50),pass char(50))");
                smt.execute("CREATE TABLE PERSON_INFO(uid int(4)primary key,id char(50) unique,name char(50),date Date)");
                String st="INSERT INTO SIGNATURE_INFO VALUES('"+user+"','"+pass+"')";
                smt.executeUpdate(st);
                FileWriter f=new FileWriter(System.getProperty("user.home")+"\\Documents\\Signature\\DBinfo.txt");
                f.write(user+" "+pass);
                f.close();
                f=new FileWriter(System.getProperty("user.home")+"\\Documents\\Signature\\Model");
                f.close();
                f=new FileWriter(System.getProperty("user.home")+"\\Documents\\Signature\\Features");
                f.close(); 
                i=1;
        } catch (Exception ex) {
             JOptionPane.showMessageDialog((Component) null, 
                      "Error Occered in Creating database.\nPlease Try again or Contact Administrator.","",JOptionPane.WARNING_MESSAGE);
            this.CloseDB();
                 return false;
        }
      
       this.CloseDB();
        return true;      
                
    }
    public void ResetDatabasePanel()
    {
        createdb.setForeground(Color.BLUE);
        showdb.setForeground(Color.LIGHT_GRAY);
        
        this.remove(dbpane);
        this.remove(showpane);
        
        this.add(dbpane);
    }

}
