package de.modulware.controller;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.modulware.dao.KitaDao;
import de.modulware.domain.Kita;

@Component
public class KitaEditor extends PropertyEditorSupport {

    private @Autowired KitaDao kitaDao;

    @Override
    public void setAsText(String text) {
        Kita kita = this.kitaDao.find(Long.valueOf(text));

        this.setValue(kita);
    }

}