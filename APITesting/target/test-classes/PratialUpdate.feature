Feature: PATCH Request
Background: 
* url 'https://restful-booker.herokuapp.com'

Scenario: Update Partial Booking
Given path  '/booking/1'
And header Authorization = 'Basic YWRtaW46cGFzc3dvcmQxMjM='
And header Content-Type = 'application/json'
And header Accept = 'application/json'
And request {"firstname" : "Swati" }
When method PATCH
Then status 200
* print response