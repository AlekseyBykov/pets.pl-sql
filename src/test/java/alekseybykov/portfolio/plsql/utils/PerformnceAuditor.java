package alekseybykov.portfolio.plsql.utils;

import org.junit.AssumptionViolatedException;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Aleksey Bykov
 * @since 26.05.2020
 */
public class PerformnceAuditor extends Stopwatch {

	private static final Logger LOGGER = Logger.getLogger(PerformnceAuditor.class.getPackage().getName());

	@Override
	protected void succeeded(long nanos, Description description) {
		log(description, "succeeded", nanos);
	}

	@Override
	protected void finished(long nanos, Description description) {
		log(description, "finished", nanos);
	}

	@Override
	protected void skipped(long nanos, AssumptionViolatedException e, Description description) {
		log(description, "skipped", nanos);
	}

	@Override
	protected void failed(long nanos, Throwable e, Description description) {
		log(description, "failed", nanos);
	}

	private static void log(Description description, String status, long nanos) {
		LOGGER.log(Level.INFO, String.format("Performance tracking: %s %s, %d ms", description.getMethodName(),
				status, TimeUnit.NANOSECONDS.toMillis(nanos)));
	}
}
