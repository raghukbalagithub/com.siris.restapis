package randomlib;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FindMax {
    final private Logger LOG = LoggerFactory.getLogger(FindMax.class);

    public FindMax() {
        LOG.info("FINDMAX CLASS OBJECT CREATION");
    }

    public int findMax(int x, int y) {
        return (x > y)? x : y;
    }
}
