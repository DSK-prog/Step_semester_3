public final class DischargeSummary {

    private final String patientId;
    private final String[] medicationCodes;

    static {
        System.out.println("Nightly ledger initialized");
    }

    public DischargeSummary(String patientId, String[] medicationCodes) {

        for (int i = 0; i < medicationCodes.length; i++) {

            if (!medicationCodes[i].matches("MED-[A-Z]")) {
                throw new IllegalArgumentException("construction rejected");
            }
        }

        this.patientId = patientId;

        this.medicationCodes = new String[medicationCodes.length];

        for (int i = 0; i < medicationCodes.length; i++) {
            this.medicationCodes[i] = medicationCodes[i];
        }
    }

    public String[] getMedicationCodes() {

        String[] copy = new String[medicationCodes.length];

        for (int i = 0; i < medicationCodes.length; i++) {
            copy[i] = medicationCodes[i];
        }

        return copy;
    }

    public DischargeSummary withCorrectedMedication(
            int index, String newCode) {

        if (!newCode.matches("MED-[A-Z]")) {
            throw new IllegalArgumentException("Invalid medication code");
        }

        String[] newCodes = getMedicationCodes();

        newCodes[index] = newCode;

        return new DischargeSummary(patientId, newCodes);
    }

    static String processNightlyBatch(
            DischargeSummary[] summaries) {

        int processed = 0;
        int nullSkipped = 0;
        int criticalCare = 0;
        int routine = 0;

        for (int i = 0; i < summaries.length; i++) {

            if (summaries[i] == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (summaries[i] instanceof CriticalCareDischargeSummary) {
                criticalCare++;
            } else {
                routine++;
            }
        }

        return processed + " processed | "
                + nullSkipped + " null skipped | "
                + criticalCare + " critical-care | "
                + routine + " routine";
    }

    public static void main(String[] args) {

        DischargeSummary d = new DischargeSummary(
            "MT2026-0142",
            new String[]{"MED-A", "MED-B"}
        );

        String[] codes = d.getMedicationCodes();

        codes[0] = "TAMPERED";

        System.out.println(d.getMedicationCodes()[0]);

        DischargeSummary[] summaries = {
            new CriticalCareDischargeSummary(
                "MT001",
                new String[]{"MED-X"},
                4
            ),
            null,
            new DischargeSummary(
                "MT002",
                new String[]{"MED-Y"}
            )
        };

        System.out.println(
            processNightlyBatch(summaries)
        );
    }
}

class CriticalCareDischargeSummary extends DischargeSummary {

    private final int icuDays;

    public CriticalCareDischargeSummary(
            String patientId,
            String[] medicationCodes,
            int icuDays) {

        super(patientId, medicationCodes);
        this.icuDays = icuDays;
    }
}