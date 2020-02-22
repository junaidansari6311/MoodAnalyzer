package com.moodanalyzer;

import org.junit.Assert;
import org.junit.Test;

public class MoodAnalyzerTest {

    @Test
    public void givenMood_WhenSadMood_ShouldReturnSad() {
        String mood = null;
        try {
            MoodAnalyzer moodAnalyzer = new MoodAnalyzer("This is sad mood");
            mood = moodAnalyzer.analyseMood();
            Assert.assertEquals("Sad",mood);
        } catch (MoodAnalysisException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenMood_WhenHappyMood_ShouldReturnHappy() {
        String mood = null;
        try {
            MoodAnalyzer moodAnalyzer = new MoodAnalyzer("This is happy mood");
            mood = moodAnalyzer.analyseMood();
            Assert.assertEquals("Happy",mood);
        } catch (MoodAnalysisException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenMood_WhenNull_ShouldReturnMessage() {
        try {
            MoodAnalyzer moodAnalyzer = new MoodAnalyzer();
            moodAnalyzer.analyseMood();
        } catch (MoodAnalysisException e) {
            Assert.assertEquals(MoodAnalysisException.ExceptionType.ENTERED_NULL,e.type);
        }
    }

    @Test
    public void givenMood_WhenEmptyMessage_ShouldReturnMessage() {
        try {
            MoodAnalyzer moodAnalyzer = new MoodAnalyzer("");
            moodAnalyzer.analyseMood();
        } catch (MoodAnalysisException e) {
            Assert.assertEquals(MoodAnalysisException.ExceptionType.ENTERED_EMPTY,e.type);
        }
    }

    @Test
    public void givenMoodAnalyzerClass_WhenProper_ShouldReturnObject() {
        MoodAnalyzer moodAnalyzer = MoodAnalyzerFactory.createMoodAnalyzer("I'm in Happy mood","com.moodanalyzer.MoodAnalyzer",String.class);
        Assert.assertEquals(new MoodAnalyzer("I'm in Happy mood"),moodAnalyzer);
    }

    @Test
    public void givenMoodAnalyzerDefaultConstructor_WhenMessageAreEqual_ShouldReturnTrue() {
        MoodAnalyzer moodAnalyzer = MoodAnalyzerFactory.createMoodAnalyzer();
        Assert.assertEquals(new MoodAnalyzer(),moodAnalyzer);
    }

    @Test
    public void givenMood_WhenClassNameIncorrect_ShouldReturnException() {
        try {
            MoodAnalyzerFactory.createMoodAnalyzer("Class not found", "com.moodanalyzer.MoodAalyzer",String.class);
        }catch (MoodAnalysisException e){
            Assert.assertEquals(MoodAnalysisException.ExceptionType.CLASS_NOT_FOUND, e.type);
        }
    }

    @Test
    public void givenMood_WhenConstructorParameterIsWrong_ShouldReturnException() {
        try {
            MoodAnalyzerFactory.createMoodAnalyzer("No such method found", "com.moodanalyzer.MoodAnalyzer",Integer.class);
        }catch (MoodAnalysisException e){
            Assert.assertEquals(MoodAnalysisException.ExceptionType.NO_SUCH_METHOD, e.type);
        }
    }

    @Test
    public void givenMessageUsingReflection_WhenProper_ShouldReturnHappy() {
        MoodAnalyzer moodAnalyzer = MoodAnalyzerFactory.createMoodAnalyzer("I'm in Happy Mood", "com.moodanalyzer.MoodAnalyzer", String.class);
        String mood = MoodAnalyzerFactory.methodInvocation( "analyseMood",moodAnalyzer);
        Assert.assertEquals("Happy", mood);
    }

    @Test
    public void givenMessageUsingReflection_WhenImproper_ShouldReturnException() {
        try {
            MoodAnalyzer moodAnalyzer = MoodAnalyzerFactory.createMoodAnalyzer("I'm in Happy Mood", "com.moodanalyzer.MoodAnalyzer", String.class);
            String mood = MoodAnalyzerFactory.methodInvocation("analyzeMoo", moodAnalyzer);
            Assert.assertEquals("Happy", mood);
        }catch (MoodAnalysisException e) {
            Assert.assertEquals(MoodAnalysisException.ExceptionType.NO_SUCH_METHOD,e.type);
        }
    }

    @Test
    public void givenFieldName_WhenProper_ShouldReturnHappy() {
        MoodAnalyzer moodAnalyzer = MoodAnalyzerFactory.createMoodAnalyzer("", "com.moodanalyzer.MoodAnalyzer", String.class);
        String mood = MoodAnalyzerFactory.settingField(moodAnalyzer,"message","I'm in Happy mood");
        Assert.assertEquals("Happy",mood);
    }
    @Test
    public void givenFieldName_WhenImProper_ShouldReturnException() {
        try {
            MoodAnalyzer moodAnalyzer = MoodAnalyzerFactory.createMoodAnalyzer("", "com.moodanalyzer.MoodAnalyzer", String.class);
            String mood = MoodAnalyzerFactory.settingField(moodAnalyzer, "mesage", "I'm in Happy mood");
            Assert.assertEquals("Happy", mood);
        } catch (MoodAnalysisException e) {
            Assert.assertEquals(MoodAnalysisException.ExceptionType.NO_SUCH_FIELD, e.type);
        }
    }

    @Test
    public void givenFieldName_WhenNull_ShouldReTurnException() {
            try {
                MoodAnalyzer moodAnalyzer = MoodAnalyzerFactory.createMoodAnalyzer("", "com.moodanalyzer.MoodAnalyzer", String.class);
                String mood = MoodAnalyzerFactory.settingField(moodAnalyzer, "message", null);
                Assert.assertEquals("Happy", mood);
            } catch (MoodAnalysisException e){
                Assert.assertEquals(MoodAnalysisException.ExceptionType.INVOCATION_ISSUE,e.type);
            }
    }
}
