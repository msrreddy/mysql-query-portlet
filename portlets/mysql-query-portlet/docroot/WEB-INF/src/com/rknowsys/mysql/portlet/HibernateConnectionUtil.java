package com.rknowsys.mysql.portlet;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.portlet.ActionRequest;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletSession;
import javax.servlet.RequestDispatcher;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.connection.ConnectionProvider;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;

public class HibernateConnectionUtil implements ConnectionProvider {

	private static SessionFactory factory = null;
	
	public static void getPreferencesValues(String jdbcURL,String userName, String password)
	{
		try{
			
			
			//PortletPreferences portletPreferences=actionRequest.getPreferences();
			Configuration cfg = new Configuration();
			
			cfg.configure("/com/rknowsys/mysql/portlet/hibernate.cfg.xml").setProperty("hibernate.connection.url", jdbcURL).setProperty("hibernate.connection.username", userName)
			.setProperty("hibernate.connection.password", password).setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
			System.out.println("password=="+password);
			factory = cfg.buildSessionFactory();
			System.out.println("Session Factory object== "+factory);
			//String show_view=GetterUtil.getString(portletPreferences.getValue("SelectUserOperation", StringPool.TRUE));
			//System.out.println("in getPV method="+show_view);
			
			
		}catch(Exception ex){
			ex.printStackTrace();
			System.out.println("plaese select database settings from configuration page");
		}
	}
	public HibernateConnectionUtil() {
		
	}
	
	@Override
	public void close() throws HibernateException {
		// TODO Auto-generated method stub

	}

	@Override
	public void closeConnection(Connection arg0) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void configure(Properties arg0) throws HibernateException {
		// TODO Auto-generated method stub

	}

	@Override
	public Connection getConnection() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean supportsAggressiveRelease() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public static SessionFactory getSessionFactory(){
		return factory;
	}
	
	public static Session getSession() {
		if(factory != null) {
			return factory.openSession();
		} else {
			return null;
		}
	}
}
