package com.lgu.loader;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TableMeta {

	public static List<String> getFieldName(ResultSet rset) throws SQLException{
		List<String> filedNames = new ArrayList<String>();
		int colCnt = rset.getMetaData().getColumnCount()+1;
		
		for(int i=1; i< colCnt ; i++){
			filedNames.add(rset.getMetaData().getColumnName(i));
		}
		return filedNames;
	}
	
	public static List<String> getFieldType(ResultSet rset) throws SQLException{
		List<String> filedTypes = new ArrayList<String>();
		int colCnt = rset.getMetaData().getColumnCount()+1;
		
		for(int i=1; i< colCnt ; i++){
			filedTypes.add(rset.getMetaData().getColumnTypeName(i));
		}
		return filedTypes;
	}
	
	public static List<String> getValues(ResultSet rset) throws SQLException{
		List<String> valuse = new ArrayList<String>();
		int colCnt = rset.getMetaData().getColumnCount()+1;
		
		for(int i=1; i< colCnt ; i++){
			valuse.add(rset.getString(i));
		}
		return valuse;
	}
}