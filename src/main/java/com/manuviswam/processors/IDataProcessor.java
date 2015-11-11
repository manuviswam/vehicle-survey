package com.manuviswam.processors;

import com.manuviswam.model.VehicleEntry;

import java.util.List;

public interface IDataProcessor {
    String process(List<VehicleEntry> entries);
}
