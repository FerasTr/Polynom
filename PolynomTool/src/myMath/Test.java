package myMath;
/**
 * This class tests all functions of Monom and Polynom, assuming a correct input is of the following form: a*x^b
 * where a is a real number and b is a positive integer.
 * @author Feras Daragma and Eyad Amer
 */
public class Test
{

	public static void main(String[] args)
	{
		System.out.println("--------TESTING MONOM--------");
		System.out.println();
		TestMonom.Test_All();
		System.out.println();
		System.out.println("--------TESTING POLYNOM--------");
		System.out.println();
		TestPolynom.Test_All();
	}

	public static class TestMonom
	{
		private static void Test_All()
		{
			test_copy();
			test_add();
			test_equals();
			test_multiply();
			test_f();
			test_string();
			test_derivative();
		}

		private static void test_derivative()
		{
			System.out.println("test_derivative");
			Monom m1 = new Monom(452.1, 69);
			System.out.println(m1.toString());
			m1.derivative();
			System.out.println("No changes should be made on m1 -> " + m1.toString());
			Monom m2 = new Monom(m1.derivative());
			System.out.println(m2.toString());
			if (m1 == m2) // Checking if they point to the same object
			{
				System.err.println("m1 and m2 are linked");
			}
			System.out.println();
			System.out.println();
		}

		private static void test_copy()
		{
			System.out.println("test_copy");
			Monom m1 = new Monom(215, 326);
			Monom m2 = new Monom(m1);
			if (m1 == m2) // Checking if they point to the same object
			{
				System.err.println("This is not a deep copy");
			}
			else
			{
				System.out.println("deep copy performed");
			}
			System.out.println();
			System.out.println();
		}

		private static void test_string()
		{
			System.out.println("test_string");
			String msg = "";
			try
			{
				msg = "";
				Monom m1 = new Monom(msg);
				System.out.println(m1);
			}
			catch (Exception e)
			{
				System.out.println(msg + '\t' + e);
			}
			try
			{
				msg = "6*x^-4";
				Monom m1 = new Monom(msg);
				System.out.println(m1);
			}
			catch (Exception e)
			{
				System.out.println(msg + '\t' + e);
			}
			try
			{
				msg = "0";
				Monom m1 = new Monom(msg);
				System.out.println(m1);
			}
			catch (Exception e)
			{
				System.out.println(msg + '\t' + e);
			}
			try
			{
				msg = "5*x^0";
				Monom m1 = new Monom(msg);
				System.out.println(m1);
			}
			catch (Exception e)
			{
				System.out.println(msg + '\t' + e);
			}
			try
			{
				msg = "0*x^5";
				Monom m1 = new Monom(msg);
				System.out.println(m1);
			}
			catch (Exception e)
			{
				System.out.println(msg + '\t' + e);
			}
			try
			{
				msg = "Hello";
				Monom m1 = new Monom(msg);
				System.out.println(m1);
			}
			catch (Exception e)
			{
				System.out.println(msg + '\t' + e);
			}
			try
			{
				msg = "5          *x^                  10";
				Monom m1 = new Monom(msg);
				System.out.println(m1);
			}
			catch (Exception e)
			{
				System.out.println(msg + '\t' + e);
			}
			try
			{
				msg = "-21.56*X^852";
				Monom m1 = new Monom(msg);
				System.out.println(m1);
			}
			catch (Exception e)
			{
				System.out.println(msg + '\t' + e);
			}
			try
			{
				msg = "+5643*x^35";
				Monom m1 = new Monom(msg);
				System.out.println(m1);
			}
			catch (Exception e)
			{
				System.out.println(msg + '\t' + e);
			}
			System.out.println();
			System.out.println();
		}

		private static void test_f()
		{
			System.out.println("test_f");
			double a1 = 2.5;
			int b1 = 2;
			Monom m1 = new Monom(a1, b1);
			System.out.println(m1.f(30) + " == 2250");
			System.out.println();
			System.out.println();
		}

		private static void test_equals()
		{
			System.out.println("test_equals");
			double a1 = 2.5;
			int b1 = 2;
			Monom m1 = new Monom(a1, b1);
			Monom m2 = new Monom(a1, b1 + 1);
			Monom m3 = new Monom(a1 + 0.001, b1);
			Monom m4 = new Monom(m1);
			System.out.println(m1 + " != " + m2);
			System.out.println(m1 + " != " + m3);
			System.out.println(m1 + " == " + m4);
			if (m1.isEqual(m2))
			{
				System.err.println(m1 + " != " + m2);
				throw new RuntimeException("Error: Monom isEqual method is wrong!");
			}
			if (m1.isEqual(m3))
			{
				System.err.println(m1 + " != " + m3);
				throw new RuntimeException("Error: Monom isEqual method is wrong!");
			}
			if (!m1.isEqual(m4))
			{
				System.err.println(m1 + " == " + m4);
				throw new RuntimeException("Error: Monom isEqual method is wrong!");
			}
			System.out.println();
			System.out.println();
		}

		private static void test_multiply()
		{
			System.out.println("test_multiply");
			double a1 = 2.5, a2 = 3;
			int b1 = 2, b2 = 4;
			Monom m1 = new Monom(a1, b1);
			Monom m2 = new Monom(a2, b2);
			Monom m3 = new Monom(m1);
			m3.multiply(m2);

			System.out.println(m1 + " * " + m2 + " = " + m3);
			Monom m4 = new Monom(a1 * a2, b1 + b2);
			if (!m3.isEqual(m4))
			{
				System.err.println(m3 + " != " + m4);
				throw new RuntimeException("Error: Monom multiply method is wrong!");
			}
			System.out.println();
			System.out.println();
		}

		private static void test_add()
		{
			System.out.println("test_add");
			double a1 = 2.5;
			int b1 = 2;
			Monom m1 = new Monom(a1, b1);
			Monom m3 = new Monom(m1);
			m3.add(m1);

			System.out.println(m1 + " + " + m1 + " = " + m3);
			Monom m4 = new Monom(a1 + a1, b1);
			if (!m3.isEqual(m4))
			{
				System.err.println(m3 + " != " + m4);
				throw new RuntimeException("Error: Monom add method is wrong!");
			}
			System.out.println();
			System.out.println();
		}
	}

	public static class TestPolynom
	{
		private static void Test_All()
		{
			test_equals();
			test_multiply();
			test_root();
			test_add_subtract();
			test_string();
			test_area();
			test_zero();

		}

		private static Polynom generatePoly(int powerDegree)
		{
			Polynom randomPoly = new Polynom();
			for (int i = 0; i < powerDegree; i++)
			{
				double coeff = (Math.random() - 0.5) * 1000;
				coeff = (int) coeff;
				coeff = coeff / 50;
				Monom tmp = new Monom(coeff, i);
				randomPoly.add(tmp);
			}
			return randomPoly;
		}

		private static void test_zero()
		{
			System.out.println("test_zero");
			Polynom p = new Polynom();
			System.out.println(p.isZero());
			p.add(new Monom("0"));
			System.out.println(p.isZero());
			p.add(new Polynom(generatePoly(3)));
			System.out.println(p.isZero());
			System.out.println();
			System.out.println();
		}

		private static void test_area()
		{
			System.out.println("test_area");
			Polynom p = new Polynom("5*x^4-3*x^3+1*x^2+120");
			double x = p.area(-2, 2, 0.08);
			System.out.println(x);
			p = new Polynom("9*x^4+4*x^2+7*x^1+5");
			x = p.area(-2, 0, 0.04);
			System.out.println(x);
			System.out.println();
			System.out.println();
		}

		private static void test_string()
		{
			System.out.println("test_string");
			String msg = "";
			try
			{
				msg = "0";
				Polynom poly = new Polynom(msg);
				System.out.println(poly.toString());
			}
			catch (Exception e)
			{
				System.out.println(msg + '\t' + e);
			}
			try
			{
				msg = "Hello";
				Polynom poly = new Polynom(msg);
				System.out.println(poly.toString());
			}
			catch (Exception e)
			{
				System.out.println(msg + '\t' + e);
			}
			try
			{
				msg = "5";
				Polynom poly = new Polynom(msg);
				System.out.println(poly.toString());
			}
			catch (Exception e)
			{
				System.out.println(msg + '\t' + e);
			}
			try
			{
				msg = "5*x^";
				Polynom poly = new Polynom(msg);
				System.out.println(poly.toString());
			}
			catch (Exception e)
			{
				System.out.println(msg + '\t' + e);
			}
			try
			{
				msg = "hell*x^o";
				Polynom poly = new Polynom(msg);
				System.out.println(poly.toString());
			}
			catch (Exception e)
			{
				System.out.println(msg + '\t' + e);
			}
			try
			{
				msg = "-20*x^3";
				Polynom poly = new Polynom(msg);
				System.out.println(poly.toString());
			}
			catch (Exception e)
			{
				System.out.println(msg + '\t' + e);
			}
			try
			{
				msg = "-20*x^3+4+58*x^0+6";
				Polynom poly = new Polynom(msg);
				System.out.println(poly.toString());
			}
			catch (Exception e)
			{
				System.out.println(msg + '\t' + e);
			}
			System.out.println();
			System.out.println();
		}

		private static void test_equals()
		{
			System.out.println("test_equals");
			Polynom p0 = generatePoly(4);
			Polynom p1 = generatePoly(5);
			Polynom p0_copy = new Polynom(p0);
			if (p0.equals(p1))
			{
				System.err.println(p0 + " != " + p1);
				throw new RuntimeException("Error: the Polynoms: " + p0 + " and " + p1 + "  should NOT be equal!!");
			}
			else
			{
				System.out.println("Passed test 1");
			}
			if (p0.equals(p1))
			{
				System.err.println(p0 + " == " + p0_copy);
				throw new RuntimeException("Error: the Polynoms: " + p0 + " and " + p0_copy + "  should be equal!!");
			}
			else
			{
				System.out.println("Passed test 2");
			}
			System.out.println();
			System.out.println();
		}

		private static void test_add_subtract()
		{
			System.out.println("test_add_subtract");
			Polynom p0 = generatePoly(4);
			Polynom p1 = generatePoly(5);
			Polynom p2 = new Polynom(p0);
			p2.add(p1);
			System.out.println(p0 + " + ");
			System.out.println(p1 + " = ");
			System.out.println(p2);
			p2.substract(p1);
			System.out.println(p2.toString());
			System.out.println();
			System.out.println();
		}

		private static void test_multiply()
		{
			System.out.println("test_multiply");
			Polynom p0 = generatePoly(2);
			Polynom p1 = generatePoly(3);
			Polynom p2 = new Polynom(p0);
			p2.multiply(p1);
			System.out.println(p0 + " * ");
			System.out.println(p1 + " = ");
			System.out.println(p2);
			System.out.println();
			System.out.println();
		}

		private static void test_root()
		{
			System.out.println("test_root");
			System.out.println("random: ");
			Polynom p0 = generatePoly(2);
			Monom m0 = new Monom(-1000, 0);
			Monom m3 = new Monom(1, 3);
			p0.add(m0);
			p0.add(m3);
			double eps = 0.01;
			double root0 = p0.root(0, 100, eps);
			System.out.println(root0);
			System.out.println(p0.f(root0));
			if (Math.abs(p0.f(root0)) < eps)
			{
				System.out.println("Correct");
			}
			System.out.println("x^2-2: ");
			Polynom p2 = new Polynom("1*x^2-2");
			double eps2 = 0.01;
			double root1 = p2.root(1.3, 1.5, eps2);
			System.out.println(root1);
			System.out.println(p2.f(root1));
			if (Math.abs(p0.f(root0)) < eps)
			{
				System.out.println("Correct");
			}
			System.out.println();
			System.out.println();
		}
	}
}
