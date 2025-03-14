package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.Person;
import util.DatabaseConnection;

public class PersonDAO {
    public void createPerson(Person person) {
        String query = "INSERT INTO person (name, age, emoji) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement st = conn.prepareStatement(query)) {
            st.setString(1, person.getName());
            st.setInt(2, person.getAge());
            st.setString(3, person.getEmoji());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Person> getAllPersons() {
        List<Person> persons = new ArrayList<>();
        String query = "SELECT * FROM person";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                persons.add(new Person(rs.getString("emoji"), rs.getString("name"), rs.getInt("age")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return persons;
    }

    public void updatePerson(Person person, int id) {
        String query = "UPDATE person SET name=?, age=?, emoji=? WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement st = conn.prepareStatement(query)) {
            st.setString(1, person.getName());
            st.setInt(2, person.getAge());
            st.setString(3, person.getEmoji());
            st.setInt(4, id);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePerson(int id) {
        String query = "DELETE FROM person WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement st = conn.prepareStatement(query)) {
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
