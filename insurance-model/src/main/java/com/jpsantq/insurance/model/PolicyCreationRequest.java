package com.jpsantq.insurance.model;

public record PolicyCreationRequest(
        String policyId,
        String policyName) {
}
