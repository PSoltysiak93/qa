package pl.systems.qajunit;

public class GamePlay {

    public static String play (int number){
        if (number == 0) throw new IllegalArgumentException("Number must not be 0");
        if (number % 3 == 0) return "ok";
        if (number % 3 != 0) return "not Ok";
        return null;


    }

}
