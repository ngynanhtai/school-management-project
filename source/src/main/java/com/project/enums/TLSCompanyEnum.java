package com.project.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TLSCompanyEnum {
    COMPANY_A("company_a", "Công ty TNHH A"),
    COMPANY_B("company_B", "Công ty TNHH B")
    ;

    private final String code;
    private final String name;

}
