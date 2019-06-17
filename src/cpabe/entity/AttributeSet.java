package cpabe.entity;

public class AttributeSet {
	private int attr_id;
	private String attr_1;
	private String attr_2;
	private String attr_3;
	private String attr_4;
	private String attr_5;
	private String attr_6;
	private String attr_7;
	private String attr_8;
	
	public AttributeSet(){
		
	}
	
	
	
	public AttributeSet( String attr_1, String attr_2, String attr_3,
			String attr_4, String attr_5, String attr_6, String attr_7,
			String attr_8) {
		super();
		this.attr_1 = attr_1;
		this.attr_2 = attr_2;
		this.attr_3 = attr_3;
		this.attr_4 = attr_4;
		this.attr_5 = attr_5;
		this.attr_6 = attr_6;
		this.attr_7 = attr_7;
		this.attr_8 = attr_8;
	}
	
	public AttributeSet(int attr_id, String attr_1, String attr_2,
			String attr_3, String attr_4, String attr_5, String attr_6,
			String attr_7, String attr_8) {
		super();
		this.attr_id = attr_id;
		this.attr_1 = attr_1;
		this.attr_2 = attr_2;
		this.attr_3 = attr_3;
		this.attr_4 = attr_4;
		this.attr_5 = attr_5;
		this.attr_6 = attr_6;
		this.attr_7 = attr_7;
		this.attr_8 = attr_8;
	}



	public int getAttr_id() {
		return attr_id;
	}

	public void setAttr_id(int attr_id) {
		this.attr_id = attr_id;
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
	public String getAttr_4() {
		return attr_4;
	}
	public void setAttr_4(String attr_4) {
		this.attr_4 = attr_4;
	}
	public String getAttr_5() {
		return attr_5;
	}
	public void setAttr_5(String attr_5) {
		this.attr_5 = attr_5;
	}
	public String getAttr_6() {
		return attr_6;
	}
	public void setAttr_6(String attr_6) {
		this.attr_6 = attr_6;
	}
	public String getAttr_7() {
		return attr_7;
	}
	public void setAttr_7(String attr_7) {
		this.attr_7 = attr_7;
	}
	public String getAttr_8() {
		return attr_8;
	}
	public void setAttr_8(String attr_8) {
		this.attr_8 = attr_8;
	}
}
