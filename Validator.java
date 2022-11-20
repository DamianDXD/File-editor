package files.actions;

public class Validator {
    private Validator (){};

    public static boolean validSelectedFormat(String str){
        FileFormat[] fileFormat = FileFormat.values();
        for(FileFormat f : fileFormat){
            if(f.toString().equalsIgnoreCase(str))
                return true;
        }
        return false;
    }


}
