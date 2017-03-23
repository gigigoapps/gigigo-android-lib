/*
 * Created by Gigigo Android Team
 *
 * Copyright (C) 2016 Gigigo Mobile Services SL
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gigigo.ggglib.utils;

import java.util.Calendar;
import java.util.Date;
import org.junit.Test;

import static com.gigigo.ggglib.utils.matchers.IsDateEqualTo.isDateEqualTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class DateUtilsTest {

  private Date getCalendar(int year, int month, int day, int hour, int minute, int second) {
    Calendar calendar = Calendar.getInstance();
    calendar.set(year, month, day, hour, minute, second);
    return calendar.getTime();
  }

  @Test public void testStringToDateWithFormatGoodDate() throws Exception {
    String stringDate = "2013-09-29T18:46:19Z";

    Date date = DateUtils.stringToDateWithFormat(stringDate, DateFormatConstants.DATE_FORMAT_TIME);

    Date expectedDate = getCalendar(2013, Calendar.SEPTEMBER, 29, 18, 46, 19);

    assertThat(expectedDate, isDateEqualTo(date));
  }

  @Test public void testStringToDateWithFormatNotEquals() throws Exception {
    String stringDate = "2013-09-29T18:46:19Z";

    Date date = DateUtils.stringToDateWithFormat(stringDate, DateFormatConstants.DATE_FORMAT_TIME);

    Date expectedDate = getCalendar(2000, Calendar.FEBRUARY, 10, 12, 12, 12);

    assertThat(expectedDate, not(isDateEqualTo(date)));
  }

  @Test public void testStringToDateWithFormatWrongDate() throws Exception {
    String stringDate = "Valor Fake";

    Date date = DateUtils.stringToDateWithFormat(stringDate, DateFormatConstants.DATE_FORMAT_TIME);

    Date expectedDate = new Date(0);

    assertThat(expectedDate, isDateEqualTo(date));
  }

  @Test public void testStringToDateWithFormatNullDate() throws Exception {
    Date date = DateUtils.stringToDateWithFormat(null, DateFormatConstants.DATE_FORMAT_TIME);
    assertNotNull(date);
  }

  @Test public void testDateToStringWithFormatNullDate() throws Exception {
    String date = DateUtils.dateToStringWithFormat(null, DateFormatConstants.DATE_FORMAT_TIME);
    assertNull(date);
  }

  @Test public void testDateToStringWithFormatOk() throws Exception {
    Date date = getCalendar(2013, Calendar.SEPTEMBER, 29, 18, 46, 19);

    String expectedDate =
        DateUtils.dateToStringWithFormat(date, DateFormatConstants.DATE_FORMAT_TIME);

    assertEquals("2013-09-29T18:46:19Z", expectedDate);
  }

  @Test public void testDateToStringWithFormatNoTimeOk() throws Exception {
    Date date = getCalendar(2013, Calendar.SEPTEMBER, 29, 18, 46, 19);

    String expectedDate =
        DateUtils.dateToStringWithFormat(date, DateFormatConstants.DATE_FORMAT_NO_TIME);

    assertEquals("2013-09-29", expectedDate);
  }

  @Test public void testStringToDateWithFormatNoTimeGoodDate() throws Exception {
    String stringDate = "2013-09-29";

    Date date =
        DateUtils.stringToDateWithFormat(stringDate, DateFormatConstants.DATE_FORMAT_NO_TIME);

    Date expectedDate = getCalendar(2013, Calendar.SEPTEMBER, 29, 0, 0, 0);

    assertThat(expectedDate, isDateEqualTo(date));
  }
}
