package myMath;

import static org.junit.Assert.*;

import java.text.DecimalFormat;
import java.util.Random;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * This class is for debugging polynom class.
 * @author darag
 */
public class MonomTest
{
	private Monom positiveM;
	private Monom negativeM;
	private Monom positiveM_2;
	private Monom negativeM_2;
	private Monom zeroMonom;
	private Monom numberMonom;
	private Monom positiveMonomString;
	private Monom negativeMonomString;
	private Monom copyPM;
	private Monom copyNM;
	private Monom expectedResult;
	private Monom temp;
	private static Random r;

	private final DecimalFormat DF = new DecimalFormat(".#####");

	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		r = new Random();
	}

	@Before
	public void setUp() throws Exception
	{
		positiveM = new Monom(5.6, 2);
		negativeM = new Monom(-5.6, 2);
		positiveM_2 = new Monom(13.303, 3);
		negativeM_2 = new Monom(-8.3145, 4);
		zeroMonom = new Monom(0, 12);
		numberMonom = new Monom(-98745, 0);
		copyPM = new Monom(positiveM);
		copyNM = new Monom(negativeM);
		positiveMonomString = new Monom("5.6*x^2");
		negativeMonomString = new Monom("-5.6*x^2");

	}

	@After
	public void tearDown() throws Exception
	{
		expectedResult = null;
		temp = null;
	}

	@Test
	public void test_init_copy()
	{
		assertNotSame(positiveM, copyPM);
		assertEquals(copyPM, positiveM);
	}

	// TODO handle this test
	@Test(expected = RuntimeException.class)
	public void test_init_string()
	{
		assertEquals(positiveM, positiveMonomString);
		temp = new Monom("5.6x^2");
		temp = new Monom("5.6*^2");
		temp = new Monom("5.6*x2");
		temp = new Monom("5.6*2");
		temp = new Monom("Hello*x^20");
		temp = new Monom("");
		temp = new Monom("5 . 6 * x ^ -2");
		temp = new Monom("+5 . 6 * x ^ +2");
		assertEquals(positiveM, temp);
		temp = new Monom("-98745");
		assertEquals(numberMonom, temp);
		temp = new Monom("-0*x^12");
		assertEquals(zeroMonom, temp);
	}

	@Test(expected = RuntimeException.class)
	public void test_fun_add()
	{
		temp = new Monom(positiveM);
		temp.add(positiveM);
		expectedResult = new Monom("11.2*x^2");
		assertEquals("Expecting 11.2*x^2.", temp, expectedResult);

		temp = new Monom(negativeM);
		temp.add(positiveM);
		expectedResult = new Monom("0");
		assertEquals("Expecting 0.", temp, expectedResult);

		temp = new Monom(positiveM_2);
		temp.add(positiveM);
		fail("Expecting a RuntimeException.");
	}

	@Test(expected = RuntimeException.class)
	public void test_fun_sub()
	{
		temp = new Monom(positiveM);
		temp.sub(positiveM);
		expectedResult = new Monom("0");
		assertEquals(temp, expectedResult);

		temp = new Monom(negativeM);
		temp.sub(positiveM);
		expectedResult = new Monom("-11.2*x^2");
		assertEquals(temp, expectedResult);

		temp = new Monom(positiveM_2);
		temp.sub(positiveM);
		fail("Expecting a RuntimeException.");
	}

	@Test
	public void test_fun_multiply()
	{
		temp = new Monom(positiveM);
		temp.multiply(positiveM_2);
		expectedResult = new Monom(5.6 * 13.303, 3 + 2);
		assertEquals(temp, expectedResult);

		temp.multiply(zeroMonom);
		expectedResult = new Monom(zeroMonom);
		assertEquals(temp, expectedResult);
	}

	@Test
	public void test_fun_derivative()
	{
		temp = new Monom(positiveM);
		positiveM.derivative();
		assertEquals(temp, positiveM);

		temp = temp.derivative();
		expectedResult = new Monom(2 * 5.6, 1);
		assertEquals(expectedResult, temp);
	}

	@Test
	public void test_fun_f()
	{
		double x = 3.22;
		double[] expectedAtX = { 58.06304, -58.06304, 444.13726, -893.83967, 0 };
		double[] atX = { positiveM.f(x), negativeM.f(x), positiveM_2.f(x), negativeM_2.f(x), zeroMonom.f(x) };
		for (int i = 0; i < atX.length; i++)
		{
			assertEquals(DF.format(expectedAtX[i]), DF.format(atX[i]));
		}
	}

	@Test
	public void test_util_toString()
	{
		String[] expectedString = { "5.6*x^2", "-5.6*x^2", "13.303*x^3", "-8.3145*x^4", "0.0*x^0" };
		String[] actualString = { positiveM.toString(), negativeM.toString(), positiveM_2.toString(), negativeM_2.toString(), zeroMonom.toString() };
		for (int i = 0; i < expectedString.length; i++)
		{
			assertEquals(expectedString[i], actualString[i]);
		}
	}

	@Test(expected = RuntimeException.class)
	public void test_util_isValid()
	{
		double randomCoeff = -100 + (200) * r.nextDouble();
		int wrongRandomPow = (int) (-2000 + (1999) * r.nextDouble());
		temp = new Monom(randomCoeff, wrongRandomPow);
	}
}
