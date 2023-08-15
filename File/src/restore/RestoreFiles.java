package restore;

import java.io.File;

public class RestoreFiles {

    public static void main(String[] args) {
        // 대상 디렉토리 지정 (예: "C:/Users/YourName/Documents")
        String targetDirectory = "H:\\downlo";
        
        restoreFiles(targetDirectory);
    }

    public static void restoreFiles(String directoryPath) {
        File directory = new File(directoryPath);

        if (!directory.isDirectory()) {
            System.out.println("Specified path is not a directory.");
            return;
        }

        File[] subDirectories = directory.listFiles(File::isDirectory);

        for (File subDirectory : subDirectories) {
            File[] filesInSubDirectory = subDirectory.listFiles();

            for (File file : filesInSubDirectory) {
                file.renameTo(new File(directory + "/" + file.getName()));
            }

            subDirectory.delete();
        }
    }
}