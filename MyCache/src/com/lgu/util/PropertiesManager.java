package com.lgu.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 
 * <p>Description: 프로퍼티를 Load 하는 Manager 클래스 
 */
public class PropertiesManager {
	/*
     * PropertiesManager
     */
    private static PropertiesManager singleInstance;
    private Properties prop = new Properties();
    
    /**
     * 생성자 함수 
     * @throws Exception 
     *
     */
    private PropertiesManager(){
    		loadProperties ();
    }
    
    /**
     * <p>Description:  인스턴스를 생성하는 메소드 </p>
     *
     * @author 박준영
     * @version 1.0
     * @cdate 2011. 10. 11 오후 3:41:56
     *
     * @return PropertiesManager 
     */
    
    public static PropertiesManager getInstance()   {
        if (singleInstance == null) {
            synchronized (PropertiesManager.class) {               
            	singleInstance = new PropertiesManager();                
            }
        }
        return singleInstance;
    }    
    
    
    /**
     * <p>Description: 
     * Module 에서 사용하는 Properties 를 Load 하는 메소드 
     * </p>
     *
     * @author 박준영
     * @version 1.0
     * @cdate 2011. 10. 11 오후 3:41:56
     * @param 
     * @return void
     */
    public void loadProperties(){
    	FileInputStream in =  null ;
    	File file = null;
    	Properties tmp = null;
        try
        {       	
         	file = new File(getFile()) ;    
         	Long lastModifiled = new Long(file.lastModified());
            in = new FileInputStream(file);
            tmp = new Properties();
            tmp.load(in);  
            
            //최종 수정일 추가 
            tmp.put("lastModifiled", lastModifiled);
            prop = tmp;
            
        }
        catch(Exception e)
        {   
        	System.out.println("PROPRETY File Load 중 에러가 발생하였습니다.");
			e.printStackTrace();
        }
        finally
        {
            try{
                if ( in != null)
                    in.close();
            }catch(IOException ee){
            	System.out.println("PROPRETY File Load 중  FileInputStream을 Close 하던중 에러가 발생하였습니다.");
    			ee.printStackTrace();
            }
        }          
    }
    
    /**
     * <p>Description: 
     * Load된 Property에서 Key값을 이용해서 Value를 얻어오는 메소드 
     * </p>
     *
     * @author 박준영
     * @version 1.0
     * @cdate 2011. 10. 11 오후 3:41:56
     * @param name  Load 하고자 하는 Key 
     * @return String
     */
    public String get (String name){
		String result = "";
		
		try	{	
			if(prop.getProperty(name) != null){
				result = new String(prop.getProperty(name).getBytes("ISO-8859-1"), "UTF-8");
			}
		}catch (Exception e) {
			System.out.println("PROPRETY 값을 가져오는 중 에러가 발생하였습니다.");
			e.printStackTrace();
		}
		
		return result;
	}
    
    /**
     * <p>Description: 
     * Property에서 name+"_"+i 형태의 키를 찾아 List로 반환<br/>
     * i는 1부터 시작하며 순차적으로 증가하면서 Key를 찾는다.<br/>
     * property 값이 null이 있을 경우 더이상 찾지 않는다.
     * </p>
     */
    public List<String> getArr (String name) {
    	List<String> result = new ArrayList<String>();
		try{
			for ( int i = 1; ; i++){
				String value = prop.getProperty(name+"_"+(i));
				if( value != null && value.length() > 0){
					result.add( value );
				}else{
					break;
				}
			}  
		}catch (Exception e) {
			System.out.println("PROPRETY 값을 List 형태로 가져오는 중 에러가 발생하였습니다.");
			e.printStackTrace();
		}
		
		return result;
	}
    
    
    /**
     * <p>Description: 
     * 파일에서 Property를 다시 Load해 오는 메소드 
     * </p>
     *
     * @author 박준영
     * @version 1.0
     * @cdate 2011. 10. 11 오후 3:41:56
     * @param 
     * @return void
     */
    public void refresh ()
	{
    	loadProperties ();
	}
    
    /**
     * <p>Description: 
     * Load된 Property의 모든 정보를 String형태로 반환하는 메소드 
     * </p>
     *
     * @author 박준영
     * @version 1.0
     * @cdate 2011. 10. 11 오후 3:41:56
     * @param 
     * @return String
     */
    public String toString ()
    {
        return String.valueOf(prop);
    }

    /**
     * <p>Description: 
     * Load된 Property의 모든 정보를 Property형태로 반환하는 메소드 
     * </p>
     *
     * @author 박준영
     * @version 1.0
     * @cdate 2011. 10. 11 오후 3:41:56
     * @param 
     * @return Properties
     */
    public Properties toProperties ()
    {
        return prop;
    }
    
    /**
     * <p>Description: 
     * 1. esb.config.file 라는 이름의 시스템환경 변수값이 있으면 
     *    esb.config.file값 으로 파일을 찾고 파일이 못찾으면
     * 2. 현재 dir 에서 상대경로 config 에서  파일명(esb.config)으로 찾는다 . 
     *    그래도 못찾으면 에러임 
     *        
     * </p>
     *
     * @author 박준영 
     * @version 1.0
     * @cdate 2011. 10. 11 오후 3:41:56
     *
     * @param 
     * @return String 찾아진 파일 경로 
     * @throws Exception
     */
    private String getFile() throws Exception{    	
    	String filePath = null ; 

	  	String configFile = System.getProperty("lgu.config.file");
	  	filePath = (configFile!=null)?configFile:"config/config.properties";
   		
   		//filePath = "E:/esb.property";
    	File file = new File(filePath);
    	if (file == null || !file.exists()){
    		throw new Exception(
    				"PROPRETY File["+filePath+"]이 존재하지 않습니다 .");
    	}
    	
    	
    	return filePath ;
    }

    /**
     * <p>Description: 
     * PropertiesManager를 테스트 하기위한 Main함수
     *        
     * </p>
     *
     * @author 박준영 
     * @version 1.0
     * @cdate 2011. 10. 11 오후 3:41:56
     *
     * @param String[]인자값
     * @return void 
     */
    public static void main(String[] args){
    	try{
    	List<String> aaa = PropertiesManager.getInstance().getArr("lgu.rule.sql");
    	for(int i=0; i<aaa.size(); i++){
    		System.out.println("aaa["+i+"]:"+aaa.get(i));
    	}
		
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
}