package org.sqli.authentification.entitie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
@Table(name="group")
public class Group {
    @Id
    private Long id ;
    private String name ;
    @OneToMany(mappedBy = "group")
    private List<User> users;
}
