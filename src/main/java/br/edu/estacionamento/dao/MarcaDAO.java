/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.estacionamento.dao;

import br.edu.estacionamento.entity.Marca;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author Lucas Fernando
 */
public class MarcaDAO {

    public MarcaDAO() {
    }
    
    public boolean cadastraMarca(Marca marca){
        Connection conexao = null;
        PreparedStatement ps = null;
        
        String sql = "INSERT INTO veiculos (descricao) VALUES (?)";
        
        try{
            conexao = Database.getInstance().getConnection();
            ps = conexao.prepareStatement(sql);
            ps.setString(1, marca.getDescricao());
            if (ps.executeUpdate() > 0){
                return true;
            }
        } catch (Exception ex){
            System.out.println("[MarcaDAO] cadastraMarca: " + ex.toString());
        } finally {
            try {
                if (ps != null){
                    ps.close();
                }
                if (conexao != null){
                    conexao.close();
                }
            } catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return false;
    }
    
}
