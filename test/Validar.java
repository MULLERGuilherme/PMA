/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Validacoes.*;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class Validar {

    public static boolean vEmail(String email) {
        boolean isEmailIdValid = false;
        if (email != null && email.length() > 0) {
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                isEmailIdValid = true;
            }
        }
        return isEmailIdValid;
    }

    public static boolean vCPF(String CPF) {
        // considera-se erro CPF's formados por uma sequencia de numeros iguais
        if (CPF.equals("00000000000")
                || CPF.equals("11111111111")
                || CPF.equals("22222222222") || CPF.equals("33333333333")
                || CPF.equals("44444444444") || CPF.equals("55555555555")
                || CPF.equals("66666666666") || CPF.equals("77777777777")
                || CPF.equals("88888888888") || CPF.equals("99999999999")
                || (CPF.length() != 11)) {
            return (false);
        }

        char dig10, dig11;
        int sm, i, r, num, peso;

        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
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
            if ((r == 10) || (r == 11)) {
                dig10 = '0';
            } else {
                dig10 = (char) (r + 48); // converte no respectivo caractere numerico
            }
            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig11 = '0';
            } else {
                dig11 = (char) (r + 48);
            }

            // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10))) {
                return (true);
            } else {
                return (false);
            }
        } catch (InputMismatchException erro) {
            return (false);
        }
    }

//        public static String imprimeCPF(String CPF) {
//            return(CPF.substring(0, 3) + "." + CPF.substring(3, 6) + "." +
//            CPF.substring(6, 9) + "-" + CPF.substring(9, 11));
//        }
    
    public static boolean vNome(String nome) {
        boolean NomeValido = false;
        if (nome != null && nome.length() > 0) {
            String expression = "^\\p{L}+[\\p{L}\\p{Z}\\p{P}]{0,}";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(nome);
            if (matcher.matches()) {
                NomeValido = true;
            }
        }
        return NomeValido;
    }
    
     public static boolean vTelefone(String telefone){
        boolean NumeroValido = false;
        if (telefone != null && telefone.length() > 0) {
            String expression = "^\\d{8,11}+$"; 
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(telefone);
            if (matcher.matches()) {
                NumeroValido = true;
            }
        }
        return NumeroValido;
    }
     
     public static boolean vCRP(String CRP){
        boolean CRPValido = false;
        if (CRP != null && CRP.length() > 0) {
            String expression = "^\\d{8}+$"; 
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(CRP);
            if (matcher.matches()) {
                CRPValido = true;
            }
        }
        return CRPValido;
    }
      public static boolean vDataNasc(String CRP){
        boolean CRPValido = false;
        if (CRP != null && CRP.length() > 0) {
            String expression = "^\\d{8}+$"; 
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(CRP);
            if (matcher.matches()) {
                CRPValido = true;
            }
        }
        return CRPValido;
    }
      
         public static String fDataNascBD(String datatxt){
             
         String data;
         data = datatxt.replaceAll("-", "/");
         String[] s = data.split("/");
         String novaData = s[2]+"/"+s[1]+"/"+s[0];
         return novaData;
        
   
    }
     
}
