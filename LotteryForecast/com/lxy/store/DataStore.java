package com.lxy.store;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.lxy.hibernate.HibernateUtils;
import com.lxy.hibernate.Lottery;
import com.lxy.statistic.SetZeroToNull;

/**
 * This class will store the data that grabbed from the Internet 
 * into the mysql
 * @author sl169
 *
 */

public class DataStore {
	private static Session session;

	/**
	 * split the numbers by space
	 * @param numbers
	 * @return
	 */
	public int[] analysisNumbers(String numbers) {

		//System.out.println(number);
		String[] number = numbers.split(" ");
		int[] a = new int[number.length];
		for (int i = 0; i < number.length; i++) {
			a[i] = Integer.parseInt(number[i]);
			
		}
		
		return a;

	}
	
	/**
	 * store the lottery data
	 * @param lottery
	 */
	public void storeData(Lottery lottery){
		
		System.out.println(lottery.getDate());
		System.out.println(lottery.getPhase());
		System.out.println(lottery.getRed_1());
		System.out.println(lottery.getRed_2());
		System.out.println(lottery.getRed_3());
		System.out.println(lottery.getRed_4());
		System.out.println(lottery.getRed_5());
		System.out.println(lottery.getRed_6());
		System.out.println(lottery.getBlue());
		System.out.println(lottery.getLucky_blue());
		try{
			session=HibernateUtils.getSession();
			session.beginTransaction();
			session.save(lottery);
			
			session.getTransaction().commit();
			System.out.println("the data stored successfully");
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally {
			HibernateUtils.closeSession(session);
		}
		
//		SetZeroToNull setZeroToNull = new SetZeroToNull();
//		setZeroToNull.setNull();
		
	}
	
	
	//the sql of select the newest 10 records is  
	//select * from tb_lottery where 1=1 order by lottery_date desc limit 10;

}
