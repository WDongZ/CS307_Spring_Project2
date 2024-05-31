import java.sql.*;
import java.sql.Date;
import java.util.*;

public class DbCtrl {
    public static void connect(String user, String password) throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/project";
        Connection conn = DriverManager.getConnection(url, user, password);
        Statement st = conn.createStatement();
        for (int i = 1; i < 373; i++) {
            st.executeQuery("update line_station set position = (select rank() over (partition by line_id order by id) from line_station where id = ?) where id = ?;");
        }
        st.close();
        conn.close();
    }
    static String insertStation(Station station){
        String sql = "insert into station (district, intro, chinese_name, english_name, status) values (?, ?, ?, ?, ?)";
        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/project", "checker", "123456");
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, station.district);
            ps.setString(2, station.intro);
            ps.setString(3, station.chineseName);
            ps.setString(4, station.englishName);
            ps.setString(5, station.status);
            ps.executeUpdate();
            ps.close();
            conn.close();
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
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/project", "checker", "123456");
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
            conn.close();
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
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/project", "checker", "123456");
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
            conn.close();
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
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/project", "checker", "123456");
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
            conn.close();
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
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/project", "checker", "123456");
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
            conn.close();
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
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/project", "checker", "123456");
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
            conn.close();
            rs.close();
            return " \" " + line.lineName + "\" modified successfully";
        } catch (SQLException e) {
            e.printStackTrace();
            return e.getSQLState() + ": " + e.getMessage();
        }
    }

    static String insertStationLine(String lineName, String stationName, int position){
        String sql = "insert into line_station (line_id,station_id,position) select l.id, s.id, ? from line l,station s where l.line_name = ? and s.chinese_name = ?";
        String sqlPre = "select count(*) from line where line_name = ?";
        String sqlPre2 = "select count(*) from station where chinese_name = ?";
        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/project", "checker", "123456");
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
            conn.close();
            rs.close();
            rs2.close();
            return " \" " + stationName + "\" inserted into \" " + lineName + "\" successfully";
        } catch (SQLException e) {
            e.printStackTrace();
            return e.getSQLState() + ": " + e.getMessage();
        }
    }

    static String deleteStationLine(String lineName, String stationName){
        String sql = "delete from line_station where line_id = (select id from line where line_name = ?) and station_id = (select id from station where chinese_name = ?)";
        String sqlPre = "select count(*) from line where line_name = ?";
        String sqlPre2 = "select count(*) from station where chinese_name = ?";
        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/project", "checker", "123456");
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
            conn.close();
            rs.close();
            rs2.close();
            return " \" " + stationName + "\" deleted from \" " + lineName + "\" successfully";
        } catch (SQLException e) {
            e.printStackTrace();
            return e.getSQLState() + ": " + e.getMessage();
        }
    }

    public static String passengerRecord (String stationName, String startTime, String passengerID){
        String sql = "insert into passenger_on (station_id, start_time, passenger_id) values " +
                "((select id from station where chinese_name = ?), ?, (select id from passenger where id_number = ?))";
        String sqlPre = "select count(*) from station where chinese_name = ?";
        String sqlPre2 = "select count(*) from passenger where id_number = ?";
        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/project", "checker", "123456");
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
            ps.executeUpdate();
            ps.close();
            conn.close();
            rs.close();
            rs2.close();
            return "Passenger " + passengerID + " recorded at " + stationName + " at " + startTime;
        } catch (SQLException e) {
            e.printStackTrace();
            return e.getSQLState() + ": " + e.getMessage();
        }
    }

    public static String cardRecord (String stationName, String startTime, String cardID){
        String sql = "insert into card_on (station_id, start_time, card_id) values ((select id from station where chinese_name = ?), ?, (select id from card where code = ?))";
        String sqlPre = "select count(*) from station where chinese_name = ?";
        String sqlPre2 = "select count(*) from card where code = ?";
        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/project", "checker", "123456");
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
            ps.executeUpdate();
            ps.close();
            conn.close();
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
        String sql = "insert into ride (start_station_id, end_station_id,price, start_time, end_time) values (?, ?, ?, ?, ?) returning id";
        String sql2 = "insert into card_ride (card_id, ride_id) values ((select id from card where code = ?), ?)";
        String sqlPre = "select count(*) from station where chinese_name = ?";
        String sqlPre2 = "select count(*) from card where code = ?";
        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/project", "checker", "123456");
            PreparedStatement psGetStartStationId = conn.prepareStatement(getStartStationId);
            PreparedStatement psGetEndStationId = conn.prepareStatement(getEndStationId);
            PreparedStatement psGetStartTime = conn.prepareStatement(getStartTime);
            PreparedStatement psGetPrice = conn.prepareStatement(getPrice);
            PreparedStatement ps = conn.prepareStatement(sql);
            PreparedStatement ps2 = conn.prepareStatement(sql2);
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
            ps.setInt(1, startStationId);
            ps.setInt(2, endStationId);
            ps.setDouble(3, price);
            ps.setTimestamp(4, startTime);
            ps.setTimestamp(5, Timestamp.valueOf(time));
            ResultSet rideId = ps.executeQuery();
            rideId.next();
            int id = rideId.getInt(1);
            ps2.setString(1, cardID);
            ps2.setInt(2, id);
            ps2.executeUpdate();
            psGetStartStationId.close();
            psGetEndStationId.close();
            psGetStartTime.close();
            psGetPrice.close();
            ps.close();
            ps2.close();
            conn.close();
            rs.close();
            rs2.close();
            rideId.close();
            rsGetStartStationId.close();
            rsGetEndStationId.close();
            rsGetStartTime.close();
            rsGetPrice.close();
            return "Card " + cardID + " recorded out at " + stationName + " at " + time + " with price " + price;
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
        String sql = "insert into ride (start_station_id, end_station_id,price, start_time, end_time) values (?, ?, ?, ?, ?) returning id";
        String sql2 = "insert into passenger_ride (passenger_id, ride_id) values ((select id from passenger where id_number = ?), ?)";
        String sqlPre = "select count(*) from station where chinese_name = ?";
        String sqlPre2 = "select count(*) from passenger where id_number = ?";
        String sqlPre3 = "select count(*) from passenger_on where passenger_id = (select id from passenger where id_number = ?)";
        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/project", "checker", "123456");
            PreparedStatement psGetStartStationId = conn.prepareStatement(getStartStationId);
            PreparedStatement psGetEndStationId = conn.prepareStatement(getEndStationId);
            PreparedStatement psGetStartTime = conn.prepareStatement(getStartTime);
            PreparedStatement psGetPrice = conn.prepareStatement(getPrice);
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
            ps.setInt(1, startStationId);
            ps.setInt(2, endStationId);
            ps.setDouble(3, price);
            ps.setTimestamp(4, startTime);
            ps.setTimestamp(5, Timestamp.valueOf(time));
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
            conn.close();
            rs.close();
            rs2.close();
            rs3.close();
            rideId.close();
            rsGetStartStationId.close();
            rsGetEndStationId.close();
            rsGetStartTime.close();
            rsGetPrice.close();
            return "Card " + passengerID + " recorded out at " + stationName + " at " + time + " with price " + price;
        } catch (SQLException e) {
            e.printStackTrace();
            return e.getSQLState() + ": " + e.getMessage();
        }
    }

    public static ArrayList<String> queryPassenger () {
        String sql = "select name, id_number, phone_number, gender, district from passenger where id in (select passenger_id from passenger_on)";
        ArrayList<String> result = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/project", "checker", "123456");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                result.add(rs.getString(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + "  " + rs.getString(4) + "  " + rs.getString(5));
            }
            rs.close();
            st.close();
            conn.close();
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
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/project", "checker", "123456");
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
            conn.close();
            rs1.close();
            rs2.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return e.getSQLState() + ": " + e.getMessage();
        }
    }

    public static ArrayList<String> queryCard () {
        String sql = "select code, money, create_time from card where id in (select card_id from card_on)";
        ArrayList<String> result = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/project", "checker", "123456");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                result.add(rs.getString(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
            }
            rs.close();
            st.close();
            conn.close();
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
            conn.close();
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
