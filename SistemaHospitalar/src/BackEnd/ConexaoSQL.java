/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;


/**
 *
 * @author samuel
 */
public class ConexaoSQL {
    private Statement stmt; //executar consultas DML
    private ResultSet rs; //gerenciar consultas DQL
    private Connection c;         

    public ConexaoSQL() {
        String Database = "bdsistemahospital";
        String user = "root";
        String password = "";
        
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/" + Database;
        
        //Abrir conexão
        try {            
            Class.forName(driver).newInstance();                               
            c = DriverManager.getConnection(url, user, password);    
            stmt = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro na conexão MySQL");            
            JOptionPane.showMessageDialog(null, e);
            JOptionPane.showMessageDialog(null, "A aplicação será finalizada...");
            System.exit(0); //Finalizar a aplicação
        }              
    }    
    
    public boolean SQLExecute(String SQL) {
        try {
            this.stmt.execute(SQL);
            return true;
        }
        catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }        
    }

    public boolean setResultSet(String Query) {
        try {
            this.rs = this.stmt.executeQuery(Query);
            return true;
        }
        catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }                
    }             
    
    public ResultSet getResultSet() {
        return rs;
    }       
    
    
}
