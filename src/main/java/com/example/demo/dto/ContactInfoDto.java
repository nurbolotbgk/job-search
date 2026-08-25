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

    @NotBlank(message = "Контактные данные не должны быть пустыми")
    private String contactValue;

    @NotNull(message = "Тип контакта обязателен")
    private Integer typeId;
}