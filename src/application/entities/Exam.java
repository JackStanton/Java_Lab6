package application.entities;

import application.interfaces.Subject;

import java.io.Serializable;

public class Exam extends Student implements Subject, Serializable {
    private int id;
    private int hours;
    private String mark;
    private String subjectName;

    public Exam( int id, String studentName, String studentSurname,String subjectName, int hours, String mark) {
        super(studentName, studentSurname);
        this.subjectName = subjectName;
        this.id = id;
        this.hours = hours;
        this.mark = mark;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getSubjectName() {
        return null;
    }

    @Override
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    @Override
    public int getHours() {
        return hours;
    }

    @Override
    public void setHours(int hours) {
        this.hours = hours;
    }

    @Override
    public String getMark() {
        return mark;
    }

    @Override
    public void setMark(String mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "id=" + id +
                ", studentName='" + super.getName()+"'"+
                ", studentSurname='"+super.getSurname()+"'"+
                ", hours=" + hours +
                ", mark='" + mark + '\'' +
                ", subjectName='" + subjectName + '\'' +
                '}';
    }
}
