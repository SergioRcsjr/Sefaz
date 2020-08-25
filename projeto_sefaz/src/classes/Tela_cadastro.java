package classes;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import javax.swing.JMenu;

public class Tela_cadastro extends JFrame {

	private JPanel contentPane;
	private JTextField tfId;
	private JTextField tfUsuario;
	private JPasswordField pfSenha;
	private JLabel lblEmail;
	private JTextField tfEmail;
	private JButton btnAbrir;
	private JTextField tfBusca;
	private JLabel lblInsiraId;
	private JTable tbDados;
	private JButton btnAtualizar;
	private JButton btnExcluir;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela_cadastro frame = new Tela_cadastro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Tela_cadastro() {
		setResizable(false);
		setTitle("Cadastro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 628, 725);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(12, 71, 56, 16);
		contentPane.add(lblId);
		
		JLabel lblUsurio = new JLabel("Usu\u00E1rio");
		lblUsurio.setBounds(12, 146, 56, 16);
		contentPane.add(lblUsurio);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(12, 209, 56, 16);
		contentPane.add(lblSenha);
		
		tfId = new JTextField();
		tfId.setEditable(false);
		tfId.setBounds(80, 68, 116, 22);
		contentPane.add(tfId);
		tfId.setColumns(10);
		
		tfUsuario = new JTextField();
		tfUsuario.setBounds(80, 143, 320, 22);
		contentPane.add(tfUsuario);
		tfUsuario.setColumns(10);
		
		pfSenha = new JPasswordField();
		pfSenha.setBounds(81, 206, 184, 22);
		contentPane.add(pfSenha);
		
		tfEmail = new JTextField();
		tfEmail.setBounds(80, 143, 320, 22);
		contentPane.add(tfEmail);
		tfEmail.setColumns(10);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				
				if (tfUsuario.getText().equals("") || pfSenha.getText().equals("") || tfEmail.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Preencha todos os dados");
				}
				else {
				
				try {
			
				Connection con = Conexao.faz_conexao();
				String sql = "insert into dados_senhas(usuario, senha, email) values(?, ?, ?) ";
				
				PreparedStatement stmt = con.prepareStatement(sql);
				
				stmt.setString(1, tfUsuario.getText());
				stmt.setString(2, pfSenha.getText());
				stmt.setString(3, tfEmail.getText());
				stmt.execute();
				
				stmt.close();
				con.close();
				JOptionPane.showMessageDialog(null, "Usuário cadastrado");
				
				tfUsuario.setText("");
				pfSenha.setText("");
				tfEmail.setText("");
				
				} catch (SQLException e1) {
				e1.printStackTrace();
				}
					
			}
			}
		});
		
		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		tfEmail.setBounds(80, 272, 320, 22);
		contentPane.add(tfEmail);
		btnSalvar.setBounds(80, 347, 97, 25);
		contentPane.add(btnSalvar);
		
		lblEmail = new JLabel("Email");
		lblEmail.setBounds(12, 275, 56, 16);
		contentPane.add(lblEmail);
		
		btnAbrir = new JButton("Abrir");
		btnAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (tfBusca.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o ID");
				}
				else {
					
				try {
					Connection con = Conexao.faz_conexao();
					
					String sql = "select * from dados_senhas where id like ?";
					
					PreparedStatement stmt = con.prepareStatement(sql);
					
					stmt.setString(1, "%" + tfBusca.getText());
					
					ResultSet rs = stmt.executeQuery();
					
					while (rs.next()) {
						tfId.setText(rs.getString("id"));
						tfUsuario.setText(rs.getString("usuario"));
						pfSenha.setText(rs.getString("senha"));
						tfEmail.setText(rs.getString("email"));
					}
					
					stmt.close();
					con.close();
				
					
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				
				}
			}
		});
		btnAbrir.setBounds(325, 396, 97, 25);
		contentPane.add(btnAbrir);
		
		tfBusca = new JTextField();
		tfBusca.setBounds(191, 397, 116, 22);
		contentPane.add(tfBusca);
		tfBusca.setColumns(10);
		
		lblInsiraId = new JLabel("Insira o ID");
		lblInsiraId.setBounds(80, 400, 85, 16);
		contentPane.add(lblInsiraId);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(80, 482, 486, 118);
		contentPane.add(scrollPane);
		
		tbDados = new JTable();
		tbDados.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Usu\u00E1rio", "Senha"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tbDados.getColumnModel().getColumn(0).setPreferredWidth(60);
		tbDados.getColumnModel().getColumn(1).setPreferredWidth(181);
		tbDados.getColumnModel().getColumn(2).setPreferredWidth(165);
		scrollPane.setViewportView(tbDados);
		
		JButton btnListarDados = new JButton("Listar dados");
		btnListarDados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Connection con = Conexao.faz_conexao();
					
					String sql = "Select *from dados_senhas";
					
					PreparedStatement stmt = con.prepareStatement(sql);
					
					ResultSet rs = stmt.executeQuery();
					
					DefaultTableModel modelo = (DefaultTableModel) tbDados.getModel();
					modelo.setNumRows(0);
					
					while (rs.next()) {
						modelo.addRow(new Object[] {rs.getString("id"),rs.getString("usuario"),rs.getString("senha")});
						
					}
					
					rs.close();
					con.close();
					
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				
			}
		});
		btnListarDados.setBounds(80, 444, 137, 25);
		contentPane.add(btnListarDados);
		
		btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (tfId.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o ID");
				}
				else {
					
				try {
					Connection con = Conexao.faz_conexao();
					
					String sql = "update dados_senhas set usuario= ?, senha= ?, email= ? where id= ?";
					
					PreparedStatement stmt = con.prepareStatement(sql);
					
					stmt.setString(1, tfUsuario.getText());
					stmt.setString(2, pfSenha.getText());
					stmt.setString(3, tfEmail.getText());
					stmt.setString(4, tfId.getText());
					
					stmt.execute();
					
					stmt.close();
					con.close();
					
					JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
					
					tfUsuario.setText("");
					pfSenha.setText("");
					tfEmail.setText("");
					
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			}
			}
		});
		btnAtualizar.setBounds(210, 347, 97, 25);
		contentPane.add(btnAtualizar);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tfId.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o ID");
				}
				else {
		
				try {
					Connection con = Conexao.faz_conexao();
					
					String sql = "delete from dados_senhas where id=?";
					
					PreparedStatement stmt = con.prepareStatement(sql);
					
					stmt.setString(1, tfId.getText());
					
					stmt.execute();
					
					stmt.close();
					con.close();
					
					JOptionPane.showMessageDialog(null, "Usuário excluído");
					
					
					tfUsuario.setText("");
					pfSenha.setText("");
					tfEmail.setText("");
					
					
				} catch (SQLException e1) {

					e1.printStackTrace();
				}
				
			}
			}
		});
		btnExcluir.setBounds(325, 347, 97, 25);
		contentPane.add(btnExcluir);
	}
}
