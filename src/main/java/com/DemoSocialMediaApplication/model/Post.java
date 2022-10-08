package com.DemoSocialMediaApplication.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity
@Table(name = "post")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(
            name = "id",
            columnDefinition = "uuid default gen_random_uuid()"
    )
    @JsonIgnore
    private UUID id;

    @Column(name = "description", columnDefinition = "TEXT")
    @Size(min = 5)
    @NotNull
    @NotEmpty
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private UserDetail userDetail;
}