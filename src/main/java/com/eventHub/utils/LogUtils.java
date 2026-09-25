package com.eventHub.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * LogUtils wraps Apache Log4j2 to provide standardized logging across 
 * Page Objects, Step Definitions, and Framework Helpers.
 */

public class LogUtils {
	
	
	
	// WHY LOG4J2 OVER SYSTEM.OUT.PRINTLN:
    // 1. System.out.println statements get mixed up during parallel thread execution.
    // 2. Log4j2 formats logs with exact timestamps, thread IDs [Thread-1], and severity levels (INFO/ERROR).
    // 3. Automatically writes logs to both Console and disk files (e.g., target/logs/automation.log).
	
	private static final Logger logger = LogManager.getLogger(LogUtils.class);
	
	// Private constructor prevents instantiation of this utility class
	
	private LogUtils() 
	{
		
	}
	
		
		/**
	     * Logs informational messages.
	     */
	
	public static void info(String message) {
        logger.info(message);
    }	
		
	/**
     * Logs warning messages for non-fatal execution delays or retries.
     */
	
	public static void warn(String message) {
        logger.warn(message);
    }
	
	/**
     * Logs standard failure error messages.
     */
    public static void error(String message) {
        logger.error(message);
    }
	
    /**
     * Logs severe errors along with full exception stack traces.
     */
    public static void error(String message, Throwable throwable) {
        logger.error(message, throwable);
    }
    
    /**
     * Logs fine-grained debugging info (useful for locator troubleshooting).
     */
    public static void debug(String message) {
        logger.debug(message);
    }
    
 // ==========================================
    // SCENARIO LIFECYCLE LOGGING HELPERS
    // ==========================================

    /**
     * Prints a visually distinct header at the start of a scenario.
     */
    
    
    public static void startScenario(String scenarioName) {
        logger.info("==========================================================================");
        logger.info("  STARTING SCENARIO: {}", scenarioName);
        logger.info("==========================================================================");
    }
    
    
    
    /**
     * Prints a visually distinct footer at the end of a scenario.
     */
    public static void endScenario(String scenarioName, String status) {
        logger.info("  FINISHED SCENARIO: {} | STATUS: {}", scenarioName, status);
        logger.info("==========================================================================");
    }
	
	
	
	
	
}
