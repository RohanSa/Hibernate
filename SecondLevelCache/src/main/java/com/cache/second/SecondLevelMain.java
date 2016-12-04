package com.cache.second;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
/*
 * Due to second level cache, hibernate will load data from cache rather
 * than hitting database again. This data from cache can be accessed from
 * any sessions.
 */
public class SecondLevelMain {
	private static SessionFactory sessionFactory;

	public static void main(String[] args) {

		try {
			Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties()).build();
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		System.out.println("**************Loading Employee by Id***************");
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		Hemployee hemployee = (Hemployee) session.load(Hemployee.class, 1);
		System.out.println(hemployee);
		transaction.commit();
		session.close();

		System.out.println("****************Loading Employee by Id***************");
		Session session1 = sessionFactory.openSession();
		Transaction transaction1 = session1.beginTransaction();

		Hemployee hemployee1 = (Hemployee) session1.load(Hemployee.class, 1);
		System.out.println(hemployee1);
		transaction1.commit();
		session1.close();

	}
}
