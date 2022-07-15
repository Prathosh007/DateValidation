package validateDate;

import java.util.*;
import java.time.LocalDate;

public class ValidationCheck {
    public String dateFormatCheck(String date) {
        try {
            if (date.length() != 10) {
                System.out.println("Length limit not maintained");
                System.out.println("Please enter the date in valid format [DD/MM/YYYY].");
                System.exit(0);
            } else if (date.charAt(2) != '/' || date.charAt(5) != '/') {
                System.out.println("Please use '/'");
                System.out.println("Please enter the date in valid format [DD/MM/YYYY].");
                System.exit(0);
            }
        } catch (Exception e) {
            System.out.println("Enter valid date.");
        }
        return date;
    }

    public void DayValidate(String date) {
        try {
            for (int i = 0; i < 2; i++) {
                if (Character.isDigit(date.toCharArray()[i])) {
                    continue;
                } else {
                    System.out.println("Day is not in integer.");
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Please enter valid date.");
        }
    }

    public void MonthValidate(String date) {
        try {
            for (int i = 3; i < 5; i++) {
                if (Character.isDigit(date.toCharArray()[i])) {
                    continue;
                } else {
                    System.out.println("Month is not in integer.");
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Please enter valid date.");
        }
    }

    public void YearValidate(String date) {
        try {
            for (int i = 6; i < 10; i++) {
                if (Character.isDigit(date.toCharArray()[i])) {
                    continue;
                } else {
                    System.out.println("Year is not in integer.");
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Please enter valid date.");
        }
    }

    public void printResult(LinkedHashMap<String, String>  result){
        for (Map.Entry<String, String> j : result.entrySet()) {
            String s = j.getKey() + " which is a " + j.getValue();
            System.out.println(s);
        }
    }


    public LinkedHashMap<String, String> getDays(int inputYear, int inputMonth, int inputDay) {
        LinkedHashMap<String, String> yearEnd = new LinkedHashMap<String, String>();
        LocalDate givenDate = LocalDate.of(inputYear, inputMonth, inputDay);
//        System.out.println(dd + "/" + mm + "/" + yyyy + which is a + givenDate.getDayOfWeek());
        for (int i = 0; i < 7; i++) {
            inputDay = i + 1;
            LocalDate nextDate = givenDate.plusDays(inputDay);
            String currDate = nextDate.toString();

            int dd = Integer.parseInt(currDate.substring(8, 10));
            int mm = Integer.parseInt(currDate.substring(5, 7));
            int yyyy = Integer.parseInt(currDate.substring(0, 4));

            LocalDate finalDate = LocalDate.of(yyyy, mm, dd);
            String day= " " + finalDate.getDayOfWeek();
            String date=dd + "/" + mm + "/" + yyyy;
            yearEnd.put(date,day);
        }
        return yearEnd;
    }

    public void testYearEnd() {
        LinkedHashMap<String, String> result = getDays(2000,12,31);
        LinkedHashMap<String, String> yearEnd = new LinkedHashMap<String, String>();

        yearEnd.put("1/1/2001", " MONDAY");
        yearEnd.put("2/1/2001", " TUESDAY");
        yearEnd.put("3/1/2001", " WEDNESDAY");
        yearEnd.put("4/1/2001", " THURSDAY");
        yearEnd.put("5/1/2001", " FRIDAY");
        yearEnd.put("6/1/2001", " SATURDAY");
        yearEnd.put("7/1/2001", " SUNDAY");
        if(yearEnd.equals(result)){
            System.out.println("Works properly for year end:TRUE");
        }else{
            System.out.println("Works properly for year end:FALSE");
        }

    }

    public void testLeapYear() {
//        String[] result = getDays(2000,02,28);
        LinkedHashMap<String, String> result = getDays(2000,02,28);
//        ArrayList leapYearArray = new ArrayList();
        LinkedHashMap<String, String> leapYear = new LinkedHashMap<String, String>();

        leapYear.put("29/2/2000", " TUESDAY");
        leapYear.put("1/3/2000", " WEDNESDAY");
        leapYear.put("2/3/2000", " THURSDAY");
        leapYear.put("3/3/2000", " FRIDAY");
        leapYear.put("4/3/2000", " SATURDAY");
        leapYear.put("5/3/2000", " SUNDAY");
        leapYear.put("6/3/2000", " MONDAY");
        if (leapYear.equals(result)){
            System.out.println("works properly for leap year:TRUE");
        }else{
            System.out.println("works properly for leap year:FALSE");
        }
    }


    public void testNonLeapYear() {
        LinkedHashMap<String, String> result = getDays(2001,02,27);
        LinkedHashMap<String, String> nonLeapYear = new LinkedHashMap<String, String>();

        nonLeapYear.put("28/2/2001", " WEDNESDAY");
        nonLeapYear.put("1/3/2001", " THURSDAY");
        nonLeapYear.put("2/3/2001", " FRIDAY");
        nonLeapYear.put("3/3/2001", " SATURDAY");
        nonLeapYear.put("4/3/2001", " SUNDAY");
        nonLeapYear.put("5/3/2001", " MONDAY");
        nonLeapYear.put("6/3/2001", " TUESDAY");

     if(nonLeapYear.equals(result)){
         System.out.println("works properly for non-leap year:TRUE");
     }else{
         System.out.println("Works properly for non-leap year:FALSE");
     }

    }

    public void test31Days() {
        LinkedHashMap<String, String> result = getDays(2001,07,27);
        LinkedHashMap<String, String> check31Days = new LinkedHashMap<String, String>();

        check31Days.put("28/7/2001", " SATURDAY");
        check31Days.put("29/7/2001", " SUNDAY");
        check31Days.put("30/7/2001", " MONDAY");
        check31Days.put("31/7/2001", " TUESDAY");
        check31Days.put("1/8/2001", " WEDNESDAY");
        check31Days.put("2/8/2001", " THURSDAY");
        check31Days.put("3/8/2001", " FRIDAY");

        if(check31Days.equals(result)){
            System.out.println("works properly for 31 days months:TRUE");
        }else{
            System.out.println("works properly for 31 days months:FALSE");
        }
    }
    public void test30Days(){
        LinkedHashMap<String, String> result = getDays(1999,11,28);
        LinkedHashMap<String, String> check30Days = new LinkedHashMap<String, String>();

        check30Days.put("29/11/1999", " MONDAY");
        check30Days.put("30/11/1999", " TUESDAY");
        check30Days.put("1/12/1999", " WEDNESDAY");
        check30Days.put("2/12/1999", " THURSDAY");
        check30Days.put("3/12/1999", " FRIDAY");
        check30Days.put("4/12/1999", " SATURDAY");
        check30Days.put("5/12/1999", " SUNDAY");

        if(check30Days.equals(result)){
            System.out.println("Works properly for 30 days month:TRUE");
        }else{
            System.out.println("Works properly for 30 days month:FALSE");
        }

    }

    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(System.in);
            System.out.print("Enter the date in format [DD/MM/YYYY] :");
            String date = in.nextLine();

            int inputDay = Integer.parseInt(date.substring(0, 2));
            int inputMonth = Integer.parseInt(date.substring(3, 5));
            int inputYear = Integer.parseInt(date.substring(6, 10));

            ValidationCheck validate = new ValidationCheck();
            validate.dateFormatCheck(date);
            validate.DayValidate(date);
            validate.MonthValidate(date);
            validate.YearValidate(date);

            ValidationCheck printResult = new ValidationCheck();
            LinkedHashMap<String, String>  result = validate.getDays(inputYear, inputMonth, inputDay);
            printResult.printResult(result);

            ValidationCheck testCase = new ValidationCheck();
            testCase.testLeapYear();
            testCase.testNonLeapYear();
            testCase.testYearEnd();
            testCase.test31Days();
            testCase.test30Days();
        } catch (Exception e) {
            System.out.println("Please enter valid date");
        }
    }
}





