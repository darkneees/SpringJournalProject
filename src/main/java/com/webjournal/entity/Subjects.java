package com.webjournal.entity;

public enum Subjects {

    MATH("Математика"),
    ALGEBRA("Алгебра"),
    GEOMETRY("Геометрия"),
    HISTORY("История"),
    GEOGRAPHY("География"),
    BIOLOGY("Биология"),
    PHYSICS("Физика"),
    CHEMISTRY("Химия"),
    SOCIAL_SCIENCE("Обществознание"),
    RUSSIAN_LANGUAGE("Русский язык"),
    ENGLISH_LANGUAGE("Английский язык"),
    LITERATURE("Литература"),
    PHYSICAL_CULTURE("Физическая культура"),
    PROCEEDINGS("Труды"),
    INFORMATICS("Информатика");

    private String displayName;

    Subjects(String displayName){
        this.displayName = displayName;
    }

    public String getDisplayName(){
        return displayName;
    }
}
