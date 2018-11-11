package myMath;

import java.util.Iterator;

/** This interface represents a general Polynom: f(x) = a1X^b1 + a2*X^b2 ... an*Xbn, where: a1, a2, ... an are real numbers
 * and b1,b2..,bn are in descending order are naturals.
 * Such polygon has the following functionality:
 * 1. initialize:
 * 1.1 initialize from string.
 * 1.2 initialize a zero polynom.
 * 1.3 initialize a deep copy.
 * 2. Math:
 * 2.1 Add Polynom to current polynom.
 * 2.2 Subtract Polynom from current polynom.
 * 2.3 Multiply Polynom by current polynom.
 * 3. Utilities:
 * 3.1 isZero which checks if this polynom is zero.
 * 3.2 Polynom derivative which returns a new polynom of the derivative.
 * 3.3 function(x) return this Polynom value at p(x).
 * 3.4 equals which returns true if and only if for any x this polynom equals current polynom.
 * 3.5 Root assuming which returns the root of the polynom within tolerance of error (eps).
 * 3.6 toString which returns a String such that it can be used for initialize and equals functions.
 * 
 * @author ben-moshe */
public interface Polynom_able extends cont_function
{
	/** Add p1 to this Polynom.
	 * 
	 * @param p1 polynom to add */
	public void add(Polynom_able p1);

	/** Add m1 to this Polynom
	 * 
	 * @param m1 Monom to add */
	public void add(Monom m1);

	/** Subtract p1 from this Polynom.
	 * 
	 * @param p1 polynom to subtract */
	public void substract(Polynom_able p1);

	/** Multiply this Polynom by p1.
	 * 
	 * @param p1 Polynom to multiply with */
	public void multiply(Polynom_able p1);

	/** Test if this Polynom is logically equals to p1.
	 * 
	 * @param p1 polynom to compare
	 * @return true if and only if this polynom represents the same function as p1 */
	public boolean equals(Polynom_able p1);

	/** Test if this is the Zero Polynom.
	 * 
	 * @return true if LinkedList is empty, true otherwise. */
	public boolean isZero();

	/** Compute a value x2 where x2 is between x1 and x0 for with |f(x2)| is less than eps, assuming (f(x0)*f(x1))is less than 0.
	 * 
	 * @param x0  starting point
	 * @param x1  end point
	 * @param eps step (positive) value
	 * @return the root (x2) in eps approximation */
	public double root(double x0, double x1, double eps);

	/** create a deep copy of this Polynom
	 * 
	 * @return a copy of this polynom */
	public Polynom_able copy();

	/** Compute a new Polynom which is the derivative of this Polynom.
	 * 
	 * @return a new polynom which is the derivative of current polynom */
	public Polynom_able derivative();

	/** Compute Riemann's Integral over this Polynom starting from x0, till x1 using eps size steps.
	 * 
	 * @return the approximated area above the x-axis below this Polynom and between
	 *         the [x0,x1] range. */
	public double area(double x0, double x1, double eps);

	/** Function for Iterator of this polynom.
	 * 
	 * @return an Iterator (of Monoms) over this Polynom */
	public Iterator<Monom> iteretor();
}
