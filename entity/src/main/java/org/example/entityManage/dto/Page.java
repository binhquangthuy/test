package org.example.entityManage.dto;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Page {
    @Min(value = 1, message = "Page min is 1")
    private Integer page = 1;

    @Min(value = 1, message = "Page size min is 1")
    private Integer pageSize = 10;

    public Page(Integer page, Integer pageSize) {
        if (page == null) this.page = 1;
        else this.page = page;
        if (pageSize == null) this.pageSize = 10;
        else this.pageSize = pageSize;
    }
}
