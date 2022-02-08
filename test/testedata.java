
import java.text.ParseException;
import java.text.SimpleDateFormat;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public class testedata {
            String date_time = "11/27/2020 05:35:00";
            SimpleDateFormat dateParser = new SimpleDateFormat("MM/dd/yy HH:mm:ss");
        {
            try {
                java.util.Date date = dateParser.parse(date_time);
                //System.out.println(date);

                SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
               System.out.println(dateFormatter.format(date));

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        
    }

