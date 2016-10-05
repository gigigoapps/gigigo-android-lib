package com.gigigo.ggglib.permissions;

import android.app.Activity;
import android.view.ViewGroup;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class PermissionsUIViewsTest extends TestCase {

  @Mock
  Activity activity;

  @Mock ViewGroup viewGroup;

  @Test
  public void shouldReturnViewGroupWhenActivityIsNotNull(){

    when(activity.findViewById(anyInt())).thenReturn(viewGroup);

    ViewGroup viewGroup = PermissionsUIViews.getAppContainer(activity);

    assertSame(this.viewGroup, viewGroup);
    verify(activity).findViewById(anyInt());

  }

  @Test(expected = NullContainerException.class)
  public void shouldThrowNullContainerExceptionWhenActivityIstNull(){
    PermissionsUIViews.getAppContainer(null);
  }
}