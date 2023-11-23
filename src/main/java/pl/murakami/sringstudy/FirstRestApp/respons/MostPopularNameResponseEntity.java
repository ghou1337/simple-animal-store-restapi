package pl.murakami.sringstudy.FirstRestApp.respons;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MostPopularNameResponseEntity {

    String name;

    long n;

    public MostPopularNameResponseEntity(String name, long n) {
        this.name = name;
        this.n = n;
    }

}
