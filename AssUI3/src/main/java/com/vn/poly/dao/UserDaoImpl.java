package com.vn.poly.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;

import com.vn.poly.constant.NamedStored;
import com.vn.poly.constant.SessionAttr;
import com.vn.poly.model.Users;
import com.vn.poly.model.Video;
import com.vn.poly.util.JPAUtil;

public class UserDaoImpl implements UserDao {
	public static void main(String[] args) {
		UserDao userDao = new UserDaoImpl();
//		userDao.findEmail("huyen19932@gmail.com");
	}

	@Override
	public List<Users> findAll() {
		EntityManager em = JPAUtil.getFactory().createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Users> query = em.createQuery("SELECT u FROM Users u", Users.class);
		List<Users> list = query.getResultList();
		System.out.println(list.toString());
		em.getTransaction().commit();
		em.close();
		return list;
	}

	@Override
	public Users create(Users users) {
		EntityManager em = JPAUtil.getFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(users);
			em.getTransaction().commit();
			System.out.println("Them moi thanh cong!");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("Them moi that bai!");

		}
		em.close();
		return users;
	}

	@Override
	public Users update(Users users) {
		EntityManager em = JPAUtil.getFactory().createEntityManager();
		try {
			em.getTransaction().begin();
//			logic

			em.merge(users);
			em.getTransaction().commit();
			System.out.println("sua thanh cong!");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("sua that bai!");
		}
		em.close();
		return users;
	}

	@Override
	public Users delete(String id) {
		EntityManager em = JPAUtil.getFactory().createEntityManager();
		Users users = em.find(Users.class, id);
		try {
			em.getTransaction().begin();
//			logic
			em.remove(users);
			em.getTransaction().commit();
			System.out.println("xoá thành công!");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("xoá thất bại!");
		}
		em.close();
		return users;
	}

	@Override
	public List<Users> findByRole(List<Users> listU, boolean admin) {
		EntityManager em = JPAUtil.getFactory().createEntityManager();
		try {
			em.getTransaction().begin();
//			logic
			TypedQuery<Users> query = em.createQuery("SELECT u FROM Users u WHERE u.isAdmin=:role", Users.class);
			query.setParameter("role", admin);

			listU = query.getResultList();
			System.out.println(listU.toString());
			em.getTransaction().commit();
			System.out.println("đã tìm thấy!");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("không tìm thấy!");
		}
		em.close();
		return listU;
	}

	@Override
	public List<Users> findByKeyword(List<Users> list, String keyword) {
		EntityManager em = JPAUtil.getFactory().createEntityManager();
		try {
			em.getTransaction().begin();
//			logic
			TypedQuery<Users> query = em.createQuery("SELECT u FROM Users u WHERE u.username LIKE ?0", Users.class);
			query.setParameter(0, keyword);

			list = query.getResultList();
			System.out.println(list.toString());
			em.getTransaction().commit();
			System.out.println("đã tìm thấy!");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("không tìm thấy!");
		}
		em.close();
		return list;
	}

	@Override
	public Users findOne(String username, String password) {
		EntityManager em = JPAUtil.getFactory().createEntityManager();
		Users users = new Users();
		try {
			TypedQuery<Users> query = em
					.createQuery("SELECT u FROM Users u WHERE u.username=:username AND u.password=:pass", Users.class);
			query.setParameter("username", username);
			query.setParameter("pass", password);
			users = query.getSingleResult();

//			System.out.println(">>Fullname: " + user.getFullname());
			System.out.println(">>IsAdmin: " + users.getUsername());

			System.out.println("đã tìm thấy!");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("không tìm thấy!");
		}
		em.close();
		return users;
	}

	@Override
	public Users findByID(Integer id) {
		EntityManager em = JPAUtil.getFactory().createEntityManager();
		Users users = new Users();
		try {
			em.getTransaction().begin();
//			logic
			users = em.find(Users.class, id);
			System.out.println(users.toString());
			em.getTransaction().commit();
			System.out.println("đã tìm thấy!");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("không tìm thấy !");
		}
		em.close();
		return users;
	}

	@Override
	public Users checkLogin(String username, String password) {
		EntityManager em = JPAUtil.getFactory().createEntityManager();

		TypedQuery<Users> q = em.createQuery("SELECT u FROM Users u WHERE u.username = ?0 AND u.password = ?1",
				Users.class);

		q.setParameter(0, username);
		q.setParameter(1, password);

		List<Users> users = (List<Users>) q.getResultList();
		System.out.println(users);
		em.close();

		if (users.size() != 0) {
			return users.get(0);
		}
		return null;
	}

	@Override
	public Users findEmail(String email) {
		EntityManager em = JPAUtil.getFactory().createEntityManager();

		List<Users> listU = new ArrayList<>();
		try {
			em.getTransaction().begin();
//			logic
			TypedQuery<Users> query = em.createQuery("SELECT u FROM Users u WHERE u.email =?0", Users.class);
			query.setParameter(0, email);

			listU = query.getResultList();
			if (!listU.isEmpty()) {
				System.out.println(listU.toString());
				em.getTransaction().commit();
				System.out.println("đã tìm thấy!");
				return listU.get(0);
			}
		} catch (Exception e) {
			// TODO: handle exception
			em.getTransaction().rollback();
			System.out.println("không tìm thấy!");
		}

//		em.close();
		return listU.get(0);
	}

	@Override
	public Users findUsername(String username) {
		EntityManager em = JPAUtil.getFactory().createEntityManager();

		List<Users> listU = new ArrayList<>();
		try {
			em.getTransaction().begin();
//			logic
			TypedQuery<Users> query = em.createQuery("SELECT u FROM Users u WHERE u.username=:userr", Users.class);
			query.setParameter("userr", username);

			listU = query.getResultList();
			if (listU.isEmpty()) {
				return null;
			}
			System.out.println(listU.toString());
			em.getTransaction().commit();
			System.out.println("đã tìm thấy!");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("không tìm thấy!");
		}
		em.close();
		return listU.get(0);
	}

	@Override
	public List<Users> findAllVideo(int pagenumber, int pagesize) {
		EntityManager em = JPAUtil.getFactory().createEntityManager();

		try {
			em.getTransaction().begin();
//			logic

			TypedQuery<Users> query = em.createQuery("SELECT u FROM Users u", Users.class);

			query.setFirstResult(pagenumber * pagesize);
			query.setMaxResults(pagesize);

			List<Users> list = query.getResultList();

			for (Users us : list) {
				System.out.println(">>Fullname: " + us.getEmail());
				System.out.println(">>IsAdmin: " + us.getId());
			}

			em.getTransaction().commit();
			System.out.println("ok");

		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("no ok");

		}
		em.close();

		return null;
	}

	@Override
	public List<Users> findUserLikedByVideoHref(Map<String, Object> params) {
		EntityManager em = JPAUtil.getFactory().createEntityManager();
		StoredProcedureQuery procedureQuery=em.createNamedStoredProcedureQuery(NamedStored.Find_User_LikedVideo);
		params.forEach((key,value)-> procedureQuery.setParameter(key, value));
		List<Users> list=procedureQuery.getResultList();
		return list;
	}

	@Override
	public List<Users> findAllActive() {
		EntityManager em = JPAUtil.getFactory().createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Users> query = em.createQuery("SELECT u FROM Users u WhERE u.isActive=1", Users.class);
		List<Users> list = query.getResultList();
		System.out.println(list.toString());
		em.getTransaction().commit();
		em.close();
		return list;
	}

	

}
