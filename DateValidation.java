package validateDate;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Scanner;

public class DateValidation {
    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(System.in);
            System.out.print("Enter the date in format [DD/MM/YYYY] :");
            String date = in.nextLine();

            try {
                if (date.length() != 10) {
                    System.out.println("Length limit exceeded");
                    System.out.println("Please enter the date in valid format [DD/MM/YYYY]");
                    System.exit(0);
                } else if (date.charAt(2) != '/' && date.charAt(5) != '/') {
                    System.out.println("Please use '/'");
                    System.out.println("Please enter the date in valid format [DD/MM/YYYY]");
                    System.exit(0);
                }

                for (int i = 0; i < 2; i++) {
                    if (Character.isDigit(date.toCharArray()[i])) {
                        continue;
                    } else {
                        System.out.println("Day is not in integer");
                        break;
                    }

                }
                for (int i = 3; i < 5; i++) {
                    if (Character.isDigit(date.toCharArray()[i])) {
                        continue;
                    } else {
                        System.out.println("Month is not in integer");
                        break;
                    }
                }
                for (int i = 6; i < 10; i++) {
                    if (Character.isDigit(date.toCharArray()[i])) {
                        continue;
                    } else {
                        System.out.println("Year is not in integer");
                        break;
                    }
                }
            } catch (Exception e) {
                System.out.println("Enter valid date");
            }

            String day = String.valueOf(date.substring(0, 2));
            String month = String.valueOf(date.substring(3, 5));
            String year = String.valueOf(date.substring(6, 10));


            int dd = Integer.parseInt(day);
            int mm = Integer.parseInt(month);
            int yyyy = Integer.parseInt(year);
            try {
                YearMonth noOfDays = YearMonth.of(yyyy, mm);
                int DaysInMonth = noOfDays.lengthOfMonth();

                for (int i = 0; i < 7; i++) {
                    if (dd < DaysInMonth) {
                        dd++;
                    } else if (dd == DaysInMonth && mm != 12) {
                        dd = 1;
                        mm++;
                    } else if (dd == DaysInMonth && mm == 12) {
                        dd = 1;
                        mm = 1;
                        yyyy++;
                    }
                    LocalDate nextDate = LocalDate.of(yyyy, mm, dd);

                    System.out.printf(" %d/%d/%d---->", dd, mm, yyyy);
                    System.out.println(nextDate.getDayOfWeek().toString());
                }
            }catch(Exception e){
                System.out.println("Please enter valid date");
            }
        } catch (Exception e) {
            System.out.println("Please enter the date in valid format [DD/MM/YYYY]");
        }
    }
}

