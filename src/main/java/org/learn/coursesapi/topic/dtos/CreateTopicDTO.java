package org.learn.coursesapi.topic.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;


@Setter
@Getter
@NoArgsConstructor
public class CreateTopicDTO {
    @NotNull(message = "Name is mandatory")
    @NotBlank(message = "Name is mandatory")
    @Length(min = 3, message = "Name should have at least 3 characters")
    private String name;

    @NotNull(message = "Description is mandatory")
    @NotBlank(message = "Description is mandatory")
    @Length(min = 10, message = "Description should have at least 10 characters")
    private  String description;
}
