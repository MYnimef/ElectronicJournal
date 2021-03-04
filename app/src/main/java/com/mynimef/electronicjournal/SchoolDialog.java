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

public class SchoolDialog extends AppCompatDialogFragment {
    private EditText address, name, number;
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
        View view = inflater.inflate(R.layout.school_dialog, null);
        builder.setView(view);

        Button okBtn = (Button) view.findViewById(R.id.okButton);
        okBtn.setOnClickListener( this::okButtonPressed );

        Button cancelBtn = (Button) view.findViewById(R.id.cancelButton);
        cancelBtn.setOnClickListener( this::cancelButtonPressed );

        address = (EditText) view.findViewById(R.id.editAddress);
        name = (EditText) view.findViewById(R.id.editName);
        number = (EditText) view.findViewById(R.id.editNumber);

        return builder.create();
    }

    public void okButtonPressed(View view) {
        String addressStr = address.getText().toString();
        String nameStr = name.getText().toString();
        String numberStr = number.getText().toString();

        if (!addressStr.isEmpty() && !nameStr.isEmpty() && !numberStr.isEmpty()) {
            data.getData(addressStr, nameStr, numberStr);
            dismiss();
        }
    }

    public void cancelButtonPressed(View view) {
        dismiss();
    }
}
