package cpabe.entityDAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import cpabe.entity.FileInfo;

public class FileInfoDAO {

	/**
	 * when a upload-event happens,we need to store a record(fileInfo) for it.
	 * 
	 * @param fileInfo
	 * */
	public void save(FileInfo fileInfo) {
		Session session = HibernateUtils.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(fileInfo);
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
		} finally {
			session.close();
		}
	}

	/**
	 * delete the specified filename file
	 * 
	 * @param file_name
	 * */
	public void delete(String filename) {
		FileInfo fileInfo = null;
		Session session = HibernateUtils.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			@SuppressWarnings("unchecked")
			List<FileInfo> fileInfos = session
					.createQuery(" from FileInfo f where f.name = ? ")
					.setString(0, filename).list();
			if (fileInfos.size() > 0) {
				fileInfo = fileInfos.get(0);
			}
			session.delete(fileInfo);
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
		} finally {
			session.close();
		}
	}

	/**
	 * update file's information, such as file'total download times.
	 * 
	 * @param fileInfo
	 * */
	public void update(FileInfo fileInfo) {
		Session session = HibernateUtils.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(fileInfo);
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
		} finally {
			session.close();
		}
	}

	/**
	 * get the specified filename file'fileInfo.
	 * 
	 * @param file_name
	 * @return fileInfo
	 * */
	@SuppressWarnings("unchecked")
	public FileInfo getByFile_name(String file_name) {

		FileInfo fileInfo = null;
		Session session = HibernateUtils.openSession();
		List<FileInfo> fileInfos  = null;
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			// operation
			fileInfos = session
					.createQuery(" from FileInfo i where i.name = ? ")
					.setString(0, file_name).list();
			tx.commit();
			if (fileInfos.size() > 0) {
				fileInfo = fileInfos.get(0);
			}
			return fileInfo;
		} catch (RuntimeException e) {
			tx.rollback();
		} finally {
			session.close();
		}
		return fileInfo;
	}

	/**
	 * get all fileinfos that uploaded by the specified upload_staff_id.
	 * 
	 * @param upload_staff_id
	 * @return fileInfos
	 * */
	@SuppressWarnings("unchecked")
	public List<FileInfo> getFileInfosByUpload_staff_id(String upload_staff_id) {
		List<FileInfo> fileInfos = null;
		Session session = HibernateUtils.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			// operation
			fileInfos = session
					.createQuery(
							" from FileInfo f where f.upload_staff_id = ? ")
					.setString(0, upload_staff_id).list();
			tx.commit();
			return fileInfos;
		} catch (RuntimeException e) {
			tx.rollback();
		} finally {
			session.close();
		}
		return fileInfos;
	}

	/**
	 * get all fileinfos that uploaded by the specified uploade_date.
	 * 
	 * @param uploade_date
	 * @return fileInfos
	 * */
	@SuppressWarnings("unchecked")
	public List<FileInfo> getFileInfosByUpload_time(String upload_date) {
		List<FileInfo> fileInfos = null;
		Session session = HibernateUtils.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			// operation
			fileInfos = session
					.createQuery(" from FileInfo f where f.upload_date = ? ")
					.setString(0, upload_date).list();
			tx.commit();
			return fileInfos;
		} catch (RuntimeException e) {
			tx.rollback();
		} finally {
			session.close();
		}
		return fileInfos;
	}

	@SuppressWarnings("unchecked")
	public List<FileInfo> getAllFileInfos() {
		List<FileInfo> fileInfos = null;
		Session session = HibernateUtils.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			fileInfos = session.createQuery(" from FileInfo ").list();
			tx.commit();
			return fileInfos;
		} catch (RuntimeException e) {
			tx.rollback();
		} finally {
			session.close();
		}
		return fileInfos;
	}
	
//	public static void main(String[] args) {
//		FileInfoDAO dao  = new FileInfoDAO();
//		int id = 1;
//		String name = "input.pdf";
//		String upload_staff_id = "2016";
//		String upload_date = null;
//		String policy = "a b c 2of3";
//		String profile = "policy is a b c 2of3";
//		int download_times = 2;
//		long filesize = 5;
//		
//		for(int i=40;i<99;i++){
//			String serverFileName = FileOperator.createFileNameInServer(name);
//			System.out.println(serverFileName);
//			Date date = new Date();
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			upload_date = sdf.format(date);
//			FileInfo fileInfo = new FileInfo(id + i, serverFileName, upload_staff_id + i, upload_date, policy, profile, download_times + i, filesize +i);
//			dao.save(fileInfo);
//		}
//		FileInfoDAO dao  = new FileInfoDAO();
//		FileInfo fileInfo = dao.getByFile_name("input-2016-05-07-13:49:36.pdf.ABE");
//		System.out.println("fileInfo == null:"+fileInfo == null);
////		System.out.println(dao.getAllFileInfos().size());
//		System.out.println();
//		System.out.println(dao.getFileInfosByUpload_time("2016-05-07 13:50:10").size());
//	}
}
