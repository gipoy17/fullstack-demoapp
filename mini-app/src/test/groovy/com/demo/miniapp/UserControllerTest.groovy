package com.demo.miniapp

import com.demo.miniapp.dto.UserDTO
import com.demo.miniapp.presentation.ApiResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.RequestEntity
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
classes = [MiniAppApplication.class],
properties = ["spring.cloud.vault.enabled: false"])
class UserControllerTest extends Specification {

    @Autowired
    private TestRestTemplate restTemplate

    @Shared
    private String baseUrl = "/user"

    @Unroll
    def "user access create user endpoint"() {
        given:
            def req = new HttpEntity<>(userDto, null);
        when:
            def response = restTemplate.exchange(baseUrl + "/create",
                    HttpMethod.POST, req,
            new ParameterizedTypeReference<ApiResponse<UserDTO>>() {})
        then:
            response.statusCode == httpStatus

        where:
        httpStatus                          || userDto
        HttpStatus.OK                       || buildUserDTO()
        HttpStatus.BAD_REQUEST              || buildUserDTO(['firstName': ''])
    }

    private UserDTO buildUserDTO(def customAttr = [:]) {
        def attr = ['firstName': 'John', 'lastName': 'doe', 'username': 'john.doe', 'password': 'test', 'role': 'Administrator']
        attr.putAll(customAttr)
        return attr
    }
}
