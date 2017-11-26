/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.estacionamento.business;

import br.edu.estacionamento.entity.Usuario;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Lucas Fernando
 */
@ManagedBean
@Named
@RequestScoped
public class LoginRN {
    Usuario login;
    
    @PostConstruct
    public void posConstrutor(){
        login = new Usuario();
    }
    
    
}
