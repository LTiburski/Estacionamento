/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.estacionamento.dao;

import br.edu.estacionamento.entity.Marca;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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
    
    public ArrayList<Marca> listaMarcas(){
        Connection conexao = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String sql = "SELECT * FROM marcas";
        
        try{
            conexao = Database.getInstance().getConnection();
            ps = conexao.prepareCall(sql);
            rs = ps.executeQuery();
            
            if (rs.isBeforeFirst()){
                ArrayList<Marca> resultado = new ArrayList<>();
                while (rs.next()){
                    Marca m = new Marca();
                    m.setId(rs.getInt("id"));
                    m.setDescricao(rs.getString("descricao"));
                    
                    resultado.add(m);
                }
                return resultado;
            }
            
        } catch (Exception ex){
            System.out.println("[MarcaDAO] listaMarcas: " + ex.toString());
        } finally {
            try{
                if (rs != null){
                    rs.close();
                }
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
        return null;
    }
}
