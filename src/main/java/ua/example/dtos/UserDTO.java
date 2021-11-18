package ua.example.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import ua.example.annotations.validation.PhoneNumber;

import javax.validation.constraints.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private int id;

    @NotEmpty(message = "Name field can not be empty")
    @Pattern(message = "Name must start with capital letter", regexp = "[A-Z][a-z]*")
    @Size(min = 2, max = 30, message = "Size must be between 2 to 30")
    private String name;

    @NotEmpty(message = "Surname field can not be empty")
    @Pattern(message = "Surname must start with capital letter", regexp = "[A-Z][a-z]*")
    @Size(min = 2, max = 30, message = "Size must be between 2 to 30")
    private String surname;


    @NotNull(message = "Age field can not be empty")
    @Range(min = 18, max = 150, message = "Age should be from 18 to 150")
    private Integer age;

    @PhoneNumber(message = "Phone number should be formatted for the pattern \"+380-(yy)-xxx-xx-xx\"")
    private String phoneNumber;
}


// name = null
// not null = false
// not empty = false
// not blank = false

// name = ""
// not null = true
// not empty = false
// not blank = false


// name = " sdas"
// not null = true
// not empty = true
// not blank = false

// name = "fdsfs"
// not null = true
// not empty = true
// not blank = true

