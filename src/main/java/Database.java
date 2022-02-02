//import java.sql.*;
//
//class App {
//    public static void main(String[] args) {
//        App app = new App();
//        app.update(1, 2000.0);
//    }
//
//    int accNum;
//    double amt;
//
//    public void update(int acc, double amt1) {
//        double newAmt = 0;
//        this.accNum = acc;
//        this.amt = amt1;
//        try {
//
//            String user = "postgres";
//            String password = "0000";
//            String url = "jdbc:postgresql://localhost:8001/sampleDB";
//            Connection conn = DriverManager.getConnection(url, user, password);
//
//            String getAmt = "select \"Amount\"\n" +
//                    "from \"bank\".customers\n" +
//                    "where  \"AccountNo\"= ?;";
//            PreparedStatement preparedStmt1 = conn.prepareStatement(getAmt);
//            preparedStmt1.setInt(1, accNum);
//            ResultSet rs =preparedStmt1.executeQuery();
//
//
//            if (rs.next()) {
//
//                newAmt = rs.getDouble(1);
//                System.out.println(newAmt);
//            }
//
//
//            // create the java mysql update preparedstatement
//            String query = "UPDATE bank.customers\n" +
//                    "\tSET  \"Amount\"=?\n" +
//                    "\tWHERE \"AccountNo\" = ?;";
//            PreparedStatement preparedStmt = conn.prepareStatement(query);
//            preparedStmt.setDouble(1, (amt + newAmt));
//            preparedStmt.setInt(2, accNum);
//
//            // execute the java preparedstatement
//            preparedStmt.executeUpdate();
//
//            conn.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//}
//
//
/////            String sql_query = "select *\n" +
////                    "from \"bank\".customers\n" +
////                    "where  \"AccountNo\"= 1;";
////
////
////
////            ResultSet rs = stmt.executeQuery(sql_query);
////
////            if (rs.next())
////            {
////                System.out.println("Name : " + rs.getString(1));
////                System.out.println("NIC : " + rs.getString(2));
////                System.out.println("Address :" + rs.getString(3));
////                System.out.println("AccNo :" + rs.getString(4));
////                System.out.println("Amount :" + rs.getString(5));
////            }
////            else
////            {
////                System.out.println("No such user id is already registered");
////            }