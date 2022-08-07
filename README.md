
#Setting git localy
```
git config --global user.email "rare.case.scenario@gmail.com"
git config --global user.name "rarecasescenario"

What if you set the git config info just for the current repository?

  git config user.email "you@example.com"
  git config user.name "Your Name"
```

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