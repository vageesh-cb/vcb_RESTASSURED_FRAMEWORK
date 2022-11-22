package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException
	{
		StepDefination sd=new StepDefination();
		if(StepDefination.placeid==null) {
		sd.add_place_payload("Renu", "US", "Pencelvania");
		sd.user_calls_with_http_request("addPlaceAPI", "Post");
		sd.verify_placeadded_maps_to_using("Renu", "getPlaceAPI");
		}
		
	}

}
