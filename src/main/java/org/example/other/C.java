package org.example.other;

import lombok.Data;

@Data
public class C {

    public C(String string, C c) {
        this.string = string;
        this.c = c;
    }

    private String string;
    private C c;
}
