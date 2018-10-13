package com.example.poc.springbootgraphql.entities;

import lombok.Data;

@Data
public class Address {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private Geography geo;
}
