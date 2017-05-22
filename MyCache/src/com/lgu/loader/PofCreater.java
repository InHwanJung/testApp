package com.lgu.loader;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * �������� POF��ü�� �����ϴ� Ŭ����
 * ����: Coherence��������(xml) �������� POF��ä �߰� �����ϵ��� �ϱ�����
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
				throw new RuntimeException("���ǵ��� ���� Ÿ��");
			}
		}
		return obj;
	}
	
	/**
	 * DB Ÿ���� JavaŸ������ ��ȯ�Ѵ�.<br>
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
		throw new RuntimeException("[SET"+dbType+"] ���ǵ��� ���� Ÿ��");
	}
	
	/**
	 *  DB�ʵ������ Ŭ�������� SET/GET �޼ҵ���� ã�´�.
	 *  �ʵ���� "_"�� ������ �̸����� ã�´�.
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
		
		throw new RuntimeException("["+prefix+field+"] �żҵ���� ã�� ����");
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
