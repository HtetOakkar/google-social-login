package com.test.resttemplate;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ResttemplateApplicationTests {

	Calculator underTest = new Calculator();
	@Test
	void itShouldAddTwoNumbers() {
		//given
		int a = 34;
		int b = 16;
		
		//when
		int result = underTest.add(a, b);
		
		//then
		int expected = 50;
		assertThat(result).isEqualTo(expected);
		
	}

	class Calculator {
		int add(int a, int b) {
			return a + b;
		}
	}
 }
