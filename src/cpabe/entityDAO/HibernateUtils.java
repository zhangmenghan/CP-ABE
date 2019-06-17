package cpabe.entityDAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import cpabe.entity.AttributeSet;
import cpabe.entity.FileDownloadInfo;
import cpabe.entity.FileInfo;
import cpabe.entity.Staff;

public class HibernateUtils {
	//SessionFactory is global variable 
	private static SessionFactory sessionFactory;
	
	static {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		cfg.addClass(Staff.class);
		cfg.addClass(AttributeSet.class);
		cfg.addClass(FileInfo.class);
		cfg.addClass(FileDownloadInfo.class);
		
		sessionFactory = cfg.buildSessionFactory();
	}
	
	/**
	 * get the global unique sessionfactory
	 * 
	 * @return sessionFactory
	 * */
	public static SessionFactory getsSessionFactory(){
		return sessionFactory;
	}
	
	/**
	 * open a session from the global unique sessionfactory
	 * 
	 * @return session
	 * */
	public static Session openSession(){
		return sessionFactory.openSession();
	}
}
