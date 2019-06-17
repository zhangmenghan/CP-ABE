package cpabe.entityDAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import cpabe.entity.FileDownloadInfo;

public class FileDownLoadInfoDAO {

	/**
	 * it is necessary to save a FileDownloadInfo when a download event happens
	 * 
	 * @param downLoadInfo
	 * */
	public void save(FileDownloadInfo downloadInfo) {
		Session session = HibernateUtils.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(downloadInfo);
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
		} finally {
			session.close();
		}
	}

	/**
	 * it is necessary to update a FileDownloadInfo when some changes occurs
	 * happens
	 * 
	 * @param downLoadInfo
	 * */
	public void update(FileDownloadInfo downloadInfo) {
		Session session = HibernateUtils.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(downloadInfo);
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
		} finally {
			session.close();
		}
	}

	/**
	 * delete a FileDownloadInfo
	 * 
	 * @param file_id
	 * */
	public void delete(String file_id) {
		FileDownloadInfo downloadInfo = null;

		Session session = HibernateUtils.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			// operation
			@SuppressWarnings("unchecked")
			List<FileDownloadInfo> downloadInfos = session
					.createQuery(
							" from FileDownloadInfo f where f.file_id = ? ")
					.setString(0, file_id).list();
			if (downloadInfos.size() > 0) {
				downloadInfo = downloadInfos.get(0);
			}
			session.delete(downloadInfo);
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
		} finally {
			session.close();
		}
	}

	/**
	 * search the filedownloadinfo according file_id
	 * 
	 * @param file_id
	 * @return downloadInfo
	 * */
	public FileDownloadInfo getByFile_id(String file_id) {
		FileDownloadInfo downloadInfo = null;
		Session session = HibernateUtils.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<FileDownloadInfo> downloadInfos = session
					.createQuery(
							" from FileDownloadInfo f where f.file_id = ? ")
					.setString(0, file_id).list();
			if (downloadInfos.size() > 0) {
				downloadInfo = downloadInfos.get(0);
			}
			tx.commit();
			return downloadInfo;
		} catch (RuntimeException e) {
			tx.rollback();
		} finally {
			session.close();
		}
		return downloadInfo;
	}

	/**
	 * obtain all filedownloadinfo regardless regardless who the downloader is
	 * 
	 * @return downloadInfos
	 * */
	@SuppressWarnings("unchecked")
	public List<FileDownloadInfo> findAll() {
		List<FileDownloadInfo> downloadInfos = null;

		Session session = HibernateUtils.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			downloadInfos = session.createQuery(" from FileDownloadInfo ")
					.list();
			tx.commit();
			return downloadInfos;
		} catch (RuntimeException e) {
			tx.rollback();
		} finally {
			session.close();
		}
		return downloadInfos;
	}

	/**
	 * obtain filedownloadinfo according the
	 * downloader'staff_id(download_staff_id)
	 * 
	 * @param download_staff_id
	 * @return downloadInfos
	 * */
	@SuppressWarnings("unchecked")
	public List<FileDownloadInfo> findByDownload_staff_id(
			String download_staff_id) {
		List<FileDownloadInfo> downloadInfos = null;
		Session session = HibernateUtils.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			downloadInfos = session
					.createQuery(
							" from FileDownloadInfo f where f.download_staff_id = ? ")
					.setString(0, download_staff_id).list();
			tx.commit();
			return downloadInfos;
		} catch (RuntimeException e) {
			tx.rollback();
		} finally {
			session.close();
		}
		return downloadInfos;
	}

	/**
	 * obtain filedownloadinfo according the
	 * downloader'staff_id(download_staff_id)
	 * 
	 * @param download_staff_id
	 * @return downloadInfos
	 * */
	@SuppressWarnings("unchecked")
	public FileDownloadInfo findByStaff_idAndFileName(String download_staff_id,
			String fileName) {
		FileDownloadInfo downloadInfo = null;
		List<FileDownloadInfo> downloadInfos = null;
		Session session = HibernateUtils.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			downloadInfos = session
					.createQuery(
							" from FileDownloadInfo f where f.download_staff_id = ? and f.file_name = ? ")
					.setString(0, download_staff_id).setString(1, fileName)
					.list();
			tx.commit();
			if (downloadInfos.size() > 0) {
				downloadInfo = downloadInfos.get(0);
			}
			return downloadInfo;
		} catch (RuntimeException e) {
			tx.rollback();
		} finally {
			session.close();
		}
		return downloadInfo;
	}

	/**
	 * obtain filedownloadinfo according the download_time regardless who the
	 * downloader is
	 * 
	 * @param download_time
	 * @return downloadInfos
	 * */
	@SuppressWarnings("unchecked")
	public List<FileDownloadInfo> findByDownload_time(String download_time) {
		List<FileDownloadInfo> downloadInfos = null;
		Session session = HibernateUtils.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			downloadInfos = session
					.createQuery(
							" from FileDownloadInfo f where f.download_time = ? ")
					.setString(0, download_time).list();
			tx.commit();
			return downloadInfos;
		} catch (RuntimeException e) {
			tx.rollback();
		} finally {
			session.close();
		}
		return downloadInfos;
	}

//	public static void main(String[] args) {
		// FileDownLoadInfoDAO dao = new FileDownLoadInfoDAO();
		// int file_id = 1;
		// String download_staff_id = "2016";
		// int download_times = 0;
		//
		// for(int i=10;i<99;i++){
		// Date date = new Date();
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// String download_time = sdf.format(date);
		// System.out.println(download_time);
		// // FileDownloadInfo downloadInfo = new FileDownloadInfo(file_id +i,
		// download_staff_id +i,download_time, download_times + i);
		// // dao.save(downloadInfo);
		// }
//		FileDownLoadInfoDAO dao = new FileDownLoadInfoDAO();
//		FileDownloadInfo info = dao.findByStaff_idAndFileName("201799", "project1-input-201422-2016-05-03.pdf.abe");
//		System.out.println("info == null"+ info == null);
//		System.out.println(info.getFile_name());
//	}
}
