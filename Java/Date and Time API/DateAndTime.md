# Java 8+ Date and Time API

## LocalDate, LocalTime, LocalDateTime

Java 8 introduced a new date and time API in the `java.time` package to address the shortcomings of the old `java.util.Date` and `java.util.Calendar` classes. The core classes for representing date and time are:

- **LocalDate**: Represents a date without a time-zone, such as `2025-06-22`. It is ideal for representing birthdays, anniversaries, or any date without time-of-day information. Example usage:
  ```java
  LocalDate today = LocalDate.now();
  LocalDate specificDate = LocalDate.of(2025, 6, 22);
  ```
  LocalDate is immutable and thread-safe. It provides methods for date manipulation, such as `plusDays()`, `minusMonths()`, and comparison methods like `isBefore()` and `isAfter()`.

- **LocalTime**: Represents a time without a date or time-zone, such as `14:30:00`. Useful for representing times of day, like opening hours. Example:
  ```java
  LocalTime now = LocalTime.now();
  LocalTime specificTime = LocalTime.of(14, 30, 0);
  ```
  LocalTime supports operations like adding or subtracting hours, minutes, or seconds.

- **LocalDateTime**: Combines date and time, but still without a time-zone, such as `2025-06-22T14:30:00`. Example:
  ```java
  LocalDateTime now = LocalDateTime.now();
  LocalDateTime meeting = LocalDateTime.of(2025, 6, 22, 14, 30);
  ```
  LocalDateTime is useful for representing a specific moment in time, but not globally unique unless combined with a time-zone.

## Period, Duration, ChronoUnit

- **Period**: Represents a quantity of time in terms of years, months, and days. It is used to measure the amount of time between two `LocalDate` instances. Example:
  ```java
  LocalDate start = LocalDate.of(2025, 1, 1);
  LocalDate end = LocalDate.of(2025, 6, 22);
  Period period = Period.between(start, end); // 0 years, 5 months, 21 days
  ```
  Period is useful for date-based calculations, such as calculating age.

- **Duration**: Represents a time-based amount of time, such as seconds, minutes, or hours. It is used with `LocalTime` or `LocalDateTime`. Example:
  ```java
  LocalTime start = LocalTime.of(9, 0);
  LocalTime end = LocalTime.of(17, 30);
  Duration duration = Duration.between(start, end); // 8 hours, 30 minutes
  ```
  Duration is ideal for measuring elapsed time or intervals.

- **ChronoUnit**: An enumeration that provides standard units of time, such as DAYS, MONTHS, YEARS, HOURS, etc. It is often used to perform calculations or measure the difference between two temporal objects:
  ```java
  long daysBetween = ChronoUnit.DAYS.between(start, end);
  ```
  ChronoUnit simplifies date/time arithmetic and supports a wide range of units.

## DateTimeFormatter

`DateTimeFormatter` is used to format and parse date-time objects. It replaces the old `SimpleDateFormat` and is immutable and thread-safe. You can use predefined formatters or define custom patterns:

- **Formatting**:
  ```java
  LocalDate date = LocalDate.now();
  String formatted = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
  ```
- **Parsing**:
  ```java
  String input = "22-06-2025";
  LocalDate parsed = LocalDate.parse(input, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
  ```
DateTimeFormatter supports localization and ISO standards, making it flexible for international applications.

## Time Zones and Conversions

Time zones are handled using the `ZoneId` and `ZonedDateTime` classes. This allows you to represent a date and time in a specific time zone, and convert between zones:

- **ZoneId**: Represents a region-based time zone, such as `Europe/Paris` or `America/New_York`.
  ```java
  ZoneId zoneId = ZoneId.of("America/New_York");
  ```
- **ZonedDateTime**: Combines date, time, and time zone into a single object.
  ```java
  ZonedDateTime zonedNow = ZonedDateTime.now(zoneId);
  ```
- **Conversion**:
  ```java
  LocalDateTime localDateTime = LocalDateTime.now();
  ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of("UTC"));
  ZonedDateTime converted = zonedDateTime.withZoneSameInstant(ZoneId.of("Asia/Tokyo"));
  ```
This API makes it easy to work with global applications, schedule events across time zones, and avoid common pitfalls with daylight saving time and offsets.