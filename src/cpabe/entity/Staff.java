package cpabe.entity;

public class Staff {
	private String staff_id;
	private String name;
	private String password;
	private int age;
	private int work_age;
	private String department;
	private String duty;
	private String attr_1;
	private String attr_2;
	private String attr_3;

	public Staff(){
		
	}
	
	
	public Staff(String staff_id, String name, String password, int age,
			int work_age, String department, String attr_1) {
		super();
		this.staff_id = staff_id;
		this.name = name;
		this.password = password;
		this.age = age;
		this.work_age = work_age;
		this.department = department;
		this.attr_1 = attr_1;
	}


	public String getStaff_id() {
		return staff_id;
	}

	public void setStaff_id(String staff_id) {
		this.staff_id = staff_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getWork_age() {
		return work_age;
	}

	public void setWork_age(int work_age) {
		this.work_age = work_age;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public String getAttr_1() {
		return attr_1;
	}

	public void setAttr_1(String attr_1) {
		this.attr_1 = attr_1;
	}

	public String getAttr_2() {
		return attr_2;
	}

	public void setAttr_2(String attr_2) {
		this.attr_2 = attr_2;
	}

	public String getAttr_3() {
		return attr_3;
	}

	public void setAttr_3(String attr_3) {
		this.attr_3 = attr_3;
	}
}
