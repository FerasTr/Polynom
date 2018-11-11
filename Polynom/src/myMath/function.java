package myMath;

/** This interface represents a simple function of type y=f(x), where both y and
 * x are real numbers. **/
public interface function
{
	/** This method calculates the value of f(x) in the current Polynom/Monom
	 * 
	 * @param x value to calculate.
	 * @return the value of function(x) */
	public double f(double x);
}
