package dateOperations;

import java.util.*;
import java.time.LocalDate;

public class DateOperation {
    public String dateFormatCheck(String date) {
        try {
            if (date.length() != 10) {
                throw new IllegalArgumentException("length limit not maintained");
            } else if (date.charAt(2) != '/' || date.charAt(5) != '/') {
                throw new IllegalArgumentException("Please use '/");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Please enter valid date");
        }
        return date;
    }

    public void validateDay(String date) {
        try {
            for (int i = 0; i < 2; i++) {
                if (Character.isDigit(date.toCharArray()[i])) {
                    continue;
                } else {
                    throw new IllegalArgumentException("Day is not in digit");
                }
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Please enter valid date");
        }
    }

    public void validateMonth(String date) {
        try {
            for (int i = 3; i < 5; i++) {
                if (Character.isDigit(date.toCharArray()[i])) {
                    continue;
                } else {
                    throw new IllegalArgumentException("Month is not in integer.");
                }
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Please enter valid date");
        }
    }

    public void validateYear(String date) {
        try {
            for (int i = 6; i < 10; i++) {
                if (Character.isDigit(date.toCharArray()[i])) {
                    continue;
                } else {
                    throw new IllegalArgumentException("Year is not in integer");
                }
            }
        } catch (Exception e) {
            System.out.println("Please enter valid date.");
        }
    }

    public void printResult(LinkedHashMap<String, String> result) {
        for (Map.Entry<String, String> j : result.entrySet()) {
            String s = j.getKey() + " which is a " + j.getValue();
            System.out.println(s);
        }
    }


    public LinkedHashMap<String, String> getDays(int inputYear, int inputMonth, int inputDay) {

        LinkedHashMap<String, String> yearEnd = new LinkedHashMap<String, String>();
        LocalDate givenDate = LocalDate.of(inputYear, inputMonth, inputDay);

        for (int i = 0; i < 7; i++) {
            inputDay = i + 1;
            LocalDate nextDate = givenDate.plusDays(inputDay);
            String currDate = nextDate.toString();

            int dd = Integer.parseInt(currDate.substring(8, 10));
            int mm = Integer.parseInt(currDate.substring(5, 7));
            int yyyy = Integer.parseInt(currDate.substring(0, 4));

            LocalDate finalDate = LocalDate.of(yyyy, mm, dd);
            String day = " " + finalDate.getDayOfWeek();
            String date = dd + "/" + mm + "/" + yyyy;
            yearEnd.put(date, day);
        }
        return yearEnd;
    }


    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(System.in);
            System.out.print("Enter the date in format [DD/MM/YYYY] :");
            String date = in.nextLine();

            int inputDay = Integer.parseInt(date.substring(0, 2));
            int inputMonth = Integer.parseInt(date.substring(3, 5));
            int inputYear = Integer.parseInt(date.substring(6, 10));

            DateOperation validate = new DateOperation();
            validate.dateFormatCheck(date);
            validate.validateDay(date);
            validate.validateMonth(date);
            validate.validateYear(date);
            LinkedHashMap<String, String> result = validate.getDays(inputYear, inputMonth, inputDay);
            validate.printResult(result);
        }catch (Exception e){
            throw new IllegalArgumentException("Date is not valid");
        }
    }
}




