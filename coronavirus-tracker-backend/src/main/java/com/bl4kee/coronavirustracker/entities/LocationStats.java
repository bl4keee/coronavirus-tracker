package com.bl4kee.coronavirustracker.entities;

import lombok.Data;

@Data
public class LocationStats {

    private String state;
    private String country;
    private int latestTotalNumberOfCases;
}
