package Signature;
import java.sql.*;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import org.opencv.core.*;
import org.opencv.imgproc.*;
import org.opencv.imgcodecs.Imgcodecs;
import libsvm.*;

public class SignatureRecognition{
	//static{System.loadLibrary(Core.NATIVE_LIBRARY_NAME);}
	static Imgcodecs gui;
        static final double PI=3.14159265358979323846;
        FileWriter f;
        static Scanner sc;
        public int state;
        final int DEG=360;
	float ptr[]=new float[150];
	int p=0;
		Mat im;                                                 //Use as temp Mat Image
		Mat sign;
		Mat tempim;
		int thv;
		int Normalize_factor=220;
		int xmax,xmin,ymin,ymax;
		int x,y;
		int xm,ym,xx,yx;
		
		
		float aspect_ratio,norm_area;
		float x_gvty,y_gvty;
		int pixel_count=0;
		int width,height,pure_width,pure_height;
		int temp,t;
	        int tx=0,ty=0;
		float x1,y1,x2,y2,x3,y3;
  SignatureRecognition()
  {
      state=0;
      for(int i=0;i<150;i++)
          ptr[i]=0.0f;
  }
    void createModelFile(String input_file_path,String model_file_path) throws IOException
    {
        
       
               BufferedReader fp = new BufferedReader(new FileReader(input_file_path));
		Vector<Double> vy = new Vector<Double>();
		Vector<svm_node[]> vx = new Vector<svm_node[]>();
		int max_index = 0;

		while(true)
		{
			String line = fp.readLine();
			if(line == null) break;

			StringTokenizer st = new StringTokenizer(line," \t\n\r\f:");

			vy.addElement(atof(st.nextToken()));
			int m = st.countTokens()/2;
			svm_node[] x = new svm_node[m];
			for(int j=0;j<m;j++)
			{
				x[j] = new svm_node();
				x[j].index = atoi(st.nextToken());
				x[j].value = atof(st.nextToken());
			}
			if(m>0) max_index = Math.max(max_index, x[m-1].index);
			vx.addElement(x);
		}
                svm_problem prob=new svm_problem();
		prob = new svm_problem();
		prob.l = vy.size();
		prob.x = new svm_node[prob.l][];
		for(int i=0;i<prob.l;i++)
			prob.x[i] = vx.elementAt(i);
		prob.y = new double[prob.l];
		for(int i=0;i<prob.l;i++)
			prob.y[i] = vy.elementAt(i);
                fp.close();
       
                svm_parameter param=new svm_parameter();
                param.svm_type = svm_parameter.NU_SVC;
		param.kernel_type = svm_parameter.RBF;
		param.degree = 3;
		param.gamma = 0.000085;	// 1/num_features
		param.coef0 = 0;
		param.nu = 0.5;
		param.cache_size = 100;
		param.C = 1;
		param.eps = 1e-3;
		param.p = 0.1;
		param.shrinking = 1;
		param.probability = 1;
		param.nr_weight = 0;
		param.weight_label = new int[0];
		param.weight = new double[0];
            svm_model model = svm.svm_train(prob,param);
            svm.svm_save_model(model_file_path,model);    
        }

	
	public int getStat()
        {
            return this.state;
        }
        public int Readimg(String fil)
        {
            state=1;
            im = Imgcodecs.imread(fil);  // Read the Sign Image file
	    if(im.empty()) { 
		System.out.println("Error loading image \n"); 
	    return 0; }
	    Imgcodecs.imwrite("C:\\Users\\Mysterious\\Desktop\\Signature\\Dataset_Signature_Final\\Dataset\\dataset1\\abc_org.png", im);
            return 1;
        }
        public void RGB_BIN()
        {
            state=2;
            	//******************Find the adaptive threshold value*************
//            Imgcodecs.imwrite("C:\\Users\\Mysterious\\Desktop\\Signature\\Dataset_Signature_Final\\Dataset\\dataset1\\abc.png", im);
	    Imgproc.cvtColor(im, im, Imgproc.COLOR_RGB2GRAY);
	    thv=Preprocessing.OtsusThreshold(im);
	    Imgproc.threshold(im,im,thv,255,1); 
	    Imgcodecs.imwrite("C:\\Users\\Mysterious\\Desktop\\Signature\\Dataset_Signature_Final\\Dataset\\dataset1\\abc.png", im);
        }
        public void RemoveNoise()
        {
            state=3;
            //*************************Noise Removal of Image*************************
	    Preprocessing.RemoveNoise(im);
        }
        public void DeSkew()
        {
            state=4;
            //************************ To remove Border Portion *********************
		xm=im.rows()/40;
		ym=im.cols()/40;
		xx=im.rows();
		yx=im.cols();
		for(x=0;x<im.rows();x++)
		{
			for(y=0;y<im.cols();y++)
			{
				if((x>=0&&x<=xm)||(y>=0&&y<=ym)||(x>=(xx-xm)&&x<=xx)||(y>=(yx-ym)&&y<=yx))
					im.put(x,y,new Double(0));

			}
		}
		//*********
		//---------------------29.39   .23
		
		xmin=im.rows()+1;
		ymin=im.cols()+1;
		xmax=-1;
		ymax=-1;
		int pixe;
		for(x=0;x<im.rows();x++)
		{
			for(y=0;y<im.cols();y++)
			{
				pixe=(int)im.get(x,y)[0];

				if(pixe==255)
				{
					if(x<xmin)
						xmin=x;
					if(x>xmax)
						xmax=x;
					if(y<ymin)
						ymin=y;
					if(y>ymax)
						ymax=y;
				
				}
			}
		}

		int imcols=ymax-ymin;
		int imrows=xmax-xmin;
		//rectangle(im,Point(ymin,xmin),Point(ymax,xmax),Scalar(255));
		//--------------------29.51
		
		int xmid=imrows/3,ymid=imcols/3;
		
		tx=ty=pixel_count=0;
		for(y=xmin;y<xmin+imrows;y++)
		{
			for(x=ymin;x<ymin+ymid;x++)
			{
				if(im.get(y,x)[0]==255)
				{
					tx+=x;
					ty+=y;
					pixel_count++;
				}
				
			}
		}
		x1=(float)Math.floor((double)tx/(double)pixel_count+0.5);
		y1=(float)Math.floor((double)ty/(double)pixel_count+0.5);
		

		tx=ty=pixel_count=0;
		for(y=xmin;y<xmin+imrows;y++)
		{
			for(x=ymin+ymid;x<ymin+2*ymid;x++)
			{
				if(im.get(y,x)[0]==255)
				{
					tx+=x;
					ty+=y;
					pixel_count++;
				}
			}
		}
		x2=(float)Math.floor((double)tx/(double)pixel_count+0.5);
		y2=(float)Math.floor((double)ty/(double)pixel_count+0.5);

		double angle;
		//---------------------31.39  .2
                angle = Math.atan((double)Math.abs(y2-y1)/(double)Math.abs(x2-x1))*180/PI;

		if(y1>y2)
			angle=-angle;

		Point pt=new Point((float)im.cols()/2,(float)im.rows()/2);
		Mat r=Imgproc.getRotationMatrix2D(pt,angle,1.0);
		Imgproc.warpAffine(im,im,r,new Size(im.cols(),im.rows()));
                int tty;
                tty = Preprocessing.OtsusThreshold(im);
		Imgproc.threshold(im,im,tty,255,0);   
        }
        public void Resize()
        {
            state=5;
		//****************************Finding Sign Axis***************************
		xmin=xx+1;
		ymin=yx+1;
		xmax=-1;
		ymax=-1;
		int pixel;
		for(x=0;x<im.rows();x++)
		{
			System.out.println();
			for(y=0;y<im.cols();y++)
			{
				pixel=(int)im.get(x,y)[0];
				//System.out.print(im.get(x,y)[0]);

				if(pixel==255)
				{
					System.out.print(im.get(x,y)[0]);
					if(x<xmin)
						xmin=x;
					if(x>xmax)
						xmax=x;
					if(y<ymin)
						ymin=y;
					if(y>ymax)
						ymax=y;
				
				}
			}
		}
		System.out.println("\n"+im.rows()+":"+im.cols()+":"+ymin+":"+xmin+":"+ymin+":"+xmin);
		//---------------------35.45   .1
		//**********************Formation of Sign Image***************************
		Imgcodecs.imwrite("C:\\Users\\Mysterious\\Desktop\\Signature\\Dataset_Signature_Final\\Dataset\\dataset1\\abc_re.png",im);
		sign=new Mat(im, new Rect(new Point(ymax,xmax),new Point(ymin,xmin)));
		Imgcodecs.imwrite("C:\\Users\\Mysterious\\Desktop\\Signature\\Dataset_Signature_Final\\Dataset\\dataset1\\abc_re_1.png",sign);
		//---------------------40----37  .5
		
		//***************Resizing Sign Image to Normalized Width******************
		Imgproc.resize(sign,sign,new Size(Normalize_factor,Normalize_factor*(xmax-xmin)/(ymax-ymin)));
		//---------------------40.2
		
        	
        }
        public void Thining()
        {
            state=6;
            // **************Thining Image************************** 
            Preprocessing.thinning(sign);
	}
        public void ImgWrite(String str)
        {
		Imgcodecs.imwrite(str,sign);
        }
        
        public void FeatureExtraction()
        {
            state=7;
                p=0;
              	//**********************************Grid Features**************************************************
		p=FeatureExtraction.GridFeatures(sign,ptr,p);
		p=FeatureExtraction.GridFeaturessecter(sign,ptr,p);
	}
        public int train(String input_file,String filepath,Float id) throws IOException
        {
            state=8;
            f=new FileWriter(input_file,true);
             if(Readimg(filepath)==1)
             {
                RGB_BIN();
                RemoveNoise();
                DeSkew();
                Resize();
                Thining();
                FeatureExtraction();
                try{
                   f.write(""+id+" ");
                      for(int i=0;i<p;i++)
                              f.write((i+1)+":"+(double)ptr[i]+" ");
                    f.write("\n");
                    f.close();
                    return 1;
                  }
                  catch(Exception e){return 0;}
             }
             else
             {
                 f.close();
                 return 0;
             }
        }
       
        public double Recognise() throws IOException
        {
            
                state=8;
                svm_model model = svm.svm_load_model(System.getProperty("user.home")+"\\Documents\\Signature\\Model");
               
		svm_node[] sx = new svm_node[p];
                
		for(int j=0;j<p;j++)
		{
                        sx[j]=new svm_node();
			sx[j].index =j+1;
			sx[j].value = ptr[j];
                       
		}
	      double rec=svm.svm_predict(model, sx);
              return rec;
	  
        }
        
      
        private static double atof(String s)
	{
		double d = Double.valueOf(s).doubleValue();
		if (Double.isNaN(d) || Double.isInfinite(d))
		{
			System.err.print("NaN or Infinity in input\n");
			System.exit(1);
		}
		return(d);
	}

	private static int atoi(String s)
	{
		return (int)Integer.parseInt(s);
	}

   
}