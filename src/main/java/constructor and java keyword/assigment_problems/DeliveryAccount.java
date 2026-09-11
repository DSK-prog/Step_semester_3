public class DeliveryAccount {
    private static int totalAccountsProcessed;

    static {
        totalAccountsProcessed = 0;
    }

    protected String studentId;
    protected double orderValue;

    public DeliveryAccount(String studentId, double orderValue) {
        this.studentId = studentId;
        this.orderValue = orderValue;
    }

    public DeliveryAccount(String studentId) {
        this(studentId, 0.0);
    }

    public final double calculateSurgeFee(int delayMinutes) {
        if (delayMinutes < 0) {
            throw new IllegalArgumentException("Invalid delay");
        }

        if (delayMinutes == 0) {
            return 0.0;
        }

        double rate = 0.01;

        return orderValue * rate * delayMinutes;
    }

    public void processAccount(DeliveryAccount account, double amount, int delayMinutes) {
        if (account == null) {
            return;
        }

        totalAccountsProcessed++;

        double surgeFee = account.calculateSurgeFee(delayMinutes);

        if (account instanceof Premium) {
            System.out.println("Premium account: " + account.studentId);
        } else {
            System.out.println("Regular account: " + account.studentId);
        }

        System.out.println("Surge fee: " + surgeFee);
    }

    public static void processBatch(
            DeliveryAccount[] accounts,
            double[] amounts,
            int[] delayMinutesArray) {

        int processed = 0;
        int nullSkipped = 0;
        int premiumCount = 0;
        int regularCount = 0;
        double grandTotal = 0.0;

        int length = Math.min(accounts.length,
                Math.min(amounts.length, delayMinutesArray.length));

        DeliveryAccount processor = new DeliveryAccount("PROCESSOR", 0);

        for (int i = 0; i < length; i++) {
            if (accounts[i] == null) {
                nullSkipped++;
                continue;
            }

            double surgeFee = accounts[i].calculateSurgeFee(delayMinutesArray[i]);
            grandTotal += surgeFee;
            processed++;

            if (accounts[i] instanceof Premium) {
                premiumCount++;
            } else {
                regularCount++;
            }
        }

        System.out.println(processed + " processed | "
                + nullSkipped + " null skipped | "
                + premiumCount + " premium | "
                + regularCount + " regular | "
                + "grand total surge fees = " + grandTotal);
    }
}

class Premium extends DeliveryAccount {
    public Premium(String studentId, double orderValue) {
        super(studentId, orderValue);
    }
}