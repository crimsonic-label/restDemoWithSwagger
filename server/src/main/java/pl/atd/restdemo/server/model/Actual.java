package pl.atd.restdemo.server.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Dummy model object. It gets some data to be checked in controller test
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Actual {
    private Integer id;
    @ApiModelProperty(dataType = "org.joda.time.LocalDate")
    private LocalDate date;
}
