package ec.edu.espe.ivstudio.unihelper.model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Andy Tiban, IV Studio, @ESPE
 */
public class CsvStorage {
    public static boolean exportUserSummaryToCsv(String filename, UserAccount user) {
        if (user == null) {
            return false;
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            // 1. EVENTOS DE CALENDARIO
            writer.println("=== CALENDAR EVENTS ===");
            writer.println("Title,Date,Time,Description");
            if (user.getCalendarEvents() != null) {
                for (CalendarEvent event : user.getCalendarEvents()) {
                    writer.printf("\"%s\",%s,%s,\"%s\"%n",
                            escape(event.getTitle()),
                            escape(event.getDate()),
                            escape(event.getTime()),
                            escape(event.getDescription()));
                }
            }

            // 2. CALIFICACIONES / NOTAS
            writer.println("\n=== GRADES ===");
            writer.println("GradeName,Score,WeightPercentage");
            if (user.getGrades() != null) {
                for (GradeItem grade : user.getGrades()) {
                    writer.printf("\"%s\",%.2f,%.2f%n",
                            escape(grade.getName()),
                            grade.getScore(),
                            grade.getWeight());
                }
            }


            writer.println("\n=== STUDY SESSIONS ===");
            writer.println("Topic,DurationMinutes");
            if (user.getStudySessions() != null) {
                for (StudySession session : user.getStudySessions()) {
                    writer.printf("\"%s\",%d%n",
                            escape(session.getTopic()),
                            session.getDurationMinutes());
                }
            }

            return true;
        } catch (IOException e) {
            System.err.println("Error saving CSV report to " + filename + ": " + e.getMessage());
            return false;
        }
    }

    private static String escape(String value) {
        if (value == null) return "";
        return value.replace("\"", "\"\"");
    }
    
    
    
}
