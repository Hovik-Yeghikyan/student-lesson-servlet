package com.vector.studentlessonservlet.service;

import com.vector.studentlessonservlet.db.DBConnectionProvider;
import com.vector.studentlessonservlet.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentService {

    private Connection connection = DBConnectionProvider.getInstance().getConnection();
    private LessonService lessonService = new LessonService();
    private UserService userService = new UserService();

    public void add(Student student) {
        String query = "INSERT INTO student(name,surname,email,age,lesson_id,students_user_id) VALUES(?,?,?,?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, student.getName());
            ps.setString(2, student.getSurname());
            ps.setString(3, student.getEmail());
            ps.setInt(4, student.getAge());
            ps.setInt(5, student.getLesson().getId());
            ps.setInt(6, student.getUser().getId());
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                student.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }


    public List<Student> getAllStudents() {
        List<Student> result = new ArrayList<>();
        String sql = "SELECT * FROM student";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setSurname(resultSet.getString("surname"));
                student.setEmail(resultSet.getString("email"));
                student.setAge(resultSet.getInt("age"));
                student.setLesson(lessonService.getLessonById(resultSet.getInt("lesson_id")));
                student.setUser(userService.getUserById(resultSet.getInt("students_user_id")));
                result.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Student getStudentById(int id) {
        String sql = "SELECT * FROM student WHERE id = " + id;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setSurname(resultSet.getString("surname"));
                student.setEmail(resultSet.getString("email"));
                student.setAge(resultSet.getInt("age"));
                student.setLesson(lessonService.getLessonById(resultSet.getInt("lesson_id")));
                student.setUser(userService.getUserById(resultSet.getInt("students_user_id")));
                return student;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteStudent(int studentId) {
        String sql = "DELETE FROM student WHERE id = " + studentId;
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Student student) {
        String query = "UPDATE student SET name = ?,surname = ?, email = ?, age = ?,lesson_id = ?,students_user_id = ?  WHERE id = " + student.getId();
        try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, student.getName());
            ps.setString(2, student.getSurname());
            ps.setString(3, student.getEmail());
            ps.setInt(4, student.getAge());
            ps.setInt(5, student.getLesson().getId());
            ps.setInt(6, student.getUser().getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public List<Student> getStudentsByUserId(int userId) {
        List<Student> result = new ArrayList<>();
        String sql = "SELECT * FROM student WHERE students_user_id = " + userId;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setSurname(resultSet.getString("surname"));
                student.setEmail(resultSet.getString("email"));
                student.setAge(resultSet.getInt("age"));
                student.setLesson(lessonService.getLessonById(resultSet.getInt("lesson_id")));
                student.setUser(userService.getUserById(resultSet.getInt("students_user_id")));
                result.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Student getStudentByEmail(String email) {
        String sql = "SELECT * FROM student WHERE name = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setSurname(resultSet.getString("surname"));
                student.setEmail(resultSet.getString("email"));
                student.setId(resultSet.getInt("age"));
                student.setLesson(lessonService.getLessonById(resultSet.getInt("lesson_id")));
                student.setUser(userService.getUserById(resultSet.getInt("students_user_id")));
                return student;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

