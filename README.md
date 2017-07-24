# phonebookapp

This is a simple phone book application with search capabilities.

## Technologies

- Backend: Java 8, Spring Boot, Spring MVC, Spring Data
- Frontend: AngularJs, Bootstrap, Javascript, HTML5, CSS3
- Storage: embedded, inmemory H2 database (packaged in the backend)
    
## Workflow

After opening the application, you can see the contacts with picture, name, phone number prestored in the phonebook. You can search for contacts by name or phone number. You can also call their phone number easily with the Call button.  

## Development

Before you can build this project, you must install and configure the following dependencies on your machine:

1. [Node.js][]: Node is for running a development web server and building the project. At least version 7.6 is needed.
2. [JDK][]: The Java SDK. At least version 1.8 is needed.
3. [Git][]: Git client.

After installing Node, you should be able to run the following command to install development tools, though npm start will run npm install as well:

    npm install

Run the following commands in two separate terminals to run the application:

    ./mvnw
    npm start

[Bower][] is used to manage CSS and JavaScript dependencies used in this application.

After running the two commands, according to the current configuration, the frontend is available on [http://localhost:8000](http://localhost:8000), the backend is available on [http://localhost:8080](http://localhost:8080).


## Building a runnable war

Run the following command to package the application in a runnable war file:

    ./mvnw -Pwar clean package

Run the following command to run the the application:

    java -jar target/phonebookapp-<current version>.war
    
i.e.:

    java -jar target/phonebookapp-0.0.1-SNAPSHOT.war

Then navigate to [http://localhost:8080](http://localhost:8080) in your browser.

## Testing

To launch the application's tests, run:

    ./mvnw clean test


[Node.js]: https://nodejs.org/
[JDK]: http://www.oracle.com/technetwork/java/javase/downloads/index.html
[Bower]: http://bower.io/
[Git]: https://git-scm.com/downloads
