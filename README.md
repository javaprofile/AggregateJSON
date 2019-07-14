# AggregateJSON
Contains functionality for performing aggregation of data. Application reads JSON response from a URL, use ObjectMapper library create JSON objects and perform aggregations on data.

# What you’ll need
<ul>
  <li>JDK 8</li>
  <li>Maven</li>
</ul>

# How to use it...
Download and install JDK or JRE 8 and maven. Set the class path for both of the tools. Download source code from github, you get the zip version.
Unzip, and navigate to Aggregation folder, which is the root folder for pom.xml.

Run below commands to create and run executable jar file.

<ul>
  <li>mvn clean compile assembly:single, will create executable jar file in target folder</li>
  <li>java -jar target/Aggregation.jar, run the jar file create in target folder.</li>
</ul>

Uploaded  jar file in target folder, run from local for comfort. so you could run last command. For this, you just need to have java 8 run time environment, and class path set for the same.
