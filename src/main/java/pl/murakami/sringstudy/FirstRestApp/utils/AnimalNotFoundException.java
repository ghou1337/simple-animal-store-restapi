package pl.murakami.sringstudy.FirstRestApp.utils;

public class AnimalNotFoundException extends RuntimeException{
    public AnimalNotFoundException() {
        super("Animal was not found");
    }
}
