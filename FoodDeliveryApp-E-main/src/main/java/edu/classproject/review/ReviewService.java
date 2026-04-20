package edu.classproject.review;

import java.util.List;

public interface ReviewService {
    Review submit(Review review);

    List<Review> listByRestaurant(String restaurantId);
}
