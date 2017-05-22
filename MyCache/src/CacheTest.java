import java.util.Iterator;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;
import lguplus.u3.com.util.UserBySpcltAuthVO;
import lguplus.u3.com.util.ApplIdMaskingPlcyVO;
import lguplus.u3.com.util.JobConyMaskingPlcyVO;

public class CacheTest {
	static NamedCache cacheSpclt = null;
	static NamedCache cacheAppl  = null;
	static NamedCache cacheJob   = null;
	
	public static void main(String args[]){
		try{
		     if(cacheSpclt == null) cacheSpclt = CacheFactory.getCache("SpcltAuthCache");
			 if(cacheAppl == null) cacheAppl   = CacheFactory.getCache("ApplMaskingCache");
			 if(cacheJob == null) cacheJob     = CacheFactory.getCache("JobMaskingCache");
			 
			 int spcltSize = cacheSpclt.size();
			 int applSize  = cacheAppl.size();
			 int jobSize   = cacheJob.size();
			 
			 UserBySpcltAuthVO auth   = null;
			 ApplIdMaskingPlcyVO appl = null;
			 JobConyMaskingPlcyVO job = null;
			 
			 System.out.println("spcltSize : "+ spcltSize);
			 System.out.println("applSize : "+ applSize);
			 System.out.println("jobSize : "+ jobSize);
			 
			 for (Iterator iter = cacheSpclt.keySet().iterator(); iter.hasNext();){				 
				 auth = (UserBySpcltAuthVO)cacheSpclt.get(iter.next());
				 System.out.println("UserId : " + auth.getuserId());
				 System.out.println("UserKey : " + auth.getUserKey());
				 System.out.println("AuthCd : " + auth.getauthCd());
				 System.out.println("AuthCntlVlue : " + auth.getauthCntlVlue());
			 }
			 			 
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("GiT Update Test");
		}
		
	}
}
