package com.gigigo.ggglib.network.context.responses;

import com.google.gson.annotations.SerializedName;


public class TestMockData {

  @SerializedName("test")
  private String test;

  public String getTest() {
    return test;
  }

  public void setTest(String test) {
    this.test = test;
  }
}
