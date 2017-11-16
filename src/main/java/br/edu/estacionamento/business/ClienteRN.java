/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.estacionamento.business;

import br.edu.estacionamento.dao.ClienteDAO;
import br.edu.estacionamento.entity.Cliente;
import java.util.ArrayList;

/**
 *
 * @author Lucas Fernando
 */
public class ClienteRN {
    ClienteDAO clienteDAO = new ClienteDAO();
    
    public ArrayList<Cliente> listaClientes(){
        return clienteDAO.lista();
    }
}
