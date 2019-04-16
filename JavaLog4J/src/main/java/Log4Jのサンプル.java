
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.RollingFileAppender;

public class Log4Jのサンプル {

    public static void main(final String[] args) {
		PropertyConfigurator.configure("LOG4Jのサンプル.properties");

        final Logger loggerA = Logger.getLogger("AAAA");
        loggerA.debug("debug!!!");
        loggerA.info("info!!!");
        loggerA.warn("warn!!!");
        loggerA.error("error!!!");
        loggerA.fatal("fatal!!!");

        final Logger loggerB = Logger.getLogger("BBBB");
		final RollingFileAppender a = (RollingFileAppender) loggerB
                .getAppender("BBBB");
		// ログディレクトリの変更
        try {
            a.setFile("LOG/BBBB/BBBB.log", a.getAppend(), a.getBufferedIO(),
                    a.getBufferSize());
        } catch (final IOException e) {

            e.printStackTrace();
        }
        loggerB.debug("debug!!!");
        loggerB.info("info!!!");
        loggerB.warn("warn!!!");
        loggerB.error("error!!!");
        loggerB.fatal("fatal!!!");
    }
}