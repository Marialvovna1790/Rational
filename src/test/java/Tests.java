import org.junit.Test;

import java.math.BigInteger;
import java.util.Arrays;

import static org.junit.Assert.*;

public class Tests {
    @Test
    public void testStandardConstructor() {
        Rational standard = new Rational();
        assertEquals("Standard constructor returns wrong numerator", 0, standard.getNumerator());
        assertEquals("Standard constructor returns wrong denominator", 1, standard.getDenominator());
    }

    @Test(expected = ArithmeticException.class)
    public void testIncorrectDenominatorConstructor() {
        new Rational(1, 0);
    }

    @Test
    public void testNegativeNumerator() {
        Rational rational = new Rational(-3, 2);
        checkMinuses(rational);
    }

    @Test
    public void testNegativeDenominator() {
        Rational rational = new Rational(3, -2);
        checkMinuses(rational);
    }

    private void checkMinuses(Rational rational) {
        assertTrue("Numerator should be negative", rational.getNumerator() < 0);
        assertTrue("Denominator should be positive", rational.getDenominator() > 0);
    }

    @Test
    public void testSimplifyRationalGreaterDenominator() {
        Rational rational = new Rational(3, 6);
        Rational expectedSimplified = new Rational(1, 2);
        assertEquals("Should be simplified", expectedSimplified, rational);
    }

    @Test
    public void testSimplifyRationalGreaterNumerator() {
        Rational rational = new Rational(21, 9);
        Rational expectedSimplified = new Rational(7, 3);
        assertEquals("Should be simplified", expectedSimplified, rational);
    }

    @Test
    public void testUnsimplyfiableRational() {
        Rational rational = new Rational(31, 7);
        Rational expected = new Rational(31, 7);
        assertEquals("Should not be simplified", expected, rational);
    }

    @Test
    public void testAddition() {
        Rational first = new Rational(5, 10);
        Rational second = new Rational(6, 20);
        Rational result = new Rational(4, 5);
        assertEquals("Wrong sum", result, first.plus(second));
    }

    @Test
    public void testSubtraction() {
        Rational first = new Rational(5, 10);
        Rational second = new Rational(2, 4);
        Rational result = new Rational(0, 1);
        assertEquals("Wrong subtract", result, first.minus(second));
    }

    @Test
    public void testSubtractionWithNegativeResult() {
        Rational first = new Rational(5, 10);
        Rational second = new Rational(4, 3);
        Rational result = new Rational(-5, 6);
        assertEquals("Wrong subtract", result, first.minus(second));
    }

    @Test
    public void testMultiply() {
        Rational first = new Rational(1, 10);
        Rational second = new Rational(4, 5);
        Rational result = new Rational(2, 25);
        assertEquals("Wrong multiply", result, first.multiply(second));
    }

    @Test
    public void testMultiplyOverflow() {
        Rational first = new Rational(Integer.MAX_VALUE, 2);
        Rational second = new Rational(Integer.MAX_VALUE, 2);
        Rational result = first.multiply(second);
        assertEquals("Wrong numerator", (long) Integer.MAX_VALUE * Integer.MAX_VALUE, result.getNumerator());
        assertEquals("Wrong denominator", 4, result.getDenominator());
    }

    @Test
    public void testDivide() {
        Rational first = new Rational(3, 5);
        Rational second = new Rational(3, 5);
        Rational result = new Rational(1, 1);
        assertEquals("Wrong divide", result, first.divide(second));
    }

    @Test(expected = ArithmeticException.class)
    public void testDivideWithZero() {
        Rational first = new Rational(1, 10);
        Rational second = new Rational(0, 5);
        first.divide(second);
    }

    @Test
    public void testEquals() {
        Rational first = new Rational(1, 2);
        Rational second = new Rational(1, 2);
        assertTrue("Should be equals", first.equals(second));
    }

    @Test
    public void testLess() {
        Rational first = new Rational(1, 2);
        Rational second = new Rational(3, 4);
        assertTrue("Should be less", first.less(second));
    }

    @Test
    public void testLessWithSameNominator() {
        Rational first = new Rational(1, 2);
        Rational second = new Rational(1, 4);
        assertTrue("Should be less", second.less(first));
    }

    @Test
    public void testLessWithSameDenominator() {
        Rational first = new Rational(3, 4);
        Rational second = new Rational(2, 4);
        assertTrue("Should be less", second.less(first));
    }

    @Test
    public void testLessEquals() {
        Rational first = new Rational(3, 4);
        Rational second = new Rational(3, 4);
        assertTrue("Should be lessEquals", second.lessOrEqual(first));
    }
}