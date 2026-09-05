public class FeeHostelManagement {

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
                System.out.println("Payment rejected.");
                return;
            }

            if (amount > getDue()) {
                System.out.println("Payment rejected.");
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

    static class HostelRoom {

        String roomNo;
        int beds;
        int occupied;

        HostelRoom(String roomNo, int beds, int occupied) {
            this.roomNo = roomNo;
            this.beds = beds;
            this.occupied = occupied;
        }

        void allot(String name) {

            if (occupied < beds) {
                occupied++;
            }
        }

        static HostelRoom findAvailableRoom(
            HostelRoom[] rooms
        ) {

            for (HostelRoom room : rooms) {

                if (room.occupied < room.beds) {
                    return room;
                }
            }

            return null;
        }

        static HostelRoom safeAllot(
            HostelRoom[] rooms,
            String studentName
        ) {

            HostelRoom room =
                findAvailableRoom(rooms);

            if (room == null) {

                System.out.println(
                    "No rooms available for " +
                    studentName
                );

                return null;
            }

            room.allot(studentName);

            return room;
        }
    }

    static class SrmStudent {

        String name;
        String regNo;

        HostelFeeAccount feeAccount;
        HostelRoom room;

        static int totalStudents = 0;

        SrmStudent(
            String name,
            String regNo,
            double totalFee
        ) {

            this.name = name;
            this.regNo = regNo;

            feeAccount =
                new HostelFeeAccount(
                    regNo,
                    totalFee
                );

            totalStudents++;
        }

        void fullStatus() {

            System.out.println("Name: " + name);
            System.out.println("Register No: " + regNo);

            System.out.println(
                "Fee Due: " +
                feeAccount.getDue()
            );

            if (room == null) {
                System.out.println("Room: unallotted");
            } else {
                System.out.println(
                    "Room: " + room.roomNo
                );
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {

        HostelRoom[] rooms = {
            new HostelRoom("C-214", 1, 0),
            new HostelRoom("C-507", 1, 0)
        };

        SrmStudent ravi =
            new SrmStudent(
                "Ravi",
                "RA01",
                200000
            );

        SrmStudent anitha =
            new SrmStudent(
                "Anitha",
                "RA02",
                200000
            );

        SrmStudent karthik =
            new SrmStudent(
                "Karthik",
                "RA03",
                200000
            );

        ravi.room =
            HostelRoom.safeAllot(
                rooms,
                ravi.name
            );

        anitha.room =
            HostelRoom.safeAllot(
                rooms,
                anitha.name
            );

        ravi.feeAccount.pay(60000);

        anitha.feeAccount.pay(20000);

        karthik.feeAccount.pay(-5000);

        ravi.fullStatus();
        anitha.fullStatus();
        karthik.fullStatus();

        System.out.println(
            "Total Students: " +
            SrmStudent.totalStudents
        );
    }
}