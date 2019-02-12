package sample;

import org.sqlite.SQLiteConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DbHandler {

    public SQLiteConnection getConnection(){
        SQLiteConnection connection;

        try {
            connection = new SQLiteConnection("jdbc:sqlite:" ,"Db/db.Db");
            System.out.println("Ok");
            return connection;
        } catch (SQLException e) {

            e.printStackTrace();
        }return null;
    }
    public void creatUsers(String login,String password)  {
        SQLiteConnection connection = getConnection();
        String query = "insert into users(login,password)"+"values(?,?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,login);
            preparedStatement.setString(2,password);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<User> getUser(){
        SQLiteConnection connection = getConnection();
        ArrayList<User> users = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("select login,password from users");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                User user = new User(resultSet.getString("login"),resultSet.getString("password"));
                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;

    }
    public void addStudent(Students students){
        SQLiteConnection connection = getConnection();
        String query;
        if (students.getStudentid() == 0) {
            query = "insert into students(name,phone,e_mail,is_active) values (?,?,?,?)";
        }else {
            query = "update students set name = ?,phone = ?,e_mail = ?,is_active = ?\n"
           +" where studentid = ?";
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,students.getName());
            preparedStatement.setString(2,students.getPhone());
            preparedStatement.setString(3,students.getE_mail());
            preparedStatement.setInt(4,students.getIsActive());
            if (students.getStudentid() != 0){
                preparedStatement.setInt(5,students.getStudentid());
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void addTeacher(Teachers teachers){
        SQLiteConnection connection = getConnection();
        String query;
        if (teachers.getTeacherid() == 0) {
            query = "insert into teachers(name,phone,e_mail,is_active) values (?,?,?,?)";
        }else {
            query = "update teachers set name = ?,phone = ?,e_mail = ?,is_active = ?\n"
                    +" where teacherid = ?";
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,teachers.getName());
            preparedStatement.setString(2,teachers.getPhone());
            preparedStatement.setString(3,teachers.geteMail());
            preparedStatement.setInt(4,teachers.getIsActive());
            if (teachers.getTeacherid() != 0){
                preparedStatement.setInt(5,teachers.getTeacherid());
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void addCourses(Course course, Days days,Times times){
        SQLiteConnection connection = getConnection();

        String query;
        if (course.getCourseid() == 0) {
            query = "insert into courses(name,price,day,time) values(?,?,?,?)";
        }
        else {
            query = "update courses set name = ?,price = ?,day = ?,time = ? \n"
            + "where courseid = ?";
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,course.getName());
            preparedStatement.setInt(2,course.getPrice());
            preparedStatement.setString(3,days.getDay());
            preparedStatement.setString(4,times.getTime());
            if (course.getCourseid() != 0) {
                preparedStatement.setInt(5, course.getCourseid());
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Course> courses(){
        SQLiteConnection connection = getConnection();
        ArrayList<Course>courses = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from  courses ");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                courses.add(new Course(resultSet.getInt(1),resultSet.getString(2),resultSet.getInt(3),resultSet.getString(4),resultSet.getString(5)));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    public void ShowCourses(){
        SQLiteConnection connection = getConnection();

        String query = "select * from courses";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet set = statement.executeQuery();

            while (set.next()){
                System.out.println(set.getString(1));
                System.out.println(set.getString(2));
                System.out.println(set.getString(3));
                System.out.println(set.getString(4));
                System.out.println(set.getString(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Students> getStudent(){
        SQLiteConnection connection = getConnection();
        String qwery = "select * from students";
        ArrayList<Students>students1 = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(qwery);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
            students1.add(new Students(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getInt(5)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }return students1;
    }
    public ArrayList<Teachers> getTeacher(){
        SQLiteConnection connection = getConnection();
        ArrayList<Teachers>teachers = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from teachers");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                teachers.add(new Teachers(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getInt(5)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }return teachers;

    }
    public ArrayList<Days> getDay(){
        SQLiteConnection connection = getConnection();
        ArrayList<Days>days = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from days");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                days.add(new Days(resultSet.getString(1)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }return days;
    }
    public ArrayList<Times> getTime(){
        SQLiteConnection connection = getConnection();
        ArrayList<Times>times = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from times");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                times.add(new Times(resultSet.getString(1)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }return times;
    }

}
