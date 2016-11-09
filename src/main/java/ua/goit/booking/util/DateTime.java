package ua.goit.booking.util;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author <a href="mailto:info@olegorlov.com">Oleg Orlov</a>
 */
public final class DateTime {

  public static DateTime getInstance() {
    return new DateTime();
  }

  public static DateTime getInstance(final Calendar calendar) {
    return new DateTime(calendar);
  }

  public static DateTime getInstance(final Date date) {
    return new DateTime(date);
  }

  public static DateTime getInstance(final TimeZone timeZone) {
    return new DateTime(timeZone);
  }

  public static DateTime getInstance(final Date date, final TimeZone timeZone) {
    return new DateTime(date, timeZone);
  }

  public static Date parse(final String date, final String format) throws ParseException {
    return getInstance().getDateFormat(format).parse(date);
  }

  public static DateTime parse(final TimeZone timeZone, final String date, final String format)
      throws ParseException {
    return getInstance(parse(date, format), timeZone);
  }

  /**
   * @return 20 Июля 2015.
   */
  public static String getDateStamp(final TimeZone timeZone) {
    return getInstance(timeZone).toString("d MMMM yyyy");
  }

  /**
   * @return 2015.
   */
  public static String getYearStamp(final TimeZone timeZone) {
    return getInstance(timeZone).toString("yyyy");
  }

  public static String getPeriod(final Date fromDate, final Date toDate, final TimeZone timeZone) {
    String dateFormat = "d MMMM yyyy";
    return getPeriod(dateFormat, fromDate, toDate, timeZone);
  }

  private static String getPeriod(final String dateFormat,
      final Date fromDate, final Date toDate, final TimeZone timeZone) {
    final String date1 = getInstance(fromDate, timeZone).toString(dateFormat);
    final String date2 = getInstance(toDate, timeZone).toString(dateFormat);

    if (date1.equals(date2)) {
      return date1;
    }

    final String[] pairs1 = date1.split(" ");
    final String[] pairs2 = date2.split(" ");

    final boolean monthsEquals;
    if (pairs1[1].equals(pairs2[1])) {
      monthsEquals = true;
    } else {
      monthsEquals = false;
    }

    final boolean yearsEquals;
    if (pairs1[2].equals(pairs2[2])) {
      yearsEquals = true;
    } else {
      yearsEquals = false;
    }

    if (yearsEquals && monthsEquals) {
      return pairs1[0] + " — " + pairs2[0] + " " + pairs2[1] + " " + pairs2[2];
    }
    if (yearsEquals && !monthsEquals) {
      return pairs1[0] + " " + pairs1[1] + " — " + pairs2[0] + " " + pairs2[1] + " " + pairs2[2];
    }
    return date1 + " — " + date2;
  }

  private static DateFormatSymbols RussianDateFormatSymbols = new DateFormatSymbols() {

    private static final long serialVersionUID = 3245940444527884590L;

    @Override
    public String[] getMonths() {
      return new String[]{"Января", "Февраля", "Марта", "Апреля", "Мая", "Июня",
          "Июля", "Августа", "Сентября", "Октября", "Ноября", "Декабря"};
    }
  };

  private final Calendar calendar;

  private DateTime(final TimeZone timeZone) {
    super();
    this.calendar = Calendar.getInstance(timeZone);
  }
  
  private DateTime() {
    this(TimeZone.getDefault());
  }

  private DateTime(final Date date, final TimeZone timeZone) {
    this(timeZone);
    this.calendar.setTime(date);
  }
  
  private DateTime(final Date date) {
    this(date, TimeZone.getDefault());
  }

  private DateTime(final Calendar calendar) {
    this(calendar, calendar.getTimeZone());
  }

  private DateTime(final Calendar calendar, final TimeZone timeZone) {
    this(calendar.getTime(), timeZone);
  }

  public Date getDate() {
    return calendar.getTime();
  }

  public Calendar getCalendar() {
    return calendar;
  }

  public TimeZone getTimeZone() {
    return calendar.getTimeZone();
  }

  public String toString(final String dateFormat) {
    return toString(dateFormat, RussianDateFormatSymbols);
  }

  public String toString(final String dateFormat, final DateFormatSymbols dateFormatSymbols) {
    return getDateFormat(dateFormat, dateFormatSymbols).format(calendar.getTime());
  }

  private SimpleDateFormat getDateFormat(final String dateFormat) {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
    simpleDateFormat.setTimeZone(getTimeZone());
    return simpleDateFormat;
  }

  private SimpleDateFormat getDateFormat(final String dateFormat,
      final DateFormatSymbols dateFormatSymbols) {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, dateFormatSymbols);
    simpleDateFormat.setTimeZone(getTimeZone());
    return simpleDateFormat;
  }

  public DateTime getTodaysDate() {
    return getInstance(calendar);
  }

  /**
   * @return DateTime with start of day.
   */
  public DateTime getStartOfDay() {
    final DateTime dateTime = getTodaysDate();
    final Calendar calendar = dateTime.getCalendar();
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    return dateTime;
  }

  /**
   * @return DateTime with end of day.
   */
  public DateTime getEndOfDay() {
    final DateTime dateTime = getStartOfDay();
    final Calendar calendar = dateTime.getCalendar();
    calendar.add(Calendar.DATE, 1);
    calendar.add(Calendar.MILLISECOND, -1);
    return dateTime;
  }

  public DateTime plusMillisecond(final int millisecond) {
    return addMillisecond(millisecond);
  }

  public DateTime minusMillisecond(final int millisecond) {
    return addMillisecond(-millisecond);
  }

  private DateTime addMillisecond(final int millisecond) {
    calendar.add(Calendar.MILLISECOND, millisecond);
    return this;
  }

  public DateTime plusSecond(final int second) {
    return addSecond(second);
  }

  public DateTime minusSecond(final int second) {
    return addSecond(-second);
  }

  private DateTime addSecond(final int second) {
    calendar.add(Calendar.SECOND, second);
    return this;
  }

  public DateTime plusMinute(final int minute) {
    return addMinute(minute);
  }

  public DateTime minusMinute(final int minute) {
    return addMinute(-minute);
  }

  private DateTime addMinute(final int minute) {
    calendar.add(Calendar.MINUTE, minute);
    return this;
  }

  public DateTime plusHour(final int hour) {
    return addHour(hour);
  }

  public DateTime minusHour(final int hour) {
    return addHour(-hour);
  }

  private DateTime addHour(final int hour) {
    calendar.add(Calendar.HOUR_OF_DAY, hour);
    return this;
  }

  public DateTime plusDays(final int days) {
    return addDate(days);
  }

  public DateTime minusDays(final int days) {
    return addDate(-days);
  }

  private DateTime addDate(final int days) {
    calendar.add(Calendar.DATE, days);
    return this;
  }

  public DateTime plusMonth(final int month) {
    return addMonth(month);
  }

  public DateTime minusMonth(final int month) {
    return addMonth(-month);
  }

  private DateTime addMonth(final int month) {
    calendar.add(Calendar.MONTH, month);
    return this;
  }

  public DateTime plusYear(final int year) {
    return addYear(year);
  }

  public DateTime minusYear(final int year) {
    return addYear(-year);
  }

  private DateTime addYear(final int year) {
    calendar.add(Calendar.YEAR, year);
    return this;
  }

  public DateTime getYesterdaysDate() {
    return getTodaysDate().minusDays(1);
  }

  public DateTime getDateWeekAgo() {
    return getTodaysDate().minusDays(6);
  }

  public DateTime getDateMonthAgo() {
    return getTodaysDate().minusMonth(1);
  }

  public DateTime getDateQuarterAgo() {
    return getTodaysDate().minusMonth(3);
  }

  public DateTime getDateYearAgo() {
    return getTodaysDate().minusYear(1);
  }

}
