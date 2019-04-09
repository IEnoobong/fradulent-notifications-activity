package co.enoobong;

import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class FraudulentActivityNotificationsTest {

  private int[] expenditures;

  private int triggerDays;

  private int exceptedNotificationCount;

  public FraudulentActivityNotificationsTest(
          int[] expenditures, int triggerDays, int exceptedNotificationCount) {
    this.expenditures = expenditures;
    this.triggerDays = triggerDays;
    this.exceptedNotificationCount = exceptedNotificationCount;
  }

  @Parameterized.Parameters(name = "{index}: calculateNumberOfNotificationsSent(expenditures,{1}) = {2}")
  public static Collection<Object[]> data() {
    final Object[][] objects = {
            {new int[]{2, 3, 4, 2, 3, 6, 8, 4, 5}, 4, 2},
            {new int[]{1, 2, 3, 4, 4}, 4, 0},
            {new int[]{10, 20, 30, 40, 50}, 3, 1},
            {new int[]{2, 3, 4, 2, 3, 6, 8, 4, 5}, 5, 2},
    };
    return Arrays.asList(objects);
  }

  @Test
  public void shouldSendNotificationsCorrectNumberOfTimes() {
    final int notificationCount = FraudulentActivityNotifications.calculateNumberOfNotificationsSent(expenditures, triggerDays);
    assertEquals(exceptedNotificationCount, notificationCount);
  }
}
