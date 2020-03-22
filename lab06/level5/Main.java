import java.util.Scanner;
import java.util.Optional;

public class Main {
    public static void main(String... args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfRecords = scanner.nextInt();

        Roster roster = new Roster("AY1920");

        for (int i = 0; i < numberOfRecords; i++) {
            String studentName = scanner.next();
            String moduleCode = scanner.next();
            String assessmentName = scanner.next();
            String grade = scanner.next();

            roster.put(roster.get(studentName)
                    .or(() -> Optional.of(new Student(studentName)))
                    .map(s -> s.put(s.get(moduleCode)
                        .or(() -> Optional.of(new Module(moduleCode)))
                        .map(m -> m.put(new Assessment(assessmentName, grade)))
                        .orElseThrow()
                        ))
                    .orElseThrow()
                    );
        }

        while (scanner.hasNext()) {
            String studentName = scanner.next();
            String moduleCode = scanner.next();
            String assessmentName = scanner.next();

            try {
                System.out.println(
                        roster.getGrade(
                            studentName,
                            moduleCode,
                            assessmentName));
            } catch (NoSuchRecordException e) {
                System.out.println(e);
            }
        }
    }
}

