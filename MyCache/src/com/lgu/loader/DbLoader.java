package com.lgu.loader;

import java.lang.reflect.InvocationTargetException;
import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;


public class DbLoader implements Callable<String>{

	private DbConnection con =  new DbConnection();
	private LoaderConfig config = LoaderConfig.getInstance();
	private StringBuilder sb = new StringBuilder();
	private int threadNum = 0;
	
	public DbLoader(int i){
		this.threadNum = i;
	}
	
	private void load() throws SQLException, SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException{
		String cacheName = config.getCacheNames().get(threadNum);
		String sql       = config.getSqls().get(threadNum);
		String className = config.getClazz().get(threadNum);
		String keyField  = config.getKeys().get(threadNum);
		
		NamedCache cache = CacheFactory.getCache(cacheName);
		ResultSet rs = con.executeQuery(sql);
		
		List<String> fieldNames = TableMeta.getFieldName(rs);
		List<String> fieldTypes = TableMeta.getFieldType(rs);
		
		int size = 100;
		Map mapBuffer = new HashMap(size);
		int cnt=0;
		
		while (rs.next()) {
			List<String> values = TableMeta.getValues(rs);
			Object pof = PofCreater.createPof(className, fieldNames, fieldTypes, values );
			cnt++;
			
			mapBuffer.put(rs.getString(keyField), pof);
			if ((cnt % size) == 0){
				cache.putAll(mapBuffer);
				mapBuffer.clear();
			}
			
			//Local Test
			if(cnt > 5000){
				break;
			}
		}
		
		if (!mapBuffer.isEmpty()) {
			cache.putAll(mapBuffer);
		}

		sb.append("[").append(cacheName)
			.append("]========================").append(String.format("%n"))
			.append("Query size=" )
			.append( rs.getRow()).append(String.format("%n"))
			.append("Cache size=")
			.append(cache.size()).append(String.format("%n"));
	}
	

	@Override
	public String call() throws Exception {
		if(con.openConnection()){
			try {
				long start = System.currentTimeMillis();
				
				load();
				
				sb.append("Load Time : ")
					.append((System.currentTimeMillis() - start))
					.append(String.format("%n"));
				
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}finally{
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return sb.toString();
	}

	public static void main(String args[]){
		
		try {
			if("KRJ-PC".equals(java.net.InetAddress.getLocalHost().getHostName())){
				System.setProperty("tangosol.coherence.distributed.localstorage", "false");
				System.setProperty("tangosol.coherence.override", "E:/NH/oracle/domains/coherence/config/runtime.xml");
				System.setProperty("tangosol.pof.config", "E:/NH/oracle/domains/coherence/config/fds-pof-config.xml");
				System.setProperty("tangosol.pof.enabled", "true");
				System.setProperty("tangosol.coherence.localhost", "192.168.0.207");

				//Local Test
				System.setProperty("tangosol.coherence.clusteraddress", "224.168.0.140");
				System.setProperty("tangosol.coherence.clusterport", "34059");
				System.setProperty("tangosol.coherence.ttl", "0");
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		//로드할 테이블(cacher) 갯수
		int totNum = LoaderConfig.getInstance().getTotNum();
		
		//ThreadPool 갯수 
		int threadPoolNum = totNum;
		
		List<Future<String>> resultMsgLi = new ArrayList<Future<String>>(totNum);
		
		ExecutorService executorService = Executors.newFixedThreadPool(threadPoolNum);
		
		// LOAD ALL
		for (int i = 0; i < totNum ; i++) {
			resultMsgLi.add(executorService.submit(new DbLoader(i)));
		}
		
		//결과 로그
		for (int i = 0; i < totNum ; i++) {
			try {
				System.out.println(resultMsgLi.get(i).get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		
		executorService.shutdown();
		CacheFactory.shutdown();
		
	}
}

