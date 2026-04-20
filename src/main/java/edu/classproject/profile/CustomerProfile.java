package edu.classproject.profile;

import java.util.List;

public record CustomerProfile(String userId, String phoneNumber, List<Address> addresses) {
}
