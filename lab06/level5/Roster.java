public class Roster extends KeyableMap<String, String, Student> {
    public Roster(String name) {
        super(name);
    }

    @Override
    public Roster put(Student student) {
        super.put(student);
        return this;
    }

    public String getGrade(
            String studentName,
            String moduleCode,
            String assessmentName) throws NoSuchRecordException {

        return get(studentName)
            .flatMap(student -> student.get(moduleCode))
            .flatMap(module -> module.get(assessmentName))
            .map(assessment -> assessment.getGrade())
            .orElseThrow(() -> new NoSuchRecordException(String.format(
                        "No such record: %s %s %s",
                        studentName,
                        moduleCode,
                        assessmentName)));
    }
}
