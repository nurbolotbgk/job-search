package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactInfoDto {

    private Long id;
    @NotBlank(message = "{validation.contact.value.notblank}")
    private String contactValue;
    @NotNull(message = "{validation.contact.type.notnull}")
    private Integer typeId;
}