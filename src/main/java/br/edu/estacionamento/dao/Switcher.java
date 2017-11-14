/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.estacionamento.dao;

import br.edu.estacionamento.configs.ServletContextInfo;
import br.edu.estacionamento.entity.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Lucas Fernando
 */
public class Switcher {

    public Switcher() {
    }
    
    public boolean autenticaUsuario(Usuario usuario){
        Connection conexao = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String sql = "SELECT * FROM bds WHERE usuario = ? AND senha = MD5(?)";
        
        try{
            conexao = Database.getInstance().getConnection();
            ps = conexao.prepareStatement(sql);
            ps.setString(1, usuario.getUsuario());
            ps.setString(2, usuario.getSenha());
            
            rs = ps.executeQuery();
            
            if (rs.next()){
                ServletContextInfo.setFullServerName(rs.getString("host"));
                return true;
            }
        } catch (Exception ex){
            System.out.println("[Swithcer] autenticaUsuario: " + ex.toString());
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
        return false;
    }
    
}
