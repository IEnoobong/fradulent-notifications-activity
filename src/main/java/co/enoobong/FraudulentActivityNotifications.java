package co.enoobong;

class FraudulentActivityNotifications {

  private static final int MAX_EXPENDITURE = 200;

  private FraudulentActivityNotifications() {
  }

  static int calculateNumberOfNotificationsSent(int[] expenditures, int d) {
    int ans = 0;
    final int[] histogram = new int[MAX_EXPENDITURE + 1];

    for (int i = 0; i < d; i++) {
      histogram[expenditures[i]] = histogram[expenditures[i]] + 1;
    }

    for (int i = d; i < expenditures.length; i++) {
      int cursor = 0;
      final int currentAmount = expenditures[i];
      double median = 0;
      int left = -1;
      int right;
      for (int e = 0; e <= MAX_EXPENDITURE; e++) {
        cursor += histogram[e];
        if (d % 2 == 1) {
          // Odd -> Pick middle one for median
          if (cursor >= d / 2 + 1) {
            median = e;
            break;
          }
        } else {
          // Even -> Pick average of two middle values for median
          if (cursor == d / 2) {
            left = e;
          }

          if (cursor > d / 2 && left != -1) {
            right = e;
            median = (left + right) / 2.0;
            break;
          }

          if (cursor > d / 2 && left == -1) {
            median = e;
            break;
          }
        }
      }

      if (currentAmount >= 2 * median) {
        ans++;
      }

      // Update histogram
      histogram[expenditures[i - d]] = histogram[expenditures[i - d]] - 1;
      histogram[expenditures[i]] = histogram[expenditures[i]] + 1;
    }

    return ans;
  }
}
