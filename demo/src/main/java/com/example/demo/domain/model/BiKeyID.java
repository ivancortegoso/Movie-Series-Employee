package com.example.demo.domain.model;

import java.io.Serializable;
import lombok.Data;

@Data
public class BiKeyID implements Serializable {
    private Long id1;
    private Long id2;
}
