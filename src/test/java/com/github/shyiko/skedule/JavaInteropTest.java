package com.github.shyiko.skedule;

import org.testng.annotations.Test;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class JavaInteropTest {

    private static void assertEquivalent(Schedule schedule, String expression) {
        assertThat(schedule.toString()).isEqualTo(expression);
    }

    @Test
    public void testInstanceConstruction() {
        assertEquivalent(Schedule.every(12, ChronoUnit.HOURS), "every 12 hours");
        assertEquivalent(Schedule.every(2, ChronoUnit.HOURS, true), "every 2 hours synchronized");
        assertEquivalent(Schedule.from(LocalTime.of(10, 0), LocalTime.of(14, 0)).every(5, ChronoUnit.MINUTES),
            "every 5 minutes from 10:00 to 14:00");

        assertEquivalent(Schedule.at(LocalTime.of(0, 0)).everyDay(), "every day 00:00");
        assertEquivalent(Schedule.at(LocalTime.of(0, 0)).everyDay(Month.MARCH), "every day of march 00:00");

        assertEquivalent(Schedule.at(LocalTime.of(9, 0)).every(DayOfWeek.MONDAY), "every monday 09:00");
        assertEquivalent(Schedule.at(LocalTime.of(9, 0)).every(DayOfWeek.MONDAY, Month.MARCH),
            "every monday of march 09:00");

        assertEquivalent(
            Schedule.at(LocalTime.of(17, 0))
                .nth(2, 3, DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY)
                .of(Month.MARCH),
            "2,3 monday,wednesday,thursday of march 17:00"
        );
        assertEquivalent(
            Schedule.at(LocalTime.of(0, 0))
                .nth(1)
                .of(Month.JANUARY, Month.APRIL, Month.JULY, Month.OCTOBER),
            "1 of january,april,july,october 00:00"
        );
    }

}
