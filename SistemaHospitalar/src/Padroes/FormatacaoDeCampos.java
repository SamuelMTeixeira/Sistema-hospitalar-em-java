/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Padroes;

import java.awt.Color;
import java.text.ParseException;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author samuel
 */
public class FormatacaoDeCampos {
    Mensagens_Prontas msg = new Mensagens_Prontas();
    
    //CRIA UMA FORMATAÇÃO ESPECÍFICA PARA RECEBER NÚMEROS DE TELEFONE
    public void Telefone (JFormattedTextField campo) {
        try {
            MaskFormatter telefone = new MaskFormatter("(##) #####-####");
            telefone.install(campo);
        }
        catch (ParseException e){
            msg.texto("Entrada inválida \nInforme os números corretamente");
        }
    }
    
    //CRIA UMA FORMATAÇÃO ESPECÍFICA PARA RECEBER DATA
    public void Data (JFormattedTextField campo) {
        try {
            MaskFormatter data = new MaskFormatter("##/##/####");
            data.install(campo);
        }
        catch (ParseException e){
            msg.texto("Entrada inválida \nInforme a data corretamente");
        }
    }
    
    //CRIA UMA FORMATAÇÃO ESPECÍFICA PARA RECEBER NÚMEROS DATA PADRÃO AMERICANO
    public void DataAmericana (JFormattedTextField campo) {
        try {
            MaskFormatter data = new MaskFormatter("####-##-##");
            data.install(campo);
        }
        catch (ParseException e){
            msg.texto("Entrada inválida \nInforme a data corretamente");
        }
    }
    
    
    //CRIA UMA FORMATAÇÃO ESPECÍFICA PARA RECEBER APENAS UM CARACTER
    public void Caracter (JFormattedTextField campo) {
        MaskFormatter formatoDois;
		try {
			formatoDois = new MaskFormatter("*");
			formatoDois.setValidCharacters("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
			formatoDois.install(campo);
		} catch (ParseException e) {
			Mensagens_Prontas msg = new Mensagens_Prontas();
                        msg.erro("letra escolhida", "valor inválido", campo);
		}
        
    }
    
    //CRIA UMA FORMATAÇÃO PARA DEIXAR O FUNDO COM TEXTO TRANSPARENTE QUANDO NÃO HAVER FOCO
    public void TextoComFoco (JTextField tfNome, String texto){
        if(tfNome.getText().equals(texto)){
            tfNome.setText("");
            tfNome.setForeground(Color.black);
        }
    }
    
    public void TextoSemFoco (JTextField tfNome, String texto){
        if(tfNome.getText().length() <= 0){
            tfNome.setForeground(new java.awt.Color(153,153,153) );
            tfNome.setText(texto);
        }
    }
    
    
}
