import java.sql.*;

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
        String sql = "insert into station (district, intro, chinese_name, english_name) values (?, ?, ?, ?)";
        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/project", "checker", "123456");
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, station.district);
            ps.setString(2, station.intro);
            ps.setString(3, station.chineseName);
            ps.setString(4, station.englishName);
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
        String sql = "update station set district = ?, intro = ?, chinese_name = ?, english_name = ? where chinese_name = ?";
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
            ps.setString(5, modifyStation);
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

//    static String insertStationLine(String lineName, String stationName, int position){
//        String sql = "insert into line_station (line_id,station_id) select l.id, s.id from line l,station s where l.line_name = ? and s.chinese_name = ?";
//
//    }

    static class Station{
        public Station(String district, String intro, String chineseName, String englishName) {
            this.district = district;
            this.intro = intro;
            this.chineseName = chineseName;
            this.englishName = englishName;
        }

        String district;
        String intro;
        String chineseName;
        String englishName;

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
