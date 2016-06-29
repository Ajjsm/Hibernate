package util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by JuanAJ on 26/06/2016.
 */
public class Fecha {
    public static String formatFecha(Date fecha) {
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        return format.format(fecha);
    }

    public static String parseFecha(Date fecha) throws ParseException {
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        return format.format(fecha);
    }
}
