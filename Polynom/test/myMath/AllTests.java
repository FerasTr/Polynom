package myMath;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Suite class to run all JUnit tests.
 * @author darag
 */
@RunWith(Suite.class)
@SuiteClasses({ MonomTest.class, PolynomTest.class })
public class AllTests
{

}
