package com.spidersolitaire.common;

import java.util.ArrayList;

import com.spidersolitaire.database.CardDetailsDatabase;

public class CardSort {
	
	
	public void start()
	{
		sortDataInStacks();
	}
	
	
	private static void sortDataInStacks()
	{
		sortDataInStacks(Stacks.Stack8);
		sortDataInStacks(Stacks.Stack7);
		sortDataInStacks(Stacks.Stack6);
		sortDataInStacks(Stacks.Stack5);
		sortDataInStacks(Stacks.Stack4);
		sortDataInStacks(Stacks.Stack3);
		sortDataInStacks(Stacks.Stack2);
		sortDataInStacks(Stacks.Stack1);		
	}
	
	private static void sortDataInStacks(Stacks stackFrom)
	{
		sortDataInColumns(stackFrom, Stacks.Stack8);
		sortDataInColumns(stackFrom, Stacks.Stack7);
		sortDataInColumns(stackFrom, Stacks.Stack6);
		sortDataInColumns(stackFrom, Stacks.Stack5);
		sortDataInColumns(stackFrom, Stacks.Stack4);
		sortDataInColumns(stackFrom, Stacks.Stack3);
		sortDataInColumns(stackFrom, Stacks.Stack2);
		sortDataInColumns(stackFrom, Stacks.Stack1);		
	}
	
	private static int sortDataInColumns(Stacks stackFrom, Stacks stackTo)
	{
		ArrayList<Integer> stackFromList =  new ArrayList<Integer>();
		ArrayList<Integer> stackToList = new ArrayList<Integer>();
		ArrayList<Integer> stackToBeCompared =  new ArrayList<Integer>();
		
		stackFromList= CardDetailsDatabase.getCardListFromStack(stackFrom);
		stackToList= CardDetailsDatabase.getCardListFromStack(stackTo);
		
		if (stackFrom == stackTo)
		{
			return -1;
		}
		// If there is no card in the stack or if the top most card in the stack is zero
		// Then we return
		if	(stackFromList.size()==0 || stackFromList.get(stackFromList.size()-1) ==0)
		{
			return -1;
		}
		
		if(stackFromList.size() == 1)
		{
			stackToBeCompared.add(stackFromList.get(0));	
		}
		
		if(stackFromList.size() >=2)
		{			
			for (int i=stackFromList.size(); i < 0; i--)
			{
				if	(stackFromList.get(i-1) == (stackFromList.get(i-2) -1))
				{
					stackToBeCompared.add(stackFromList.get(i-1));				
				}
				if(stackToBeCompared.size() >0 && stackFromList.get(i-1) != (stackFromList.get(i-2) -1))
				{
					stackToBeCompared.add(stackFromList.get(i-1));	
					break;
				}
				if(stackToBeCompared.size() == 0 && stackFromList.get(i-1) != (stackFromList.get(i-2) -1))
				{
					stackToBeCompared.add(stackFromList.get(i-1));	
					break;
				}
			}
		}
		
		
		
		return 0;
	}

}
