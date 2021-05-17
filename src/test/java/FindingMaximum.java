
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import randomlib.FindMax;

public class FindingMaximum {
    final private Logger LOG = LoggerFactory.getLogger(FindingMaximum.class);

    @Test
    public void findMaxTest() {
        LOG.info("STARTING THE TESTCASE TO FIND THE MAX OF 2 GIVEN NUMBERS");

        FindMax fmObj = new FindMax();
        LOG.info("MAXIMUM OF THE GIVEN 2 NUMBERS: {}", fmObj.findMax(2, 3));

        LOG.warn("WARNING ENABLED");
    }
}
