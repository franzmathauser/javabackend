Masterthesis JavaBackend
========================

Logback/Glassfish Configuration
-------------------------------
1. copy these jars into `%GLASSFISH_HOME%/glassfish/lib/endorsed`
   * jul-to-slf4j.jar
   * slf4j-api.jar
   * logback-classic.jar
   * logback-core.jar
2. copy `src/main/setup/logback.xml` into `%GLASSFISH_HOME%/glassfish/domains/domain1/config/`
3. copy `src/main/setup/custom_logging.properties` into `%GLASSFISH_HOME%/glassfish/domains/domain1/config/`
4. add following lines into `domain.xml` (`<java-config>...</java-config>`)

        <jvm-options>-Dlog4j.configuration=file:///${com.sun.aas.instanceRoot}/config/custom_logging.properties</jvm-options>
        <jvm-options>-Dlogback.configurationFile=file:///${com.sun.aas.instanceRoot}/config/logback.xml</jvm-options>
       
5. restart server

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


