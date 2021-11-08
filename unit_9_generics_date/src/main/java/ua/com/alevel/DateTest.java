package ua.com.alevel;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateTest {

    public void test() {
        Date date = new Date();
        System.out.println("date = " + date);
        // Mon Nov  8 15:59:51 EET 2021
        // GMT and UTC
        System.out.println("start date = " + new Date(0L));
        System.out.println("finish date = " + new Date(Long.MAX_VALUE));

        TimeZone timeZone = TimeZone.getDefault();
        System.out.println("timeZone = " + timeZone);

        Calendar now = Calendar.getInstance();
        System.out.println("now = " + now.getTime());

        Calendar next = Calendar.getInstance();
        next.add(Calendar.MINUTE, 60);

        System.out.println("next = " + next.getTime());

        LocalTime localTime = LocalTime.now();
        System.out.println("localTime = " + localTime);

        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("localDateTime = " + localDateTime);

        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        System.out.println("offsetDateTime = " + offsetDateTime);
    }
}
