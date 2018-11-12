
package myMath;

/** This class represents a simple "Monom" of shape a*x^b, where a is a real number and b is an integer.
 * The class implements function and supports simple operations such as: construction, value at x, derivative, add and
 * multiply.
 * see: https://en.wikipedia.org/wiki/Monomial
 * Monom has the following structure: a*x^b
 * 
 * @author Boaz */
public class Monom implements function
{

	/** Default constructor for monom.
	 * 
	 * @param a is coefficient.
	 * @param b is power. */
	public Monom(double a, int b)
	{
		this.set_coefficient(a);
		this.set_power(b);
	}

	/** This is a deep copy constructor.
	 * 
	 * @param ot Monom to copy from */
	public Monom(Monom ot)
	{
		this(ot.get_coefficient(), ot.get_power());
	}

	/** This constructor builds a monom from string of shape: a*x^b.
	 * 
	 * @param s String to turn into a monom object */
	public Monom(String s)
	{
		if (s != null)
		{
			s = s.replaceAll("\\s", ""); // Replacing all spaces with null character.
			s = s.toLowerCase(); // Replace X with x.
			if (s.contains("*x^"))
			{
				String[] arr = s.split("\\*x\\^"); // Using split and regex to build an array.
				try
				{
					this.set_coefficient(Double.parseDouble(arr[0])); // Try to parse to double from string
				}
				catch (Exception e)
				{
					this.set_coefficient(0); // If its not possible then set a = 1.
				}

				try
				{
					this.set_power(Integer.parseInt(arr[1])); // Try to parse to double from string
				}
				catch (Exception e)
				{
					this.set_power(0); // If its not possible then set a = 0.
				}
			}
			else if (s.matches("[+-]?\\d+(?:\\.\\d+)?")) // This regex checks if the string is a real number.
			{
				try
				{
					this.set_coefficient(Double.parseDouble(s));
				}
				catch (Exception e)
				{
					this.set_coefficient(0);
				}

				this.set_power(0);
			}
			else
			{
				throw new RuntimeException("Wrong parameter for the Constructor"); // Throw an exception if the input in not valid.
			}

		}
		else
		{
			throw new RuntimeException("Wrong parameter for the Constructor"); // Throw an exception if the input in not valid.
		}
	}

	/** This function adds current monom to monom m.
	 * 
	 * @param m monom to add. */
	public void add(Monom m)
	{
		if (this.get_power() == m.get_power()) // Check is they both have the same power.
		{
			double coeff = this._coefficient + m._coefficient;
			this.set_coefficient(coeff);
			if (coeff == 0)
			{
				this.set_power(0);
			}

		}
		else
		{
			throw new RuntimeException("The two monoms have different powers.");
		}
	}

	/** This function subtracts current m monom from current monom.
	 * 
	 * @param m monom to subtract. */
	public void sub(Monom m)
	{
		if (this.get_power() == m.get_power()) // Check is they both have the same power.
		{
			double coeff = this._coefficient - m._coefficient;
			this.set_coefficient(coeff);
			if (coeff == 0)
			{
				this.set_power(0);
			}

		}
		else
		{
			throw new RuntimeException("The two monoms have different powers.");
		}
	}

	/** This function multiplies current monom by monom m.
	 * 
	 * @param m monom to multiply */
	public void multiply(Monom m)
	{
		if (m.get_coefficient() == 0)
		{
			this.set_coefficient(0); // Change current monom's coefficient.
			this.set_power(0); // Change current monom's power.
		}
		else
		{
			this.set_coefficient(m._coefficient * this._coefficient); // Change current monom's coefficient.
			this.set_power(m._power + this._power); // Change current monom's power.
		}
	}

	/** This function calculates the derivative of current monom.
	 * 
	 * @return returns new monom with new values. */
	public Monom derivative()
	{
		if (this._power > 0)
		{
			return new Monom(this._power * this._coefficient, this._power - 1); // Calculate new coefficient and power.
		}
		else
		{
			return new Monom(0, 0); // if power is equal to 0 then return new monom 0.
		}
	}

	/** @return This function returns the coefficient. */
	public double get_coefficient()
	{
		return this._coefficient;
	}

	/** @return This function returns the power. */
	public int get_power()
	{
		return this._power;
	}

	/** @return f(x) = a*x^b. */
	@Override
	public double f(double x)
	{
		return this.get_coefficient() * Math.pow(x, this.get_power()); // Calculate and return the monom's value at x.
	}

	/** @return String format of current monom. */
	@Override
	public String toString()
	{
		return this._coefficient + "*x^" + this._power; // a*x^b.
	}

	/** This function checks if this monom has valid arguments.
	 * 
	 * @return true if valide, false otherwise */
	public boolean isValid()
	{
		if (this.get_power() < 0)
		{
			return false; // Return false if this power is negative.
		}
		if (this.get_coefficient() == 0)
		{
			return false; // Return false if this coefficient is 0.
		}
		return true;
	}

	/** This function checks if monom m is equal to current monom.
	 * 
	 * @param m Monom to compare
	 * @return true if equal, false otherwise. */
	@Override
	public boolean equals(Object m)
	{
		if (m != null)
		{
			if ((m instanceof Monom))
			{
				if (((Monom) m).get_power() == this.get_power() && ((Monom) m).get_coefficient() == this.get_coefficient()) // Checks coefficient and power.
				{
					return true;
				}
				else
				{
					return false;
				}
			}
			else
			{
				throw new RuntimeException("m is not a Monom"); // if not Monom then throw an exception.
			}

		}
		else
		{
			throw new RuntimeException("m is NULL"); // if null then throw an exception.
		}
	}

	// *********** Private Methods and Data ************

	/** This function sets the power to b.
	 * 
	 * @param b */
	private void set_power(int p)
	{
		if (!(p < 0))
		{
			if (this._coefficient == 0)
			{
				this._power = 0; // set power to 0 if coefficient is 0.
			}
			else
			{
				this._power = p; // set power to p if and only if p is a positive integer.
			}
		}
		else
		{
			throw new RuntimeException("Power should not be negative"); // Throw an exception if p is negative.
		}
	}

	/** This function sets the coefficient to a.
	 * 
	 * @param a */
	private void set_coefficient(double a)
	{
		this._coefficient = a;
	}

	private double _coefficient; // this is the a.
	private int _power; // this is the b.
}
