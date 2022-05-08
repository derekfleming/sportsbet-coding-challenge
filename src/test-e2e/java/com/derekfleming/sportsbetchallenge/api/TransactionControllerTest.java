package com.derekfleming.sportsbetchallenge.api;

import com.derekfleming.sportsbetchallenge.api.dto.CustomerDto;
import com.derekfleming.sportsbetchallenge.api.dto.TicketDto;
import com.derekfleming.sportsbetchallenge.api.dto.TransactionRequest;
import com.derekfleming.sportsbetchallenge.api.dto.TransactionResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TransactionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @ParameterizedTest
    @MethodSource("validArguments")
    public void controllerReturnsExpectedResponse(TransactionRequest input, TransactionResponse expected) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        MvcResult mvcResult = mockMvc.perform(
                post("/transactions")
                        .content(objectMapper.writeValueAsString(input))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        TransactionResponse actual = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), TransactionResponse.class);

        assertEquals(expected, actual);
    }

    private static Stream<Arguments> validArguments() {
        return Stream.of(
                Arguments.of(
                        TransactionRequest.builder()
                                .transactionId(1)
                                .customers(List.of())
                                .build(),
                        TransactionResponse.builder()
                                .transactionId(1)
                                .build()),
                Arguments.of(
                        TransactionRequest.builder()
                                .transactionId(1)
                                .customers(List.of(
                                        CustomerDto.builder()
                                                .name("John Smith")
                                                .age(70)
                                                .build(),
                                        CustomerDto.builder()
                                                .name("Jane Doe")
                                                .age(5)
                                                .build(),
                                        CustomerDto.builder()
                                                .name("Bob Doe")
                                                .age(6)
                                                .build()
                                ))
                                .build(),
                        TransactionResponse.builder()
                                .transactionId(1)
                                .tickets(List.of(
                                        TicketDto.builder()
                                                .ticketType("Children")
                                                .quantity(2)
                                                .totalCost(10.00)
                                                .build(),
                                        TicketDto.builder()
                                                .ticketType("Senior")
                                                .quantity(1)
                                                .totalCost(17.5)
                                                .build()
                                ))
                                .totalCost(27.5)
                                .build()

                )
        );
    }
}