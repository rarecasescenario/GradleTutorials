
#Logging with log4j2
```
https://logging.apache.org/log4j/2.x/manual/configuration.html
https://stackoverflow.com/questions/19641962/how-to-use-a-properties-file-in-eclipse-java-dynamic-web-project
```


http://localhost:9080/GradleWebLoggerWar/status

#Git
```
How to generate ssh key
http://linuxproblem.org/art_9.html
```

<feature>appSecurity-3.0</feature>





<basicRegistry id="basic" realm="BasicRealm">
	<user name="LOGUSER" password="welcome123"/>
</basicRegistry>

<webApplication id="restServicesWar" location="restServices.war" name="restServicesWar">
	<application-bnd>
		<security-role name="restClient">
			<user name="LOGUSER"/>
		</security-role>
	</applicaiton-bnd>
</webApplication>