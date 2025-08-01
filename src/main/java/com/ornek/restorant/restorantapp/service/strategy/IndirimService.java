package com.ornek.restorant.restorantapp.service.strategy;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class IndirimService {

    private IndirimStratejisi indirimStratejisi;

    public void setIndirimStratejisi(IndirimStratejisi indirimStratejisi) {
        this.indirimStratejisi = indirimStratejisi;
    }
    public BigDecimal indirimUygula(BigDecimal toplamTutar) {
        if(indirimStratejisi==null){
            return toplamTutar;
        }
        return indirimStratejisi.indirimUygula(toplamTutar);
    }
}
