package com.example.crud.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "`user`")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "`username`")
    private String username;
    
    @Column(name = "`name`")
    private String name;
    
    @Column(name = "`lastname`")
    private String lastname;
    
    @Column(name = "`age`")
    private int age;
    
    @Column(name = "`birthDate`")
    private LocalDate birthDate;
    
    @Column (name = "`activo`")
    private boolean activo;
    
}
