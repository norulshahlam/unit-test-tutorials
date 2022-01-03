package com.shah.unittest.unittesttutorials.business;

/*
play around with mockito by moking the List.class

2. also use of verify() - 
to test number of method invocations too. We can test exact number of times, at least once, at least, at most number of invocation times for a mocked method.
https://www.journaldev.com/21855/mockito-verify

3. use of argumentCaptor() -
Use it to capture argument values for further assertions. 

Mockito verifies argument values in natural java style: by using an equals() method.This is also the recommended way of matching arguments because it makes tests clean & simple.In some situations though, it is helpful to assert on certain arguments after the actual verification.

4. spying() - 
Mockito provides option to create spy on real objects. When spy is called, then actual method of real object is called.
https://www.tutorialspoint.com/mockito/mockito_spying.htm

The difference is that in mock, you are creating a complete mock or fake object while in spy, there is the real object and you just spying or stubbing specific methods of it.

When using mock objects, the default behavior of the method when not stub is do nothing. Simple means, if its a void method, then it will do nothing when you call the method or if its a method with a return then it may return null, empty or the default value.

While in spy objects, of course, since it is a real method, when you are not stubbing the method, then it will call the real method behavior. If you want to change and mock the method, then you need to stub it.

 */
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ListMockTest1a {
	@Mock
	List<String> mock;
	
	@AfterEach
	void tearDown() {
		mock=null;
	}

	@Test
	void size() {
		// BASICS IN USING MOCK - GIVEN, WHEN, THEN
		when(mock.size()).thenReturn(5);
		assertEquals(5, mock.size());
	}

	@Test
	void diffValues() {
		// when this mock is called the first time, return 5. on second time, return 10.
		when(mock.size()).thenReturn(5).thenReturn(10);
		assertEquals(5, mock.size());
		assertEquals(10, mock.size());
	}

	@Test
	void withParam() {
		when(mock.get(0)).thenReturn("shah");
		assertEquals("shah", mock.get(0));
	}

	@Test
	public void returnWithGenericParameters() {
		// HERE INSTEAD OF DEFINING MOCK WITH SPECIFIC VALUE, U CAN DEFINE ANY VALUE (OR
		// ANY DATATYPES).
		// ALSO CALLED ARGUMENT MATCHERS
		when(mock.get(anyInt())).thenReturn("in28Minutes");

		assertEquals("in28Minutes", mock.get(0));
		assertEquals("in28Minutes", mock.get(1));
	}

	@Test
	void mockToThrowException() {
		when(mock.get(0)).thenThrow(new RuntimeException());
		Assertions.assertThrows(RuntimeException.class, () -> {
			mock.get(0);
		});
	}

	@Test
	void BDDApproach() {
		// GIVEN
		when(mock.get(0)).thenReturn("shah");

		// WHEN
		Object actual = mock.get(0);

		// THEN
		assertEquals("shah", actual);
	}

	@Test
	void mockWithoutDefiningAnything() {
		// WHEN U DONT DEFIONE ANYTHING, IT WILL RETURN NULL.
		assertEquals(null, mock.get(0));
	}

	@Test
	public void verificationBasics() {
		// SUT
		String value1 = mock.get(0);
		String value2 = mock.get(1);

		verify(mock).get(0);

		// VERIFY THAT .get() can be called twice.
		verify(mock, times(2)).get(anyInt());
		verify(mock, atLeast(1)).get(anyInt());
		verify(mock, atLeastOnce()).get(anyInt());

		// VERIFY THAT .get() can be max twice.
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
		//MOCKING WORKS IF U SET IT TO RETURN SOMETHING
		mock.add("Test");
		mock.add("Test2");
		System.out.println(mock.size());// 0
		when(mock.size()).thenReturn(5);
		System.out.println(mock.size());// 5
	}

	@Test
	public void spying() {
		// SPY IS PARTIAL MOCK, WHERE U CAN IMPLEMENT REAL VALUES. IN REAL APP, MOCK IS USED 99% INSTEAD OF SPY. AVOID USING SPY IF POSSBILE AS IT IS HARD TO MAINTAIN
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

	}
}
