import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Slf4jSample {

	public static void main(String[] args) {

		Logger logger = LoggerFactory.getLogger(Slf4jSample.class);
		logger.trace("trace");
		logger.debug("debug");
		logger.info("info");
		logger.warn("warn");
		logger.error("error");
	}

}
