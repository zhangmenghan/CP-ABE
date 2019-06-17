package cpabe.entityDAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import cpabe.entity.AttributeSet;

public class AttributeSetDAO {

	public void save(AttributeSet attributeSet) {
		Session session = HibernateUtils.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(attributeSet);
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
		} finally {
			session.close();
		}
	}
	
	public void update(AttributeSet attributeSet) {
		Session session = HibernateUtils.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			int attr_id = getAttributeSet().getAttr_id();
			attributeSet.setAttr_id(attr_id);
			session.update(attributeSet);
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
		} finally {
			session.close();
		}
	}

	public AttributeSet getAttributeSet() {
		AttributeSet attributeSet = null;
		Session session = HibernateUtils.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			List<AttributeSet> attributeSets = session.createQuery(
					" from AttributeSet ").list();
			if (attributeSets.size() > 0) {
				attributeSet = attributeSets.get(0);
			}
			tx.commit();
			return attributeSet;
		} catch (RuntimeException e) {
			tx.rollback();
		} finally {
			session.close();
		}
		return attributeSet;
	}

	public void delete() {

		Session session = HibernateUtils.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			AttributeSet attributeSet = getAttributeSet();
			if (attributeSet != null) {
				session.delete(attributeSet);
			}
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
		} finally {
			session.close();
		}
	}

	public static void main(String[] args) {
//		AttributeSet attributeSet = new AttributeSet("boss:boss", "ceo:ceo", "software:cooperate",
//				"software:special", "finance:finance", "hr:hr", "planner:planner", "salesman:salesman");
//		System.out.println(new AttributeSetDAO().getAttributeSet().getAttr_1());
	}
}
