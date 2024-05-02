package org.example.utility.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class GeneralResponse<T> {

    @JsonProperty("data")
    private T data;

    @JsonProperty("status")
    private HttpStatus status = HttpStatus.OK;

    public GeneralResponse(T data) {
        this.data = data;
    }
}
