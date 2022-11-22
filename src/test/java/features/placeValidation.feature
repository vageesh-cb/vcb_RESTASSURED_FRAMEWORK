Feature: Validate Place API's

@AddPlace @Regression
Scenario Outline: To verify Place is succesfully added by AddPlace API
Given AddPlace Payload "<name>" "<language>" "<address>"
When user calls "addPlaceAPI" with http "Post" request
Then API call got success with status code "200"
And "status" in response code is "OK"
And "scope" in response code is "APP"
And verify placeadded maps to "<name>" using "getPlaceAPI"

Examples:

    |name|language|address|
    |vcb |Hindi   |CTA    |
 #  |rcb |English |DVG    |	
 
@DeletePlace @Regression
Scenario: To verify Delete Place fictionality is working 
 Given DeletePlace payload
When user calls "deletePlaceAPI" with http "Post" request
Then API call got success with status code "200"
And "status" in response code is "OK"
 

