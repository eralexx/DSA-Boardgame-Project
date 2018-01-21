package Model.Classes;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MySQLAccess {
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private Information info= Information.getInstance();

    public void readDataBase() throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost/DSA_TEST?"
                            + "user=root&password=dani2000");

            statement = connect.createStatement();
            //Falta insertarlo al inicio de mi http webserver.
            LoadInfoFromDB();
            showInfo();

        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }

    private void showInfo() throws  Exception{
        try{
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost/DSA_TEST?"
                            + "user=root&password=dani2000");

            statement = connect.createStatement();
        resultSet = statement
                .executeQuery("select * from DSA_TEST.users");
        writeMetaData(resultSet);
        writeResultSetUser(resultSet);

        resultSet = statement
                .executeQuery("select * from DSA_TEST.games");

        writeResultSetGames(resultSet);
        }
        catch(Exception e){
            throw e;
        } finally {
            close();
        }
    }

    private void writeResultSetGames(ResultSet resultSet) throws  Exception {
        try{
            while (resultSet.next()) {

                int id= resultSet.getInt("id");
                String username = resultSet.getString("players");
                String email = resultSet.getString("winner");
                String password = resultSet.getString("timestamp");
                System.out.println("id: " + String.valueOf(id));
                System.out.println("players: " + username);
                System.out.println("winner: " + email);
                System.out.println("timestamp: " + password);
            }


        }
        catch(Exception e){
            throw e;
        } finally {
            close();
        }
    }

    public void LoadInfoFromDB() throws Exception{
        try {
            this.info.getUserLists().setRegisteredUsers(GetAllUsersInDB());
            this.info.setGamesFinished(GetAllGamesInDB());

            for (User user :  this.info.getUserLists().getRegisteredUsers()){


                user.setGamesWon(info.getGamesFinished().stream()
                        .filter(item -> item.getWinner().equals(user.getEmail()))
                        .collect(Collectors.toList()).size());

                user.setGamesPlayed(info.getGamesFinished().stream()
                        .filter(item -> item.getPlayers().contains(user))
                        .collect(Collectors.toList()).size());
            }
        }
        catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }

    private List<Game> GetAllGamesInDB() throws Exception {
        try{
            Game newGame = new Game();
            List<Game> output = new ArrayList<>();
            List<User> userList= new ArrayList<>();

            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost/DSA_TEST?"
                            + "user=root&password=dani2000");
            statement = connect.createStatement();
            resultSet = statement
                    .executeQuery("select * from DSA_TEST.games");

            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                String playerIDs = resultSet.getString("players");// separados por  ;
                int winnerID = resultSet.getInt("winner");
                String timeStamp = resultSet.getString("timestamp");
                String[]asd=playerIDs.split(";");

                for(String stringId: asd){
                  int idPlayer= Integer.parseInt(stringId);
                  userList.add(info.getUserLists().getUserById(idPlayer));
                }
                newGame =  new Game(id,userList,info.getUserLists().getUserById(winnerID).getEmail(), timeStamp);
                output.add(newGame);
            }

            return output;
        }
        catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }

    private List<User> GetAllUsersInDB()throws Exception{
        try {
            User newUser = new User();
            List<User> output = new ArrayList<>();

            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost/DSA_TEST?"
                            + "user=root&password=dani2000");
            statement = connect.createStatement();
            resultSet = statement
                    .executeQuery("select * from DSA_TEST.users");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String imagepath = resultSet.getString("imagepath");
                newUser= new User(id, username, email, password, 0,0, imagepath);
                output.add(newUser);
            }
            return output;
        }
         catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }
    public void insertGameIntoDB(Game game)throws Exception {
        try {
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost/DSA_TEST?"
                            + "user=root&password=dani2000");
            statement = connect.createStatement();
            preparedStatement = connect
                    .prepareStatement("insert into  DSA_TEST.games values (default, ?, ?, ? )");

            int winnerID = this.info.getUserLists().getRegisteredUsers().stream()
                    .filter(item -> item.getEmail().equals(game.getWinner()))
                    .findFirst().get().getId();
            List<String> playerIds = new ArrayList<>();
            for (User x : game.getPlayers()) {
                playerIds.add(String.valueOf(x.getId()));
            }

            preparedStatement.setString(1, String.join(";", playerIds));
            preparedStatement.setString(2, String.valueOf(winnerID));
            preparedStatement.setString(3, game.getTimeStamp());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }

    public void insertUserIntoDB(User user)throws Exception{
        try {
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost/DSA_TEST?"
                            + "user=root&password=dani2000");
            statement = connect.createStatement();
            preparedStatement = connect
                    .prepareStatement("insert into  DSA_TEST.users values (default, ?, ?, ?, ? )");

            preparedStatement.setString(1,user.getUserName());
            preparedStatement.setString(2,user.getEmail());
            preparedStatement.setString(3,user.getPassword());
            preparedStatement.setString(4,user.getImagePath());
            preparedStatement.executeUpdate();
        }
         catch (Exception e) {
            throw e;
        } finally {
            close();
    }

    }
    private void writeMetaData(ResultSet resultSet) throws SQLException {

        System.out.println("The columns in the table are: ");

        System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
        for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
            System.out.println("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i));
        }
    }

    private void writeResultSetUser(ResultSet resultSet) throws SQLException {

        while (resultSet.next()) {

            int id= resultSet.getInt("id");
            String username = resultSet.getString("username");
            String email = resultSet.getString("email");
            String password = resultSet.getString("password");
            String imagepath = resultSet.getString("imagepath");
            System.out.println("id: " + String.valueOf(id));
            System.out.println("UserName: " + username);
            System.out.println("email: " + email);
            System.out.println("password: " + password);
            System.out.println("imagepath: " + imagepath);
        }
    }

    // You need to close the resultSet
    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
    }
}
