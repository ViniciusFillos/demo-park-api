package com.vinifillos.demoparkapi;


import com.vinifillos.demoparkapi.jwt.JwtToken;
import com.vinifillos.demoparkapi.web.dto.UsuarioLoginDto;
import com.vinifillos.demoparkapi.web.exception.ErrorMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "/sql/usuarios/usuarios-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/usuarios/usuarios-delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class AutenticacaoIT {

    @Autowired
    WebTestClient testClient;

    @Test
    public void autenticar_ComCredenciaisValidas_RetornarTokenComStatus200() {
        JwtToken resposeBody = testClient
                .post()
                .uri("/api/v1/auth")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue( new UsuarioLoginDto("ana@gmail.com", "123456"))
                .exchange()
                .expectStatus().isOk()
                .expectBody(JwtToken.class)
                .returnResult().getResponseBody();
        org.assertj.core.api.Assertions.assertThat(resposeBody).isNotNull();
    }

    @Test
    public void autenticar_ComCredenciaisInvalidas_RetornarErrorMessageComStatus400() {
        ErrorMessage resposeBody = testClient
                .post()
                .uri("/api/v1/auth")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue( new UsuarioLoginDto("invalido@gmail.com", "123456"))
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();
        org.assertj.core.api.Assertions.assertThat(resposeBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(resposeBody.getStatus()).isEqualTo(400);

        resposeBody = testClient
                .post()
                .uri("/api/v1/auth")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue( new UsuarioLoginDto("ana@gmail.com", "000000"))
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();
        org.assertj.core.api.Assertions.assertThat(resposeBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(resposeBody.getStatus()).isEqualTo(400);

    }

    @Test
    public void autenticar_ComUsernameInvalido_RetornarErrorMessageComStatus422() {
        ErrorMessage resposeBody = testClient
                .post()
                .uri("/api/v1/auth")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue( new UsuarioLoginDto("", "123456"))
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();
        org.assertj.core.api.Assertions.assertThat(resposeBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(resposeBody.getStatus()).isEqualTo(422);

        resposeBody = testClient
                .post()
                .uri("/api/v1/auth")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue( new UsuarioLoginDto("@gmail.com", "123456"))
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();
        org.assertj.core.api.Assertions.assertThat(resposeBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(resposeBody.getStatus()).isEqualTo(422);
    }

    @Test
    public void autenticar_ComPasswordInvalido_RetornarErrorMessageComStatus422() {
        ErrorMessage resposeBody = testClient
                .post()
                .uri("/api/v1/auth")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue( new UsuarioLoginDto("ana@gmail.com", ""))
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();
        org.assertj.core.api.Assertions.assertThat(resposeBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(resposeBody.getStatus()).isEqualTo(422);

        resposeBody = testClient
                .post()
                .uri("/api/v1/auth")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue( new UsuarioLoginDto("ana@gmail.com", "123"))
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();
        org.assertj.core.api.Assertions.assertThat(resposeBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(resposeBody.getStatus()).isEqualTo(422);

        resposeBody = testClient
                .post()
                .uri("/api/v1/auth")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue( new UsuarioLoginDto("ana@gmail.com", "1234567"))
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();
        org.assertj.core.api.Assertions.assertThat(resposeBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(resposeBody.getStatus()).isEqualTo(422);
    }

}
