package com.mynimef.electronicjournal;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity implements DialogData {
    int amountOfSchools = 0;
    Button[] schoolButton;
    LinearLayout[] afterSchool;
    School[] schoolList;

    boolean[] clickOnSchool;

    Button[] employersButton;
    Button[] teachersButton;
    Button[] classesButton;

    boolean[] clickOnEmployers, clickOnTeachers, clickOnClasses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
            schoolButton[i] = (Button) inflater.inflate(R.layout.school_button, schoolLayout, false);
            schoolButton[i].setTag(i);
            schoolButton[i].setOnClickListener(this::onSchoolClick);
            schoolButton[i].setText("School number: " + schoolList[i].Number + ", " + schoolList[i].Name);
            schoolLayout.addView(schoolButton[i]);

            afterSchool[i] = (LinearLayout) inflater.inflate(R.layout.after_school, schoolLayout, false);
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
        int tap = (int) tappedSchool.getTag();
        if (clickOnSchool[tap]) {
            afterSchool[tap].setVisibility(View.GONE);
            clickOnSchool[tap] = false;
        }
        else {
            afterSchool[tap].setVisibility(View.VISIBLE);
            clickOnSchool[tap] = true;

            Button empBtn = (Button) afterSchool[tap].findViewById(R.id.employersButton);
            empBtn.setOnClickListener( this::onEmployersClick );
            empBtn.setTag(tap);
            Button teachBtn = (Button) afterSchool[tap].findViewById(R.id.teachersButton);
            teachBtn.setOnClickListener( this::onTeachersClick );
            teachBtn.setTag(tap);
            Button classBtn = (Button) afterSchool[tap].findViewById(R.id.classesButton);
            classBtn.setOnClickListener( this::onClassesClick );
            classBtn.setTag(tap);
        }
    }

    public void addSchoolClick(View view) {
        FragmentManager manager = getSupportFragmentManager();
        SchoolDialog myDialogFragment = new SchoolDialog();
        myDialogFragment.show(manager, "myDialog");
    }

    @Override
    public void getData(String address, String name, String number) {
        if (amountOfSchools != 0) {
            School[] sup = schoolList;
            schoolList = new School[amountOfSchools + 1];
            for (int i = 0; i < amountOfSchools; i++) {
                schoolList[i] = sup[i];
            }
        }
        else {
            schoolList = new School[amountOfSchools + 1];
        }
        schoolList[amountOfSchools] = new School();
        schoolList[amountOfSchools].Address = address;
        schoolList[amountOfSchools].Name = name;
        schoolList[amountOfSchools].Number = number;
        amountOfSchools++;
        makeSchoolButtons();
    }

    public void onEmployersClick(View view) {
        Button tapped = (Button) view;
        int tap = (int) tapped.getTag();
        LinearLayout empLayout = (LinearLayout) afterSchool[tap].findViewById(R.id.employersLayout);

        if (clickOnEmployers[tap]) {
            empLayout.setVisibility(View.GONE);
            clickOnEmployers[tap] = false;
        }
        else {
            empLayout.setVisibility(View.VISIBLE);
            Button empBtn = (Button) afterSchool[tap].findViewById(R.id.addEmployersButton);
            //empBtn.setOnClickListener( this::onEmployersClick );
            empBtn.setTag(tap);
            clickOnEmployers[tap] = true;
        }
    }

    public void onTeachersClick(View view) {
        Button tapped = (Button) view;
        int tap = (int) tapped.getTag();
        LinearLayout teachLayout = (LinearLayout) afterSchool[tap].findViewById(R.id.teachersLayout);

        if (clickOnTeachers[tap]) {
            teachLayout.setVisibility(View.GONE);
            clickOnTeachers[tap] = false;
        }
        else {
            teachLayout.setVisibility(View.VISIBLE);
            Button teachBtn = (Button) afterSchool[tap].findViewById(R.id.addTeachersButton);
            //empBtn.setOnClickListener( this::onEmployersClick );
            teachBtn.setTag(tap);
            clickOnTeachers[tap] = true;
        }
    }

    public void onClassesClick(View view) {
        Button tapped = (Button) view;
        int tap = (int) tapped.getTag();
        LinearLayout classLayout = (LinearLayout) afterSchool[tap].findViewById(R.id.classesLayout);

        if (clickOnClasses[tap]) {
            classLayout.setVisibility(View.GONE);
            clickOnClasses[tap] = false;
        }
        else {
            classLayout.setVisibility(View.VISIBLE);
            Button classBtn = (Button) afterSchool[tap].findViewById(R.id.addClassesButton);
            //empBtn.setOnClickListener( this::onEmployersClick );
            classBtn.setTag(tap);
            clickOnClasses[tap] = true;
        }
    }
}