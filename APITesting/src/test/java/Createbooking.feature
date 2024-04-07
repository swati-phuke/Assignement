Feature: Create a booking
Background:
* url 'https://restful-booker.herokuapp.com'

* def requestPayload = 
"""
{
    "firstname" : "Pragati",
    "lastname" : "Bankar",
    "totalprice" : 112,
    "depositpaid" : true,
    "bookingdates" : {
        "checkin" : "2018-01-01",
        "checkout" : "2019-01-01"
    },
    "additionalneeds" : "Breakfast"
}
"""

Scenario: To create a new booking in the API
Given path '/booking'
And request requestPayload
And header Content-Type = 'application/json'
And header Accept = 'application/json'
When method POST
Then status 200
* print response


