package pl.atd.restdemo.server.service;

import org.springframework.stereotype.Service;
import pl.atd.restdemo.server.model.Actual;
import pl.atd.restdemo.server.model.Agreement;
import pl.atd.restdemo.server.model.Agreements;

import java.time.LocalDate;
import java.util.Arrays;

/**
 * Dummy service - it returns nothing, it does not matter because the controller test mocks it
 */

@Service
public class DirectDebitService {
    public Actual getActual(Integer id, Integer startYear, Integer startMonth) {
        return new Actual(id, LocalDate.of(startYear, startMonth, 1));
    }

    public Agreement getAgreement(Integer id) {
        return new Agreement(id);
    }

    public Agreements getAgreementList() {
        return new Agreements(Arrays.asList(new Agreement(1), new Agreement(2), new Agreement(3)));
    }
}
