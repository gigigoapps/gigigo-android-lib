package com.gigigo.ggglib.network.mappers;

import com.gigigo.ggglib.mappers.ExternalClassToModelMapper;
import com.gigigo.ggglib.mappers.MapperUtils;
import com.gigigo.ggglib.mappers.ModelToExternalClassMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MapperUtilsTest {

    @Mock ExternalClassToModelMapper responseMapper;

    @Mock ModelToExternalClassMapper requestMapper;

    @Mock Object data;

    @Mock Object response;

    @Test
    public void testCheckNullDataResponseOk() throws Exception {
        when(responseMapper.externalClassToModel(anyObject())).thenReturn(response);

        assertEquals(response, MapperUtils.checkNullDataResponse(responseMapper, data));

        verify(responseMapper).externalClassToModel(anyObject());
    }

    @Test
    public void testCheckNullDataResponseNull() throws Exception {
        assertNull(MapperUtils.checkNullDataResponse(responseMapper, null));

        verify(responseMapper, never()).externalClassToModel(anyObject());
    }

    @Test
    public void checkNullDataRequestOk() throws Exception {
        when(requestMapper.modelToExternalClass(anyObject())).thenReturn(response);

        assertEquals(response, MapperUtils.checkNullDataRequest(requestMapper, data));

        verify(requestMapper).modelToExternalClass(anyObject());
    }

    @Test
    public void testcheckNullDataRequestNull() throws Exception {
        assertNull(MapperUtils.checkNullDataRequest(requestMapper, null));

        verify(requestMapper, never()).modelToExternalClass(anyObject());
    }
}