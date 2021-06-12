# ClickjackingTesting-SwDProject
for Windows
## Installation
1. Development Platform - Eclipse
    - [Download Eclipse](https://www.eclipse.org/downloads/)
2. Server - Apache Tomcat Server
    - [Download Apache Tomcat Server](https://tomcat.apache.org/download-90.cgi)
    
3. Clone the repository and import it to eclipse.
4. Configure file */WEB-INF/classes/config.properties*.

```
path_project = C:/Users/.../ClickjackingTest/
```
5. Run the server on the port 8080.

## Tips to avoid problems
1. Eclipse problem auto-refresh: To set auto-refresh, go to window > preferences > general > workspace and check the refresh using native hooks or polling and refresh on access check-boxes. 
2. Internet Explorer problem execution selenium: Open IE > settings > security > and check the activete protect mode for "Internet", "Local Internet", "Reliable Site", "Not Reliable Site".
3. Internet Explorer problem execution selenium: Open IE > Zoom > set it to 100%.

## Somethings wrong!!
If you find that something's wrong with this package, you can let me know by raising an issue on the GitHub issue tracker, or take it as a task and 🧑‍💻 resolve it 💪 --> create a PullRequest 🛠.
