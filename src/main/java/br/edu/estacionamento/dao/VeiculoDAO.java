/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.estacionamento.dao;

import br.edu.estacionamento.entity.Marca;
import br.edu.estacionamento.entity.Veiculo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Lucas Fernando
 */
public class VeiculoDAO {

    public VeiculoDAO() {
    }

    public boolean cadastraVeiculo(Veiculo veiculo) {
        Connection conexao = null;
        PreparedStatement ps = null;

        String sql = "INSERT INTO veiculos (placa, marca, modelo, cor) VALUES (?, ?, ?, ?)";

        try {
            conexao = Database.getInstance().getConnection();
            ps = conexao.prepareStatement(sql);
            ps.setString(1, veiculo.getPlaca());
            ps.setInt(2, veiculo.getMarca().getId());
            ps.setString(3, veiculo.getModelo());
            ps.setString(4, veiculo.getCor());

            if (ps.executeUpdate() > 0) { // Verifica quantas linhas foram alteradas na query
                return true;
            }
        } catch (Exception ex) {
            System.out.println("[VeiculoDAO]");
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conexao != null) {
                    conexao.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    public ArrayList<Veiculo> listaVeiculos() {
        Connection conexao = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT v.*, m.descricao FROM veiculos v INNER JOIN marcas m ON v.marca = m.id";

        try {
            conexao = Database.getInstance().getConnection();
            ps = conexao.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.isBeforeFirst()){
                ArrayList<Veiculo> resultado = new ArrayList<>();
                while (rs.next()){
                    Veiculo v = new Veiculo();
                    v.setId(rs.getInt("id"));
                    v.setCor(rs.getString("marca"));
                    v.setModelo(rs.getString("modelo"));
                    v.setPlaca(rs.getString("placa"));
                    
                    // Dados de marca
                    Marca m = new Marca();
                    m.setDescricao(rs.getString("m.descricao"));
                    
                    v.setMarca(m);
                    
                    resultado.add(v);
                }
                return resultado;
            }
        } catch (Exception ex) {
            System.out.println("[VeiculoDAO] listaVeiculos: " + ex.toString());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null){
                    ps.close();
                }
                if (conexao != null){
                    conexao.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

}
