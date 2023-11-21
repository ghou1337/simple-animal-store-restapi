package pl.murakami.sringstudy.FirstRestApp.utils;

public class AnimalNotCreatedException extends RuntimeException{
    public AnimalNotCreatedException() {
        super("Animal was not created");
    }
}
