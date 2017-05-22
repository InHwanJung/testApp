package com.lgu.loader;

import java.util.List;

import com.lgu.util.PropertiesManager;

public class LoaderConfig {
	private static final String KEY_CACHE_NAME = "lgu.rule.cache.name";
	private static final String KEY_SELECT_SQL = "lgu.rule.sql";
	private static final String KEY_POF_CLASS  = "lgu.rule.pof.class";
	private static final String KEY_POF_KEY    = "lgu.rule.pof.key";
	
	private List<String> cacheNames = null;
	private List<String> sqls       = null;
	private List<String> clazz      = null;
	private List<String> keys       = null;
	
	private int totNum = 0;
	
	private static LoaderConfig instance = new  LoaderConfig();
	
	private LoaderConfig(){
		PropertiesManager propsMng = PropertiesManager.getInstance();
		cacheNames = propsMng.getArr(KEY_CACHE_NAME);
		sqls       = propsMng.getArr(KEY_SELECT_SQL);
		clazz      = propsMng.getArr(KEY_POF_CLASS);
		keys       = propsMng.getArr(KEY_POF_KEY);
		
		totNum     = Math.min(Math.min(cacheNames.size(), sqls.size()) 
						, Math.min(clazz.size(), keys.size()));
	}
	
	public static LoaderConfig getInstance(){
		return instance;
	}

	/**
	 * @return the cacheNames
	 */
	public List<String> getCacheNames() {
		return cacheNames;
	}

	/**
	 * @param cacheNames the cacheNames to set
	 */
	public void setCacheNames(List<String> cacheNames) {
		this.cacheNames = cacheNames;
	}

	/**
	 * @return the sqls
	 */
	public List<String> getSqls() {
		return sqls;
	}

	/**
	 * @param sqls the sqls to set
	 */
	public void setSqls(List<String> sqls) {
		this.sqls = sqls;
	}

	/**
	 * @return the clazz
	 */
	public List<String> getClazz() {
		return clazz;
	}

	/**
	 * @param clazz the clazz to set
	 */
	public void setClazz(List<String> clazz) {
		this.clazz = clazz;
	}

	/**
	 * @return the keys
	 */
	public List<String> getKeys() {
		return keys;
	}

	/**
	 * @param keys the keys to set
	 */
	public void setKeys(List<String> keys) {
		this.keys = keys;
	}

	/**
	 * @return the totNum
	 */
	public int getTotNum() {
		return totNum;
	}

	/**
	 * @param totNum the totNum to set
	 */
	public void setTotNum(int totNum) {
		this.totNum = totNum;
	}

	/**
	 * @param instance the instance to set
	 */
	public static void setInstance(LoaderConfig instance) {
		LoaderConfig.instance = instance;
	}

}
