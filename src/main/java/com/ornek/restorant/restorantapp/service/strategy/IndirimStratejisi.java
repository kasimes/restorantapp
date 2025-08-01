package com.ornek.restorant.restorantapp.service.strategy;

import java.math.BigDecimal;

public interface IndirimStratejisi {
    BigDecimal indirimUygula(BigDecimal toplamTutar);
        
    
}
