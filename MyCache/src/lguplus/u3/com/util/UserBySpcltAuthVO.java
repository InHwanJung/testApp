package lguplus.u3.com.util;
import java.io.IOException;

import com.tangosol.io.pof.PofReader;
import com.tangosol.io.pof.PofWriter;
import com.tangosol.io.pof.PortableObject;
/**
 * <PRE>
 * 파일명 : userBySpcltAuthVO 
 * 설명 : jobConyMaskingPlcyVO Output을 정의하는  VO  Class이다.
 * 주의사항 :
 * </PRE>
 *
 * @author    $Author: deployer $ 
 * @version   $Revision: 1.0 $
 * @date      $Date: 2017/01/19 04:51:45 $
 */
@SuppressWarnings("serial")
public class UserBySpcltAuthVO implements PortableObject {
	
    /**
     * UserID+AuthCd
     */
    public String userKey;
	/**
     * 사용자ID
     */
    public int userId;
    /**
     * 권한코드
     */
    public String authCd;
    
    /**
     * 권한제어값
     */
    public String authCntlVlue;
   
    public String getUserKey() {
		return userKey;
	}

	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}
	
    public int getuserId(){
        return userId;
    }
    
    public void setuserId(Integer userId){
        this.userId = userId;
    }
    
    public String getauthCd(){
        return authCd;
    }
    
    public void setauthCd(String authCd){
        this.authCd = authCd;
    }
    public String getauthCntlVlue(){
        return authCntlVlue;
    }
    
    public void setauthCntlVlue(String authCntlVlue){
        this.authCntlVlue = authCntlVlue;
    }

	@Override
	public void readExternal(PofReader reader) throws IOException {
		// TODO Auto-generated method stub
		userKey      = reader.readString(0);
 		userId       = reader.readInt(1);
		authCd       = reader.readString(2);
		authCntlVlue = reader.readString(3);
	}

	@Override
	public void writeExternal(PofWriter writer) throws IOException {
		// TODO Auto-generated method stub
		writer.writeString(0, userKey);
		writer.writeInt(1, userId);
		writer.writeString(2, authCd);
		writer.writeString(3, authCntlVlue);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authCd == null) ? 0 : authCd.hashCode());
		result = prime * result + ((authCntlVlue == null) ? 0 : authCntlVlue.hashCode());
		result = prime * result + userId;
		result = prime * result + ((userKey == null) ? 0 : userKey.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserBySpcltAuthVO other = (UserBySpcltAuthVO) obj;
		if (authCd == null) {
			if (other.authCd != null)
				return false;
		} else if (!authCd.equals(other.authCd))
			return false;
		if (authCntlVlue == null) {
			if (other.authCntlVlue != null)
				return false;
		} else if (!authCntlVlue.equals(other.authCntlVlue))
			return false;
		if (userId != other.userId)
			return false;
		if (userKey == null) {
			if (other.userKey != null)
				return false;
		} else if (!userKey.equals(other.userKey))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserBySpcltAuthVO [userKey=" + userKey + ", userId=" + userId + ", authCd=" + authCd + ", authCntlVlue="
				+ authCntlVlue + "]";
	}   
   

}