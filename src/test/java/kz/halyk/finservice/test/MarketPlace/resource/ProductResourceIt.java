package kz.halyk.finservice.test.MarketPlace.resource;

import kz.halyk.finservice.test.MarketPlace.dto.product.ProductCreateDto;
import kz.halyk.finservice.test.MarketPlace.dto.product.ProductSearchDto;
import kz.halyk.finservice.test.MarketPlace.dto.user.AuthDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductResourceIt {

    private static final String PRODUCT_RESOURCE_URL = "http://localhost:8080/api/products";
    private static final String LOGIN_URL = "http://localhost:8080/open-api/login";

    @Autowired
    private TestRestTemplate testRestTemplate;

    private String bearerToken;
    private HttpHeaders headers;

    /***
     * Application requires authorization in any API requests.
     */
    @BeforeEach
    public void login() {
        AuthDto authDto = new AuthDto();
        authDto.setLogin("210107177");
        authDto.setPassword("Aa123456");
        ResponseEntity<Map> mapResponseEntity = testRestTemplate.postForEntity(LOGIN_URL, authDto, Map.class);
        if (mapResponseEntity.getStatusCode().equals(HttpStatus.OK)) {
            bearerToken = mapResponseEntity.getBody().get("token").toString();
            headers = new HttpHeaders();
            headers.setBearerAuth(bearerToken);
        }
    }

    /***
     * User authorization check.
     */
    @Test
    public void userShouldBeAuthorized() {
        ResponseEntity<String> forEntity = testRestTemplate.getForEntity(PRODUCT_RESOURCE_URL, String.class);
        Assertions.assertEquals(forEntity.getStatusCode(), HttpStatus.FORBIDDEN);
    }

    /***
     * Checking for a non-existent product.
     */
    @Test
    public void getById() {
        StringBuilder urlBuilder = new StringBuilder(PRODUCT_RESOURCE_URL).append("/").append("2222222");
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> forEntity = testRestTemplate.exchange(urlBuilder.toString(), HttpMethod.GET, entity,
                String.class);
        Assertions.assertEquals(forEntity.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    /***
     * Checking if all fields are required.
     */
    @Test
    public void createProductTest() {
        ProductCreateDto productCreateDto = new ProductCreateDto();
        productCreateDto.setProductName("Test - 1");
        HttpEntity<ProductCreateDto> entity =new HttpEntity<>(productCreateDto, headers);
        ResponseEntity<String> exchange = testRestTemplate.exchange(PRODUCT_RESOURCE_URL, HttpMethod.POST, entity, String.class);
        Assertions.assertEquals(exchange.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    /***
     * Checking the price for correctness.
     */
    @Test
    public void priceShouldBeGreaterThanZero() {
        ProductCreateDto productCreateDto = new ProductCreateDto();
        productCreateDto.setProductName("Test - 2");
        productCreateDto.setDescription("Testing product");
        productCreateDto.setPrice(-2000);
        productCreateDto.setCategoryCode("FOOD");
        HttpEntity<ProductCreateDto> entity =new HttpEntity<>(productCreateDto, headers);
        ResponseEntity<String> exchange = testRestTemplate.exchange(PRODUCT_RESOURCE_URL, HttpMethod.POST, entity, String.class);
        Assertions.assertEquals(exchange.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    /***
     * Checking product search on price validation.
     */
    @Test
    public void test() {
        ProductSearchDto productSearchDto = new ProductSearchDto();
        productSearchDto.setPriceTo(70);
        productSearchDto.setPriceFrom(90);
        StringBuilder urlBuilder = new StringBuilder(PRODUCT_RESOURCE_URL).append("/search?priceFrom=%s&priceTo=%d");
        String url = String.format(urlBuilder.toString(), productSearchDto.getPriceFrom(), productSearchDto.getPriceTo());
        HttpEntity<ProductCreateDto> entity =new HttpEntity<>(null, headers);
        ResponseEntity<String> exchange = testRestTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        Assertions.assertEquals(exchange.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    /***
     * Checking the category when searching for a product.
     */
    @Test
    public void categoryShouldBeHaveCorrectCode() {
        String categoryCode = "AAAA";
        ProductSearchDto productSearchDto = new ProductSearchDto();
        productSearchDto.setCategoryCode(categoryCode);
        StringBuilder urlBuilder = new StringBuilder(PRODUCT_RESOURCE_URL).append("/search?categoryCode=%s");
        String url = String.format(urlBuilder.toString(), productSearchDto.getCategoryCode());
        ResponseEntity<String> exchange = testRestTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(null, headers),
                String.class);
        Assertions.assertEquals(exchange.getStatusCode(), HttpStatus.BAD_REQUEST);

    }

    /***
     * Checking the uniqueness of the product name and the completeness of the data.
     */
    @Test
    public void productCreateDataShouldHaveUniqueProductNameAndCorrectCategoryCode() {
        ProductCreateDto productCreateDto = fillProductDto();
        HttpEntity<ProductCreateDto> body = new HttpEntity<>(productCreateDto, headers);
        ResponseEntity<String> exchange = testRestTemplate.exchange(PRODUCT_RESOURCE_URL, HttpMethod.POST, body,
                String.class);
        Assertions.assertEquals(exchange.getStatusCode(), HttpStatus.BAD_REQUEST);

        productCreateDto.setProductName("Test1234");
        body = new HttpEntity<>(productCreateDto, headers);
        ResponseEntity<String> exchange2 = testRestTemplate.exchange(PRODUCT_RESOURCE_URL, HttpMethod.POST, body,
                String.class);
        Assertions.assertEquals(exchange2.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    private ProductCreateDto fillProductDto() {
        ProductCreateDto productCreateDto = new ProductCreateDto();
        productCreateDto.setPrice(2000);
        productCreateDto.setCategoryCode("AAAA"); // INCORRECT
        productCreateDto.setProductName("Test1"); // INCORRECT
        productCreateDto.setDescription("Testing product creation");
        return productCreateDto;
    }
}
