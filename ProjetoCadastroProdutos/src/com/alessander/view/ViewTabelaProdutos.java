package com.alessander.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.alessander.model.bean.Produto;
import com.alessander.model.dao.ProdutoDao;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Component;

@SuppressWarnings("serial")
public class ViewTabelaProdutos extends JFrame {

	private JPanel panel;
	private JTable tabela;
	private JTextField txtDescricao;
	private JTextField txtQuantidade;
	private JTextField txtPreco;

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewTabelaProdutos frame = new ViewTabelaProdutos();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public ViewTabelaProdutos() {
		setResizable(false);
		setTitle("Produtos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 509, 373);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		
		JScrollPane scrollpane = new JScrollPane();
		
		txtDescricao = new JTextField();
		txtDescricao.setColumns(10);
		
		txtQuantidade = new JTextField();
		txtQuantidade.setColumns(10);
		
		txtPreco = new JTextField();
		txtPreco.setColumns(10);
		
		JLabel lblDescricao = new JLabel("DESCRIÇÃO");
		JLabel lblQuantidade = new JLabel("QUANTIDADE");
		JLabel lblPreco = new JLabel("PREÇO");
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnCadastrar) {
					if(txtDescricao.getText().isEmpty() || txtQuantidade.getText().isEmpty() || txtPreco.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos");
					} else {
						Produto p = new Produto();
						ProdutoDao dao = new ProdutoDao();
						
						p.setDescricao(txtDescricao.getText());
						p.setQuantidade(Integer.parseInt(txtQuantidade.getText()));
						p.setPreco(Double.parseDouble(txtPreco.getText()));
						
						txtDescricao.setText("");
						txtQuantidade.setText("");
						txtPreco.setText("");
						
						dao.cadastrar(p);
						
						lerTabela();
					}
				}
			}
		});
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int i = tabela.getSelectedRow();
				if(i >= 0) {
					Produto p = new Produto();
					ProdutoDao dao = new ProdutoDao();
					
					p.setId((int) tabela.getValueAt(i, 0));
					dao.excluir(p);
					
					txtDescricao.setText("");
					txtQuantidade.setText("");
					txtPreco.setText("");
					
					lerTabela();
					
				} else {
					JOptionPane.showMessageDialog(null, "Selecione a linha que você deseja excluir");
				}
			}
		});
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int i = tabela.getSelectedRow();
				if(i >= 0) {
					Produto p = new Produto();
					ProdutoDao dao = new ProdutoDao();
					
					p.setDescricao(txtDescricao.getText());
					p.setQuantidade(Integer.parseInt(txtQuantidade.getText()));
					p.setPreco(Double.parseDouble(txtPreco.getText()));
					p.setId((int) tabela.getValueAt(i, 0));
					dao.atualizar(p);
					
					txtDescricao.setText("");
					txtQuantidade.setText("");
					txtPreco.setText("");
					
					lerTabela();
					
				} else {
					JOptionPane.showMessageDialog(null, "Selecione a linha que você deseja atualizar");
				}
				
			}
		});

		GroupLayout gl_contentPane = new GroupLayout(panel);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollpane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(txtDescricao, GroupLayout.PREFERRED_SIZE, 244, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblDescricao))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(txtQuantidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblQuantidade))
								.addGap(6)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lblPreco)
										.addGap(118))
									.addComponent(txtPreco, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(btnCadastrar, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnExcluir, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnAtualizar, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(20)
							.addComponent(txtDescricao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPreco)
								.addComponent(lblQuantidade)
								.addComponent(lblDescricao))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtQuantidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtPreco, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addGap(25)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCadastrar)
						.addComponent(btnExcluir)
						.addComponent(btnAtualizar))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollpane, GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE))
		);
		gl_contentPane.linkSize(SwingConstants.HORIZONTAL, new Component[] {btnCadastrar, btnExcluir, btnAtualizar});
		
		tabela = new JTable();
		
		tabela.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int i = tabela.getSelectedRow();
				if(i >= 0) {
					txtDescricao.setText(tabela.getValueAt(i, 1).toString());
					txtQuantidade.setText(tabela.getValueAt(i, 2).toString());
					txtPreco.setText(tabela.getValueAt(i, 3).toString());
				}
			}
		});
		
		tabela.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				int i  = tabela.getSelectedRow();
				if(i >= 0) {
					txtDescricao.setText(tabela.getValueAt(i, 1).toString());
					txtQuantidade.setText(tabela.getValueAt(i, 2).toString());
					txtPreco.setText(tabela.getValueAt(i, 3).toString());
				}
			}
		});
	
		tabela.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "DESCRIÇÃO", "QUANTIDADE", "PREÇO"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				true, true, true, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		scrollpane.setViewportView(tabela);
		panel.setLayout(gl_contentPane);
		
		DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
		tabela.setRowSorter(new TableRowSorter<DefaultTableModel>(modelo));
		
		lerTabela();
	}
	
	public void lerTabela() {
		DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
		modelo.setNumRows(0);
		ProdutoDao produtoDao = new ProdutoDao();
		
		for(Produto p : produtoDao.ler()) {
			
			modelo.addRow(new Object[] {
					p.getId(),
					p.getDescricao(),
					p.getQuantidade(),
					p.getPreco()
			});
		}
	}
}
