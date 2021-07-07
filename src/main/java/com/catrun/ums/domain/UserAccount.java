package com.catrun.ums.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Accessors(chain = true)
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@Entity
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Username can't be blank. ")
    @Size(min = 3, max = 16, message = "Username length should be between 3 and 16 characters. ")
    @Pattern(regexp = "^[a-zA-Z]{3,16}$", message = "The username must be only Latin characters. ")
    @Column(nullable = false, unique = true, length = 16)
    private String username;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @NotBlank(message = "Name can't be blank. ")
    @Size(min = 1, max = 16, message = "Name should be between 1 and 16 characters. ")
    @Pattern(regexp = "^[a-zA-Z]{1,16}$", message = "The fist name must be only Latin characters. ")
    @Column(nullable = false, length = 16)
    private String firstName;

    @NotBlank(message = "First name can't be blank. ")
    @Size(min = 1, max = 16, message = "Last name should be between 1 and 16 characters. ")
    @Pattern(regexp = "^[a-zA-Z]{1,16}$", message = "The last name must be only Latin characters. ")
    @Column(nullable = false, length = 16)
    private String lastName;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createdAt;
}
