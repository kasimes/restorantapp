package com.ornek.restorant.restorantapp.service.strategy;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class YuzdeIndirim implements IndirimStratejisi {

    private final BigDecimal yuzde;

    public YuzdeIndirim(@Value("${indirim.yuzde:10}") double yuzde) {
        this.yuzde = BigDecimal.valueOf(yuzde);
    }

    @Override
    public BigDecimal indirimUygula(BigDecimal toplamTutar) {
        BigDecimal indirim = toplamTutar.multiply(yuzde).divide(BigDecimal.valueOf(100));
        return toplamTutar.subtract(indirim);
    }
}

