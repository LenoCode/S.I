package general;

import java.lang.reflect.Field;

class Tester{

    private final String king=new String();
    private final String low = new String();
}

public class Generics {

    public static void main(String[] args){

        Tester k = new Tester();

        java.lang.reflect.Field[] fields = k.getClass().getDeclaredFields();


        for (Field field : fields){
            System.out.println(field.getType().getSimpleName());
        }

    }
}
