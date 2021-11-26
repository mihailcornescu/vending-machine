package com.mvpmatch.vendingmachine;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mvpmatch.vendingmachine.persistence.ProductRepository;
import com.mvpmatch.vendingmachine.persistence.entity.ProductEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONCompare;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment= RANDOM_PORT, properties = {"spring.datasource.url=jdbc:h2:mem:vending-db"})
class VendingMachineApplicationTests {

    private static final String BASE_URI_DATA = "src/test/resources/data/";

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        ProductEntity productEntity = ProductEntity.builder()
                .id(1L)
                .amountAvailable(10)
                .cost(BigDecimal.valueOf(2.5))
                .productName("milk")
                .sellerId(1)
                .build();
        productRepository.save(productEntity);
    }

    @Test
    @WithMockUser(roles="SELLER")
    void createProduct() throws Exception {
        String requestJson = Files.readString(Path.of(BASE_URI_DATA + "request_create_product.json"));
        mockMvc.perform(post("/api/v1/products").contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isCreated());
        Optional<ProductEntity> optional = productRepository.findFirstByProductName("apples");
        assertTrue(optional.isPresent());
        ProductEntity productEntity = optional.get();
        assertEquals("apples", productEntity.getProductName());
    }

    @Test
    @WithMockUser(roles = "BUYER")
    void getProduct() throws Exception {
        String expectedString = Files.readString(Path.of(BASE_URI_DATA + "response_get_product.json"));
        ResultActions resultActions = mockMvc.perform(get("/api/v1/products/1")
                .contentType(MediaType.APPLICATION_JSON));
        resultActions.andExpect(status().isOk());
        String responseJsonString = resultActions.andReturn().getResponse().getContentAsString();
        JSONCompare.compareJSON(expectedString, responseJsonString, JSONCompareMode.STRICT);
    }

    @Test
    @WithMockUser(roles="SELLER")
    void updateProduct() throws Exception {
        String expectedString = Files.readString(Path.of(BASE_URI_DATA + "response_put_product.json"));
        String requestJson = Files.readString(Path.of(BASE_URI_DATA + "request_put_product.json"));
        ResultActions resultActions = mockMvc.perform(put("/api/v1/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk());
        String responseJsonString = resultActions.andReturn().getResponse().getContentAsString();
        JSONCompare.compareJSON(expectedString, responseJsonString, JSONCompareMode.STRICT);
    }

}
