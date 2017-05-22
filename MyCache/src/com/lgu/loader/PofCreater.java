package com.lgu.loader;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 동적으로 POF객체를 생성하는 클래서
 * 목적: Coherence설정파일(xml) 수정없이 POF객채 추가 가능하도록 하기위함
 * @author Administrator
 *
 */
public class PofCreater {

	
	public static Object createPof(String className, List<String> fields, List<String> typs, List<String> values) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException{
		Class clz = Class.forName(className);
		Constructor c = clz.getConstructor();
		Object obj= c.newInstance();
		
		for (int i = 0; i < fields.size(); i++) {
			String methodNm = findMethodName(clz, fields.get(i), true);
			Class typeClz = convertType(typs.get(i));
			Method m = clz.getMethod(methodNm, typeClz );
			
			if( typeClz == Integer.class){
				m.invoke(obj, new Integer( values.get(i)));
			}else if( typeClz == String.class){
				m.invoke(obj, values.get(i)  );
			}else{
				throw new RuntimeException("정의되지 않은 타입");
			}
		}
		return obj;
	}
	
	/**
	 * DB 타입을 Java타입으로 변환한다.<br>
	 * ORALCE <br>
	 * - NUMBER to Integer<br>
	 * - VARCHAR2 to String<br>
	 * @param dbType
	 * @return
	 */
	private static Class convertType(String dbType){
		if("NUMBER".equals(dbType)){
			return Integer.class;
		} else if("VARCHAR2".equals(dbType)){
			return String.class;
		}
		throw new RuntimeException("[SET"+dbType+"] 정의되지 않은 타입");
	}
	
	/**
	 *  DB필드명으로 클래스에서 SET/GET 메소드명을 찾는다.
	 *  필드명에서 "_"은 제거한 이름으로 찾는다.
	 * @param clz
	 * @param field
	 * @param isSet
	 * @return
	 */
	private static String findMethodName(Class clz, String field, boolean isSet){
		Method[] methods = clz.getMethods();
		field = field.replaceAll("_", "").toUpperCase();
		String prefix = isSet?"SET":"GET";
		
		for(Method method: methods){
			String name = method.getName().toUpperCase(); 
			if( name.startsWith(prefix)
					&& name.endsWith(field)){
				return method.getName();
			}
		}
		
		throw new RuntimeException("["+prefix+field+"] 매소드명을 찾기 실패");
	}
/*	
	public static void main(String args[]){
		PofCreater pc = new PofCreater();
		List<String> methods = new ArrayList<String>();
		methods.add("SEQ_NUM");
		methods.add("Regtype");
		
		List<String> types = new ArrayList<String>();
		types.add("NUMBER");
		types.add("VARCHAR2");
		
		List<String> values = new ArrayList<String>();
		values.add("1");
		values.add("test");
		
		String clzName = "com.nonghyup.fds.pof.NfdsBlackuser";
		
		try {
			Object obj = pc.createPof(clzName, methods,types ,values );
			
			System.out.println((com.nonghyup.fds.pof.NfdsBlackuser)obj);
			
		}  catch (Exception e) {
			e.printStackTrace();
		}
	}
*/
}
