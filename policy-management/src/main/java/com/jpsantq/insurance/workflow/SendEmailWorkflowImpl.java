package com.jpsantq.insurance.workflow;

import com.jpsantq.insurance.activities.ActivityStubsProvider;
import com.jpsantq.insurance.activities.SendEmailActivities;
import com.jpsantq.insurance.model.PolicyCreationRequest;
import io.quarkiverse.temporal.TemporalWorkflow;
import io.temporal.failure.TemporalFailure;
import io.temporal.workflow.Saga;
import lombok.extern.jbosslog.JBossLog;

@JBossLog
@TemporalWorkflow(workers = "named-worker")
public class SendEmailWorkflowImpl implements SendEmailWorkflow {

    private final SendEmailActivities activities = ActivityStubsProvider.getSendEmailActivities();

    @Override
    public void run(PolicyCreationRequest data) {

        log.info("Workflow started for policy: " + data.policyId());

        // Initialize the saga for potential compensations
        Saga saga = new Saga(new Saga.Options.Builder().build());

        try {
            activities.sendEmail(data);
            saga.addCompensation(() -> activities.compensateEmail(data));

        } catch (TemporalFailure e) {
            saga.compensate();
            throw e;
        }
    }

    @Override
    public PolicyCreationRequest details() {
        return null;
    }
}
