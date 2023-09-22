# Done-list
## Project Overview
"Done-list" is a web-based application designed to allow users to record tasks they have completed and reflect on how they feel after completing these tasks. The project serves as a unique twist on the traditional to-do list, emphasizing retrospective reflection and personal growth.

## Origin and Licensing
This project originates from the [play-scala-anorm-example](https://github.com/playframework/play-samples/tree/2.9.x/play-scala-anorm-example) template and has since undergone various modifications to suit the "Done-list" domain. Both the original template and the "Done-list" project are licensed under the CC0-1.0 license.

## Modifications Made
### Domain-specific Naming
Names have been changed to align more with the "Done-list" domain. This includes class names, table names, and routes.
### Database Structure
New positions were added to the database:

- **Category** 
Classifications for tasks, including Cleaning, Cooking, Shopping, Programming, Working, etc.

- **Term**
  specifies the significance and the reflection period of a task, such as Daily, Weekly, Monthly, and Annual.
### Graphical User Interface
The GUI has been redesigned to reflect the unique nature and requirements of the "Done-list" domain.
### Example Data
New sample data has been added to the database to provide a better representation of potential real-world use cases.
### Testing
Test cases have been modified to be compatible with the new changes made to the software.
### Database Adaptation
Originally configured for an in-memory H2 database, the system has now been adapted for an embedded H2 database.
### Production Setup on Windows
While setting up the application for local production on Windows, a couple of issues were encountered and resolved:
A secret key was required, as the default one wasn't suitable.
The application's configuration file needed a specific path to the server due to the absence of a root server path.
Getting Started

*This documentation was generated with the assistance of OpenAI's ChatGPT.*