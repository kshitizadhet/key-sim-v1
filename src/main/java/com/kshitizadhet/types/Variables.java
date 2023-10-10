package com.kshitizadhet.types;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Variables {
    private int delay;
    private String data;

    public Variables(final int delay, final String data) {
        this.delay = delay;
        this.data = data;
    }
}
