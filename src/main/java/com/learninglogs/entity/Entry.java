package com.learninglogs.entity;

import java.sql.Timestamp;

/**
 * ╔══════════════════════════════════════════════════════╗
 * ║           QUEST: Build the Entry Entity              ║
 * ║                                                      ║
 * ║   Complete TODOs 1-5 below to earn 120 XP!           ║
 * ╚══════════════════════════════════════════════════════╝
 *
 * An Entry represents a learning note under a Topic.
 * This class maps to the `entries` table in your database:
 *
 *   ┌─────────────────────────────────────────────────┐
 *   │  entries table                                   │
 *   ├────────────┬────────────────────────────────────┤
 *   │ id         │ INT, AUTO_INCREMENT, PRIMARY KEY    │
 *   │ topic_id   │ INT, NOT NULL, FOREIGN KEY → topics │
 *   │ text       │ TEXT, NOT NULL                      │
 *   │ created_at │ TIMESTAMP                           │
 *   │ updated_at │ TIMESTAMP                           │
 *   └────────────┴────────────────────────────────────┘
 *
 * Look at Topic.java for reference — Entry follows the same pattern!
 *
 * Database Column → Java Field:
 *   id         → int id
 *   topic_id   → int topicId
 *   text       → String text
 *   created_at → Timestamp createdAt
 *   updated_at → Timestamp updatedAt
 */
public class Entry {

    // ============================================================
    // TODO 1: Declare the fields (+8 XP each = 40 XP)
    // ============================================================
    // Declare these private fields:
    //   - int id
    //   - String text
    //   - int topicId
    //   - Timestamp createdAt
    //   - Timestamp updatedAt
    //
    // Look at Topic.java for reference — same pattern, different fields.
    //
    // Why int topicId instead of Topic topic?
    //   The database stores a number (topic_id) as a foreign key,
    //   not the whole Topic object. We keep the Java field simple
    //   to match: just store the ID number.
    //
    // Hint: private int id;
    // ============================================================
    private int id;
    private String text;
    private int topicId;
    private Timestamp createdAt;
    private Timestamp updatedAt;



    // ============================================================
    // TODO 2: Create the insert constructor (+20 XP)
    // ============================================================
    // Create a constructor that takes (String text, int topicId)
    // Inside the constructor:
    //   - Set this.text = text
    //   - Set this.topicId = topicId
    //
    // When inserting, you only need the text and topicId —
    // the database generates the id and timestamps automatically.
    //
    // Look at Topic.java's simple constructor Topic(String name)
    // for reference — yours takes two parameters instead of one.
    //
    // Hint:
    //   public Entry(String text, int topicId) {
    //       this.text = text;
    //       this.topicId = topicId;
    //   }
    // ============================================================

    public Entry(String text, int topicId) {
        this.text = text;
        this.topicId = topicId;
    }


    // ============================================================
    // TODO 3: Create the full constructor (+20 XP)
    // ============================================================
    // Create a constructor that takes:
    //   (int id, String text, int topicId,
    //    Timestamp createdAt, Timestamp updatedAt)
    // Set all five fields.
    //
    // This is used when READING from the database — you have all
    // the column values, so you pass them all in.
    //
    // Look at Topic.java's full constructor for reference.
    //
    // Hint:
    //   public Entry(int id, String text, int topicId,
    //                Timestamp createdAt, Timestamp updatedAt) {
    //       this.id = id;
    //       this.text = text;
    //       this.topicId = topicId;
    //       this.createdAt = createdAt;
    //       this.updatedAt = updatedAt;
    //   }
    // ============================================================

    public Entry(int id, String text, int topicId, Timestamp createdAt, Timestamp updatedAt){
        this.id = id;
        this.text = text;
        this.topicId = topicId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    // ============================================================
    // TODO 4: Create getters and setter (+20 XP)
    // ============================================================
    // Fill in each method body below:
    //   - getId()        → returns id
    //   - getText()      → returns text
    //   - getTopicId()   → returns topicId
    //   - getCreatedAt() → returns createdAt
    //   - getUpdatedAt() → returns updatedAt
    //   - setText(String text) → sets text
    //
    // Hint: return this.id;
    // ============================================================
    public int getId() {
        // Write your code here
        return this.id;
    }

    public String getText() {
        // Write your code here
        return this.text;
    }

    public int getTopicId() {
        // Write your code here
        return this.topicId;
    }

    public Timestamp getCreatedAt() {
        // Write your code here
        return this.createdAt;
    }

    public Timestamp getUpdatedAt() {
        // Write your code here
        return this.updatedAt;
    }

    public void setText(String text) {
        // Write your code here
        this.text = text;
    }

    // ============================================================
    // TODO 5: Override toString() (+20 XP — ACHIEVEMENT: Scribe!)
    // ============================================================
    // Return a readable string like:
    //   "[1] Learned about variables (Topic ID: 2, Created: 2026-02-24 10:00:00.0)"
    //
    // Look at Topic.java's toString() for reference.
    //
    // Hint:
    //   return "[" + id + "] " + text
    //        + " (Topic ID: " + topicId
    //        + ", Created: " + createdAt + ")";
    // ============================================================
    @Override
    public String toString() {
        // Write your code here
        return "[" +id + "] " + text
                + " (Topic ID: " + topicId
                + ", Created: " + createdAt + ")";
    }
}
