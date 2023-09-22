package com.calebematos.worstmovie.domain.service;

import com.calebematos.worstmovie.domain.model.Movie;
import com.calebematos.worstmovie.domain.model.Producer;
import com.calebematos.worstmovie.domain.model.dto.CsvData;
import com.calebematos.worstmovie.domain.model.dto.CsvHeader;
import com.calebematos.worstmovie.domain.repository.MovieRepository;
import com.calebematos.worstmovie.domain.repository.ProducerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.*;

@Component
@RequiredArgsConstructor
public class DatabaseInitializer {

    private final ProducerRepository producerRepository;
    private final MovieRepository movieRepository;
    public static final String SEPARATOR = ";";

    @EventListener(ApplicationReadyEvent.class)
    public void addCsvDataToDatabase() {
        var movieList = this.getClass().getResourceAsStream("/csv/movielist.csv");
        List<CsvData> csvDataList = getCsvData(movieList);
        formatCsvData(csvDataList);
    }

    @Transactional
    private void formatCsvData(List<CsvData> csvDataList) {

        for (CsvData csvData : csvDataList) {

            List<Producer> producers = getProducers(csvData.producers());

            Movie movie = Movie.builder()
                    .event_year(Integer.parseInt(csvData.year()))
                    .title(csvData.title())
                    .studios(csvData.studios())
                    .winner(csvData.winner())
                    .producers(producers)
                    .build();

            movieRepository.save(movie);
        }
    }

    private List<Producer> getProducers(String producers) {
        List<String> producerNames = new ArrayList<>();
        String[] splitAnd = producers.split(" and ");
        if (splitAnd.length > 1) {
            producerNames.add(splitAnd[1]);
            String[] splitComma = splitAnd[0].split(",");
            producerNames.addAll(Arrays.stream(splitComma).filter(t -> !t.isBlank()).toList());
        } else {
            producerNames.add(splitAnd[0]);
        }
        List<Producer> producerList = new ArrayList<>();

        for (String name : producerNames) {
            Producer producer = producerRepository.findByName(name.trim());
            if (producer == null) {
                producer = Producer.builder().name(name.trim()).build();
                producerRepository.saveAndFlush(producer);
            }
            producerList.add(producer);
        }
        return producerList;
    }

    private List<CsvData> getCsvData(InputStream movieList) {
        List<CsvData> csvDataList = new ArrayList<>();


        Scanner scanner = new Scanner(movieList);
        String[] headers = scanner.nextLine().split(SEPARATOR);

        while (scanner.hasNextLine()) {

            String line = scanner.nextLine();
            parseLine(line, headers, csvDataList);

        }

        return csvDataList;
    }


    private void parseLine(String line, String[] headers, List<CsvData> csvDataList) {
        String[] split = line.split(SEPARATOR);

        Map<String, String> map = mappingLine(split, headers);

        CsvData csvData = new CsvData(map.get(CsvHeader.YEAR.getHeaderName()),
                map.get(CsvHeader.TITLE.getHeaderName()),
                map.get(CsvHeader.STUDIOS.getHeaderName()),
                map.get(CsvHeader.PRODUCERS.getHeaderName()),
                "yes".equals(map.get(CsvHeader.WINNER.getHeaderName())));

        csvDataList.add(csvData);
    }

    private Map<String, String> mappingLine(String[] line, String[] headers) {
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < line.length; i++) {
            map.put(headers[i], line[i]);
        }

        return map;
    }


}
