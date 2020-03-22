public class Student extends KeyableMap<String, String, Module> {
    public Student(String name) {
        super(name);
    }

    @Override
    public Student put(Module module) {
        super.put(module);
        return this;
    }
}
