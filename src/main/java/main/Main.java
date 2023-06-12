package main;

import models.Pais;
import services.PaisService;

import javax.swing.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            PaisService paisService = new PaisService();

            Pais pais = new Pais();
            pais.setId(7412);
            pais.setNome("Cingapura");
            pais.setAbreviacao("SG");
            pais.setRegistroAcademico("00207623");

            int id = paisService.insert(pais);

            System.out.println("id inserido: " + id);


            // Find All
//            List<Pais> paisList = paisService.findAll();
//            System.out.println(paisList.toString());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
}