package com.spidersolitaire.common;

import java.util.ArrayList;

import com.spidersolitaire.database.CardDetailsDatabase;

public class StackLocation {
	
	private static int baseStackYlocation=237;
	private static int unTurnedCardSize = 3;
	private static int turnedCardSize = 20;
	
	public static int stackYLocation(Stacks stack)
	{
		int location =0;
		
		ArrayList<Integer> cardList = new ArrayList<Integer>();
		cardList = CardDetailsDatabase.getCardListFromStack(stack);
		
		location = baseStackYlocation+(unTurnedCardSize * unTurnedCardCount(cardList)) + (turnedCardSize* turnedCardCount(cardList));		
		
		return location;
	}

	public static int stackXLocation(Stacks stack)
	{
		int xLocation =0;
		switch(stack)
		{
			case Stack1:
				xLocation =340;
				break;
			case Stack2:
				xLocation =417;
				break;
			case Stack3:
				xLocation =494;
				break;
			case Stack4:
				xLocation =572;
				break;
			case Stack5:
				xLocation =649;
				break;
			case Stack6:
				xLocation =726;
				break;
			case Stack7:
				xLocation =805;
				break;
			case Stack8:
				xLocation =881;
				break;
			case Extra_Stack:
				xLocation =0;
				break;
			default:
				xLocation =0;
				break;
		}
		
		return xLocation;
	}
	
	private static int unTurnedCardCount(ArrayList<Integer> cardList)
	{
		int cardCount =0;
		
		for(int i :cardList)
		{
			if	(i ==0)
			{
				cardCount++;
			}
		}		
		return cardCount;		
	}	
	
	private static int turnedCardCount(ArrayList<Integer> cardList)
	{
		int cardCount =0;
		
		for(int i :cardList)
		{
			if	(i !=0)
			{
				cardCount++;
			}
		}
		
		return cardCount;		
	}

}
