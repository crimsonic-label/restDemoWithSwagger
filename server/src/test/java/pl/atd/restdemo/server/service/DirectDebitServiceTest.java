package pl.atd.restdemo.server.service;

import org.junit.Test;
import pl.atd.restdemo.server.model.Actual;
import pl.atd.restdemo.server.model.Agreement;
import pl.atd.restdemo.server.model.Agreements;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class DirectDebitServiceTest {

    private DirectDebitService directDebitService = new DirectDebitService();

    @Test
    public void shouldCreateActual(){
        Actual actual = directDebitService.getActual(1, 1999, 12);
        assertNotNull(actual);
        assertEquals(Integer.valueOf(1), actual.getId());
        assertEquals(LocalDate.of(1999,12,1), actual.getDate());
    }

    @Test
    public void shouldCreateAgreement(){
        Agreement agreement = directDebitService.getAgreement(1);
        assertNotNull(agreement);
        assertEquals(Integer.valueOf(1), agreement.getId());
    }

    @Test
    public void shouldCreateAgreementsList(){
        Agreements agreementList = directDebitService.getAgreementList();
        assertNotNull(agreementList);
        assertEquals(3, agreementList.getAgreementList().size());
    }
}