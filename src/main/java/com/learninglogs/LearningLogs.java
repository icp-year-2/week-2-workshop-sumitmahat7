package com.learninglogs;

import com.learninglogs.entity.Topic;
import com.learninglogs.entity.Entry;
import com.learninglogs.dao.TopicDao;
import com.learninglogs.dao.TopicDaoImpl;
import com.learninglogs.dao.EntryDao;
import com.learninglogs.dao.EntryDaoImpl;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * ╔══════════════════════════════════════════════════════╗
 * ║     Learning Logs Terminal — Main Menu                ║
 * ║                                                      ║
 * ║   Topics (options 1-2) are PROVIDED. Study them!     ║
 * ║   Complete TODOs 9-10 to earn 65 XP!                 ║
 * ║   Bonus TODO 12 is worth 20 XP!                      ║
 * ╚══════════════════════════════════════════════════════╝
 */
public class LearningLogs {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TopicDao topicDao = new TopicDaoImpl();
        EntryDao entryDao = new EntryDaoImpl();

        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║     Welcome to Learning Logs Terminal    ║");
        System.out.println("║     Now with Database Storage!           ║");
        System.out.println("╚══════════════════════════════════════════╝");
        System.out.println();

        boolean running = true;

        while (running) {
            System.out.println("┌──────────────────────────────┐");
            System.out.println("│         MAIN MENU            │");
            System.out.println("├──────────────────────────────┤");
            System.out.println("│  1. Add a new Topic          │");
            System.out.println("│  2. View all Topics          │");
            System.out.println("│  3. Add an Entry             │");
            System.out.println("│  4. View all Entries         │");
            System.out.println("│  5. View Entries by Topic    │");
            System.out.println("│  6. Exit                     │");
            System.out.println("└──────────────────────────────┘");
            System.out.print("Choose an option (1-6): ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                // ── PROVIDED: Add a Topic ──────────────────────────
                case "1" -> {
                    System.out.print("Enter topic name: ");
                    String name = scanner.nextLine().trim();

                    if (name.isEmpty()) {
                        System.out.println("Topic name cannot be empty!\n");
                    } else {
                        Topic topic = new Topic(name);
                        boolean success = topicDao.insertTopic(topic);
                        if (success) {
                            System.out.println("Topic added: " + name);
                        } else {
                            System.out.println("Failed to add topic.\n");
                        }
                        System.out.println();
                    }
                }
                // ── PROVIDED: View all Topics ─────────────────────
                case "2" -> {
                    ArrayList<Topic> topics = topicDao.fetchAllTopics();

                    if (topics == null || topics.isEmpty()) {
                        System.out.println("No topics yet. Add your first topic!\n");
                    } else {
                        System.out.println("\n── Your Topics ──────────────────");
                        for (Topic topic : topics) {
                            System.out.println("  " + topic);
                        }
                        System.out.println("─────────────────────────────────");
                        System.out.println("  Total: " + topics.size() + " topic(s)\n");
                    }
                }
                // ============================================================
                // TODO 9: Add an Entry (+40 XP)
                // ============================================================
                // When the user selects option 3, you should:
                //   1. Fetch and display all topics (so user can pick one)
                //   2. Ask the user to enter a topic ID
                //   3. Validate the topic ID (handle non-numbers)
                //   4. Ask the user to enter their learning note text
                //   5. Create an Entry object and insert it using entryDao
                //   6. Show a confirmation message
                //
                // Study how option "1" (Add Topic) works — yours is similar
                // but needs a topic selection step first.
                //
                // You'll need:
                //   - topicDao.fetchAllTopics() to show available topics
                //   - Integer.parseInt(input) to convert the ID string to int
                //   - new Entry(text, topicId) to create the entry
                //   - entryDao.insertEntry(entry) to save it
                //
                // Handle these edge cases:
                //   - No topics exist yet → "No topics yet. Add a topic first!"
                //   - Invalid number → catch NumberFormatException
                //   - Empty text → "Entry text cannot be empty!"
                //
                // Hint:
                //   case "3" -> {
                //       ArrayList<Topic> topics = topicDao.fetchAllTopics();
                //       if (topics == null || topics.isEmpty()) {
                //           System.out.println("No topics yet. Add a topic first!\n");
                //       } else {
                //           System.out.println("\n── Select a Topic ───────────────");
                //           for (Topic topic : topics) {
                //               System.out.println("  " + topic);
                //           }
                //           System.out.println("─────────────────────────────────");
                //           System.out.print("Enter topic ID: ");
                //           String idInput = scanner.nextLine().trim();
                //           try {
                //               int topicId = Integer.parseInt(idInput);
                //               System.out.print("Enter your learning note: ");
                //               String text = scanner.nextLine().trim();
                //               if (text.isEmpty()) {
                //                   System.out.println("Entry text cannot be empty!\n");
                //               } else {
                //                   Entry entry = new Entry(text, topicId);
                //                   boolean success = entryDao.insertEntry(entry);
                //                   if (success) {
                //                       System.out.println("Entry added under topic ID: " + topicId);
                //                   } else {
                //                       System.out.println("Failed to add entry.\n");
                //                   }
                //                   System.out.println();
                //               }
                //           } catch (NumberFormatException e) {
                //               System.out.println("Please enter a valid number!\n");
                //           }
                //       }
                //   }
                // ============================================================
                case "3" -> {
                    ArrayList<Topic> topics = topicDao.fetchAllTopics();

                    if (topics == null || topics.isEmpty()) {
                        System.out.println("No topics yet. Add a topic first!\n");
                    } else {
                        System.out.println("\n── Select a Topic ───────────────");
                        for (Topic topic : topics) {
                            System.out.println(" " + topic);
                        }
                        System.out.println("─────────────────────────────────");
                        System.out.print("Enter topic ID: ");
                        String idInput = scanner.nextLine().trim();

                        try {
                            int topicId = Integer.parseInt(idInput);
                            System.out.print("Enter your learning notes: ");
                            String text = scanner.nextLine().trim();

                            if (text.isEmpty()) {
                                System.out.println("Entry text cannot be empty!\n");
                            } else {
                                Entry entry = new Entry(text, topicId);
                                boolean success = entryDao.insertEntry(entry);

                                if (success) {
                                    System.out.println("Entry added under topic ID: " + topicId);
                                } else {
                                    System.out.println("Failed to add entry.\n");

                                }
                                System.out.println();
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Please enter a valid number!\n");
                        }

                    }
                }


                // ============================================================
                // TODO 10: View all Entries (+25 XP — ACHIEVEMENT: Operator!)
                // ============================================================
                // When the user selects option 4, you should:
                //   1. Call entryDao.fetchAllEntries()
                //   2. If the list is empty, show "No entries yet."
                //   3. Otherwise, loop through and display each entry
                //
                // Study how option "2" (View Topics) works — same pattern!
                //
                // Hint:
                //   case "4" -> {
                //       ArrayList<Entry> entries = entryDao.fetchAllEntries();
                //       if (entries == null || entries.isEmpty()) {
                //           System.out.println("No entries yet. Add your first entry!\n");
                //       } else {
                //           System.out.println("\n── All Entries ──────────────────");
                //           for (Entry entry : entries) {
                //               System.out.println("  " + entry);
                //           }
                //           System.out.println("─────────────────────────────────\n");
                //       }
                //   }
                // ============================================================
                case "4" -> {
                    // Write your code here
                    ArrayList<Entry> entries = entryDao.fetchAllEntries();
                    if (entries == null || entries.isEmpty()) {
                        System.out.println("No entries yet. Add your first entry!\n");
                    } else {
                        System.out.println("\n── All Entries ──────────────────");
                        for (Entry entry : entries) {
                            System.out.println(" " + entry);
                        }
                        System.out.println("───────────────────\n");
                    }
                }
                // ============================================================
                // BONUS TODO 12: View Entries by Topic (+20 XP)
                // ============================================================
                // When the user selects option 5, you should:
                //   1. Fetch and display all topics
                //   2. Ask the user to enter a topic ID
                //   3. Call entryDao.fetchEntriesByTopicId(topicId)
                //   4. Display the filtered entries
                //
                // This combines the topic-selection pattern from TODO 9
                // with the entry-display pattern from TODO 10.
                //
                // Requires: BONUS TODO 11 (fetchEntriesByTopicId)
                //
                // Hint:
                //   case "5" -> {
                //       ArrayList<Topic> topics = topicDao.fetchAllTopics();
                //       if (topics == null || topics.isEmpty()) {
                //           System.out.println("No topics yet. Add a topic first!\n");
                //       } else {
                //           System.out.println("\n── Select a Topic ───────────────");
                //           for (Topic topic : topics) {
                //               System.out.println("  " + topic);
                //           }
                //           System.out.println("─────────────────────────────────");
                //           System.out.print("Enter topic ID: ");
                //           String idInput = scanner.nextLine().trim();
                //           try {
                //               int topicId = Integer.parseInt(idInput);
                //               ArrayList<Entry> entries = entryDao.fetchEntriesByTopicId(topicId);
                //               if (entries == null || entries.isEmpty()) {
                //                   System.out.println("No entries for this topic.\n");
                //               } else {
                //                   System.out.println("\n── Entries for Topic " + topicId + " ──");
                //                   for (Entry entry : entries) {
                //                       System.out.println("  " + entry);
                //                   }
                //                   System.out.println("─────────────────────────────────\n");
                //               }
                //           } catch (NumberFormatException e) {
                //               System.out.println("Please enter a valid number!\n");
                //           }
                //       }
                //   }
                // ============================================================
                case "5" -> {
                    // Write your code here
                    ArrayList<Topic> topics = topicDao.fetchAllTopics();
                    if (topics == null || topics.isEmpty()) {
                        System.out.println("No topics yet. Add atopic first!\n");
                    } else {
                        System.out.println("\n── Select a Topic ───────────────");
                        for (Topic topic : topics) {
                            System.out.println(" " + topic);
                        }
                        System.out.println("────────────────");
                        System.out.print("Enter topic ID: ");
                        String idInput = scanner.nextLine().trim();

                        try {
                            int topicId = Integer.parseInt(idInput);
                            ArrayList<Entry> entries = entryDao.fetchEntriesByTopicId(topicId);
                            if (entries == null || entries.isEmpty()) {
                                System.out.println("No entries for this topic.\n");

                            } else {
                                System.out.println("\n── Entries for Topic " + topicId + "──");

                                for (Entry entry : entries) {
                                    System.out.println(" " + entry);
                                }
                                System.out.println("────────────────────────────────\n");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Please enter avalid number!\n");
                        }
                    }
                }
                case "6" -> {
                    running = false;
                    System.out.println("\nHappy Learning! See you next time.\n");
                }
                default -> System.out.println("Invalid option. Please choose 1-6.\n");
            }
        }

        scanner.close();
    }
}
