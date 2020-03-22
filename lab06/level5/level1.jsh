/open Keyable.java
/open KeyableMap.java
class CallerID implements Keyable<Integer> { 
  int number; 
  String name;
  CallerID(int number, String name) { this.number = number; this.name = name; }
  @Override public Integer getKey() { return this.number; }
  @Override public String toString() { return this.number + ": " + this.name; }
}
KeyableMap<String,Integer,CallerID> map = new KeyableMap<>("phonebook")
map.put(new CallerID(65164463, "OWT")).put(new CallerID(66752378, "Blocked"));
map.get(65164463)
map.get(66752378)
map.get(65432100)
