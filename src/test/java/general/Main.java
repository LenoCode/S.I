package general;


public class Main {


    public static void main(String[] args){
        String a = new String("Split da vidimo</END(PROTOCOL_VERSION_'1')>Nova recinica");


        for (String c : a.split("[<,/]*END[(]PROTOCOL[_]VERSION[_]'\\d+'[),>]*")){
            System.out.println(c);
        }
    }
}
