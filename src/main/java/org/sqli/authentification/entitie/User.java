package org.sqli.authentification.entitie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue
    private Long id ;
    private String login;
    private String password ;
    private int loginAttempts ;
    @ManyToOne
    private Group group;



}


