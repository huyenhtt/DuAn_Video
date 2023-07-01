package com.vn.poly.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.vn.poly.model.History;
import com.vn.poly.model.Users;
import com.vn.poly.model.Video;
import com.vn.poly.util.JPAUtil;

public class HistoryDaoImpl implements HistoryDao {
public static void main(String[] args) {
	HistoryDao his=new HistoryDaoImpl();
	his.findByUserandIsLiked("huyennt");
}
	@Override
	public List<History> findByUser(String username) {
		
		EntityManager em = JPAUtil.getFactory().createEntityManager();
		List<History> list =new ArrayList<>();
		try {
			em.getTransaction().begin();
//			logic
			TypedQuery<History> query = em.createQuery("SELECT h FROM History h WHERE h.users.username=?0 AND h.video.isActive=1 ORDER BY h.viewDate DESC", History.class);
			query.setParameter(0, username);
			list = query.getResultList();
			System.out.println(list.toString());
			em.getTransaction().commit();
			System.out.println("đã tìm thấy findByUser!");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("không tìm thấy findByUser!");
		}
		em.close();
		return list;
	}

	@Override
	public List<History> findByUserandIsLiked(String username) {
		EntityManager em = JPAUtil.getFactory().createEntityManager();
		List<History> list =new ArrayList<>();
		try {
			em.getTransaction().begin();
//			logic
			TypedQuery<History> query = em.createQuery("SELECT h FROM History h WHERE h.users.username=?0 AND h.video.isActive=1 AND h.isLike=1 ORDER BY h.viewDate DESC", History.class);
			query.setParameter(0, username);
			list = query.getResultList();
			System.out.println(list.toString());
			em.getTransaction().commit();
			System.out.println("đã tìm thấy UserandIsLiked!");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("không tìm thấy UserandIsLiked!");
		}
		em.close();
		return list;
	}

	@Override
	public History findByUserIdAndVideoId(Integer userID, Integer videoID) {
		EntityManager em = JPAUtil.getFactory().createEntityManager();
		History history=new History();
		List<History> list6=new ArrayList<>();
		try {
			em.getTransaction().begin();
//			logic
			TypedQuery<History> query = em.createQuery("SELECT h FROM History h WHERE h.Users.id=?0 AND h.Video.id=?1 AND  h.Video.isActive=1", History.class);
			query.setParameter(0, userID);
			query.setParameter(1, videoID);
			 list6=query.getResultList();
			
			 history = query.getSingleResult();
//			if (list6.size()!=0) {
				System.out.println(history.toString());
			em.getTransaction().commit();
			System.out.println("đã tìm thấy UserIdAndVideoId!");
//			}
			
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("không tìm thấy UserIdAndVideoId!");
		}
		em.close();
		return history;
	}

	@Override
	public History createHis(History entity) {
		EntityManager em = JPAUtil.getFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(entity);
			em.getTransaction().commit();
			System.out.println("Them moi thanh cong!");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("Them moi that bai!");

		}
		em.close();
		return entity;
	}

	@Override
	public History updateHis(History enHistory) {
		EntityManager em = JPAUtil.getFactory().createEntityManager();
		try {
			em.getTransaction().begin();
//			logic

			em.merge(enHistory);
			em.getTransaction().commit();
			System.out.println("sua thanh cong history!");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("sua that bai history!");
		}
		em.close();
		return enHistory;
	}

}
