public class StudentStaticBoundary {

    static class SrmStudent {

        String name;
        String regNo;
        int attendance;

        static String university = "SRM University";
        static int admissionCount = 0;

        SrmStudent(String name) {

            this.name = name;

            admissionCount++;

            this.regNo =
                "RA2311003010" +
                String.format("%02d", admissionCount);

            this.attendance = 0;
        }

        void printIdCard() {

            System.out.println(
                name + " | " + regNo
            );
        }

        static void printTotalAdmissions() {

            System.out.println(
                "Total Admissions: " +
                admissionCount
            );
        }
    }

    public static void main(String[] args) {

        SrmStudent student1 =
            new SrmStudent("Ravi");

        SrmStudent student2 =
            new SrmStudent("Meera");

        student1.printIdCard();
        student2.printIdCard();

        SrmStudent.printTotalAdmissions();
    }
}