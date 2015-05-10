package PolinomProccesing;

import java.util.ArrayList;


import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
//import org.testng.annotations.BeforeTest;

import static org.testng.Assert.*;

public class PolinomTest {

	Polinom P,R;
	@DataProvider
	 public Object[][] dataGetValue() {
		return new Object[][] {
				new Object[] { 1, 6 },
				new Object[] { 2, 10 },
				new Object[] { 3, 14 },
				new Object[] { 4, 18 },
				new Object[] { 5, 22 },
		};
	}
	

	@Test(dataProvider = "dataGetValue")
	public void getValueTest1(Integer x, Integer rez) {
		ArrayList<Monom> mon=new ArrayList<Monom>();
		mon.add(new MonomInt(2,0));
		mon.add(new MonomReal(4.0,1));
		P=new Polinom(mon);
		assertEquals(P.getValue(x),rez,0.001);
	}

	@DataProvider
	public Object[][] dataToString() {
		return new Object[][] {
				new Object[] { 2,4.2, "4.2x+2" },
				new Object[] { 2, 10.0,"10x+2" },
				new Object[] { -3, 14.5,"14.5x-3" },
				new Object[] { 4, -18.4,"-18.4x+4" },
				new Object[] { 5, 22.0,"22x+5" },
		};
	}

	@Test(dataProvider = "dataToString")
	public void toStringTest1(Integer a,Double b, String rez) {
		ArrayList<Monom> mon=new ArrayList<Monom>();
		mon.add(new MonomInt(a,0));
		mon.add(new MonomReal(b,1));
		
		P=new Polinom(mon);
		assertEquals(P.toString(),rez);
	} 
	
	@DataProvider
	public Object[][] dataDerivate() {
		return new Object[][] {
				new Object[] { 2,4,2,"4x+4" },
				new Object[] { 2, 10,5,"10x+10" },
				new Object[] { -3, 14,5,"10x+14" },
				new Object[] { 4, -18,-12,"-24x-18"},
				new Object[] { 5, 22,0,"22" },
		};
	}

	@Test(dataProvider = "dataDerivate")
	public void derivateTest1(Integer a,Integer b,Integer c, String rez) {
		ArrayList<Monom> mon=new ArrayList<Monom>();
		mon.add(new MonomInt(a,0));
		mon.add(new MonomInt(b,1));
		mon.add(new MonomInt(c,2));
		
		P=new Polinom(mon);
		P=P.derivate();
		assertEquals(P.toString(),rez);
	}
	
	
	
	@DataProvider
	public Object[][] dataAdd() {
		return new Object[][] {
				new Object[] {  2,   4, 2,  1.0,  5.0,  3.0,  "5x^2+9x+3" },
				new Object[] {  2,  10, 0,  1.3,  2.1,  3.4,  "3.4x^2+12.1x+3.3" },
				new Object[] { -3,  14, 5, -1.0, -2.0, -3.0,  "2x^2+12x-4" },
				new Object[] {  4, -18, 4, -1.0, -2.0, -3.0,  "x^2-20x+3"},
				new Object[] {  5,  22, 0, -1.0, -2.0, -3.0,  "-3x^2+20x+4" },
		};
	}

	@Test(dataProvider = "dataAdd")
	public void addTest1(Integer a1,Integer a2,Integer a3,Double b1,Double b2,Double b3, String rez) {
		ArrayList<Monom> mon1=new ArrayList<Monom>();
		mon1.add(new MonomInt(a1,0));
		mon1.add(new MonomInt(a2,1));
		mon1.add(new MonomInt(a3,2));
		ArrayList<Monom> mon2=new ArrayList<Monom>();
		mon2.add(new MonomReal(b1,0));
		mon2.add(new MonomReal(b2,1));
		mon2.add(new MonomReal(b3,2));
		
		P=new Polinom(mon1);
		R=new Polinom(mon2);
		assertEquals(P.add(R).toString(),rez);
	} 
	
	@DataProvider
	public Object[][] dataSub() {
		return new Object[][] {
				new Object[] {  2,   4, 2,  1.0,  5.0,  3.0,  "-x^2-x+1" },
				new Object[] {  2,  10, 0,  1.3,  2.1,  3.4,  "-3.4x^2+7.9x+0.7" },
				new Object[] { -3,  14, 5, -1.0, -2.0, -3.0,  "8x^2+16x-2" },
				new Object[] {  4, -18, 4, -1.0, -2.0, -3.0,  "7x^2-16x+5"},
				new Object[] {  5,  22, 0, -1.0, -2.0, -3.0,  "3x^2+24x+6" },
		};
	}

	@Test(dataProvider = "dataSub")
	public void subtractTest1(Integer a1,Integer a2,Integer a3,Double b1,Double b2,Double b3, String rez) {
		ArrayList<Monom> mon1=new ArrayList<Monom>();
		mon1.add(new MonomInt(a1,0));
		mon1.add(new MonomInt(a2,1));
		mon1.add(new MonomInt(a3,2));
		ArrayList<Monom> mon2=new ArrayList<Monom>();
		mon2.add(new MonomReal(b1,0));
		mon2.add(new MonomReal(b2,1));
		mon2.add(new MonomReal(b3,2));
		
		P=new Polinom(mon1);
		R=new Polinom(mon2);
		assertEquals(P.subtract(R).toString(),rez);
	} 

	@DataProvider
	public Object[][] dataIntegrate() {
		return new Object[][] {
				new Object[] { 2,4,2,"0.6x^3+2x^2+2x" },
				new Object[] { 2, 10,5,"1.6x^3+5x^2+2x" },
				new Object[] { -3, 14,5,"1.6x^3+7x^2-3x" },
				new Object[] { 4, -18,-12,"-4x^3-9x^2+4x"},
				new Object[] { 5, 22,0,"11x^2+5x" },
		};
	}

	@Test(dataProvider = "dataIntegrate")
	public void integrateTest1(Integer a,Integer b,Integer c, String rez) {
		ArrayList<Monom> mon1=new ArrayList<Monom>();
		mon1.add(new MonomInt(a,0));
		mon1.add(new MonomInt(b,1));
		mon1.add(new MonomInt(c,2));
		
		P=new Polinom(mon1);
		assertEquals(P.integrate().toString(),rez);
	}

}
