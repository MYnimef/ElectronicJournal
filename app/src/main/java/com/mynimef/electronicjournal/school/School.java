package com.mynimef.electronicjournal.school;

public class School {
    public int AmountOfTeachers, AmountOfEmployers, AmountOfClasses;

    public Teacher[] Teachers;
    public Employee[] Employers;
    public Student[] Students;

    public String Address, Name, Number;

    public Class[] Classes;
    public Elective[] Electives;
    public Section[] Sections;

    public void addTeacher(String FullName, String Phone, String CardID, String Position, String Qualification) {
        if (AmountOfTeachers == 0) {
            Teachers = new Teacher[AmountOfTeachers + 1];
            Teachers[AmountOfTeachers] = new Teacher();
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
            sub[AmountOfTeachers] = new Teacher();
            sub[AmountOfTeachers].FullName = FullName;
            sub[AmountOfTeachers].Phone = Phone;
            sub[AmountOfTeachers].CardID = CardID;
            sub[AmountOfTeachers].Position = Position;
            sub[AmountOfTeachers].Qualification = Qualification;
            Teachers = sub;
        }
        AmountOfTeachers++;
    }

    public void addEmployee(String FullName, String Phone, String CardID, String Position) {
        if (AmountOfEmployers == 0) {
            Employers = new Employee[AmountOfEmployers + 1];
            Employers[AmountOfEmployers] = new Employee();
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
            sub[AmountOfEmployers] = new Employee();
            sub[AmountOfEmployers].FullName = FullName;
            sub[AmountOfEmployers].Phone = Phone;
            sub[AmountOfEmployers].CardID = CardID;
            sub[AmountOfEmployers].Position = Position;
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
