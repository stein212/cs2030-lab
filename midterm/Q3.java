   import java.util.List;
   enum ContactNature { CASUAL, CLOSE, FAMILY }
   class Case {
       private /*final*/ int id;
       /*private final List<ConfirmedCase> contacts;
       private final ContactNature nature;*/
   }
class Contact{int n; Case f; Case t;}
   class ImportedCase extends Case {
       private /*final*/ String country;
   }
   /*class LocalCase extend ConfirmedCase {
   }*/
   class Cluster {
       private /*final*/ String name;
       private /*final*/ List<Case> cases;
   }
