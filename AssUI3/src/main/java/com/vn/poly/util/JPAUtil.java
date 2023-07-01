package com.vn.poly.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
//	private static EntityManagerFactory factory;

//	static public EntityManager getEntityManager() {
//		if (factory == null || !factory.isOpen()) {
//			factory = Persistence.createEntityManagerFactory("JAVA04_PolyOE");
//
//		}
//		return factory.createEntityManager();
//	}
//
//	static public void shutdown() {
//		if (factory != null && factory.isOpen()) {
//			factory.close();
//		}
//		factory = null;
//	}
	static EntityManagerFactory factory = null;

	public static EntityManagerFactory getFactory() {
		// 1. Load persistence => EntityMnagerFactory
		if (factory == null) {
			factory = Persistence.createEntityManagerFactory("JAVA04_PolyOE");
		}
		return factory;
	}
}
