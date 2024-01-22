package projetoTesteCursoApi;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class TesteApiCampeonatoBrasileiro {
	
	
	@Test
	public void testeApiCampeonatoBrasileiro() {
		String path = "campeonatos/14";
		String authorization = "Bearer test_3923ecb982bcebf98a15e2e55ee854";
		String url = "https://api.api-futebol.com.br/v1/";
		Map<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Authorization",authorization);
		
		given()
			.log().all()
			.headers(headerMap)
		.when()
			.get(url+path)
		.then()
			.log().all()
			.assertThat()
			.statusCode(200)
			.body("nome_popular", containsString("Brasileirão Série B"));
		
		
		}
	
	@Test
	public void testeApiArtilheiroCampeonatoBrasileiro() {
		String path = "14/artilharia";
		String authorization = "Bearer test_3923ecb982bcebf98a15e2e55ee854";
		String url = "https://api.api-futebol.com.br/v1/";
		Map<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Authorization",authorization);
		
		String retornoPath =
		given()
			.log().all()
			.headers(headerMap)
		.when()
			.get("https://api.api-futebol.com.br/v1/campeonatos/14/artilharia")
		.then()
			.log().all()
			.assertThat()
			.statusCode(200)
			.body("atleta[0].nome_popular", containsString ("Caio Dantas"))
			.extract()
			.path("atleta[0].nome_popular");
		
		System.out.println("O artilheiro do Campeonato Brasileiro Série B é " + retornoPath);
		Assert.assertEquals("Caio Dantas", retornoPath);
		}
}


