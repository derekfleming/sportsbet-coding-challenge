package com.derekfleming.sportsbetchallenge.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Customer {
    private String name;
    private Integer age;
}
