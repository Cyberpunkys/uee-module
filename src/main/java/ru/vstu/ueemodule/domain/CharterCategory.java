package ru.vstu.ueemodule.domain;

public enum CharterCategory {

    CHARTER("Грамота"),
    THANKS_LETTER("Благодарственное письмо"),
    DIPLOMA("Диплом");

    private final String categoryName;

    CharterCategory(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
