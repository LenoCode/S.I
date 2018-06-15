package novo;

public class Main {


    public static void main(String[] args){
        String data = "<DATA_STRING>classIdent:notificationtestmethod|methodIdent:test01|message:adad</END_LINE>";

        System.out.println(data.split("</END_LINE>")[0]);
    }
}
