import java.util.*;

class FileVersion{
    // baseVersion to store the base version of the file and made it private so no others can have access
    private String baseVersion;
    // this map is used to store the different deltas/versions
    // In this map Integer holds the version number and String holds the changes or deltas addes etc..
    // so that when we give version number we can get the changes made or any other.
    private Map<Integer, String>deltas;

    //parameterized constructore to intialize the above variables
    FileVersion(String content){
        baseVersion=content;
        deltas=new HashMap<>();
    }
    //this method is used to add the deltas to the deltas Hashmap
    public void addDelta(int versionNumber, String delta) {
        deltas.put(versionNumber, delta);
    }
    //getContentByVersion method helps us to get the deltas for the given version number
    public String getContentByVersion(int version){
        StringBuilder content=new StringBuilder(baseVersion);
        for(int i=1; i<=version; i++){
            if(deltas.containsKey(i)){
                applyDelta(content, deltas.get(i));
            }else{
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

public class VersionedFileStorage {
    public static void main(String[] args) {
        // input is taken statically
        //Adding base content for the file
        FileVersion fileVersion= new FileVersion("Base content");

        // Adding deltas for versions
        fileVersion.addDelta(1, "1st mod changes made");
        fileVersion.addDelta(2, "Version 2 changes Updates and Others");

        // Retrieve content for specific versions
        System.out.println("Version 0: " + fileVersion.getContentByVersion(0));
        System.out.println("Version 1: " + fileVersion.getContentByVersion(1));
        System.out.println("Version 2: " + fileVersion.getContentByVersion(2));
    }
}
