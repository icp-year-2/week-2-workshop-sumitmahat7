package com.learninglogs.dao;

import com.learninglogs.entity.Entry;
import com.learninglogs.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * ╔══════════════════════════════════════════════════════╗
 * ║       QUEST: Build the Entry DAO Implementation      ║
 * ║                                                      ║
 * ║   Complete TODOs 7-8 to earn 110 XP!                 ║
 * ║   Bonus TODO 11 is worth an extra 20 XP!             ║
 * ╚══════════════════════════════════════════════════════╝
 *
 * This class implements the EntryDao interface.
 * It contains the actual database queries for entries.
 *
 * REFERENCE: Look at TopicDaoImpl.java — it shows the complete
 * JDBC pattern for both INSERT and SELECT operations.
 * Your code follows the SAME pattern, just with different
 * table names, columns, and entity objects.
 *
 * entries table columns:
 *   id         → rs.getInt("id")
 *   topic_id   → rs.getInt("topic_id")
 *   text       → rs.getString("text")
 *   created_at → rs.getTimestamp("created_at")
 *   updated_at → rs.getTimestamp("updated_at")
 */
public class EntryDaoImpl implements EntryDao {

    // ============================================================
    // TODO 7: Implement insertEntry() (+50 XP — ACHIEVEMENT: Engineer!)
    // ============================================================
    // This method should:
    //   1. Get a database connection
    //   2. Prepare an INSERT SQL statement
    //   3. Set the parameters (topic_id and text)
    //   4. Execute the update
    //   5. Return true if successful, false if not
    //   6. Close the connection in a finally block
    //
    // SQL to use: "INSERT INTO entries (topic_id, text) VALUES (?, ?)"
    //
    // Setting two parameters:
    //   statement.setInt(1, entry.getTopicId());    ← fills first ?
    //   statement.setString(2, entry.getText());     ← fills second ?
    //
    // This is the SAME pattern as insertTopic() in TopicDaoImpl,
    // but with two parameters instead of one.
    //
    // Hint:
    //   Connection conn = null;
    //   try {
    //       conn = DatabaseConnection.getConnection();
    //       String sql = "INSERT INTO entries (topic_id, text) VALUES (?, ?)";
    //       PreparedStatement statement = conn.prepareStatement(sql);
    //       statement.setInt(1, entry.getTopicId());
    //       statement.setString(2, entry.getText());
    //       statement.executeUpdate();
    //       return true;
    //   } catch (SQLException e) {
    //       System.out.println("Error inserting entry: " + e.getMessage());
    //       return false;
    //   } finally {
    //       DatabaseConnection.closeConnection(conn);
    //   }
    // ============================================================
    @Override
    public boolean insertEntry(Entry entry) {
        // Write your code here
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "INSERT INTO entries (topic_id, text) VALUES (?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, entry.getTopicId());
            statement.setString(2, entry.getText());
            statement.executeUpdate();
            return true;
        }catch (SQLException e) {
            System.out.println("Error inserting entry: " + e.getMessage());
            return false;
        }finally{
            DatabaseConnection.closeConnection(conn);
        }

    }

    // ============================================================
    // TODO 8: Implement fetchAllEntries() (+60 XP — ACHIEVEMENT: Builder!)
    // ============================================================
    // This method should:
    //   1. Create an empty ArrayList<Entry>
    //   2. Get a database connection
    //   3. Prepare a SELECT SQL statement
    //   4. Execute the query to get a ResultSet
    //   5. Loop through each row in the ResultSet
    //   6. For each row, create an Entry object and add it to the list
    //   7. Return the list
    //   8. Close the connection in a finally block
    //
    // SQL to use: "SELECT * FROM entries"
    //
    // Reading entry columns from ResultSet:
    //   rs.getInt("id")              → entry id
    //   rs.getString("text")         → entry text
    //   rs.getInt("topic_id")        → which topic it belongs to
    //   rs.getTimestamp("created_at") → when it was created
    //   rs.getTimestamp("updated_at") → when it was last updated
    //
    // This is the SAME pattern as fetchAllTopics() in TopicDaoImpl,
    // but creating Entry objects instead of Topic objects.
    //
    // Hint:
    //   ArrayList<Entry> entries = new ArrayList<>();
    //   Connection conn = null;
    //   try {
    //       conn = DatabaseConnection.getConnection();
    //       String sql = "SELECT * FROM entries";
    //       PreparedStatement statement = conn.prepareStatement(sql);
    //       ResultSet rs = statement.executeQuery();
    //       while (rs.next()) {
    //           Entry entry = new Entry(
    //               rs.getInt("id"),
    //               rs.getString("text"),
    //               rs.getInt("topic_id"),
    //               rs.getTimestamp("created_at"),
    //               rs.getTimestamp("updated_at")
    //           );
    //           entries.add(entry);
    //       }
    //   } catch (SQLException e) {
    //       System.out.println("Error fetching entries: " + e.getMessage());
    //   } finally {
    //       DatabaseConnection.closeConnection(conn);
    //   }
    //   return entries;
    // ============================================================
    @Override
    public ArrayList<Entry> fetchAllEntries() {
        // Write your code here
        ArrayList<Entry> entries = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM entries";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Entry entry = new Entry(
                        rs.getInt("id"),
                        rs.getString("text"),
                        rs.getInt("topic_id"),
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("updated_at")
                );
                entries.add(entry);
            }
        }catch (SQLException e) {
            System.out.println("Error fetching entries: " + e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(conn);
        }
        return entries;

    }

    // ============================================================
    // BONUS TODO 11: Implement fetchEntriesByTopicId() (+20 XP)
    // ============================================================
    // This method fetches only entries where topic_id matches.
    //
    // SQL to use: "SELECT * FROM entries WHERE topic_id = ?"
    //
    // This combines what you learned in:
    //   - fetchAllEntries() → SELECT + ResultSet loop
    //   - findTopicByName() → WHERE clause with ? parameter
    //
    // The difference from fetchAllEntries():
    //   - SQL has WHERE topic_id = ?
    //   - Use statement.setInt(1, topicId) to fill the ?
    //   - Everything else is the same!
    //
    // Hint:
    //   Same as fetchAllEntries(), but change the SQL to:
    //   "SELECT * FROM entries WHERE topic_id = ?"
    //   and add: statement.setInt(1, topicId);
    // ============================================================
    @Override
    public ArrayList<Entry> fetchEntriesByTopicId(int topicId) {
        // Write your code here
        ArrayList<Entry> entries = new ArrayList<>();
        Connection conn = null;

        try {
            conn = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM entries WHERE topic_id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, topicId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Entry entry = new Entry(
                        rs.getInt("id"),
                        rs.getString("text"),
                        rs.getInt("topic_id"),
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("updated_at")
                );
                entries.add(entry);
            }
        }catch (SQLException e) {
            System.out.println("Error fetching entries by topic ID: " + e.getMessage());

        }finally {
            DatabaseConnection.closeConnection(conn);
        }
        return entries;
    }
}
