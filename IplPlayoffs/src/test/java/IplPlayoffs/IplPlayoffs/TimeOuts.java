package IplPlayoffs.IplPlayoffs;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atMost;

import static org.mockito.Mockito.doThrow;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import junit.framework.Assert;

@RunWith(MockitoJUnitRunner.class)
public class TimeOuts {
	
	@InjectMocks
	Playoff playOff=new Playoff();
	
	@Mock
	PlayOffLogic playOffLogic;
	
	
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
		
		//will use verify method here we are using any string so it passes as it expects same value
		//verify(playOffLogic).eliminator1(team1, team2);
		
		
		//now the concept of expectation will come into picture  it checks how many times the method is called 
		//we have a set of keywords times, never,atLeast(min),atLeastOnce,atmost(max)
		
		
		//verify(playOffLogic,times(1)).eliminator1(team1, team2);
		//verify(playOffLogic,never()).eliminator1(team1, team2);
		//verify(playOffLogic,atLeastOnce()).eliminator1(team1, team2);
		//verify(playOffLogic,atLeast(2)).eliminator1(team1, team2);
		//verify(playOffLogic,atMost(1)).eliminator1(team1, team2);
		verify(playOffLogic,timeout(100)).eliminator1(team1, team2);
		
		
	}
	
	
	//In this test wee are hardcoding values 
	@Test
	public void testWithInjectMock1(){
		String team1= "Banglore";
		String team2= "Gujarat";
		String team3= "Hyderabad";
		String team4= "kolkatta";
		
		when(playOffLogic.eliminator1(team1, team2)).thenReturn(team1);
		when(playOffLogic.eliminator2(team3, team4)).thenReturn(team3);
		
		
		
		String result1=playOff.fetchEliminator1(team1, team2);
		String result2=playOff.fetchEliminator2(team3, team4);
		when(playOffLogic.eliminator2(result1, result2)).thenReturn(result2);
		
		Assert.assertEquals("Hyderabad",playOff.fetchEliminator2(result1, result2));
		
		//here the test fails because expected is team1,team2 but passed is team1,team3
		//verify(playOffLogic).eliminator1(team1, team3);
		
		
		//verify(playOffLogic,times(1)).eliminator1(team1, team2);
		//verify(playOffLogic,never()).eliminator1(team1, team2);
		//verify(playOffLogic,atLeastOnce()).eliminator1(team1, team2);
		//verify(playOffLogic,atLeast(2)).eliminator1(team1, team2);
		//verify(playOffLogic,atMost(1)).eliminator1(team1, team2);
		
	}


	
	
	//next comes the concept of exception we have a annotation called expected we can mention the type of exception
	//we can mock the expection and give the resut
	@Test(expected = RuntimeException.class)
	public void testException(){
		
		String team1= "Banglore";
		String team2= "Gujarat";
		String team3= "Hyderabad";
		String team4= "kolkatta";
		
		doThrow(new RuntimeException("Hyderabad")).when(playOffLogic.eliminator1(team1, team2));
		when(playOffLogic.eliminator2(team3, team4)).thenReturn(team3);
		
		
		
		String result1=playOff.fetchEliminator1(team1, team2);
		String result2=playOff.fetchEliminator2(team3, team4);
		when(playOffLogic.eliminator2(result1, result2)).thenReturn(result2);
		
		Assert.assertEquals("Hyderabad",playOff.fetchEliminator2(result1, result2));
		
	}


}
