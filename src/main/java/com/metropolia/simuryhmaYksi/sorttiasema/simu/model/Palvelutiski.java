package com.metropolia.simuryhmaYksi.sorttiasema.simu.model;

import com.metropolia.simuryhmaYksi.sorttiasema.eduni.distributions.ContinuousGenerator;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.framework.Tapahtumalista;

public class Palvelutiski extends Palvelupiste {
    public Palvelutiski(ContinuousGenerator generator, Tapahtumalista tapahtumalista, TapahtumanTyyppi tyyppi) {
        super(generator, tapahtumalista, tyyppi);
    }
}
