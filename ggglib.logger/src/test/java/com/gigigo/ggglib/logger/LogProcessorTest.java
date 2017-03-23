package com.gigigo.ggglib.logger;

import com.gigigo.ggglib.logger.GGGLogImpl;
import com.gigigo.ggglib.logger.LogProcessor;
import org.junit.Test;

import static org.junit.Assert.*;

public class LogProcessorTest {

  @Test public void logProcessorTraceTest() throws Exception {

    LogProcessor logProcessor = new LogProcessor(true);

    String tag =  "TAG";
    String text =  "TEXT";

    String processedTag = logProcessor.processTagToShow(tag);
    String processedText = logProcessor.processTextToShow(text, 0);

    assertEquals(tag, processedTag);
    assertNotEquals(text, processedText);
    assertTrue(processedText.contains(text) && processedText.contains(getClass().getSimpleName()));

  }

  @Test public void logProcessorWithoutTraceTest() throws Exception {

    LogProcessor logProcessor = new LogProcessor(false);

    String tag =  "TAG";
    String text =  "TEXT";

    String processedTag = logProcessor.processTagToShow(tag);
    String processedText = logProcessor.processTextToShow(text, 0);

    assertEquals(tag, processedTag);
    assertEquals(text, processedText);
  }

  @Test public void logProcessorNullValuesTest() throws Exception {

    LogProcessor logProcessor = new LogProcessor(false);

    String processedTag = logProcessor.processTagToShow(null);
    String processedText = logProcessor.processTextToShow(null, 0);

    assertEquals(processedTag, GGGLogImpl.class.getSimpleName());
    assertEquals(processedText, null);

    logProcessor = new LogProcessor(true);

    processedTag = logProcessor.processTagToShow(null);
    processedText = logProcessor.processTextToShow(null, 0);

    assertNotEquals(processedTag, GGGLogImpl.class.getSimpleName());
    assertTrue(processedText.contains("null") && processedText.contains(getClass().getSimpleName()));
  }
}
