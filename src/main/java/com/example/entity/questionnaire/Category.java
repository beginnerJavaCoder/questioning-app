package com.example.entity.questionnaire;

/*
If in the future, we want to add new categories of questionnaires,
we can do it in 2 different ways:
- add new const in this enumeration (only with changes in source file);
- if planning changes more ambitious, we should rework all category-system. For that:
1) add new user's status MODERATOR, that allows to user create new categories of questionnaires;
2) replace enum Category with global variable, that will contain categories in string representation;
3) write interface for adding new categories by moderators.
 */
public enum Category {
    AUTO,
    FOOD,
    CATS,
    DOGS
}
