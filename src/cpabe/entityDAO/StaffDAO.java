package cpabe.entityDAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import cpabe.entity.Staff;

public class StaffDAO {
	/**
	 * save staff entity
	 * 
	 * @param staff
	 * */
	public void save(Staff staff) {
		Session session = HibernateUtils.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(staff);
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
		} finally {
			session.close();
		}
	}

	/**
	 * update staff's information
	 * 
	 * @param staff
	 * */
	public void updata(Staff staff) {
		Session session = HibernateUtils.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(staff);
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
		} finally {
			session.close();
		}
	}

	/**
	 * delete a staff from table in database according staff's staff_id.
	 * 
	 * @param staff_id
	 * */
	public void delete(String staff_id) {

		Session session = HibernateUtils.openSession();
		Transaction tx = null;
		try {
			Staff staff = null;
			tx = session.beginTransaction();
			List<Staff> staffs = session
					.createQuery(" from Staff s where s.staff_id = ? ")
					.setString(0, staff_id).list();
			if (staffs.size() > 0) {
				staff = staffs.get(0);
			}
			session.delete(staff);
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
		} finally {
			session.close();
		}
	}

	/**
	 * search a staff from table tb_staff in database according his/her staff_id
	 * and password(passwordMD5).
	 * 
	 * @param staff_id
	 * @param passwordMD5
	 * @return staff
	 * */
	public Staff getByIdAndPassword(String staff_id, String passwordMD5) {
		Staff staff = null;
		Session session = HibernateUtils.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			List<Staff> staffs = session
					.createQuery(
							" from Staff s where s.staff_id = ? and s.password = ? ")
					.setString(0, staff_id).setString(1, passwordMD5).list();
			if (staffs.size() > 0) {
				staff = staffs.get(0);
			}
			tx.commit();
			return staff;
		} catch (RuntimeException e) {
			tx.rollback();
		} finally {
			session.close();
		}
		return staff;
	}

	/**
	 * search a staff from table tb_staff in database according his/her staff_id
	 * 
	 * @param staff_id
	 * @return staff
	 * */
	public Staff getByStaff_id(String staff_id) {
		Staff staff = null;
		Session session = HibernateUtils.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			List<Staff> staffs = session
					.createQuery(" from Staff s where s.staff_id = ? ")
					.setString(0, staff_id).list();
			if (staffs.size() > 0) {
				staff = staffs.get(0);
			}
			tx.commit();
			return staff;
		} catch (RuntimeException e) {
			tx.rollback();
		} finally {
			session.close();
		}
		return staff;
	}

	public List<Staff> findAll() {
		List<Staff> staffs = null;
		Session session = HibernateUtils.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			staffs = session.createQuery(" from Staff ").list();
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
		} finally {
			session.close();
		}
		return staffs;
	}

	public static void main(String[] args) {
		StaffDAO dao = new StaffDAO();

		// Staff staff = dao.getByIdAndPassword("201600", "20142204");
		// System.out.println(staff.getName());

		// for(int i=0;i<5;i++){
		// Integer staff_id = 201720 + i,password = 58974562 + i,age =
		// 45+i,work_age = 18+i;
		// String name = "公孙"+i;
		// String department = "plan";
		// String attr_1 = "planner";
		// Staff staff = new Staff(staff_id.toString(), name,
		// password.toString(), age, work_age, department, attr_1);
		// dao.save(staff);
		// }

		// Staff staff = new Staff();
		// staff.setStaff_id("201623");
		// staff.setPassword("25648792");
		// staff.setName("王刚毅");
		// staff.setDepartment("hr");
		// staff.setAttr_1("hr");
		// staff.setAge(45);
		// staff.setWork_age(25);
		//
		// dao.save(staff);
		// dao.delete("201623");
		List<Staff> staffs = dao.findAll();
		System.out.println(staffs.size());
	}
}
