package com.ifood.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class KitchenDTO {
    private Long id;

    @NotNull(message = "Name is required")
    private String name;

    public KitchenDTO() {
    }

    public KitchenDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
