public class Assessment implements Keyable<String> {
    private final String name;
    private final String grade;

    public Assessment(String name, String grade) {
        this.name = name;
        this.grade = grade;
    }

    public String getGrade() {
        return grade;
    }

    @Override
    public String getKey() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("{%s: %s}", name, grade);
    }
}
