package org.sqli.authentification.entitie;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
@Table(name="USER_GROUP")
public class Group {
    @Id
    @Column(name = "ID")
    private Long id ;
    @Column(name = "NAME")
    private String name ;
    @JsonIgnore
    @OneToMany(mappedBy = "group")
    private List<User> users;
}
