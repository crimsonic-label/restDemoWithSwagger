package pl.atd.restdemo.server.mvc;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.atd.restdemo.server.model.Actual;
import pl.atd.restdemo.server.model.Agreement;
import pl.atd.restdemo.server.model.Agreements;
import pl.atd.restdemo.server.service.DirectDebitService;

@RestController
@RequestMapping("/api")
public class DirectDebitController {
    private static final String ID_NUMBER = "ID_NUMBER";
    private DirectDebitService directDebitService;

    public DirectDebitController(DirectDebitService directDebitService) {
        this.directDebitService = directDebitService;
    }

    @GetMapping("/actual/{"+ ID_NUMBER +"}")
    public ResponseEntity<Actual> getActual(@PathVariable(name = ID_NUMBER) Integer pbsNumber,
                                                                  @RequestParam("startYear") Integer startYear,
                                                                  @RequestParam("startMonth") Integer startMonth) {
        return new ResponseEntity<>(directDebitService.getActual(pbsNumber, startYear, startMonth), HttpStatus.OK);
    }

    @GetMapping("/agreement/{"+ ID_NUMBER +"}")
    public ResponseEntity<Agreement> getAgreement(@PathVariable(name = DirectDebitController.ID_NUMBER) Integer pbsNumber) {
        return new ResponseEntity<>(directDebitService.getAgreement(pbsNumber), HttpStatus.OK);
    }

    @GetMapping("/agreementList")
    public ResponseEntity<Agreements> getAgreementList() {
        return new ResponseEntity<>(directDebitService.getAgreementList(), HttpStatus.OK);
    }
}
