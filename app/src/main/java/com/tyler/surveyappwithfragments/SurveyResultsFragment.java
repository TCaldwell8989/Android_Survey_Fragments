package com.tyler.surveyappwithfragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A Fragment that displays the survey results and ability to reset scores
 */
public class SurveyResultsFragment extends Fragment {

    private final String TAG = "RESULT FRAG";

    TextView answer1TV;
    TextView answer2TV;
    Button resetBtn;

    //Creates a new Instance
    public static SurveyResultsFragment newInstance() {
        SurveyResultsFragment fragment = new SurveyResultsFragment();
        return fragment;
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_survey_results, container, false);

        int firstScore = -1;
        int secondScore = -1;

        // Validate arguments have been received
        if (getArguments() != null) {
            firstScore = getArguments().getInt(MainActivity.ANSWER1_BUNDLE_KEY, -1);
            secondScore = getArguments().getInt(MainActivity.ANSWER2_BUNDLE_KEY, -1);
        }

        answer1TV = (TextView) view.findViewById(R.id.first_score_textview);
        answer2TV = (TextView) view.findViewById(R.id.second_score_textview);
        resetBtn = (Button) view.findViewById(R.id.reset_button);

        if (firstScore == -1) {
            // No answer received, no arguments, or no data for ANSWER_BUNDLE_KEY
            answer1TV.setText("0");
            answer2TV.setText("0");
        } else {
            answer1TV.setText(firstScore);
            answer2TV.setText(secondScore);
        }

        // Event Handlers
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog areYouSureDialog = new AlertDialog.Builder(getActivity())
                       .setTitle("Reset the scores?")
                       .setMessage("")
                       .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialogInterface, int which) {
                               answer1TV.setText("0");
                               answer2TV.setText("0");
                           }
                       })
                        .setNegativeButton(R.string.no, null)
                        .create();
                areYouSureDialog.show();
            }
        });

        return view;

    }

    public void getAnswer(int answer) {

        if (answer == 0) {
            int currentScore = Integer.parseInt(answer1TV.getText().toString());
            currentScore += 1;
            answer1TV.setText(String.valueOf(currentScore));
        } else if (answer == 1) {
            int currentScore = Integer.parseInt(answer2TV.getText().toString());
            currentScore += 1;
            answer2TV.setText(String.valueOf(currentScore));
        } else {
            Log.d(TAG, "Error");
        }
    }



}

