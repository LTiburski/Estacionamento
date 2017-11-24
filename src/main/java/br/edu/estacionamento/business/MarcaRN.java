/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.estacionamento.business;

import br.edu.estacionamento.dao.MarcaDAO;
import br.edu.estacionamento.entity.Marca;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Lucas Fernando
 */
@ManagedBean
@Named
@ViewScoped
public class MarcaRN implements Serializable {
    
    MarcaDAO marcaDAO = new MarcaDAO();
    
    private Marca marca;
    private ArrayList<MarcaRN> lista;
    
    @PostConstruct
    public void posConstrutor(){
        marca = new Marca();
    }
    
    public String cadastraMarca(){
        if (marcaDAO.cadastraMarca(marca)){
            return "index?redirect=true";
        }
        return null;
    }
    
    public ArrayList<Marca> listaMarcas(){
        return marcaDAO.listaMarcas();
    }
}
