package files.actions;

import java.io.IOException;
import java.util.List;

public class FileManagementService {

    FileFunctions fileFunctions = new FileFunctions();

    public void createNewFile(String format, String fileName){
        if(Validator.validSelectedFormat(format)){
            try{
                fileFunctions.createEmptyFile(FileFormat.valueOf(format.toUpperCase()), fileName);
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
        fileFunctions.deleteFile(name);
    }

    public void showFiles(){
        fileFunctions.showFiles();
    }

    public void readFileContent(String name){
        try{
            List<String> content = fileFunctions.readFile(name);
            for(String str : content){
                System.out.println(str);
            }
        } catch(IncorectNameException e){
            e.printStackTrace();
        } catch(IOException e) {
            System.err.println("Błąd podczas odczytu zawartości pliku " + name);
            e.printStackTrace();
        }
    }

    public void writeToFile(String name, List<String> lines){
        try{
            fileFunctions.writeToFile(name, lines);
        } catch(IncorectNameException e){
            e.printStackTrace();
        } catch(IOException e){
            System.err.println("Błąd podczas zapisu danych do pliku " + name);
            e.printStackTrace();
        }
    }

    public boolean display10Lines(int next10, String name){
        try{
            fileFunctions.display10Lines(next10,name);
            return true;
        } catch(IncorectNameException e){
            e.printStackTrace();
            return false;
        } catch(IOException e){
            System.err.println("Błąd podczas wyświetlania fragmentu tekstu");
            e.printStackTrace();
            return false;
        }
    }

    public void createDirectory(String name){
        fileFunctions.createDir(name);
    }

    public void deleteDirectory(String name){
        fileFunctions.deleteDir(name);
    }

    public void setPath(String path){
        try{
            fileFunctions.setPath(path);
        } catch (IncorectNameException e){
            e.printStackTrace();
        }
    }

    public void getPath(){
        System.out.println(fileFunctions.getPath());
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
