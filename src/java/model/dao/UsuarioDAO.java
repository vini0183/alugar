package model.dao;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.bean.Usuario;

public class UsuarioDAO {
    
    public Usuario validarLogin(Usuario user) {
        Usuario usuarios = new Usuario();
        
        try {
            
            Connection conexao = Conexao.conectar();
            PreparedStatement stmt = null;
            ResultSet rs = null;
            
            stmt = conexao.prepareStatement("select * from usuario where email = ? and senha = ?");
            
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getSenha());
            
            rs = stmt.executeQuery();
            
            if(rs.next()) {
                usuarios.setId_usuario(rs.getInt("id_usuario"));
                usuarios.setNome(rs.getString("nome"));
                usuarios.setEmail(rs.getString("email"));
                usuarios.setSenha(rs.getString("senha"));
            }
            
            rs.close();
            stmt.close();
            conexao.close();
            
        }catch(Exception e) {
            e.printStackTrace();
        }
        return usuarios;
    }
    
    public void cadastroUsuario(Usuario user) {
        
        try {
            Connection conexao = Conexao.conectar();
            PreparedStatement stmt = null;
            
            stmt = conexao.prepareStatement("insert into usuario(nome,email,senha) values(?,?,?)");
            
            stmt.setString(1, user.getNome());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getSenha());
            
            stmt.executeUpdate();
            
            stmt.close();
            conexao.close();
            
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
