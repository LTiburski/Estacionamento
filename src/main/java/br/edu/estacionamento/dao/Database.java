/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.estacionamento.dao;
import br.edu.estacionamento.configs.ServletContextInfo;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.dbcp2.BasicDataSource;
/**
 *
 * @author Lucas Fernando
 */
public class Database {

    private static Database instance; // referencia para a instancia
    private BasicDataSource ds; // pool de conexoes, responsável pela gestão da conexao
    static ServletContextInfo sci = new ServletContextInfo();

    private Database() {
        ds = new BasicDataSource();
        ds.setDriverClassName(sci.getDriver());
        ds.setUsername(sci.getUsername());
        ds.setPassword(sci.getPassword());
        ds.setUrl(sci.getFullServerName());
        // tunning de pool
        ds.setMinIdle(5); // tempo minimo que deve ficar sem utilização antes de se marcar para remoção do pool
        ds.setMaxIdle(40); // tempo maximo que deve ficar sem utilização antes de se marcar para remoção do pool
        ds.setMaxOpenPreparedStatements(180); // número maximo de preparedstatements abertos por conexao
        // as linhas abaixo garantem que a conexão esteja sempre correta para ser passada ás classes solicitantes
        ds.setValidationQuery("select 1"); //toda vez que precisar validar se a conexao está ok ira rodar esta query no sgdb
        ds.setTestOnBorrow(true); // testar a conexao com a query toda vez que alguem pedir uma conexao?
        ds.setTestOnCreate(true); // testar a conexao com a query toda vez que uma nova conexao for criada?
        ds.setTestWhileIdle(true); // testar a conexao quando o iddle dela for atingido?
        ds.setTestOnReturn(false); // testar a conexão quando for retornada ao pool? (Nao precisa, pois testamos quando enviamos ao usuario
        ds.setMaxWaitMillis(10); // tempo maximo a se aguardar pela conexao
        ds.setMaxTotal(-1);
    }

    public static Database getInstance() throws IOException, SQLException, PropertyVetoException {
        // singleton de retorno desta classe
        // singleton utilizando lazy initialization. Atenção a isso quando a utilizar. Cuidado com threads
        if (null == instance) {
            instance = new Database();
            return instance;
        } else {
            return instance;
        }
    }

    public Connection getConnection() {
        try {
            // pego uma conexao diretamente do pool de conexoes
            Connection connection = this.ds.getConnection();
            if (connection.isValid(200)) {
                return connection;
            } else {
                return null;
            }
        } catch (Exception e) {
            // Sempre imprimir o stackstrace. 
            // dessa forma fica mais facil achar a raiz dos problemas
            e.printStackTrace();
        }
        return null;
    }
}
