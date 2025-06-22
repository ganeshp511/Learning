import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Java Date and Time Mini Project
 * Demonstrates usage of Java 8+ Date and Time API.
 */
public class DateTimeDemo {
    public static void main(String[] args) {
        // 1. LocalDate: Date without time or timezone
        LocalDate today = LocalDate.now();
        LocalDate birthday = LocalDate.of(1990, 6, 22);
        System.out.println("Today's date: " + today);
        System.out.println("Birthday: " + birthday);

        // 2. LocalTime: Time without date or timezone
        LocalTime now = LocalTime.now();
        LocalTime lunchTime = LocalTime.of(12, 30);
        System.out.println("Current time: " + now);
        System.out.println("Lunch time: " + lunchTime);

        // 3. LocalDateTime: Date and time without timezone
        LocalDateTime meeting = LocalDateTime.of(2025, 6, 22, 14, 0);
        System.out.println("Meeting: " + meeting);

        // 4. Period: Difference between two dates (years, months, days)
        Period age = Period.between(birthday, today);
        System.out.println("Age: " + age.getYears() + " years, " + age.getMonths() + " months, " + age.getDays() + " days");

        // 5. Duration: Difference between two times (hours, minutes, seconds)
        Duration lunchDuration = Duration.between(lunchTime, now);
        System.out.println("Time since lunch: " + lunchDuration.toMinutes() + " minutes");

        // 6. ChronoUnit: Calculate days between two dates
        long daysToMeeting = ChronoUnit.DAYS.between(today, meeting.toLocalDate());
        System.out.println("Days until meeting: " + daysToMeeting);

        // 7. DateTimeFormatter: Formatting and parsing
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        String formattedDate = today.format(formatter);
        System.out.println("Formatted today: " + formattedDate);
        LocalDate parsedDate = LocalDate.parse("22 Jun 2025", formatter);
        System.out.println("Parsed date: " + parsedDate);

        // 8. Time zones: ZonedDateTime and conversions
        ZoneId zoneParis = ZoneId.of("Europe/Paris");
        ZonedDateTime parisTime = ZonedDateTime.now(zoneParis);
        System.out.println("Current time in Paris: " + parisTime);

        ZonedDateTime utcTime = parisTime.withZoneSameInstant(ZoneId.of("UTC"));
        System.out.println("Same instant in UTC: " + utcTime);
    }
}
