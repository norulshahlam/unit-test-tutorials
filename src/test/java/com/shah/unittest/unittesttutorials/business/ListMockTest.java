package com.shah.unittest.unittesttutorials.business;
/*
furhter play around with mockito moking the List.class

2. also use of verify() - 
to test number of method invocations too. We can test exact number of times, at least once, at least, at most number of invocation times for a mocked method.
https://www.journaldev.com/21855/mockito-verify

3. use of argumentCaptor() -
Use it to capture argument values for further assertions. 

Mockito verifies argument values in natural java style: by using an equals() method.This is also the recommended way of matching arguments because it makes tests clean & simple.In some situations though, it is helpful to assert on certain arguments after the actual verification.

4. spying() - 
Mockito provides option to create spy on real objects. When spy is called, then actual method of real object is called.
https://www.tutorialspoint.com/mockito/mockito_spying.htm

 */
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

class ListMockTest {
	List<String> mock = mock(List.class);

	@Test
	void size() {
		List mock = mock(List.class);
		when(mock.size()).thenReturn(5);
		assertEquals(5, mock.size());
	}

	@Test
	void diffValues() {
		List mock = mock(List.class);
		when(mock.size()).thenReturn(5).thenReturn(10);
		assertEquals(5, mock.size());
		assertEquals(10, mock.size());
	}

	@Test
	void withParam() {
		List mock = mock(List.class);
		when(mock.get(0)).thenReturn("shah");
		assertEquals("shah", mock.get(0));
	}

	@Test
	public void returnWithGenericParameters() {
		when(mock.get(anyInt())).thenReturn("in28Minutes");

		assertEquals("in28Minutes", mock.get(0));
		assertEquals("in28Minutes", mock.get(1));
	}

	@Test
	public void verificationBasics() {
		// SUT
		String value1 = mock.get(0);
		String value2 = mock.get(1);

		// Verify
		verify(mock).get(0);
		verify(mock, times(2)).get(anyInt());
		verify(mock, atLeast(1)).get(anyInt());
		verify(mock, atLeastOnce()).get(anyInt());
		verify(mock, atMost(2)).get(anyInt());
		verify(mock, never()).get(2);
	}

	@Test
	public void argumentCapturing() {

		// SUT
		mock.add("SomeString");

		// Verification
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		verify(mock).add(captor.capture());

		assertEquals("SomeString", captor.getValue());

	}

	@Test
	public void multipleArgumentCapturing() {

		// SUT
		mock.add("SomeString1");
		mock.add("SomeString2");

		// Verification
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

		verify(mock, times(2)).add(captor.capture());

		List<String> allValues = captor.getAllValues();

		assertEquals("SomeString1", allValues.get(0));
		assertEquals("SomeString2", allValues.get(1));

	}

	@Test
	public void mocking() {
		ArrayList<String> arrayListMock = mock(ArrayList.class);
		System.out.println(arrayListMock.get(0));// null
		System.out.println(arrayListMock.size());// 0
		arrayListMock.add("Test");
		arrayListMock.add("Test2");
		System.out.println(arrayListMock.size());// 0
		when(arrayListMock.size()).thenReturn(5);
		System.out.println(arrayListMock.size());// 5
	}

	@Test
	public void spying() {
		ArrayList<String> arrayListSpy = spy(ArrayList.class);
		arrayListSpy.add("Test0");
		System.out.println(arrayListSpy.get(0));// Test0
		System.out.println(arrayListSpy.size());// 1
		arrayListSpy.add("Test");
		arrayListSpy.add("Test2");
		System.out.println(arrayListSpy.size());// 3

		when(arrayListSpy.size()).thenReturn(5);
		System.out.println(arrayListSpy.size());// 5

		arrayListSpy.add("Test4");
		System.out.println(arrayListSpy.size());// 5

		verify(arrayListSpy).add("Test4");
		
		/*
		 The difference is that in mock, you are creating a complete mock or fake object while in spy, there is the real object and you just spying or stubbing specific methods of it.

When using mock objects, the default behavior of the method when not stub is do nothing. Simple means, if its a void method, then it will do nothing when you call the method or if its a method with a return then it may return null, empty or the default value.

While in spy objects, of course, since it is a real method, when you are not stubbing the method, then it will call the real method behavior. If you want to change and mock the method, then you need to stub it.
		 */
	}

}
