/open Keyable.java
/open KeyableMap.java
/open Assessment.java
/open Module.java
/open Student.java
new Assessment("Lab1", "B")
new Assessment("Lab1", "B").getGrade()
new Assessment("Lab1", "B").getKey()
new Module("CS2040").put(new Assessment("Lab1", "B")).put(new Assessment("Lab2","A+"))
new Module("CS2040").put(new Assessment("Lab1", "B")).put(new Assessment("Lab2","A+")).get( "Lab1")
new Module("CS2040").put(new Assessment("Lab1", "B")).put(new Assessment("Lab2","A+")).get( "Lab2")
new Module("CS2040").put(new Assessment("Lab1", "B")).put(new Assessment("Lab2","A+")).get( "Lab3")
new Module("CS2040").put(new Assessment("Lab1", "B")).put(new Assessment("Lab2","A+")).get( "Lab1").getGrade()
new Student("Tony").put(new Module("CS2040").put(new Assessment("Lab1", "B")))
new Student("Tony").put(new Module("CS2040").put(new Assessment("Lab1", "B"))).get("CS2040" )
new Student("Tony").put(new Module("CS2040").put(new Assessment("Lab1", "B"))).get("CS2040" ).get("Lab1")
new Student("Tony").put(new Module("CS2040").put(new Assessment("Lab1", "B"))).get("CS2040" ).get("Lab1").getGrade()
Student natasha = new Student("Natasha");
natasha.put(new Module("CS2040").put(new Assessment("Lab1", "B")))
natasha.put(new Module("CS2030").put(new Assessment("PE", "A+")).put(new Assessment("Lab2",  "C")))
Student tony = new Student("Tony");
tony.put(new Module("CS1231").put(new Assessment("Test", "A-")))
tony.put(new Module("CS2100").put(new Assessment("Test", "B")).put(new Assessment("Lab1", " F")))
