package com.greatminds.ayni.iam.domain.model.commands;

public record SignInCommand(
        String username,
        String password
) {
}
