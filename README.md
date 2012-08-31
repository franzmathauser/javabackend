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

1. **Receive Places (GOOGLE API - format)**

        curl -k -i -H "Accept: application/json" --basic -u franz:123 -X GET "https://localhost:8181/JavaBackend/rest/secure/places?location=48.13661,11.57709"

2. **Receive Transactions**

   2.1 Successful:

        curl -k -i -H "Accept: application/json" --basic -u franz:123 -X GET "https://127.0.0.1:8181/JavaBackend/rest/secure/bankaccount/1/transactions"
    
   2.2 Error:

        curl -k -i -H "Accept: application/json" --basic -u franz:123 -X GET "https://127.0.0.1:8181/JavaBackend/rest/secure/bankaccount/2/transactions"

