package notepad;

import java.io.*;
import java.nio.file.*;


public class FileManager {

    private File currentFile = null;

    public String readFile(File file) throws IOException {
        return Files.readString(file.toPath());
    }

    public void writeFile(File file, String content) throws IOException {
        Files.writeString(file.toPath(), content);
        this.currentFile = file;
    }

   
    public File ensureExtension(File file) {
        if (!file.getName().contains(".")) {
            return new File(file.getPath() + ".txt");
        }
        return file;
    }

    public File getCurrentFile()          { return currentFile; }
    public void  setCurrentFile(File f)   { this.currentFile = f; }
    public boolean hasCurrentFile()       { return currentFile != null; }
    public String  getCurrentFileName()   { return currentFile != null ? currentFile.getName() : "Untitled"; }
}
