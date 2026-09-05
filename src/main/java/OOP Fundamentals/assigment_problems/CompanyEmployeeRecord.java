public class CompanyEmployeeRecord {

    static class Employee {

        private int empId;
        private String empName;
        private double salary;

        Employee(
            int empId,
            String empName,
            double salary
        ) {
            this.empId = empId;
            this.empName = empName;
            this.salary = salary;
        }

        double getSalary() {
            return salary;
        }
    }

    static class ManagerEmployee extends Employee {

        private double teamBonus;

        ManagerEmployee(
            int empId,
            String empName,
            double salary,
            double teamBonus
        ) {
            super(empId, empName, salary);
            this.teamBonus = teamBonus;
        }

        double effectiveSalary() {

            return getSalary() + teamBonus;
        }
    }

    static class InternEmployee extends Employee {

        private double stipendCap;

        InternEmployee(
            int empId,
            String empName,
            double salary,
            double stipendCap
        ) {
            super(empId, empName, salary);
            this.stipendCap = stipendCap;
        }

        double effectiveSalary() {

            if (getSalary() < stipendCap) {
                return getSalary();
            }

            return stipendCap;
        }
    }

    static class ParkingSlot {

        String slotNo;
        int capacity;
        int occupiedCount;

        ParkingSlot(
            String slotNo,
            int capacity,
            int occupiedCount
        ) {
            this.slotNo = slotNo;
            this.capacity = capacity;
            this.occupiedCount = occupiedCount;
        }

        void allot() {

            if (occupiedCount < capacity) {
                occupiedCount++;
            }
        }

        static ParkingSlot findAvailableSlot(
            ParkingSlot[] slots
        ) {

            for (ParkingSlot slot : slots) {

                if (slot.occupiedCount < slot.capacity) {
                    return slot;
                }
            }

            return null;
        }

        static ParkingSlot safeAllot(
            ParkingSlot[] slots
        ) {

            ParkingSlot slot =
                findAvailableSlot(slots);

            if (slot == null) {
                return null;
            }

            slot.allot();

            return slot;
        }
    }

    String name;
    String empId;
    Employee employee;
    ParkingSlot slot;

    static int totalRecords = 0;

    CompanyEmployeeRecord(
        String name,
        String empId,
        Employee employee
    ) {
        this.name = name;
        this.empId = empId;
        this.employee = employee;

        totalRecords++;
    }

    String fullProfile() {

        double pay;

        if (employee instanceof ManagerEmployee) {

            ManagerEmployee manager =
                (ManagerEmployee) employee;

            pay = manager.effectiveSalary();

        } else if (employee instanceof InternEmployee) {

            InternEmployee intern =
                (InternEmployee) employee;

            pay = intern.effectiveSalary();

        } else {

            pay = employee.getSalary();
        }

        String slotInfo;

        if (slot == null) {
            slotInfo = "no parking assigned";
        } else {
            slotInfo = slot.slotNo;
        }

        return name +
            " | Pay: Rs " +
            pay +
            " | Slot: " +
            slotInfo;
    }

    public static void main(String[] args) {

        ParkingSlot[] parkingSlots = {
            new ParkingSlot("A1", 1, 0),
            new ParkingSlot("A2", 1, 0)
        };

        ManagerEmployee manager =
            new ManagerEmployee(
                101,
                "Divya",
                70000,
                8000
            );

        Employee employee =
            new Employee(
                102,
                "Karan",
                40000
            );

        InternEmployee intern =
            new InternEmployee(
                103,
                "Meera",
                12000,
                10000
            );

        CompanyEmployeeRecord record1 =
            new CompanyEmployeeRecord(
                "Divya",
                "E101",
                manager
            );

        CompanyEmployeeRecord record2 =
            new CompanyEmployeeRecord(
                "Karan",
                "E102",
                employee
            );

        CompanyEmployeeRecord record3 =
            new CompanyEmployeeRecord(
                "Meera",
                "E103",
                intern
            );

        record1.slot =
            ParkingSlot.safeAllot(parkingSlots);

        record2.slot =
            ParkingSlot.safeAllot(parkingSlots);

        // record3 is intentionally left without a parking slot.

        System.out.println(record1.fullProfile());
        System.out.println(record2.fullProfile());
        System.out.println(record3.fullProfile());

        System.out.println(
            "Total records: " +
            CompanyEmployeeRecord.totalRecords
        );
    }
}