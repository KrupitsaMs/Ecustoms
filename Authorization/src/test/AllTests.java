package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * AllTests.java
 * Class provides start of all Junit tests
 * @author MasSword
 */

@RunWith(Suite.class)
@SuiteClasses({ InformDAOTest.class, UserDAOTest.class })
public class AllTests {

}
