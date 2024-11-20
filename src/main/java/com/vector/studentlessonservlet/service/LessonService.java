package com.vector.studentlessonservlet.service;

import com.vector.studentlessonservlet.db.DBConnectionProvider;
import com.vector.studentlessonservlet.model.Lesson;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LessonService {

    private Connection connection = DBConnectionProvider.getInstance().getConnection();
    private UserService userService = new UserService();

    public void add(Lesson lesson) {
        String query = "INSERT INTO lesson(name,duration,price,lecturer_name,user_id) VALUES(?,?,?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, lesson.getName());
            ps.setInt(2, lesson.getDuration());
            ps.setDouble(3, lesson.getPrice());
            ps.setString(4, lesson.getLecturerName());
            ps.setInt(5,lesson.getUser().getId());
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                lesson.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }


    public List<Lesson> getAllLessons() {
        List<Lesson> result = new ArrayList<>();
        String sql = "SELECT * FROM lesson";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Lesson lesson = new Lesson();
                lesson.setId(resultSet.getInt("id"));
                lesson.setName(resultSet.getString("name"));
                lesson.setDuration(resultSet.getInt("duration"));
                lesson.setPrice(resultSet.getDouble("price"));
                lesson.setLecturerName(resultSet.getString("lecturer_name"));
                lesson.setUser(userService.getUserById(resultSet.getInt("user_id")));
                result.add(lesson);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Lesson getLessonById(int id) {
        String sql = "SELECT * FROM lesson WHERE id = " + id;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                Lesson lesson = new Lesson();
                lesson.setId(resultSet.getInt("id"));
                lesson.setName(resultSet.getString("name"));
                lesson.setDuration(resultSet.getInt("duration"));
                lesson.setPrice(resultSet.getDouble("price"));
                lesson.setLecturerName(resultSet.getString("lecturer_name"));
                lesson.setUser(userService.getUserById(resultSet.getInt("user_id")));
                return lesson;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteLesson(int lessonId) {
        String sql = "DELETE FROM lesson WHERE id = " + lessonId;
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Lesson lesson) {
        String query = "UPDATE lesson SET name = ?,duration = ?, price = ?, lecturer_name = ?, user_id = ? WHERE id = " + lesson.getId();
        try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, lesson.getName());
            ps.setInt(2, lesson.getDuration());
            ps.setDouble(3, lesson.getPrice());
            ps.setString(4, lesson.getLecturerName());
            ps.setInt(5, lesson.getUser().getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }


    public List<Lesson> getLessonsByUserId(int userId) {
        List<Lesson> result = new ArrayList<>();
        String sql = "SELECT * FROM lesson WHERE user_id = " + userId;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Lesson lesson = new Lesson();
                lesson.setId(resultSet.getInt("id"));
                lesson.setName(resultSet.getString("name"));
                lesson.setDuration(resultSet.getInt("duration"));
                lesson.setPrice(resultSet.getDouble("price"));
                lesson.setLecturerName(resultSet.getString("lecturer_name"));
                lesson.setUser(userService.getUserById(resultSet.getInt("user_id")));
                result.add(lesson);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Lesson getLessonByNameAndUserId(String name, int userId) {
        String sql = "SELECT * FROM lesson WHERE name = ? AND user_id =?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Lesson lesson = new Lesson();
                lesson.setId(resultSet.getInt("id"));
                lesson.setName(resultSet.getString("name"));
                lesson.setDuration(resultSet.getInt("duration"));
                lesson.setPrice(resultSet.getDouble("price"));
                lesson.setLecturerName(resultSet.getString("lecturer_name"));
                lesson.setUser(userService.getUserById(resultSet.getInt("user_id")));
                return lesson;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
