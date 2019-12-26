package com.woniu.pojo;

public class Chart {
  private int countC;
  private int countJ;
  
	public int getCountC() {
		return countC;
	}
	public void setCountC(int countC) {
		this.countC = countC;
	}
	public int getCountJ() {
		return countJ;
	}
	public void setCountJ(int countJ) {
		this.countJ = countJ;
	}
	@Override
	public String toString() {
		return "Chart [countC=" + countC + ", countJ=" + countJ + "]";
	}
  
	
}
