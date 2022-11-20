package files.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Functions
 * Create empty file or dir, delete, add text to file, read,
 * display by 10 lines
 *
 */

public class FileManagement {
    private static final String FIRSTSCAN = "fs";

    public static void main(String[] args) {
        FileManagementService.clearConsole();
        FileManagementService fileMService = new FileManagementService();
        System.out.println("\n\tZarządzaj plikami w podanym folderze.\n");
        String response = FIRSTSCAN;
        printMenu();

        while(!response.equals("exit")){
            if(!response.equals(FIRSTSCAN) && !response.equals("0")) System.out.println("0 - wyświetl menu");
            System.out.print("Wybierz akcje: ");
            Scanner scanner = new Scanner(System.in);
            response = scanner.nextLine();
            switch (response.toLowerCase()){
                case "0":
                    FileManagementService.clearConsole();
                    printMenu();
                    break;
                case "1":
                    FileManagementService.clearConsole();
                    fileMService.getPath();
                    break;
                case "2":
                    FileManagementService.clearConsole();
                    System.out.println("Podaj ścieżkę:");
                    fileMService.setPath(scanner.nextLine());
                    break;
                case "3":
                    FileManagementService.clearConsole();
                    System.out.println("Podaj nazwę pliku");
                    String name = scanner.nextLine();
                    System.out.println("Podaj format pliku(doc, txt, awl)");
                    String format = scanner.nextLine();
                    fileMService.createNewFile(format,name);
                    break;
                case "4":
                    FileManagementService.clearConsole();
                    System.out.println("Podaj nazwę pliku do usunięcia");
                    name = scanner.nextLine();
                    fileMService.deleteFile(name);
                    break;
                case "5":
                    FileManagementService.clearConsole();
                    System.out.println("Podaj nazwę folderu do stworzenia");
                    name = scanner.nextLine();
                    fileMService.createDirectory(name);
                    break;
                case "6":
                    FileManagementService.clearConsole();
                    System.out.println("Podaj nazwę folderu do usunięcia");
                    name = scanner.nextLine();
                    fileMService.deleteDirectory(name);
                    break;
                case "7":
                    FileManagementService.clearConsole();
                    fileMService.showFiles();
                    break;
                case "8":
                    FileManagementService.clearConsole();
                    System.out.println("Podaj nazwę pliku");
                    name = scanner.nextLine();
                    fileMService.readFileContent(name);
                    break;
                case "9":
                    FileManagementService.clearConsole();
                    System.out.println("Podaj nazwę pliku");
                    name = scanner.nextLine();
                    System.out.println("Wpisz tekst");
                    List<String> lines = new ArrayList<>();
                    boolean continueLoop = true;
                    do{
                        String newLine = scanner.nextLine();
                        if(!newLine.isEmpty())
                            lines.add(newLine);
                        else
                            continueLoop = false;
                    } while (continueLoop);
                    fileMService.writeToFile(name,lines);
                    break;

                case "10":
                    String resp = "";
                    int next10 = 0;
                    boolean correctRead = true;
                    FileManagementService.clearConsole();
                    System.out.println("Podaj nazwę pliku");
                    name = scanner.nextLine();
                    correctRead = fileMService.display10Lines(next10,name);
                    while(!resp.equals("q") && correctRead){
                        System.out.println("\nWpisz literę oraz zatwierdz. Przewijaj tekst za pomocą litery 'f' oraz 'b'. Aby zakończyć wpisz 'q'");
                        resp = scanner.nextLine();
                        if(resp.equals("f")) {
                            next10++;
                            FileManagementService.clearConsole();
                            correctRead = fileMService.display10Lines(next10,name);
                        }
                        if(next10>0 && resp.equals("b")){
                            next10--;
                            FileManagementService.clearConsole();
                            correctRead = fileMService.display10Lines(next10,name);
                        }
                        System.out.println();
                    }
                    break;
                case "exit":
                    System.out.println("Aplikacja kończy działanie");
                    break;

                default:
                    System.out.println("Podano niepoprawną wartość");
                    break;
            }
            System.out.println();
        }
    }

     private static void printMenu(){
        System.out.println("Wybierz jedną z dostępnych akcji");
        System.out.println("0 - wyświetl menu");
        System.out.println("1 - wyświetl ścieżkę folderu");
        System.out.println("2 - zmień ścieżkę folderu");
        System.out.println("3 - utwórz nowy plik");
        System.out.println("4 - usuń plik");
        System.out.println("5 - utwórz nowy folder");
        System.out.println("6 - usuń pusty folder");
        System.out.println("7 - wyświetl zawartość folderu");
        System.out.println("8 - odczytaj całą zawartość pliku");
        System.out.println("9 - dodaj dane do pliku");
        System.out.println("10 - wyświetl fragment pliku");
        System.out.println("exit - zakończ działanie programu");
    }

}
