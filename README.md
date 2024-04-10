# HR Management System

The HR Management System is a comprehensive software solution designed to streamline human resources processes within organizations. It offers a range of functionalities to manage employee data, salaries, attendance and leave management, training sessions, and more.

## Features

- **Employee Management:** Add, view, update, and delete employee information.
- **Salary Management:** Manage employee salaries, including CRUD operations.
- **Attendance Tracking:** Record and manage employee attendance.
- **Training Session Management:** Schedule and manage training sessions for employees.
- **RESTful API:** Provides a RESTful API for interacting with the HR system programmatically.
- **SOAP Web Services:** Supports SOAP web services for integrating with other systems.

## Technologies Used

- Java
- Jakarta EE (formerly Java EE)
- JAX-RS for RESTful services
- JAX-WS for SOAP services
- Maven for project management
- Git for version control

## Getting Started

To get started with the HR Management System, follow these steps:

1. Ensure you have Apache Tomcat installed. If not, you can download it from [here](http://tomcat.apache.org/) and follow the installation instructions provided.

2. Clone the repository:
  ```https://github.com/Omar-Abdelmutaleb/HR-Webservices-APIs.git```
4. Build the project using Maven
   `mvn clean install`
5. Deploy the built WAR file to your Tomcat server. You can do this by copying the WAR file from the target directory to the webapps directory in your Tomcat installation.

Start your Tomcat server:

```bash
cd /path/to/tomcat/bin
./startup.sh   # for Unix/Linux
./startup.bat  # for Windows
```

5.Access the application at `http://127.0.0.1:{Tomcat PORT}/rest-api/webapi/{required end point}` For REST-API.

or Access the application at `http://127.0.0.1:{Tomcat PORT}/soap-api/soap/{required end point}` For SOAP-API.

If you encounter any issues during the installation or deployment process, please refer to the Tomcat documentation or reach out for support.
`https://tomcat.apache.org/`

[REST-Documentation](https://documenter.getpostman.com/view/34095735/2sA35LWfMZ)

[SOAP-Documentation](https://documenter.getpostman.com/view/34095735/2sA3BgBvxD)
