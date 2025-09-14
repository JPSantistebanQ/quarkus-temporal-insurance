package com.jpsantq.insurance.activities;

import com.jpsantq.insurance.model.PolicyCreationRequest;
import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface SendEmailActivities {
    @ActivityMethod
    String sendEmail(PolicyCreationRequest details);

    @ActivityMethod
    void compensateEmail(PolicyCreationRequest details);
}