package com.moodanalyzer;

public class MoodAnalyzer {
    public String message;

    public MoodAnalyzer() {
        message="Default";
    }

    public MoodAnalyzer(String message) {
        this.message=message;
    }

    public String analyseMood(){
        try {
            if(message.isEmpty()){
                throw new MoodAnalysisException("Message should not be empty", MoodAnalysisException.ExceptionType.ENTERED_EMPTY);
            }
            if (message.contains("sad"))
                return "Sad";
            return "Happy";
        }
        catch (NullPointerException ne) {
            throw new MoodAnalysisException("Please enter a proper message", MoodAnalysisException.ExceptionType.ENTERED_NULL);
        }
    }

    public boolean equals(Object another) {
        if(this.message.equals(((MoodAnalyzer) another).message))
            return true;
        return false;
        }
}
