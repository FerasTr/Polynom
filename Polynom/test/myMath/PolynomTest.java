package myMath;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PolynomTest
{
	private Polynom positiveP;
	private Polynom negativeP;
	private Polynom positiveP_2;
	private Polynom negativeP_2;
	private Polynom zeroPolynom;
	private Polynom copyPP;
	private Polynom copyNP;
	private Polynom expectedResult;
	private Polynom temp;
	
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
		positiveP = new Polynom("5.6*x^3+35*x^2+5*x^0");
		negativeP = new Polynom("-5.6*x^3-35*x^2-5*x^0");
		positiveP_2 = new Polynom("36.5*x^4+2.3*x^3-1*x^2");
		negativeP_2 = new Polynom("-8.3145*x^4-2.3*x^3+2*x^2");
		zeroPolynom = new Polynom("0");
		copyPP = new Polynom(positiveP);
		copyNP = new Polynom(negativeP_2);
	}

	@After
	public void tearDown() throws Exception
	{
		expectedResult = null;
		temp = null;
	}

	@Test
	public void test_init_empty()
	{
		temp = new Polynom();
		assertNotNull(temp); // Should be 0.
	}

	@Test
	public void test_init_string()
	{
		
	}

	@Test
	public void test_init_copy()
	{
		
		assertEquals(copyNP, negativeP_2);
	}

	/*	
	@Test
	public void test_fun_add_toPolynom()
	{

	}

	@Test
	public void test_fun_sub_toPolynom()
	{

	}

	@Test
	public void test_fun_multiply_byPolynom()
	{

	}

	@Test
	public void test_fun_multiply_byMonom()
	{

	}

	@Test
	public void test_fun_f()
	{

	}

	@Test
	public void test_util_isZero()
	{

	}

	@Test
	public void test_fun_derivative()
	{

	}

	@Test
	public void test_fun_area()
	{

	}

	@Test
	public void test_fun_root()
	{

	}

	@Test
	public void test_util_toString()
	{

	}

	@Test
	public void test_util_size()
	{

	}

	@Test
	public void test_fun_add_toMonom()
	{

	}

	@Test
	public void test_fun_sub_toMonom()
	{

	}

	@Test
	public void test_util_copyNew()
	{

	}*/

}
