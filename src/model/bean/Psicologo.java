/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

/**
 *
 * @author User
 */
public class Psicologo {
 private int CodPsicologo;
 private String CRP;
 private String Nome_completo;
 private String Email;
 private String Login;
 private String Senha;

    public int getCodPsicologo() {
        return CodPsicologo;
    }

    public void setCodPsicologo(int CodPsicologo) {
        this.CodPsicologo = CodPsicologo;
    }

    public String getCRP() {
        return CRP;
    }

    public void setCRP(String CRP) {
        this.CRP = CRP;
    }

    public String getNome_completo() {
        return Nome_completo;
    }

    public void setNome_completo(String Nome_completo) {
        this.Nome_completo = Nome_completo;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String Login) {
        this.Login = Login;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String Senha) {
        this.Senha = Senha;
    }
 
}
