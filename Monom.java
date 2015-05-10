package PolinomProccesing;
/**
 * 
 * Clasa abstracta ce retine coeficientul si gradul Monomului
 * 
 * @author Bogdan Ciceu
 *
 */
public abstract class Monom {
	protected int degree;
	protected Number coef; 

	public Number getCoef(){
		return coef;
	}

	public int getDegree() {
		return degree;
	}

	public void setDegree(int degree) {
		this.degree = degree;
	}

	public void setCoef(Number coef) {
		this.coef = coef;
	}

	public abstract Monom add(Monom y);
	public abstract Monom subtract(Monom y);
	public abstract Monom multiply(Monom y);
	public abstract Monom derivation();
	public abstract StringBuilder toStringBuilder();
	public abstract boolean checkInteger();
	public abstract Monom negativeValue();

	public Monom divide(Monom y){	
		Monom z=new MonomReal(this.coef.doubleValue()/y.coef.doubleValue(),this.degree-y.degree);
		if(z.checkInteger()){
			return new MonomInt(this.coef.intValue()/y.coef.intValue(),this.degree-y.degree);
		}
		else {
			return z;
		}
	}
	
	public Monom integration() {
		// Functia Math.floor im rotunjeste valoarea in functie de numarul la care impart, 
		//daca vreau o zecimala =>val*10/10
		//daca vreau doua zecimale =>val*100/100 etc.
		Monom z=new MonomReal(Math.floor((double)(this.coef.doubleValue()/(this.degree+1))*10)/10,this.degree+1);
		if (z.checkInteger()){
			return new MonomInt(this.coef.intValue()/(this.degree+1),this.degree+1);
		}
		else return z;
	}
}
