package com.connection.databaseconnection.evento.convidado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
@Controller
public class ConvidadoController {
    @Autowired
    ConvidadoServices service;
    @CrossOrigin
    @GetMapping("/export")
    public void exportToCsv(HttpServletResponse response) throws IOException {

        response.setContentType("text/csv");
        String fileName = "lista_de_convidados.csv";

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=" + fileName;

        response.setHeader(headerKey, headerValue);

        List<Convidado> listConvidado = service.listAll();
        ICsvBeanWriter csvWrite = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = {"Nome","Email"};
        String[] nameMapping = {"nomeConvidado", "email"};

        csvWrite.writeHeader((csvHeader));

        for (Convidado convidado : listConvidado) {
            csvWrite.write(convidado, nameMapping);
        }
        csvWrite.close();
    }
}
