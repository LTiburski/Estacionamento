/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.estacionamento.business;

import br.edu.estacionamento.dao.ClienteDAO;
import br.edu.estacionamento.entity.Cliente;
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
public class ClienteRN implements Serializable{
    ClienteDAO clienteDAO = new ClienteDAO();
    
    // Propriedades do bean
    ArrayList<Cliente> lista;
    Cliente cliente;
    
    @PostConstruct
    public void posConstrutor(){
        lista = listaClientes();
        cliente = new Cliente();
    }
    
    public ArrayList<Cliente> listaClientes(){
        return clienteDAO.lista();
    }

    public String cadastrarCliente(){
        if(clienteDAO.insereCliente(cliente)){
            return "index?faces-redirect=true";
        }
        return null;
    }
    
    public ArrayList<Cliente> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Cliente> lista) {
        this.lista = lista;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    
}
