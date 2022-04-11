package com.thegreatapi.jaxrs.download.file.extension;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@ApplicationScoped
@Path("/extension/")
public class ExtensionResource {

    private static final String FILE_NAME = "/dummy.pdf";

    @GET
    @Path("hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello";
    }

    @GET
    @Path("download")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response download() throws IOException {
        try (InputStream file = Thread.currentThread().getContextClassLoader().getResourceAsStream(FILE_NAME)) {
            if (file == null) {
                throw new FileNotFoundException();
            } else {
                return Response.ok(file, MediaType.APPLICATION_OCTET_STREAM)
                               .header("Content-Disposition", "inline; filename=\"dummy.pdf\"")
                               .build();
            }
        }
    }
}
