Masterthesis JavaBackend
========================

Application Configuration
-------------------------
* cp src/main/resources/backend.template.properties ${user.home}/backend.properties
* Insert your individual Settings in ${user.home}/backend.properties
* Example Path ${user.home}: "C:\Users\MATHAF"

If you prefer to store your property-file somewhere else, you can configure a 
different path at: src/main/resources/backend_config.xml

REST-Calls: 
-----------
* Login
`curl -i -H "Accept: application/json" -H "Content-Type: application/x-www-form-urlencoded" -X POST -d "username=franz&password=123" http://localhost:8080/JavaBackend/rest/auth/login`

* Logout
`curl -i -H "Accept: application/json" -X GET http://localhost:8080/JavaBackend/rest/auth/logout`

* Receive Secured Places
`curl -i -H "Accept: application/json" --basic -u franz:123 -X GET "http://127.0.0.1:8080/JavaBackend/rest/secure/places?location=48.13661,11.57709"`
