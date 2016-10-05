package com.gigigo.ggglib.context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class TestUtils {

  private static File getFileFromPath(Object obj, String fileName) {
    ClassLoader classLoader = obj.getClass().getClassLoader();
    URL resource = classLoader.getResource(fileName);
    return new File(resource.getPath());
  }

  public static String getContentFromFile(String fileName, Object context) throws Exception {
    File file = getFileFromPath(context, fileName);
    FileInputStream fin = new FileInputStream(file);
    String response = convertStreamToString(fin);
    fin.close();
    return response;
  }

  public static String convertStreamToString(InputStream is) throws Exception {
    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
    StringBuilder sb = new StringBuilder();
    String line;
    while ((line = reader.readLine()) != null) {
      sb.append(line).append("\n");
    }
    reader.close();
    return sb.toString();
  }

  public static void printMemoryInform() {
    System.out.println("Available processors (cores): " +
        Runtime.getRuntime().availableProcessors());

    long allocatedMemory      = (Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory());

    long presumableFreeMemory = Runtime.getRuntime().maxMemory() - allocatedMemory;

  /* Total amount of free memory available to the JVM */
    System.out.println("Free memory (bytes): " +
        presumableFreeMemory);

  /* This will return Long.MAX_VALUE if there is no preset limit */
    long maxMemory = Runtime.getRuntime().maxMemory();
  /* Maximum amount of memory the JVM will attempt to use */
    System.out.println("Maximum memory (bytes): " +
        (maxMemory == Long.MAX_VALUE ? "no limit" : maxMemory));

  /* Total memory currently in use by the JVM */
    System.out.println("Total memory (bytes): " +
        Runtime.getRuntime().totalMemory());

  /* Get a list of all filesystem roots on this system */
    File[] roots = File.listRoots();

  /* For each filesystem root, print some info */
    for (File root : roots) {
      System.out.println("File system root: " + root.getAbsolutePath());
      System.out.println("Total space (bytes): " + root.getTotalSpace());
      System.out.println("Free space (bytes): " + root.getFreeSpace());
      System.out.println("Usable space (bytes): " + root.getUsableSpace());
    }
  }


}
