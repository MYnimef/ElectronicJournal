package com.mynimef.electronicjournal;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatDialogFragment;

public class EmployerDialog extends AppCompatDialogFragment {
    private EditText fullName, phone, cardID, position;
    DialogData data;

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        data = (DialogData) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().setCanceledOnTouchOutside(false);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.employer_dialog, null);
        builder.setView(view);

        Button okBtn = (Button) view.findViewById(R.id.okButton);
        okBtn.setOnClickListener( this::okButtonPressed );

        Button cancelBtn = (Button) view.findViewById(R.id.cancelButton);
        cancelBtn.setOnClickListener( this::cancelButtonPressed );

        fullName = (EditText) view.findViewById(R.id.editFullName);
        phone = (EditText) view.findViewById(R.id.editPhone);
        cardID = (EditText) view.findViewById(R.id.editCardID);
        position = (EditText) view.findViewById(R.id.editPosition);

        return builder.create();
    }

    public void okButtonPressed(View view) {
        String fullNameStr = fullName.getText().toString();
        String phoneStr = phone.getText().toString();
        String cardIdStr = cardID.getText().toString();
        String positionStr = cardID.getText().toString();

        if (!fullNameStr.isEmpty() && !phoneStr.isEmpty() && !cardIdStr.isEmpty() && !positionStr.isEmpty()) {
            data.getEmployerData(fullNameStr, phoneStr, cardIdStr, positionStr);
            dismiss();
        }
    }

    public void cancelButtonPressed(View view) {
        dismiss();
    }
}
