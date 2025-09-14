package com.jpsantq.insurance.activities;

import com.jpsantq.insurance.model.PolicyCreationRequest;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.jbosslog.JBossLog;

@JBossLog
@ApplicationScoped
//@TemporalActivity(workers = "named-worker")
public class SendEmailActivitiesImpl implements SendEmailActivities {

    @Override
    public String sendEmail(PolicyCreationRequest details) {
        log.info("Sending email for policy: " + details.policyId() + " - " + details.policyName());
        return "ENDED" + details.policyId();
    }

    @Override
    public void compensateEmail(PolicyCreationRequest details) {
        log.info("compensateEmail for policy: " + details.policyId());
    }
}
