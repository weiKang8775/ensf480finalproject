# ENSF 480 Final Project

## Usage

First the files must have been downloaded and unzipped and set up in any IDE. Once this is done, the user must 
edit the IDBCredentials file located in the model.controller package in order to change the DB_PASSWORD and enter 
their local mysql password to connect to MySQL.

```
final String DB_PASSWORD = "your-password-here";
```

Once this is completed the user can open the create-database.sql file in MySQL Workbench, and connect to a local
instance to execute the create-database.sql file. If the script executes with no errors, the local database is set up.

Following this, the user can begin running the program by exporting the project as a runnable .JAR file through their IDE.
Then, locate the .JAR file and double click on it to run the application.

If all goes well, the application will run, and the credentials for each user is listed below if you wish to login

		email: user1@example.com password: user1
		email: user2@example.com password: user2
		... 
		email: user5@example.com password: user5

The user will be able to view and search movies and their showtime and available seats. They will also be able to
add tickets to their shopping cart and checkout.

## Info
* Thomas Kahessay 		thomas.kahessay@ucalgary.ca 
* Arpith Daniel Indukuri	arpith.indukuri@ucalgary.ca
* Wei Kang			wei.kang@ucalgary.ca
* Ryan Ittyipe			ryan.ittyipe1@ucalgary.ca
