package general;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.HashMap;

public class ExceptionTesting {

    public static void main(String[] args){
        try{
          bewtest();
        }catch(Exception e){
           for (StackTraceElement element : e.getStackTrace()){
               System.out.println(element.getMethodName());
           }
           new SocketException().test(e);
        }
    }
    public static void test()throws Exception {
        cnewtest();
    }
    public static void bewtest()throws Exception{
       test();
    }
    public static void cnewtest()throws Exception{
        newtest();
    }
    public static void newtest()throws Exception{
        int bytesRead = -1;
        throw new SocketTimeoutException("kasda");
    }

}
interface solution{
    void handle(Exception e);
}

class SocketException{

    HashMap<String,solution> mapa = new HashMap<>();

    public SocketException(){
        mapa.put(new SocketTimeoutExceptionSolution().toString(),new SocketTimeoutExceptionSolution());
    }

    public void test(Exception e){

        mapa.get(e.getClass().getSimpleName()).handle(e);
    }
}

class SocketTimeoutExceptionSolution implements solution{

    public SocketTimeoutExceptionSolution(){

    }

    @Override
    public String toString() {
        return SocketTimeoutException.class.getSimpleName();
    }

    @Override
    public void handle(Exception e) {
        System.out.println(" ovo je timeout exception " +e.getMessage());
    }
}


//U dijelu kojem je nastao exception,zelim pokupiti neke objekte koje bi mogao koristiti za solution