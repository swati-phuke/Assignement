
 Feature: Delete booking
 
 Background: 
 * url 'https://restful-booker.herokuapp.com'
 
 
 
 Scenario: 
 Given path '/booking/1'
 And header Authorization = 'Basic YWRtaW46cGFzc3dvcmQxMjM='
 And header Content-Type = 'application/json'
 And header Cookie  = 'token=abc123'
 When method DELETE
 Then status 201
 * print response
 