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
import java.io.PrintStream;
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
    public static void main(String args[]) throws IOException, InterruptedException {
    	loadJarDll();
    	
    	PrintStream trainfileStream = new PrintStream("C:\\Users\\Mysterious\\Desktop\\Signature\\train_logs12.txt");
    	PrintStream testfileStream = new PrintStream("C:\\Users\\Mysterious\\Desktop\\Signature\\test_logs _forge12.txt");
    	
    	
    	int pn=12;
    	int ra=0;
    	int rb=5;
    	boolean train=false;//true;
    	
    	String perno,imgno,img;
    	String path="C:\\Users\\Mysterious\\Desktop\\Signature\\Dataset_Signature_Final\\Dataset\\dataset1\\forge\\";
    	String FeatureFile="C:\\Users\\Mysterious\\Desktop\\Signature\\Features";
    	String ModalFile="C:\\Users\\Mysterious\\Desktop\\Signature\\Modal";
    	SignatureRecognition sr=new SignatureRecognition();
    	long a,b;
    	
    	if(train)
    		System.setOut(trainfileStream);
    	else
    	{
    		System.setOut(testfileStream);
    	}
    	for(int i=1;i<=pn;i++)
    	{
    		perno= String.format("%03d" , i);
    		for(int j=0;j<5;j++)
    		{
    			imgno= String.format("%02d" , j);
    			img="021"+imgno+perno+".png";
    			
    			System.out.println(img);
    			if(j>=ra&&j<rb)
    			{
    				if(train)
    				{
	    				System.out.println("==========Train["+img+"]===========");
	    				System.out.println(path+img);     
	    				a=System.currentTimeMillis();
	    				sr.train(FeatureFile, path+img,i);
	    				b=System.currentTimeMillis()-a;
	    				System.out.println("Time="+b+"ms");
	    				a=System.currentTimeMillis();
	    				sr.createModelFile(FeatureFile, ModalFile);
	    				b=System.currentTimeMillis()-a;
	    				System.out.println("Time="+b+"ms");
    				}
    				else
    				{
    					System.out.println("==========Test["+img+"]===========");
    					a=System.currentTimeMillis();
    					RecogSignature sig=new RecogSignature(path+img,ModalFile,null);
    					b=System.currentTimeMillis()-a;
	    				System.out.println("Time="+b+"ms");
    				}
    			}
    			if(j==rb)
    			{
    				Thread.sleep(3000);
    			}
    		}
    	}
//    	RecogProgress prg=new RecogProgress(recp,sig);
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
