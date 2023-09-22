# Done-list
## Project Overview
"Done-list" is a web-based application designed to allow users to record tasks they have completed and reflect on how they feel after completing these tasks. The project serves as a unique twist on the traditional to-do list, emphasizing retrospective reflection and personal growth.

## Origin and Licensing
This project originates from the [play-scala-anorm-example](https://github.com/playframework/play-samples/tree/2.9.x/play-scala-anorm-example) template and has since undergone various modifications to suit the "Done-list" domain. Both the original template and the "Done-list" project are licensed under the CC0-1.0 license.

## Modifications Made
### Domain-specific Naming
Names have been changed to align more with the "Done-list" domain. This includes class names, table names, and routes. One of the prominent changes in the code was the renaming of "Computer" to "CompletedTask", aligning better with the project's theme.
### Database Structure
New positions were added to the database:

- **Category** 
Classifications for tasks, including Cleaning, Cooking, Shopping, Programming, Working, etc.

- **Term**
  specifies the significance and the reflection period of a task, such as Daily, Weekly, Monthly, and Annual.
### Graphical User Interface
The GUI has been redesigned to reflect the unique nature and requirements of the "Done-list" domain.

![GUI](/doc/img/gui.png)

### Example Data
New sample data has been added to the database to provide a better representation of potential real-world use cases.
### Testing
Test cases have been modified to be compatible with the new changes made to the software.
### Database Adaptation
Originally configured for an in-memory H2 database, the system has now been adapted for an embedded H2 database. 
The transition from an in-memory to an embedded database primarily involved updating the configuration settings. The following changes were made:

```
db.default.url="jdbc:h2:~/done-list"
db.default.username=sa
db.default.password=""
```

These configurations direct the application to use the local embedded H2 database named "done-list" with the default credentials.

### Production Setup:
The `sbt dist` command was executed to produce a distribution for production. When deploying and running this distribution, it is necessary to add a secret key to the application's configuration to ensure security and functionality. An example of a secret key would be:

```
play.http.secret.key="ad31779d4ee49d5ad5162bf1429c32e2e9933f3b"
```

It's crucial to ensure that the secret key is kept confidential to maintain the security of the application.

*This documentation was generated with the assistance of OpenAI's ChatGPT.*