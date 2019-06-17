package cpabe.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import cpabe.entity.Staff;
import cpabe.entityDAO.StaffDAO;

public class StaffService {
	StaffDAO dao = new StaffDAO();
	public boolean verifyStaff(String staff_id,String passwordMD5){
//		String passwordMD5 = modifyPasswordMD5(password);
		boolean b =  (dao.getByIdAndPassword(staff_id, passwordMD5)!=null)?true:false;
		return b;
	}
	
//	public boolean verifyStaff(String staff_id,String password){
//		String passwordMD5 = modifyPasswordMD5(password);
//		boolean b =  (dao.getByIdAndPassword(staff_id, passwordMD5)!=null)?true:false;
//		System.out.println(b);
//		return b;
//	}
	
	public boolean isExists(String staff_id){
		return (dao.getByStaff_id(staff_id)!=null)?true:false;
	}
	
	public void addStaff(Staff staff) throws Exception{
		if(!isExists(staff.getStaff_id())){
			//modify staff'password to MD5
			staff.setPassword(modifyPasswordMD5(staff.getPassword()));
			dao.save(staff);
		}else {
			throw new Exception("<"+ staff.getName()+">is existed");
		}
	}
	
	public static String modifyPasswordMD5(String password){
		String passwordMD5 = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte[] passwordBytes = md.digest();
			passwordMD5 = new String(passwordBytes);
			return passwordMD5;
		} catch (NoSuchAlgorithmException e) {
			//Discard
		}
		return passwordMD5;
	}
}
