# Startup
-----------
## Jenkins setup

Navigate to http://54.173.12.218:8080/jenkins/ (contact Revature CoE for login credentials).

Go to the Maven project named "Revinsure".

Select "Configure" on the left navbar, and ensure the following settings are correct:

### General
* Enable discard old builds
* Strategy: Log Rotation
* Days to keep build: Blank
* Max # of builds to keep: 1
* The following 7 checkboxes should be unchecked.

### Source Code Management
* Select Git
* Repository URL: https://github.com/Revature-Revinsure/Revinsure
* Credentials: none
* Branch Specifier (blank for 'any'): */main
* Repository browser: (Auto)
* No additional behaviors

### Build Triggers
* Check "Build whenever a SNAPSHOT dependency is built"
* Check "GitHub hook trigger for GITScm polling"
* All others should be unchecked

### Build Environment
* Check "Provide Node & npm /bin folder to PATH; all others in that group are unchecked.
* NodeJS installation: Node
* npmrc file: use system default
* Cache location: Default (~/.npm or %APP DATA%\npm-cache
* With Ant should be unchecked

### Pre Steps
In Execute shell command, write:
```
cd /root/.jenkins/workspace/RevInsure/RevinsureAngular
npm update
ng build
```
This will create the Angular project. It will save the files in a dist folder.

### Build
* Root POM: RevinsureSpring/pom.xml
* Goals and options: clean package

### Post Steps
* Select "Run only if build succeeds"

In Execute shell command, write:
```
#!/bin/bash
echo kill existing
pkill -f 'java -jar'
echo starting deploy
BUILD_ID=dontKillMe nohup java -jar /root/.jenkins/workspace/RevInsure/RevinsureSpring/target/Revinsure-0.0.1-SNAPSHOT.jar &
echo deploy finished
```
This will initially kill your application and then rebuild it so that it can't be shutdown by Jenkins (which it will by default)


### Build settings
* No changes

### Post-build Actions 
#### This will grab the Angular files from dist folder to the S3 bucket
#### We are using Port:8000 as per our applicationContext. Ensure you have access to the port you are deploying to, whichever one it may be. Contact Revature CoE for port access.
* S3 profile: S3 Jenkins Profile
* Source: \*\*/RevinsureAngular/dist/RevinsureAngular/*
* Exclude: Should be blank
* Destination bucket: revinsure
* Storage class: STANDARD
* Bucket RegionL us-east-1
* Only "Flatten directories" should be checked
* Publish Failure Result Constraint: FAILURE
* "Don't wait for completion of concurrent builds before publishing to S3" should be unchecked
* "Do Not Set Build Result on Failure" should be unchecked
* Build console log level: INFO
