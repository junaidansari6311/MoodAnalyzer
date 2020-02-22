package com.moodanalyzer;

public class MoodAnalysisException extends RuntimeException {

    public ExceptionType type;
    private String message;

    public enum ExceptionType
    {
        ENTERED_NULL,ENTERED_EMPTY,CLASS_NOT_FOUND,NO_SUCH_METHOD,NO_SUCH_FIELD,INVOCATION_ISSUE;
    }
    public MoodAnalysisException(String message,ExceptionType type) {
        this.message=message;
        this.type=type;
    }
}
