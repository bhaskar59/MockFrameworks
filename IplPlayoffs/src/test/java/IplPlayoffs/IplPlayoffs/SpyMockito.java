package IplPlayoffs.IplPlayoffs;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.spy;


import static org.mockito.Mockito.doThrow;

import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import junit.framework.Assert;

@RunWith(MockitoJUnitRunner.class)
public class SpyMockito {
	
	
	private Playoff playOff;
	
	private PlayOffLogic playOffLogic;
	
	
	//inject mock is nothing but creating an instance
	//if you are not using inject mocks then u need to create the instance in before method and mockk the mocking class
	
	
	@Before
	public void setup(){
		playOff=new Playoff();
		//playOffLogic =mock(PlayOffLogic.class);
		PlayOffLogicImpl playOffLogicImpl = new PlayOffLogicImpl();
		playOffLogic =spy(playOffLogicImpl);
		playOff.setPlayOffLogic(playOffLogic);
		
	}
	
	
	// normal mockito junit using when we are not passing any input we use mockito.anyString so it will pass
	@Test
	public void testWithInjectMock(){
		String team1= "Banglore";
		String team2= "Gujarat";
		String team3= "Hyderabad";
		String team4= "kolkatta";
		
		when(playOffLogic.eliminator1(Mockito.anyString(), Mockito.anyString())).thenReturn("Banglore");
		when(playOffLogic.eliminator2(Mockito.anyString(), Mockito.anyString())).thenReturn("Hyderabad");
		String result1=playOff.fetchEliminator1(team1, team2);
		String result2=playOff.fetchEliminator2(team3, team4);
		
		Assert.assertEquals("Hyderabad",playOff.fetchEliminator2(result1, result2));
		
		//create an inOrder verifier for a single mock
	      InOrder inOrder = inOrder(playOffLogic);

	      //following will make sure that eliminator1 is first called then eliminator2 is called.
	      inOrder.verify(playOffLogic).eliminator1(team1, team2);
	      inOrder.verify(playOffLogic).eliminator2(team3, team4);
	      
		
		//will use verify method here we are using any string so it passes as it expects same value
		//verify(playOffLogic).eliminator1(team1, team2);
		
		
		//now the concept of expectation will come into picture  it checks how many times the method is called 
		//we have a set of keywords times, never,atLeast(min),atLeastOnce,atmost(max)
		
		
		//verify(playOffLogic,times(1)).eliminator1(team1, team2);
		//verify(playOffLogic,never()).eliminator1(team1, team2);
		//verify(playOffLogic,atLeastOnce()).eliminator1(team1, team2);
		//verify(playOffLogic,atLeast(2)).eliminator1(team1, team2);
		//verify(playOffLogic,atMost(1)).eliminator1(team1, team2);
		
		
	}

	class PlayOffLogicImpl implements PlayOffLogic {

		public String eliminator1(String team1, String team2) {
			// TODO Auto-generated method stub
			return team1;
		}

		public String eliminator2(String team1, String team2) {
			// TODO Auto-generated method stub
			return team2;
		}
		
	}
}
