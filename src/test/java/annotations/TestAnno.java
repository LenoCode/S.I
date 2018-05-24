package annotations;


import socket_installer.SI_parts.notification.notification_processor.annotation_parser.AnnotationParser;
import socket_installer.SI_parts.notification.notification_processor.annotation_parser.method_annotater.MethodAnnotater;



public class TestAnno {




    public static void main(String[] args){
        TestMethod s = new TestMethod();
        AnnotationParser processor = new AnnotationParser();


        try {
            TestMethod[] a ={new TestMethod()};
            TestMethod b = processor.identifyClass(a,"TestMethod");
            MethodAnnotater methodAnnotater = processor.identifyMethod(b,b.getClass().getMethods(),"method");
            methodAnnotater.runSteps(null,"Ovo je data");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
