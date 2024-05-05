package com.vinifillos.demoparkapi;


import com.vinifillos.demoparkapi.web.dto.EstacionamentoCreateDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "/sql/estacionamentos/estacionamentos-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/estacionamentos/estacionamentos-delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class EstacionamentoIT {

    @Autowired
    WebTestClient testClient;

    @Test
    public void criarCheckIn_ComDadosValidos_RetornarCreatedAndLocation() {
        EstacionamentoCreateDto createDto = EstacionamentoCreateDto.builder()
                .placa("AAA-1A11")
                .marca("PORSCHE")
                .modelo("911")
                .cor("AZUL")
                .clienteCpf("60341191973")
                .build();

        testClient
                .post()
                .uri("/api/v1/estacionamentos/check-in")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "ana@gmail.com", "123456"))
                .bodyValue(createDto)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().exists(HttpHeaders.LOCATION)
                .expectBody()
                .jsonPath("placa").isEqualTo("AAA-1A11")
                .jsonPath("marca").isEqualTo("PORSCHE")
                .jsonPath("cor").isEqualTo("AZUL")
                .jsonPath("clienteCpf").isEqualTo("60341191973")
                .jsonPath("modelo").isEqualTo("911")
                .jsonPath("recibo").exists()
                .jsonPath("dataEntrada").exists()
                .jsonPath("vagaCodigo").exists();
    }

    @Test
    public void criarCheckIn_ComRoleClient_RetornarErrorMessageComStatus403() {
        EstacionamentoCreateDto createDto = EstacionamentoCreateDto.builder()
                .placa("AAA-1A11")
                .marca("PORSCHE")
                .modelo("911")
                .cor("AZUL")
                .clienteCpf("60341191973")
                .build();

        testClient
                .post()
                .uri("/api/v1/estacionamentos/check-in")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "bia@gmail.com", "123456"))
                .bodyValue(createDto)
                .exchange()
                .expectStatus().isForbidden()
                .expectBody()
                .jsonPath("status").isEqualTo("403")
                .jsonPath("path").isEqualTo("/api/v1/estacionamentos/check-in")
                .jsonPath("method").isEqualTo("POST");
    }

    @Test
    public void criarCheckIn_ComDadosInvalidos_RetornarErrorMessageComStatus422() {
        EstacionamentoCreateDto createDto = EstacionamentoCreateDto.builder()
                .placa("")
                .marca("PORSCHE")
                .modelo("911")
                .cor("AZUL")
                .clienteCpf("60341191973")
                .build();

        testClient
                .post()
                .uri("/api/v1/estacionamentos/check-in")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "ana@gmail.com", "123456"))
                .bodyValue(createDto)
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody()
                .jsonPath("status").isEqualTo("422")
                .jsonPath("path").isEqualTo("/api/v1/estacionamentos/check-in")
                .jsonPath("method").isEqualTo("POST");

        createDto = EstacionamentoCreateDto.builder()
                .placa("AAA-1A11")
                .marca("")
                .modelo("911")
                .cor("AZUL")
                .clienteCpf("60341191973")
                .build();

        testClient
                .post()
                .uri("/api/v1/estacionamentos/check-in")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "ana@gmail.com", "123456"))
                .bodyValue(createDto)
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody()
                .jsonPath("status").isEqualTo("422")
                .jsonPath("path").isEqualTo("/api/v1/estacionamentos/check-in")
                .jsonPath("method").isEqualTo("POST");

        createDto = EstacionamentoCreateDto.builder()
                .placa("AAA-1A11")
                .marca("PORSCHE")
                .modelo("")
                .cor("AZUL")
                .clienteCpf("60341191973")
                .build();

        testClient
                .post()
                .uri("/api/v1/estacionamentos/check-in")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "ana@gmail.com", "123456"))
                .bodyValue(createDto)
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody()
                .jsonPath("status").isEqualTo("422")
                .jsonPath("path").isEqualTo("/api/v1/estacionamentos/check-in")
                .jsonPath("method").isEqualTo("POST");

        createDto = EstacionamentoCreateDto.builder()
                .placa("AAA-1A11")
                .marca("PORSCHE")
                .modelo("911")
                .cor("")
                .clienteCpf("60341191973")
                .build();

        testClient
                .post()
                .uri("/api/v1/estacionamentos/check-in")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "ana@gmail.com", "123456"))
                .bodyValue(createDto)
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody()
                .jsonPath("status").isEqualTo("422")
                .jsonPath("path").isEqualTo("/api/v1/estacionamentos/check-in")
                .jsonPath("method").isEqualTo("POST");

        createDto = EstacionamentoCreateDto.builder()
                .placa("AAA-1A11")
                .marca("PORSCHE")
                .modelo("911")
                .cor("AZUL")
                .clienteCpf("")
                .build();

        testClient
                .post()
                .uri("/api/v1/estacionamentos/check-in")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "ana@gmail.com", "123456"))
                .bodyValue(createDto)
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody()
                .jsonPath("status").isEqualTo("422")
                .jsonPath("path").isEqualTo("/api/v1/estacionamentos/check-in")
                .jsonPath("method").isEqualTo("POST");
    }

    @Test
    public void criarCheckIn_ComCpfInexistente_RetornarErrorMessageComStatus404() {
        EstacionamentoCreateDto createDto = EstacionamentoCreateDto.builder()
                .placa("AAA-1A11")
                .marca("PORSCHE")
                .modelo("911")
                .cor("AZUL")
                .clienteCpf("05107347070")
                .build();

        testClient
                .post()
                .uri("/api/v1/estacionamentos/check-in")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "ana@gmail.com", "123456"))
                .bodyValue(createDto)
                .exchange()
                .expectStatus().isEqualTo(404)
                .expectBody()
                .jsonPath("status").isEqualTo("404")
                .jsonPath("path").isEqualTo("/api/v1/estacionamentos/check-in")
                .jsonPath("method").isEqualTo("POST");
    }

    @Sql(scripts = "/sql/estacionamentos/estacionamento-insert-vagas-ocupadas.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/sql/estacionamentos/estacionamento-delete-vagas-ocupadas.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void criarCheckIn_ComVagasOcupadas_RetornarErrorMessageComStatus404() {
        EstacionamentoCreateDto createDto = EstacionamentoCreateDto.builder()
                .placa("AAA-1A11")
                .marca("PORSCHE")
                .modelo("911")
                .cor("AZUL")
                .clienteCpf("60341191973")
                .build();

        testClient
                .post()
                .uri("/api/v1/estacionamentos/check-in")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "ana@gmail.com", "123456"))
                .bodyValue(createDto)
                .exchange()
                .expectStatus().isEqualTo(404)
                .expectBody()
                .jsonPath("status").isEqualTo("404")
                .jsonPath("path").isEqualTo("/api/v1/estacionamentos/check-in")
                .jsonPath("method").isEqualTo("POST");
    }

   @Test
    public void buscarCheckIn_ComPerfilAdmin_RetornarDadosComStatus200() {

        testClient
               .get()
               .uri("/api/v1/estacionamentos/check-in/{recibo}", "20230313-101300")
               .headers(JwtAuthentication.getHeaderAuthorization(testClient, "ana@gmail.com", "123456"))
               .exchange()
               .expectStatus().isEqualTo(200)
               .expectBody()
               .jsonPath("placa").isEqualTo("FIT-1A20")
               .jsonPath("marca").isEqualTo("FIAT")
               .jsonPath("cor").isEqualTo("VERDE")
               .jsonPath("clienteCpf").isEqualTo("60341191973")
               .jsonPath("modelo").isEqualTo("PALIO")
               .jsonPath("recibo").isEqualTo("20230313-101300")
               .jsonPath("dataEntrada").isEqualTo("2023-03-13 10:15:00")
               .jsonPath("vagaCodigo").isEqualTo("A-01");
    }

    @Test
    public void buscarCheckIn_ComPerfilCliente_RetornarDadosComStatus200() {

        testClient
                .get()
                .uri("/api/v1/estacionamentos/check-in/{recibo}", "20230313-101300")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "bia@gmail.com", "123456"))
                .exchange()
                .expectStatus().isEqualTo(200)
                .expectBody()
                .jsonPath("placa").isEqualTo("FIT-1A20")
                .jsonPath("marca").isEqualTo("FIAT")
                .jsonPath("cor").isEqualTo("VERDE")
                .jsonPath("clienteCpf").isEqualTo("60341191973")
                .jsonPath("modelo").isEqualTo("PALIO")
                .jsonPath("recibo").isEqualTo("20230313-101300")
                .jsonPath("dataEntrada").isEqualTo("2023-03-13 10:15:00")
                .jsonPath("vagaCodigo").isEqualTo("A-01");
    }

    @Test
    public void buscarCheckIn_ComReciboInexistente_RetornarErrorMessageComStatus404() {

        testClient
                .get()
                .uri("/api/v1/estacionamentos/check-in/{recibo}", "00000000-000000")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "ana@gmail.com", "123456"))
                .exchange()
                .expectStatus().isEqualTo(404)
                .expectBody()
                .jsonPath("status").isEqualTo("404")
                .jsonPath("path").isEqualTo("/api/v1/estacionamentos/check-in/00000000-000000")
                .jsonPath("method").isEqualTo("GET");
    }

    @Test
    public void criarCheckOut_ComReciboExistente_RetornarSucessoComStatus404() {

        testClient
                .put()
                .uri("/api/v1/estacionamentos/check-out/{recibo}", "20230313-101300")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "ana@gmail.com", "123456"))
                .exchange()
                .expectStatus().isEqualTo(200)
                .expectBody()
                .jsonPath("placa").isEqualTo("FIT-1A20")
                .jsonPath("modelo").isEqualTo("PALIO")
                .jsonPath("marca").isEqualTo("FIAT")
                .jsonPath("cor").isEqualTo("VERDE")
                .jsonPath("dataEntrada").isEqualTo("2023-03-13 10:15:00")
                .jsonPath("clienteCpf").isEqualTo("60341191973")
                .jsonPath("vagaCodigo").isEqualTo("A-01")
                .jsonPath("recibo").isEqualTo("20230313-101300")
                .jsonPath("dataSaida").exists()
                .jsonPath("valor").exists()
                .jsonPath("desconto").exists();
    }

    @Test
    public void criarCheckOut_ComReciboInexistente_RetornarErrorMessageComStatus404() {

        testClient
                .put()
                .uri("/api/v1/estacionamentos/check-out/{recibo}", "00000000-000000")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "ana@gmail.com", "123456"))
                .exchange()
                .expectStatus().isEqualTo(404)
                .expectBody()
                .jsonPath("status").isEqualTo("404")
                .jsonPath("path").isEqualTo("/api/v1/estacionamentos/check-out/00000000-000000")
                .jsonPath("method").isEqualTo("PUT");
    }

    @Test
    public void criarCheckOut_ComRoleCliente_RetornarErrorMessageComStatus403() {

        testClient
                .put()
                .uri("/api/v1/estacionamentos/check-out/{recibo}", "20230313-101300")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "bia@gmail.com", "123456"))
                .exchange()
                .expectStatus().isEqualTo(403)
                .expectBody()
                .jsonPath("status").isEqualTo("403")
                .jsonPath("path").isEqualTo("/api/v1/estacionamentos/check-out/20230313-101300")
                .jsonPath("method").isEqualTo("PUT");
    }
}


