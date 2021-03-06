package com.hexotic.shadow.logs;

import java.io.File;
import java.util.Map;

public interface Log {

	/**
	 * Check to see if Shadowing has started on the log
	 * 
	 * @return True if shadowing, false otherwise
	 */
	public boolean isStarted();
	
	/**
	 * Start shadowing the log
	 */
	public void startShadow();
	
	/**
	 * Adds listeners to the log.  This is a requirement for all implementations
	 * of the log
	 * 
	 * @param listener
	 */
	public void addLogListener(LogListener listener);
	
	/**
	 * Closes and stops shadowing of the log
	 */
	public void shutdown();
	
	/**
	 * Stops shadow from tailing the log file but does not notify the
	 * rest of the program that the log has stopped.  This is useful for 
	 * restarting logging without the UI removing all references and then 
	 * re-adding them
	 */
	public void stop();
	
	/**
	 * Sets the log as "Activated" which means that it is the currently
	 * displayed log in Shadow.  This method is called to notify any other
	 * object that is listening that the log is now "Active"
	 */
	public void activate();
	
	/**
	 * Sets the log as "Deactivated" which means that it is no longer 
	 * the currently displayed log in Shadow.  This method is called to notify
	 * any other objects that are listening that the log is no longer "Active"
	 */
	public void deactivate();
	
	/**
	 * This method is like activate and deactivate, however it allows the state of the
	 * log to be changed manually without notifying listeners
	 * @param active
	 */
	public void setActive(boolean active);
	
	/**
	 * This method will return a boolean variable indicated whether or not the
	 * log is activated or, in other words, if the log is the currently active 
	 * log in shadow.  Typically there should only be one log activated at a time
	 * however, the implementation does allow for multiple logs to be activated if
	 * needed by some future requirement
	 * 
	 * @return True if the log is activated, false otherwise
	 */
	public boolean isActivated();
	
	/**
	 * Each log will have a unique ID typically generated by the LogFactory (where all logs should be created)
	 * This method will return that unique id
	 * 
	 * @return Unique Log Id
	 */
	public String getLogId();
	
	/**
	 * This method will return a file object pointing to the local file on disk.  For remote files, this
	 * will still be the local .sshadow file.  It is up to the developer to handle what to do with this
	 * information
	 * 
	 * @return Local log file on disk
	 */
	public File getFile();
	
	/**
	 * This method will re-read the flag properties to update what lines are to be flaged with 
	 * what color
	 * 
	 */
	public void refreshFlags();
	
	/**
	 * Get list of flags for the log
	 * 
	 * @return Map of Flag name and definitions
	 */
	public Map<String, String> getFlags();
	
	/**
	 * Checks a line to see if it contains any of the flags
	 * @return the flag of the line
	 */
	public String checkFlags(String line);
}
