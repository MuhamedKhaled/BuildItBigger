package com.os.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.os.joker.Joker;

import javax.inject.Named;

/** An endpoint class we are exposing */
@Api(
        name = "jokerApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.builditbigger.os.com",
                ownerName = "backend.builditbigger.os.com",
                packagePath = ""
        )
)
public class JokerApi {
    private Joker joker = new Joker();

    @ApiMethod(name = "tellAJoke")
    public Joke tellAJoke() {
        Joke response = new Joke();
        response.setData(joker.getJoke());
        return response;
    }
}
