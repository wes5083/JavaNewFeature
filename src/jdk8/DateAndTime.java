package jdk8;

import java.time.*;

public class DateAndTime {
    public static void main(String[] args) {
        //current date
        LocalDate localDate = LocalDate.now();
        // time
        LocalTime localTime = LocalTime.of(12, 20);
        //current date and time
        LocalDateTime localDateTime = LocalDateTime.now();
        //2017-03-13T11:28:29.247+08:00：east zone 8
        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        //2017-03-13T04:28:29.248+01:00[Europe/Paris]：
        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("Europe/Paris"));


        //2017-03-13T03:38:57.134Z
        Instant instant = Instant.now();
        //2017-03-13T03:38:57.134Z
        Instant instant1 = instant.plus(Duration.ofMillis(5000));
        //2017-03-13T03:38:47.134Z
        Instant instant2 = instant.minus(Duration.ofMillis(5000));
        //2017-03-13T03:38:42.134Z
        Instant instant3 = instant.minusSeconds(10);

        Duration duration = Duration.ofMillis(5000);
        duration = Duration.ofSeconds(60);
        duration = Duration.ofMinutes(10);

        Period period = Period.ofDays(6);
        period = Period.ofMonths(6);
        period = Period.between(LocalDate.now(), LocalDate.now().plusDays(60));
    }
}
