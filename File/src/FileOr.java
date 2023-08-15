import java.io.File;

public class FileOr {

    private static final String[] IMAGE_EXTENSIONS = { "jpg", "jpeg", "png", "gif", "bmp", "webp" };
    private static final String[] VIDEO_EXTENSIONS = { "mp4", "mkv", "flv", "mov", "avi" };
    private static final String[] COMPRESSED_EXTENSIONS = { "zip", "rar", "tar", "gz", "7z" };

    public static void main(String[] args) {
        String targetDirectory = "H:\\downlo";
        organizeFiles(targetDirectory);
    }

    public static void organizeFiles(String directoryPath) {
        File directory = new File(directoryPath);

        if (!directory.isDirectory()) {
            System.out.println("Specified path is not a directory.");
            return;
        }

        File[] files = directory.listFiles();

        for (File file : files) {
            if (file.isFile()) {
                String fileType = getFileType(file);

                if (fileType != null) {
                    File subDirectory = new File(directoryPath + "/" + fileType);
                    if (!subDirectory.exists()) {
                        subDirectory.mkdir();
                    }

                    file.renameTo(new File(subDirectory + "/" + file.getName()));
                }
            }
        }
    }

    public static String getFileType(File file) {
        String extension = getFileExtension(file);

        if (contains(IMAGE_EXTENSIONS, extension)) {
            return "Images";
        } else if (contains(VIDEO_EXTENSIONS, extension)) {
            return "Videos";
        } else if (contains(COMPRESSED_EXTENSIONS, extension)) {
            return "Compressed";
        }

        return null;
    }

    public static String getFileExtension(File file) {
        String fileName = file.getName();
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
            return fileName.substring(dotIndex + 1).toLowerCase();
        }
        return null;
    }

    public static boolean contains(String[] array, String value) {
        for (String s : array) {
            if (s.equals(value)) {
                return true;
            }
        }
        return false;
    }
}