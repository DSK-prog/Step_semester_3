public class LoanReceipt {

    private final String memberId;
    private final String[] bookIds;

    static {
        System.out.println("Nightly circulation ledger initialized");
    }

    public LoanReceipt(String memberId, String[] bookIds) {

        for (int i = 0; i < bookIds.length; i++) {

            if (!bookIds[i].matches("BK-[0-9]{3}")) {
                throw new IllegalArgumentException("construction rejected");
            }
        }

        this.memberId = memberId;

        this.bookIds = new String[bookIds.length];

        for (int i = 0; i < bookIds.length; i++) {
            this.bookIds[i] = bookIds[i];
        }
    }

    public String[] getBookIds() {

        String[] copy = new String[bookIds.length];

        for (int i = 0; i < bookIds.length; i++) {
            copy[i] = bookIds[i];
        }

        return copy;
    }

    public LoanReceipt withCorrectedBookId(
            int index, String newId) {

        if (!newId.matches("BK-[0-9]{3}")) {
            throw new IllegalArgumentException("Invalid book ID");
        }

        String[] newBookIds = getBookIds();

        newBookIds[index] = newId;

        return new LoanReceipt(memberId, newBookIds);
    }

    static String processNightlyCirculation(
            LoanReceipt[] receipts) {

        int processed = 0;
        int nullSkipped = 0;
        int referenceOnly = 0;
        int regular = 0;

        for (int i = 0; i < receipts.length; i++) {

            if (receipts[i] == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (receipts[i] instanceof ReferenceOnlyLoanReceipt) {
                referenceOnly++;
            } else {
                regular++;
            }
        }

        return processed + " processed | "
                + nullSkipped + " null skipped | "
                + referenceOnly + " reference-only | "
                + regular + " regular";
    }

    public static void main(String[] args) {

        LoanReceipt r = new LoanReceipt(
            "LIB-8841",
            new String[]{"BK-100", "BK-101"}
        );

        String[] ids = r.getBookIds();

        ids[0] = "HACKED";

        System.out.println(r.getBookIds()[0]);

        LoanReceipt[] receipts = {
            new ReferenceOnlyLoanReceipt(
                "LIB-001",
                new String[]{"BK-200"},
                "Reading Room 3"
            ),
            null,
            new LoanReceipt(
                "LIB-002",
                new String[]{"BK-201"}
            )
        };

        System.out.println(
            processNightlyCirculation(receipts)
        );
    }
}

class ReferenceOnlyLoanReceipt extends LoanReceipt {

    private final String roomNumber;

    public ReferenceOnlyLoanReceipt(
            String memberId,
            String[] bookIds,
            String roomNumber) {

        super(memberId, bookIds);
        this.roomNumber = roomNumber;
    }
}