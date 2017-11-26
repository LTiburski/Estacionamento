/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.estacionamento.business;

import br.edu.estacionamento.dao.VeiculoDAO;
import br.edu.estacionamento.entity.Veiculo;
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
public class VeiculoRN implements Serializable {

    VeiculoDAO veiculoDAO = new VeiculoDAO();

    Veiculo veiculo;
    ArrayList<Veiculo> lista;

    @PostConstruct
    public void posConstrutor() {
        veiculo = new Veiculo();
        lista = listaVeiculos();
    }

    public ArrayList<Veiculo> listaVeiculos() {
        return veiculoDAO.listaVeiculos();
    }

    public String cadastrarVeiculo() {
        if (veiculoDAO.cadastraVeiculo(veiculo)) {
            return "index?faces-redirect=true";
        }
        return null;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public ArrayList<Veiculo> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Veiculo> lista) {
        this.lista = lista;
    }
}
