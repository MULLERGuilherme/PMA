package Validacoes;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Validacoes.*;
import com.github.lgooddatepicker.components.DatePicker;
import com.toedter.calendar.JDateChooser;
import java.sql.Date;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import model.bean.Paciente;

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
        if (CPF.isEmpty()) {
            return true;
        }
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

    public static boolean vTelefone(String telefone) {
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

    public static boolean vCRP(String CRP) {
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

    public static boolean vDataNasc(String CRP) {
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

    public static Object fDataNascBD(Date datatxt) {

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormatada = formato.format(datatxt);

        return dataFormatada;
    }

 public static Object fDatetime(Timestamp datat) {


        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime fdate =datat.toLocalDateTime();
        return dtf.format(fdate)  ;
    
}

    public static boolean vCamposVazios(JFrame jframe, JTextField Nome, JTextField Email, JTextField CPF, DatePicker DataNasc, JTextField Telefone) {
        boolean vazio = false;
        String msg = "Existem Campos Obrigatórios em branco.\nFavor verificar os seguintes campos:\n";
        if (Nome.getText().isEmpty()) {
            msg += "Nome Completo";
            vazio = true;
        }
        if (Email.getText().isEmpty()) {
            msg += "\nEmail";
            vazio = true;
        }
//            if(CPF.getText().isEmpty()){
//               msg+= "\nCPF";
//                vazio = true;
//            }
        if (Telefone.getText().isEmpty()) {
            msg += "\nTelefone";
            vazio = true;
        }
        if (DataNasc.getDate() == null) {
            msg += "\nData de Nascimento";
            vazio = true;
        }
        if (vazio) {
            JOptionPane.showMessageDialog(jframe, msg);
        }
        return vazio;

    }
     public static boolean vCamposVaziosAnm(JFrame jframe, JTextField QueixaPrincipal, DatePicker data ) {
        boolean vazio = false;
        String msg = "Existem Campos Obrigatórios em branco.\nFavor verificar os seguintes campos:\n";
        if (QueixaPrincipal.getText().isEmpty()) {
            msg += "QueixaPrincipal";
            vazio = true;
        }
        if (data.getDate() == null) {
            msg += "\n InicioDaQueixa";
            vazio = true;
        }
     
        if (vazio) {
            JOptionPane.showMessageDialog(jframe, msg);
        }
        return vazio;

    }
    
        public static boolean vCamposVaziosAnt(JFrame jframe, JTextField Assunto, JTextField Texto ) {
        boolean vazio = false;
        String msg = "Existem Campos Obrigatórios em branco.\nFavor verificar os seguintes campos:\n";
        if (Assunto.getText().isEmpty()) {
            msg += "\nAssunto";
            vazio = true;
        }
        if (Texto.getText().isEmpty()) {
            msg += "\n Texto";
            vazio = true;
        }
     
        if (vazio) {
            JOptionPane.showMessageDialog(jframe, msg);
        }
        return vazio;

    }
    public static boolean vCamposVaziosPSI(JFrame jframe, JTextField Nome, JTextField Email, JTextField CRP, JTextField Login, JTextField Senha, JTextField Telefone) {
        boolean vazio = false;
        String msg = "Existem Campos Obrigatórios em branco.\nFavor verificar os seguintes campos:\n";
        if (Nome.getText().isEmpty()) {
            msg += "Nome Completo";
            vazio = true;
        }
        if (Email.getText().isEmpty()) {
            msg += "\nEmail";
            vazio = true;
        }
        if (CRP.getText().isEmpty()) {
            msg += "\nCRP";
            vazio = true;
        }
        if (Telefone.getText().isEmpty()) {
            msg += "\nTelefone";
            vazio = true;
        }
        if (Login.getText().isEmpty()) {
            msg += "\nLogin";
            vazio = true;
        }
        if (Senha.getText().isEmpty()) {
            msg += "\nSenha";
            vazio = true;
        }
        if (vazio) {
            JOptionPane.showMessageDialog(jframe, msg);
        }
        return vazio;

    }
    
    

    public static boolean vCamposVaziosPSI(JFrame jframe, JTextField Email, JTextField CRP) {
        boolean vazio = false;
        String msg = "Existem Campos Obrigatórios em branco.\nFavor verificar os seguintes campos:\n";

        if (Email.getText().isEmpty()) {
            msg += "\nEmail";
            vazio = true;
        }
        if (CRP.getText().isEmpty()) {
            msg += "\nCRP";
            vazio = true;
        }
        if (vazio) {
            JOptionPane.showMessageDialog(jframe, msg);
        }
        return vazio;

    }

    public static boolean vCamposVaziosPSI(JFrame jframe, JTextField Usuario, JTextField Senha, JTextField ConfirmarSenha) {
        boolean vazio = false;
        String msg = "Existem Campos Obrigatórios em branco.\nFavor verificar os seguintes campos:\n";

        if (Usuario.getText().isEmpty()) {
            msg += "\nUsuário";
            vazio = true;
        }
        if (Senha.getText().isEmpty()) {
            msg += "\nNova Senha";
            vazio = true;
        }
        if (ConfirmarSenha.getText().isEmpty()) {
            msg += "\nConfirmar Senha";
            vazio = true;
        }
        if (vazio) {
            JOptionPane.showMessageDialog(jframe, msg);
        }
        return vazio;

    }
    
    
    public static boolean vCamposVaziosManterPSI(JFrame jframe, JTextField Nome, JTextField Email, JTextField CRP, JTextField Login, JTextField Telefone) {
        boolean vazio = false;
        String msg = "Existem Campos Obrigatórios em branco.\nFavor verificar os seguintes campos:\n";
        if (Nome.getText().isEmpty()) {
            msg += "Nome Completo";
            vazio = true;
        }
        if (Email.getText().isEmpty()) {
            msg += "\nEmail";
            vazio = true;
        }
        if (CRP.getText().isEmpty()) {
            msg += "\nCRP";
            vazio = true;
        }
        if (Telefone.getText().isEmpty()) {
            msg += "\nTelefone";
            vazio = true;
        }
        if (Login.getText().isEmpty()) {
            msg += "\nLogin";
            vazio = true;
        }
        if(Telefone.getText().isEmpty()){
            msg += "\nTelefone 1";
            vazio = true;
        }
        if (vazio) {
            JOptionPane.showMessageDialog(jframe, msg);
        }
        return vazio;

    }
    
}
