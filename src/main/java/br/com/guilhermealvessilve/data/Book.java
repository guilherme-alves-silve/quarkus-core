package br.com.guilhermealvessilve.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    private Long id;

    @NotBlank(message = "The name cannot be null")
    private String name;

    @NotBlank(message = "The author cannot be null")
    private String author;

    @Min(value = 1, message = "The minimum number of pages must be one")
    private int pages;
}
