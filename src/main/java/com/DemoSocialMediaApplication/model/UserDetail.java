package com.DemoSocialMediaApplication.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table()
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class  UserDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(
            name = "id",
            columnDefinition = "uuid default gen_random_uuid()"
    )
    private UUID id;

    @Column(name = "first_name", nullable = false)
    @Size(min = 5, message = "Minimum length is 5")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    @Email()
    private String email;

    private LocalDate dob;

    @OneToMany(mappedBy = "userDetail")
    @JsonIgnore
    private List<Post> post;

}