/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.estacionamento.dao;

import br.edu.estacionamento.entity.Cliente;
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
public class ClienteDAO {

    public ClienteDAO() {
    }

    public boolean insereCliente(Cliente cliente){
        Connection conexao = null;
        PreparedStatement ps = null;
        
        String sql = "INSERT INTO clientes (nome, cgc, endereco, cep) VALUES (?, ?, ?, ?)";
        
        try{
            conexao = Database.getInstance().getConnection();
            ps = conexao.prepareStatement(sql);
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getCgc());
            ps.setString(3, cliente.getEndereco());
            ps.setString(4, cliente.getCep());
            
            if (ps.executeUpdate() > 0){
                return true;
            }
        } catch (Exception ex){
            System.out.println("[ClienteDAO] insereCliente: " + ex.toString());
        } finally {
            try{
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
    
    public ArrayList<Cliente> lista() {
        Connection conexao = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ResultSet rsVeiculos = null;

        String sqlClientes = "SELECT * FROM cliente c";
        String sqlVeiculos = "SELECT v.id, v.placa, v.modelo, v.cor, m.id, m.descricao "
                + "FROM veiculos v INNER JOIN marcas m ON v.marca = m.id";

        try {
            conexao = Database.getInstance().getConnection();
            ps = conexao.prepareStatement(sqlClientes);
            rs = ps.executeQuery();

            if (rs.isBeforeFirst()) {
                ArrayList<Cliente> resultado = new ArrayList<>();
                while (rs.next()) {
                    Cliente c = new Cliente();
                    c.setId(rs.getInt("id"));
                    c.setNome(rs.getString("nome"));
                    c.setCgc(rs.getString("cgc"));
                    c.setEndereco(rs.getString("endereco"));
                    c.setCep(rs.getString("cep"));

                    ps = conexao.prepareStatement(sqlVeiculos);
                    rsVeiculos = ps.executeQuery();

                    if (rs.isBeforeFirst()) {
                        ArrayList<Veiculo> listaVeiculos = new ArrayList<>();
                        while (rsVeiculos.next()) {
                            Veiculo v = new Veiculo();
                            v.setId(rsVeiculos.getInt(rs.getInt("id")));
                            v.setPlaca(rsVeiculos.getString("placa"));
                            v.setModelo(rsVeiculos.getString("modelo"));
                            v.setCor(rsVeiculos.getString("cor"));
                            
                            Marca m = new Marca();
                            
                            m.setId(rsVeiculos.getInt("m.id"));
                            m.setDescricao(rsVeiculos.getString("m.descricao"));
                            
                            v.setMarca(m);
                        }
                        c.setVeiculos(listaVeiculos);
                    }
                    resultado.add(c);
                }
                return resultado;
            }
        } catch (Exception ex) {
            System.out.println("[ClienteDAO] lista: " + ex.toString());
        } finally {
            try {
                if (rsVeiculos != null) {
                    rsVeiculos.close();
                }
                if (rs != null) {
                    rs.close();
                }
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
        return null;
    }
}
