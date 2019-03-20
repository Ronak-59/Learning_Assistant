package com.ionicframework.attendance914014;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by akash on 28/12/14.
 */
public class DeleteAdapter extends RecyclerView.Adapter<DeleteAdapter.DeleteViewHolder> {
    private ArrayList<Subject> _subjects;

    public DeleteAdapter(ArrayList<Subject> subjects) {
        _subjects = subjects;
    }

    @Override
    public DeleteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DeleteViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.sub_settings_delete,parent,false));
    }

    @Override
    public void onBindViewHolder(final DeleteViewHolder holder, int position) {
        final Subject subject = _subjects.get(position);
        holder._textView.setText(subject.getName());



        holder._delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationManager managerCompat = (NotificationManager) view.getContext().getSystemService(Context.NOTIFICATION_SERVICE);
                managerCompat.cancel((int) subject.getId());
                new DatabaseHandler(view.getContext()).delete(subject);
                _subjects.remove(_subjects.indexOf(subject));

               notifyDataSetChanged();
            }
        });


    }

    @Override
    public int getItemCount() {
        return _subjects.size();
    }

    public class DeleteViewHolder extends RecyclerView.ViewHolder {

        private TextView _textView;
        private ImageButton _delete;

        public DeleteViewHolder(View itemView) {
            super(itemView);
            _textView = (TextView) itemView.findViewById(R.id.subject_setting);
            _delete = (ImageButton) itemView.findViewById(R.id.delete_button);
        }
    }
}
