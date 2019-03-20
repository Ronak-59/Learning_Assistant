package com.ionicframework.attendance914014;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by akash on 26/12/14.
 */
public class TodayAdapter extends RecyclerView.Adapter <TodayAdapter.TodayViewHolder> {
    private ArrayList<Subject> _subjects;

    public TodayAdapter(ArrayList<Subject> subjects) {
        _subjects = subjects;
    }

    @Override
    public TodayViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.today,parent,false);
        return new TodayViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final TodayViewHolder holder, int position) {
        final Subject subject = _subjects.get(position);

        holder._presentView.setText(subject.getPresentClass());

        holder._totalView.setText(subject.getTotalClass());

        holder._subject.setText(subject.getName());

        holder._present.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subject.setPresentClass(subject.getPresentClass() + 1);
                subject.setTotalClass(subject.getTotalClass() + 1);
            }
        });

        holder._absent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subject.setTotalClass(subject.getTotalClass() + 1);
            }
        });

        holder._noClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"No Class",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return _subjects.size();
    }

    public class TodayViewHolder extends RecyclerView.ViewHolder {
        private TextView _subject,_presentView,_totalView;
        private Button _present,_absent,_noClass;


        public TodayViewHolder(View itemView) {
            super(itemView);

            _subject = (TextView) itemView.findViewById(R.id.info_today);
            _present = (Button) itemView.findViewById(R.id.present_button);
            _absent = (Button) itemView.findViewById(R.id.absent_button);
            _noClass = (Button) itemView.findViewById(R.id.no_class_button);

            _presentView = (TextView) itemView.findViewById(R.id.present_view);
            _totalView = (TextView) itemView.findViewById(R.id.total_view);
        }
    }
}
