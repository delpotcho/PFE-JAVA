package org.sqli.authentification.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserFormDtoRequest {
    private String login;
        private String password;
        private String group;
}
