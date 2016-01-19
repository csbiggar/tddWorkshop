package com.tddworkshop;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class ClassUnderTestTest {

    @InjectMocks
    ClassUnderTest classUnderTest;

    @Mock
    DependencyClass dependencyClass;

    @Before
    public void setUp() throws Exception {
        when(dependencyClass.method(anyInt())).thenReturn("A mocked response");
    }

    @Test
    public void junitMockitTest(){
        assertThat(classUnderTest.method(1,2), containsString("mocked"));
    }

}
