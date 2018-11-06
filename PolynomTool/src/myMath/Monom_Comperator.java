package myMath;

import java.util.Comparator;

/** This class extends the interface Comparator which provide a way to compare Monoms and sort them. */
public class Monom_Comperator implements Comparator<Monom>
{

	@Override
	public int compare(Monom arg0, Monom arg1)
	{
		return arg1.get_power() - arg0.get_power(); // The Monom with the bigger power is the larger Monom.
	}
}
