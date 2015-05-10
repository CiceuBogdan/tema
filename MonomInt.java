package PolinomProccesing;
/**
 * Clasa ce mosteneste clasa Monom. Coeficientul este de tip Integer
 * @author Bogdan
 *
 */

public class MonomInt extends Monom {


	MonomInt(Integer coef,int degree) {
		this.coef=coef;
		this.degree=degree;

	}

	public Monom add(Monom y){	
		if (y.checkInteger()){
			return new MonomInt(this.coef.intValue()+y.coef.intValue(),this.degree);
		}
		else return new MonomReal(this.coef.doubleValue()+y.coef.doubleValue(),this.degree);

	}

	public Monom subtract(Monom y){	
		if (y.checkInteger()){
			return new MonomInt(this.coef.intValue()-y.coef.intValue(),this.degree);
		}
		else return new MonomReal(this.coef.doubleValue()-y.coef.doubleValue(),this.degree);

	}

	public Monom multiply(Monom y){	
		if (y.checkInteger()){
			return new MonomInt(this.coef.intValue()*y.coef.intValue(),this.degree+y.degree);
		}
		else return new MonomReal(this.coef.doubleValue()*y.coef.doubleValue(),this.degree+y.degree);
	}

	public Monom derivation() {
		
		return new MonomInt(this.degree*this.coef.intValue(),this.degree-1);
	}

	public Monom negativeValue(){
		return new MonomInt(-this.coef.intValue(),this.degree);
	}
	
	public StringBuilder toStringBuilder(){
		StringBuilder monString=new StringBuilder("");;

		if (this.coef.intValue() != 0){
			if(this.coef.intValue()>0) {
				monString.append("+");
			}
			if (this.coef.intValue()!=1){
				if(this.coef.intValue()!=-1) { // verific daca coeficientul e diferit de 1 sau daca exponentul e zero nu mai conteaza daca coeficientul e diferit de unu
						monString.append(this.coef.intValue() +((this.degree!=0)?"x"+((this.degree!=1)?"^"+this.degree:""):""));
				}
				else monString.append("-"+((this.degree!=0)?"x"+((this.degree!=1)?"^"+this.degree:""):"1"));
			}
				else
					monString.append((this.degree!=0)?"x"+((this.degree!=1)?"^"+this.degree:""):""+this.coef.intValue());


		}
		return monString;
	}

	@Override
	public boolean checkInteger() {
		return true;
	}

	

}
