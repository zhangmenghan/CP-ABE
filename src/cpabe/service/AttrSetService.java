package cpabe.service;

import java.util.List;

import cpabe.entity.AttributeSet;
import cpabe.entityDAO.AttributeSetDAO;
import cpabe.file.FileOperator;

public class AttrSetService {
	public String createAttrSetString() {
		String attrSetString = "";
		AttributeSetDAO dao = new AttributeSetDAO();
		AttributeSet attributeSet = dao.getAttributeSet();
		if (!"".equals(attributeSet.getAttr_1())) {
			attrSetString = attrSetString + "?" + attributeSet.getAttr_1();
		} else {
			attrSetString = attrSetString + "?" + "*";
		}

		if (!"".equals(attributeSet.getAttr_2())) {
			attrSetString = attrSetString + "?" + attributeSet.getAttr_2();
		} else {
			attrSetString = attrSetString + "?" + "*";
		}

		if (!"".equals(attributeSet.getAttr_3())) {
			attrSetString = attrSetString + "?" + attributeSet.getAttr_3();
		} else {
			attrSetString = attrSetString + "?" + "*";
		}

		if (!"".equals(attributeSet.getAttr_4())) {
			attrSetString = attrSetString + "?" + attributeSet.getAttr_4();
		} else {
			attrSetString = attrSetString + "?" + "*";
		}

		if (!"".equals(attributeSet.getAttr_5())) {
			attrSetString = attrSetString + "?" + attributeSet.getAttr_5();
		} else {
			attrSetString = attrSetString + "?" + "*";
		}

		if (!"".equals(attributeSet.getAttr_6())) {
			attrSetString = attrSetString + "?" + attributeSet.getAttr_6();
		} else {
			attrSetString = attrSetString + "?" + "*";
		}

		if (!"".equals(attributeSet.getAttr_7())) {
			attrSetString = attrSetString + "?" + attributeSet.getAttr_7();
		} else {
			attrSetString = attrSetString + "?" + "*";
		}

		if (!"".equals(attributeSet.getAttr_8())) {
			attrSetString = attrSetString + "?" + attributeSet.getAttr_8();
		} else {
			attrSetString = attrSetString + "?" + "*";
		}
		return attrSetString;
	}

	public AttributeSet transStrToAttrSet(String attrSetString) {
		AttributeSet attributeSet = new AttributeSet();
		List<String> attributeList = FileOperator
				.parseStringToAttributeList(attrSetString);
		if (!"*".equals(attributeList.get(0))) {
			attributeSet.setAttr_1(attributeList.get(0));
		}
		if (!"*".equals(attributeList.get(1))) {
			attributeSet.setAttr_2(attributeList.get(1));
		}
		if (!"*".equals(attributeList.get(2))) {
			attributeSet.setAttr_3(attributeList.get(2));
		}
		if (!"*".equals(attributeList.get(3))) {
			attributeSet.setAttr_4(attributeList.get(3));
		}
		if (!"*".equals(attributeList.get(4))) {
			attributeSet.setAttr_5(attributeList.get(4));
		}
		if (!"*".equals(attributeList.get(5))) {
			attributeSet.setAttr_6(attributeList.get(5));
		}
		if (!"*".equals(attributeList.get(6))) {
			attributeSet.setAttr_7(attributeList.get(6));
		}
		if (!"*".equals(attributeList.get(7))) {
			attributeSet.setAttr_8(attributeList.get(7));
		}
		return attributeSet;
	}
}
