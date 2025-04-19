mvn clean test -DsuiteXmlFile=testng.xml

 What This Does:
 
mvn clean test: cleans and runs tests.

-DsuiteXmlFile=testng.xml: tells Maven to use your custom testng.xml suite.

Make sure your testng.xml is placed at the root of your project (same level as pom.xml), or update the path accordingly (e.g., -DsuiteXmlFile=src/test/resources/testng.xml).

If you want to run using IDE :

In IntelliJ or any other IDE:

Right-click on testng.xml

Click Run 'testng.xml'

This does the same as above via the GUI

or Right click on the CarValuationTest.java and select Run Java
