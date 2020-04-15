   import java.util.List;
   enum ContactNature { CASUAL, CLOSE, FAMILY }
   class ConfirmedCase {
       private final int id;
       private final List<ConfirmedCase> contacts;
       private final ContactNature nature;
   }
   class ImportedCase extend ConfirmedCase {
       private final String country;
   }
   class LocalCase extend ConfirmedCase {
   }
   class Cluster {
       private final String name;
       private final List<ConfirmedCase> cases;
   }
