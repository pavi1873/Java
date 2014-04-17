package com.spidersolitaire.common;

import com.spidersolitaire.database.CardDetailsDatabase;

public class InitiateStacks {
	
	public static void setUp()
	{
		deleteAllOldData();
		setInitialCardData();		
	}
	
	private static void deleteAllOldData()
	{
		CardDetailsDatabase.deleteAllCardFromStack(Stacks.Stack1);
		CardDetailsDatabase.deleteAllCardFromStack(Stacks.Stack2);
		CardDetailsDatabase.deleteAllCardFromStack(Stacks.Stack3);
		CardDetailsDatabase.deleteAllCardFromStack(Stacks.Stack4);
		CardDetailsDatabase.deleteAllCardFromStack(Stacks.Stack5);
		CardDetailsDatabase.deleteAllCardFromStack(Stacks.Stack6);
		CardDetailsDatabase.deleteAllCardFromStack(Stacks.Stack7);
		CardDetailsDatabase.deleteAllCardFromStack(Stacks.Stack8);
		CardDetailsDatabase.deleteAllCardFromStack(Stacks.Extra_Stack);
	}
	
	private static void setInitialCardData()
	{
		for (int i=0 ;  i< 4; i++)
		{
			CardDetailsDatabase.insertCardToStack(Stacks.Stack1,Constants.DEFAULT_CARD_VALUE);
			CardDetailsDatabase.insertCardToStack(Stacks.Stack2,Constants.DEFAULT_CARD_VALUE);
			CardDetailsDatabase.insertCardToStack(Stacks.Stack3,Constants.DEFAULT_CARD_VALUE);
			CardDetailsDatabase.insertCardToStack(Stacks.Stack4,Constants.DEFAULT_CARD_VALUE);
			CardDetailsDatabase.insertCardToStack(Stacks.Stack5,Constants.DEFAULT_CARD_VALUE);
			CardDetailsDatabase.insertCardToStack(Stacks.Stack6,Constants.DEFAULT_CARD_VALUE);
		}
		
		for (int i=0 ;  i< 3; i++)
		{
			CardDetailsDatabase.insertCardToStack(Stacks.Stack7,Constants.DEFAULT_CARD_VALUE);
			CardDetailsDatabase.insertCardToStack(Stacks.Stack8,Constants.DEFAULT_CARD_VALUE);		
		}
		
	}
}
