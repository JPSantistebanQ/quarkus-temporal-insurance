package com.jpsantq.insurance.model;

public record PolicyCreationResponse(
        String policyId, String policyName, String status) {
}
