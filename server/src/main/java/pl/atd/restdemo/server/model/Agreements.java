package pl.atd.restdemo.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Dummy model object. It gets some data to be checked in controller test
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Agreements {
    private List<Agreement> agreementList;
}
