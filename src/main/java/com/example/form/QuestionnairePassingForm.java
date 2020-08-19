package com.example.form;

public class QuestionnairePassingForm {
    /*
    userAnswers is not rectangular matrix, where each number
    of line is same to number of question in the source questionnaire.
    Each answer that user mark as right, in this matrix will be boolean value 'true'.
     */
    private boolean[][] userAnswers;

    public boolean[][] getUserAnswers() {
        return userAnswers;
    }

    public void setUserAnswers(boolean[][] userAnswers) {
        this.userAnswers = userAnswers;
    }
}
