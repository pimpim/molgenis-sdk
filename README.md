# MOLGENIS software development kit

MOLGENIS is an collaborative open source project on a mission to generate great software infrastructure for life science research. Each app in the MOLGENIS family comes with rich data management interface and plug-in integration of analysis tools in R, Java and web services.

The procedure below tells you how to 
1. checkout the molgenis project AND this sdk 
2. install Eclipse
3. compile the molgenis 'core' (only needed once)
4. create and customize your own molgenis using the molgenis-sdk project as template

If you have experience with Maven and Java this should be quite familiar.

## 1. clone the molgenis repo and sdk

Go to the directory where you keep your git repositories, e.g.

	cd ~/git

Create a new workspace directory, e.g.

	mkdir molgenis-workspace
	cd molgenis-workspace


Clone the molgenis repo and sdk into this directory

	git clone https://github.com/<yourname>/molgenis.git
	git clone https://github.com/<yourname>/molgenis-sdk.git


Now you have a folder 'molgenis' and 'molgenis-sdk' in your workspace directory.

## 2. install and configure eclipse == 
(tested with latest eclipse download, J2EE Juno 4.2 SR1, Mac OSX 64bit)

MOLGENIS is created with help of Maven and Freemarker. You need a few eclipse plugins to work with those.

start eclipse:

	~/Software/eclipse-juno-4.2/eclipse


When asked chose (new) workspace directory. I choose to simply same directory ~/git/molgenis-workspace as before

Now install the plugins by choosing {{{Help -> Eclipse marketplace}}}. 
Add the following (you can restart Eclipse when done):

* maven integration for eclipse
* Apt M2E connector
* JBoss Tools (ONLY SELECT THE 'FreeMarker IDE feature' later in the wizard!)
* testng

Now you have configure Eclipse. Restart eclipse.

## 3. import the molgenis project into eclipse

Start Eclipse, select your workspace if asked.

Click: File -> Import ... -> Existing Maven Projects 

Set root directory to your git checkout folder. E.g. ~/git/molgenis-workspace
(this means you can still see the 'molgenis' folder).

Eclipse discovers all molgenis modules. Only select:

* molgenis-core
* molgenis-core-ui
* molgenis-sdk

Click next/okay; eclipse will now import the modules. Also Eclipse will automatically install maven connector plugins when needed (restart follows)

## 4. compile the molgenis code

If still open, close the 'Welcome' screen

Eclipse will automatically build and download jars

Right mouse 'molgenis-core' -> Run as -> Maven install
Right mouse 'molgenis-core-ui' -> Run as -> Maven install

After generation eclipse will compile automagically

## 4. generate the molgenis-sdk for the first time

create mysql database.

Assumed is that you installed mysql
Log in via terminal using your root credentials

	mysql -u root -p

Give create a database with permissions to molgenis user

	create database molgenis;
	grant all privileges on molgenis.* to molgenis@localhost identified by 'molgenis';
	flush privileges;

(We use JPA so database schema will be updated automatically. Sometimes you may need to 'drop database molgenis' and then 'create databse molgenis'


## 6. run your app (molgenis-sdk project)

Right click 'molgenis-sdk' -> Run as ... -> Maven build ...

In the 'goals' box type in 'jetty:start'

Choose Run. Now jetty will be started.

Open your browser at http://localhost:8080/

## 7. start customizing

You can edit the data model and user interface model in

	src/main/resources/molgenis_db.xml
	src/main/resources/molgenis_ui.xml
	
You can edit the code in 
	src/main/java
	
You can change database settings in
	src/main/resources/molgenis.properties