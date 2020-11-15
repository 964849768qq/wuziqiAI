package com.yyt.wuziqi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QiXing {
    private int huo4=0;
    private int mian4=0;
    private int huo3=0;
    private int mian3=0;
    private int huo2=0;
    private int mian2=0;
    private int si4=0;
    private int si3=0;
    private int si2=0;
    private int victory=0;
}
