
package myMath;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Predicate;

import myMath.Monom;

/** This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 * 
 * @author Boaz */
public class Polynom implements Polynom_able
{
	/** This is an empty constructor. */
	public Polynom()
	{
		polyn = new ArrayList<Monom>(); // Initialize an empty ArrayList.
	}

	/** This constructor builds a polynom from a string.
	 * 
	 * @param p String to parse into a polynom */
	public Polynom(String p)
	{
		this();
		if (p != null)
		{
			p = p.replaceAll("\\s", ""); // Replacing all spaces with null character.
			p = p.toLowerCase(); // Replace X with x.
			if (p.contains("*x^"))
			{
				String[] arr = p.split("(?=\\+|\\-)"); // Split between + and -.
				for (int i = 0; i < arr.length; i++)
				{
					this.add(new Monom(arr[i]));
				}
			}
			else if (p.matches("[+-]?\\d+(?:\\.\\d+)?"))
			{ // This regex checks if the string is a real number.
				try
				{
					double x = Double.parseDouble(p);
					this.add(new Monom(x, 0));
				}
				catch (Exception e)
				{
					throw new RuntimeException("ERROR: Wrong parameter for the constructor, msg:" + e); // Throw an exception if the input in not valid.
				}

			}
			else
			{
				throw new RuntimeException("ERROR: Wrong parameter for the constructor"); // Throw an exception if the input in not valid.
			}

		}
		else
		{
			throw new RuntimeException("ERROR: Wrong parameter for the constructor"); // Throw an exception if the input in not valid.
		}
	}

	/** This constructor performs a deep copy constructor.
	 * 
	 * @param p Polynom to copy */
	public Polynom(Polynom_able p)
	{
		this(); // Initialize to an empty list.
		Iterator<Monom> itr = p.iteretor(); // Iterator is used to go over items in the arraylist.
		while (itr.hasNext())
		{
			Monom m1 = (Monom) itr.next();
			this.add(new Monom(m1)); // Add each monom to current polynom.
		}
	}

	/** This function adds p1 to current polynom. */
	@Override
	public void add(Polynom_able p1)
	{
		Iterator<Monom> itr = p1.iteretor();
		while (itr.hasNext())
		{
			Monom monom = (Monom) itr.next(); // Iterate over p1 and add each monom to this polynom.
			this.add(monom);
		}
	}

	/** This function subtracts p1 from current polynom. */
	@Override
	public void substract(Polynom_able p1)
	{
		Iterator<Monom> itr = p1.iteretor();
		while (itr.hasNext())
		{
			Monom monom = (Monom) itr.next(); // Iterate over p1 and subtract each monom from this polynom.
			this.sub(monom);
		}
	}

	/** This function multiplies p1 by current polynom. */
	@Override
	public void multiply(Polynom_able p1)
	{
		Polynom p = new Polynom(); // This polynom is used to add and sort.
		if (!this.isZero() && !p1.isZero())
		{
			Iterator<Monom> itr = this.iteretor();
			while (itr.hasNext())
			{
				Monom monom = (Monom) itr.next();
				Polynom temp = new Polynom(p1); // This polynom is used to multiply each monom in p1.
				temp.multiply(monom);
				p.add(temp); // Add and sort
			}
			this.polyn = p.polyn; // Move the result to current polynom.
		}

	}

	/** This function multiplies monom m by current polynom.
	 * 
	 * @param m is used to multiply */
	public void multiply(Monom m)
	{
		if (m.get_coefficient() == 0) // if this monom is 0 then the polynom would also be equal to 0.
		{
			this.polyn.clear();
		}
		else
		{
			Iterator<Monom> iter = this.iteretor();
			while (iter.hasNext())
			{
				Monom temp = iter.next();
				temp.multiply(m);
			}
		}
	}

	/** @return f(x) = a_1*x^b_1+a_2*x^b_2+...+a_n*x^b_n. */
	@Override
	public double f(double x)
	{
		Iterator<Monom> it = iteretor();
		double sum = 0;
		while (it.hasNext())
		{
			Monom monom = (Monom) it.next();
			sum += monom.f(x); // Calculate and sum each monom's value at x.
		}
		return sum; // return the sum.
	}

	/** This function checks if polynom p1 is equal to current polynom for every x.
	 * 
	 * @return true if equal, false otherwise. */
	@Override
	public boolean equals(Polynom_able p1)
	{
		if (this.size() == ((Polynom) p1).size()) // Check size, if they are not equal in size then return false.
		{
			Iterator<Monom> m1 = this.iteretor();
			Iterator<Monom> m2 = p1.iteretor();
			while (m2.hasNext())
			{
				Monom monom = (Monom) m1.next();
				Monom monom2 = (Monom) m2.next();
				if (!monom.isEqual(monom2))
				{
					return false;
				}
			}
			return true;
		}
		return false;
	}

	/** Check if current polynom equals to 0.
	 * Use polynom size to perform the check, because 0 is not allowed to enter the polynom. */
	@Override
	public boolean isZero()
	{
		if (this.polyn.size() == 0) // if empty then size is 0.
		{
			return true;
		}
		return false;
	}

	/** This function calculates the derivative of current polynom.
	 * 
	 * @return p is a new polynom that is the derivative of current polynom. */
	@Override
	public Polynom_able derivative()
	{
		Polynom p = new Polynom(this); // New polynom to store result.
		Iterator<Monom> itr = p.iteretor();
		while (itr.hasNext())
		{
			Monom monom = (Monom) itr.next();
			monom.derivative();
			if (!monom.isValid()) // Check if the derivative is valid, if not then remove it.
			{
				itr.remove();
			}
		}
		return p;
	}

	/** This function calculates the estimated area between x0 and x1 above the X-axis using the Midpoint Riemann Sum.
	 * 
	 * @return aproxmArea */
	@Override
	public double area(double x0, double x1, double eps)
	{
		double aproxmArea = 0; // Sum of each rectangle.
		double numOfRec =Math.abs( (x1 - x0) / eps); // Number of rectangles calculated using eps.
		double epsM = eps / 2;
		for (int i = 1; i <= numOfRec; i++)
		{
			double Area = eps * this.f(x0 + epsM);
			if (Area > 0) // Only sum rectangles above the X-axis.
			{
				aproxmArea += Area;
			}
			epsM += eps;
		}
		return aproxmArea;
	}

	/** This function finds the root of the function with eps as tolerance of error.
	 * 
	 * @return root x2 between x0 and x1 if found. */
	@Override
	public double root(double x0, double x1, double eps)
	{
		double f0 = this.f(x0);
		double f1 = this.f(x1);

		if (f0 * f1 > 0)
		{
			throw new RuntimeException("ERROR: x1 and x0 are not opposite to one another."); // If the values are invalid
		}

		double lengthX = Math.abs(x0 - x1);
		double lengthF = Math.abs(f0 - f1);
		while (lengthX > eps || lengthF > eps) // Check if we reached tolerance of error.
		{
			double xm = (x0 + x1) / 2;
			double fm = this.f(xm);
			double value = f0 * fm;
			if (value < 0)
			{
				x1 = xm;
				f1 = this.f(x1);
			}
			else if (value > 0)
			{
				x0 = xm;
				f0 = this.f(x0);
			}

			else
			{
				return xm;
			}
			lengthX = Math.abs(x0 - x1);
			lengthF = Math.abs(f0 - f1);
		}
		return x1;
	}

	/** This function builds a reusable string from current polynom.
	 * 
	 * @return ans */
	public String toString()
	{
		String ans = "";
		boolean first = false;
		if (this.isZero())
		{
			ans = "0";
			return ans;
		}
		Iterator<Monom> it = this.iteretor();
		while (it.hasNext())
		{
			Monom mitr = (Monom) it.next();

			if (mitr.get_coefficient() > 0)
			{
				if (first)
				{
					ans += "+" + mitr.toString();
				}
				else
				{
					ans += mitr.toString();
					first = true;
				}
			}
			else
			{
				ans += mitr.toString();
				first = true;
			}

		}
		return ans;
	}

	/** @return of current polynom. */
	public int size()
	{
		return this.polyn.size();
	}

	/** Adds monom m1 to current polynom. */
	@Override
	public void add(Monom m1)
	{
		boolean added = false;
		Iterator<Monom> it = this.iteretor();
		while (it.hasNext() && !added)
		{
			Monom monom = (Monom) it.next();
			if (monom.get_power() == m1.get_power()) // Add if found monoms with equal powers.
			{
				monom.add(m1);
				if (!monom.isValid()) // Remove if not valid
				{
					it.remove();
				}
				added = true;
			}
		}
		if (!added) // If not added (no other monoms with equal power) then add to the arraylist.
		{
			if (m1.isValid())
			{
				polyn.add(m1);

			}
		}
		this.polyn.sort(CMPRMON); // Sort list.
	}

	/** Subtracts monom m1 from current polynom
	 * 
	 * @param m1 Monom to subtract. */
	public void sub(Monom m1)
	{
		boolean subbed = false;
		Iterator<Monom> it = this.iteretor();
		while (it.hasNext() && !subbed)
		{
			Monom monom = (Monom) it.next();
			if (monom.get_power() == m1.get_power())
			{
				monom.sub(m1);
				if (!monom.isValid())
				{
					it.remove();
				}
				subbed = true;
			}
		}
		if (!subbed) // If not subtracted (no other monoms with equal power) then add to the arraylist.
		{
			m1.multiply(new Monom(-1, 0)); // Add it and flip sign.
			polyn.add(m1);
		}
		this.polyn.sort(CMPRMON);
	}

	/** Create a deep copy of current monom and return new polynom.
	 * 
	 * @return p */
	@Override
	public Polynom_able copy()
	{
		Polynom p = new Polynom(this); // Perform a deep copy.
		return p;
	}

	/** @return Iterator of current polynom's list. */
	@Override
	public Iterator<Monom> iteretor()
	{
		return this.polyn.iterator();
	}

	// *********** Private Methods and Data ************

	private ArrayList<Monom> polyn;
	private static Comparator<Monom> CMPRMON = new Monom_Comperator();

}
