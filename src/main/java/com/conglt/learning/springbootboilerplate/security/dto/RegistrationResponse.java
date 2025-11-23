package com.conglt.learning.springbootboilerplate.security.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for registration response.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegistrationResponse {

    private Long id;

    private String username;

    private String email;

    private String firstName;

    private String lastName;
}

