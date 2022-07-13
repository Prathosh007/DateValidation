package validateDate;

import java.time.LocalDate;
import java.util.Scanner;

public class ValidationCheck {
    public String DateFormatCheck(String date) {
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

    public void PrintDays(int yyyy, int mm, int dd, String date) {
        try {
            LocalDate givenDate = LocalDate.of(yyyy, mm, dd);
            System.out.println(date + "---->" + givenDate.getDayOfWeek());

            for (int i = 0; i < 7; i++) {
                dd = i + 1;
                LocalDate nextDate = givenDate.plusDays(dd);
                String currDate = nextDate.toString();

                String nextDay = String.valueOf(currDate.substring(8, 10));
                String nextMonth = String.valueOf(currDate.substring(5, 7));
                String nextYear = String.valueOf(currDate.substring(0, 4));

                int day = Integer.parseInt(nextDay);
                int month = Integer.parseInt(nextMonth);
                int year = Integer.parseInt(nextYear);

                LocalDate finalDate = LocalDate.of(year, month, day);

                System.out.print(day + "/" + month + "/" + year + "---->");
                System.out.println(finalDate.getDayOfWeek());
            }

        } catch (Exception e) {
            System.out.println("Please enter valid date.");
        }
    }


    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(System.in);
            System.out.print("Enter the date in format [DD/MM/YYYY] :");
            String date = in.nextLine();


            String inputDay = String.valueOf(date.substring(0, 2));
            String inputMonth = String.valueOf(date.substring(3, 5));
            String inputYear = String.valueOf(date.substring(6, 10));

            ValidationCheck format = new ValidationCheck();
            format.DateFormatCheck(date);

            ValidationCheck dayValidate = new ValidationCheck();
            dayValidate.DayValidate(date);

            ValidationCheck monthValidate = new ValidationCheck();
            monthValidate.MonthValidate(date);

            ValidationCheck yearValidate = new ValidationCheck();
            yearValidate.YearValidate(date);


            int dd = Integer.parseInt(inputDay);
            int mm = Integer.parseInt(inputMonth);
            int yyyy = Integer.parseInt(inputYear);


            ValidationCheck print = new ValidationCheck();
            print.PrintDays(yyyy, mm, dd, date);
        } catch (Exception e) {
            System.out.println("Please enter valid date.");
        }

    }
}


