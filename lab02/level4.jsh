/open Loader.java
/open Cruise.java
/open SmallCruise.java
/open BigCruise.java
Cruise b = new BigCruise("B0001", 0, 70, 3000)
b.getArrivalTime()
b.getServiceCompletionTime()
b.getNumOfLoadersRequired()
new Loader(1).serve(b).serve(b)
new Loader(2).serve(b)
new Loader(3).serve(b)
new Loader(4).serve(new BigCruise("B2345", 0, 30, 1450)).serve(new SmallCruise("S0000", 29))
new Loader(5).serve(new BigCruise("B3456", 0, 75, 1510)).serve(new SmallCruise("S0001", 30))
/exit
