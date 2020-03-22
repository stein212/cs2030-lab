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
        try {
            return map.get(studentName)
                .get(moduleCode)
                .get(assessmentName)
                .getGrade();
        } catch (NullPointerException e) {
            throw new NoSuchRecordException(
                    String.format(
                        "No such record: %s %s %s",
                        studentName,
                        moduleCode,
                        assessmentName));
        }
    }
}
