package PolinomProccesing;

import java.util.ArrayList;
//import java.util.List;


/**
 * Clasa Polinom este clasa ce formeaza un sir de Monoame si implementeaza asupra acestui sir urmatoarele operatii: 
 * a. aflarea valorii polinomului pentru o variabila data de utilizator;
 * b. adunarea a doua polinoame;
 * c. scaderea a doua polinoame;
 * d. inmultirea a doua polinoame;
 * e. impartirea a doua polinoame;
 * f. derivarea a unui polinom;
 * g. integrarea unui polinom. 
 * 
 * @author Bogdan
 * @Param: lista de Monoame
 *
 */


public class Polinom {
	private ArrayList<Monom> coefs;
	
	
	
	public Polinom(ArrayList<Monom> coefs){
		
		this.coefs=fixCoefs(coefs);
	}

	public Polinom (String stringP){

		String[] monoms=stringP.split(",");
		String[] coefDegree;
		ArrayList<Monom> newCoefs=new ArrayList<Monom>();
		
		for(String monom:monoms){
			try{
				coefDegree=monom.split("-");
				newCoefs.add(new MonomInt(Integer.parseInt(coefDegree[0]),Integer.parseInt(coefDegree[1])));
			} catch (Exception e){
				newCoefs=new ArrayList<Monom>();
				this.coefs=newCoefs;
			}
			
		}
		this.coefs=fixCoefs(newCoefs);
	
	}
	
	
	public ArrayList<Monom> getCoefs(){
		return coefs;
	}
	
	/**
	 * Functia ce calculeaza valoarea Polinomului intr-un punct dat
	 * @param x-un numar real
	 * @return valoarea Polinomului in punctul x
	 */
	public double getValue(double x){
		double value = 0;
		int exp = 0;

		for(Monom coef:coefs){
			value += coef.getCoef().doubleValue() * Math.pow(x, exp);
			exp ++;
		}

		return value;
	}
	
	/**
	 * Aceasta functie calculeaza noile Monoame corespunzatoare pentru Polinomul derivat si 
	 * formeaza un polinom nou din noile monoamele rezultate 
	 * @return Polinomul derivat
	 */
	public Polinom derivate(){

		ArrayList<Monom> newCoefs=new ArrayList<Monom>() ;


		// (a*x^n)'=n*a*x^(n-1)
		// this.coefs(0) dispare
		if (coefs.size()==1){
			newCoefs.add(new MonomInt(0,0));
			return new Polinom(newCoefs);
		}
		else{
			for (Monom coef:coefs){
				newCoefs.add(coef.derivation());
			}
			return new Polinom(newCoefs);
		}
	}

	/**
	 * Aceasta functie integreaza polinomul, iar prin urmare rezultatul va fi o primitiva a polinomului
	 * @return una dintre primitivele polinomului
	 */
	public Polinom integrate(){
		// neimplementat
		ArrayList<Monom> newCoefs= new ArrayList<Monom>();

		//constanta C
		newCoefs.add(new MonomInt(0 ,0));
		//
		for(Monom coef:coefs)
			newCoefs.add(coef.integration());
		return new Polinom(newCoefs);
	}

	/**
	 * Adunarea a doua polinoame P+Q
	 * @param Q
	 * @return un polinom ce reprezinta suma celor doua polinoame
	 */
	public Polinom add(Polinom Q){

		ArrayList<Monom> newCoefs= new ArrayList<Monom>();


		boolean sum=false;

		for (Monom coef:coefs){
			sum=false;
			for (Monom Qcoef:Q.coefs){
				if(Qcoef.getDegree()==coef.getDegree() ){
					newCoefs.add(coef.add(Qcoef));
					sum=true;
				}
			}
			if (!sum){
				newCoefs.add(coef);
			}
		}

		boolean exists=false;
		for (Monom Qcoef:Q.coefs){
			exists=false;
			for (Monom newCoef:newCoefs){
				if(Qcoef.getDegree()==newCoef.getDegree() ){
					exists=true;
				}
			}
			if(!exists){
				newCoefs.add(Qcoef);
			}

		}
		newCoefs=fixCoefs(newCoefs);
		return new Polinom(newCoefs);
	}
	
	/**
	 * Scaderea a doua polinoame P-Q
	 * @param Q
	 * @return this-Q
	 */
	public Polinom subtract(Polinom Q){

		//aceasta functie este aproape identica cu functia add, 
		//diferenta consta in faptul ca valoarea  coeficientilor din monoamele din Q sunt scazute, si nu adunate
		ArrayList<Monom> newCoefs= new ArrayList<Monom>();


		boolean sum=false;

		for (Monom coef:coefs){
			sum=false;
			for (Monom Qcoef:Q.coefs){
				if(Qcoef.getDegree()==coef.getDegree() ){
					newCoefs.add(coef.subtract(Qcoef));
					sum=true;
				}
			}
			if (!sum){
				newCoefs.add(coef);
			}
		}

		boolean exists=false;
		for (Monom Qcoef:Q.coefs){
			exists=false;
			for (Monom newCoef:newCoefs){
				if(Qcoef.getDegree()==newCoef.getDegree() ){
					exists=true;
				}
			}
			if(!exists){
				newCoefs.add(Qcoef.negativeValue());
			}

		}
		newCoefs=fixCoefs(newCoefs);
		return new Polinom(newCoefs);

	}

	/**
	 * Metoda ce realizeaza inmultirea a doua polinoame
	 * @param Q
	 * @return 
	 */
	public Polinom multiply(Polinom Q){
		ArrayList<Monom> newCoefs= new ArrayList<Monom>();
		Monom z;
		int gradMin,gradMax,newGrad;
		int i,k;
		boolean thisBigest;
		if (this.coefs.size()<Q.getCoefs().size()){
			gradMin=this.coefs.size();
			gradMax=Q.getCoefs().size();
			thisBigest=false;
		}
		else
		{
			gradMin=Q.getCoefs().size();
			gradMax=this.coefs.size();
			thisBigest=true;
		}


		newGrad=gradMin+gradMax-2;
		for(k=0;k<=newGrad;k++){
			z=new MonomInt(0,0);

			if (k<gradMin){
				for(i=0;i<=k;i++){
					z=new MonomReal(z.getCoef().doubleValue()+this.coefs.get(i).multiply(Q.coefs.get(k-i)).getCoef().doubleValue(),i);	
					if(z.checkInteger())
						z=new MonomInt(z.getCoef().intValue(),i);
				}

			}
			else {
				for(i=0;i<gradMin;i++){
					if(i+k-gradMin+1<=gradMax-1){
						if(thisBigest)
							z=new MonomReal(z.getCoef().doubleValue()+Q.getCoefs().get(gradMin-1-i).multiply(this.coefs.get(k+i-gradMin+1)).getCoef().doubleValue(),k);
						else
							z=new MonomReal(z.getCoef().doubleValue()+this.coefs.get(gradMin-1-i).multiply(Q.coefs.get(k+i-gradMin+1)).getCoef().doubleValue(),k);	
						if(z.checkInteger())
							z=new MonomInt(z.getCoef().intValue(),z.getDegree());
					}
				}

			}
			newCoefs.add(z);

		}
		return new Polinom(newCoefs);
	}
	
	/**
	
	 * Metoda ce realizeaza impartirea a doua polinoame
	 * 
	 * @param Q
	 * @return polinomul rezultat fara rest
	 */
	public Polinom divide(Polinom Q){
		/*Pentru aceasta metoda vom folosi un polinom pentru rest, un polinom pentru deimpartit care se va schimba constant, un polinom pentru rezultat, un monom  
		 * si diferite variabilele: (int)gradP,(int)gradQ,(Monom)maxCoefQ,(Monom)maxCoefP,(List<Monom>)coefsRez,(Polinom)auxRez,(List<Monom>)coefsAuxRez,(int)expMon
		 *  */

		// polinoamele auxiliare
		int i;
		Polinom rest;
		Polinom auxRez;
		ArrayList<Monom> coefsAuxRez=new ArrayList<Monom>();
		Monom Mon;


		// deimpartitul
		Polinom P=this;
		int gradP=P.getCoefs().size()-1;
		Monom maxCoefP;

		//impartitorul
		int gradQ=Q.getCoefs().size()-1;
		Monom maxCoefQ=Q.getCoefs().get(gradQ);

		//rezultatul final
		ArrayList<Monom> coefsRez=new ArrayList<Monom>();
		int gradRez=gradP-gradQ;
		// rezultatul este zero daca Gradul lui P e mai mic decat gradul lui Q////////////////////
		if (gradRez<0) {
			coefsRez.add(new MonomInt(0,0));
			return new Polinom(coefsRez);
		}
		else{
			for(i=0;i<=gradRez;i++)
				coefsRez.add(new MonomInt(0,0));
			// incepe algoritmul de impartire
			while(gradP>=gradQ) {
				// aflam coeficientul termenului rezultat din imparitre
				maxCoefP=P.getCoefs().get(gradP);
				Mon=maxCoefP.divide(maxCoefQ);
				//aflam gradul termenului rezultat

				// construim un polinom null pana la pozitia termenului rezultat
				for(i=0;i<Mon.getDegree();i++)
					coefsAuxRez.add(new MonomInt(0,0));
				//adaugam termenul in polinomul null
				coefsAuxRez.add(Mon);
				// salvam termenul in rezultatul final
				coefsRez.set(Mon.getDegree(),Mon);
				// apelam constructorul, ex rezultat: auxRez=(2x^4+0x^3+0x^2+0..) 
				auxRez=new Polinom(coefsAuxRez);
				// rest=Q*auxRez
				rest=Q.multiply(auxRez);
				// coefsAuxRez trebuie sters pentru a fi pregatit pentru noul termen ce va rezulta in urma urmatoarei impartiri
				coefsAuxRez=new ArrayList<Monom>();
				//aflam noul deimpartit si stabilim gradul acestuia
				P=P.subtract(rest);
				i=P.getCoefs().size()-1;
				while(i>-1 && P.getCoefs().get(i).getCoef().doubleValue()==0.0 )
					P.getCoefs().remove(i--);
				gradP=P.getCoefs().size()-1;

			}
		
			return new Polinom(coefsRez);
		}
	}
	
	
	/**
	 * Metoda ce oferã varianta String a polinomului
	 */
	public String toString(){

		// folosesc StringBuilder pentru a aloca mai putina memorie
		StringBuilder myAmigo=new StringBuilder("");
		int exp=coefs.size()-1;
		if (exp<0){
			return "0";
		}
		else {
			if(exp==0){
				if (coefs.get(0).coef.doubleValue()!=0){
					return coefs.get(0).toStringBuilder().toString();
				}
				else return "0";
			}

			else{
				for(Monom coef:coefs){
					myAmigo=(coef.toStringBuilder().append(myAmigo));
				}
				if (coefs.get(coefs.size()-1).getCoef().doubleValue()>=0){
					myAmigo.deleteCharAt(0);
				}
				return myAmigo.toString();
			}
		}

	}

	/**
	 * Metoda ce ia un sir de numere separate prin virgula si il transforma intr=un polinom
	 *  (ex: 1,2,3,4=4x^3+3x^2+2x+1)
	 * @param stringP
	 * @return
	 */


	private ArrayList<Monom> fixCoefs(ArrayList<Monom> coefs){
		ArrayList<Monom> newCoefs=new ArrayList<>();
		boolean ok=true;
		int i=0,degreeMax=0;

		while (ok){
			newCoefs.add(new MonomInt(0,i));
			for(Monom coef:coefs){
				if (coef.getDegree()==i){
					newCoefs.set(i, newCoefs.get(i).add(coef));

				}
				if(i==0 && coef.getDegree()>degreeMax){
					degreeMax=coef.getDegree();
				}
			}
			if (i==degreeMax){
				ok=false;
			}
			i++;

		}
		i=newCoefs.size()-1;
		while(i>-1 && newCoefs.get(i).coef.doubleValue()==0.0 )
			newCoefs.remove(i--);

		return newCoefs;
	}

	/**
	 * Pentru o afisare mai eleganta a coeficientilor reali. 
	 * Aceasta metoda poate sau nu fi folosita, in functie de nevoile utilizatorului. 
	 * Daca utilizator are nevoie de o precizie ridicata atunci nu este nevoie sa 
	 * foloseasca aceasta metoda de transformare, altfel, daca doreste sa vada un rezultat elegant, 
	 * poate folosi cu increderea aceasta metoda
	 * @param coefs
	 * @return Un polinom cu valorile reale rotunjite la o precizie de 2 unitati dupa virgula
	 */
	public Polinom round(ArrayList<Monom> coefs){

		for(Monom coef:coefs){
			if (!coef.checkInteger()){
				coef.setCoef(Math.floor((double)coef.getCoef()*100)/100);
			}
		}
		return new Polinom (coefs);
	}


}