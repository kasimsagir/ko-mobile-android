package com.kinght.commerce.data.network.services;

import com.kinght.commerce.data.network.ServiceCallback;
import com.kinght.commerce.data.network.entities.Promotion.Promotions;

import java.util.List;

public interface PromotionServices {

    void getPromotions(ServiceCallback<List<Promotions>> serviceCallback);
}
