package pl.murakami.sringstudy.FirstRestApp.utils;

public class AnimalNotCreatedException extends RuntimeException{
    public AnimalNotCreatedException(String message) {
        super(message);
    }
}
