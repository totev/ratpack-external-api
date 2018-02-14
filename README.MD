Ratpack-External-API Showcase
---

This is a basic project which shows how a ratpack based server may relay http get calls to an external REST-API.


To use simply run gradle:
 `gradle run`
 
The server runs and listens on port 5050 and currently has 2 registered endpoints
 - /api/simpsons
 - /api/starwars
 
The first one uses the core http client from *io.github.http-builder-ng* and the other one *ratpack.http.client*.