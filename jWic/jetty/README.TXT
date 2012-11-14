This directory contains all required file to run the jWic default webapp in 
the jetty webserver. Jetty (http://www.mortbay.org/) is a very lightweight
100% pure java http server. To run the app, manually create a run configuration
with the following properties:

	Name: <your favorit name>
	Project: jWic
	Main class: org.mortbay.start.Main
	Programm arguments: jetty/jetty.xml
	
	Under the classpatch tab, manualy add the following jars that are in this directory:
	- jetty-6.0.1.jar
	- jetty-util-6.0.1.jar
	- start.jar
	
Now you can simply run the server and the default webapp.