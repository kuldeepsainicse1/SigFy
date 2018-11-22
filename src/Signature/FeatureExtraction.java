/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Signature;

import org.opencv.core.Mat;

/**
 *
 * @author Mysterious_K
 */
public class FeatureExtraction {
    ////////////////////////////////////////////////////////////////////////////////
	
    private static double PI=3.14159265358979323846;
    static int pp=0,pp1=0;
	static int GridFeaturessecter(Mat im,float ptr[],int p)
	{
		pp=p;
		calculate_densitysecter(im,ptr,p,0,0,im.rows(),im.cols(),0,0,0,0,1);
		return pp;
	}

	static void calculate_densitysecter(Mat im,float ptr[],int p,int x1,int y1,int x2,int y2,int xcc1,int ycc1,int xcc2,int ycc2,int layer)
	{
		int c,xc,yc,cond;
	        double left,right,leftangle,rightangle,l,r;
		if(layer>0)
		{
			        xc=yc=0;
				xc=(x2-x1)/2;
				yc=(y2-y1)/2;
				calculate_densitysecter(im,ptr,p,x1,y1,xc,yc,xc,yc,x1,y1,layer-1);
				calculate_densitysecter(im,ptr,p,x1,yc,xc,y2,xc,yc,x1,y2,layer-1);
				calculate_densitysecter(im,ptr,p,xc,y1,x2,yc,xc,yc,x2,y1,layer-1);
				calculate_densitysecter(im,ptr,p,xc,yc,x2,y2,xc,yc,x2,y2,layer-1);
		}
		else
		{
			left=leftangle=0.0;
			right=rightangle=0.0;
			l=0.0;r=0.0;
			for(int x=x1;x<x2;x++)
			{
					for(int y=y1;y<y2;y++)
					{
						if(im.get(x,y)[0]==255)
						{
							cond=(xcc2-xcc1)*(y-ycc1)-(ycc2-ycc1)*(x-xcc1);
							if(cond>0)
							{
								left+=Math.sqrt((double)((xcc1-x)*(xcc1-x)+(ycc1-y)*(ycc1-y)));
                                                                if(xcc1-x!=0)
                                                                    leftangle+=Math.atan((double)Math.abs(ycc1-y)/(double)Math.abs(xcc1-x))*180/PI;
								l++;
							}
							else
							{
								right+=Math.sqrt((double)((xcc1-x)*(xcc1-x)+(ycc1-y)*(ycc1-y)));
								if(xcc1-x!=0)
                                                                    rightangle+=Math.atan((double)Math.abs(ycc1-y)/(double)Math.abs(xcc1-x))*180/PI;
								r++;
							}
						}
					}
			}
			
			if(l!=0)
			{
				ptr[pp++]=(float) ((float)left/l);
				ptr[pp++]=(float) ((float)leftangle/l);
			}
			else
			{
				ptr[pp++]=(float)0.0;
				ptr[pp++]=(float)0.0;	
			}
			if(r!=0)
			{
				ptr[pp++]=(float) ((float)right/r);
				ptr[pp++]=(float) ((float)rightangle/r);
				//cout<<"::"<<rightangle<<"\n";
			}
			else
			{
				ptr[pp++]=(float)0.0;
				ptr[pp++]=(float)0.0;
			}
			
		}

	}
	static int GridFeatures(Mat im,float ptr[],int p)
	{
		pp1=p;
		calculate_density(im,ptr,p,0,0,im.rows(),im.cols(),2);
		return pp1;

	}
	static void calculate_density(Mat im,float ptr[],int p,int x1,int y1,int x2,int y2,int layer)
	{
		int c,xc,yc,den;
		if(layer>0)
		{
			    xc=yc=0;
				c=1;
				for(int x=x1;x<x2;x++)
				{
					for(int y=y1;y<y2;y++)
					{
						if(im.get(x,y)[0]==255)
						{
							xc+=x;
							yc+=y;
							c++;
						}
					}
				}
				xc=xc/c;
				yc=yc/c;

				
				calculate_density(im,ptr,p,x1,y1,xc,yc,layer-1);
				calculate_density(im,ptr,p,x1,yc,xc,y2,layer-1);
				calculate_density(im,ptr,p,xc,y1,x2,yc,layer-1);
				calculate_density(im,ptr,p,xc,yc,x2,y2,layer-1);
		}
		else
		{
			den=0;
			for(int x=x1;x<x2;x++)
			{
					for(int y=y1;y<y2;y++)
					{
						
						if(im.get(x,y)[0]==255)
							den++;
					}
			}
			ptr[pp1++]=(float)den;
		}
	}
	//********************End of Grid Feature**********************************************
        
        static int GridFeaturesE(Mat im,float ptr[],int p)
	{
		pp1=p;
		calculate_densityE(im,ptr,p,0,0,im.rows(),im.cols(),4);
		return pp1;

	}
	static void calculate_densityE(Mat im,float ptr[],int p,int x1,int y1,int x2,int y2,int layer)
	{
		int c,xc,yc,den;
		if(layer>0)
		{
			        xc=yc=0;
				c=1;
				for(int x=x1;x<x2;x++)
				{
					for(int y=y1;y<y2;y++)
					{
						if(im.get(x,y)[0]==255)
						{
							xc+=x;
							yc+=y;
							c++;
						}
					}
				}
				xc=xc/c;
				yc=yc/c;

				if(layer%2==0)
                                {
                                    calculate_densityE(im,ptr,p,x1,y1,xc,y2,layer-1);
                                    calculate_densityE(im,ptr,p,xc,y1,x2,y2,layer-1);
                                }
                                else
                                {
                                    calculate_densityE(im,ptr,p,x1,y1,x2,yc,layer-1);
                                    calculate_densityE(im,ptr,p,x1,yc,x2,y2,layer-1);
                                }
		}
		else
		{
			den=0;
			for(int x=x1;x<x2;x++)
			{
					for(int y=y1;y<y2;y++)
					{
						
						if(im.get(x,y)[0]==255)
							den++;
					}
			}
			ptr[pp1++]=(float)den;
		}
	}
        
}
