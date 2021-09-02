package com.vnpt.eoffice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "User")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User extends AuditingAbstractEntity implements Serializable {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "hidden_password", nullable = true)
    private String hiddenPassword;

    @Column(name = "phone_number", nullable = true, length = 16)
    private String phoneNumber;

    @Column(name = "email", nullable = true, length = 50)
    private String email;

    @Column(name = "url_avatar")
    private String avatar;

    @Column(name = "role")
    private String role;

    @Column(name = "status", length = 1)
    private Integer status;

    @Column(name = "address", nullable = true)
    private String address;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "dob")
    private LocalDate dob;

    @Column(name = "gender")
    private String gender;

    @Column(name = "username", nullable = false, unique = true)
    private String username;
}
