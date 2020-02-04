/open Cruise.java
/open Loader.java
new Loader(1)
new Loader(1).canServe(new Cruise("A1234", 0, 1, 30))
new Loader(1).serve(new Cruise("A1234", 0, 1, 30))
new Loader(1).serve(new Cruise("A1234", 0, 1, 30)).canServe(new Cruise("A2345", 30, 1, 30))
new Loader(1).serve(new Cruise("A1234", 0, 1, 30)).serve(new Cruise("A2345", 30, 1, 30))
new Loader(1).serve(new Cruise("A1234", 0, 1, 30)).canServe(new Cruise("A2345", 10, 1, 30))
new Loader(1).serve(new Cruise("A1234", 0, 1, 30)).serve(new Cruise("A2345", 10, 1, 30))
Loader loader = new Loader(2)
loader.serve(new Cruise("U8888", 0, 1, 20))
loader.serve(new Cruise("U8888", 1, 1, 20))
loader = new Loader(3)
loader.canServe(null)
loader.serve(null)
/exit
