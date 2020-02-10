package com.belatrix.references.patterns.commands;

import com.belatrix.references.patterns.exceptions.BusinessException;
import com.belatrix.references.patterns.exceptions.TechnicalException;

public interface PageProcessService{

    void pageProcessInBackGround() throws BusinessException, TechnicalException;
}
