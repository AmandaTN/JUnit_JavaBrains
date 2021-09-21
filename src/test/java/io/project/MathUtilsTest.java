package io.project;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import static org.junit.jupiter.api.Assumptions.*;

import static  org.junit.jupiter.api.Assertions.*;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("When running MathUtils")
public class MathUtilsTest {

    MathUtils mathUtils;
    TestInfo testInfo;
    TestReporter testReporter;
    @BeforeAll
    //SHOULD be static
    static void beforeAllInit(){
        System.out.println("This need to run before all");
    }

    @BeforeEach
    void init(TestInfo testInfo, TestReporter testReporter){
        this.testInfo = testInfo;
        this.testReporter = testReporter;
        mathUtils = new MathUtils();
        testReporter.publishEntry("Running " + testInfo.getDisplayName() + " with tag " + testInfo.getTags());
    }

    @AfterEach
    void cleanUp(){
        System.out.println(("Cleaning up..."));
    }

    @Nested
    @DisplayName("Add Method.")
    @Tag("Math")
    class AddTest {
        @Test
        @DisplayName("Testing add method for +")
        void testAddPositive() {
            assertEquals(2,mathUtils.add(1,1),() -> "The add method should add two numbers.");;
        }

        @Test
        @DisplayName("Testing add method for -")
        void testAddNegative() {
            assertEquals(-2,mathUtils.add(4,-6),() -> "The add method should add two numbers.");;
        }
    }

    @Test
    @Tag("Math")
    @DisplayName("Multiply Method.")
    void testMultiply(){
        assertAll(
                () -> assertEquals(4, mathUtils.multiply(2,2)),
                () -> assertEquals(0, mathUtils.multiply(2,0)),
                () -> assertEquals(-2, mathUtils.multiply(2,-1))
        );
    }

    @Test
    @Tag("Circle")
    @RepeatedTest(3)
    @DisplayName("Circle Area Method")
    void testComputeCircleArea(){
        assertEquals(314.1592653589793, mathUtils.computeCircleArea(10), () -> "Should return right circle area.");
    }

    @Test
    @Tag("Math")
    @EnabledOnOs(OS.MAC)
    void testDivide(){
        boolean someValue = true;
        //This test only will be excuted it the assumption is TRUE
        assumeTrue(someValue);
        assertThrows(ArithmeticException.class, () -> mathUtils.divide(1,0), () -> "Divide by 0, should throw.");
    }

    @Test
    @Disabled
    @DisplayName("This is a disable method")
    void testDisable(){
        fail("This tes should fail");
    }
}
