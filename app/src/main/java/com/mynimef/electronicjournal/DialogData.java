package com.mynimef.electronicjournal;

public interface DialogData {
    void getSchoolData(String address, String name, String number);
    void getEmployerData(String fullName, String phone, String cardID, String position);
    void getTeacherData(String fullName, String phone, String cardID, String position, String qualification);
    void getClassData();
}
