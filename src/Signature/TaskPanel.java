/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Signature;

import java.awt.Toolkit;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Mysterious_K
 */

class TaskPanel extends JPanel
{
    private JLabel logo,close;
    private Icon img;
    private JSeparator js;
    private JFrame superframe;
    TaskPanel(int width,int height,final JFrame superframe)
    {
        this.superframe=superframe;
        this.setLayout(null);
        img=new ImageIcon(this.getClass().getResource("logosmall.gif"));
        logo=new JLabel("",img,0);
        img=new ImageIcon(this.getClass().getResource("close.gif"));
        close=new JLabel("",img,0);
        logo.setBounds(20,0,134,35);
        add(logo);
        close.setBounds(width-30,10,15,15);
        add(close);
        close.addMouseListener(new MouseAdapter(){@Override
        public void mouseClicked(MouseEvent e) {
        superframe.dispose();}});
        js=new JSeparator();
        js.setBounds(0,height-1,width,2);
        add(js);
    }
}
