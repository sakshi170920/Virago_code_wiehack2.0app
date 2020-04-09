package com.example.pcos_app;

public class PCOSQuestions {

    private String Diagnosis;
    private String mood;
    private String exercise;
    private String conceive;
    private String periods;

    public String getDiagnosis() {
        return Diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        Diagnosis = diagnosis;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public String getExercise() {
        return exercise;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }

    public String getConceive() {
        return conceive;
    }

    public void setConceive(String conceive) {
        this.conceive = conceive;
    }

    public String getPeriods() {
        return periods;
    }

    public void setPeriods(String periods) {
        this.periods = periods;
    }

    public PCOSQuestions(String diagnosis, String mood, String exercise, String conceive, String periods) {
        Diagnosis = diagnosis;
        this.mood = mood;
        this.exercise = exercise;
        this.conceive = conceive;
        this.periods = periods;
    }
}
