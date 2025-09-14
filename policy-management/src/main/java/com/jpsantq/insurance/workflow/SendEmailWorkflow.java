package com.jpsantq.insurance.workflow;

import com.jpsantq.insurance.model.PolicyCreationRequest;
import io.temporal.workflow.QueryMethod;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface SendEmailWorkflow {

    @WorkflowMethod
    public void run(PolicyCreationRequest data);

    @QueryMethod
    public PolicyCreationRequest details();
}
