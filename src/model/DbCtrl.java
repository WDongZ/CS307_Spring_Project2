package model;

import java.lang.reflect.Array;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class DbCtrl {
    private static Connection conn;

    static {
        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/project", "checker", "123456");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void disconnect() throws SQLException {
        conn.close();
    }
    static String insertStation(Station station){
        String sql = "insert into station (district, intro, chinese_name, english_name, status) values (?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, station.district);
            ps.setString(2, station.intro);
            ps.setString(3, station.chineseName);
            ps.setString(4, station.englishName);
            ps.setString(5, station.status);
            ps.executeUpdate();
            ps.close();
            return " \" " + station.chineseName + "\" inserted successfully";
        } catch (SQLException e) {
            e.printStackTrace();
            return e.getSQLState() + ": " + e.getMessage();
        }
    }

    static String deleteStation(String chineseName){
        String sqlPre = "select count(*) from station where chinese_name = ?";
        String sql = "delete from station where chinese_name = ?";
        try {
            PreparedStatement psPre = conn.prepareStatement(sqlPre);
            PreparedStatement ps = conn.prepareStatement(sql);
            psPre.setString(1, chineseName);
            ResultSet rs = psPre.executeQuery();
            rs.next();
            if (rs.getInt(1) == 0) {
                return " \" " + chineseName + "\" not found";
            }
            ps.setString(1, chineseName);
            ps.executeUpdate();
            ps.close();
            rs.close();
            return " \" " + chineseName + "\" deleted successfully";
        } catch (SQLException e) {
            e.printStackTrace();
            return e.getSQLState() + ": " + e.getMessage();
        }
    }

    static String modifyStation(String modifyStation, Station station){
        String sqlPre = "select count(*) from station where chinese_name = ?";
        String sql = "update station set district = ?, intro = ?, chinese_name = ?, english_name = ?, status = ? where chinese_name = ?";
        try {
            PreparedStatement psPre = conn.prepareStatement(sqlPre);
            PreparedStatement ps = conn.prepareStatement(sql);
            psPre.setString(1, modifyStation);
            ResultSet rs = psPre.executeQuery();
            rs.next();
            if (rs.getInt(1) == 0) {
                return " \" " + modifyStation + "\" not found";
            }
            ps.setString(1, station.district);
            ps.setString(2, station.intro);
            ps.setString(3, station.chineseName);
            ps.setString(4, station.englishName);
            ps.setString(5, station.status);
            ps.setString(6, modifyStation);
            ps.executeUpdate();
            ps.close();
            rs.close();
            return " \" " + station.chineseName + "\" modified successfully";
        } catch (SQLException e) {
            e.printStackTrace();
            return e.getSQLState() + ": " + e.getMessage();
        }
    }

    static String insertLine(Line line){
        String sql = "insert into line (line_name, start_time, end_time, intro, mileage, color, first_opening, url) values (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, line.lineName);
            ps.setTime(2, Time.valueOf(line.startTime));
            ps.setTime(3, Time.valueOf(line.endTime));
            ps.setString(4, line.intro);
            ps.setDouble(5, Double.parseDouble(line.mileage));
            ps.setString(6, line.color);
            ps.setDate(7, Date.valueOf(line.firstOpening));
            ps.setString(8, line.url);
            ps.executeUpdate();
            ps.close();
            return " \" " + line.lineName + "\" inserted successfully";
        } catch (SQLException e) {
            e.printStackTrace();
            return e.getSQLState() + ": " + e.getMessage();
        }
    }

    static String deleteLine(String lineName){
        String sqlPre = "select count(*) from line where line_name = ?";
        String sql = "delete from line where line_name = ?";
        try {
            PreparedStatement psPre = conn.prepareStatement(sqlPre);
            PreparedStatement ps = conn.prepareStatement(sql);
            psPre.setString(1, lineName);
            ps.setString(1, lineName);
            ResultSet rs = psPre.executeQuery();
            rs.next();
            if (rs.getInt(1) == 0) {
                return " \" " + lineName + "\" not found";
            }
            ps.executeUpdate();
            ps.close();
            rs.close();
            return " \" " + lineName + "\" deleted successfully";
        } catch (SQLException e) {
            e.printStackTrace();
            return e.getSQLState() + ": " + e.getMessage();
        }
    }

    static String modifyLine(String modifyLine, Line line){
        String sqlPre = "select count(*) from line where line_name = ?";
        String sql = "update line set line_name = ?, start_time = ?, end_time = ?, intro = ?, mileage = ?, color = ?, first_opening = ?, url = ? where line_name = ?";
        try {
            PreparedStatement psPre = conn.prepareStatement(sqlPre);
            PreparedStatement ps = conn.prepareStatement(sql);
            psPre.setString(1, modifyLine);
            ResultSet rs = psPre.executeQuery();
            rs.next();
            if (rs.getInt(1) == 0) {
                return " \" " + modifyLine + "\" not found";
            }
            ps.setString(1, line.lineName);
            ps.setTime(2, Time.valueOf(line.startTime));
            ps.setTime(3, Time.valueOf(line.endTime));
            ps.setString(4, line.intro);
            ps.setDouble(5, Double.parseDouble(line.mileage));
            ps.setString(6, line.color);
            ps.setDate(7, Date.valueOf(line.firstOpening));
            ps.setString(8, line.url);
            ps.setString(9, modifyLine);
            ps.executeUpdate();
            ps.close();
            rs.close();
            return " \" " + line.lineName + "\" modified successfully";
        } catch (SQLException e) {
            e.printStackTrace();
            return e.getSQLState() + ": " + e.getMessage();
        }
    }

    public static String insertStationLine(String lineName, String stationName, int position){
        String sql = "insert into line_station (line_id,station_id,position) select l.id, s.id, ? from line l,station s where l.line_name = ? and s.chinese_name = ?";
        String sqlPre = "select count(*) from line where line_name = ?";
        String sqlPre2 = "select count(*) from station where chinese_name = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            PreparedStatement psPre = conn.prepareStatement(sqlPre);
            PreparedStatement psPre2 = conn.prepareStatement(sqlPre2);
            psPre.setString(1, lineName);
            psPre2.setString(1, stationName);
            ResultSet rs = psPre.executeQuery();
            ResultSet rs2 = psPre2.executeQuery();
            rs.next();
            rs2.next();
            if (rs.getInt(1) == 0) {
                return " \" " + lineName + "\" not found";
            }
            if (rs2.getInt(1) == 0) {
                return " \" " + stationName + "\" not found";
            }
            ps.setInt(1, position);
            ps.setString(2, lineName);
            ps.setString(3,stationName);
            ps.executeUpdate();
            ps.close();
            rs.close();
            rs2.close();
            return " \" " + stationName + "\" inserted into \" " + lineName + "\" successfully";
        } catch (SQLException e) {
            e.printStackTrace();
            return e.getSQLState() + ": " + e.getMessage();
        }
    }

    public static String deleteStationLine(String lineName, String stationName){
        String sql = "delete from line_station where line_id = (select id from line where line_name = ?) and station_id = (select id from station where chinese_name = ?)";
        String sqlPre = "select count(*) from line where line_name = ?";
        String sqlPre2 = "select count(*) from station where chinese_name = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            PreparedStatement psPre = conn.prepareStatement(sqlPre);
            PreparedStatement psPre2 = conn.prepareStatement(sqlPre2);
            psPre.setString(1, lineName);
            psPre2.setString(1, stationName);
            ResultSet rs = psPre.executeQuery();
            ResultSet rs2 = psPre2.executeQuery();
            rs.next();
            rs2.next();
            if (rs.getInt(1) == 0) {
                return " \" " + lineName + "\" not found";
            }
            if (rs2.getInt(1) == 0) {
                return " \" " + stationName + "\" not found";
            }
            ps.setString(1, lineName);
            ps.setString(2,stationName);
            ps.executeUpdate();
            ps.close();
            rs.close();
            rs2.close();
            return " \" " + stationName + "\" deleted from \" " + lineName + "\" successfully";
        } catch (SQLException e) {
            e.printStackTrace();
            return e.getSQLState() + ": " + e.getMessage();
        }
    }

    public static String passengerRecord (String stationName, String startTime, String passengerID, String carriage){
        String sql = "insert into passenger_on (station_id, start_time, passenger_id, carriage) values " +
                "((select id from station where chinese_name = ?), ?, (select id from passenger where id_number = ?), ?)";
        String sqlPre = "select count(*) from station where chinese_name = ?";
        String sqlPre2 = "select count(*) from passenger where id_number = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            PreparedStatement psPre = conn.prepareStatement(sqlPre);
            PreparedStatement psPre2 = conn.prepareStatement(sqlPre2);
            psPre.setString(1, stationName);
            psPre2.setString(1, passengerID);
            ResultSet rs = psPre.executeQuery();
            ResultSet rs2 = psPre2.executeQuery();
            rs.next();
            rs2.next();
            if (rs.getInt(1) == 0) {
                return " \" " + stationName + "\" not found";
            }
            if (rs2.getInt(1) == 0) {
                return " \" " + passengerID + "\" not found";
            }
            ps.setString(1, stationName);
            ps.setTimestamp(2, Timestamp.valueOf(startTime));
            ps.setString(3, passengerID);
            ps.setString(4, carriage);
            ps.executeUpdate();
            ps.close();
            rs.close();
            rs2.close();
            return "Passenger " + passengerID + " recorded at " + stationName + " at " + startTime;
        } catch (SQLException e) {
            e.printStackTrace();
            return e.getSQLState() + ": " + e.getMessage();
        }
    }

    public static String cardRecord (String stationName, String startTime, String cardID, String carriage){
        String sql = "insert into card_on (station_id, start_time, card_id) values ((select id from station where chinese_name = ?), ?, (select id from card where code = ?), ?)";
        String sqlPre = "select count(*) from station where chinese_name = ?";
        String sqlPre2 = "select count(*) from card where code = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            PreparedStatement psPre = conn.prepareStatement(sqlPre);
            PreparedStatement psPre2 = conn.prepareStatement(sqlPre2);
            psPre.setString(1, stationName);
            psPre2.setString(1, cardID);
            ResultSet rs = psPre.executeQuery();
            ResultSet rs2 = psPre2.executeQuery();
            rs.next();
            rs2.next();
            if (rs.getInt(1) == 0) {
                return " \" " + stationName + "\" not found";
            }
            if (rs2.getInt(1) == 0) {
                return " \" " + cardID + "\" not found";
            }
            ps.setString(1, stationName);
            ps.setTimestamp(2, Timestamp.valueOf(startTime));
            ps.setString(3, cardID);
            ps.setString(4, carriage);
            ps.executeUpdate();
            ps.close();
            rs.close();
            rs2.close();
            return "Card " + cardID + " recorded at " + stationName + " at " + startTime;
        } catch (SQLException e) {
            e.printStackTrace();
            return e.getSQLState() + ": " + e.getMessage();
        }
    }

    public static String outCardRecord(String stationName, String time, String cardID){
        String getStartStationId = "select station_id from card_on where card_id = (select id from card where code = ?)";
        String getEndStationId = "select id from station where chinese_name = ?";
        String getStartTime = "select start_time from card_on where card_id = (select id from card where code = ?)";
        String getPrice = "select price from price where start_station = ? and end_station = ?";
        String getCarriage = "select carriage from card_on where card_id = (select id from card where code = ?)";
        String sql = "insert into ride (start_station_id, end_station_id,price, start_time, end_time, carriage) values (?, ?, ?, ?, ?, ?) returning id";
        String sql2 = "insert into card_ride (card_id, ride_id) values ((select id from card where code = ?), ?)";
        String sql3 = "update card set money = money - ? where code = ?";
        String sqlPre = "select count(*) from station where chinese_name = ?";
        String sqlPre2 = "select count(*) from card where code = ?";
        try {
            PreparedStatement psGetStartStationId = conn.prepareStatement(getStartStationId);
            PreparedStatement psGetEndStationId = conn.prepareStatement(getEndStationId);
            PreparedStatement psGetStartTime = conn.prepareStatement(getStartTime);
            PreparedStatement psGetPrice = conn.prepareStatement(getPrice);
            PreparedStatement psGetCarriage = conn.prepareStatement(getCarriage);
            PreparedStatement ps = conn.prepareStatement(sql);
            PreparedStatement ps2 = conn.prepareStatement(sql2);
            PreparedStatement ps3 = conn.prepareStatement(sql3);
            PreparedStatement psPre = conn.prepareStatement(sqlPre);
            PreparedStatement psPre2 = conn.prepareStatement(sqlPre2);
            psPre.setString(1, stationName);
            psPre2.setString(1, cardID);
            ResultSet rs = psPre.executeQuery();
            ResultSet rs2 = psPre2.executeQuery();
            rs.next();
            rs2.next();
            if (rs.getInt(1) == 0) {
                return " \" " + stationName + "\" not found";
            }
            if (rs2.getInt(1) == 0) {
                return " \" " + cardID + "\" not found";
            }
            psGetStartStationId.setString(1, cardID);
            ResultSet rsGetStartStationId = psGetStartStationId.executeQuery();
            rsGetStartStationId.next();
            int startStationId = rsGetStartStationId.getInt(1);
            psGetEndStationId.setString(1, stationName);
            ResultSet rsGetEndStationId = psGetEndStationId.executeQuery();
            rsGetEndStationId.next();
            int endStationId = rsGetEndStationId.getInt(1);
            psGetStartTime.setString(1, cardID);
            ResultSet rsGetStartTime = psGetStartTime.executeQuery();
            rsGetStartTime.next();
            Timestamp startTime = rsGetStartTime.getTimestamp(1);
            psGetPrice.setInt(1, startStationId);
            psGetPrice.setInt(2, endStationId);
            ResultSet rsGetPrice = psGetPrice.executeQuery();
            rsGetPrice.next();
            double price = rsGetPrice.getDouble(1);
            psGetCarriage.setString(1, cardID);
            ResultSet rsGetCarriage = psGetCarriage.executeQuery();
            rsGetCarriage.next();
            String carriage = rsGetCarriage.getString(1);
            if (carriage.equals("business")) {
                price *= 2;
            }
            ps.setInt(1, startStationId);
            ps.setInt(2, endStationId);
            ps.setDouble(3, price);
            ps.setTimestamp(4, startTime);
            ps.setTimestamp(5, Timestamp.valueOf(time));
            ps.setString(6, carriage);
            ResultSet rideId = ps.executeQuery();
            rideId.next();
            int id = rideId.getInt(1);
            ps2.setString(1, cardID);
            ps2.setInt(2, id);
            ps2.executeUpdate();
            ps3.setDouble(1, price);
            ps3.setString(2, cardID);
            ps3.executeUpdate();
            psGetStartStationId.close();
            psGetEndStationId.close();
            psGetStartTime.close();
            psGetPrice.close();
            psGetCarriage.close();
            ps.close();
            ps2.close();
            ps3.close();
            rs.close();
            rs2.close();
            rideId.close();
            rsGetStartStationId.close();
            rsGetEndStationId.close();
            rsGetStartTime.close();
            rsGetPrice.close();
            rsGetCarriage.close();
            return "Card " + cardID + " recorded out at " + stationName + " at " + time + " with price " + price + " and carriage " + carriage;
        } catch (SQLException e) {
            e.printStackTrace();
            return e.getSQLState() + ": " + e.getMessage();
        }
    }

    public static String outPassengerRecord(String stationName, String time, String passengerID){
        String getStartStationId = "select station_id from passenger_on where passenger_id = (select id from passenger where id_number = ?)";
        String getEndStationId = "select id from station where chinese_name = ?";
        String getStartTime = "select start_time from passenger_on where passenger_id = (select id from passenger where id_number = ?)";
        String getPrice = "select price from price where start_station = ? and end_station = ?";
        String getCarriage = "select carriage from passenger_on where passenger_id = (select id from passenger where id_number = ?)";
        String sql = "insert into ride (start_station_id, end_station_id,price, start_time, end_time, carriage) values (?, ?, ?, ?, ?, ?) returning id";
        String sql2 = "insert into passenger_ride (passenger_id, ride_id) values ((select id from passenger where id_number = ?), ?)";
        String sqlPre = "select count(*) from station where chinese_name = ?";
        String sqlPre2 = "select count(*) from passenger where id_number = ?";
        String sqlPre3 = "select count(*) from passenger_on where passenger_id = (select id from passenger where id_number = ?)";
        try {
            PreparedStatement psGetStartStationId = conn.prepareStatement(getStartStationId);
            PreparedStatement psGetEndStationId = conn.prepareStatement(getEndStationId);
            PreparedStatement psGetStartTime = conn.prepareStatement(getStartTime);
            PreparedStatement psGetPrice = conn.prepareStatement(getPrice);
            PreparedStatement psGetCarriage = conn.prepareStatement(getCarriage);
            PreparedStatement ps = conn.prepareStatement(sql);
            PreparedStatement ps2 = conn.prepareStatement(sql2);
            PreparedStatement psPre = conn.prepareStatement(sqlPre);
            PreparedStatement psPre2 = conn.prepareStatement(sqlPre2);
            PreparedStatement psPre3 = conn.prepareStatement(sqlPre3);
            psPre.setString(1, stationName);
            psPre2.setString(1, passengerID);
            psPre3.setString(1, passengerID);
            ResultSet rs = psPre.executeQuery();
            ResultSet rs2 = psPre2.executeQuery();
            ResultSet rs3 = psPre3.executeQuery();
            rs.next();
            rs2.next();
            rs3.next();
            if (rs.getInt(1) == 0) {
                return " \" " + stationName + "\" not found";
            }
            if (rs2.getInt(1) == 0) {
                return " \" " + passengerID + "\" not found";
            }
            if (rs3.getInt(1) == 0) {
                return " \" " + passengerID + "\" not recorded";
            }
            psGetStartStationId.setString(1, passengerID);
            ResultSet rsGetStartStationId = psGetStartStationId.executeQuery();
            rsGetStartStationId.next();
            int startStationId = rsGetStartStationId.getInt(1);
            psGetEndStationId.setString(1, stationName);
            ResultSet rsGetEndStationId = psGetEndStationId.executeQuery();
            rsGetEndStationId.next();
            int endStationId = rsGetEndStationId.getInt(1);
            psGetStartTime.setString(1, passengerID);
            ResultSet rsGetStartTime = psGetStartTime.executeQuery();
            rsGetStartTime.next();
            Timestamp startTime = rsGetStartTime.getTimestamp(1);
            psGetPrice.setInt(1, startStationId);
            psGetPrice.setInt(2, endStationId);
            ResultSet rsGetPrice = psGetPrice.executeQuery();
            rsGetPrice.next();
            double price = rsGetPrice.getDouble(1);
            psGetCarriage.setString(1, passengerID);
            ResultSet rsGetCarriage = psGetCarriage.executeQuery();
            rsGetCarriage.next();
            String carriage = rsGetCarriage.getString(1);
            if (carriage.equals("business")) {
                price *= 2;
            }
            ps.setInt(1, startStationId);
            ps.setInt(2, endStationId);
            ps.setDouble(3, price);
            ps.setTimestamp(4, startTime);
            ps.setTimestamp(5, Timestamp.valueOf(time));
            ps.setString(6, carriage);
            ResultSet rideId = ps.executeQuery();
            rideId.next();
            int id = rideId.getInt(1);
            ps2.setString(1, passengerID);
            ps2.setInt(2, id);
            ps2.executeUpdate();
            psGetStartStationId.close();
            psGetEndStationId.close();
            psGetStartTime.close();
            psGetPrice.close();
            ps.close();
            ps2.close();
            rs.close();
            rs2.close();
            rs3.close();
            rideId.close();
            rsGetStartStationId.close();
            rsGetEndStationId.close();
            rsGetStartTime.close();
            rsGetPrice.close();
            return "Card " + passengerID + " recorded out at " + stationName + " at " + time + " with price " + price + " and carriage " + carriage;
        } catch (SQLException e) {
            e.printStackTrace();
            return e.getSQLState() + ": " + e.getMessage();
        }
    }

    public static ArrayList<String> queryPassenger () {
        String sql = "select name, id_number, phone_number, gender, district from passenger where id in (select passenger_id from passenger_on)";
        ArrayList<String> result = new ArrayList<>();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                result.add(rs.getString(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + "  " + rs.getString(4) + "  " + rs.getString(5));
            }
            rs.close();
            st.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            result.add(e.getSQLState() + ": " + e.getMessage());
            return result;
        }
    }

    public static String queryPath (String startStation, String endStation) {
        String sql = "select path from path where start_station_id = (select id from station where chinese_name = ?) and end_station_id = (select id from station where chinese_name = ?)";
        String sqlPre = "select count(*) from station where chinese_name = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            PreparedStatement psPre = conn.prepareStatement(sqlPre);
            PreparedStatement psPre2 = conn.prepareStatement(sqlPre);
            psPre.setString(1, startStation);
            ResultSet rs1 = psPre.executeQuery();
            psPre2.setString(1, endStation);
            ResultSet rs2 = psPre2.executeQuery();
            rs1.next();
            rs2.next();
            if (rs1.getInt(1) == 0) {
                return " \" " + startStation + "\"  not found";
            }
            if (rs2.getInt(1) == 0) {
                return " \" " + endStation + "\"  not found";
            }
            ps.setString(1, startStation);
            ps.setString(2, endStation);
            ResultSet resultSet = ps.executeQuery();
            resultSet.next();
            String result = resultSet.getString(1);
            resultSet.close();
            ps.close();
            rs1.close();
            rs2.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return e.getSQLState() + ": " + e.getMessage();
        }
    }

    public static ArrayList<String> queryBus (String station) {
        String sql = "select bus_name, bus_stop_name from bus_v where chinese_name = '" + station + "'" + "order by bus_stop_name";
        String sqlPre = "select count(*) from station where chinese_name = ?";
        ArrayList<String> result = new ArrayList<>();
        try {
            PreparedStatement psPre = conn.prepareStatement(sqlPre);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            psPre.setString(1, station);
            ResultSet rs1 = psPre.executeQuery();
            rs1.next();
            if (rs1.getInt(1) == 0) {
                result.add(" \" " + station + "\"  not found");
                return result;
            }
            while (rs.next()) {
                result.add(rs.getString(1) + "  " + rs.getString(2));
            }
            rs.close();
            st.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            result.add(e.getSQLState() + ": " + e.getMessage());
            return result;
        }
    }
    public static ArrayList<String> queryCard () {
        String sql = "select code, money, create_time from card where id in (select card_id from card_on)";
        ArrayList<String> result = new ArrayList<>();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                result.add(rs.getString(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
            }
            rs.close();
            st.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            result.add(e.getSQLState() + ": " + e.getMessage());
            return result;
        }
    }

    public static ArrayList<String> queryPassengerRide (String name, String phoneNumber, String gender, String district, String idNumber, String startTime, String endTime, String carriage, String startStation, String endStation) {
        StringBuilder sqlBd = new StringBuilder("select * from pr_v where true");
        String getStationId = "select id from station where chinese_name = ?";
        String getStation = "select chinese_name from station where id = ?";
        if (!name.equals("null")) sqlBd.append(" and name = '").append(name).append("'" );
        if (!phoneNumber.equals("null")) sqlBd.append(" and phone_number = '").append(phoneNumber).append("'");
        if (!gender.equals("null")) sqlBd.append(" and gender = '").append(gender).append("'");
        if (!district.equals("null")) sqlBd.append(" and district = '").append(district).append("'");
        if (!idNumber.equals("null")) sqlBd.append(" and id_number = '").append(idNumber).append("'");
        if (!startTime.equals("null")) sqlBd.append(" and start_time >= '").append(startTime).append("'");
        if (!endTime.equals("null")) sqlBd.append(" and end_time <= '").append(endTime).append("'");
        if (!carriage.equals("null")) sqlBd.append(" and carriage = '").append(carriage).append("'");
        ArrayList<String> result = new ArrayList<>();
        try {
            PreparedStatement psGetStartStationId = conn.prepareStatement(getStationId);
            PreparedStatement psGetEndStationId = conn.prepareStatement(getStationId);
            psGetStartStationId.setString(1, startStation);
            psGetEndStationId.setString(1, endStation);
            ResultSet rsGetStartStationId = psGetStartStationId.executeQuery();
            ResultSet rsGetEndStationId = psGetEndStationId.executeQuery();
            rsGetStartStationId.next();
            rsGetEndStationId.next();
            if (!startStation.equals("null")) {
                int startStationId = rsGetStartStationId.getInt(1);
                sqlBd.append(" and start_station_id = ").append(startStationId);
            }
            if (!endStation.equals("null")){
                int endStationId = rsGetEndStationId.getInt(1);
                sqlBd.append(" and end_station_id = ").append(endStationId);
            }
            String sql = sqlBd.toString();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PreparedStatement psGetStartStation = conn.prepareStatement(getStation);
                PreparedStatement psGetEndStation = conn.prepareStatement(getStation);
                psGetStartStation.setInt(1, rs.getInt(6));
                psGetEndStation.setInt(1, rs.getInt(7));
                ResultSet rsGetStartStation = psGetStartStation.executeQuery();
                ResultSet rsGetEndStation = psGetEndStation.executeQuery();
                rsGetStartStation.next();
                rsGetEndStation.next();
                result.add(rs.getString(1) + "  " + rs.getString(2) + "  " + rs.getString(3)
                        + "  " + rs.getString(4) + "  " + rs.getString(5) + "  " + rsGetStartStation.getString(1)
                        + "  " + rsGetEndStation.getString(1) + "  " + rs.getString(8) + "  " + rs.getString(9)
                        + "  " + rs.getString(10)+ "  " + rs.getString(11));
            }
            rs.close();
            ps.close();
            rsGetStartStationId.close();
            rsGetEndStationId.close();
            psGetStartStationId.close();
            psGetEndStationId.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            result.add(e.getSQLState() + ": " + e.getMessage());
            return result;
        }
    }

    public static String queryForwardStation(String lineName, String stationName, int count) {
        String sql = "select s.chinese_name from line_station ls, station s where ls.line_id = " +
                "(select id from line where line_name = ?) and ls.station_id = s.id and ls.position = " +
                "(select position from line_station where line_id = (select id from line where line_name = ?) " +
                "and station_id = (select id from station where chinese_name = ?)) + ?";
        String preSql = "select count(*) from line where line_name = ?";
        String preSql2 = "select count(*) from station where chinese_name = ?";
        String preSql3 = "select count(*) from line_station where line_id = (select id from line where line_name = ?) " +
                "and station_id = (select id from station where chinese_name = ?)";
        String preSql4 = "select count(*) from line_station ls, station s where ls.line_id = " +
                "(select id from line where line_name = ?) and ls.station_id = s.id and ls.position = " +
                "(select position from line_station where line_id = (select id from line where line_name = ?) " +
                "and station_id = (select id from station where chinese_name = ?)) + ?";
        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/project", "checker", "123456");
            PreparedStatement ps = conn.prepareStatement(sql);
            PreparedStatement psPre = conn.prepareStatement(preSql);
            PreparedStatement psPre2 = conn.prepareStatement(preSql2);
            PreparedStatement psPre3 = conn.prepareStatement(preSql3);
            PreparedStatement psPre4 = conn.prepareStatement(preSql4);
            psPre.setString(1, lineName);
            psPre2.setString(1, stationName);
            psPre3.setString(1, lineName);
            psPre3.setString(2, stationName);
            psPre4.setString(1, lineName);
            psPre4.setString(2, lineName);
            psPre4.setString(3, stationName);
            psPre4.setInt(4, count);
            ResultSet rs = psPre.executeQuery();
            ResultSet rs2 = psPre2.executeQuery();
            ResultSet rs3 = psPre3.executeQuery();
            ResultSet rs4 = psPre4.executeQuery();
            rs.next();
            rs2.next();
            rs3.next();
            rs4.next();
            if (rs.getInt(1) == 0) {
                return " \" " + lineName + "\" not found";
            }
            if (rs2.getInt(1) == 0) {
                return " \" " + stationName + "\" not found";
            }
            if (rs3.getInt(1) == 0) {
                return " \" " + stationName + "\" not found in \" " + lineName + "\"";
            }
            if (rs4.getInt(1) == 0) {
                return "Query out of range";
            }
            ps.setString(1, lineName);
            ps.setString(2, lineName);
            ps.setString(3, stationName);
            ps.setInt(4, count);
            ResultSet resultSet = ps.executeQuery();
            StringBuilder result = new StringBuilder();
            while (resultSet.next()) {
                result.append(resultSet.getString(1)).append(" ");
            }
            resultSet.close();
            ps.close();
            rs.close();
            rs2.close();
            rs3.close();
            rs4.close();
            return result.toString();
        } catch (SQLException e) {
            e.printStackTrace();
            return e.getSQLState() + ": " + e.getMessage();
        }

    }
    public static String query(String sql){
        StringBuilder result = new StringBuilder();
        try (Statement statement = conn.createStatement()) {
            boolean isResultSet = statement.execute(sql);
            if (isResultSet) {
                try (ResultSet resultSet = statement.getResultSet()) {
                    ResultSetMetaData metaData = resultSet.getMetaData();
                    int columnCount = metaData.getColumnCount();
                    for (int i = 1; i <= columnCount; i++) {
                        result.append(metaData.getColumnName(i)).append("\t");
                    }
                    result.append("\n");
                    while (resultSet.next()) {
                        for (int i = 1; i <= columnCount; i++) {
                            result.append(resultSet.getString(i)).append("\t");
                        }
                        result.append("\n");
                    }
                    return result.toString();
                }
            } else {
                int updateCount = statement.getUpdateCount();
                return "更新计数: " + updateCount;
            }
        } catch (SQLException ex) {
            return "执行失败: " + ex.getMessage();
        }
    }

    public static ArrayList<String> queryCardRide(String code, String startStationC, String endStationC, String startTimeC, String endTimeC, String carriageC) {
        StringBuilder sqlBd = new StringBuilder("select * from cr_v where true");
        String getStationId = "select id from station where chinese_name = ?";
        String getStation = "select chinese_name from station where id = ?";
        if (!code.equals("null")) sqlBd.append(" and code = '").append(code).append("'" );
        if (!startStationC.equals("null")) sqlBd.append(" and start_station_id = (select id from station where chinese_name = '").append(startStationC).append("')");
        if (!endStationC.equals("null")) sqlBd.append(" and end_station_id = (select id from station where chinese_name = '").append(endStationC).append("')");
        if (!startTimeC.equals("null")) sqlBd.append(" and start_time >= '").append(startTimeC).append("'");
        if (!endTimeC.equals("null")) sqlBd.append(" and end_time <= '").append(endTimeC).append("'");
        if (!carriageC.equals("null")) sqlBd.append(" and carriage = '").append(carriageC).append("'");
        ArrayList<String> result = new ArrayList<>();
        try {
            PreparedStatement psGetStartStationId = conn.prepareStatement(getStationId);
            PreparedStatement psGetEndStationId = conn.prepareStatement(getStationId);
            psGetStartStationId.setString(1, startStationC);
            psGetEndStationId.setString(1, endStationC);
            ResultSet rsGetStartStationId = psGetStartStationId.executeQuery();
            ResultSet rsGetEndStationId = psGetEndStationId.executeQuery();
            rsGetStartStationId.next();
            rsGetEndStationId.next();
            String sql = sqlBd.toString();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PreparedStatement psGetStartStation = conn.prepareStatement(getStation);
                PreparedStatement psGetEndStation = conn.prepareStatement(getStation);
                psGetStartStation.setInt(1, rs.getInt(2));
                psGetEndStation.setInt(1, rs.getInt(3));
                ResultSet rsGetStartStation = psGetStartStation.executeQuery();
                ResultSet rsGetEndStation = psGetEndStation.executeQuery();
                rsGetStartStation.next();
                rsGetEndStation.next();
                result.add(rs.getString(1) + "  " + rsGetStartStation.getString(1)
                        + "  " + rsGetEndStation.getString(1) + "  " + rs.getString(4) + "  "
                        + rs.getString(5) + "  " + "  " + rs.getString(6) + "  " + rs.getString(7));
            }
            rs.close();
            ps.close();
            rsGetStartStationId.close();
            rsGetEndStationId.close();
            psGetStartStationId.close();
            psGetEndStationId.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            result.add(e.getSQLState() + ": " + e.getMessage());
            return result;
        }
    }

    static class Station{
        public Station(String district, String intro, String chineseName, String englishName, String status) {
            this.district = district;
            this.intro = intro;
            this.chineseName = chineseName;
            this.englishName = englishName;
            this.status = status;
        }

        String district;
        String intro;
        String chineseName;
        String englishName;
        String status;

    }

    static class Line{

        String lineName;
        String startTime;
        String endTime;
        String intro;
        String mileage;
        String color;
        String firstOpening;
        String url;

        public Line(String lineName, String startTime, String endTime, String intro, String mileage, String color, String firstOpening, String url) {
            this.lineName = lineName;
            this.startTime = startTime;
            this.endTime = endTime;
            this.intro = intro;
            this.mileage = mileage;
            this.color = color;
            this.firstOpening = firstOpening;
            this.url = url;
        }
    }

}
