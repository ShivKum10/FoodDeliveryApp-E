package edu.classproject.delivery;

import java.util.List;

public interface DeliveryPartnerService {
    DeliveryPartner register(String name);

    void setAvailability(String partnerId, boolean available);

    List<DeliveryPartner> getAvailablePartners();
}
