package myMath;

import static org.junit.Assert.*;

import java.text.DecimalFormat;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
/**
 * This class is for debugging polynom class.
 * @author darag
 */
public class PolynomTest
{
	private Polynom positiveP;
	private Polynom negativeP;
	private Polynom copyPP;
	private Polynom copyNP;
	private Polynom temp;
	private final DecimalFormat DF = new DecimalFormat(".#####");

	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{

	}

	@Before
	public void setUp() throws Exception
	{
		// Two Polynoms used for testing
		positiveP = new Polynom(" + 5.6       *    x  ^    3 + 3  5* x^2 + 5");
		negativeP = new Polynom("-008.3145*x^ 4 - 002 .  3 *x^ 3    + 2*   x ^ 2");
		copyPP = new Polynom(positiveP);
		copyNP = new Polynom(negativeP);
	}

	@After
	public void tearDown() throws Exception
	{
		temp = null;
	}

	public void test_equals()
	{
		assertFalse(positiveP.equals(negativeP));
		assertTrue(copyPP.equals(positiveP));
	}

	@Test
	public void test_init_empty()
	{
		temp = new Polynom();
		assertEquals(temp, new Polynom());
		assertTrue(temp.size() == 0);
		assertNotNull(temp); // Should be 0.
	}

	@Test(expected = RuntimeException.class)
	public void test_init_string()
	{
		temp = new Polynom();
		temp.add(new Monom(5.6, 3));
		temp.add(new Monom(35, 2));
		temp.add(new Monom(5, 0));
		assertEquals(positiveP, temp);
		assertEquals(positiveP.toString(), temp.toString());

		temp = new Polynom(negativeP.toString());
		assertEquals(negativeP, temp);

		// Will throw RuntimeException
		temp = new Polynom("Hello");
		temp = new Polynom("-20*x^3+4+58*x^0+6");
		temp = new Polynom("5*x^");
		temp = new Polynom("hell*x^o");

		// TODO add these legal polynoms
		// temp = new Polynom("-1*x");
		// temp = new Polynom("-x");
		// temp = new Polynom("xx");
		// temp = new Polynom("-20x^3+4+58x^0+6");

	}

	@Test
	public void test_init_copy()
	{
		temp = (Polynom) positiveP.copy();
		assertEquals(positiveP, temp);
		assertEquals(copyPP, positiveP);
		assertEquals(copyNP, negativeP);
	}

	@Test
	public void test_addANDsubANDsize_toPolynom()
	{
		temp = new Polynom();
		temp.add(positiveP);
		assertEquals(temp, positiveP);
		assertTrue(temp.size() == 3);

		temp.add(negativeP);
		assertEquals(temp, new Polynom("-8.3145*x^4 + 3.3*x^3 + 37*x^2 + 5"));
		assertTrue(temp.size() == 4);

		temp.substract(new Polynom("1.6855*x^4 + 0.3*x^3 + 40*x^2 + 5 - 7*x^5"));
		assertEquals(temp, new Polynom("7*x^5 - 10*x^4 + 3*x^3 - 3*x^2"));
		temp.substract(new Polynom("7*x^5 - 10*x^4 + 3*x^3 - 3*x^2"));
		assertEquals(temp, new Polynom());
		assertTrue(temp.size() == 0);
	}

	@Test
	public void test_multiply_byPolynom()
	{
		temp = new Polynom(positiveP);
		temp.multiply(new Polynom("1"));
		assertEquals(positiveP, temp);

		temp.multiply(new Polynom("5*x^2 + 2*x^1"));
		assertEquals(new Polynom("28*x^5 + 186.2*x^4 + 70*x^3 + 25*x^2 + 10*x^1"), temp);

		temp.multiply(new Polynom("0"));
		assertEquals(new Polynom(), temp);

		temp.multiply(new Polynom("5*x^5"));
		assertEquals(new Polynom(), temp);
	}

	@Test
	public void test_multiply_byMonom()
	{
		temp = new Polynom(positiveP);
		temp.multiply(new Monom("1"));
		assertEquals(positiveP, temp);

		temp.multiply(new Monom("-3.7*x^3"));
		assertEquals(new Polynom("-20.72*x^6 - 129.5*x^5 - 18.5*x^3"), temp);

		temp.multiply(new Monom("0"));
		assertEquals(new Polynom(), temp);

		temp.multiply(new Monom("5"));
		assertEquals(new Polynom(), temp);
	} // 5.6*x^3 + 35*x^2 + 5

	@Test
	public void test_f()
	{
		assertTrue(positiveP.f(5) == 1580);
		assertTrue(negativeP.f(10) == -85245);
		assertTrue(negativeP.f(0) == 0 && positiveP.f(0) == 5);
	}

	@Test
	public void test_isZero()
	{

		temp = new Polynom();

		assertTrue(temp.isZero());

		temp.add(new Monom(0, 2));

		assertTrue(temp.isZero());

		temp.add(new Monom("1*x^3"));

		assertFalse(temp.isZero());

		temp.multiply(new Polynom("0"));

		assertTrue(temp.isZero());

	}

	@Test
	public void test_derivative()
	{
		temp = (Polynom) (new Polynom("-6*x^5 - 4*x^3 + 2*x^2 + 10*x^1 -5")).derivative();
		assertEquals(new Polynom("-30*x^4 - 12*x^2 + 4*x^1 + 10"), temp);
	}

	@Test
	public void test_area()
	{
		temp = new Polynom("0.2*x^2 - 5");

		assertTrue(Math.abs(temp.area(-8, 8, 1) - 54.933333) < 1);
		assertTrue(Math.abs(temp.areaUnderAbove(-8, 8, 1, true) - 21.6) < 1);
		assertTrue(Math.abs(temp.areaUnderAbove(-8, 8, 1, false) - 33.33333333) < 1);

		assertTrue(Math.abs(temp.area(-8, 8, 0.1) - 54.933333) < 0.1);
		assertTrue(Math.abs(temp.areaUnderAbove(-8, 8, 0.1, true) - 21.6) < 0.1);
		assertTrue(Math.abs(temp.areaUnderAbove(-8, 8, 0.1, false) - (33.33333333)) < 0.1);

		assertTrue(Math.abs(temp.area(-8, 8, 0.002) - 54.933333) < 0.002);
		assertTrue(Math.abs(temp.areaUnderAbove(-8, 8, 0.002, true) - 21.6) < 0.002);
		assertTrue(Math.abs(temp.areaUnderAbove(-8, 8, 0.002, false) - 33.33333333) < 0.002);
	}

	@Test
	public void test_extreme()
	{
		temp = new Polynom("0.2*x^4-1.5*x^3+3.0*x^2-1*x^1-5");
		assertTrue(Math.abs(temp.extremeMinMax(-2, 6, 0.01).get(0) - 0.194) < 0.01);
		assertTrue(Math.abs(temp.extremeMinMax(-2, 6, 0.01).get(1) - 1.753) < 0.01);
		assertTrue(Math.abs(temp.extremeMinMax(-2, 6, 0.01).get(2) - 3.679) < 0.01);
		
		temp = new Polynom("2*x^2");
		assertTrue(Math.abs(temp.extremeMinMax(-2, 6, 0.001).get(0) - 0) < 0.001);
	}

	@Test
	public void test_root()
	{
		temp = new Polynom("0.2*x^2 - 2*x^1 + 2");

		assertTrue(Math.abs(temp.root(0, 4, 1) - 1.127) < 1);
		assertTrue(Math.abs(temp.root(4, 11, 1) - 8.873) < 1);

		assertTrue(Math.abs(temp.root(0, 4, 0.1) - 1.127) < 0.1);
		assertTrue(Math.abs(temp.root(4, 11, 0.1) - 8.873) < 0.1);

		assertTrue(Math.abs(temp.root(0, 4, 0.0002) - 1.127) < 0.0002);
		assertTrue(Math.abs(temp.root(4, 11, 0.0002) - 8.873) < 0.0002);

		temp = new Polynom("1*x^1");

		assertTrue(Math.abs(temp.root(-4, 4, 0.0002) - 0) < 0.0002);
	}

	@Test
	public void test_toString()
	{
		temp = new Polynom();
		assertEquals(temp.toString(), "0");
		temp.add(new Monom(0, 10));
		assertEquals(temp.toString(), "0");
		temp.add(new Monom(5.6, 3));
		temp.add(new Monom(35, 2));
		temp.add(new Monom(5, 0));
		assertEquals(temp.toString(), positiveP.toString());
	}

	@Test
	public void test_addANDsubANDsize_toMonom()
	{
		// 5.6*x^3 + 35*x^2 + 5

		temp = new Polynom();
		assertTrue(temp.size() == 0);

		temp.add(new Monom(17.5, 2));
		assertTrue(temp.getMonom(0).get_coefficient() == 17.5 && temp.getMonom(0).get_power() == 2);
		assertTrue(temp.size() == 1);

		temp.add(new Monom(5.6, 3));
		assertTrue(temp.getMonom(0).get_coefficient() == 5.6 && temp.getMonom(0).get_power() == 3);
		assertTrue(temp.getMonom(1).get_coefficient() == 17.5 && temp.getMonom(1).get_power() == 2);
		assertTrue(temp.size() == 2);

		temp.add(new Monom(17.5, 2));
		assertTrue(temp.getMonom(0).get_coefficient() == 5.6 && temp.getMonom(0).get_power() == 3);
		assertTrue(temp.getMonom(1).get_coefficient() == 35 && temp.getMonom(1).get_power() == 2);
		assertTrue(temp.size() == 2);

		temp.add(new Monom("5"));
		assertEquals(temp, positiveP);
		assertTrue(temp.size() == 3);

		temp.substract(new Monom("35*x^2"));
		assertEquals(temp, new Polynom("5.6*x^3 + 5"));
		assertTrue(temp.size() == 2);

		temp.substract(new Monom("5.6*x^3"));
		temp.substract(new Monom("5"));
		assertEquals(temp, new Polynom());
		assertTrue(temp.size() == 0);
	}

}
