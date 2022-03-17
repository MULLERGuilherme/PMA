/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import connection.ConnectionFactory;
import java.text.ParseException;
import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvException;

/**
 *
 * @author User
 */
public class Main {

    public static int cod = -1;

    public static void main(String[] args) throws ParseException {
        Dotenv dotenv = Dotenv.load();

        try {

            ConnectionFactory.USER = dotenv.get("USER");
            ConnectionFactory.PASSWORD = dotenv.get("PASSWORD");
            ConnectionFactory.URL = dotenv.get("URL");
        } catch(DotenvException e){
            System.out.println("Favor Verificar o arquivo .env");
        }
        TelaLogin tl = new TelaLogin();
        tl.setVisible(true);

    }
}
