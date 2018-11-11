package myMath;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MonomTest
{
	private Monom m;
	private Monom ms;
	private Monom copy;
	private Monom expectedResult;

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
		m = new Monom(5.6,2);
		copy = new Monom(m);
		ms = new Monom("5.6*x^2");
	}

	@After
	public void tearDown() throws Exception
	{
		expectedResult = null;
	}

	@Test
	public void test_init_copy()
	{
		assertNotSame(m, copy);
		assertEquals(m.toString(), copy.toString());
	}

	@Test
	public void test_init_string()
	{
		assertEquals(ms, m);
	}

/*	@Test
	public void testAdd()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testSub()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testMultiply()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testDerivative()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testF()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testToString()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testIsValid()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testIsEqual()
	{
		fail("Not yet implemented");
	}
*/
}
