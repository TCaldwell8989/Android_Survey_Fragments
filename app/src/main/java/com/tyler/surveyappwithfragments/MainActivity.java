package com.tyler.surveyappwithfragments;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements
        SurveyQuestionDetailFragment.AddScoreListener,
        ConfigureSurveyFragment.NewQuestionListener {

    private static final String QUESTION_ITEM_KEY = "QUESTION ITEM KEY";
    public static final String ANSWER1_BUNDLE_KEY = "ANSWER1 KEY";
    public static final String ANSWER2_BUNDLE_KEY = "ANSWER2 KEY";
    private static final String SURVEY_QUESTION_FRAG_TAG = "SURVEY FRAGMENT";
    private static final String CONFIG_SURVEY_FRAG_TAG = "CONFIG FRAGMENT";
    private static final String RESULTS_FRAG_TAG = "RESULTS FRAGMENT";

    //Create instances of fragments
    SurveyQuestionDetailFragment detailFragment;
    SurveyResultsFragment resultsFragment;
    ConfigureSurveyFragment configureFragment;

    private QuestionItem defaultItem;

    private static final String TAG = "MAIN ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            // No savedInstanceState -- create Activity
            Log.d(TAG, "OnCreate has no instance state. Set up Fragments");

            defaultItem = new QuestionItem("Your question goes here", "Answer1 goes here" ,"Answer2 goes here");

            detailFragment = SurveyQuestionDetailFragment.newInstance(defaultItem);
            resultsFragment = SurveyResultsFragment.newInstance();
            configureFragment = ConfigureSurveyFragment.newInstance();

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();

            ft.add(R.id.display_survey_view_container, detailFragment, SURVEY_QUESTION_FRAG_TAG);
            ft.add(R.id.results_survey_view_container, resultsFragment, RESULTS_FRAG_TAG);
            ft.add(R.id.configure_survery_view_container, configureFragment, CONFIG_SURVEY_FRAG_TAG);

            ft.commit();

        } else {
            // There is savedInstanceState, app is resuming
            // Restore the data
            defaultItem = savedInstanceState.getParcelable(QUESTION_ITEM_KEY);
            Log.d(TAG, "onCreate has saved instance state question item = " + defaultItem);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outBundle) {
        super.onSaveInstanceState(outBundle);
        outBundle.putParcelable(QUESTION_ITEM_KEY, defaultItem);
    }

    // Send the score to the ResultsFragment
    public void sendScore(int score) {

        resultsFragment.getAnswer(score);

    }

    @Override
    public void newItemCreated(QuestionItem newItem) {
        // Get a reference to SurveyQuestionDetail fragment
        // notify it for update
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.display_survey_view_container, SurveyQuestionDetailFragment.newInstance(newItem));
        ft.commit();
    }
}
