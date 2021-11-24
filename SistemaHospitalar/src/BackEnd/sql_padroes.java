/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd;

import Padroes.Mensagens_Prontas;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author samuel
 */
public class sql_padroes {
    
    Mensagens_Prontas msg = new Mensagens_Prontas();
    
    private ConexaoSQL  conexao = new ConexaoSQL();
    
    public boolean isExists ( String table, String column, String name){
    String val = "false";
        try {
            /* <<< LINHA DE COMANDO SQL >>> */
            String SQL = "SELECT 'true' AS result FROM "+table+" WHERE ("+column+" = '"+name+"' )";
            
            /* <<< EXECUTA LINHA DE COMANDO SQL >>> */
            this.conexao.setResultSet(SQL);
            
            /* <<< PEGA OS VALORES DA TELA, DO PRIMEIRO PARA O ULTIMO >>> */
            if (this.conexao.getResultSet().first() ){
                val = this.conexao.getResultSet().getString("result");
            } 
        }
        catch(SQLException e)  {
            JOptionPane.showMessageDialog(null, e);
        }
        
        
        if(val.equals("true")) {
            return true;
        }
        else return false;
        
    }
    
    public String getCodigo (JComboBox cb, String cod, String table){
        String codigo = (String) cb.getSelectedItem();
        try {
           this.conexao.setResultSet("SELECT "+cod+" FROM "+table+" WHERE nome LIKE '"+codigo+"'");
           if(this.conexao.getResultSet().first() )
                return this.conexao.getResultSet().getString(cod);
            else return "erro";
        }
        catch (SQLException e){
            return "erro";
        }
    }  
    
    public void setPreencherComboBox (JComboBox cb, String table){
        try {
            String SQL = "SELECT DISTINCT nome FROM "+table+" order by nome";
            this.conexao.setResultSet(SQL);
            cb.removeAllItems();
            if(this.conexao.getResultSet().first() ) {
                do {
                    cb.addItem(this.conexao.getResultSet().getString("nome"));
                }while(this.conexao.getResultSet().next());
            }
           cb.setSelectedIndex(-1);
        }
        catch (SQLException e){
            msg.problemMessage("erro", "");
        }
    }
    
    
    
    
    
    
}
