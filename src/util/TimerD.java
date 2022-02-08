/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.Timer;

/**
 *
 * @author Eliézer
 */
public final class TimerD {
    public static void setTimer(JDialog jd, int Tempo){
            // final JDialog dialog = new JDialog(this, "Test", true);
        Timer timer = new Timer(Tempo, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jd.setVisible(false);
                jd.dispose();
            }
        });
        timer.setRepeats(false);
        timer.start();
        jd.setVisible(true); 
    }
    
}
