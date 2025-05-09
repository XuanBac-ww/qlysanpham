package com.dev.qlisanpham.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class KhoHangHoaId implements Serializable {
    private Long kho;
    private Long hangHoa;

    // equals() and hashCode()
}
