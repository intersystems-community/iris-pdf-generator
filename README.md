# Plain text to PDF converter

This repository contains an example text file to PDF converter and instructions for how to use it from InterSystems IRIS.

## Installation

Installing TextToPDF requires that you have access to your IRIS server with sufficient access to work with gateways and classes.

### 1. Clone this repository

Clone this repository into a directoruy on your IRIS server:

```
git clone https://github.com/intersystems-community/iris-pdf-generator.git
```

### 2. Create a Java Gateway

In the management console, go to System Administration > Configuration > Connectivity > Object Gateways and choose "Create New Gateway"

```
Object Gateway For:  Java
Gateway Name: iris-pdf-generator
Port: 54545
Class Path: (directory where you cloned the repo)/target/iris-pdf-generator-0.1.0-jar-with-dependencies.jar
```

Leave all the others as default and click Save.

### 3. Load the class file

In the management console, make sure you are in the user namespace, go to System Explorer > Classes, and choose "Import".  Browse to the directory where you cloned this repo and choose "all files" from the file type dropdown.  Select the PdfGenerator.cls file.  Click "Next", then "Import".

## Usage

Now that everything is installed, you can use it from IRIS.  Open a console and do the following, replacing "<clone dir>" with the name of the directory you cloned this repository into:

```
set status = ##class(User.PdfGenerator).TextToPdf("<clone dir>\example.txt", "<clone dir>\example.pdf")
```

That will create `example.pdf` as a PDF version of `example.txt`.

## Build Instructions

This is a small maven project.  If you have maven installed then you should be able to build the sources via:

```
maven install
```
