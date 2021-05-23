package com.bl4kee.coronavirustracker.services;

import com.bl4kee.coronavirustracker.entities.LocationStats;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Data
@Service
@RequiredArgsConstructor
public class CoronavirusDataService {

    public List<LocationStats> allStats = new ArrayList<>();

    private static String CORONAVIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void fetchCoronavirusData() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(CORONAVIRUS_DATA_URL))
                .build();

        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        parseResponse(httpResponse);
    }

    private void parseResponse(HttpResponse<String> response) throws IOException {
        StringReader reader = new StringReader(response.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);

        printRecords(records);
    }

    private void printRecords(Iterable<CSVRecord> records) {
        List<LocationStats> newStats = new ArrayList<>();

        for (CSVRecord record : records) {
            LocationStats locationStat = new LocationStats();
            locationStat.setState(record.get("Province/State"));
            locationStat.setCountry(record.get("Country/Region"));
            locationStat.setLatestTotalNumberOfCases(Integer.parseInt(record.get(record.size() -  1)));
            newStats.add(locationStat);
        }
        this.allStats = newStats;
    }
}
