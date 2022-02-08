/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 *
 * @author Eliézer
 */
public final class Util {
   public static void SizeJanela(javax.swing.JFrame jf){
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    int h = screenSize.height;
    int w = screenSize.width;

    jf.setSize(w,h);
    
    jf.setLocationRelativeTo(null);
    
    jf.setVisible(true); 
    jf.setExtendedState(JFrame.MAXIMIZED_BOTH); 
    jf.setResizable(true); 
    
   
   }
   }
