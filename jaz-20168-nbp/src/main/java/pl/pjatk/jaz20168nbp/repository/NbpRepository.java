package pl.pjatk.jaz20168nbp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pjatk.jaz20168nbp.model.ExchangeRate;

public interface NbpRepository extends JpaRepository<ExchangeRate, Long> {
}
