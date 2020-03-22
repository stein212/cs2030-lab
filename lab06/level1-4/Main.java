import java.util.Scanner;

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

            Student student = roster.get(studentName);
            
            if (student == null) {
                student = new Student(studentName);
                roster.put(student);
            }

            Module module = student.get(moduleCode);

            if (module == null) {
                module = new Module(moduleCode);
                student.put(module);
            }

            module.put(new Assessment(assessmentName, grade));
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

