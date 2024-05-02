package org.example.utility.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
//@Builder
public class ListResponse<T> {

    @JsonProperty("data")
    private List<T> data;

    @JsonProperty("total")
    private Integer total;

    @JsonProperty("status")
    private HttpStatus status;

    public ListResponse(List<T> data, Integer total) {
        this.data = data;
        this.total = total;
        this.status = HttpStatus.OK;
    }
}
