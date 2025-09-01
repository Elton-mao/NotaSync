package com.br.compol.getnfe.core.model.usuario;

import com.br.compol.getnfe.enums.GruposDeUsuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Usuarios")
public class Usuario {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="login" ,nullable = false,length = 50, unique = true)
    private String login;
    
    @Column(name = "nome", length=80 )
    private String nome;

    @Column(name = "email", length = 150,unique = true)
    private String email;
    
    @Column(name ="grupoDeUsuario", nullable = false)
    @Enumerated(EnumType.STRING)
    private GruposDeUsuario grupo; 

    @Column(name = "senha",length = 100)
    private String senha; 
    

    

}
