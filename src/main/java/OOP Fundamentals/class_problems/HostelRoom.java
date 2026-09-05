public class HostelRoom {

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

            System.out.println(
                name + " allotted to room " + roomNo
            );
        }
    }

    static HostelRoom findAvailableRoom(HostelRoom[] rooms) {

        for (HostelRoom room : rooms) {

            if (room.occupied < room.beds) {
                return room;
            }
        }

        return null;
    }

    static void safeAllot(
        HostelRoom[] rooms,
        String studentName
    ) {

        HostelRoom room = findAvailableRoom(rooms);

        if (room == null) {
            System.out.println("No rooms available.");
        } else {
            room.allot(studentName);
        }
    }

    public static void main(String[] args) {

        HostelRoom[] rooms = {
            new HostelRoom("C-214", 3, 2),
            new HostelRoom("C-507", 2, 2)
        };

        safeAllot(rooms, "Divya");

        safeAllot(rooms, "Rahul");
    }
}