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
//        System.out.println(dd + "/" + mm + "/" + yyyy + "---->" + givenDate.getDayOfWeek());

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
            stringArray[i] = day + "/" + month + "/" + year + "--->" + finalDate.getDayOfWeek();
//            System.out.println(stringArray[i]);
        }
        return stringArray;
    }

    public String getDays(int day, int month, int year) {

        LocalDate finalDate = LocalDate.of(year, month, day);
        System.out.print(day + "/" + month + "/" + year + "---->");
        System.out.println(finalDate.getDayOfWeek());
        return null;


    }

    public static void main(String[] args) {

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
        String[] yearEndExpected = {"1/1/2001--->MONDAY", "2/1/2001--->TUESDAY", "3/1/2001--->WEDNESDAY", "4/1/2001--->THURSDAY", "5/1/2001--->FRIDAY", "6/1/2001--->SATURDAY", "7/1/2001--->SUNDAY"};
        System.out.println("\nTestcase1(result)");
        for (int i = 0; i < 7; i++) {
            System.out.println(yearEndResult[i]);
            if (yearEndExpected[i].equalsIgnoreCase(yearEndResult[i])) {
                flag = 1;
            } else {
                flag = 0;
                break;
            }
        }
        if (flag == 1) {
            System.out.println("Test case 1 is passed");
        } else {
            System.out.println("Test case 1 is failed");
        }

        String[] leapYearResult = print.printDays(2000, 02, 28);
        String[] leapYearExpected = {"29/2/2000--->TUESDAY", "1/3/2000--->WEDNESDAY", "2/3/2000--->THURSDAY", "3/3/2000--->FRIDAY", "4/3/2000--->SATURDAY", "5/3/2000--->SUNDAY", "6/3/2000--->MONDAY"};
        System.out.println("\nTestcase2(result)");
        for (int i = 0; i < 7; i++) {
            System.out.println(leapYearResult[i]);
            if (leapYearExpected[i].equalsIgnoreCase(leapYearResult[i])) {
                flag = 1;
            } else {
                flag = 0;
                break;
            }
        }
        if (flag == 1) {
            System.out.println("Test case 2 is passed");
        } else {
            System.out.println("Test case 2 is failed");
        }

        String[] nonLeapYearResult = print.printDays(2001, 02, 27);
        String[] nonLeapyearExpected = {"28/2/2001--->WEDNESDAY", "1/3/2001--->THURSDAY", "2/3/2001--->FRIDAY", "3/3/2001--->SATURDAY", "4/3/2001--->SUNDAY", "5/3/2001--->MONDAY", "6/3/2001--->TUESDAY"};
        System.out.println("\nTestcase3(result)");
        for (int i = 0; i < 7; i++) {
            System.out.println(nonLeapYearResult[i]);
            if (nonLeapyearExpected[i].equalsIgnoreCase(nonLeapYearResult[i])) {
                flag = 1;
            } else {
                flag = 0;
                break;
            }
        }
        if (flag == 1) {
            System.out.println("Test case 3 is passed");
        } else {
            System.out.println("Test case 3 is failed");
        }

        String[] julyResult = print.printDays(2001, 07, 27);
        String[] julyExpected = {"28/7/2001--->SATURDAY",
                "29/7/2001--->SUNDAY",
                "30/7/2001--->MONDAY",
                "31/7/2001--->TUESDAY",
                "1/8/2001--->WEDNESDAY",
                "2/8/2001--->THURSDAY",
                "3/8/2001--->FRIDAY"};
        System.out.println("\nTestcase4(result)");
        for (int i = 0; i < 7; i++) {
            System.out.println(nonLeapYearResult[i]);
            if (nonLeapyearExpected[i].equalsIgnoreCase(nonLeapYearResult[i])) {
                flag = 1;
            } else {
                flag = 0;
                break;
            }
        }
        if (flag == 1) {
            System.out.println("Test case 4 is passed");
        } else {
            System.out.println("Test case 4 is failed");
        }
    }
}




