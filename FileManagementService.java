package files.actions;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class FileManagementService {

    NewFile file = new NewFile();

    public void createNewFile(String format, String fileName){
        if(Validator.validSelectedFormat(format)){
            try{
                file.createEmptyFile(FileFormat.valueOf(format.toUpperCase()), fileName);
            } catch (IOException e) {
                System.err.println("Błąd podczas tworzenia nowego pliku");
                e.printStackTrace();
            }
        }
        else{
            System.out.println("Niepoprawny format");
        }
    }

    public void deleteFile(String name){
        file.deleteFile(name);
    }

    public void showFiles(){
        file.showFiles();
    }

    public void readFileContent(String name){
        try{
            List<String> content = file.readFile(name);
            for(String str : content){
                System.out.println(str);
            }
           // System.out.println(Arrays.toString(content.toArray()));
        } catch(EmptyNameOfFileException e){
            e.printStackTrace();
        } catch(IOException e) {
            System.err.println("Błąd podczas odczytu zawartości pliku " + name);
            e.printStackTrace();
        }

    }

    public void writeToFile(String name, List<String> lines){
        try{
            file.writeToFile(name, lines);
        } catch(EmptyNameOfFileException e){
            e.printStackTrace();
        } catch(IOException e){
            System.err.println("Błąd podczas zapisu danych do pliku " + name);
            e.printStackTrace();
        }
    }

    public void display10Lines(int next10, String name){
        try{
            file.display10Lines(next10,name);
        } catch(EmptyNameOfFileException e){
            e.printStackTrace();
        } catch(IOException e){
            System.err.println("Błąd podczas wyświetlania fragmentu tekstu");
            e.printStackTrace();
        }
    }

    public void createDirectory(String name){
        file.createDir(name);
    }

    public void deleteDirectory(String name){
        file.deleteDir(name);
    }

    public void setPath(String path){
        try{
            file.setPath(path);
        } catch (EmptyNameOfFileException e){
            e.printStackTrace();
        }

    }

    public void getPath(){
        System.out.println(file.getPath());
    }

    public static void clearConsole(){
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {}
    }
}
