public class LibraryMember {


    static class BrokenLibraryMember {

        static String name;
        static String memberId;
        static int booksIssued;

        BrokenLibraryMember(
            String name,
            String memberId,
            int booksIssued
        ) {
            BrokenLibraryMember.name = name;
            BrokenLibraryMember.memberId = memberId;
            BrokenLibraryMember.booksIssued = booksIssued;
        }
    }

    /*
     * FIXED VERSION
     *
     * name, memberId and booksIssued are instance fields because
     * every member needs separate values.
     *
     * libraryName and memberCount are static because they are
     * shared by the whole library.
     */

    static class FixedLibraryMember {

        String name;
        String memberId;
        int booksIssued;

        static String libraryName = "SRM Library";
        static int memberCount = 0;

        FixedLibraryMember(
            String name,
            int booksIssued
        ) {

            this.name = name;
            this.booksIssued = booksIssued;

            memberCount++;

            this.memberId =
                "LM-" +
                (1000 + memberCount);
        }

        void printMemberCard() {

            System.out.println(
                name + " | " + memberId
            );
        }

        static void printTotalMembers() {

            System.out.println(
                "Total members: " +
                memberCount
            );
        }
    }

    public static void main(String[] args) {

        System.out.println("Broken version:");

        BrokenLibraryMember member1 =
            new BrokenLibraryMember(
                "Aditi",
                "LM-1001",
                2
            );

        BrokenLibraryMember member2 =
            new BrokenLibraryMember(
                "Rohan",
                "LM-1002",
                3
            );

        System.out.println(
            BrokenLibraryMember.name
        );

        System.out.println(
            BrokenLibraryMember.name
        );

        System.out.println(
            "(Aditi's data was overwritten — both members now show Rohan)"
        );

        System.out.println();

        System.out.println("Fixed version:");

        FixedLibraryMember fixed1 =
            new FixedLibraryMember(
                "Aditi",
                2
            );

        FixedLibraryMember fixed2 =
            new FixedLibraryMember(
                "Rohan",
                3
            );

        fixed1.printMemberCard();
        fixed2.printMemberCard();

        FixedLibraryMember.printTotalMembers();
    }
}