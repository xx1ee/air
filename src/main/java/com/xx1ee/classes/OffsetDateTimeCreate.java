package com.xx1ee.classes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OffsetDateTimeCreate implements Serializable {
    Integer year;
    Integer month;
    Integer dayOfMonth;
    Integer hour;
    Integer minute;
    Integer second;
    Integer nanoOfSecond;
    Integer offset;
}
