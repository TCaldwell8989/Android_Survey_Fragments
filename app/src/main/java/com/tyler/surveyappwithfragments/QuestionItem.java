package com.tyler.surveyappwithfragments;

import android.os.Parcel;
import android.os.Parcelable;

/**
 *  A class to hold a question and two answers
 */
public class QuestionItem implements Parcelable {

    private String question;
    private String answer1;
    private String answer2;

    public QuestionItem(Parcel in) {
        question = in.readString();
        answer1 = in.readString();
        answer2 = in.readString();
    }

    public QuestionItem(String question, String answer1, String answer2) {
        this.question = question;
        this.answer1 = answer1;
        this.answer2 = answer2;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    @Override
    public String toString() {
        return ("Question: " + question + " Answer1: " + answer1 + " Answer2: " + answer2);
    }

    // Code required by the Parcelable interface. If QuestionItem is parceable, can send as Extra
    static final Parcelable.Creator<QuestionItem> CREATOR = new Parcelable.Creator<QuestionItem>() {
        @Override
        public QuestionItem createFromParcel(Parcel in) {
            return new QuestionItem(in);
        }

        @Override
        public QuestionItem[] newArray(int size) {
            return new QuestionItem[size];
        }
    };

    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(question);
        dest.writeString(answer1);
        dest.writeString(answer2);
    }

}
