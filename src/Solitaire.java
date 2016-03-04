/**
 * @(#)Solitaire.java
 */
import java.awt.*;
import java.applet.*;
import java.awt.event.*;

public class Solitaire extends Applet implements ActionListener  {
	
	public int start=0;  //global!
	public int finish=0;
	Button[][] bSpot;	  // 2 dimensional array of buttons
	public void init() {
		setLayout(new GridLayout(7,7));	// gives a grid with room for 49 buttons
		bSpot=new Button[8][8];			// only use [1][1] to [7][7]
		int r,c;						// row and column
		setBackground(Color.cyan);
		for (r=1; r<8; r++){
			for (c=1; c<8; c++){
				//give the button the label of its rc value
				bSpot[r][c]=new Button(Integer.toString(r)+Integer.toString(c));
				bSpot[r][c].setForeground(Color.red);
				bSpot[r][c].setBackground(Color.red);
				add(bSpot[r][c]);
				bSpot[r][c].addActionListener(this);
	

				// clear the four courners
				if ((r==1||r==2||r==6||r==7)&&
					(c==1||c==2||c==6||c==7||c==8)){
					bSpot[r][c].setBackground(Color.cyan);
					bSpot[r][c].setForeground(Color.cyan);
					bSpot[r][c].removeActionListener(this);
				}
				// white out the centre
				if ((r==4)&&(c==4)){
					bSpot[r][c].setBackground(Color.white);
					bSpot[r][c].setForeground(Color.white);
				}
			}//for c
		}	// for r
	} // init
//*****************************************************************
	public void actionPerformed(ActionEvent e) {
		// get index of button that was pressed
		int index=Integer.parseInt(e.getActionCommand());
		// split into row and column coordinates
		int r=index / 10; 
		int c=index % 10;
		// if r2 and c2 is clicked, index = 42. e.g., 4 = 2, c = 2
		int inbetween=0;
		// if square is red make it the start.
		// if square is white make it the finish.
		if (bSpot[r][c].getBackground()==Color.red){
			start=index; 
		// if square is red (START), button at [4][2] (current example), start value = 42
		}
		else{
		// after second press, if b.spot[4][4] (FINISH)
			finish=index;
			if (checkValid(start,finish)){
				bSpot[finish/10][finish%10].setBackground(Color.red);
				bSpot[finish/10][finish%10].setForeground(Color.red);
				//make the finish red
				
				bSpot[start/10][start%10].setBackground(Color.white);
				bSpot[start/10][start%10].setForeground(Color.white);
				//make the start white
			
				//make the inbetween white
				inbetween=Math.abs((start+finish)/2);
				bSpot[inbetween/10][inbetween%10].setBackground(Color.white);
				bSpot[inbetween/10][inbetween%10].setForeground(Color.white);
			}// if checkValid
		} //else
	}//action performed method
/******************************************************************************/
	public boolean checkValid(int start,int finish) {
		boolean result=false;
		// lets say start is 42 finish is 44
		// colour anything (result is true) only if start values are 42, 24, 64, 46 (finish is 44)
		// after that, if the difference in the start and finish is 2 or 20, checkValid is true
		if (Math.abs(start-finish)==2 || Math.abs(start-finish)==20) {
			result = true;
		}
		return result;
	} // checkValid
}
