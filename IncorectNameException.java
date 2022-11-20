package files.actions;

public class IncorectNameException extends Exception{
    public IncorectNameException(String errorMessage){
        //System.out.println("Niepoprawna nazwa");
        super(errorMessage);
    }
}
