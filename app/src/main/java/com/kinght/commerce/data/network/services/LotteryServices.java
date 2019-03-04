package com.kinght.commerce.data.network.services;

import com.kinght.commerce.data.network.ServiceCallback;
import com.kinght.commerce.data.network.entities.CommonResponse;
import com.kinght.commerce.data.network.entities.Lottery.Lottery;

import java.util.List;

public interface LotteryServices {
    void getLotteries(ServiceCallback<List<Lottery>> listServiceCallback);
    void getLotteryDetail(String lotteryId,ServiceCallback<Lottery> lotteryServiceCallback);
    void partipicateLottery(String lotteryId, ServiceCallback<CommonResponse> lotteryServiceCallback);
}
