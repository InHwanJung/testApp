package com.lgu.loader;

import com.lgu.util.PropertiesManager;

public class DbConfig {
	/**
	 * Driver class name.
	 */
	private static String driver = null;

	/**
	 * Connection URL.
	 */
	private static String url = null;

	/**
	 * User name.
	 */
	private static String userName = null;;

	/**
	 * Password.
	 */
	private static String passWord = null;
	
	private static DbConfig instance = new DbConfig();
	
	private DbConfig(){
		loadDBConfig();
	}
	
	private void loadDBConfig(){
		PropertiesManager propsMng = PropertiesManager.getInstance();
		try {
			userName = propsMng.get("lgu.db.username");
			passWord = propsMng.get("lgu.db.password");
		} catch (Exception e) {
			e.printStackTrace();
		}
		driver 	= propsMng.get("lgu.db.driver");
		System.out.println("JDBC driver " + driver);
		url 	= propsMng.get("lgu.db.url");
		System.out.println("JDBC driver " + url);
	}

	/**
	 * @return the instance
	 */
	public static DbConfig getInstance() {
		return instance;
	}
	
	/**
	 * @return the driver
	 */
	public String getDriver() {
		return driver;
	}

	/**
	 * @param driver the driver to set
	 */
	public void setDriver(String driver) {
		DbConfig.driver = driver;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		DbConfig.url = url;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		DbConfig.userName = userName;
	}

	/**
	 * @return the passWord
	 */
	public String getPassWord() {
		return passWord;
	}

	/**
	 * @param passWord the passWord to set
	 */
	public void setPassWord(String passWord) {
		DbConfig.passWord = passWord;
	}
}
