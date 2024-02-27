import java.util.*;
import java.io.*;

class FileSystem{
    // baseVersion to store the base version of the file and made it private so no others can have access
    private String baseVersion;
    // this map is used to store the different deltas/versions
    // In this map Integer holds the version number and String holds the changes or deltas addes etc..
    // so that when we give version number we can get the changes made or any other.
    private Map<Integer, String> deltas;

    //parameterized constructore to intialize the above variables
    FileSystem(String content){
        baseVersion=content;
        deltas = new HashMap<>();
    }
    //this method is used to add the deltas to the deltas Hashmap
    public void addDelta(int versionNumber, String delta) {
        deltas.put(versionNumber, delta);
    }
    //getContentByVersion method helps us to get the deltas for the given version number
    public String getContentByVersion(int version){
        StringBuilder content = new StringBuilder(baseVersion);
        for (int i=1; i<=version; i++) {
            if (deltas.containsKey(i)) {
                applyDelta(content, deltas.get(i));
            } else {
                break; // No more deltas for this version
            }
        }
        return content.toString();
    }
    //here we use addDelta to update the old version of delta with the new version of the delta so that went a version number that is not present is given we can return the last updated version
    private void applyDelta(StringBuilder content, String text) {
        // Apply delta to the base content
        content.replace(0, content.length(), text);
    }
}

public class DynamicversionStorage {
    public static void main(String[] args) {

        //to take input from the user
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter base content: ");
        //Taking the base version content from the user
        String baseContent = scanner.nextLine();
        //creating the fileSystem class object and baseContent is passed as a parameter
        FileSystem fileSystem = new FileSystem(baseContent);
        int versionNumber =1;
        // taking deltas from user till user enters 0;
        while(true){
            System.out.print("Enter delta for version " + versionNumber + "(enter 0 to exit ): ");
            String delta = scanner.nextLine();
            if(delta.equals("0")){
                break;
            }
            //adding deltas to hashmap
            fileSystem.addDelta(versionNumber, delta);
            versionNumber+=1;
        }

        // Retrieve content for specific versions
            System.out.println("Base Version: " + fileSystem.getContentByVersion(0));

            while(true){
            System.out.print("Enter version number between 1 and "+ (versionNumber==1?1 : (versionNumber-1)) +" to retrieve content (0 to exit): ");
            int versionToRetrieve=scanner.nextInt();
            if(versionToRetrieve==0){
                System.out.println("Exiting Program!!  GoodBye! ");
                break;
            } else if(versionToRetrieve>=versionNumber){
                System.out.println("Version not available!!! Pls enter a valid Version Number");
                continue;
            }

            System.out.println("Content for Version " + versionToRetrieve + ": " +fileSystem.getContentByVersion(versionToRetrieve));
        }
    }
}
