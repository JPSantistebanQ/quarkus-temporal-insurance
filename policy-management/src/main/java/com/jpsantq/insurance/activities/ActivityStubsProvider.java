package com.jpsantq.insurance.activities;

import io.temporal.activity.ActivityCancellationType;
import io.temporal.activity.ActivityOptions;
import io.temporal.common.RetryOptions;
import io.temporal.workflow.Workflow;
import lombok.experimental.UtilityClass;

import java.time.Duration;

@UtilityClass
public class ActivityStubsProvider {

    // Activity options for demo purposes, limiting retry time
    private final static ActivityOptions options = ActivityOptions.newBuilder()
            .setStartToCloseTimeout(Duration.ofSeconds(30))
            .setRetryOptions(RetryOptions.newBuilder()
                    .setDoNotRetry(
                            NullPointerException.class.getName(),
                            IllegalArgumentException.class.getName())
                    .setInitialInterval(Duration.ofSeconds(1))
                    .setMaximumInterval(Duration.ofSeconds(100))
                    .setBackoffCoefficient(2)
                    .setMaximumAttempts(500)
                    .build())
            .build();

    public static SendEmailActivities getSendEmailActivities() {
        ActivityOptions newOptions = ActivityOptions.newBuilder(options)
                .setTaskQueue("named-worker")
                .setCancellationType(ActivityCancellationType.WAIT_CANCELLATION_COMPLETED)
                .build();

        return Workflow.newActivityStub(
                SendEmailActivities.class,
                newOptions);
    }
}
