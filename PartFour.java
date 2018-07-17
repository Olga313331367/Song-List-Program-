/*
 * Name: Bashalkhanova Olga
 * School: Fredericton High
 * Local Facilitator: Ms Sutherland
 * Assignment Number: J13
 * Date: June 8th, 2018
*/

// This program scrolls a list of five songs, in different colours every time

//importing necessary classes from the java library

import java.awt.*;
import java.applet.Applet;
import java.util.*;

public class PartFour extends Applet implements Runnable 		//inherit stuff from the Applet class
 {//class definition begins
	//variables 

	int width = 400;
	int height = 200;
	int yPos = 0;
	int xPos = width / 2 - 20;
	int nameCounter = 0;
	int colourIndex;

	Thread runner;							//declaring the thread
    
	String[] list = {"The Spin", "Arete", "Supermassive Black Hole", "Oceans", "Fly Away With Me"};
    	//creating an array of strings to hold the names of songs

	public void start()
	{//this method is called whenever the user returns to the applet and it is needed so that the applet does not run when there is nobody to look at it
	
		if (runner == null)					//if there is no thread
		{
			runner = new Thread(this);			//passing the current focus (this applet) into the thread constructor as an argument using the 'this' command. This is needed so that the program knows where to look for its behaviour
			runner.start();				
		}
	}

	public void run()
	{//this method contains the majority of the instructions and is called whenever a new thread is started
		
		while(xPos <= width)					//while the text is within the applet
		{
			if (yPos < (height / 2))			//if the text is higher than the center 
			{
				yPos += 2;				//increment the y position counter (move the text down a bit)
                 		repaint();				//repaint the text (call the paint method again)
                 		try 
                 		{
                     			runner.sleep(50);		//'sit still' for 50 milliseconds so that this loop does not dominate the processor and other parts of the program can execute
                 		}
                		catch (InterruptedException e) { }	
		 	}

			else if ((yPos == (height / 2)) && (xPos != width))	//if the text reaches the middle (height wise) and is still within the applet (width wise)	
			{
				xPos +=2;				//increment the x position counter
				repaint();				//the rest of this is the same as in the first if statement
				try
				{
					runner.sleep(50);
				}
				catch (InterruptedException e) {}
			}
             
		 	else if (xPos <= width) 			//once the text reaches the side
	  		{

		 		yPos = 0;				//set the position back to initial position
    		 		xPos = width / 2 - 20;
		 		nameCounter++;				//increment the name counter (move on to the next name)

				int[] colours = {0, 1 , 2, 3, 4, 5};	//create an array of 5 integers and call the array 'colours'
				Random rnd = new Random();		//create a next object called rnd of the type Random
				colourIndex = rnd.nextInt (colours.length);	//set the integer colourIndex equal to a random number from the array
	  		}
	  	}
     	}

	
	public void paint (Graphics gr)
	{//this is the actual drawing method of the program

		//switch statement is used to set the pen colour
		switch (colourIndex)						//a number called colourIndex is passed into the switch statement
		{
			case 0:							//if colourIndex is equal to zero
				gr.setColor(Color.red);				//set the pen colour to red
				break;						//break statement is needed so that all the cases after this one do not execute 
			case 1:						
				gr.setColor(Color.cyan);
				break;	
			case 2:
				gr.setColor(Color.blue);
				break;
			case 3:
				gr.setColor(Color.green);
				break;
			case 4:
				gr.setColor(Color.orange);
				break;
			case 5:
				gr.setColor(Color.black);
				break;
		}

		//this portion of the program is needed so that the names start from the beginning again

		if (nameCounter < list.length)					//until the names run out (nameCounter becomes equal to 5)
		{
			gr.drawString(list[nameCounter], xPos, yPos);		//print the object from the array which index is the same as the nameCounter. Print it at a position indicated by variables xPos and yPos
		}
		else 								//once nameCounter becomes equal to 5	
		{
			nameCounter = 0;					//set it back to zero
		}
	
	}
}