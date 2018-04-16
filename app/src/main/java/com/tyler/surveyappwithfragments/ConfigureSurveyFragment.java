package com.tyler.surveyappwithfragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A Fragment for configuring a new survey question and answers
 */
public class ConfigureSurveyFragment extends Fragment {

    EditText newQuestionText;
    EditText newAnswerOneText;
    EditText newAnswerTwoText;
    Button newSurveyButton;

    private static final String TAG = "New Question Item";

    // Listener that can receive a new Question Item
    NewQuestionListener mQuestionListener;

    public static ConfigureSurveyFragment newInstance() {
        return new ConfigureSurveyFragment();
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_configure_survey, container, false);

        newQuestionText = (EditText) view.findViewById(R.id.survey_question_edittext);
        newAnswerOneText = (EditText) view.findViewById(R.id.survey_answer_one_edittext);
        newAnswerTwoText = (EditText) view.findViewById(R.id.survey_answer_two_edittext);
        newSurveyButton = (Button) view.findViewById(R.id.configure_survey_button);

        newSurveyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (newQuestionText.getText().length() > 0 && newAnswerOneText.getText().length() > 0 &&
                        newAnswerTwoText.getText().length() > 0) {
                    String newQuestion = newQuestionText.getText().toString();
                    String newAnswerOne = newAnswerOneText.getText().toString();
                    String newAnswerTwo = newAnswerTwoText.getText().toString();

                    // Clear configure survey fields
                    newQuestionText.getText().clear();
                    newAnswerOneText.getText().clear();
                    newAnswerTwoText.getText().clear();

                    QuestionItem newItem = new QuestionItem(newQuestion, newAnswerOne, newAnswerTwo);

                    Log.d(TAG, "New Question item: " + newItem);

                    // Return newItem back to calling Activity
                    mQuestionListener.newItemCreated(newItem);

                } else {
                    Toast.makeText(getActivity(), "Enter new question and answers", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof NewQuestionListener){
            mQuestionListener = (NewQuestionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement NewQuestionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mQuestionListener = null;
    }


    public interface NewQuestionListener {
        void newItemCreated(QuestionItem newItem);
    }
}