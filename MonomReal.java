package PolinomProccesing;
/**
 * Clasa ce mosteneste clasa Monom. Coeficientul este de tip Double
 * @author Bogdan
 *
 */

public class MonomReal extends Monom {
	
	
	MonomReal(Double coef,int degree) {
		this.coef=coef;
		this.degree=degree;
	}
	
	
	public boolean checkInteger(){
		if (coef.doubleValue()==coef.intValue())
			return true;
		return false;
	}
	
	public Monom add(Monom y)
	{
		Monom z=new MonomReal(this.coef.doubleValue()+y.coef.doubleValue(),this.degree);
		// verific daca in urma adunarii coeficientul rezultat poate fi adus sub forma de numar intreg
		if (z.checkInteger())
			return new MonomInt(z.getCoef().intValue(),z.degree);
		else
			return z;	
	}
	
	public Monom subtract(Monom y)
	{
		Monom z=new MonomReal(this.coef.doubleValue()-y.coef.doubleValue(),this.degree);
		// verific daca in urma adunarii coeficientul rezultat poate fi adus sub forma de numar intreg
		if (z.checkInteger())
			return new MonomInt(z.getCoef().intValue(),z.degree);
		else
			return z;	
	}
	
	public Monom multiply(Monom y) {
		
		Monom z=new MonomReal(this.coef.doubleValue()*y.coef.doubleValue(),this.degree+y.degree);
		// verific daca in urma adunarii coeficientul rezultat poate fi adus sub forma de numar intreg
		if (z.checkInteger())
			return new MonomInt(z.getCoef().intValue(),z.degree);
		else
			return z;
	}

	
	public Monom derivation() {
		
		
		return new MonomReal(this.degree*this.coef.doubleValue(),this.degree-1);
	}
	
	public Monom negativeValue(){
			return new MonomReal(-this.coef.doubleValue(),this.degree);
	}
	public StringBuilder toStringBuilder(){
		StringBuilder monString=new StringBuilder("");;

		if (this.coef.doubleValue() != 0){
			if(this.coef.doubleValue()>0) {
				monString.append("+");
			}
			if (this.coef.doubleValue()!=1){
				if(this.coef.doubleValue()!=-1) { // verific daca coeficientul e diferit de 1 sau daca exponentul e zero nu mai conteaza daca coeficientul e diferit de unu
						monString.append(this.coef.doubleValue() +((this.degree!=0)?"x"+((this.degree!=1)?"^"+this.degree:""):""));
				}
				else monString.append("-"+((this.degree!=0)?"x"+((this.degree!=1)?"^"+this.degree:""):"1"));
			}
				else
					monString.append((this.degree!=0)?"x"+((this.degree!=1)?"^"+this.degree:""):""+this.coef.doubleValue());


		}
		return monString;
	}

	
	
}
