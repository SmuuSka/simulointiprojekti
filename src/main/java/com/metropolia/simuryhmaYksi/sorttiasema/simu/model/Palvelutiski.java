package com.metropolia.simuryhmaYksi.sorttiasema.simu.model;

import com.metropolia.simuryhmaYksi.sorttiasema.eduni.distributions.ContinuousGenerator;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.framework.Tapahtumalista;

public class Palvelutiski extends Palvelupiste {
    // moro
    public Palvelutiski(ContinuousGenerator generator, Tapahtumalista tapahtumalista, TapahtumanTyyppi tyyppi) {
        super(generator, tapahtumalista, tyyppi);
    }
}
