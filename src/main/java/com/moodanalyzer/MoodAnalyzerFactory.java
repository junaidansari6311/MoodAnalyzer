package com.moodanalyzer;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class MoodAnalyzerFactory {

    public static MoodAnalyzer createMoodAnalyzer(String message,String className,Class classType) {
        try {
            Constructor<?> moodConstructor = Class.forName(className).getConstructor(classType);
            Object moodObject = moodConstructor.newInstance(message);
            return (MoodAnalyzer) moodObject;
        } catch (NoSuchMethodException e) {
            throw new MoodAnalysisException("No such method",MoodAnalysisException.ExceptionType.NO_SUCH_METHOD);
        } catch (ClassNotFoundException e) {
            throw new MoodAnalysisException("Class not found",MoodAnalysisException.ExceptionType.CLASS_NOT_FOUND);
        }catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static MoodAnalyzer createMoodAnalyzer() {
        try {
            Constructor<?> moodConstructor = Class.forName("com.moodanalyzer.MoodAnalyzer").getConstructor();
            Object moodObject = moodConstructor.newInstance();
            return (MoodAnalyzer) moodObject;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
