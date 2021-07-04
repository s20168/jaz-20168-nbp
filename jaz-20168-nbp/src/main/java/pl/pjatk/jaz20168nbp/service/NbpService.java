package pl.pjatk.jaz20168nbp.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.pjatk.jaz20168nbp.model.ExchangeRate;
import pl.pjatk.jaz20168nbp.model.ExchangeResponse;
import pl.pjatk.jaz20168nbp.model.Ore;
import pl.pjatk.jaz20168nbp.repository.NbpRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class NbpService {

    private final RestTemplate restTemplate;
    private final NbpRepository nbpResponseRepository;

    public NbpService(RestTemplate restTemplate, NbpRepository nbpResponseRepository) {
        this.restTemplate = restTemplate;
        this.nbpResponseRepository = nbpResponseRepository;
    }

    public ExchangeRate calculateGoldFromDates(String beginDate, String endDate) {
        String url = "http://api.nbp.pl/api/cenyzlota/" + beginDate + "/" + endDate;

        ExchangeResponse[] response = restTemplate.getForObject(url, ExchangeResponse[].class);
        double average = calculateAveragePrice(response);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        ExchangeRate exchangeRate = new ExchangeRate();
        exchangeRate.setOre(Ore.GOLD);
        exchangeRate.setBeginDate(LocalDate.parse(beginDate, formatter));
        exchangeRate.setEndDate(LocalDate.parse(endDate, formatter));
        exchangeRate.setExchange(average);
        exchangeRate.setHour(LocalDateTime.now());
        nbpResponseRepository.save(exchangeRate);
        return exchangeRate;
    }

    public double calculateAveragePrice(ExchangeResponse[] response) {
        double sumPrice = 0;
        for (var exResponse : response) {
            sumPrice += exResponse.getCena();
        }
        double averagePrice = sumPrice / response.length;
        return averagePrice;
    }

    public List<ExchangeRate> getAllSavedExchangeRates() {
        return nbpResponseRepository.findAll();
    }
}
