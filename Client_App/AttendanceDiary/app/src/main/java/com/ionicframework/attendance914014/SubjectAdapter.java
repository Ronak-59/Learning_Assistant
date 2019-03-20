package com.ionicframework.attendance914014;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by akash on 25/12/14.
 */
public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.SubjectViewHolder> {
    private ArrayList<Subject> _subjects;

    public SubjectAdapter(ArrayList<Subject> subjects) {
        _subjects = subjects;
    }

    @Override
    public SubjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.status_card,parent,false);
        return new SubjectViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final SubjectViewHolder holder, int position) {
        final Subject subject = _subjects.get(position);

        holder._subjectHead.setTitle(subject.getName());



        float progress;

        if (subject.getTotalClass() == 0) {
            progress = 0;
        } else {
            progress =( subject.getPresentClass() * 100 / subject.getTotalClass() );
        }

        //holder._presentView.setHighlightColor(R.color);
        //holder._progressBar.getProgressDrawable().setColorFilter(Color);
        holder._progressBar.setProgress((int) progress);

        //holder._progressBar.setBackgroundColor(R.color.material_blue_grey_950);

        holder._textView.setText("Percentage " + progress);

        Log.d("Debug","Total class is " + subject.getTotalClass() + " Present class is " + 700/10 + " progress is " + progress);

       /* holder._presentView.setext("present" + subject.getPresentClass());
        holder._totalView.setText("absent" + subject.getTotalClass());*/

        holder._cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final View common = view.findViewById(R.id.subject_head);

                Intent i = new Intent(view.getContext(),UpdateActivity.class);
                i.putExtra("Sub",subject.getId());

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN ) {
                    ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation((android.app.Activity) view.getContext(),common,"Bar");
                    view.getContext().startActivity(i,optionsCompat.toBundle());
                } else {
                    view.getContext().startActivity(i);
                }


            }
        });

    }

    @Override
    public int getItemCount() {
        return _subjects.size();
    }


    public class SubjectViewHolder extends RecyclerView.ViewHolder{
        private TextView _textView,_presentView,_totalView;
        private ProgressBar _progressBar;
        private Toolbar _subjectHead;
        private CardView _cardView;



        public SubjectViewHolder(final View itemView) {
            super(itemView);

            _textView = (TextView) itemView.findViewById(R.id.info);
            _progressBar = (ProgressBar) itemView.findViewById(R.id.progress);
            /*_presentView = (TextView) itemView.findViewById(R.id.present_view);
            _totalView = (TextView) itemView.findViewById(R.id.total_view);*/
            _subjectHead = (Toolbar) itemView.findViewById(R.id.subject_head);
            _cardView = (CardView) itemView.findViewById(R.id.cards_status);


        }


    }


}


