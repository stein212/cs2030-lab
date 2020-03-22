/open Keyable.java
/open KeyableMap.java
/open Assessment.java
/open Module.java
/open Student.java
/open Roster.java
/open NoSuchRecordException.java
Student natasha = new Student("Natasha");
natasha.put(new Module("CS2040").put(new Assessment("Lab1", "B")))
natasha.put(new Module("CS2030").put(new Assessment("PE", "A+")).put(new Assessment("Lab2",  "C")))
Student tony = new Student("Tony");
tony.put(new Module("CS1231").put(new Assessment("Test", "A-")))
tony.put(new Module("CS2100").put(new Assessment("Test", "B")).put(new Assessment("Lab1", " F")))
Roster roster = new Roster("AY1920").put(natasha).put(tony)
roster
roster.get("Tony").get("CS1231").get("Test").getGrade()
roster.get("Natasha").get("CS2040").get("Lab1").getGrade()
roster.getGrade("Tony","CS1231","Test")
roster.getGrade("Natasha","CS2040","Lab1")
try {
  roster.getGrade("Steve","CS1010","Lab1");
} catch (NoSuchRecordException e) {
  System.out.println(e.getMessage());
}
try {
  roster.getGrade("Tony","CS1231","Exam");
} catch (NoSuchRecordException e) {
  System.out.println(e.getMessage());
}
