public class PatientVitals {

    private double[] readings;
    private int count;

    public PatientVitals(double[] initialReadings) {

        readings = new double[500];
        count = 0;

        for (int i = 0; i < initialReadings.length; i++) {
            recordReading(initialReadings[i]);
        }
    }

    public void recordReading(double reading) {

        if (reading <= 0 || reading > 45) {
            return;
        }

        if (count < readings.length) {
            readings[count] = reading;
            count++;
        }
    }

    public double getAverage() {

        if (count == 0) {
            return 0;
        }

        double sum = 0;

        for (int i = 0; i < count; i++) {
            sum = sum + readings[i];
        }

        return sum / count;
    }

    public double[] getAllReadings() {

        double[] result = new double[count];

        for (int i = 0; i < count; i++) {
            result[i] = readings[i];
        }

        return result;
    }

    public static void main(String[] args) {

        PatientVitals v = new PatientVitals(
            new double[]{36.5, -2, 37.1}
        );

        double[] values = v.getAllReadings();

        for (int i = 0; i < values.length; i++) {
            System.out.println(values[i]);
        }

        values[0] = 999;

        System.out.println("After changing copy:");

        double[] newValues = v.getAllReadings();

        for (int i = 0; i < newValues.length; i++) {
            System.out.println(newValues[i]);
        }
    }
}