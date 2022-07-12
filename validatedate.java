package com.company;

import java.time.LocalDate;
import java.util.Scanner;

public class validatedate {
    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(System.in);
            System.out.print("Enter the date in format DD/MM/YYYY: ");
            String date = in.nextLine();

            try {
                if (date.length() != 10) {
                    System.out.println("Please enter the date in valid format DD/MM/YYYY:");
                    System.exit(0);
                } else if (date.charAt(2) != '/' && date.charAt(5) != '/') {
                    System.out.println("Please enter the date in valid format DD/MM/YYYY:");
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

            int yes = 0;
            try {
                if (((yyyy % 4 == 0) && ((yyyy % 400 == 0) || (yyyy % 100 != 0)))) {
                    yes = 1;
                }

                LocalDate somedate = LocalDate.of(yyyy, mm, dd);
                System.out.println(date + "----> " + somedate.getDayOfWeek().toString());

                if (yes == 1 && mm == 2) {                   //leapyear(feb)
                    for (int i = 0; i < 7; i++) {
                        if (dd < 29) {
                            dd++;
                        } else if (dd == 29 && mm != 12) {
                            dd = 1;
                            mm++;

                        } else if (dd == 29 && mm == 12) {
                            dd = 1;
                            mm = 1;
                            yyyy++;

                        }
                        LocalDate nextdate = LocalDate.of(yyyy, mm, dd);

                        System.out.printf("%d/%d/%d---->", dd, mm, yyyy);
                        System.out.println(nextdate.getDayOfWeek().toString());

                    }
                } else if (yes == 0 && mm == 2) {                  //non-leap year(feb)
                    for (int i = 0; i < 7; i++) {

                        if (dd < 28) {
                            dd++;
                        } else if (dd == 28 && mm != 12) {
                            dd = 1;
                            mm++;

                        } else if (dd == 28 && mm == 12) {
                            dd = 1;
                            mm = 1;
                            yyyy++;

                        }
                        LocalDate nextdate = LocalDate.of(yyyy, mm, dd);

                        System.out.printf("%d/%d/%d---->", dd, mm, yyyy);
                        System.out.println(nextdate.getDayOfWeek().toString());

                    }
                } else if (mm == 1 || mm == 3 || mm == 5 || mm == 7 || mm == 8 || mm == 10 || mm == 12) { //(31 days)
                    for (int i = 0; i < 7; i++) {

                        if (dd < 31) {
                            dd++;
                        } else if (dd == 31 && mm != 12) {
                            dd = 1;
                            mm++;

                        } else if (dd == 31 && mm == 12) {
                            dd = 1;
                            mm = 1;
                            yyyy++;

                        }
                        LocalDate nextdate = LocalDate.of(yyyy, mm, dd);
                        System.out.printf("%d/%d/%d---->", dd, mm, yyyy);
                        System.out.println(nextdate.getDayOfWeek().toString());

                    }
                } else {
                    for (int i = 0; i < 7; i++) {     //(30 days)

                        if (dd < 30) {
                            dd++;
                        } else if (dd == 30 && mm != 12) {
                            dd = 1;
                            mm++;

                        } else if (dd == 30 && mm == 12) {
                            dd = 1;
                            mm = 1;
                            yyyy++;

                        }
                        LocalDate nextdate = LocalDate.of(yyyy, mm, dd);
                        System.out.printf("%d/%d/%d---->", dd, mm, yyyy);
                        System.out.println(nextdate.getDayOfWeek().toString());
                    }

                }
            } catch (Exception e) {
                System.out.println("please enter valid date");
            }
        } catch (Exception e) {
            System.out.println("Please enter the date in valid format DD/MM/YYYY:");
        }
    }
}