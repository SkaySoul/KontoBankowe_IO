package testing;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({AccountTest.class, DataSetTest.class, TransferTest.class})
public class KontoBankoweTestSuite {
}
