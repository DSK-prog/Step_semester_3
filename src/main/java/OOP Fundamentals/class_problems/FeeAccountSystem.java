public class FeeAccountSystem {

    static class FeeAccount {

        private String regNo;
        private double totalFee;
        private double amountPaid;

        FeeAccount(String regNo, double totalFee) {
            this.regNo = regNo;
            this.totalFee = totalFee;
            this.amountPaid = 0;
        }

        void pay(double amount) {

            if (amount <= 0) {
                System.out.println("Invalid payment amount.");
                return;
            }

            amountPaid += amount;
        }

        double getDue() {
            return totalFee - amountPaid;
        }
    }

    static class HostelFeeAccount extends FeeAccount {

        HostelFeeAccount(String regNo, double totalFee) {
            super(regNo, totalFee);
        }

        void payInTwoInstallments(double amount) {
            pay(amount);
            pay(amount);
        }
    }

    static class ScholarshipFeeAccount extends FeeAccount {

        private double scholarshipPercent;

        ScholarshipFeeAccount(
            String regNo,
            double totalFee,
            double scholarshipPercent
        ) {
            super(regNo, totalFee);
            this.scholarshipPercent = scholarshipPercent;
        }

        double effectiveDue() {

            double due = getDue();

            return due - (due * scholarshipPercent / 100);
        }
    }

    public static void main(String[] args) {

        FeeAccount normal =
            new FeeAccount("RA01", 150000);

        normal.pay(150000);

        HostelFeeAccount hostel =
            new HostelFeeAccount("RA02", 200000);

        hostel.payInTwoInstallments(30000);

        ScholarshipFeeAccount scholarship =
            new ScholarshipFeeAccount("RA03", 180000, 20);

        System.out.println(
            "Normal Account Due: " +
            normal.getDue()
        );

        System.out.println(
            "Hostel Account Due: " +
            hostel.getDue()
        );

        System.out.println(
            "Scholarship Effective Due: " +
            scholarship.effectiveDue()
        );

        if (hostel instanceof HostelFeeAccount) {
            System.out.println(
                "Hostel account supports installments."
            );
        }

        if (scholarship instanceof ScholarshipFeeAccount) {
            System.out.println(
                "Scholarship account supports scholarship."
            );
        }
    }
}