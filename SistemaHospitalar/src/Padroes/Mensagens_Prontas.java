/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Padroes;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author samuel
 */
public class Mensagens_Prontas {
    public void erro (String nome, String descricao, JTextField campo){
        JOptionPane.showMessageDialog(null, "O valor inserido em "+nome+" é inválido \n"+descricao, "ALERTA IMPORTANTE", JOptionPane.ERROR_MESSAGE);
        campo.setText("");
        campo.requestFocus();
    }
    
    public void successfullyRegistered (String nome){
        JOptionPane.showMessageDialog(null, nome+" com sucesso!");
    } 
    
    public void voidCamp (String nome, JTextField campo){
        JOptionPane.showMessageDialog(null, "Impossível concluir ação! \nAinda não foi informado "+nome);
        campo.setText("");
        campo.requestFocus();
    }

    public void texto (String valor){
        JOptionPane.showMessageDialog(null, valor);
    }

    public void problemMessage (String acao, String descricao) {
        JOptionPane.showMessageDialog(null, "Impossível concluir a operação de "+acao+"\n"+descricao, "ALERTA IMPORTANTE", JOptionPane.ERROR_MESSAGE);
    }
    
    public boolean confirmarAcao (String acao, String nome) {
        if (JOptionPane.showConfirmDialog(null, "Você tem certeza que deseja "+acao+" "+nome+"?", "MENSAGEM DE CONFIRMAÇÃO", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            return true;
        }
        else
            return false;
    }
    
}
