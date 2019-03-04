package com.kinght.commerce.data.network.services;

import com.kinght.commerce.data.network.ServiceCallback;
import com.kinght.commerce.data.network.entities.CommonResponse;
import com.kinght.commerce.data.network.entities.Entries.Entry;
import com.kinght.commerce.data.network.entities.Entries.UpdateEntryRequest;
import com.kinght.commerce.data.network.entities.Report.ReportRequest;

import java.util.List;

public interface EntryServices {

    void createReport(String entryId,ReportRequest reportRequest, ServiceCallback<CommonResponse> commonResponseServiceCallback);
    void getEntries(ServiceCallback<List<Entry>> entries);
    void deleteEntry(String entryId,ServiceCallback<CommonResponse> commonResponseServiceCallback);
    void getEntry(String entryId,ServiceCallback<Entry> entryServiceCallback);
    void updateEntry(String area,String entryId, UpdateEntryRequest updateEntryRequest,ServiceCallback<CommonResponse> commonResponseServiceCallback);
}
