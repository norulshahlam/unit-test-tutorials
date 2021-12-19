package com.shah.unittest.unittesttutorials.spike;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.startsWith;
import static org.hamcrest.Matchers.*;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

/*
Hamcrest is a framework for writing matcher objects allowing ‘match’ rules to be defined declaratively. There are a number of situations where matchers are invaluable, such as UI validation or data filtering, but it is in the area of writing flexible tests that matchers are most commonly used
 */
public class HamcrestMatchersTest1b {

	@Test
	public void learning() {
		List<Integer> numbers = Arrays.asList(12, 15, 45);

		//	U CAN USE THIS APPORACH
		assertThat(numbers, hasSize(3));
		assertThat(numbers, hasItems(12, 45));
		assertThat(numbers, everyItem(greaterThan(10)));
		assertThat(numbers, everyItem(lessThan(100)));
		//	 ... OR THE SHORTHAND APPORACH
		assertThat(numbers).hasSize(3).contains(12, 45).allMatch(x -> x > 10).allMatch(x -> x < 100)
				.noneMatch(x -> x < 0);

		assertThat("", isEmptyString());
		assertThat("ABCDE", containsString("BCD"));
		assertThat("ABCDE", startsWith("ABC"));
		assertThat("ABCDE", endsWith("CDE"));

		assertThat("").isEmpty();
		assertThat("ABCDE").contains("BCD").startsWith("ABC").endsWith("CDE");

	}
}
