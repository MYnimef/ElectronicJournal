package com.mynimef.electronicjournal;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mynimef.electronicjournal.school.School;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements DialogData {
    private int amountOfSchools = 0;
    Button[] schoolButton;
    LinearLayout[] afterSchool;
    ArrayList<School> schoolList = new ArrayList();

    boolean[] clickOnSchool;

    Button[] employersButton;
    Button[] teachersButton;
    Button[] classesButton;

    boolean[] clickOnEmployers, clickOnTeachers, clickOnClasses;
    int actualTap;

    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

        //schoolList.add(myRef.child("users").child("user").getClass());
        makeSchoolButtons();
    }

    void makeSchoolButtons() {
        LinearLayout schoolLayout = findViewById(R.id.ListOfSchools);
        schoolLayout.removeAllViews();

        schoolButton = new Button[amountOfSchools];
        afterSchool = new LinearLayout[amountOfSchools];
        clickOnSchool = new boolean[amountOfSchools];
        clickOnEmployers = new boolean[amountOfSchools];
        clickOnTeachers = new boolean[amountOfSchools];
        clickOnClasses = new boolean[amountOfSchools];
        for (int i = 0; i < amountOfSchools; i++) {
            LayoutInflater inflater = (LayoutInflater) getApplicationContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            schoolButton[i] = (Button) inflater.inflate(R.layout.school_button, schoolLayout,
                    false);
            schoolButton[i].setTag(i);
            schoolButton[i].setOnClickListener(this::onSchoolClick);
            schoolButton[i].setText("School number: " + schoolList.get(i).Number + ", " +
                    schoolList.get(i).Name);
            schoolLayout.addView(schoolButton[i]);

            afterSchool[i] = (LinearLayout) inflater.inflate(R.layout.after_school, schoolLayout,
                    false);
            afterSchool[i].setVisibility(View.GONE);
            clickOnSchool[i] = false;
            clickOnEmployers[i] = false;
            clickOnTeachers[i] = false;
            clickOnClasses[i] = false;
            schoolLayout.addView(afterSchool[i]);
        }
    }

    public void onSchoolClick(View view) {
        Button tappedSchool = (Button) view;
        actualTap = (int) tappedSchool.getTag();
        if (clickOnSchool[actualTap]) {
            afterSchool[actualTap].setVisibility(View.GONE);
            clickOnSchool[actualTap] = false;
        }
        else {
            afterSchool[actualTap].setVisibility(View.VISIBLE);
            clickOnSchool[actualTap] = true;

            Button empBtn = (Button) afterSchool[actualTap].findViewById(R.id.employersButton);
            empBtn.setOnClickListener( this::onEmployersClick );
            empBtn.setTag(actualTap);
            Button teachBtn = (Button) afterSchool[actualTap].findViewById(R.id.teachersButton);
            teachBtn.setOnClickListener( this::onTeachersClick );
            teachBtn.setTag(actualTap);
            Button classBtn = (Button) afterSchool[actualTap].findViewById(R.id.classesButton);
            classBtn.setOnClickListener( this::onClassesClick );
            classBtn.setTag(actualTap);
        }
    }

    public void addSchoolClick(View view) {
        FragmentManager manager = getSupportFragmentManager();
        SchoolDialog myDialogFragment = new SchoolDialog();
        myDialogFragment.show(manager, "myDialog");
    }

    public void getSchoolData(String address, String name, String number) {
        School school = new School();
        school.Address = address;
        school.Name = name;
        school.Number = number;
        schoolList.add(school);
        amountOfSchools++;
        makeSchoolButtons();
    }

    public void onEmployersClick(View view) {
        Button tapped = (Button) view;
        actualTap = (int) tapped.getTag();
        LinearLayout empLayout = (LinearLayout) afterSchool[actualTap].findViewById(R.id.employersLayout);

        if (clickOnEmployers[actualTap]) {
            empLayout.setVisibility(View.GONE);
            clickOnEmployers[actualTap] = false;
        }
        else {
            empLayout.setVisibility(View.VISIBLE);
            Button empBtn = (Button) afterSchool[actualTap].findViewById(R.id.addEmployersButton);
            empBtn.setOnClickListener( this::addEmployersClick );
            empBtn.setTag(actualTap);
            clickOnEmployers[actualTap] = true;
        }
        makeEmployersButtons();
    }

    public void onTeachersClick(View view) {
        Button tapped = (Button) view;
        actualTap = (int) tapped.getTag();
        LinearLayout teachLayout = (LinearLayout) afterSchool[actualTap].findViewById(R.id.teachersLayout);

        if (clickOnTeachers[actualTap]) {
            teachLayout.setVisibility(View.GONE);
            clickOnTeachers[actualTap] = false;
        }
        else {
            teachLayout.setVisibility(View.VISIBLE);
            Button teachBtn = (Button) afterSchool[actualTap].findViewById(R.id.addTeachersButton);
            teachBtn.setOnClickListener( this::addTeachersClick );
            teachBtn.setTag(actualTap);
            clickOnTeachers[actualTap] = true;
        }
        makeTeachersButtons();
    }

    public void onClassesClick(View view) {
        Button tapped = (Button) view;
        actualTap = (int) tapped.getTag();
        LinearLayout classLayout = (LinearLayout) afterSchool[actualTap].findViewById(R.id.classesLayout);

        if (clickOnClasses[actualTap]) {
            classLayout.setVisibility(View.GONE);
            clickOnClasses[actualTap] = false;
        }
        else {
            classLayout.setVisibility(View.VISIBLE);
            Button classBtn = (Button) afterSchool[actualTap].findViewById(R.id.addClassesButton);
            classBtn.setOnClickListener( this::addClassesClick );
            classBtn.setTag(actualTap);
            clickOnClasses[actualTap] = true;
        }
        makeClassesButtons();
    }

    public void addEmployersClick(View view) {
        Button tapped = (Button) view;
        actualTap = (int) tapped.getTag();

        FragmentManager manager = getSupportFragmentManager();
        EmployerDialog myDialogFragment = new EmployerDialog();
        myDialogFragment.show(manager, "myDialog");
    }

    public void getEmployerData(String fullName, String phone, String cardID, String position) {
        schoolList.get(actualTap).addEmployee(fullName, phone, cardID, position);
        makeEmployersButtons();
    }

    public void makeEmployersButtons() {
        LinearLayout employersLayout = afterSchool[actualTap].findViewById(R.id.newEmployersLayout);
        employersLayout.removeAllViews();

        employersButton = new Button[schoolList.get(actualTap).AmountOfEmployers];
        for (int i = 0; i < schoolList.get(actualTap).AmountOfEmployers; i++) {
            LayoutInflater inflater = (LayoutInflater) getApplicationContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            employersButton[i] = (Button) inflater.inflate(R.layout.employer_button,
                    employersLayout, false);
            employersButton[i].setTag(i);
            employersButton[i].setOnClickListener(this::onEmployerButtonClick);
            employersButton[i].setText(schoolList.get(actualTap).Employers[i].Position + " - " +
                    schoolList.get(actualTap).Employers[i].FullName);
            employersLayout.addView(employersButton[i]);
        }
    }

    public void onEmployerButtonClick(View view) {
    }

    public void addTeachersClick(View view) {
        Button tapped = (Button) view;
        actualTap = (int) tapped.getTag();

        FragmentManager manager = getSupportFragmentManager();
        TeacherDialog myDialogFragment = new TeacherDialog();
        myDialogFragment.show(manager, "myDialog");
    }

    public void getTeacherData(String fullName, String phone, String cardID, String position,
                               String qualification) {
        schoolList.get(actualTap).addTeacher(fullName, phone, cardID, position, qualification);
        makeTeachersButtons();
    }

    public void makeTeachersButtons() {
        LinearLayout teachersLayout = afterSchool[actualTap].findViewById(R.id.newTeachersLayout);
        teachersLayout.removeAllViews();

        teachersButton = new Button[schoolList.get(actualTap).AmountOfTeachers];
        for (int i = 0; i < schoolList.get(actualTap).AmountOfTeachers; i++) {
            LayoutInflater inflater = (LayoutInflater) getApplicationContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            teachersButton[i] = (Button) inflater.inflate(R.layout.teacher_button,
                    teachersLayout, false);
            teachersButton[i].setTag(i);
            teachersButton[i].setOnClickListener(this::onTeacherButtonClick);
            teachersButton[i].setText(schoolList.get(actualTap).Teachers[i].Position + " - " +
                    schoolList.get(actualTap).Teachers[i].FullName);
            teachersLayout.addView(teachersButton[i]);
        }
    }

    public void onTeacherButtonClick(View view) {
    }

    public void addClassesClick(View view) {
        Button tapped = (Button) view;
        actualTap = (int) tapped.getTag();

        FragmentManager manager = getSupportFragmentManager();
        ClassDialog myDialogFragment = new ClassDialog();
        myDialogFragment.show(manager, "myDialog");
    }

    public void getClassData(String number) {
    }

    public void makeClassesButtons() {
        LinearLayout classesLayout = afterSchool[actualTap].findViewById(R.id.newEmployersLayout);
        classesLayout.removeAllViews();

        classesButton = new Button[schoolList.get(actualTap).AmountOfClasses];
        for (int i = 0; i < schoolList.get(actualTap).AmountOfClasses; i++) {
            LayoutInflater inflater = (LayoutInflater) getApplicationContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            classesButton[i] = (Button) inflater.inflate(R.layout.employer_button,
                    classesLayout, false);
            classesButton[i].setTag(i);
            classesButton[i].setOnClickListener(this::onEmployerButtonClick);
            classesButton[i].setText(schoolList.get(actualTap).Employers[i].Position + " - " +
                    schoolList.get(actualTap).Employers[i].FullName);
            classesLayout.addView(classesButton[i]);
        }
    }

    public void onSaveClick(View view) {
        myRef.child("users").child("user").setValue(schoolList);
    }
}