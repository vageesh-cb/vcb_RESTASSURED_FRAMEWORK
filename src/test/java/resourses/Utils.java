package resourses;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {

	static RequestSpecification req;
	public RequestSpecification requestSpecification() throws IOException {
		
	    if(req==null) {
		PrintStream log=new PrintStream(new FileOutputStream("logging.txt"));
		req=new RequestSpecBuilder().setBaseUri(getGlobalProperty("baseUri")).addQueryParam("key", "qaclick123").
				addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log)).setContentType(ContentType.JSON).build();
		return req;
	    }
	    return req;
	}

	
	public static String getGlobalProperty(String key) throws IOException {
		
		Properties property=new Properties();
		FileInputStream fis=new FileInputStream("C:\\VCBWORKSPACE\\RESTASSURED_FRAMEWORK\\src\\test\\java\\resourses\\globalProperties.properties");
		property.load(fis);
		return property.getProperty(key);
		
	}
	
	public static String getJsonPath(Response response,String key) {
		
		String res=response.asString();
		JsonPath js=new JsonPath(res);
		return js.get(key).toString();
	}
	
	public String getDeletePlacePayload(String placeid) {
		
		return "{\r\n"
				+ "    \"place_id\":\""+placeid+"\"\r\n"
				+ "}\r\n"
				+ "";
	}
}
