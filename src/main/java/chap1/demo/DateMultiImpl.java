package chap1.demo;

import java.time.LocalDate;

public class DateMultiImpl {
    public static void main(String... args) {
        LocalDate cur = LocalDate.now();
        Date_1 date_1 = new DateMultiImpl().new Date_1(cur.getYear(), cur.getMonthValue(), cur.getDayOfMonth());
        System.out.println(date_1);
        Date_2 date_2 = new DateMultiImpl().new Date_2(cur.getYear(), cur.getMonthValue(), cur.getDayOfMonth());
        System.out.println(date_2);

        System.out.println(String.format("Date_1 equals to Date_2: %s", date_2.equals(date_1)));
    }

    class Date_1 {
        int year;
        int month;
        int day;

        public Date_1(int theYear, int theMonth, int theDay) {
            this.year = theYear;
            this.month = theMonth;
            this.day = theDay;
        }

        public int getYear() {
            return year;
        }

        public int getMonth() {
            return month;
        }

        public int getDay() {
            return day;
        }

        @Override
        public String toString() {
            return String.format("%d/%d/%d", getMonth(), getDay(), getYear());
        }
    }

    class Date_2 {
        int value;

        public Date_2(int year, int month, int day) {
            this.value = year * 512 + month * 32 + day;
        }

        public int getYear() {
            return value / 512;
        }

        public int getMonth() {
            return value / 32 % 16;
        }

        public int getDay() {
            return value % 32;
        }

        @Override
        public String toString() {
            return String.format("%d/%d/%d", getMonth(), getDay(), getYear());
        }

        public boolean equals(Object x) {
            if (this == x) return true;
            if (x == null) return false;
            if (this.getClass() != x.getClass()) return false;
            Date_2 that = (Date_2) x;
            if (this.getYear() != that.getYear()
                    || this.getMonth() != that.getMonth()
                    || this.getDay() != that.getDay()) return false;
            return true;
        }
    }
}
