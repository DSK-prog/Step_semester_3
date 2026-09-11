public class FareSplitter {

    String tripId;
    double totalFare;
    int passengerCount;

    public FareSplitter(
        String tripId,
        double totalFare,
        int passengerCount
    ) {

        if (totalFare < 0) {
            throw new IllegalArgumentException(
                "Fare cannot be negative"
            );
        }

        if (passengerCount <= 0) {
            throw new IllegalArgumentException(
                "Passenger count must be positive"
            );
        }

        this.tripId = tripId;
        this.totalFare = totalFare;
        this.passengerCount = passengerCount;
    }

    public FareSplitter(
        String tripId,
        double totalFare
    ) {

        this(tripId, totalFare, 2);
    }

    public FareSplitter(String tripId) {

        this(tripId, 0.0, 2);
    }

    double[] fareBreakdown() {

        double[] result =
            new double[passengerCount];

        if (totalFare == 0) {
            return result;
        }

        double basicShare =
            Math.floor((totalFare / passengerCount) * 100) / 100;

        double totalAssigned =
            basicShare * passengerCount;

        double remainder =
            Math.round((totalFare - totalAssigned) * 100);

        for (int i = 0; i < passengerCount; i++) {

            result[i] = basicShare;
        }

        result[passengerCount - 1] += remainder / 100.0;

        return result;
    }

    boolean isConfirmationOverdue(
        int confirmed,
        int expected
    ) {

        return confirmed < expected;
    }

    public static void main(String[] args) {

        FareSplitter fare =
            new FareSplitter(
                "TRIP001",
                100000,
                3
            );

        double[] breakdown =
            fare.fareBreakdown();

        for (double share : breakdown) {
            System.out.printf("%.2f ", share);
        }

        System.out.println();

        FareSplitter provisional =
            new FareSplitter("TRIP003");

        double[] result =
            provisional.fareBreakdown();

        for (double share : result) {
            System.out.printf("%.1f ", share);
        }

        System.out.println();
    }
}