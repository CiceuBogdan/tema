// git version 2
package PolinomProccesing;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import java.awt.BorderLayout;

import javax.swing.border.LineBorder;

import java.awt.Color;

/**
 * Aceasta este interfata grafica care va interactiona cu utilizatorul.
 * @author Bogdan
 *
 */




@SuppressWarnings("serial")
public class SwingApp extends JFrame {

	private JTextField coefsQ,coefsP; // aici se scriu coeficientii polinoamelor
	private JLabel txtP,txtQ;// folosite ca explicatii pentru utilizator
	private JTextField afisP,afisQ; // aici se va afisa polinomul introdus
	private JTextField valueP,valueQ;
	private JButton okP,okQ; // butonul ce anunta inceperea 
	//constructiei polinomului in functie de date introduse de utilziator
	private JComponent forP,forQ,resultPanel;// JPanelurile folosite
	private String stringP,stringQ; // sirurile de caractere introdus de utilizator in JTextField-urile coefsQ si coefP
	private Polinom P,Q,R; // polinoamele cu care se lucreaza, R-polinomul rezultat


	private JTextField result;

	public SwingApp() {

		// construim Jframeul pe care vom lucra in intreaga aplicatie
		setTitle("My first Frame");
		setLocation(10,200); // default is 0,0 (top left corner)
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// functie ce asigura ca aplicatie este inchisa si nu ascunsa
		this.setResizable(false);
		this.setSize(1000,350);// marimea Jframe-ului

		forP = new JPanel(); // JPanel reprezinta o anumita parte din JFrame in care se pot pune anumite comenzi
		Dimension size = new Dimension(500,100);
		forP.setMaximumSize(size);
		forP.setPreferredSize(size);
		forP.setMinimumSize(size);

		forP.setBorder(new LineBorder(new Color(255, 0, 0), 6));
		this.add(forP, BorderLayout.NORTH);

		// componentele JPanel-ului pentru polinomul P
		// comp1///////////////////////////////////////////////////////
		txtP = new JLabel("Introduceti coeficientii lui P: ");

		//comp2///////////////////////////////////////////////////////
		coefsP = new JTextField();
		coefsP.setColumns(10);
		coefsP.setToolTipText("a0,a1,a2,a3...");
		//coefsP.setBounds(20,20,40,40);

		//comp3///////////////////////////////////////////////////////
		okP= new JButton("ok");
		okP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stringP=coefsP.getText();
				P=new Polinom(stringP);
				if (P.getCoefs().size()==0){
					afisP.setText("Polinom== coef-degree,coef-degree,coef-degree...");
				}
				else{
					afisP.setText(P.toString());
				}
			}
		}); 

		//comp4////////////////////////////////////////////////////////
		afisP=new JTextField();
		afisP.setEditable(false);
		afisP.setColumns(26);
		

		//comp5/////////////////////////////////////////
		//getValue

		JLabel txtGetVP = new JLabel(" iar x=");

		JTextField xValue = new JTextField();
		xValue.setColumns(5);
		xValue.setToolTipText("x");

		JButton getVP= new JButton("P(x)=");
		getVP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int x=Integer.parseInt(xValue.getText());
				valueP.setText(String.valueOf(P.getValue(x)));
			}
		});
		valueP=new JTextField();
		valueP.setEditable(false);
		valueP.setColumns(8);
	
		//end getValueP///////

		//Comp6/////////////////////////////////////////////////
		//integrare


		JButton integrP=new JButton("Integrare P");
		/*integrQ.setVerticalTextPosition(AbstractButton.BOTTOM);
		        integrQ.setHorizontalTextPosition(AbstractButton.LEFT);
		        integrQ.setAlignmentY(BOTTOM_ALIGNMENT);*/
		integrP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				R=P.integrate();
				result.setText(R.toString());

			}
		}); 

		//Comp7.................................................
		//derivare
		JButton derivP=new JButton("Derivare P");
		derivP.setVerticalTextPosition(AbstractButton.BOTTOM);
		derivP.setHorizontalTextPosition(AbstractButton.LEFT);
		derivP.setAlignmentY(BOTTOM_ALIGNMENT);
		derivP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				R=P.derivate();
				result.setText(R.toString());

			}
		});

		// adaugarea componentelor
		forP.add(txtP);
		forP.add(coefsP);
		forP.add(okP);
		forP.add(afisP);
		forP.add(txtGetVP);
		forP.add(xValue);
		forP.add(getVP);
		forP.add(valueP);
		forP.add(integrP);
		forP.add(derivP);

		////END forP/////////////////////////////////////////////////////////////////////

		////Start ForQ ////////////////////////////////////////////////////////////////
		forQ = new JPanel();
		forQ.setBorder(new LineBorder(Color.YELLOW, 5));
		forQ.setPreferredSize(new Dimension(700, 100));
		getContentPane().add(forQ, BorderLayout.CENTER);

		////comp1................................................................
		txtQ = new JLabel("Introduceti coeficientii lui Q: ");

		//comp2///////////////////////////////////////////////////////
		coefsQ = new JTextField();
		coefsQ.setColumns(10);
		coefsQ.setToolTipText("b0,b1,b2,b3...");
		//coefsP.setBounds(20,20,40,40);

		//comp3///////////////////////////////////////////////////////
		okQ= new JButton("ok");
		okQ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stringQ=coefsQ.getText();
				Q=new Polinom(stringQ);
				if (Q.getCoefs().size()==0){
					afisQ.setText("Polinom== coef-degree,coef-degree,coef-degree...");
				}
				else{
					afisQ.setText(Q.toString());
				}
			}
		}); 

		//comp4////////////////////////////////////////////////////////
		afisQ=new JTextField();
		afisQ.setEditable(false);
		afisQ.setColumns(26);
		

		//comp5/////////////////////////////////////////
		//getValue
		JLabel txtGetVQ = new JLabel(",iar x=");

		JTextField xValueQ = new JTextField();
		xValueQ.setColumns(5);
		xValueQ.setToolTipText("x");

		JButton getVQ= new JButton("Q(x)=");
		getVQ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int x=Integer.parseInt(xValueQ.getText());
				valueQ.setText(String.valueOf(Q.getValue(x)));
			}
		});
		valueQ=new JTextField();
		valueQ.setEditable(false);
		valueQ.setColumns(8);

		//end getValueP///////

		//Comp6/////////////////////////////////////////////////
		//integrare


		JButton integrQ=new JButton("Integrare Q");
		/*integrQ.setVerticalTextPosition(AbstractButton.BOTTOM);
        integrQ.setHorizontalTextPosition(AbstractButton.LEFT);
        integrQ.setAlignmentY(BOTTOM_ALIGNMENT);*/
		integrQ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				R=Q.integrate();
				result.setText(R.toString());

			}
		}); 

		//Comp7.................................................
		//derivare
		JButton derivQ=new JButton("Derivare Q");

		derivQ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				R=Q.derivate();
				result.setText(R.toString());

			}
		});
		// adaugarea componentelor
		forQ.add(txtQ);
		forQ.add(coefsQ);
		forQ.add(okQ);
		forQ.add(afisQ);
		forQ.add(txtGetVQ);
		forQ.add(xValueQ);
		forQ.add(getVQ);
		forQ.add(valueQ);
		forQ.add(integrQ);
		forQ.add(derivQ);


		// END forQ


		//Star resultPanel////////////////////////////////////////////////////////////////////////////////
		resultPanel = new JPanel();
		resultPanel.setBorder(new LineBorder(Color.BLUE, 5));
		resultPanel.setPreferredSize(new Dimension(700, 100));
		getContentPane().add(resultPanel, BorderLayout.SOUTH);

		// comp 1 adunare /////////////////////////////////////////////////////////////////////////////////

		JButton adunare=new JButton("P+Q");
		adunare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				R=P.add(Q);
				R=R.round(R.getCoefs());
				result.setText(R.toString());

			}
		});

		// comp 2 scadere /////////////////////////////////////////////////////////////////////////////////

		JButton scadere=new JButton("P-Q");
		scadere.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				R=P.subtract(Q);
				R=R.round(R.getCoefs());
				result.setText(R.toString());

			}
		});

		// comp 3 inmultire /////////////////////////////////////////////////////////////////////////////////

		JButton inmultire=new JButton("P*Q");
		inmultire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				R=P.multiply(Q);
				R=R.round(R.getCoefs());
				result.setText(R.toString());

			}
		});


		// comp 4 impartire /////////////////////////////////////////////////////////////////////////////////

		JButton impartire=new JButton("P/Q");
		impartire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				R=P.divide(Q);
				Polinom rest=P.subtract(Q.multiply(R));
				R=R.round(R.getCoefs());
				rest=rest.round(rest.getCoefs());
				result.setText(R.toString()+" rest "+rest.toString());

			}
		});


		////comp5 ...................................................................................
		JComponent txtRes = new JLabel("Rezultatul este: ");

		result=new JTextField();
		result.setEditable(false);
		result.setColumns(56);
		

		////comp6
		// foloseste rezultatul ca si polinomul P

		JButton useAsP=new JButton("Use for P");
		useAsP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				P=R;
				afisP.setText(P.toString());

			}
		});

		////comp7/////////////////////////////////////////////////////////////
		// foloseste rezultatul ca si polinomul Q

		JButton useAsQ=new JButton("Use for Q");
		useAsQ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Q=R;
				afisQ.setText(Q.toString());

			}
		});


		//adaugarea componentelor
		resultPanel.add(adunare);
		resultPanel.add(scadere);
		resultPanel.add(inmultire);
		resultPanel.add(impartire);
		resultPanel.add(txtRes);
		resultPanel.add(result);
		resultPanel.add(useAsP);
		resultPanel.add(useAsQ);
		


	}

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		JFrame f = new SwingApp();
		f.show();
	}
}