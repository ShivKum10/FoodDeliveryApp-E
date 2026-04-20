package edu.classproject.profile;

public interface ProfileService {
    CustomerProfile upsertProfile(String userId, String phoneNumber);

    CustomerProfile addAddress(String userId, Address address);

    CustomerProfile getProfile(String userId);
}
