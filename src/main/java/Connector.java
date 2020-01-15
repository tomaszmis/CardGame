
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Connector {
    private String url = "jdbc:mysql://127.0.0.1:3306/cardgame?autoReconnect=true&useSSL=false";
    private String user = "wms";
    private String password = "wms";
    private ResultSet resultSet;
    private List<String> list= new ArrayList<String>();
    private Connection connection;

    public List getResultsFromDataBase() {
        try {
            connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM cardgame.score ORDER BY score.score DESC";
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                list.add(resultSet.getString("number") + " " + resultSet.getString("nick") + " " + resultSet.getString("score"));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        finally {
           try {
               connection.close();
           }catch (SQLException sqlEx){
               sqlEx.printStackTrace();
           }

        }
        return list;
    }
    public void saveResult(Map<String,Integer> scores){
        try{
            connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            String sql = "";
            for(Map.Entry<String,Integer> entry : scores.entrySet()){
                sql = "Insert INTO score(nick,score) Values(\'" + entry.getKey() +  "\', \'" +  entry.getValue() + "\')";
                statement.executeUpdate(sql);

            }
        }catch (SQLException sqlEx){
            sqlEx.printStackTrace();
        }
    }
    public int getMaxNumber(){
        int maxNumber = 0;
        try{
            connection = DriverManager.getConnection(url,user,password);
            Statement statement = connection.createStatement();
            String sql = "SELECT MAX(number) as `max` FROM cardgame.score";
            ResultSet resultSet = statement.executeQuery(sql);
            if(resultSet.next()) maxNumber = Integer.parseInt(resultSet.getString("max"));
        }catch (SQLException sqlEx){
            sqlEx.printStackTrace();
        }finally {
            try{
                connection.close();
            }catch (SQLException sqlEx){
                sqlEx.printStackTrace();
            }
        }
        return maxNumber;
    }

}