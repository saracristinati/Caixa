package DAO;

import Model.MercadoModel;
import java.sql.Connection;
import ConexaoSQL.Conexao;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class MercadoDAO {

    public void CadastrarProduto(MercadoModel mercadoModel) throws Exception {

        String sql = "INSERT INTO caixa (produto,quantidade, preco_total,preco_unitario) VALUES (?,?,?,?)";

        Connection connection = null;
        PreparedStatement ps = null;

        try {

            connection = Conexao.ConexaoSQL();
            ps = connection.prepareStatement(sql);


            ps.setString(1, mercadoModel.getProduto());
            ps.setInt(2, mercadoModel.getQuantidade());
            ps.setDouble(3, mercadoModel.getPreco_total());
            ps.setDouble(4, mercadoModel.getPreco_unitario());

            ps.execute();

        } finally {

            if (ps != null) {
                ps.close();
            }
            if (connection != null) {
                connection.close();
            }

        }

    }

    public List<MercadoModel> ShowProduto() {

        String sql = "SELECT * FROM caixa";

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ArrayList<MercadoModel> ems = new ArrayList<>();
        ResultSet resultSet = null;

        try {

            connection = Conexao.ConexaoSQL();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                MercadoModel mercadoModel = new MercadoModel();

                mercadoModel.setId_caixa(resultSet.getInt("id_caixa"));
                mercadoModel.setProduto(resultSet.getString("produto"));
                mercadoModel.setQuantidade(resultSet.getInt("quantidade"));
                mercadoModel.setPreco_total(resultSet.getInt("preco_total"));
                mercadoModel.setPreco_unitario(resultSet.getInt("preco_unitario"));

                ems.add(mercadoModel);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return ems;
    }

}
