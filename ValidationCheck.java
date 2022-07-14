package validateDate;

import java.util.Scanner;
import java.time.LocalDate;

public class ValidationCheck {
    static int flag = 0;

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

    public String[] printDays(int yyyy, int mm, int dd) {
        String stringArray[] = new String[7];
        LocalDate givenDate = LocalDate.of(yyyy, mm, dd);
//        System.out.println(dd + "/" + mm + "/" + yyyy + which is a + givenDate.getDayOfWeek());

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
            stringArray[i] = day + "/" + month + "/" + year + " which is a " + finalDate.getDayOfWeek();
//            System.out.println(stringArray[i]);
        }
        return stringArray;
    }

    public String getDays(int day, int month, int year) {

        LocalDate finalDate = LocalDate.of(year, month, day);
        System.out.print(day + "/" + month + "/" + year + " which is a ");
        System.out.println(finalDate.getDayOfWeek());
        return null;


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
            format.dateFormatCheck(date);

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
            print.printDays(yyyy, mm, dd);

            String[] yearEndResult = print.printDays(2000, 12, 31);
            String[] yearEndExpected = {"1/1/2001 which is a MONDAY",
                    "2/1/2001 which is a TUESDAY",
                    "3/1/2001 which is a WEDNESDAY",
                    "4/1/2001 which is a THURSDAY",
                    "5/1/2001 which is a FRIDAY",
                    "6/1/2001 which is a SATURDAY",
                    "7/1/2001 which is a SUNDAY"};
            for (int i = 0; i < 7; i++) {
                if (yearEndExpected[i].equalsIgnoreCase(yearEndResult[i])) {
                    flag = 1;
                } else {
                    flag = 0;
                    break;
                }
            }
            if (flag == 1) {
                System.out.println("Works properly for year end: TRUE");
            } else {
                System.out.println("Works properly for year end: FALSE");
            }

            String[] leapYearResult = print.printDays(2000, 02, 28);
            String[] leapYearExpected = {"29/2/2000 which is a TUESDAY",
                    "1/3/2000 which is a WEDNESDAY",
                    "2/3/2000 which is a THURSDAY",
                    "3/3/2000 which is a FRIDAY",
                    "4/3/2000 which is a SATURDAY",
                    "5/3/2000 which is a SUNDAY",
                    "6/3/2000 which is a MONDAY"};

            for (int i = 0; i < 7; i++) {
                if (leapYearExpected[i].equalsIgnoreCase(leapYearResult[i])) {
                    flag = 1;
                } else {
                    flag = 0;
                    break;
                }
            }
            if (flag == 1) {
                System.out.println("Works properly for leap-year: TRUE");
            } else {
                System.out.println("Works properly for leap-year: FALSE");
            }

            String[] nonLeapYearResult = print.printDays(2001, 02, 27);
            String[] nonLeapyearExpected = {"28/2/2001 which is a WEDNESDAY",
                    "1/3/2001 which is a THURSDAY",
                    "2/3/2001 which is a FRIDAY",
                    "3/3/2001 which is a SATURDAY",
                    "4/3/2001 which is a SUNDAY",
                    "5/3/2001 which is a MONDAY",
                    "6/3/2001 which is a TUESDAY"};

            for (int i = 0; i < 7; i++) {
                if (nonLeapyearExpected[i].equalsIgnoreCase(nonLeapYearResult[i])) {
                    flag = 1;
                } else {
                    flag = 0;
                    break;
                }
            }
            if (flag == 1) {
                System.out.println("Works properly for non-leap year: TRUE");
            } else {
                System.out.println("Works properly for non-leap year: FALSE");
            }

            String[] julyResult = print.printDays(2001, 07, 27);
            String[] julyExpected = {"28/7/2001--SATURDAY",
                    "29/7/2001 which is a SUNDAY",
                    "30/7/2001 which is a MONDAY",
                    "31/7/2001 which is a TUESDAY",
                    "1/8/2001 which is a WEDNESDAY",
                    "2/8/2001 which is a THURSDAY",
                    "3/8/2001 which is a FRIDAY"};

            for (int i = 0; i < 7; i++) {
                if (nonLeapyearExpected[i].equalsIgnoreCase(nonLeapYearResult[i])) {
                    flag = 1;
                } else {
                    flag = 0;
                    break;
                }
            }
            if (flag == 1) {
                System.out.println("Works properly for july month end: TRUE");
            } else {
                System.out.println("Works properly for july month end: FALSE");
            }
        } catch (Exception e) {
            System.out.println("Please enter valid date");
        }
    }

}




