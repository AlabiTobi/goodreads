package com.ordinaka.goodreads.models;

import com.ordinaka.goodreads.models.enums.AccountStatus;
import com.ordinaka.goodreads.models.enums.Gender;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name= "users")
@Validated
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class User {
    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    @SequenceGenerator(
            name = "user_id_sequence",
            sequenceName = "user_id_sequence"
    )

    @Id
    @Column(name = "user_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "user_id_sequence")
    private Long id;
    @NonNull
    @NotBlank
    private String firstName;

    @NonNull
    @NotBlank
    private String lastName;

    @Email
    @NonNull
    @NotBlank
    @Column(unique = true)
    private String email;

    @NonNull
    @NotBlank
    private String password;
    private LocalDateTime dob;

    private LocalDateTime dateJoined;
    private String Location;

    @Enumerated(value= EnumType.STRING)
    private AccountStatus accountStatus;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;



}
