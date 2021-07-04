package pl.pjatk.jaz20168nbp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class ExchangeRate {
    @Id
    @GeneratedValue
    private Long id;
    private Ore ore;
    private LocalDate beginDate;
    private LocalDate endDate;
    private double exchange;
    private LocalDateTime hour;

    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setHour(LocalDateTime hour) {
        this.hour = hour;
    }

    public LocalDate getBeginDate() {
        return beginDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public LocalDateTime getHour() {
        return hour;
    }

    public Long getId() {
        return id;
    }

    public Ore getOre() {
        return ore;
    }

    public double getExchange() {
        return exchange;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOre(Ore ore) {
        this.ore = ore;
    }

    public void setExchange(double exchange) {
        this.exchange = exchange;
    }
}
