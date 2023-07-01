package com.vn.poly.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.vn.poly.dto.VideoLikedInfor;
import com.vn.poly.util.JPAUtil;

public class StatsDaoImpl implements StatsDaos {
	public static void main(String[] args) {
		StatsDaos sta = new StatsDaoImpl();
		sta.findVideoLikedInfo();
	}

	@Override
	public List<VideoLikedInfor> findVideoLikedInfo() {
		EntityManager em = JPAUtil.getFactory().createEntityManager();
		em.getTransaction().begin();

		String sql = "select v.id,v.title,v.href,sum(cast(h.isLike as int)) from Video v left join History h\r\n"
				+ "on h.videoID=v.id\r\n" + "where v.isActive=1\r\n" + "group by v.id,v.title,v.href\r\n"
				+ "order by\r\n" + "sum(cast(h.isLike as int)) desc";
		Query query = em.createNativeQuery(sql);
		List<Object[]> list = query.getResultList();
		List<VideoLikedInfor> lisstVid = new ArrayList<>();
		list.forEach(obj -> {
			VideoLikedInfor vid = new VideoLikedInfor();
			vid.setId((Integer) obj[0]);
			vid.setTitle((String) obj[1]);
			vid.setHref((String) obj[2]);
			vid.setTotalLike(obj[3] == null ? 0 : (Integer) obj[3]);
			lisstVid.add(vid);
		});
		System.out.println(lisstVid.toString());
//           VideoLikedInfor vid=new VideoLikedInfor();
//           vid.setId(null);
		em.getTransaction().commit();
		em.close();
		return lisstVid;
	}

}
