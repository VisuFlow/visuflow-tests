package de.unipaderborn.visuflow;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.ILogListener;
import org.eclipse.core.runtime.IStatus;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.osgi.framework.Bundle;

public class LoggerTest {

	private Logger logger;
	private TestLog testLog;

	@Before
	public void setup() {
		testLog = new TestLog();
		logger = new Logger(testLog);
	}

	@Test
	public void testInfo() {
		logger.info("Info");
		IStatus log = testLog.lastLog;

		Assert.assertEquals("Info", log.getMessage());
		Assert.assertEquals(IStatus.INFO, log.getSeverity());
	}

	@Test
	public void testWarn() {
		logger.warn("Warn");
		IStatus log = testLog.lastLog;

		Assert.assertEquals("Warn", log.getMessage());
		Assert.assertEquals(IStatus.WARNING, log.getSeverity());
	}

	@Test
	public void testWarnExc() {
		Exception ex = new RuntimeException("Test warning");
		logger.warn("Warn", ex);
		IStatus log = testLog.lastLog;

		Assert.assertEquals("Warn", log.getMessage());
		Assert.assertEquals(IStatus.WARNING, log.getSeverity());
		Assert.assertEquals(ex, log.getException());
	}

	@Test
	public void testError() {
		logger.error("Error");
		IStatus log = testLog.lastLog;

		Assert.assertEquals("Error", log.getMessage());
		Assert.assertEquals(IStatus.ERROR, log.getSeverity());
	}

	@Test
	public void testErrorExc() {
		Exception ex = new RuntimeException("Test error");
		logger.error("Error", ex);
		IStatus log = testLog.lastLog;

		Assert.assertEquals("Error", log.getMessage());
		Assert.assertEquals(IStatus.ERROR, log.getSeverity());
		Assert.assertEquals(ex, log.getException());
	}

	public class TestLog implements ILog {
		public IStatus lastLog;

		@Override
		public void removeLogListener(ILogListener listener) {
		}

		@Override
		public void log(IStatus status) {
			lastLog = status;
		}

		@Override
		public Bundle getBundle() {
			return null;
		}

		@Override
		public void addLogListener(ILogListener listener) {
		}
	}

}
