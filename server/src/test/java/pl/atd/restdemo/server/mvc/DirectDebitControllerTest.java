package pl.atd.restdemo.server.mvc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.atd.restdemo.server.exception.AgreementNotFoundException;
import pl.atd.restdemo.server.model.Actual;
import pl.atd.restdemo.server.model.Agreement;
import pl.atd.restdemo.server.model.Agreements;
import pl.atd.restdemo.server.service.DirectDebitService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * spring test but context is created only with controller, advice and mvc components
 * set controller class to be tested
 */
@RunWith(SpringRunner.class)
@WebMvcTest(DirectDebitController.class)
public class DirectDebitControllerTest {

    // mock to which we send requests for url
    @Autowired
    private MockMvc mvc;

    // mocked service - we will define what data will be returned with this mock
    @MockBean
    private DirectDebitService directDebitService;

    /**
     * check the date returned by controller
     */
    @Test
    public void shouldReturnActuals() throws Exception {

        // define the data returned with the service mock
        LocalDate date = LocalDate.of(2019, 1, 1);
        Actual actual = new Actual(1, date);
        given(directDebitService.getActual(1, date.getYear(), date.getMonthValue())).willReturn(actual);

        // request for an url with params
        mvc.perform(get("/api/actual/" + actual.getId() +
                "?startYear="+date.getYear()+"&startMonth=" + date.getMonthValue())
                .contentType(MediaType.APPLICATION_JSON))
                // check the response status
                .andExpect(status().isOk())
                // check the returned data - take json elements
                .andExpect(jsonPath("$.id", is(actual.getId())))
                .andExpect(jsonPath("$.date", is(actual.getDate().toString())));
    }

    /**
     * check if controller returns error status when the service throws an exception
     * The controller advice should catch the exception and set the response status
     */
    @Test
    public void shouldReturn404StatusForNonExistingActulasDueToControllerAdvice() throws Exception {

        LocalDate date = LocalDate.of(2019, 1, 1);
        // NotFoundAdvice returns 404 status with a message: Agreement not found
        given(directDebitService.getActual(1, 2019, 1)).willThrow(new AgreementNotFoundException());

        mvc.perform(get("/api/actual/1?startYear=2019&startMonth=1")
                .contentType(MediaType.APPLICATION_JSON))
                // check response error status
                .andExpect(status().isNotFound())
                // check the response message set by the controller advice
                .andExpect(content().string("Agreement not found"));
    }

    @Test
    public void shouldReturnAgreement() throws Exception {

        Agreement agreement = new Agreement(1);
        given(directDebitService.getAgreement(1)).willReturn(agreement);

        mvc.perform(get("/api/agreement/" + agreement.getId() )
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(agreement.getId())));
    }

    @Test
    public void shouldReturnAgreements() throws Exception {
        List<Agreement> agreements = new ArrayList<>(2);
        agreements.add(new Agreement(1));
        agreements.add(new Agreement(2));
        given(directDebitService.getAgreementList()).willReturn(new Agreements(agreements));

        mvc.perform(get("/api/agreementList/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.agreementList", hasSize(agreements.size())))
                .andExpect(jsonPath("$.agreementList[0].id", is(agreements.get(0).getId())))
                .andExpect(jsonPath("$.agreementList[1].id", is(agreements.get(1).getId())));
    }


    @Test
    public void shouldReturn404StatusForNonExistingAgreementDueToControllerAdvice() throws Exception {

        // NotFoundAdvice returns 404 status with a message: Agreement not found
        given(directDebitService.getAgreement(1)).willThrow(new AgreementNotFoundException());

        mvc.perform(get("/api/agreement/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()).andExpect(content().string("Agreement not found"));
    }

}