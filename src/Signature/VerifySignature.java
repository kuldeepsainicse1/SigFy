/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Signature;

/**
 *
 * @author Mysterious_K
 */

class VerifySignature extends Thread
{
    String file_path;
    double result;
    VerifyPanel verp;
    SignatureRecognition sr;
    VerifySignature(String file_path,VerifyPanel verp)
    {
        this.verp=verp;
        this.file_path=file_path;
         sr=new SignatureRecognition();
        start();
    }
    public void run()
    {
                          
                          if(sr.Readimg(file_path)==1)
                          {
                           sr.RGB_BIN();
                           System.out.printf("\n::RN");
                           sr.RemoveNoise();
                           System.out.printf("\n::DS");
                           sr.DeSkew();
                           System.out.printf("\n::R");
                           sr.Resize();
                           System.out.printf("\n::Thining");
                           sr.Thining();
                           System.out.printf("\n::feature");
                           sr.FeatureExtraction();
                           try{ 
                               sr.ImgWrite("F:\\pattern\\a.jpg");
                               result=sr.Recognise();
                               
                           verp.setResult(result);
                           System.out.printf("\n::"+result);
                           }catch(Exception ex){}
                          }
    }
    public int getstate()
    {
        return sr.getStat();
    }
}
