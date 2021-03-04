package com.mynimef.electronicjournal;

public class School {
    public int AmountOfTeachers, AmountOfEmployers, AmountOfClasses;

    public Teacher[] Teachers;
    public Employee[] Employers;
    public Student[] Students;

    public String Address, Name, Number;

    public java.lang.Class[] Classes;
    public Elective[] Electives;
    public Section[] Sections;

    public void addTeacher(String FullName, int Phone, int CardID, String Position, String Qualification) {
        if (AmountOfTeachers == 0) {
            Teachers = new Teacher[1];
            Teachers[AmountOfTeachers].FullName = FullName;
            Teachers[AmountOfTeachers].Phone = Phone;
            Teachers[AmountOfTeachers].CardID = CardID;
            Teachers[AmountOfTeachers].Position = Position;
            Teachers[AmountOfTeachers].Qualification = Qualification;
        }
        else {
            Teacher[] sub = new Teacher[AmountOfTeachers + 1];
            for (int i = 0; i < AmountOfTeachers; i++) {
                sub[i] = Teachers[i];
            }
            sub[AmountOfTeachers + 1].FullName = FullName;
            sub[AmountOfTeachers + 1].Phone = Phone;
            sub[AmountOfTeachers + 1].CardID = CardID;
            sub[AmountOfTeachers + 1].Position = Position;
            sub[AmountOfTeachers + 1].Qualification = Qualification;
            Teachers = sub;
        }
        AmountOfTeachers++;
    }

    public void addEmployee(String FullName, int Phone, int CardID, String Position) {
        if (AmountOfEmployers == 0) {
            Employers = new Employee[1];
            Employers[AmountOfEmployers].FullName = FullName;
            Employers[AmountOfEmployers].Phone = Phone;
            Employers[AmountOfEmployers].CardID = CardID;
            Employers[AmountOfEmployers].Position = Position;
        }
        else {
            Employee[] sub = new Employee[AmountOfEmployers + 1];
            for (int i = 0; i < AmountOfEmployers; i++) {
                sub[i] = Employers[i];
            }
            sub[AmountOfEmployers + 1].FullName = FullName;
            sub[AmountOfEmployers + 1].Phone = Phone;
            sub[AmountOfEmployers + 1].CardID = CardID;
            sub[AmountOfEmployers + 1].Position = Position;
            Employers = sub;
        }
        AmountOfEmployers++;
    }

    public void addClass() {

    }

    public void getListTeachers() {}
    public void getListEmployers() {}
    public void getListStudents() {}
}
