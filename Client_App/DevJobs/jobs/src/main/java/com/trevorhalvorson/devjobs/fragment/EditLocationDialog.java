package com.trevorhalvorson.devjobs.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.trevorhalvorson.devjobs.R;

/**
 * Created by Trevor on 9/6/2015.
 */
public class EditLocationDialog extends DialogFragment {

    public static EditLocationDialog newInstance() {
        return new EditLocationDialog();
    }

    public interface EditLocationDialogListener {
        void onFinishEditDialog(String inputText);
    }

    public static void setListener(EditLocationDialogListener listener) {
        mListener = listener;
    }

    private static EditLocationDialogListener mListener;
    private EditText mEditText;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View view = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_location, null);
        mEditText = (EditText) view.findViewById(R.id.location_edit_text);
        mEditText.requestFocus();

        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setTitle(R.string.location)
                .setNeutralButton("CLOSE", null)
                .setNegativeButton("ANYWHERE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mListener.onFinishEditDialog("");
                        dismiss();
                    }
                })
                .setPositiveButton("SET", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mListener.onFinishEditDialog(mEditText.getText().toString());
                        dismiss();
                    }
                })
                .create();
    }
}