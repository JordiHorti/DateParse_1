/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication3;

import java.util.Calendar;

/**
 *
 * @author jordi
 */
public class Date_parse_1 {

    /**
     * @param args the command line arguments
     */
      public static void main(String[] args) {
        

         String test[] = new String[] {
             
            "5/6/23",  "d/m/yy",             
            "5d6/23",  "d/m/yy",
            "01/01/23",   "dd/mm/yyyy",            
            "29/02/2023", "dd/mm/yyyy",              
            "29/02/2008", "dd/mm/yyyy",          
            "12/2021/03", "MM/dd/YYYY",
            "27/02/2023", "DD/mm/yyyy",           
            "12/2023/04", "MM/yyyy/dd",
            "2022/03/05", "yyyy/mm/dd",
            "2021/04/25", "dd,mm,yyyy",
            "22/202/2023", "dd/mm/yyyy",
            "22-12-2023", "dd-mm-yyyy",
            "30/02/2023",  "dd/mm/yyyy",
            "13/05/1969",  "dd/mm/yyyy",
            "13/05/2005",  "dd-mm-yyyy",           
            "15,07,2022",  "dd,mm,yyyy",            
            "13/05/2019",  "dd.mm.yyyy",
            "01/01/01",   "dd/mm/yy",
            "29/02/2000", "dd/mm/yyyy",
            "29/02/2001", "dd/mm/yyyy",           
            "31/06/2023", "dd/mm/yyyy",             
            "31->01->2000", "dd->mm->yyyy",             
            "31|01|2000", "dd|mm|yyyy",
            "2023",       "yyyy",
             "30/30/07", "dd/dd/mm",          
             "30/12/20", "dd/mm/yy"
             
        };
 
        for (int n=0; n<test.length; n+=2) {
            java.util.Date d = parseDate(test[n], test[n+1]);
            System.out.printf("%s -> %s\n", test[n], d);
        }
    }
      
    public static java.util.Date parseDate(String strDate, String format) {

        int date = 0, month = 0, year = 0, contador =0;

        String str_date="", str_month="", str_year="";

        String format_conv = format.toUpperCase();

        int indx0 = strDate.indexOf('/');
        int indx1 = strDate.indexOf('/', indx0 + 1);

        for (int i = 0; i < format_conv.length(); i++) {

            if ((format_conv.charAt(i) != 'D') && (format_conv.charAt(i) != 'M') && (format_conv.charAt(i) != 'Y') || ((strDate.length() < format.length()) || (format.length() < strDate.length()) || (strDate.length() < 6))) {
                if (format_conv.charAt(i) == (strDate.charAt(i))) {
                    contador++;
                } else {
                    return null;
                }
            }

            if (format_conv.charAt(i) == 'D') {
                str_date = strDate.substring((i - date), (contador + 1));
                date++;
                contador++;

            }
            if (format_conv.charAt(i) == 'M') {

                if (month > 2) {
                    System.out.print("El número de mes se excede en dígitos:  ");
                    return null;
                }

                str_month = strDate.substring((i - month), contador + 1);
                contador++;
                month++;
            }
            if (format_conv.charAt(i) == 'Y') {
                str_year = strDate.substring((i - year), (contador + 1));
                contador++;
                year++;
            }

        }
        if ((date == 0) || (month == 0) || (year == 0)){
            return null;
        }
        date = Integer.parseInt(str_date);
        month = Integer.parseInt(str_month);
        year = Integer.parseInt(str_year);
        
        year= validacionAnio(year);
        if ((date > diasEnMes(month,year)) || (validacionAnio(year) > 2099) || (date  < 1) || (date > 31) || (validacionAnio (year) == 0)){
            return null;
        }
        
            Calendar cal = Calendar.getInstance();

            cal.set(Calendar.DATE,date);

            cal.set(Calendar.MONTH,month -1);

            cal.set(Calendar.YEAR,year);

            cal.set(Calendar.HOUR_OF_DAY, 0);

            cal.set(Calendar.MINUTE, 0);

            cal.set(Calendar.SECOND, 0);
        
            cal.set(Calendar.MILLISECOND, 0);
            
        return cal.getTime();
        
            
        }
    
    public static int diasEnMes(int month, int year) {

        int result;
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 10:
            case 12:
                return 31;
            case 2:
                if (((year % 4) == 0) && (((year % 100) != 0) || ((year % 400) == 0))) {
                    return 29;
                } else {
                    return 28;
                }

            default:
                return 30;

        }

    }

    public static int validacionAnio(int year) {
        if ((year > 9) && (year < 100)) {
            if (year > 50) {
                year = year + 1900;
            } else {
                year = year + 2000;
            }
        }
        return year;
    }
     
}

