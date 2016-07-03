package util;

import com.toedter.calendar.IDateEvaluator;
import hibernate.Operations;

import java.awt.*;
import java.util.*;
import java.util.List;


/**
 * Created by JuanAJ on 03/07/2016.
 */

public class MarcarFechas implements IDateEvaluator {
    private List<Date> listaVacas = new ArrayList<>();

    public MarcarFechas(List<Date> listaVacas){
        this.listaVacas = listaVacas;
    }

    @Override
    public boolean isSpecial(Date date) {
        return listaVacas.contains(date);
    }

    @Override
    public Color getSpecialForegroundColor() {
        return Color.RED;
    }

    @Override
    public Color getSpecialBackroundColor() {
        return null;
    }

    @Override
    public String getSpecialTooltip() {
        return "Dia festivo";
    }

    @Override
    public boolean isInvalid(Date date) {
        return false;
    }

    @Override
    public Color getInvalidForegroundColor() {
        return Color.WHITE;
    }

    @Override
    public Color getInvalidBackroundColor() {
        return Color.BLACK;
    }

    @Override
    public String getInvalidTooltip() {
        return null;
    }

}
