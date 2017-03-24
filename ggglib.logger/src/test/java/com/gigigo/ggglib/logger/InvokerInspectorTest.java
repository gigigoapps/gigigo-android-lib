package com.gigigo.ggglib.logger;

import org.junit.Test;

import static org.junit.Assert.*;


public class InvokerInspectorTest {
  @Test public void findInvokerSuccess(){
    InvokerInspector invokerInspector = new InvokerInspector();
    invokerInspector.calculateInvoker(0, InvokerInspectorTest.class);
    assertEquals(InvokerInspectorTest.class.getSimpleName()+".java", invokerInspector.obtainInvokerClassName());
    assertNotEquals(invokerInspector.obtainInvokerClassName(), "NotFound");
    assertNotEquals(invokerInspector.obtainInvokerLine(), "(NotFound:0)");
  }

  @Test public void findInvokerFail(){
    InvokerInspector invokerInspector = new InvokerInspector();
    invokerInspector.calculateInvoker(-1, Object.class);
    assertNotEquals(InvokerInspectorTest.class.getName(), invokerInspector.obtainInvokerClassName());
    assertEquals(invokerInspector.obtainInvokerClassName(), "NotFound");
    assertEquals(invokerInspector.obtainInvokerLine(), "(NotFound:0)");
  }

  @Test public void findInvokerBadParams(){
    InvokerInspector invokerInspector = new InvokerInspector();
    invokerInspector.calculateInvoker(-1, null);
    assertNotEquals(InvokerInspectorTest.class.getName(), invokerInspector.obtainInvokerClassName());
    assertEquals(invokerInspector.obtainInvokerClassName(), "NotFound");
    assertEquals(invokerInspector.obtainInvokerLine(), "(NotFound:0)");
  }
}
