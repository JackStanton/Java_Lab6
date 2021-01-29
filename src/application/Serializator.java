package application;

import java.io.*;
import java.sql.Time;
import java.time.LocalDateTime;

public class Serializator {
    private static File logFile = new File("serialize_log.txt");
    private static FileWriter fileWriter;

    static {
        try {
            fileWriter = new FileWriter(logFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Serializator() throws IOException {
    }

    public static void serialize(Object obj) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data.dat"));
        oos.writeObject(obj);
        String message = "Serialize object type of:"+ obj.getClass();
        serializatorLogger(message);
        System.out.println("Serialize is complete");
    }

    public static Object deserialize() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data.dat"));
        Object obj = ois.readObject();
        String message = "Deserialize object type of:"+ obj.getClass();
        serializatorLogger(message);
        System.out.println("Deserialize is complete");
        return obj;
    }

    private static void serializatorLogger(String message) throws IOException {
        fileWriter.write(LocalDateTime.now()+"| "+message+"\n");
        fileWriter.flush();
    }
}
