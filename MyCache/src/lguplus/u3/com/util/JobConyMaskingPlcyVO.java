package lguplus.u3.com.util;
import java.io.IOException;

import com.tangosol.io.pof.PofReader;
import com.tangosol.io.pof.PofWriter;
import com.tangosol.io.pof.PortableObject;
/**
 * <PRE>
 * 파일명 : jobConyMaskingPlcyVO 
 * 설명 :jobConyMaskingPlcyVO Output을  정의하는  VO  Class이다.
 * 주의사항 :
 * </PRE>
 *
 * @author    $Author: deployer $ 
 * @version   $Revision: 1.0 $
 * @date      $Date: 2017/01/19 04:51:45 $
 */
@SuppressWarnings("serial")
public class JobConyMaskingPlcyVO implements PortableObject {
	

/*	
 * 업무전달명
	암호화여부
	마스킹여부
	마스킹추가컬럼명
	마스킹예외컬럼명
	사용여부

	jobConvyNm
	crypYn
	mskingYn
	mskingAddClmnNm
	mskingExcpClmnNm
	useYn
*/

    /**
     * 업무전달명
     */
    public String jobConvyNm;    
	/**
     * 암호화여부
     */
    public String crypYn;    
    /**
     * 마스킹여부
     */
    public String mskingYn;
    /**
     * 마스킹추가컬럼명
     */
    public String mskingAddClmnNm;    
    /**
     * 마스킹예외컬럼명
     */
    public String mskingExcpClmnNm;    
    /**
     * 사용여부
     */
    public String useYn;
    
   
    public String getjobConvyNm(){
        return jobConvyNm;
    }
    
    public void setjobConvyNm(String jobConvyNm){
        this.jobConvyNm= jobConvyNm;
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
    
    public String getmskingExcpClmnNm(){
        return mskingExcpClmnNm;
    }
    
    public void setmskingExcpClmnNm(String mskingExcpClmnNm){
        this.mskingExcpClmnNm= mskingExcpClmnNm;
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
    	jobConvyNm        = reader.readString(0);
    	crypYn            = reader.readString(1);
    	mskingYn          = reader.readString(2);
    	mskingAddClmnNm   = reader.readString(3);
    	mskingExcpClmnNm  = reader.readString(4);
		useYn             = reader.readString(5);
	}

	@Override
	public void writeExternal(PofWriter writer) throws IOException {
		// TODO Auto-generated method stub
		writer.writeString(0, jobConvyNm);
		writer.writeString(1, crypYn);
		writer.writeString(2, mskingYn);
		writer.writeString(3, mskingAddClmnNm);
		writer.writeString(4, mskingExcpClmnNm);
		writer.writeString(5, useYn);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((crypYn == null) ? 0 : crypYn.hashCode());
		result = prime * result + ((jobConvyNm == null) ? 0 : jobConvyNm.hashCode());
		result = prime * result + ((mskingAddClmnNm == null) ? 0 : mskingAddClmnNm.hashCode());
		result = prime * result + ((mskingExcpClmnNm == null) ? 0 : mskingExcpClmnNm.hashCode());
		result = prime * result + ((mskingYn == null) ? 0 : mskingYn.hashCode());
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
		JobConyMaskingPlcyVO other = (JobConyMaskingPlcyVO) obj;
		if (crypYn == null) {
			if (other.crypYn != null)
				return false;
		} else if (!crypYn.equals(other.crypYn))
			return false;
		if (jobConvyNm == null) {
			if (other.jobConvyNm != null)
				return false;
		} else if (!jobConvyNm.equals(other.jobConvyNm))
			return false;
		if (mskingAddClmnNm == null) {
			if (other.mskingAddClmnNm != null)
				return false;
		} else if (!mskingAddClmnNm.equals(other.mskingAddClmnNm))
			return false;
		if (mskingExcpClmnNm == null) {
			if (other.mskingExcpClmnNm != null)
				return false;
		} else if (!mskingExcpClmnNm.equals(other.mskingExcpClmnNm))
			return false;
		if (mskingYn == null) {
			if (other.mskingYn != null)
				return false;
		} else if (!mskingYn.equals(other.mskingYn))
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
		return "JobConyMaskingPlcyVO [jobConvyNm=" + jobConvyNm + ", crypYn=" + crypYn + ", mskingYn=" + mskingYn
				+ ", mskingAddClmnNm=" + mskingAddClmnNm + ", mskingExcpClmnNm=" + mskingExcpClmnNm + ", useYn=" + useYn
				+ "]";
	}
}