package pl.pjatk.jaz20168nbp.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pjatk.jaz20168nbp.model.ExchangeRate;
import pl.pjatk.jaz20168nbp.service.NbpService;

import java.util.List;

@RestController
@RequestMapping("/nbp")
public class NbpController {

    private final NbpService nbpService;


    public NbpController(NbpService nbpService) {
        this.nbpService = nbpService;
    }
    @GetMapping(value = "/{beginDate}/{endDate}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ExchangeRate> calculateAverageGoldPrice(
            @PathVariable String beginDate,
            @PathVariable String endDate) {
        return ResponseEntity.ok(nbpService.calculateGoldFromDates(beginDate, endDate));
    }
    @GetMapping(value = "/rates")
    public ResponseEntity<List<ExchangeRate>> getAllSavedExchangeRates () {
        return ResponseEntity.ok(nbpService.getAllSavedExchangeRates());


    }
}
