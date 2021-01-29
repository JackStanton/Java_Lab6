package application;

import java.io.IOException;
import java.util.ArrayList;

public class AppStarter {


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        CSVWorker csvWorker = new CSVWorker(args[0]);
        Serializator.serialize(csvWorker.getExams());
        ArrayList subjects = (ArrayList) Serializator.deserialize();
        System.out.println("Size of new ArrayList: "+subjects.size());
    }
}
