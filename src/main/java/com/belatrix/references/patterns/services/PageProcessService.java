package com.belatrix.references.patterns.services;

import com.belatrix.references.patterns.exceptions.TechnicalException;

public interface PageProcessService{

    void pageProcessInBackGround() throws Exception, TechnicalException;
}
