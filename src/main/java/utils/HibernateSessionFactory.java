package utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactory {

	private static SessionFactory sessionFactory;

	public static Session getSession() {

		if (sessionFactory == null) {
			try {
				Properties props = new Properties();
				FileReader connectionProperties = new FileReader("src/main/resources/connection.properties");
				props.load(connectionProperties);
				sessionFactory = new Configuration().configure()
						.setProperty("hibernate.connection.url", props.getProperty("url"))
						.setProperty("hibernate.connection.username", props.getProperty("username"))
						.setProperty("hibernate.connection.password", props.getProperty("password"))
						.buildSessionFactory();

			} catch (IOException e) {
				e.printStackTrace();
			}

			return sessionFactory.getCurrentSession();

		} else {
			return sessionFactory.getCurrentSession();
		}
	}

}
