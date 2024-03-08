package io.javabrains;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestReporter;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS) //only one inntance for all methods
@DisplayName("when running MathUtils")
class MathUtilsTest {

	MathUtils mathUtils;
	TestInfo testInfo;
	TestReporter testReporter;
//	@BeforeAll
//	 void beforeAllInit() { //no need of static for @TestInstance(TestInstance.Lifecycle.PER_CLASS) case
//		System.out.println("run before all");
//	}

//	@BeforeAll
//	static void beforeAllInit() {
//		System.out.println("run before all");
//	}
	/*
	 * TestInfo and TestReporter are actually java interfaces, not classes. don't
	 * worry about the underlying implementation.use interfaces directly
	 */
	@BeforeEach
	void init(TestInfo testInfo, TestReporter testReporter) {
		this.testInfo = testInfo;
		this.testReporter = testReporter;
		mathUtils = new MathUtils();
	}

	@AfterEach
	void cleanUp() {
		System.out.println("cleaning up");
	}

	@Test
	@DisplayName("testing add method")
	void testAdd() {
		int expected = 2;
		int actual = mathUtils.add(1, 1);
		assertEquals(expected, actual, () -> "should return sum " + expected + " but return " + actual);
	}

	@Nested
	@DisplayName("add method")
	class AddTest {

		@Test
		@DisplayName("adding positive numbers")
		void testAddPositive() {
			assertEquals(2, mathUtils.add(1, 1), "should return the right sum");
		}

		@Test
		@DisplayName("adding negative numbers")
		void testAddNegative() {
			assertEquals(-2, mathUtils.add(-1, -1), "should return the right sum");
		}
	}

	@Test
	void testComputeCircleRadius() {
		assertEquals(314, mathUtils.computeCircleArea(10), "should return right circle area");
	}

	@Test
	void testDivide() {
		assertThrows(ArithmeticException.class, () -> mathUtils.divide(1, 0), "divide by zero should throw exception");

	}

	@Test
	@Disabled
	void testDisable() {
		fail("should be disable");

	}

	/*
	 * Conditional Execution
	 * 
	 * @EnableOnOs(OS.Linux)
	 * 
	 * @EnableOnJre(JRE.JAVA_11)
	 * 
	 * @EnableIf
	 * 
	 * @EnableIfSystemProperty
	 * 
	 * @EnableIfEnvironmentVariable
	 */
	// assume
	@Test
	void isServerUp() {
		boolean isServerUp = false;
		assumeTrue(isServerUp);
		System.out.println("server is up");
	}

	@Test
	@DisplayName("multiply method")
	void testMultiply() {
		System.out.println("running "+ testInfo.getDisplayName());
		assertAll(() -> assertEquals(4, mathUtils.multiply(2, 2)), () -> assertEquals(0, mathUtils.multiply(2, 0)),
				() -> assertEquals(-2, mathUtils.multiply(2, -1)));
	}
}
