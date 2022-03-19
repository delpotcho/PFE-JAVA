package org.sqli.authentification.entitie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER")
public class User {
    @Id
    @GeneratedValue
    private Long id ;
    private String login;
    private String password ;
    private boolean enabled ;
    @Column(name = "loginattempts")
    private int loginAttempts ;
    @ManyToOne
    private Group group;



}


