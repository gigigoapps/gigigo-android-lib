package com.gigigo.ggglib.network.context.mappers;

import com.gigigo.ggglib.model.TestMock;
import com.gigigo.ggglib.network.context.responses.TestMockData;
import com.gigigo.ggglib.mappers.ExternalClassToModelMapper;


public class TestMapper implements ExternalClassToModelMapper<TestMockData, TestMock> {

  @Override public TestMock externalClassToModel(TestMockData testMockData) {
    TestMock testMock = new TestMock();
    testMock.setTest(testMockData.getTest());
    return testMock;
  }

}
