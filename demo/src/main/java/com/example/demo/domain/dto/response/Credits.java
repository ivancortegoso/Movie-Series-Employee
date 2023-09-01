package com.example.demo.domain.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Credits {
    private int id;
    private List<Cast> castList;
}
