package resourses;

import java.util.ArrayList;
import java.util.List;

import POJO.Add_PlacePojoClass;
import POJO.Location;

public class TestDataBuild {

	public  Add_PlacePojoClass addPlacePayload(String name, String language, String address) {
		Add_PlacePojoClass ap=new Add_PlacePojoClass();
		ap.setAccuracy("50");
		ap.setName(name);
		ap.setPhone_number("1234567890");
		ap.setAddress(address);
		ap.setWebsite("www.ryard@gmail.com");
		ap.setLanguage(language);
		
		List<String> type=new ArrayList<String>();
		type.add("shoe park");
		type.add("shop");
		
		ap.setTypes(type);
		
		Location loc=new Location();
		loc.setLat(-38.383494);
		loc.setLng(33.427362);
		
		ap.setLocation(loc);
		return ap;
		
	}
}
