package org.sqli.authentification.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sqli.authentification.entitie.Group;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDtoResponse {

    private Long id ;
    private String login;
    private String group;
}
