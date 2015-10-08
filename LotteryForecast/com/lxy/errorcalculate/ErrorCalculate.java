package com.lxy.errorcalculate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.lxy.hibernate.Forecast;
import com.lxy.hibernate.ForecastError;
import com.lxy.hibernate.HibernateUtils;
import com.lxy.hibernate.Lottery;

/**
 * when you did your prediction
 * and the real numbers come out
 * you can run this method to figure out the error
 * so you can make the next prediction according to this error
 * @author sl169
 *
 */

public class ErrorCalculate {
	
	
	private static Session session;
	Lottery lottery = new Lottery();
	Forecast forecast = new Forecast();
	ForecastError error = new ForecastError();
	
	public void errorCalculate(){
		
		//query the latest lottery information
		List lotteryList = null;
		
		try{
			session=HibernateUtils.getSession(); 
			session.beginTransaction();         
			Query query = session.createQuery("from Lottery l where 1=1 order by l.date desc");
			query.setFirstResult(0);
			query.setMaxResults(1);
			lotteryList = query.list();     
			session.getTransaction().commit(); 
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally {
			HibernateUtils.closeSession(session);
		}
		
		for (int i = 0; i < lotteryList.size(); i++) {
			lottery = (Lottery) lotteryList.get(i);
			
			//show the lottery's phase to make sure that the two phases are the same
			System.out.println(lottery.getPhase());
		}
		
		
		//query the latest forecasted information
		List forecastList = null;
		try{
			session=HibernateUtils.getSession(); 
			session.beginTransaction();         
			Query query = session.createQuery("from Forecast f where 1=1 order by f.phase desc");
			query.setFirstResult(0);
			query.setMaxResults(1);
			forecastList = query.list();     
			session.getTransaction().commit(); 
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally {
			HibernateUtils.closeSession(session);
		}
		
		for (int i = 0; i < forecastList.size(); i++) {
			forecast = (Forecast) forecastList.get(i);
			
			//show the forecast phase to make sure that the two phases are the same
			System.out.println(forecast.getPhase());
		}
		
		//The error calculate must be made while the latest lottery's phase is the same to the latest forecast's phase
		if(lottery.getPhase().equals(forecast.getPhase())){
			//calculate the error by the formula error = lottery - forecast
			error.setPhase(lottery.getPhase());  //use the lottery and the forecast phase as the phase of error
			error.setRed_1(lottery.getRed_1() - forecast.getRed_1());
			error.setRed_2(lottery.getRed_2() - forecast.getRed_2());
			error.setRed_3(lottery.getRed_3() - forecast.getRed_3());
			error.setRed_4(lottery.getRed_4() - forecast.getRed_4());
			error.setRed_5(lottery.getRed_5() - forecast.getRed_5());
			error.setRed_6(lottery.getRed_6() - forecast.getRed_6());
			error.setBlue(lottery.getBlue() - forecast.getBlue());
			//error.setLucky_blue(lottery.getLucky_blue() - forecast.getLucky_blue());
			
			
			System.out.println(error.getRed_1());
			System.out.println(error.getRed_2());
			System.out.println(error.getRed_3());
			System.out.println(error.getRed_4());
			System.out.println(error.getRed_5());
			System.out.println(error.getRed_6());
			System.out.println(error.getBlue());
			//System.out.println(error.getLucky_blue());
			
			//save the error into table error
			try{
				session=HibernateUtils.getSession();
				session.beginTransaction();
				session.save(error);
				session.getTransaction().commit();
				System.out.println("the error stored successfully");
			}catch(Exception e) {
				e.printStackTrace();
				session.getTransaction().rollback();
			}finally {
				HibernateUtils.closeSession(session);
			}
		}else{
			System.out.println("the phases of lottery and forecast are not same, please make the forecast or wait the next lottery comes out.");
		}
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ErrorCalculate errorCal = new ErrorCalculate();
		errorCal.errorCalculate();
	}

}
