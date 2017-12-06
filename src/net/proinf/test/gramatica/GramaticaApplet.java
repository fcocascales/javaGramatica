/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.proinf.test.gramatica;

import javax.swing.JFrame;
//import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author Administrador
 */
public class GramaticaApplet extends javax.swing.JApplet {
    
    @Override
    public void init () {       
        
        lookAndFeel();        
                
        GramaticaPanel panel = new GramaticaPanel(new GramaticaModelo());        
        this.getContentPane().add(panel);              
    }  
    
    private void lookAndFeel() {
        //JFrame.setDefaultLookAndFeelDecorated(true);
        try {
            //UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {             
            //JOptionPane.showMessageDialog(this, ex.getMessage());
        }    
    }

    /**   
     */
    public static void main(String[] args) {
        
        JFrame frame = new JFrame();
        GramaticaApplet applet = new GramaticaApplet();

        frame.getContentPane().add(applet);
        applet.init();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,400);
        frame.setTitle("Gramática");
        frame.setVisible(true);
    }
}
