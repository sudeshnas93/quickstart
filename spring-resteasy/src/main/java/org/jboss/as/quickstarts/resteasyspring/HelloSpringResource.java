/*
 * JBoss, Home of Professional Open Source
 * Copyright 2015, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.as.quickstarts.resteasyspring;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.MatrixParam;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Joshua Wilson
 *
 */
@Path("main")
public class HelloSpringResource {

    @Autowired
    GreetingBean greetingBean;

    /**
     * Create a default REST endpoint that directs the user to use the demonstration endpoints.
     *
     * @return html
     */
    @GET
    @Produces("text/html")
    public Response getDefault(@Context UriInfo uriInfo) {
        String baseURI = uriInfo.getBaseUri().toString();
        if (!baseURI.endsWith("/")) baseURI += '/';
        baseURI = baseURI + "main/";
        String msg = "Hello. <br> Please try <a href='"+baseURI+"hello?name=yourname'>spring-resteasy/main/hello?name=yourname</a>"
            + "<br> Or try <a href='"+baseURI+"basic'>spring-resteasy/main/basic</a>"
            + "<br> Or try <a href='"+baseURI+"queryParam?param=query'>spring-resteasy/main/queryParam?param=query</a>"
            + "<br> Or try <a href='"+baseURI+"matrixParam;param=matrix'>spring-resteasy/main/matrixParam;param=matrix</a>"
            + "<br> Or try <a href='"+baseURI+"uriParam/789'>spring-resteasy/main/uriParam/789</a>"
            + "<br> Or try <a href='"+baseURI+"locating/hello?name=yourname'>spring-resteasy/main/locating/hello?name=yourname</a>"
            + "<br> Or try <a href='"+baseURI+"locating/basic'>spring-resteasy/main/locating/basic</a>"
            + "<br> Or try <a href='"+baseURI+"locating/queryParam?param=query'>spring-resteasy/main/locating/queryParam?param=query</a>"
            + "<br> Or try <a href='"+baseURI+"locating/matrixParam;param=matrix'>spring-resteasy/main/locating/matrixParam;param=matrix</a>"
            + "<br> Or try <a href='"+baseURI+"locating/uriParam/789'>spring-resteasy/main/locating/uriParam/789</a>";
        System.out.println("getDefault()");
        return Response.ok(msg).build();
    }

    @GET
    @Path("hello")
    @Produces("text/plain")
    public Response sayHello(@QueryParam("name") String name) {
        String greetingMsg = greetingBean.greet(name);
        System.out.println("Sending greeing: " + greetingMsg);
        return Response.ok(greetingMsg).build();
    }

    @GET
    @Path("basic")
    @Produces("text/plain")
    public String getBasic() {
        System.out.println("getBasic()");
        return "basic";
    }

    @PUT
    @Path("basic")
    @Consumes("text/plain")
    public void putBasic(String body) {
        System.out.println(body);
    }

    @GET
    @Path("queryParam")
    @Produces("text/plain")
    public String getQueryParam(@QueryParam("param") String param) {
        return param;
    }

    @GET
    @Path("matrixParam")
    @Produces("text/plain")
    public String getMatrixParam(@MatrixParam("param") String param) {
        return param;
    }

    @GET
    @Path("uriParam/{param}")
    @Produces("text/plain")
    public int getUriParam(@PathParam("param") int param) {
        return param;
    }

}
