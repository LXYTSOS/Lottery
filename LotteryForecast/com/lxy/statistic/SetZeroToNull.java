package com.lxy.statistic;

import org.hibernate.Session;

import com.lxy.hibernate.HibernateUtils;

public class SetZeroToNull {
	private static Session session;
	public void setNull(){
		
		String sql = "update tb_lottery set lucky_blue = 0, sale = 0 where lucky_blue = null || sale = null";
		try{
			session=HibernateUtils.getSession();
			session.beginTransaction();
			
			session.createSQLQuery(sql).executeUpdate();
			session.getTransaction().commit();
			System.out.println("the zeros have been set to null successfully");
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally {
			HibernateUtils.closeSession(session);
		}
		
		
	}
	
	public static void main(String[] args){
		SetZeroToNull setzn = new SetZeroToNull();
		setzn.setNull();
	}
	

}
