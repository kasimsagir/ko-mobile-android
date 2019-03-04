package com.kinght.commerce.data.network.services;

import com.kinght.commerce.data.network.ServiceCallback;
import com.kinght.commerce.data.network.entities.CommonResponse;
import com.kinght.commerce.data.network.entities.Entries.Entry;
import com.kinght.commerce.data.network.entities.Servers.CreateEntryRequest;
import com.kinght.commerce.data.network.entities.Servers.Servers;

import java.util.List;

public interface ServerServices {

    void getServers(ServiceCallback<List<Servers>> serversServiceCallback);
    void createEntry(String serverId, CreateEntryRequest createEntryRequest, ServiceCallback<CommonResponse> commonResponseServiceCallback);
    void getServerEntries(String serverId, ServiceCallback<List<Entry>> listServiceCallback );
}
