/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Signature;

import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;

/**
 *
 * @author Mysterious_K
 */
public class Preprocessing {
    
	//***************Global Threasholding using Otsus Method***************************
	static int OtsusThreshold(Mat im)
	{
        int hist[]=new int[256];
		int summ=0;
		float pp[]=new float[256];
		float p1[]=new float[256];
		float m[]=new float[256];
		float mg=0;
		float max=0;
		float sigma[]=new float[256];
		int th=0;
		for(int i=0;i<im.rows();i++)
			for(int j=0;j<im.cols();j++)
				hist[(int)im.get(i, j)[0]]++;

		for(int i=0;i<256;i++)
			summ+=hist[i];

		for(int k=0;k<256;k++)
		{
			pp[k]=(float)hist[k]/(float)summ;
			p1[k]=0;
			m[k]=0;
			for(int i=0;i<k;i++)
			{
				p1[k]+=pp[i];
				m[k]+=i*pp[i];
			}
			mg+=k*pp[k];
		}
		for(int k=0;k<256;k++)
		{
			sigma[k]=((mg*p1[k]-m[k])*(mg*p1[k]-m[k]))/(p1[k]*(1-p1[k]));
			if(sigma[k]>max)
			{
				max=sigma[k];
				th=k;
			}
		}
		return th;
	}
	//*****************End of Otsus Threshold Method******************************
        
	//******************Zhang-Suen Thinning algorithm****************************
	static void thinningIteration(Mat im, int iter)  
	{
		Mat marker =Mat.zeros(im.size(),CvType.CV_8UC1);
		for (int i = 1; i < im.rows()-1; i++)
		{
			for (int j = 1; j < im.cols()-1; j++)
			{
				int p2 = (int)im.get(i-1, j)[0];
				int p3 = (int)im.get(i-1, j+1)[0];
				int p4 =(int) im.get(i, j+1)[0];
				int p5 =(int) im.get(i+1, j+1)[0];
				int p6 =(int) im.get(i+1, j)[0];
				int p7 =(int) im.get(i+1, j-1)[0];
				int p8 =(int) im.get(i, j-1)[0];
				int p9 =(int) im.get(i-1, j-1)[0];
				int A =((p2==0&&p3==1)?1:0)+((p3==0&&p4==1)?1:0)+
				((p4 == 0 && p5 == 1)?1:0) + ((p5 == 0 && p6 == 1)?1:0) +
				((p6 == 0 && p7 == 1)?1:0) + ((p7 == 0 && p8 == 1)?1:0) +
				((p8 == 0 && p9 == 1)?1:0) + ((p9 == 0 && p2 == 1)?1:0);
				int B = p2 + p3 + p4 + p5 + p6 + p7 + p8 + p9;
				int m1 = iter == 0 ? (p2 * p4 * p6) : (p2 * p4 * p8);
				int m2 = iter == 0 ? (p4 * p6 * p8) : (p2 * p6 * p8);
				if (A == 1 && (B >= 2 && B <= 6) && m1 == 0 && m2 == 0)
                                    marker.put(i,j,new Double(1));
			}
		}
		Core.bitwise_not(marker,marker);
                Core.bitwise_and(im,marker,im);
                
	}
	static void thinning(Mat im)
	{
            Imgproc.threshold(im, im, 128,1,0);
            
            Mat prev = Mat.zeros(im.size(),CvType.CV_8UC1);
		Mat diff =new Mat();
		do {
		thinningIteration(im, 0);
		thinningIteration(im, 1);
		Core.absdiff(im, prev, diff);
		im.copyTo(prev);
		}
		while (Core.countNonZero(diff) > 0);
            
               Imgproc.threshold(im, im,0,255,0);   
	}
	//***********End of Zhang-Suen Thinning algorithm*****************************

	//************************ To remove Noise ***********************************
	static void RemoveNoise(Mat m)
	{
		
		int valu;
		int b,w;
		for(int x=1;x<m.rows()-1;x++)
		{
			for(int y=1;y<m.cols()-1;y++)
			{
				
				valu=(int)m.get(x-1,y-1)[0]+(int)m.get(x,y-1)[0]+(int)m.get(x+1,y-1)[0]+
					(int)m.get(x-1,y)[0]+(int)m.get(x+1,y)[0]+
					(int)m.get(x-1,y+1)[0]+(int)m.get(x,y+1)[0]+(int)m.get(x+1,y+1)[0];
				w=valu/255;
				b=9-w;
				if((int)m.get(x,y)[0]==255&&w<2)
					m.put(x,y,new Double(0));
				else if((int)m.get(x,y)[0]==0&&b<2)
					m.put(x,y,new Double(255));
			}
		}
		
	}
	//******************End of Noise Removal*************************************

    
}
