package com.vn.poly.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.vn.poly.model.Users;
import com.vn.poly.model.Video;
import com.vn.poly.util.JPAUtil;

public class VideoDaoImpl implements VideoDao {
	public static void main(String[] args) {
		VideoDao videoDao = new VideoDaoImpl();
		videoDao.findByHref("2QnDy0kVIrQ");
	}

	@Override
	public List<Video> findAll() {
		EntityManager em = JPAUtil.getFactory().createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Video> query = em.createQuery("SELECT v FROM Video v WHERE v.isActive=1", Video.class);
		List<Video> list = query.getResultList();
		System.out.println(list.toString());
		em.getTransaction().commit();
		em.close();
		return list;
	}

	@Override
	public Video create(Video video) {
		EntityManager em = JPAUtil.getFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(video);
			em.getTransaction().commit();
			System.out.println("Them moi thanh cong!");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("Them moi that bai!");

		}
		em.close();
		return video;
	}

	@Override
	public Video update(Video video) {
		EntityManager em = JPAUtil.getFactory().createEntityManager();
		try {
			em.getTransaction().begin();
//			logic

			em.merge(video);
			em.getTransaction().commit();
			System.out.println("sua thanh cong!");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("sua that bai!");
		}
		em.close();
		return video;
	}

	@Override
	public Video delete(String id) {
		EntityManager em = JPAUtil.getFactory().createEntityManager();
		Video video = em.find(Video.class, id);
		try {
			em.getTransaction().begin();
//			logic
			em.remove(video);
			em.getTransaction().commit();
			System.out.println("xoá thành công!");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("xoá thất bại!");
		}
		em.close();
		return video;
	}

	@Override
	public Video findByID(Integer id) {
		EntityManager em = JPAUtil.getFactory().createEntityManager();
		Video video = new Video();
		try {
			em.getTransaction().begin();
//			logic
			video = em.find(Video.class, id);
			System.out.println(video.toString());
			em.getTransaction().commit();
			System.out.println("đã tìm thấy!");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("không tìm thấy !");
		}
		em.close();
		return video;
	}

	@Override
	public Video findByHref(String href) {
		EntityManager em = JPAUtil.getFactory().createEntityManager();
		Video video = null;
		try {
			em.getTransaction().begin();
//			logic
			TypedQuery<Video> query = em.createQuery("SELECT u FROM Video u WHERE u.href =?0", Video.class);
			query.setParameter(0, href);

			video = query.getSingleResult();
//			System.out.println(video.toString());
			System.out.println("Id=" + video.getId().toString());
			em.getTransaction().commit();
			System.out.println("đã tìm thấy!");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("không tìm thấy!");
		}
		em.close();
		return video;
	}
//	public static void findPage(int page, int size) {
//		EntityManagerFactory enf = Persistence.createEntityManagerFactory("JAVA04_PolyOE");
//		EntityManager em = enf.createEntityManager();
//		try {
//			em.getTransaction().begin();
////			logic
//
//			TypedQuery<Users> query = em.createQuery("SELECT u FROM Users u", Users.class);
//
//			query.setFirstResult(page * size);
//			query.setMaxResults(size);
//
//			List<Users> list = query.getResultList();
//
//			for (Users us : list) {
//				System.out.println(">>Fullname: " + us.getFullname());
//				System.out.println(">>IsAdmin: " + us.getAdmin());
//			}
//
//			em.getTransaction().commit();
//			System.out.println("ok");
//
//		} catch (Exception e) {
//			em.getTransaction().rollback();
//			System.out.println("no ok");
//
//		}
//		em.close();
//		enf.close();
//	}
	@Override
	public List<Video> findAllVideo(int pagenumber, int pagesize) {
		EntityManager em = JPAUtil.getFactory().createEntityManager();
		List<Video> list = null ;
		try {
			em.getTransaction().begin();
//			logic

			TypedQuery<Video> query = em.createQuery("SELECT u FROM Video u WHERE u.isActive=1", Video.class);

			query.setFirstResult((pagenumber-1) * pagesize);
			query.setMaxResults(pagesize);

			 list = query.getResultList();

			for (Video us : list) {
				System.out.println(">>href: " + us.getHref());
				System.out.println(">>Id: " + us.getId());
			}

			em.getTransaction().commit();
			System.out.println("ok");

		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("no ok");

		}
		em.close();
	
		return list;
	}

}
