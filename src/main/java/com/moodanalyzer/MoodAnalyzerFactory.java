package com.moodanalyzer;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
    public static String methodInvocation(String methodName,Object moodAnalyzer) {
        try {
            Method declaredMethod = moodAnalyzer.getClass().getDeclaredMethod(methodName);
            return  (String) declaredMethod.invoke(moodAnalyzer);
        } catch (NoSuchMethodException e) {
            throw new MoodAnalysisException(e.getMessage(),MoodAnalysisException.ExceptionType.NO_SUCH_METHOD);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            throw new MoodAnalysisException(e.getMessage(),MoodAnalysisException.ExceptionType.INVOCATION_ISSUE);
        }
        return null;
    }

    public static String settingField(Object moodAnalyzer, String fieldName, String fieldValue) {
        try {
            Field messageField = moodAnalyzer.getClass().getDeclaredField(fieldName);
            messageField.set(moodAnalyzer,fieldValue);
            return MoodAnalyzerFactory.methodInvocation("analyseMood",moodAnalyzer);
        } catch (NoSuchFieldException e) {
            throw new MoodAnalysisException(e.getMessage(),MoodAnalysisException.ExceptionType.NO_SUCH_FIELD);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

}
