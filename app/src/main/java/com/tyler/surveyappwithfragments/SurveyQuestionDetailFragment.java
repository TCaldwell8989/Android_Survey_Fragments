package com.tyler.surveyappwithfragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * A Fragment for displaying the survey question and answers
 */
public class SurveyQuestionDetailFragment extends Fragment {

    private static final String QUESTION_ITEM_ARGUMENT = "question item argument";
    private static final String TAG = "QUESTION DETAIL FRAG";

    // Save a reference to the answer the user clicked, so can call methods to send data
    AddScoreListener mAddScoreListener;

    //Creates a new Instance
    public static SurveyQuestionDetailFragment newInstance(QuestionItem question) {
        final Bundle args = new Bundle();
        args.putParcelable(QUESTION_ITEM_ARGUMENT, question);
        final SurveyQuestionDetailFragment fragment = new SurveyQuestionDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_survey_question_detail, container, false);

        // Get the QuestionItem item from the arguments passed in when fragment was created
        final QuestionItem item = getArguments().getParcelable(QUESTION_ITEM_ARGUMENT);
        Log.d(TAG, "onCreateView received the following item: " + item);

        // Set up the view
        final TextView surveyQuestion = (TextView) view.findViewById(R.id.survey_question_textview);
        final Button firstButton = (Button) view.findViewById(R.id.first_answer_button);
        final Button secondButton = (Button) view.findViewById(R.id.second_answer_button);

        surveyQuestion.setText(item.getQuestion());
        firstButton.setText(item.getAnswer1());
        secondButton.setText(item.getAnswer2());

        if (surveyQuestion.getText().toString() == "Your question goes here") {
            firstButton.setEnabled(false);
            secondButton.setEnabled(false);
        }

        // Event Handlers
        firstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int score = 0;
                mAddScoreListener.sendScore(score);
            }
        });

        secondButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int score = 1;
                mAddScoreListener.sendScore(score);
            }
        });

        return view;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof AddScoreListener){
            mAddScoreListener = (AddScoreListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement NewSurveyQuestionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mAddScoreListener = null;
    }

    interface AddScoreListener {
        void sendScore(int score);
    }
}