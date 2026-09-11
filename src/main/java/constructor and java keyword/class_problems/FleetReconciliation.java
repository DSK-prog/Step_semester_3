public class FleetReconciliation {

    static class BusTicketAccount {

        String bookingId;
        double ticketFare;

        static String depotName;

        static {
            depotName = "SRM Depot";
        }

        BusTicketAccount(
            String bookingId,
            double ticketFare
        ) {

            this.bookingId = bookingId;
            this.ticketFare = ticketFare;
        }

        BusTicketAccount(String bookingId) {

            this(bookingId, 0.0);
        }

        final double calculatePenalty(
            int minutesLate
        ) {

            if (minutesLate < 0) {
                throw new IllegalArgumentException(
                    "Minutes late cannot be negative"
                );
            }

            return minutesLate * 10.0;
        }
    }

    static class SleeperAccount
        extends BusTicketAccount {

        SleeperAccount(
            String bookingId,
            double ticketFare
        ) {

            super(bookingId, ticketFare);
        }

        SleeperAccount(String bookingId) {

            super(bookingId);
        }
    }

    static int processed;
    static int nullSkipped;
    static int sleeperCount;
    static int regularCount;
    static double grandTotalPenalty;

    void processAccount(
        BusTicketAccount account,
        double amount,
        int minutesLate
    ) {

        if (account == null) {
            nullSkipped++;
            return;
        }

        double penalty =
            account.calculatePenalty(minutesLate);

        if (account instanceof SleeperAccount) {

            sleeperCount++;

        } else {

            regularCount++;
        }

        processed++;
        grandTotalPenalty += penalty;
    }

    static void processBatch(
        BusTicketAccount[] accounts,
        double[] amounts,
        int[] minutesLateArray
    ) {

        if (accounts == null ||
            amounts == null ||
            minutesLateArray == null) {

            throw new IllegalArgumentException(
                "Arrays cannot be null"
            );
        }

        int limit =
            Math.min(
                accounts.length,
                Math.min(
                    amounts.length,
                    minutesLateArray.length
                )
            );

        FleetReconciliation processor =
            new FleetReconciliation();

        for (int i = 0; i < limit; i++) {

            processor.processAccount(
                accounts[i],
                amounts[i],
                minutesLateArray[i]
            );
        }

        System.out.println(
            processed +
            " processed | " +
            nullSkipped +
            " null skipped | " +
            sleeperCount +
            " sleeper | " +
            regularCount +
            " regular"
        );

        System.out.println(
            "Grand total penalties = Rs " +
            grandTotalPenalty
        );
    }

    public static void main(String[] args) {

        BusTicketAccount[] accounts = {

            new SleeperAccount(
                "BK001",
                2000
            ),

            null,

            new BusTicketAccount(
                "BK002",
                1200
            )
        };

        double[] amounts = {
            1200,
            900,
            700
        };

        int[] minutesLateArray = {
            10,
            5,
            0
        };

        processBatch(
            accounts,
            amounts,
            minutesLateArray
        );
    }
}