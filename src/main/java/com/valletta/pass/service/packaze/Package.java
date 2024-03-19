package com.valletta.pass.service.packaze;

import javax.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Package {

    private Integer packageSeq;
    private String packageName;
}
