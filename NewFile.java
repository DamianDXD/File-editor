package files.actions;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NewFile {
    private String path;
    private final String doubleBackslash = "//";

    public NewFile() {
        path = System.getProperty("user.home") + "\\Documents\\EmptyFile"; //default directory
    }

    public NewFile(String path) {
        this.path = path;
    }


    public void createEmptyFile(FileFormat format, String name) throws IOException {
        String n = name + format.getFormat();
        File file = new File(path, n);
        if (file.createNewFile())
            System.out.println("Utworzono nowy pusty plik o nazwie: " + name);
        else
            System.out.println("Plik o podanej nazwie już istnieje");
    }

    public void deleteFile(String name) {
        //if true -  remove extension, compare name of found file to name of file to delete
        //if false - format not recognised, use whole name
        File fileToDelete;
        String str = findFileInDirectory(name);
        if (str.isEmpty()) {
            System.out.println("Brak pliku o podanej nazwie!");
        } else {
            fileToDelete = new File(path + "\\" + str);
            if (fileToDelete.delete())
                System.out.println("Plik został usunięty");
            else
                System.out.println("Plik nie został usunięty");
        }
    }

    public void showFiles() {
        File filesInDirectory = new File(path);
        File[] files = filesInDirectory.listFiles();
        for (File f : Objects.requireNonNull(files))
            System.out.println(f.toString().substring(path.length() + 1));
    }

    public List<String> readFile(String name) throws IOException, EmptyNameOfFileException {
        String str = findFileInDirectory(name);
        if (str.isEmpty()) {throw new EmptyNameOfFileException("Brak nazwy pliku");}

        File file = new File(path + "\\" + str);
        List<String> list = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file));) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                list.add(line);
            }
        }
        return list;
    }

    public void writeToFile(String name, List<String> lines) throws IOException, EmptyNameOfFileException {
        String str = findFileInDirectory(name);
        if (str.isEmpty()) { throw new EmptyNameOfFileException("Brak nazwy pliku");}

        File file = new File(path + "\\" + str);
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(file, true));) {
            for (String l : lines) {
                printWriter.println(l);
            }
        }
        System.out.println("Dane zostały zapisane");
    }

    public void display10Lines(int next10, String name) throws IOException, EmptyNameOfFileException {
        List<String> list = new ArrayList<>(readFile(name));
        for(int i = next10 * 10; (i < list.size()) && (i < next10 * 10 + 10); i++) {
            System.out.println(list.get(i));
        }
        if((next10 * 10 + 10) - list.size() >= 10) System.out.println("Jesteś poza obszarem");
    }

    public void createDir(String name){
        File file = new File(path + "\\" + name);
        if(file.mkdir())
            System.out.println("Folder został stworzony");
        else
            System.out.println("Folder nie został stworzony");
    }

    public void deleteDir(String name){
        File file = new File(path + "\\" + name);
        if(file.delete())
            System.out.println("Folder został usunięty");
        else
            System.out.println("Folder nie został usunięty");
    }
    private String findFileInDirectory(String nameFile) {
        File filesInDirectory = new File(path);
        File[] files = filesInDirectory.listFiles();
        for (File f : Objects.requireNonNull(files)) {
            String nameOfFile = f.toString().substring(path.length() + 1);
            int lastDotIndex = nameOfFile.lastIndexOf(".");
            String str = lastDotIndex > 0 ? nameOfFile.substring(0, lastDotIndex) : f.toString().substring(path.length() + 1);
            if (nameFile.equals(str)) {
                return nameOfFile;
            }
        }
        return ""; //            !!! to add own exception !!!
    }

    //Setter and Getter
    public String getPath() {
        return path;
    }

    public void setPath(String path) throws EmptyNameOfFileException {
        if(path.isEmpty()) throw new EmptyNameOfFileException("Brak ścieżki");
        this.path = path;
    }

}
