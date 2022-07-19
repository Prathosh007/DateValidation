package dateOperations;

import java.io.*;
import java.net.HttpURLConnection;
import java.sql.*;
import java.util.*;
import java.time.LocalDate;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.net.URL;
import java.net.URLConnection;

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

    public void printResult(LinkedHashMap<String, String> result, List<Double> tempArray) {
        if (tempArray != null) {
            int i = 0;
            for (Map.Entry<String, String> j : result.entrySet()) {
                String s = j.getKey() + " which is a " + j.getValue() + " with a temperature of " + tempArray.get(i++) + "°C";
                System.out.println(s);
            }
        } else {
            for (Map.Entry<String, String> j : result.entrySet()) {
                String s = j.getKey() + " which is a " + j.getValue();
                System.out.println(s);

            }
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

    public void updateToFile(String date, LinkedHashMap<String, String> result) {
        try {
            FileWriter file = new FileWriter("date.txt", true);
            BufferedWriter b = new BufferedWriter(file);

            b.append("INPUT:" + date + "\n");
            b.append("OUTPUT:");

            for (Map.Entry<String, String> j : result.entrySet()) {
                String s1 = j.getKey() + " which is a " + j.getValue();
                b.append("\n" + s1);
            }
            b.newLine();
            b.close();
            file.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateToDatabase(String date, LinkedHashMap<String, String> result) throws Exception {
        try {
            Connection con = getConnection();
            PreparedStatement update = con.prepareStatement("INSERT INTO dateoperation (Input,Output) VALUES('" + date + "','" + result + "')");
            update.executeUpdate();
            System.out.println("Data updated to database!!!");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void displayDatabase() throws Exception {
        try {
            Connection con = getConnection();
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from dateoperation");

            while (resultSet.next()) {
                System.out.println("\nINPUT " + resultSet.getInt("ID") + ": " + resultSet.getString("Input"));
                System.out.println("\nOUTPUT " + resultSet.getInt("ID") + ": " + resultSet.getString("output"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static Connection getConnection() {
        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dateoperations", "root", "Prathosh@3");
            Class.forName(driver);
            System.out.println("connected to JDBC!!!");
            return connection;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;

    }

    public List<Double> temp(LinkedHashMap<String, String> result) throws Exception {
        LinkedHashMap<String, String> tempList = new LinkedHashMap<String, String>();
        List<Double> tempArray = new ArrayList<>();
        JSONParser jsonParser = new JSONParser();
        try {
            String yourKey = "UZ6V5RXVQCZDXERMKVY5HSYUC";
            URL url = new URL("https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/chennai?unitGroup=metric&key=UZ6V5RXVQCZDXERMKVY5HSYUC&contentType=json"); // 79843 = US postal Zip Code for Marfa, Texas.
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            int responseCode = conn.getResponseCode();
            System.out.println("Response code: " +responseCode );
            InputStream is = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

//            String inputLine;
//            StringBuffer response = new StringBuffer();
//            while((inputLine = reader.readLine())){
//                response.append(inputLine);
//            }

            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
            JSONArray days = (JSONArray) jsonObject.get("days");

            for (Object o : days) {
                JSONObject forecast = (JSONObject) o;

                String dateTime = (String) forecast.get("datetime");
                int tempDay = Integer.parseInt(dateTime.substring(8, 10));
                int tempMonth = Integer.parseInt(dateTime.substring(5, 7));
                int tempYear = Integer.parseInt(dateTime.substring(0, 4));

                String date = tempDay + "/" + tempMonth + "/" + tempYear;

                LocalDate tempDate = LocalDate.of(tempYear, tempMonth, tempDay);

                String day = " " + tempDate.getDayOfWeek();
                tempList.put(date, day);


                Double temp = (Double) forecast.get("tempmax");
                tempArray.add(temp);

            }
            boolean s1 = (result.containsKey("19/7/2022") || result.containsKey("20/7/2022") || result.containsKey(" 21/7/2022") || result.containsKey("22/7/2022") || result.containsKey("23/7/2022") || result.containsKey("24/7/2022") || result.containsKey("25/7/2022") || result.containsKey("26/7/2022") || result.containsKey("27/7/2022"));
            if (s1 == true) {
                return tempArray;
            }
            else if (s1 == false) {
                tempArray=null;
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return tempArray;
    }

        public static void main (String[] args){
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
                List<Double> tempArray = validate.temp(result);
                validate.printResult(result,tempArray );
                validate.updateToFile(date, result);
                updateToDatabase(date, result);
//                displayDatabase();
            } catch (Exception e) {
                throw new IllegalArgumentException("Date is not valid");
            }
        }
    }





