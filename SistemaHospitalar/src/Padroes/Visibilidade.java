/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Padroes;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

/**
 *
 * @author samuel
 */
public class Visibilidade {
    public void ocultar (JLabel txt, JTextField tf, JButton btn){
        txt.setVisible(false);
        tf.setVisible(false);
        btn.setVisible(false);
        tf.setText("");
    }
   public void mostrar (JLabel txt, JTextField tf){
        txt.setVisible(true);
        tf.setVisible(true);
    } 
    
    
    public void visualizarPainel (JPanel p, JSeparator s){
        p.setVisible(true);
        s.setVisible(true);
    }
    
    public void novaAba (JLabel txt, JTextField tf) {
            txt.setVisible(true);
            tf.setVisible(true);
            tf.requestFocus();
    }

    
}
