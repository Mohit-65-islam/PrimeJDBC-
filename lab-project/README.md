# Lab Practice #1: JDBC-Servlet Recalling (ICT 3108)

**Full Name:** Mohitul Islam
**Student ID:** YOUR STUDENT ID
**University Email:** YOUR_ID@mbstu.ac.bd
**GitHub Repository:** https://github.com/YOUR_USERNAME/YOUR_REPO

## Project structure

```
lab-project/
├── README.md
├── sql/setup.sql                          # Task 2: database + table
├── PrimeJDBC/src/PrimeJDBC.java           # Task 2: JDBC prime insertion
└── Servletdemo/                           # Tasks 3-4: Maven web app (Tomcat 10.1)
    ├── pom.xml
    └── src/main/
        ├── java/com/example/servletdemo/PrimeServlet.java
        └── webapp/index.jsp
```

## Task 1: IntelliJ IDEA Ultimate
IntelliJ IDEA Ultimate installed and activated with the JetBrains educational licence (valid through 04/11/2027).


## Task 2: JDBC prime insertion
1. Start MySQL in XAMPP, then run `sql/setup.sql` in phpMyAdmin.
2. Add `mysql-connector-j-26.10.0.jar` to the project build path.
3. Run `PrimeJDBC`, enter `n`.

Console output:
```
Enter n: 3
The 3-th prime number is: 5
Connected to database successfully.
Record inserted.
ID | n | nth_prime
1 | 29 | 109
2 | 3 | 5
```



## Task 3: Apache Tomcat 10.1
1. Downloaded the 64-bit Windows zip from https://tomcat.apache.org/download-10.cgi and extracted it.
2. IntelliJ: *Run → Edit Configurations → + → Tomcat Server → Local*, set the Tomcat home folder.
3. Deployment tab: add artifact `Servletdemo:war exploded`, application context `/Servletdemo`.
4. Server tab: HTTP port `8080`. Started the server and checked the log for `Server startup in [...] milliseconds`.

## Task 4: Primality test servlet
`PrimeServlet` is mapped to `/prime` with `@WebServlet`.

| Input | URL | Result |
|-------|-----|--------|
| 17 | `http://localhost:8080/Servletdemo/prime?number=17` | 17 is a PRIME number |
| 20 | `http://localhost:8080/Servletdemo/prime?number=20` | 20 is NOT a prime number |
| 1 | `http://localhost:8080/Servletdemo/prime?number=1` | 1 is NOT a prime number |
| abc | `http://localhost:8080/Servletdemo/prime?number=abc` | Please enter a valid integer |



## Final checklist
- [x] IntelliJ IDEA Ultimate installed and activated
- [x] JDBC connection established and prime number inserted
- [x] Apache Tomcat configured and running
- [x] Java Servlet tested successfully in a browser
- [x] GitHub Markdown file completed with codes and screenshots
