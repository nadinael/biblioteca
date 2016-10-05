package br.ueg.biblio.core;

import br.ueg.biblio.cfg.BiblioCfg;
import br.ueg.biblio.files.BiblioCfgFileManager;
import br.ueg.biblio.model.Emprestimo;
import br.ueg.biblio.model.interfaces.Injetavel;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class ControleDataDeEmprestimo implements Injetavel {

    public ControleDataDeEmprestimo() {
    }

    public static Calendar gerarDevolucao(Emprestimo emprestimo) {
        LocalDateTime hoje = conCalendarParaLocalDateTime(emprestimo.getDataEmprestimo());
        BiblioCfg emprestimoCfg = BiblioCfgFileManager.getBiblioCfg();
        LocalDateTime devolucao = hoje.plusDays(emprestimoCfg.getPrazo() + emprestimo.getDiasAdicionais());

        if (emprestimoCfg.isSabadoUltil()) {
            if (devolucao.getDayOfWeek() == DayOfWeek.SUNDAY) {
                devolucao = devolucao.plusDays(1);
            }
        } else {
            while ((devolucao.getDayOfWeek() == DayOfWeek.SATURDAY)
                    || (devolucao.getDayOfWeek() == DayOfWeek.SUNDAY)) {
                devolucao = devolucao.plusDays(1);
            }
        }

        return conLocalDateTimeParaCalendar(devolucao);
    }
    
    
    

    public static Date conLocalDateTimeParaDate(LocalDateTime ldt) {
        return Date.from(ldt.toInstant(ZoneOffset.UTC));
    }

    public static Calendar conLocalDateTimeParaCalendar(LocalDateTime ldt) {
        Instant instante = ldt.toInstant(ZoneOffset.UTC);
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(instante.toEpochMilli());
        return cal;
    }

    public static LocalDate conLocalDateTimeParaLocalDate(LocalDateTime ldt) {
        return ldt.toLocalDate();
    }

    public static Date conLocalDateParaDate(LocalDate ld) {
        return Date.from(ld.atStartOfDay().toInstant(ZoneOffset.UTC));
    }

    public static Calendar conLocalDateParaCalendar(LocalDate ld) {
        Instant instante = ld.atStartOfDay().toInstant(ZoneOffset.UTC);
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(instante.toEpochMilli());
        return cal;
    }

    public static LocalDateTime conLocalDateParaLocalDateTime(LocalDate ld) {
        return ld.atStartOfDay();
    }

    public static LocalDate conDateParaLocalDate(Date date) {
        return LocalDate.ofEpochDay(date.getTime());
    }

    public static LocalDateTime conDateParaLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneOffset.UTC);
    }

    public static Calendar conDateParaCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date.getTime());
        return calendar;
    }

    public static Date conCalendarParaDate(Calendar calendar) {
        return calendar.getTime();
    }

    public static LocalDate conCalendarParaLocalDate(Calendar calendar) {
        return LocalDate.ofEpochDay(calendar.toInstant().toEpochMilli());
    }

    public static LocalDateTime conCalendarParaLocalDateTime(Calendar calendar) {
        return LocalDateTime.ofInstant(calendar.toInstant(), ZoneOffset.UTC);
    }

}
