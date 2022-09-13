## Micronaut 3.6.3 Documentation

- [User Guide](https://docs.micronaut.io/3.6.3/guide/index.html)
- [API Reference](https://docs.micronaut.io/3.6.3/api/index.html)
- [Configuration Reference](https://docs.micronaut.io/3.6.3/guide/configurationreference.html)
- [Micronaut Guides](https://guides.micronaut.io/index.html)
---

- [Shadow Gradle Plugin](https://plugins.gradle.org/plugin/com.github.johnrengelman.shadow)
## Feature reactor documentation

- [Micronaut Reactor documentation](https://micronaut-projects.github.io/micronaut-reactor/snapshot/guide/index.html)


## Feature http-client documentation

- [Micronaut HTTP Client documentation](https://docs.micronaut.io/latest/guide/index.html#httpClient)

## Feature jdbc-tomcat documentation

- [Micronaut Tomcat JDBC Connection Pool documentation](https://micronaut-projects.github.io/micronaut-sql/latest/guide/index.html#jdbc)

## Docker instructions
- `docker build -t bakeoff-mn .`
- `docker run --name bakeoff-mn -p 80:8080 -e JDBC_URL=jdbc:postgresql://{DB_ADDRESS}:5432/artists bakeoff-mn`



