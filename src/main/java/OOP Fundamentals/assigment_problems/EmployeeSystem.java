public class EmployeeSystem {

    static class Employee {

        private int empId;
        private String empName;
        private double salary;

        Employee(int empId, String empName, double salary) {
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

    public static void main(String[] args) {

        Employee plain =
            new Employee(101, "Aditi", 40000);

        ManagerEmployee manager =
            new ManagerEmployee(
                102,
                "Rohan",
                70000,
                8000
            );

        InternEmployee intern =
            new InternEmployee(
                103,
                "Karan",
                12000,
                10000
            );

        if (plain instanceof ManagerEmployee) {

            ManagerEmployee m =
                (ManagerEmployee) plain;

            System.out.println(
                "Manager effective pay: Rs " +
                m.effectiveSalary()
            );

        } else if (plain instanceof InternEmployee) {

            InternEmployee i =
                (InternEmployee) plain;

            System.out.println(
                "Intern effective pay: Rs " +
                i.effectiveSalary()
            );

        } else {

            System.out.println(
                "Plain employee pay: Rs " +
                plain.getSalary()
            );
        }

        if (manager instanceof ManagerEmployee) {

            System.out.println(
                "Manager effective pay: Rs " +
                manager.effectiveSalary()
            );
        }

        if (intern instanceof InternEmployee) {

            System.out.println(
                "Intern effective pay: Rs " +
                intern.effectiveSalary()
            );
        }
    }
}