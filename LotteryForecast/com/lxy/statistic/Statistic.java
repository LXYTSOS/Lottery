package com.lxy.statistic;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;

import com.lxy.hibernate.HibernateUtils;
import com.lxy.hibernate.Lottery;

/**
 * This class will deal with the statistics
 * this will calculate the frequency of numbers showed in each ball 
 * @author sl169
 *
 */

public class Statistic {
	
	private static Session session;
	Lottery lottery = new Lottery();
	

	
	public void statisticData(){
		List<Lottery> list = null;
		try{
			session=HibernateUtils.getSession(); 
			session.beginTransaction();         
			Query query = session.createQuery("from Lottery l order by l.date desc");
			list = query.list();     
			session.getTransaction().commit(); 
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally {
			HibernateUtils.closeSession(session);
		}
		int[] a = new int[list.size()];
		
		//statistic of the first red ball
		statisticRed1(a,list);
		statisticRed2(a,list);
		statisticRed3(a,list);
		statisticRed4(a,list);
		statisticRed5(a,list);
		statisticRed6(a,list);
		statisticBlue(a,list);
//		statisticLuckyBlue(a,list);
	}
	
	public void statisticRed1(int[] a, List list){
		//get the red_1 column of the lottery
		for (int i = 0; i < list.size(); i++) {
			lottery = (Lottery) list.get(i);
			a[i] = lottery.getRed_1();
		}
		int max = calMax(a);
		int min = calMin(a);
		//now I will calculate the numbers of the number of the first red ball
		System.out.println("===The start of the calculatation of the first red ball=== ");
		System.out.println("Max = " + max);
		calculate(a);
		System.out.println("Min = " + min);
		System.out.println("===The end of the calculatation of the first red ball=== ");
	}
	
	public void statisticRed2(int[] a, List list){
		//get the red_1 column of the lottery
		for (int i = 0; i < list.size(); i++) {
			lottery = (Lottery) list.get(i);
			a[i] = lottery.getRed_2();
		}
		int max = calMax(a);
		int min = calMin(a);
		//now I will calculate the numbers of the number of the second red ball
		System.out.println("===The start of the calculatation of the second red ball=== ");
		System.out.println("Max = " + max);
		calculate(a);
		System.out.println("Min = " + min);
		System.out.println("===The end of the calculatation of the second red ball=== ");
	}
	
	public void statisticRed3(int[] a, List list){
		//get the red_1 column of the lottery
		for (int i = 0; i < list.size(); i++) {
			lottery = (Lottery) list.get(i);
			a[i] = lottery.getRed_3();
		}
		int max = calMax(a);
		int min = calMin(a);
		//now I will calculate the numbers of the number of the third red ball
		System.out.println("===The start of the calculatation of the third red ball=== ");
		System.out.println("Max = " + max);
		calculate(a);
		System.out.println("Min = " + min);
		System.out.println("===The end of the calculatation of the third red ball=== ");
	}
	
	public void statisticRed4(int[] a, List list){
		//get the red_1 column of the lottery
		for (int i = 0; i < list.size(); i++) {
			lottery = (Lottery) list.get(i);
			a[i] = lottery.getRed_4();
		}
		int max = calMax(a);
		int min = calMin(a);
		//now I will calculate the numbers of the number of the fourth red ball
		System.out.println("===The start of the calculatation of the fourth red ball=== ");
		System.out.println("Max = " + max);
		calculate(a);
		System.out.println("Min = " + min);
		System.out.println("===The end of the calculatation of the fourth red ball=== ");
	}
	
	public void statisticRed5(int[] a, List list){
		//get the red_1 column of the lottery
		for (int i = 0; i < list.size(); i++) {
			lottery = (Lottery) list.get(i);
			a[i] = lottery.getRed_5();
		}
		int max = calMax(a);
		int min = calMin(a);
		//now I will calculate the numbers of the number of the fifth red ball
		System.out.println("===The start of the calculatation of the fifth red ball=== ");
		System.out.println("Max = " + max);
		calculate(a);
		System.out.println("Min = " + min);
		System.out.println("===The end of the calculatation of the fifth red ball=== ");
	}
	
	public void statisticRed6(int[] a, List list){
		//get the red_1 column of the lottery
		for (int i = 0; i < list.size(); i++) {
			lottery = (Lottery) list.get(i);
			a[i] = lottery.getRed_6();
		}
		int max = calMax(a);
		int min = calMin(a);
		//now I will calculate the numbers of the number of the sixth red ball
		System.out.println("===The start of the calculatation of the sixth red ball=== ");
		System.out.println("Max = " + max);
		calculate(a);
		System.out.println("Min = " + min);
		System.out.println("===The end of the calculatation of the sixth red ball=== ");
	}
	
	public void statisticBlue(int[] a, List list){
		//get the red_1 column of the lottery
		for (int i = 0; i < list.size(); i++) {
			lottery = (Lottery) list.get(i);
			a[i] = lottery.getBlue();
		}
		int max = calMax(a);
		int min = calMin(a);
		//now I will calculate the numbers of the number of the blue ball
		System.out.println("===The start of the calculatation of the blue ball=== ");
		System.out.println("Max = " + max);
		calculate(a);
		System.out.println("Min = " + min);
		System.out.println("===The end of the calculatation of the blue ball=== ");
	}
	
	public void statisticLuckyBlue(int[] a, List list){
		//get the red_1 column of the lottery
		for (int i = 0; i < list.size(); i++) {
			lottery = (Lottery) list.get(i);
			a[i] = lottery.getLucky_blue();
		}
		int max = calMax(a);
		int min = calMin(a);
		//now I will calculate the numbers of the number of the lucky blue ball
		System.out.println("===The start of the calculatation of the lucky blue ball=== ");
		System.out.println("Max = " + max);
		calculate(a);
		System.out.println("Min = " + min);
		System.out.println("===The end of the calculatation of the lucky blue ball=== ");
	}
	
	public void calculate(int[] a){
		HashMap<Integer , Integer> hash = new HashMap<Integer , Integer>();
		for (int i = 0; i < a.length; i++) {
			if(!hash.isEmpty() && hash.containsKey(a[i])){
				hash.put(a[i], hash.get(a[i])+1);
			}else{
				hash.put(a[i], 1);
			}
		}
		
		Set<Integer> set = hash.keySet();
		for (Integer key : set) {
			System.out.println(key + "===>" + hash.get(key));
		}
	}
	
	public int calMax(int[] a){
		int max = a[0];
		for (int i = 1; i < a.length; i++) {
			if(max < a[i])
				max = a[i];
		}
		return max;
	}
	
	public int calMin(int[] a){
		int min = a[0];
		for (int i = 1; i < a.length; i++) {
			if(min > a[i])
				min = a[i];
		}
		return min;
	}
	
	public static void main(String[] args){
		
		Statistic sta = new Statistic();
		sta.statisticData();
	}

}
