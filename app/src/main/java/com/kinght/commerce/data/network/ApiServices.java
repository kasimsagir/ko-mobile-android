package com.kinght.commerce.data.network;


import com.kinght.commerce.data.network.services.ApplicationServices;
import com.kinght.commerce.data.network.services.EntryServices;
import com.kinght.commerce.data.network.services.LotteryServices;
import com.kinght.commerce.data.network.services.PromotionServices;
import com.kinght.commerce.data.network.services.UserServices;
import com.kinght.commerce.data.network.services.ServerServices;

public interface ApiServices extends UserServices, ServerServices, ApplicationServices, PromotionServices, EntryServices, LotteryServices {
}
