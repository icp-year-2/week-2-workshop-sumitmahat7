package com.learninglogs.dao;

import com.learninglogs.entity.Topic;
import com.learninglogs.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * ╔══════════════════════════════════════════════════════╗
 * ║       Topic DAO Implementation                       ║
 * ║                                                      ║
 * ║   insertTopic() and fetchAllTopics() are PROVIDED.   ║
 * ║   Bonus TODO 6 is worth 40 XP!                       ║
 * ╚══════════════════════════════════════════════════════╝
 *
 * Study the provided methods — they show the JDBC pattern
 * you'll use in EntryDaoImpl.java.
 *
 * Key patterns to notice:
 *   - Connection conn = null; ... try/catch/finally
 *   - PreparedStatement with ? placeholders
 *   - executeUpdate() for INSERT, executeQuery() for SELECT
 *   - Always close connection in finally block
 */
public class TopicDaoImpl implements TopicDao {

    /**
     * Insert a new topic. (PROVIDED — study the JDBC pattern!)
     *
     * Pattern:
     *   1. Get connection
     *   2. Prepare SQL with ? placeholder
     *   3. Set parameters
     *   4. Execute update
     *   5. Close connection in finally
     */
    @Override
    public boolean insertTopic(Topic topic) {
        // ============================================================
        // BONUS TODO 13: Prevent duplicate topic names (+20 XP)
        // ============================================================
        // Add a check HERE (before the insert) to prevent duplicates.
        // First, complete BONUS TODO 6 (findTopicByName), then add:
        //
        //   if (findTopicByName(topic.getName()) != null) {
        //       System.out.println("Topic already exists: " + topic.getName());
        //       return false;
        //   }
        //
        // Why?
        //   Without this check, a user could add "Python" twice.
        //   By calling findTopicByName() first, you reject duplicates
        //   with a friendly message instead of a database error.
        // ============================================================

        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "INSERT INTO topics (name) VALUES (?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, topic.getName());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error inserting topic: " + e.getMessage());
            return false;
        } finally {
            DatabaseConnection.closeConnection(conn);
        }
    }

    /**
     * Fetch all topics. (PROVIDED — study the JDBC pattern!)
     *
     * Pattern for SELECT queries:
     *   1. Create empty ArrayList
     *   2. Get connection
     *   3. Prepare SQL
     *   4. Execute query → get ResultSet
     *   5. Loop with rs.next(), create objects
     *   6. Close connection in finally
     *   7. Return list
     */
    @Override
    public ArrayList<Topic> fetchAllTopics() {
        ArrayList<Topic> topics = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM topics";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Topic topic = new Topic(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getTimestamp("created_at"),
                    rs.getTimestamp("updated_at")
                );
                topics.add(topic);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching topics: " + e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(conn);
        }
        return topics;
    }

    // ============================================================
    // BONUS TODO 6: Implement findTopicByName() (+40 XP — ACHIEVEMENT: Seeker!)
    // ============================================================
    // This method searches for a topic by name (case-insensitive).
    // Return the Topic if found, or null if not found.
    //
    // SQL to use: "SELECT * FROM topics WHERE LOWER(name) = LOWER(?)"
    //
    // Why LOWER()?
    //   LOWER() converts both the column value and your search
    //   term to lowercase before comparing. So "Python" matches
    //   "python", "PYTHON", etc.
    //
    // This is similar to fetchAllTopics() but:
    //   - Uses WHERE to filter (not all rows)
    //   - Uses setString(1, name) to fill the ? placeholder
    //   - Returns ONE Topic (not a list) — use if (rs.next()) instead of while
    //
    // Hint:
    //   Connection conn = null;
    //   try {
    //       conn = DatabaseConnection.getConnection();
    //       String sql = "SELECT * FROM topics WHERE LOWER(name) = LOWER(?)";
    //       PreparedStatement statement = conn.prepareStatement(sql);
    //       statement.setString(1, name);
    //       ResultSet rs = statement.executeQuery();
    //       if (rs.next()) {
    //           return new Topic(
    //               rs.getInt("id"),
    //               rs.getString("name"),
    //               rs.getTimestamp("created_at"),
    //               rs.getTimestamp("updated_at")
    //           );
    //       }
    //   } catch (SQLException e) {
    //       System.out.println("Error finding topic: " + e.getMessage());
    //   } finally {
    //       DatabaseConnection.closeConnection(conn);
    //   }
    //   return null;
    // ============================================================
    @Override
    public Topic findTopicByName(String name) {
        // Write your code here
        Connection conn = null;

        try {
            conn = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM topics WHERE LOWER(name) = LOWER(?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return new Topic(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("updated_at")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error finding topic: " + e.getMessage());

        } finally {
            DatabaseConnection.closeConnection(conn);
        }
        return null;
    }
}
