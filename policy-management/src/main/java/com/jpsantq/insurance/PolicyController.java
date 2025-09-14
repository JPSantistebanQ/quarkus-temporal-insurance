package com.jpsantq.insurance;

import com.jpsantq.insurance.model.PolicyCreationRequest;
import com.jpsantq.insurance.model.PolicyCreationResponse;
import com.jpsantq.insurance.workflow.SendEmailWorkflow;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import lombok.extern.jbosslog.JBossLog;

@JBossLog
@ApplicationScoped
@Path("/policy")
public class PolicyController {

    @Inject
    WorkflowClient client;

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
        log.info(request.toString());
        startSubscription(request);
        return new PolicyCreationResponse(request.policyId(), request.policyName(), "OK");
    }

    public void startSubscription(PolicyCreationRequest data) {

        WorkflowOptions options = WorkflowOptions.newBuilder()
                .setWorkflowId(data.policyId())
                //.setTaskQueue("<default>")
                .setTaskQueue("named-worker")
                .build();

        SendEmailWorkflow workflow = client.newWorkflowStub(SendEmailWorkflow.class, options);
        WorkflowClient.start(workflow::run, data);

    }
}
