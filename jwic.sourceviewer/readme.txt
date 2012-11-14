jWic Source Viewer

- Installation guide

	All you need for this application to work on your computer is a standalone server (e.g.: Apache Tomcat).
For Tomcat users, it's enough to unzip the sv.zip file into the TOMCAT_HOME/webapps directory. After you do
this, you will have a new folder called "sviewer". After restarting Tomcat, you can check your browser and enter the
following url (we assume that the port is 8080):
	
	http://localhost:8080/sviewer/sv
	
This should do it. :)

- Configuring your own "workspace"
	
	Inside the TOMCAT_HOME/webapps/sviewer folder, you have the WEB-INF folder. Inside this folder there is a 
file called "workspace.xml". That is the file that defines your workspace shown in the Source Viewer application.

	Define a Group (that will hold projects):
<group name="Samples">

	Under the "project" tag you can define a new project. You can select a name for your project a parent
(the Group that holds that project) and a commentFile.

<project name="myProject" parent="myGoup" commentFile="myCommentFile.html">
	
	Inside the project tag you can also define the other resources (source-folders, normal folders), using
an absolute or relative path.

e.g.:
This defines a source-folder
<source path="mySourceFolder_path"/>

	You can also set the boolean "scan" attribute (default set to "true"), and if you set it to "false", you must
specify manually the packages that will be available. This attribute can be used when you don't want to see all
the packages from the source-folder.
<source path="mySourceFolder_path" scan="false">

Define a source folder called "mySourceFolder"
<source name="mySourceFolder" path="mySourceFolder_path"/>

Define the packages inside that source-folder
<package>de.jwic.samples</package>

Define a regular folder called "myFolder"
<folder name="myFolder" path="myFolder_path"/>

We hope you enjoy this jWic application and we are waiting for your feedback, positive or negative. :)
http://www.jwic.de