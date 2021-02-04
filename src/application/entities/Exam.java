package application.entities;

import application.interfaces.SubjectAbstract;


public class Exam extends SubjectAbstract {

    public Exam(int id, String name, String surname, String subjectName, int hours, String mark) {
        super(id, name, surname, subjectName, hours, mark);
    }

    public Exam() {
    }
}
