package com.spring.demo.service;

import org.assertj.core.util.Lists;
import org.assertj.core.util.Maps;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.engine.support.hierarchical.Node;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class MockTest1 {

    @Mock
    Random random;

    @BeforeEach
    public void before() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void test01() {
        Random mockRandom = Mockito.mock(Random.class);
        Assertions.assertEquals(0, mockRandom.nextInt());

        Mockito.when(mockRandom.nextInt()).thenReturn(100);
        Assertions.assertEquals(100, mockRandom.nextInt());
    }

    @Test
    public void test02() {
        Mockito.when(random.nextInt()).thenReturn(100);
        Assertions.assertEquals(100, random.nextInt());
    }

    @Test
    public void test03() {
        Random mockRandom = Mockito.mock(Random.class);
        Mockito.when(mockRandom.nextInt()).thenReturn(100);
        //Assert.assertEquals(100, mockRandom.nextInt());
    }

    @Test
    public void testReturnValueInDependentOnMethodParameter()  {
        Comparable<Integer> c= mock(Comparable.class);
        when(c.compareTo(anyInt())).thenReturn(-1);
        //assert
        assertEquals(-1, c.compareTo(9));
    }

    @Test
    public void testLinkedListSpyWrong() {
        // 让我们来模拟一个LinkedList
        List<String> list = new LinkedList<>();
        List<String> spy = spy(list);

        // spy.get(0)将会调用真实的方法
        // 将会抛出 IndexOutOfBoundsException (list是空的)
        when(spy.get(0)).thenReturn("foo");
        assertEquals("foo", spy.get(0));
    }

    @Test
    public void testLinkedListSpyCorrect() {
        // 让我们来模拟一个LinkedList
        List<String> list = new LinkedList<>();
        List<String> spy = spy(list);
        // 必须使用doReturn来插桩
        doReturn("foo").when(spy).get(0);
        assertEquals("foo", spy.get(0));
    }

    public static void main(String[] args) {

    }
}