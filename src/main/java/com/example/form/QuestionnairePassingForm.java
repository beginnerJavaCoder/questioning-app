package com.example.form;

public class QuestionnairePassingForm {
    /*
    userAnswers is not rectangular matrix, where each number
    of line is same to number of question in the source questionnaire.
    Each answer that user mark as right, in this matrix will be boolean value 'true'.
     */
    private boolean[][] userAnswers;
    /*
    Id of user who passes questionnaire.
     */
    private Integer userId;

    public boolean[][] getUserAnswers() {
        return userAnswers;
    }

    public void setUserAnswers(boolean[][] userAnswers) {
        this.userAnswers = userAnswers;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
