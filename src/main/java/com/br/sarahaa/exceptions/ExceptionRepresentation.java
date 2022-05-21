package com.br.sarahaa.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.Data;

import java.util.Set;


@Data
@Builder
@JsonInclude(Include.NON_EMPTY)
public class ExceptionRepresentation {

    private String errorMessage;

    private String errorSource;

    private Set<String> validationErrors;

}
