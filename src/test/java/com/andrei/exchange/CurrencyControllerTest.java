package com.andrei.exchange;

import static org.mockito.BDDMockito.*;
import com.andrei.exchange.controller.CurrencyController;
import com.andrei.exchange.models.Currency;
import com.andrei.exchange.service.CurrencyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(CurrencyController.class)
@AutoConfigureWebClient
public class CurrencyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CurrencyService currencyServiceMock;

    @Test
    public void testCurrencyAndDateEndpoint() throws Exception {

        Date dateNow = new Date();
        String formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(dateNow);

        Currency usd = new Currency("usd", 1.0, dateNow);

        Mockito.when(currencyServiceMock.getByDateAndCurrency(any(Date.class), eq("usd"))).thenReturn(usd);

        mockMvc.perform(get("/currency")
                .param("date", formattedDate)
                .param("currency", "usd")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("application/json"))
                .andExpect(jsonPath("$.rate").value(usd.getRate()))
                .andExpect(jsonPath("$.currency").value(usd.getCurrency()));
    }


    @Test
    public void testDateEndpoint() throws Exception {

        Date dateNow = new Date();
        String formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(dateNow);
        Currency usd = new Currency("usd", 1.0, dateNow);
        Currency cad = new Currency("cad", 1.1, dateNow);
        List<Currency> currencyList = Arrays.asList(usd, cad);

        Mockito.when(currencyServiceMock.getAllByDate(any(Date.class))).thenReturn(currencyList);

        mockMvc.perform(get("/date")
                .param("date", formattedDate)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("application/json"))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].currency").value(usd.getCurrency()))
                .andExpect(jsonPath("$[0].rate").value(usd.getRate()));

    }

    @Test
    public void testCurrencyEndpoint() throws Exception {

        Date dateNow = new Date();
        String formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(dateNow);

        Date yesterday = new Date(System.currentTimeMillis() - 1000L * 60L * 60L * 24L);

        Currency usd = new Currency("usd", 1.0, dateNow);
        Currency usd2 = new Currency("usd", 1.1, yesterday);
        List<Currency> currencyList = Arrays.asList(usd, usd2);
        Page<Currency> page = new PageImpl<>(currencyList);

        Mockito.when(currencyServiceMock.getAllByCurrency(eq("usd"), any(PageRequest.class))).thenReturn(page);

        mockMvc.perform(get("/currency/all")
                .param("currency", "usd")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("application/json"))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].date").value(formattedDate));

    }
}