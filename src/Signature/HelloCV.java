package Signature;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

public class HelloCV {
	 public static void main(String[] args) throws IOException{
		 System.out.println("Loading OpenCV");
		 Main.loadJarDll();
		 System.out.println("Signature Training");
		 SignatureRecognition sr=new SignatureRecognition();
		 sr.train(System.getProperty("user.home")+"\\Documents\\Signature\\Features","C:\\Users\\Mysterious\\Desktop\\Signature\\Dataset_Signature_Final\\Dataset\\dataset1\\real\\00100001.png",(float) 11);
         
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
