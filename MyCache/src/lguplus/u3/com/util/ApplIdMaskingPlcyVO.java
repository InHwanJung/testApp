package lguplus.u3.com.util;

import java.io.IOException;

import com.tangosol.io.pof.PofReader;
import com.tangosol.io.pof.PofWriter;
import com.tangosol.io.pof.PortableObject;

/**
 * <PRE>
 * ���ϸ� : applIdMaskingPlcyVO 
 * ���� : applIdMaskingPlcyVO Output�� �����ϴ�  VO   Class�̴�.
 * ���ǻ��� :
 * </PRE>
 *
 * @author    $Author: deployer $ 
 * @version   $Revision: 1.0 $
 * @date      $Date: 2017/01/19 04:51:45 $
 */
@SuppressWarnings("serial")
public class ApplIdMaskingPlcyVO implements PortableObject {
	

/*	applicationIdNm
	sysDvCd
	crypYn
	mskingYn
	mskingAddClmnNm
	useYn
	
	���ø����̼�ID��
	�ý��۱����ڵ�
	��ȣȭ����
	����ŷ����
	����ŷ�߰��÷���
	��뿩��
*/
	/**
     * ���ø����̼�ID��
     */
    public String applicationIdNm;    
	/**
     * �ý��۱����ڵ�
     */
    public String sysDvCd;    
    /**
     * ��ȣȭ����
     */
    public String crypYn;
    /**
     * ����ŷ����
     */
    public String mskingYn;    
    /**
     * ����ŷ�߰��÷���
     */
    public String mskingAddClmnNm;    
    /**
     * ��뿩��
     */
    public String useYn;
    
   
    public String getapplicationIdNm(){
        return applicationIdNm;
    }
    
    public void setapplicationIdNm(String applicationIdNm){
        this.applicationIdNm= applicationIdNm;
    } 
    
    public String getsysDvCd(){
        return sysDvCd;
    }
    
    public void setsysDvCd(String sysDvCd){
        this.sysDvCd= sysDvCd;
    } 
    public String getcrypYn(){
        return crypYn;
    }
    
    public void setcrypYn(String crypYn){
        this.crypYn= crypYn;
    } 
    public String getmskingYn(){
        return mskingYn;
    }
    
    public void setmskingYn(String mskingYn){
        this.mskingYn= mskingYn;
    } 
    public String getmskingAddClmnNm(){
        return mskingAddClmnNm;
    }
    
    public void setmskingAddClmnNm(String mskingAddClmnNm){
        this.mskingAddClmnNm= mskingAddClmnNm;
    } 
    public String getuseYn(){
        return useYn;
    }
    
    public void setuseYn(String useYn){
        this.useYn= useYn;
    } 
    
    @Override
	public void readExternal(PofReader reader) throws IOException {
		// TODO Auto-generated method stub
 		applicationIdNm  = reader.readString(0);
		sysDvCd          = reader.readString(1);
		crypYn           = reader.readString(2);
		mskingYn         = reader.readString(3);
		mskingAddClmnNm  = reader.readString(4);
		useYn            = reader.readString(5);
	}

	@Override
	public void writeExternal(PofWriter writer) throws IOException {
		// TODO Auto-generated method stub
		writer.writeString(0, applicationIdNm);
		writer.writeString(1, sysDvCd);
		writer.writeString(2, crypYn);
		writer.writeString(3, mskingYn);
		writer.writeString(4, mskingAddClmnNm);
		writer.writeString(5, useYn);
	}
	
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((applicationIdNm == null) ? 0 : applicationIdNm.hashCode());
		result = prime * result + ((crypYn == null) ? 0 : crypYn.hashCode());
		result = prime * result + ((mskingAddClmnNm == null) ? 0 : mskingAddClmnNm.hashCode());
		result = prime * result + ((mskingYn == null) ? 0 : mskingYn.hashCode());
		result = prime * result + ((sysDvCd == null) ? 0 : sysDvCd.hashCode());
		result = prime * result + ((useYn == null) ? 0 : useYn.hashCode());
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
		ApplIdMaskingPlcyVO other = (ApplIdMaskingPlcyVO) obj;
		if (applicationIdNm == null) {
			if (other.applicationIdNm != null)
				return false;
		} else if (!applicationIdNm.equals(other.applicationIdNm))
			return false;
		if (crypYn == null) {
			if (other.crypYn != null)
				return false;
		} else if (!crypYn.equals(other.crypYn))
			return false;
		if (mskingAddClmnNm == null) {
			if (other.mskingAddClmnNm != null)
				return false;
		} else if (!mskingAddClmnNm.equals(other.mskingAddClmnNm))
			return false;
		if (mskingYn == null) {
			if (other.mskingYn != null)
				return false;
		} else if (!mskingYn.equals(other.mskingYn))
			return false;
		if (sysDvCd == null) {
			if (other.sysDvCd != null)
				return false;
		} else if (!sysDvCd.equals(other.sysDvCd))
			return false;
		if (useYn == null) {
			if (other.useYn != null)
				return false;
		} else if (!useYn.equals(other.useYn))
			return false;
		return true;
	}	
    
	@Override
	public String toString() {
		return "ApplIdMaskingPlcyVO [applicationIdNm=" + applicationIdNm + ", sysDvCd=" + sysDvCd + ", crypYn=" + crypYn
				+ ", mskingYn=" + mskingYn + ", mskingAddClmnNm=" + mskingAddClmnNm + ", useYn=" + useYn + "]";
	}	
}