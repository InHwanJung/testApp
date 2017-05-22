package com.lgu.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 
 * <p>Description: ������Ƽ�� Load �ϴ� Manager Ŭ���� 
 */
public class PropertiesManager {
	/*
     * PropertiesManager
     */
    private static PropertiesManager singleInstance;
    private Properties prop = new Properties();
    
    /**
     * ������ �Լ� 
     * @throws Exception 
     *
     */
    private PropertiesManager(){
    		loadProperties ();
    }
    
    /**
     * <p>Description:  �ν��Ͻ��� �����ϴ� �޼ҵ� </p>
     *
     * @author ���ؿ�
     * @version 1.0
     * @cdate 2011. 10. 11 ���� 3:41:56
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
     * Module ���� ����ϴ� Properties �� Load �ϴ� �޼ҵ� 
     * </p>
     *
     * @author ���ؿ�
     * @version 1.0
     * @cdate 2011. 10. 11 ���� 3:41:56
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
            
            //���� ������ �߰� 
            tmp.put("lastModifiled", lastModifiled);
            prop = tmp;
            
        }
        catch(Exception e)
        {   
        	System.out.println("PROPRETY File Load �� ������ �߻��Ͽ����ϴ�.");
			e.printStackTrace();
        }
        finally
        {
            try{
                if ( in != null)
                    in.close();
            }catch(IOException ee){
            	System.out.println("PROPRETY File Load ��  FileInputStream�� Close �ϴ��� ������ �߻��Ͽ����ϴ�.");
    			ee.printStackTrace();
            }
        }          
    }
    
    /**
     * <p>Description: 
     * Load�� Property���� Key���� �̿��ؼ� Value�� ������ �޼ҵ� 
     * </p>
     *
     * @author ���ؿ�
     * @version 1.0
     * @cdate 2011. 10. 11 ���� 3:41:56
     * @param name  Load �ϰ��� �ϴ� Key 
     * @return String
     */
    public String get (String name){
		String result = "";
		
		try	{	
			if(prop.getProperty(name) != null){
				result = new String(prop.getProperty(name).getBytes("ISO-8859-1"), "UTF-8");
			}
		}catch (Exception e) {
			System.out.println("PROPRETY ���� �������� �� ������ �߻��Ͽ����ϴ�.");
			e.printStackTrace();
		}
		
		return result;
	}
    
    /**
     * <p>Description: 
     * Property���� name+"_"+i ������ Ű�� ã�� List�� ��ȯ<br/>
     * i�� 1���� �����ϸ� ���������� �����ϸ鼭 Key�� ã�´�.<br/>
     * property ���� null�� ���� ��� ���̻� ã�� �ʴ´�.
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
			System.out.println("PROPRETY ���� List ���·� �������� �� ������ �߻��Ͽ����ϴ�.");
			e.printStackTrace();
		}
		
		return result;
	}
    
    
    /**
     * <p>Description: 
     * ���Ͽ��� Property�� �ٽ� Load�� ���� �޼ҵ� 
     * </p>
     *
     * @author ���ؿ�
     * @version 1.0
     * @cdate 2011. 10. 11 ���� 3:41:56
     * @param 
     * @return void
     */
    public void refresh ()
	{
    	loadProperties ();
	}
    
    /**
     * <p>Description: 
     * Load�� Property�� ��� ������ String���·� ��ȯ�ϴ� �޼ҵ� 
     * </p>
     *
     * @author ���ؿ�
     * @version 1.0
     * @cdate 2011. 10. 11 ���� 3:41:56
     * @param 
     * @return String
     */
    public String toString ()
    {
        return String.valueOf(prop);
    }

    /**
     * <p>Description: 
     * Load�� Property�� ��� ������ Property���·� ��ȯ�ϴ� �޼ҵ� 
     * </p>
     *
     * @author ���ؿ�
     * @version 1.0
     * @cdate 2011. 10. 11 ���� 3:41:56
     * @param 
     * @return Properties
     */
    public Properties toProperties ()
    {
        return prop;
    }
    
    /**
     * <p>Description: 
     * 1. esb.config.file ��� �̸��� �ý���ȯ�� �������� ������ 
     *    esb.config.file�� ���� ������ ã�� ������ ��ã����
     * 2. ���� dir ���� ����� config ����  ���ϸ�(esb.config)���� ã�´� . 
     *    �׷��� ��ã���� ������ 
     *        
     * </p>
     *
     * @author ���ؿ� 
     * @version 1.0
     * @cdate 2011. 10. 11 ���� 3:41:56
     *
     * @param 
     * @return String ã���� ���� ��� 
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
    				"PROPRETY File["+filePath+"]�� �������� �ʽ��ϴ� .");
    	}
    	
    	
    	return filePath ;
    }

    /**
     * <p>Description: 
     * PropertiesManager�� �׽�Ʈ �ϱ����� Main�Լ�
     *        
     * </p>
     *
     * @author ���ؿ� 
     * @version 1.0
     * @cdate 2011. 10. 11 ���� 3:41:56
     *
     * @param String[]���ڰ�
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