/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Padroes;

import java.util.InputMismatchException;
import javax.swing.JTextField;

/**
 *
 * @author samuel
 */
public class Validacoes {

    Mensagens_Prontas msg = new Mensagens_Prontas();
    public boolean isFloat(JTextField identificacaoCampo, String nomeDoCampo) {
        try {
            float valor = Float.parseFloat( identificacaoCampo.getText() );
            return true;
        }
        
        catch (Exception e) {
            identificacaoCampo.setText("");
            identificacaoCampo.requestFocus();
            msg.erro(nomeDoCampo, "Informe o valor corretamente", identificacaoCampo);
            return false;
        }
    }
    
    public boolean isInt (JTextField identificacaoCampo, String nomeDoCampo) {
        try {
            int valor = Integer.parseInt(identificacaoCampo.getText() );
            return true;
        }
        
        catch (Exception e) {
            identificacaoCampo.setText("");
            identificacaoCampo.requestFocus();
            msg.erro(nomeDoCampo, "Informe o valor corretamente", identificacaoCampo);
            return false;
        }
    }
    
    public boolean isNotReal (JTextField identificacaoCampo, float val){
        if (val <0) {
            identificacaoCampo.setText("");
            identificacaoCampo.requestFocus();
            return true;
        }
        else 
            return false;
    }
    
    public boolean isVoid (JTextField identificacaoCampo){
        if ( identificacaoCampo.getText().length() == 0 ) {
            return true;
        }
        else {
            return false;
        }  
    }
    
    // VALIDA SE O CPF É REALMENTE VÁLIDO
    public boolean isCPF  (String CPF) {
        CPF = CPF.replace('.',' ');
        CPF = CPF.replace('-',' ');
        CPF = CPF.replaceAll(" ","");
                
                // considera-se erro CPF's formados por uma sequencia de numeros iguais
		if (CPF.equals("00000000000") || CPF.equals("11111111111") || CPF.equals("22222222222") || CPF.equals("33333333333") || CPF.equals("44444444444") || CPF.equals("55555555555") || CPF.equals("66666666666") || CPF.equals("77777777777") || CPF.equals("88888888888") || CPF.equals("99999999999") || (CPF.length() != 11))
			return (false);

		char dig10, dig11;
		int sm, i, r, num, peso;

                // VERIFICA SE O CPF É REAL
		try {
			// Calculo do 1o. Digito Verificador
			sm = 0;
			peso = 10;
			for (i = 0; i < 9; i++) {
				// converte o i-esimo caractere do CPF em um numero:
				// por exemplo, transforma o caractere '0' no inteiro 0        
				// (48 eh a posicao de '0' na tabela ASCII)        
				num = (int) (CPF.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
				dig10 = '0';
			else
				dig10 = (char) (r + 48); // converte no respectivo caractere numerico

			// Calculo do 2o. Digito Verificador
			sm = 0;
			peso = 11;
			for (i = 0; i < 10; i++) {
				num = (int) (CPF.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
				dig11 = '0';
			else
				dig11 = (char) (r + 48);

			// Verifica se os digitos calculados conferem com os digitos informados.
			if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
				return (true);
			else
				return (false);
		} catch (InputMismatchException erro) {
			return (false);
		}
        
        
        
    }
    
    
}
