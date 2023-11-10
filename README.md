# MVC JEE

## Requirements
- JDK or JRE 11
- Tomcat 10.1
- MySQL (optional)

## Installation

### Standard Installation

If you prefer to install the project directly on your desktop, follow these steps:

1. Install MySQL on your host system if not already installed.

2. Create a MySQL user with the username "exxea" and password "exxea" and grant it all privileges on a database named "api."

3. Run the SQL script located at `db/init.sql` within the "api" database to initialize the necessary database schema.

4. [Clone this repository](https://github.com/jguillaumesio/exxea_jee_tp.git).

5. Now run server/app which is the JEE application within a Tomcat 10.1 server

### Docker installation 
```
git clone https://github.com/jguillaumesio/exxea_jee_tp.git
```
```
cd exxea_jee_tp
```
```
docker compose up
```
Now just go to http://127.0.0.1:8080/demo_war/book
