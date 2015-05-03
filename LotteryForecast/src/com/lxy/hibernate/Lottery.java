package com.lxy.hibernate;

import java.util.Date;

/*
 * the numbers of every phase of balls
 */

public class Lottery {
	
	private String date;      //the date that lottery was published
	private String phase;   //the phase of the lottery
	private int red_1;      //the first red ball, the number is from 1 to 33
	private int red_2;      //the second red ball, the number is from 1 to 33
	private int red_3;      //the third red ball, the number is from 1 to 33
	private int red_4;      //the fourth red ball, the number is from 1 to 33
	private int red_5;      //the fifth red ball, the number is from 1 to 33
	private int red_6;      //the sixth red ball, the number is from 1 to 33
	private int blue;       //the blue ball, the number is from 1 to 16
	private int lucky_blue; //the lucky blue ball, the number is from 1 to 16
	public int getLucky_blue() {
		return lucky_blue;
	}
	public void setLucky_blue(int lucky_blue) {
		this.lucky_blue = lucky_blue;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getPhase() {
		return phase;
	}
	public void setPhase(String phase) {
		this.phase = phase;
	}
	public int getRed_1() {
		return red_1;
	}
	public void setRed_1(int red_1) {
		this.red_1 = red_1;
	}
	public int getRed_2() {
		return red_2;
	}
	public void setRed_2(int red_2) {
		this.red_2 = red_2;
	}
	public int getRed_3() {
		return red_3;
	}
	public void setRed_3(int red_3) {
		this.red_3 = red_3;
	}
	public int getRed_4() {
		return red_4;
	}
	public void setRed_4(int red_4) {
		this.red_4 = red_4;
	}
	public int getRed_5() {
		return red_5;
	}
	public void setRed_5(int red_5) {
		this.red_5 = red_5;
	}
	public int getRed_6() {
		return red_6;
	}
	public void setRed_6(int red_6) {
		this.red_6 = red_6;
	}
	public int getBlue() {
		return blue;
	}
	public void setBlue(int blue) {
		this.blue = blue;
	}
	
	
	

}
