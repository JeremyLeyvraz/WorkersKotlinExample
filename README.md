# WorkersKotlinExample

Simple project with two modules: app and libraryExample.
The goal of this project is to demonstrate how workers can operate with Hilt.

## App
Simple application that use Compose, Hilt and Workers.
This application has two buttons: one to execute an one-time worker, the other one to execute a periodic worker.
The periodic worker is executed every 15 minutes.
When the worker is executed, the current time is insert in the database.
A flow is collected to display the database content in a list on the screen.

## libraryExample
Simple android module with 5 classes:
- AppDatabase: application database for Room.
- Date: entity to describe a date.
- DateDao: data access object to define operation on the date.
- Module: Hilt module to use the dao in the application project.
- MyWorker: Worker used to insert the current date in the database.
