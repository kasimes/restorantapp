package com.ornek.restorant.restorantapp.service.strategy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class EsikIndirim implements IndirimStratejisi {

    private final BigDecimal esikTutar;
    private final BigDecimal indirimMiktari;

    public EsikIndirim(
            @Value("${indirim.esik:100}") double esikTutar,
            @Value("${indirim.miktar:10}") double miktariMiktari
    ) {
        this.esikTutar = BigDecimal.valueOf(esikTutar);
        this.indirimMiktari = BigDecimal.valueOf(miktariMiktari);
    }

    @Override
    public BigDecimal indirimUygula(BigDecimal toplamTutar) {
        if (toplamTutar.compareTo(this.esikTutar) >= 0) {
            return toplamTutar.subtract(indirimMiktari);
        }
        return toplamTutar;
    }
}

