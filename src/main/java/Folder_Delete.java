import java.io.*;
import java.util.*;
import java.nio.file.Files;

public class Folder_Delete {
  //-----------------------------------------------------------------------------------------------------------------------
  private static String SOURCE_BASE_FOLDER = "."; //by default, process current directory
  private static List<String> sourceFileList = new ArrayList<String>();
  private static Map<String, List<String>> propertiesMap = null;
  private static List<String> skippedExtentionList = null;
  private static List<String> repoFlagList = null;
  private static boolean repoFlag = false;
  private static boolean forceFlag = false;
  
  private static String repoDir = "";
  //-----------------------------------------------------------------------------------------------------------------------
  public static void main(String[] args) throws Exception {
    List<String> argList = new ArrayList<String>();
    Collections.addAll(argList, args);

    if(argList != null && argList.size() >= 1 ) {
        SOURCE_BASE_FOLDER = argList.get(0);
    }

    if(SOURCE_BASE_FOLDER.endsWith("\\")) {
        SOURCE_BASE_FOLDER = SOURCE_BASE_FOLDER.substring(0,SOURCE_BASE_FOLDER.length()-1);
    }
    //System.out.println("SOURCE_BASE_FOLDER ='"+SOURCE_BASE_FOLDER+"'");
    File sourceFolder = new File(SOURCE_BASE_FOLDER);
    if(!sourceFolder.exists()) {
        System.out.println("Folder "+SOURCE_BASE_FOLDER + " does not exist. Exiting");
        System.exit(-1);
    }

    sourceFileList = FileUtil.getFileListFromFolder(SOURCE_BASE_FOLDER);
    String propertyFilePath = Util.locatePropertiesFile();
    propertiesMap = PropertiesLoader.loadToHashMap(propertyFilePath);

    Properties prop = PropertiesLoader.load(propertyFilePath);

    for(int i=0;i<sourceFileList.size();i++) {
        String fileName = sourceFileList.get(i);
		//------------------------------------------------------------
        File file = new File(fileName);
        if(!file.exists()) {
			System.out.println("FILE_NOT_FOUND:"+fileName);
            continue;
        } else {
            try {
                file.delete();
                System.out.println("DELETE:"+fileName);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
  }
  //-----------------------------------------------------------------------------------------------------------------------

  //-----------------------------------------------------------------------------------------------------------------------
}
