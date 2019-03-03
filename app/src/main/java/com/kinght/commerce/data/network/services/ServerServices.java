package com.kinght.commerce.data.network.services;

import com.kinght.commerce.data.network.ServiceCallback;
import com.kinght.commerce.data.network.entities.Servers.Servers;

import java.util.List;

public interface ServerServices {

    void getServers(ServiceCallback<List<Servers>> serversServiceCallback);
}
