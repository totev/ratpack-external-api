import groovyx.net.http.HttpBuilder
import ratpack.exec.Blocking
import static ratpack.groovy.Groovy.ratpack
import ratpack.http.client.HttpClient
import ratpack.http.client.StreamedResponse

import static ratpack.jackson.Jackson.json

ratpack {
    handlers {
        prefix('api', {
            get('starwars', {
                // Forward request to target
                request.body.flatMap { requestBody ->
                    HttpClient httpClient = context.get(HttpClient)
                    httpClient.requestStream(new URI('https://swapi.co/api/people/1/')) { spec ->
                        spec.method(request.method)
                                .headers { headers ->
                            // copy original request headers
                            //headers.copy(request.headers)

                        }.body {
                            it.buffer requestBody.buffer
                        }
                    }
                }.then { StreamedResponse responseStream ->
                    responseStream.forwardTo(response)
                }
            })
            get('simpsons', {

                Blocking.get {
                    HttpBuilder.configure {
                        request.raw = 'https://thesimpsonsquoteapi.glitch.me/quotes'
                    }.get()

                }.then {
                    render json(it)
                }

            })
        })
    }
}