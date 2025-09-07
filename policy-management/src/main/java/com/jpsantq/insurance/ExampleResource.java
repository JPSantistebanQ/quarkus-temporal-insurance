package com.jpsantq.insurance;

import com.jpsantq.insurance.model.PolicyCreationRequest;
import com.jpsantq.insurance.model.PolicyCreationResponse;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@ApplicationScoped
@Path("/policy")
public class ExampleResource {

    @GET
    @Path("hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from Quarkus REST";
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public PolicyCreationResponse createPolicy(PolicyCreationRequest request) {
        Log.info(request.toString());
        return new PolicyCreationResponse(request.policyId(), request.policyName(), "OK");
    }
}
