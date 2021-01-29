package application;

import application.entities.Credit;
import application.entities.Exam;
import application.interfaces.Subject;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class CSVWorker implements Serializable{
    private String fileName;
    private ArrayList<Subject> exams;
    private int errors = 0;
    private int num = 0;
    private int warnings = 0;
    private int examsCount = 0;
    private int creditsCount = 0;

    public CSVWorker(String fileName) throws IOException {
        this.fileName = fileName;
        this.exams = getObjectsList(fileName);
    }

    public CSVWorker() {
    }

    public ArrayList<Subject> getExams() {
        return exams;
    }

    private ArrayList<Subject> getObjectsList(String fileName) throws IOException {
        File logFile = new File("log.txt");
        FileWriter fileWriter = new FileWriter(logFile);
        File errorsFile = new File("errors.txt");
        FileWriter errorsWriter = new FileWriter(errorsFile);
        String del = ";";
        String[] splitString;
        ArrayList<Subject> ret = new ArrayList<>();
        warnings = 0;
        errors = 0;
        num = 1;
        examsCount = 0;
        creditsCount = 0;
        try {
            File file = new File(fileName);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                splitString = line.replaceAll(String.valueOf((char) 65279),"").split(del);
                if (checkInteger(splitString[0])&&checkInteger(splitString[4])){
                    if(!checkInteger(splitString[1])&&!checkInteger(splitString[2])&&!checkInteger(splitString[3])&&!checkInteger(splitString[5])){
                        warningLogger(errorsWriter,num);
                        warnings++;
                    }
                    Subject subject;
                    if (splitString[5].equals("зач")){
                        subject = new Credit(Integer.parseInt(splitString[0]), splitString[1], splitString[2], splitString[3], Integer.parseInt(splitString[4]), splitString[5]);
                        creditsCount++;
                    }else {
                        subject = new Exam(Integer.parseInt(splitString[0]), splitString[1], splitString[2], splitString[3], Integer.parseInt(splitString[4]), splitString[5]);
                        examsCount++;
                    }
                    objectLogger(subject, fileWriter);
                    ret.add(subject);
                    line = reader.readLine();
                    num++;
                } else {
                    errorsLogger(errorsWriter, num);
                    errors++;
                    line = reader.readLine();
                    num++;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        showMessageInConsole();
        showSummaryInLogFile(fileWriter);
        return ret;
    }

    private void objectLogger(Subject subject, FileWriter fileWriter) throws IOException {
        logFileWriter("Create object: "+ subject, fileWriter);
    }

    private void errorsLogger(FileWriter fileWriter, int line ) throws IOException {
        logFileWriter("Error in line: "+ line, fileWriter);
    }

    private void warningLogger(FileWriter fileWriter, int line) throws IOException {
        logFileWriter("Warning in line: "+ line,fileWriter);
    }

    private void logFileWriter(String message, FileWriter fileWriter) throws IOException {
        fileWriter.write(LocalDateTime.now()+"| "+message);
        fileWriter.write("\n");
        fileWriter.flush();
    }

    private void showMessageInConsole(){
        System.out.println("Done! \nCount of processed lines: " + (num - 1) +" Log in file 'log.txt'");
        System.out.println("Count of errors: "+errors + ". More details in file 'errors.txt'");
        System.out.println("Count of warnings: "+warnings + ". More details in file 'errors.txt'");
    }

    private void showSummaryInLogFile(FileWriter fileWriter) throws IOException {
        String message = "\n==========================================================================================================================\n";
        logFileWriter(message,fileWriter);
        message = "Count of valid lines: " + (num - errors - 1);
        logFileWriter(message,fileWriter);
        message = "Count of exams: " + (examsCount);
        logFileWriter(message,fileWriter);
        message = "Count of credits: " + (creditsCount);
        logFileWriter(message,fileWriter);
    }

    private boolean checkInteger(String str){
        boolean res = false;
        char[] ch = str.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            if(!(ch[i] >= 48 && ch[i] <= 59)){
                return false;
            }
        }
        return true;
    }
}
