package com.nicopaez.apisugerencias;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.maciejwalkowiak.wiremock.spring.ConfigureWireMock;
import com.maciejwalkowiak.wiremock.spring.EnableWireMock;
import com.maciejwalkowiak.wiremock.spring.InjectWireMock;
import com.nicopaez.apisugerencias.dtos.SugerenciaResponse;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableWireMock(@ConfigureWireMock(name = "default", properties = {
		"openweather.baseurl",
		"app.client-apis.bar.base-path",
		"app.client-apis.mojo.base-path"}))
class ApiSugerenciasApplicationTests {

	@InjectWireMock("default")
	private WireMockServer wiremock;

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void greetingShouldReturnDefaultMessage() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/api/",
				String.class)).contains("Hello, World");
	}

	String OPEN_WEATHER_JSON_RESPONSE = "{\"coord\":{\"lon\":36,\"lat\":45},\"weather\":[{\"id\":801,\"main\":\"Clouds\",\"description\":\"few clouds\",\"icon\":\"02n\"}],\"base\":\"stations\",\"main\":{\"temp\":293.55,\"feels_like\":293.55,\"temp_min\":293.55,\"temp_max\":293.55,\"pressure\":1018,\"humidity\":73,\"sea_level\":1018,\"grnd_level\":1017},\"visibility\":10000,\"wind\":{\"speed\":4.46,\"deg\":182,\"gust\":4.71},\"clouds\":{\"all\":16},\"dt\":1727991670,\"sys\":{\"country\":\"UA\",\"sunrise\":1728013066,\"sunset\":1728054679},\"timezone\":10800,\"id\":688817,\"name\":\"Vulkanovka\",\"cod\":200}";

	@Test
	public void getSugerenciasDeberiaDevolverVestimentaSurgerida() throws Exception {
		configurarSimuladorProveedorTemperatura();
		String vestimentaSugerida = this.restTemplate.getForObject(getUrl(), SugerenciaResponse.class).vestimentaSugerida;
		assertThat(vestimentaSugerida).isEqualTo("Remera");
	}

	private @NotNull String getUrl() {
		String url = "http://localhost:" + port + "/api/sugerencias";
		return url;
	}

	private void configurarSimuladorProveedorTemperatura() {
		this.wiremock.stubFor(get(urlEqualTo("/?lat=45&lon=36&APPID=null"))
				.willReturn(okJson(OPEN_WEATHER_JSON_RESPONSE)));
	}
}
